/**
 * 字典表单
 */
layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'layer', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common;


    //执行渲染
    table.render({
        elem: '#itemTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'itemTableId'
        , height: 400 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '状态', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naItemStore', minWidth: 170, title: '题集', fixed: true},
            {field: 'desItemStore', minWidth: 250, title: '描述'},
            {field: 'itemNum', minWidth: 100, title: '题目数量', align: "right"},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#itemBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/item/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

    //执行渲染
    table.render({
        elem: '#itemTable1' //指定原始表格元素选择器（推荐id选择器）
        , id: 'itemTableId1'
        , height: 390 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '状态', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naItemStore', minWidth: 170, title: '题集', fixed: true},
            {field: 'desItemStore', minWidth: 250, title: '描述'},
            {field: 'itemNum', minWidth: 100, title: '题目数量', align: "right"},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#itemBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/item/list'
        , limit: 800
        , even: true
        , page: false
    });

    //执行渲染
    table.render({
        elem: '#itemTable2' //指定原始表格元素选择器（推荐id选择器）
        , id: 'itemTableId3'
        , height: 390 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'fgActive', width: 100, title: '状态', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naItemStore', minWidth: 170, title: '题集', fixed: true},
            {field: 'desItemStore', minWidth: 250, title: '描述'},
            {field: 'itemNum', minWidth: 100, title: '题目数量', align: "right"},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#itemBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/item/list'
        , limit: 800
        , even: true
        , page: false
    });

});

