layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'form', 'jquery', 'step', 'element', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , common = layui.common
        , form = layui.form
        , step = layui.step
        , element = layui.element;

    step.render({
        elem: '#stepForm',
        filter: 'stepForm',
        width: '100%', //设置容器宽度
        stepWidth: '680px',
        height: '500px',
        stepItems: [{
            title: '选择题集'
        }, {
            title: '试卷参数'
        }, {
            title: '设置必考题'
        }, {
            title: '生成试卷'
        }]
    });


    form.on('submit(formStep)', function (data) {
        step.next('#stepForm');
        return false;
    });

    form.on('submit(formStep2)', function (data) {
        step.next('#stepForm');
        return false;
    });

    form.on('submit(formStep3)', function (data) {
        step.next('#stepForm');
        return false;
    });

    $('.pre').click(function () {
        step.pre('#stepForm');
    });

    $('.next').click(function () {
        step.next('#stepForm');
    });

    $("#importItem").on('click', function () {
        $('#importItemHidden').click();
    });

    //执行渲染
    table.render({
        elem: '#itemTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'itemTableId'
        , height: 440 //容器高度
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

    // 导入试题
    $("#importItem").on('click', function () {
        var url = basePath + 'pf/p/item/page';
    });

});

