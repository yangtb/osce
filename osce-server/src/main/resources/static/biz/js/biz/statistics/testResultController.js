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
        elem: '#testTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'testTableId'
        , height: 'full-68' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            //-- 考试名称 计划开考时间 计划结束时间 考场数 学生数 考试类型 状态 创建时间
            {field: 'naPlan', minWidth: 170, title: '考试名称', fixed: true},
            {field: 'gmtPlanBegin', minWidth: 170, title: '计划开考时间'},
            {field: 'gmtPlanEnd', width: 170, title: '计划结束时间'},
            {field: 'areaNum', minWidth: 100, align:'right', title: '考场数'},
            {field: 'studentNum', minWidth: 100, align:'right', title: '学生数'},
            {field: 'fgReplan', width: 100, title: '考试类型', align:'center', templet: '#fgReplanTpl'},
            {field: 'sdPlanStatus', minWidth: 165, title: '状态', templet: '#sdPlanStatusTpl'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 160, title: '操作', align: 'center', toolbar: '#testBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/statistics/test/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

    //监听工具条
    table.on('tool(testTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }else if (obj.event === 'del') {
            var currentData = new Array();
            currentData.push(data)
            _delTest(currentData);
        }
    });

    //监听提交
    form.on('submit(testSearchFilter)', function (data) {
        var name = data.field.naPlan;
        table.reload('testTableId', {
            where: {
                naPlan: name
            }
            , height: 'full-68'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('testTableId')
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
        var idPlan = currentEditData ? currentEditData.idPlan : '';
        $('#testRecordHidden').attr('lay-href', basePath + '/pf/p/statistics/test/score/page?idPlan=' + idPlan);
        $('#testRecordHidden').click();
    };

    //监听行双击事件
    table.on('rowDouble(testTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('testTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delTest(data);
    });

    var _delTest = function (currentData) {
        var url = basePath + '/pf/r/statistics/test/del';
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
            title: '删除实训记录提示',
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
                    _testTableReload();
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

    var _testTableReload = function () {
        table.reload('testTableId');
    }


});



