layui.config({
    base: basePath + '/layui/build/js/'
}).extend({
    formSelects: 'formSelects-v4'
}).use(['layer', 'form', 'table', 'jquery', 'common', 'transfer', 'util', 'formSelects'], function () {
    var $ = layui.$
        , common = layui.common
        , layer = layui.layer
        , transfer = layui.transfer
        , formSelects = layui.formSelects;

    transfer.render({
        elem: '#test4'
        , id: 'key123'
        //, data: data1
        , title: ['当前班级学员', '待迁移学员']
        , showSearch: true
        , width: 285
        , height: 417
    })

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
            onClick: onClick
        }
    };

    var zNodes = [], zTree;
    $(document).ready(function () {
        loadLeftTree()
        loadTreeSelect();
    });

    function loadLeftTree() {
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/grade/list/all',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                // console.log(data)
                loadDept(data.data);
                return true;
            },
            error: function () {
                layer.closeAll('loading');
                return false;
            }
        });
    }

    function loadDept(gradeData) {
        if (!gradeData) {
            return;
        }

        layer.load(2);
        var bizData = {
            idGrade: ''
        }
        $.ajax({
            url: basePath + '/pf/r/dept/tree',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                zNodes = buildZtreeData(gradeData, data.data);
                $.fn.zTree.init($("#departTree"), setting, zNodes);
                zTree = $.fn.zTree.getZTreeObj("departTree");
                return true;
            },
            error: function () {
                layer.closeAll('loading');
                return false;
            }
        });
        var bodyHeight = $(this).height() - $("#treeTitle").height() - 60;
        $("#departTree").css("min-height", bodyHeight);
        $("#departTree").css("max-height", bodyHeight);
    }

    function loadTreeSelect() {
        $.ajax({
            url: basePath + '/pf/r/grade/list/all/tree',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if (data.data) {
                    renderDeptSelect(data.data)
                }
                return true;
            },
            error: function () {
                layer.closeAll('loading');
                return false;
            }
        });
    }

    function buildZtreeData(gradeData, deptData) {
        var nodeList = new Array();
        $.each(gradeData, function (index, content) {
            var node = {
                checked: false,
                id: 'g-' + content.idGrade,
                name: content.naGrade,
                noR: false,
                nocheck: false,
                open: true
            }
            nodeList.push(node);
        });
        $.each(deptData, function (index, content) {
            if (!content.pId) {
                $.each(gradeData, function (index1, content1) {
                    if (content1.idGrade == content.idGrade) {
                        content.pId = 'g-' + content1.idGrade;
                    }
                });
            }
            nodeList.push(content);
        });
        return nodeList;
    }

    function renderDeptSelect(nodeList) {
        formSelects.data('select1', 'local', {
            arr: nodeList
        });
    }

    // 选中tree
    function onClick(e, treeId, treeNode) {
        // console.log(treeNode)
        if (treeNode.sdDepartCa && treeNode.sdDepartCa == '3') {
            layer.load(2);
            var bizData = {
                idDepart: treeNode.id
            }
            $.ajax({
                url: basePath + '/pf/r/student/list/byGradeId',
                type: 'post',
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify(bizData),
                success: function (data) {
                    layer.closeAll('loading');
                    // console.log(data.data)
                    var data1 = [];
                    if (data.data) {

                        $.each(data.data, function (index, content) {
                            var sexStr = content.sex == '1' ? '男' : '女';
                            var stuData = {
                                "value": content.userId,
                                "title": content.realName + '（' + sexStr + '）'  + content.phoneNo
                            }
                            data1.push(stuData)
                        });
                    }
                    transfer.reload('key123', {
                        title: ['[' + treeNode.name + '] 学员', '待迁移学员']
                        , data: data1
                        , showSearch: true
                    })
                    return true;
                },
                error: function () {
                    layer.closeAll('loading');
                    return false;
                }
            });
        }
    };


    $('#closeMoveWind').on('click', function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    $('#moveStudent').on('click', function () {
        var depts = formSelects.value('select1');
        if (!depts || depts.length == 0) {
            layer.tips('请选目标班级', '#idDepart', {tips: 1});
            return;
        }

        var idDeparts = new Array()
            , messageTitle = '';
        $.each(depts, function (index, content) {
            if (content.sdDepartCa == '3') {
                // console.log(content)
                if (messageTitle) {
                    messageTitle += ', ';
                }
                messageTitle += '【' + content.name + '】';
                idDeparts.push(content.value);
            }
        });
        if (idDeparts.length == 0) {
            layer.tips('必须选泽班级', '#idDepart', {tips: 1});
            return;
        }

        var idUsers = new Array();
        var getData = transfer.getData('key123'); //获取右侧数据
        if (!getData || getData.length == 0) {
            layer.msg("无待迁移学员");
            return;
        }

        $.each(getData, function (index, content) {
            idUsers.push(content.value);
        });

        layer.confirm('学员将迁移至班级' + messageTitle + '，点击"确定"继续', {
            title: '学员迁移提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {

            layer.load(2);
            var bizData = {
                idUsers : idUsers ,
                idDeparts : idDeparts
            }
            $.ajax({
                url: basePath + '/pf/r/student/move',
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
                        layer.msg("学员迁移成功");
                    }
                    return true;
                },
                error: function () {
                    layer.closeAll('loading');
                    return false;
                }
            });
        });
    });



});

