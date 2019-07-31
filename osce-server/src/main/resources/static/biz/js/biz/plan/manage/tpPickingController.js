layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    tableMerge: 'tableMerge'
}).use(['layer', 'index', 'tableMerge'], function () {
    var $ = layui.$
        , table = layui.table
        , tableMerge = layui.tableMerge;


    //执行渲染
    table.render({
        elem: '#tpPickingTable' //指定原始表格元素选择器（推荐id选择器）
       // , id: 'tpPickingTableId'
        , height: 600 //容器高度
        , toolbar: true
        , title: '领料计划'
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            //{checkbox: true, fixed: true},
            {field: 'planDay', merge: true, width: 170, title: '日期', fixed: true},
            {field: 'planSection',merge: ['planDay', 'planSection'], width: 200, title: '时段'},
            {field: 'naRoom', width: 100, title: '房间'},
            {field: 'naSkillCase', width: 160, title: '技能'},
            {field: 'numSkillCase', width: 100, title: '训练次数'},
            {field: 'fgConsumables', width: 100, title: '设备类型'},
            {field: 'naDevice', width: 170, title: '设备'},
            {field: 'sdDeviceUnit', width: 80, title: '单位'},
            {field: 'numDevice', width: 120, title: '单次消耗数量'},
            {field: 'numPlan', width: 120, title: '总消耗数量'},
        ]] //设置表头
        , url: basePath + '/pf/p/plan/pick/list'
        , limit: 30
        , limits: [30, 50, 100]
        , page: true
        , where: {
            idPlan: idPlan
        }
        ,done: function(){
            tableMerge.render(this)
        }
    });


});



