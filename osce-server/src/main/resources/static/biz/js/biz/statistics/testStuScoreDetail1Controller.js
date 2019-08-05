layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table'], function () {
    var $ = layui.$
        , table = layui.table

    //执行渲染
    table.render({
        elem: '#stuScore1Table' //指定原始表格元素选择器（推荐id选择器）
        , id: 'stuScore1TableId'
        , height: 'full-20' //容器高度
        , totalRow: true
        , size: 'sm'
        , cols: [[
            {type: 'numbers', title: 'R'},
            {field: 'sdItemCa', width: 100, title: '题目类型', align: 'center', totalRowText: '合计'},
            {field: 'mainItem', minWidth: 200, title: '题干'},
            {field: 'sdItemLevel', width: 100, title: '难度', align: 'center'},
            {field: 'score', width: 100, title: '分值', align: 'right', totalRow: true},
            {field: 'scoreResult', width: 100, title: '得分', align: 'right', totalRow: true},
        ]] //设置表头
        , url: basePath + '/pf/p/statistics/stu/score1/list'
        , where: {
            idExec: idExec
        }
    });


});



