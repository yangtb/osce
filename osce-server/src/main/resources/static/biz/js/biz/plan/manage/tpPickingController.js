layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common;


    //执行渲染
    table.render({
        elem: '#tpPickingTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'tpPickingTableId'
        , height: 600 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '当前学届', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naGrade', minWidth: 170, title: '学届名称', fixed: true},
            {field: 'desGrade', minWidth: 200, title: '学届描述'},
            {field: 'tpPickingNum', minWidth: 100, title: '班级数'},
            {field: 'studentNum', minWidth: 100, title: '学员数'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
        ]] //设置表头
        , url: basePath + '/pf/p/tpPicking/list'
        , limit: 500
        , even: true
        //, limits: [15, 30, 100]
        , page: false
    });


});



