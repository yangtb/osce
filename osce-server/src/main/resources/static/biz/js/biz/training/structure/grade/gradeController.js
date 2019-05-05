layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'common'], function () {
    var $ = layui.$,
        table = layui.table,
        form = layui.form,
        common = layui.common;

    //执行渲染
    table.render({
        elem: '#gradeTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'gradeTableId'
        , height: 'full-68' //容器高度
        , cols: [[
            {checkbox: true, fixed: true},
            {field: 'naGrade', minWidth: 170, title: '学届名称', fixed: true},
            {field: 'desGrade', minWidth: 200, title: '学届描述'},
            {field: 'gradeNum', minWidth: 100, title: '班级数'},
            {field: 'studentNum', minWidth: 100, title: '学员数'},
            {field: 'currentGrade', minWidth: 80, title: '当前学届'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 170, title: '操作', align: 'center', toolbar: '#gradeBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/grade/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

});

