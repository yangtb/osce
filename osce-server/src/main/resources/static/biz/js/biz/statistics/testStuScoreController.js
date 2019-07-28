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
        elem: '#testScoreTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'testScoreTableId'
        , height: '700' //容器高度
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
    table.on('tool(testScoreTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            openScoreDetail(data);
        }
    });

    function openScoreDetail(data) {

        var index = common.open('学员【张三】成绩', basePath + '/pf/p/statistics/test/score/detail/page', 800, 500);
        //layer.full(index)
    }
    

    var _testScoreTableReload = function () {
        table.reload('testScoreTableId');
    }


});



