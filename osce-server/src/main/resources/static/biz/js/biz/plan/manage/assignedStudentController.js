layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'common', 'tableSelect'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common
        , tableSelect = layui.tableSelect;


    tableSelect.render({
        elem: '#selectStudent',
        checkedKey: 'naDevice',
        searchKey: 'naDevice',
        searchPlaceholder: '请输入设备名称',
        table: {
            url: basePath + '/pf/p/model/list',
            height: 400,
            width: 800,
            cols: [[
                {checkbox: true, fixed: true},
                {field: 'naDevice', minWidth: 170, title: '班级'},
                {field: 'desDevice', minWidth: 150, title: '账号'},
                {field: 'sdDeviceUnit', minWidth: 120, title: '姓名'},
                {field: 'sdDeviceUnit', minWidth: 120, title: '性别'},
                {field: 'sdDeviceUnit', minWidth: 120, title: '手机号'},
                {field: 'sdDeviceUnit', minWidth: 120, title: '身份证号'},
            ]]
            , limits: [10, 20, 50]
            , page: true
        },
        done: function (elem, data) {
            var bizData = data.data[0];
            if (bizData) {
                $('#idDevice').val(bizData.idDevice);
                $('#naDevice').val(bizData.naDevice);
            }
        }
    });

    //执行渲染
    table.render({
        elem: '#assignedStudentTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'assignedStudentTableId'
        , height: 600 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '当前学届', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naGrade', minWidth: 170, title: '学届名称', fixed: true},
            {field: 'desGrade', minWidth: 200, title: '学届描述'},
            {field: 'assignedStudentNum', minWidth: 100, title: '班级数'},
            {field: 'studentNum', minWidth: 100, title: '学员数'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
        ]] //设置表头
        , url: basePath + '/pf/p/assignedStudent/list'
        , limit: 500
        , even: true
        //, limits: [15, 30, 100]
        , page: false
    });

    //监听工具条
    table.on('tool(assignedStudentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    //监听提交
    form.on('submit(assignedStudentSearchFilter)', function (data) {
        var name = data.field.naGrade;
        var fgActive = data.field.fgActive;
        table.reload('assignedStudentTableId', {
            where: {
                naGrade: name
            }
            , height: 'full-68'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('assignedStudentTableId')
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
            common.open('新增学届', basePath + '/pf/p/assignedStudent/form?formType=' + formType, 430, 255);
        } else {
            common.open('编辑学届', basePath + '/pf/p/assignedStudent/form?formType=' + formType, 430, 255, _successFunction(currentEditData));
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
    table.on('rowDouble(assignedStudentTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('assignedStudentTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delGrade(data);
    });

    var _delGrade = function (currentData) {
        var url = basePath + '/pf/r/assignedStudent/del';
        var reqData = new Array();
        var messageTitle = '';
        var delFlag = false, delMsg = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naGrade + '】';
            reqData.push(content.idGrade);

            if (content.assignedStudentNum > 0) {
                delFlag = true;
                delMsg += '【' + content.naGrade + '】';
            }
        });

        if (delFlag) {
            layer.alert(delMsg + '<br><span style="color: red; font-weight: bold">学届下已有班级，不允许删除，请重新选择操作</span>', {
                title: '删除学届提示',
                resize: false,
                btn: ['确定']
            });
            return false;
        }

        var data = {};
        data.list = reqData;
        data.status = '1';
        layer.confirm('确定删除' + messageTitle + '么？', {
            title: '删除学届提示',
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
                    _assignedStudentTableReload();
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

    var _assignedStudentTableReload = function () {
        table.reload('assignedStudentTableId', {
            where: {
                //type: type
            },
            height: 'full-68'
        });
    }


});



