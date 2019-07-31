layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'form', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common;

    //执行渲染
    table.render({
        elem: '#templateTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'templateTableId'
        , height: 'full-68' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '状态', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naModel', minWidth: 170, title: '模板名称', fixed: true},
            {field: 'fgChild', width: 100, title: '模板类型', align: 'center', templet: '#fgChildTpl'},
            {field: 'numStudentMax', minWidth: 100, title: '最大学生数', align: 'right'},
            {field: 'numArea', width: 90, title: '考场数', align: 'right'},
            {field: 'weightManager', minWidth: 150, title: '主考官权重（%）', align: 'right'},
            {field: 'weightAssistant', minWidth: 160, title: '现场考官权重（%）', align: 'right'},
            {field: 'weightRemote', minWidth: 160, title: '中控考官权重（%）', align: 'right'},
            {field: 'minInterval', minWidth: 165, title: '考试间隔时间（min）', align: 'right'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#templateBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/plan/template/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

    //监听工具条
    table.on('tool(templateTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    //监听提交
    form.on('submit(templateSearchFilter)', function (data) {
        var name = data.field.naGrade;
        var fgActive = data.field.fgActive;
        table.reload('templateTableId', {
            where: {
                naGrade: name
            }
            , height: 'full-68'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    $('#add').on('click', function () {
        _addOrEdit("add");
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('templateTableId')
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
        var idModel = currentEditData ? currentEditData.idModel : '';
        $('#editTemplate').attr('lay-href', basePath + '/pf/p/plan/template/form?idModel=' + idModel);
        $('#editTemplate').click();
    };

    //监听行双击事件
    table.on('rowDouble(templateTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('templateTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delGrade(data);
    });

    var _delGrade = function (currentData) {
        var url = basePath + '/pf/r/plan/template/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naModel + '】';
            reqData.push(content.idModel);

        });

        var data = {};
        data.list = reqData;
        data.status = '1';
        layer.confirm('确定删除' + messageTitle + '么？', {
            title: '删除实训模板提示',
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
                    _templateTableReload();
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

    var _templateTableReload = function () {
        table.reload('templateTableId', {
            where: {
                //type: type
            },
            height: 'full-68'
        });
    }

    //监听删除操作
    form.on('switch(fgActiveCheckFilter)', function (obj) {
        var reqData = new Array();
        var bizData = {};
        reqData.push(this.value);
        bizData.list = reqData;
        if (obj.elem.checked) {
            bizData.status = '1';
        } else {
            bizData.status = '0';
        }
        $.ajax({
            url: basePath + '/pf/r/plan/template/updateStatus',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.tips(data.msg, obj.othis);
                    return false;
                } else {
                    layer.tips("设置成功", obj.othis);
                    return true;
                }
            },
            error: function () {
                common.errorMsg("设置失败");
                return false;
            }
        });
    });


    $('#designExamPaper').on('click', function () {
        var checkStatus = table.checkStatus('templateTableId')
            , data = checkStatus.data;
        if (data.length != 1) {
            layer.tips('请先选中一个模板', '#designExamPaper', {tips: 1});
            return;
        }
        var currentEditData = data[0];
        $('#designExamPaperHidden').attr('lay-href',
            basePath + '/pf/p/plan/paper/page?idModel=' + currentEditData.idModel);
        $('#designExamPaperHidden').click();
    });


    $('#createExam').on('click', function () {
        var checkStatus = table.checkStatus('templateTableId')
            , data = checkStatus.data;
        if (data.length != 1) {
            layer.tips('请先选中一个模板', '#createExam', {tips: 1});
            return;
        }
        var currentEditData = data[0];
        $('#editPlan').attr('lay-href',
            basePath + '/pf/p/plan/manage/form?idModelFrom=' + currentEditData.idModel);
        $('#editPlan').click();
    });

});



