layui.config({
    base: basePath + '/layui/build/js/'
}).extend({
    excel: 'layui_exts/excel.min'
}).use(['layer', 'form', 'table', 'jquery', 'common', 'excel', 'laytpl', 'element'], function () {
    var $ = layui.$,
        form = layui.form
        , table = layui.table
        , common = layui.common
        , layer = layui.layer
        , excel = layui.excel
        , laytpl = layui.laytpl
        , element = layui.element;

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
        layer.load(2);
        var bizData = {
            idGrade: currentGrade
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
                return true;
            },
            error: function () {
                layer.closeAll('loading');
                return false;
            }
        });
        var bodyHeight = $(this).height() - $("#treeTitle").height() - 70;
        $("#departTree").css("min-height", bodyHeight);
        $("#departTree").css("max-height", bodyHeight);
    });


    form.on('select(idGradeCurrentFilter)', function(data){
        $('#queryDept').trigger('click');
        table.reload('studentTableId', {
            where: {
                idGrade : $('#idGrade').val(),
                idDepart : ''
            }
        });
    });

    //监听提交
    form.on('submit(deptSearchFilter)', function (data) {
        layer.load(2);
        var bizData = {
            idGrade: data.field.idGrade
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

    var currentIdDepart, currentNaDepart;
    // 选中tree
    function onClick(e, treeId, treeNode) {

        currentIdDepart = treeNode.id;
        currentNaDepart = treeNode.name;

        table.reload('studentTableId', {
            where: {
                idDepart: treeNode.id
            }
            , height: 'full-150'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    };

    //监听提交
    form.on('submit(studentSearchFilter)', function (data) {
        table.reload('studentTableId', {
            where: {
                keywords: data.field.keywords
            }
            , height: 'full-150'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    //执行渲染
    table.render({
        elem: '#studentTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'studentTableId'
        , height: 'full-150' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'naDepart', minWidth: 160, title: '班级', fixed: true},
            {field: 'username', minWidth: 130, title: '账号'},
            {field: 'realName', minWidth: 140, title: '姓名'},
            {field: 'sex', minWidth: 80, title: '性别', templet: '#sexTpl'},
            {field: 'phoneNo', minWidth: 150, title: '手机号'},
            {field: 'idcard', minWidth: 200, title: '身份证号'},
            {field: 'enabled', width: 80, title: '状态',align : 'center', templet: '#enabledTpl'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#studentBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/student/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
        , where: {
            idGrade : $('#idGrade').val()
        }
    });

    //监听工具条
    table.on('tool(studentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    $('#add').on('click', function () {
        _addOrEdit("add");
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('studentTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#edit', {tips: 1});
            return;
        }
        if (data.length > 1) {
            layer.tips('请选中一行记录进行编辑', '#edit', {tips: 1});
            return;
        }
        var currentEditData = data[0];
        _addOrEdit("edit", currentEditData);
    });

    var _addOrEdit = function (formType, currentEditData) {
        if (formType == 'add') {
            common.open('新增学员', basePath + '/pf/p/student/form?formType=' + formType + '&idGrade=' + $('#idGrade').val(), 700, 415);
        } else {
            common.open('编辑学员', basePath + '/pf/p/student/form?formType=' + formType, 700, 415, _successFunction(currentEditData));
        }
    };

    var _successFunction = function (data) {
        return function (layero, index) {
            var iframe = window['layui-layer-iframe' + index];
            //调用子页面的全局函数
            iframe.fullForm(data);
        }
    };

    //监听行双击事件
    table.on('rowDouble(studentTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('studentTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delStudent(data);
    });

    var _delStudent = function (currentData) {
        var url = basePath + '/pf/r/student/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.realName + '】';
            reqData.push(content.idStudentDepart);
        });
        var data = {};
        data.list = reqData;
        data.status = '1';
        layer.confirm('确定学员' + messageTitle + '么？', {
            title: '删除学员提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            _commonAjax(index, url, data, "删除");
        })
    }

    var _commonAjax = function (index, url, reqData, msg) {
        layer.load(2);
        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    common.sucChildMsg(msg + "成功");
                    if (index) {
                        layer.close(index);
                    }
                    _studentReload();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg(msg + "失败");
                return false;
            }
        });
    }

    var _studentReload = function () {
        table.reload('studentTableId', {
            where: {
                //type: type
            },
            height: 'full-150'
        });
    }

    // 模板下载
    $("#templateDownLoad").on('click', function () {
        document.getElementById("exportForm").submit();
    });

    // 模板上传
    $(function(){
        // 监听上传文件的事件
        $('#itemImport').change(function(e) {
            var files = e.target.files;
            uploadExcel(files);
        });
        // 文件拖拽
        $('body')[0].ondragover = function(e) {
            e.preventDefault();
        }
        $('body')[0].ondrop = function(e) {
            e.preventDefault();
            var files = e.dataTransfer.files;
            uploadExcel(files);
        }
    });

    /**
     * 上传excel的处理函数，传入文件对象数组
     * @param  {[type]} files [description]
     * @return {[type]}       [description]
     */
    function uploadExcel(files) {
        if (!currentIdDepart) {
            layer.alert("请先在左侧选择中一个班级");
            $('#itemImport').val('');
            return;
        }

        var naGrade = $("#idGrade").find("option:selected").text();

        try {
            excel.importExcel(files, {
                // 读取数据的同时梳理数据
                fields: {
                    'realName': 'A'
                    ,'sexStr': 'B'
                    ,'idcard': 'C'
                    ,'phoneNo': 'D'
                    ,'userCd': 'E'
                    ,'email': 'F'
                }
            }, function(data) {
                // 如果不需要展示直接上传，可以再次 $.ajax() 将JSON数据通过 JSON.stringify() 处理后传递到后端即可
                layer.open({
                    title: '文件转换结果'
                    ,area: ['980px', '500px']
                    ,tipsMore: true
                    ,content: laytpl($('#LAY-excel-export-ans').html()).render({data: data, naGrade: naGrade, naDepart: currentNaDepart, files: files})
                    ,success: function() {
                        element.render('tab');
                    }
                    ,yes: function(index, layero){
                        //console.log(JSON.stringify(data));
                        bachImportStudent(data);
                        layer.close(index); //如果设定了yes回调，需进行手工关闭
                    }
                    ,end: function(){
                        $('#itemImport').val('');
                    }
                });

            });
        } catch (e) {
            $('#itemImport').val('')
            layer.alert(e.message);
        }
    };

    function bachImportStudent(data) {
        var stuArr = new Array();
        for (var key in data) {
            var dataList = data[key];

            for (var key1 in dataList) {
                $.each(dataList[key1], function (index, content) {
                    if (index != 0) {
                        if (content.sexStr == '男') {
                            content.sex = 1;
                        } else if (content.sexStr == '女') {
                            content.sex = 2;
                        }
                        stuArr.push(content);
                    }
                });
            }
        }

        var reqData = {
            idGrade: $("#idGrade").val(),
            idDepart: currentIdDepart,
            users: stuArr
        }

        layer.msg('正在导入数据，请耐心等待......', {
            icon: 16,
            shade: 0.01,
            time: false
        });
        $.ajax({
            url: basePath + '/pf/r/student/bach/add',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            success: function (data) {
                layer.closeAll();
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    var sucData = data.data;
                    if (sucData.length == 0) {
                        layer.msg("导入成功");
                    } else {
                        var msg = '';
                        var contentTable = '<table class="layui-table">\n' +
                            '    <colgroup>\n' +
                            '      <col width="150">\n' +
                            '      <col>\n' +
                            '    </colgroup>\n' +
                            '    <thead>\n' +
                            '      <tr>\n' +
                            '        <th>姓名</th>\n' +
                            '        <th>错误信息</th>\n' +
                            '      </tr> \n' +
                            '    </thead>\n' +
                            '    <tbody>\n';
                        $.each(sucData, function (index, content) {
                            contentTable +=
                                '      <tr>\n' +
                                '        <td>' + content.realName + '</td>\n' +
                                '        <td>' + content.importErrorMsg + '</td>\n' +
                                '      </tr>\n';
                        });
                        contentTable +=  '    </tbody>\n' +
                            '  </table>';


                        layer.open({
                            title: '导入学员数据失败',
                            type: 1,
                            anim: 5,
                            resize: false,
                            area: ['500px', '350px'],
                            content: '<div style="margin: 10px">\n' + contentTable + '</div>'
                        });


                        /*$.each(sucData, function (index, content) {
                            if (msg) {
                                msg += ', ';
                            }
                            msg += content.realName;
                        });
                        layer.alert('导入学员【' + msg + '】数据失败');*/
                    }
                    _studentReload();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('');
                layer.msg("网络异常");
                return false;
            }
        });
    }




    $('#moveStudent').on('click', function () {

        layui.layer.open({
            title: '<b>往届学员迁移</b>',
            //skin: 'layui-layer-molv', //样式类名
            type: 2,
            area: ['900px', '520px'],
            fixed: false, //不固定
            maxmin: false,
            content: basePath + '/pf/p/student/move/page',
            shadeClose: true
        });
    });


});

