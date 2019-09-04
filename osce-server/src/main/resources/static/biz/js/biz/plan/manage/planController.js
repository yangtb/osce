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
            {field: 'naPlan', minWidth: 170, title: '考试名称', fixed: true},
            {field: 'gmtPlanBegin', minWidth: 170, title: '计划开考时间'},
            {field: 'gmtPlanEnd', minWidth: 170, title: '计划结束时间'},
            {field: 'areaNum', minWidth: 100, title: '考场数', align: 'right'},
            {field: 'studentNum', minWidth: 100, title: '学生数', align: 'right'},
            {field: 'fgReplan', width: 120, title: '考试类型', align: 'center', templet: '#fgReplanTpl'},
            {field: 'sdPlanStatus', minWidth: 170, title: '状态', templet: '#sdPlanStatusTpl'},
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
            editPlan(data);
        }
    });

    //监听提交
    form.on('submit(planSearchFilter)', function (data) {
        var name = data.field.naPlan;
        table.reload('planTableId', {
            where: {
                naPlan: name
            }
            , height: 'full-68'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    $('#add').on('click', function () {
        editPlan();
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('planTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#edit', {tips: 1});
            return;
        }
        if (data.length > 1) {
            layer.tips('请选中一行记录进行编辑', '#edit', {tips: 1});
            return;
        }
        editPlan(data[0]);
    });

    function editPlan(data) {
        var idPlan = data ? data.idPlan : '';
        if (idPlan) {
            $('#editPlan').text("编辑[" + data.naPlan + "]计划");
        } else {
            $('#editPlan').text("新增实训计划");
        }
        $('#editPlan').attr('lay-href', basePath + '/pf/p/plan/manage/form?idPlan=' + idPlan);
        $('#editPlan').click();
    }

    //监听行双击事件
    table.on('rowDouble(planTableFilter)', function (obj) {
        editPlan(obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('planTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delPlan(data);
    });

    var _delPlan = function (currentData) {
        var url = basePath + '/pf/r/plan/manage/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naPlan + '】';
            reqData.push(content.idPlan);
        });


        var data = {};
        data.list = reqData;
        data.status = '1';
        layer.confirm('确定删除' + messageTitle + '么？', {
            title: '删除计划提示',
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

    $('#endExam').on('click', function () {
        var checkStatus = table.checkStatus('planTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#endExam', {tips: 1});
            return;
        }

        var url = basePath + '/pf/r/plan/manage/end';
        var reqData = new Array();
        var messageTitle = '';
        $.each(data, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naPlan + '】';
            reqData.push(content.idPlan);
        });


        var data = {};
        data.list = reqData;
        data.status = '1';
        layer.confirm('确定结束考试' + messageTitle + '么？', {
            title: '结束考试提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            _commonAjax(index, url, data, "结束考试");
        })

    });

});



