layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , common = layui.common;

    //执行渲染
    table.render({
        elem: '#testScoreTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'testScoreTableId'
        , height: '700' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'naDepart', minWidth: 120, title: '班级', align: 'center'},
            {field: 'realName', minWidth: 120, title: '姓名', align: 'center'},
            {field: 'sex', width: 80, title: '性别', align: 'center'},
            {field: 'phoneNo', width: 170, title: '手机号'},
            {field: 'naArea', width: 120, title: '考场', align: 'center'},
            {field: 'sdStationCaText', width: 120, title: '科目'},
            {field: 'idPaperText', width: 170, title: '试题'},
            {field: 'scoreSum', width: 100, title: '评分', align: 'right'},
            {fixed: 'right', width: 160, title: '操作', align: 'center', toolbar: '#testBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/statistics/test/score/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
        , where: {
            idPlan: idPlan
        }
    });

    //监听工具条
    table.on('tool(testScoreTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            openScoreDetail(data);
        }
    });

    function openScoreDetail(data) {
        if (data.sdSkillCa == '1') {
            common.open('学员【' + data.realName + '】成绩（<span style="color: #1E9FFF">' + data.scoreSum + '</span> 分）',
                basePath + '/pf/p/statistics/test/score/detail1/page?idExec=' + data.idExec, 800, 500)
        } else {
            var index = common.open('学员【' + data.realName + '】成绩（<span style="color: #1E9FFF">' + data.scoreSum + '</span> 分）',
                basePath + '/pf/p/statistics/test/score/detail/page?idExec=' + data.idExec, 800, 500);
        }
        //layer.full(index)
    }


});



