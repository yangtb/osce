layui.config({
    base: basePath + '/layui/build/js/'
}).use(['layer', 'form', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common;

    if (!common.readLocalStorage('tip-dept-mg')) {
        layer.msg('左侧区域鼠标点击右键可新增分类', {
            time: 20000, //20s后自动关闭
            btnAlign: 'c', //按钮居中
            btn: ['知道了']
        }, function () {
            common.writeLocalStorage("tip-dept-mg", true);
        });
    }

    form.verify({
        commonLength: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        commonLength255: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        },
        sort: function (value) {
            if (value != null || value != '') {
                return;
            }
            if (!value.match(/^[1-9]\d*$/)) {
                $('#sort').focus();
                layer.tips('排序必须是正整数', '#sort', {tips: 1});
                return;
            }
        }
    });

    // ===============  ztree =========================//
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true
        },
        check: {
            enable: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick,
            onRightClick: OnRightClick
        }
    };

    var zNodes = [], zTree, rMenu;
    $(document).ready(function () {
        layer.load(2);
        var bizData = {
            idGrade : currentGrade
        }
        $.ajax({
            url: basePath + '/pf/r/dept/tree',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                zNodes = data.data;
                $.fn.zTree.init($("#departTree"), setting, zNodes);
                zTree = $.fn.zTree.getZTreeObj("departTree");
                rMenu = $("#rMenu");
                return true;
            },
            error: function () {
                layer.closeAll('loading');
                return false;
            }
        });
        var bodyHeight = $(this).height() - $("#treeTitle").height() - 80;
        $("#departTree").css("min-height", bodyHeight);
        $("#departTree").css("max-height", bodyHeight);
    });

    function OnRightClick(event, treeId, treeNode) {
        if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
            zTree.cancelSelectedNode();
            showRMenu("root", event.clientX, event.clientY);
        } else if (treeNode && !treeNode.noR) {
            zTree.selectNode(treeNode);
            showRMenu("node", event.clientX, event.clientY);
        }
    }

    function showRMenu(type, x, y) {
        $("#rMenu").show();
        if (type == "root") {
            $("#m_del").hide();
        } else {
            $("#m_del").show();
        }

        y += document.body.scrollTop;
        x += document.body.scrollLeft;
        rMenu.css({"top": y + "px", "left": x + "px", "visibility": "visible"});

        $("body").bind("mousedown", onBodyMouseDown);
    }

    function hideRMenu() {
        if (rMenu) rMenu.css({"visibility": "hidden"});
        $("body").unbind("mousedown", onBodyMouseDown);
    }

    function onBodyMouseDown(event) {
        if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
            rMenu.css({"visibility": "hidden"});
        }
    }

    // 左侧tree选中全局参数
    var idDepart,
        cd,
        pId,
        idStr,
        name,
        selectedTreeNode;

    /**
     * 递归获取所有节点中值
     * @param treeNode
     * @param result
     * @returns {*}
     */
    function getAllChildrenNodes(treeNode, result) {
        if (treeNode.isParent) {
            var childrenNodes = treeNode.children;
            if (childrenNodes) {
                for (var i = 0; i < childrenNodes.length; i++) {
                    result += ',' + childrenNodes[i].idDepart;
                    result = getAllChildrenNodes(childrenNodes[i], result);
                }
            }
        }
        return result;
    };

    // 选中tree填充表单
    function onClick(e, treeId, treeNode) {
        var parentNode = treeNode.getParentNode();
        var idDepartParName;
        if (parentNode) {
            idDepartParName = parentNode.name;
        }

        //获取选中节点的值
        idStr = getAllChildrenNodes(treeNode, treeNode.idDepart);
        selectedTreeNode = treeNode;

        idDepart = treeNode.idDepart;
        cd = treeNode.id;
        pId = treeNode.pId;
        name = treeNode.name;
        var bizData = {
            idDepart: idDepart
        }

        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/dept/detail',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    $("#departForm").autofill(data.data);
                    $("#idDepartParName").val(idDepartParName);
                    layui.use('form', function () {
                        layui.form.render();
                    });
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("获取机构信息失败");
                return false;
            }
        });
    };


    $("#m_add").on('click', function () {
        hideRMenu();
        $('#reset').trigger('click');
        $('#save').trigger('click');
        var nodes = zTree.getSelectedNodes(),
            selectedTreeNode = nodes[0];
        if (selectedTreeNode) {
            $('#idDepartPar').val(selectedTreeNode.id);
            $('#idDepartParName').val(selectedTreeNode.name);
        }

    });

    $("#m_del").on('click', function () {
        hideRMenu();
        removeTreeNode();
    });

    function removeTreeNode() {
        var nodes = zTree.getSelectedNodes(), msg;

        var msg;
        if (nodes && nodes.length > 0) {
            if (nodes[0].children && nodes[0].children.length > 0) {
                msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。请确认！";
            } else {
                msg = "您确定要删除该节点吗？";
            }
        }

        layer.confirm(msg, {
            title: '删除机构提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            var url = basePath + '/pf/r/dept/del';

            var idStrArr = '[' + getAllChildrenNodes(nodes[0], nodes[0].id) + ']'
            var bizData = {
                list : eval("(" + idStrArr + ")")
            };
            layer.load(2);
            $.ajax({
                url: url,
                type: 'post',
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify(bizData),
                success: function (data) {
                    layer.closeAll();
                    if (data.code != 0) {
                        common.errorMsg(data.msg);
                        return false;
                    } else {
                        zTree.removeNode(nodes[0]);
                        common.sucMsg("删除成功");
                        $('#reset').trigger('click');
                        return true;
                    }
                },
                error: function () {
                    layer.closeAll('loading');
                    common.errorMsg("删除失败");
                    return false;
                }
            });
            return false;
        })
    }

    form.on('submit(addDepart)', function (data) {
        var formType = data.field.idDepart ? 'edit' : 'add';

        var naDepart = data.field.naDepart;
        var idDepartPar = data.field.idDepartPar;

        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/dept/save',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    common.sucChildMsg("保存成功");

                    if (formType == 'edit') {
                        var nodes = zTree.getSelectedNodes()[0];
                        nodes.name = naDepart;
                        zTree.updateNode(nodes);
                        return false;
                    } else {
                        $("#idDepart").val(data.data);
                        var selectedNode = zTree.getSelectedNodes()[0];
                        var newNode = {
                            id: data.data,
                            name: naDepart
                        }
                        if (selectedNode) {
                            newNode.pId = idDepartPar;
                            newNode.checked = selectedNode.checked;
                            zTree.addNodes(selectedNode, newNode);
                        } else {
                            zTree.addNodes(null, newNode);
                        }
                        var node = zTree.getNodeByParam("id", data.data);
                        console.log(node)
                        zTree.selectNode(node);
                        return false;
                    }
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("保存失败");
                return false;
            }
        });
        return false;
    });

    //监听提交
    form.on('submit(deptSearchFilter)', function (data) {
        layer.load(2);
        var bizData = {
            idGrade : data.field.idGrade
        }
        $.ajax({
            url: basePath + '/pf/r/dept/tree',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                zNodes = data.data;
                $.fn.zTree.init($("#departTree"), setting, zNodes);
                return true;
            },
            error: function () {
                layer.closeAll('loading');
                return false;
            }
        });
    });

    var _successFunction = function (data) {
        return function (layero, index) {
            var iframe = window['layui-layer-iframe' + index];
            //调用子页面的全局函数
            iframe.fullForm(data);
        }
    };

});

