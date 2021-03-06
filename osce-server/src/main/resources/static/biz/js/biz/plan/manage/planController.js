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
        elem: '#planTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'planTableId'
        , height: 'full-68' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'naGrade', minWidth: 170, title: '考试名称', fixed: true},
            {field: 'desGrade', minWidth: 170, title: '计划开考时间'},
            {field: 'desGrade', minWidth: 170, title: '计划结束时间'},
            {field: 'planNum', minWidth: 100, title: '考场数'},
            {field: 'studentNum', minWidth: 100, title: '学生数'},
            {field: 'gmtCreate', minWidth: 170, title: '考试类型'},
            {field: 'gmtCreate', minWidth: 170, title: '状态'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#planBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/plan/manage/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

    //监听工具条
    table.on('tool(planTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    //监听提交
    form.on('submit(planSearchFilter)', function (data) {
        var name = data.field.naGrade;
        table.reload('planTableId', {
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
        var checkStatus = table.checkStatus('planTableId')
            , data = checkStatus.data;
        /*if (data.length == 0) {
            layer.tips('请先选中一行记录', '#edit', {tips: 1});
            return;
        }
        if (data.length > 1) {
            layer.tips('请选中一行记录进行编辑', '#edit', {tips: 1});
            return;
        }*/
        var currentEditData = data[0];
        //_addOrEdit("edit", currentEditData);
        $('#editPlan').attr('lay-href', basePath + '/pf/p/plan/manage/form');
        $('#editPlan').click();
    });


    var _addOrEdit = function (formType, currentEditData) {
        if (formType == 'add') {
            common.open('新增学届', basePath + '/pf/p/plan/form?formType=' + formType, 430, 255);
        } else {
            common.open('编辑学届', basePath + '/pf/p/plan/form?formType=' + formType, 430, 255, _successFunction(currentEditData));
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
    table.on('rowDouble(planTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('planTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delGrade(data);
    });

    var _delGrade = function (currentData) {
        var url = basePath + '/pf/r/plan/del';
        var reqData = new Array();
        var messageTitle = '';
        var delFlag = false, delMsg = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naGrade + '】';
            reqData.push(content.idGrade);

            if (content.planNum > 0) {
                delFlag = true;
                delMsg += '【' + content.naGrade + '】';
            }
        });

        if(delFlag) {
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
                    _planTableReload();
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

    var _planTableReload = function () {
        table.reload('planTableId', {
            where: {
                //type: type
            },
            height: 'full-68'
        });
    }

});



