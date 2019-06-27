layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['layer', 'index', 'table'], function () {
    var $ = layui.$
        , table = layui.table;

    //执行渲染
    table.render({
        elem: '#studentTable' //指定原始表格元素选择器（推荐id选择器）
        , toolbar: true
        , title: '学员'
        , height: 580 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'realName',  width: 150, title: '姓名', fixed: true},
            {field: 'phoneNo', width: 120, title: '联系方式'},
            {field: 'naArea', width: 100, title: '考场'},
            {field: 'naStation', width: 160, title: '考站'},
            {field: 'sdStationCaText', width: 100, title: '基地'},
            {field: 'sdSkillCaText', width: 100, title: '技能'},
            {field: 'naRoom', width: 100, title: '房间'},
            {field: 'planTime', width: 150, title: '日期'},
            {field: 'timeBegin', width: 120, title: '开始时间'},
            {field: 'timeEnd', width: 120, title: '结束时间'},
        ]] //设置表头
        , url: basePath + '/pf/p/plan/publish/item/student/list'
        , limit: 5000
        , page: false
        , where: {
            idPlan: idPlan
        }
    });


    //执行渲染
    table.render({
        elem: '#spTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'spTableId'
        , toolbar: true
        , height: 580 //容器高度
        , title: 'SP'
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'realName',  width: 150, title: '姓名', fixed: true},
            {field: 'phoneNo', width: 120, title: '联系方式'},
            {field: 'naArea', width: 100, title: '考场'},
            {field: 'naStation', width: 160, title: '考站'},
            {field: 'sdStationCaText', width: 100, title: '基地'},
            {field: 'sdSkillCaText', width: 100, title: '技能'},
            {field: 'naRoom', width: 100, title: '房间'},
            {field: 'planTime', width: 150, title: '日期'},
        ]] //设置表头
        , url: basePath + '/pf/p/plan/publish/item/sp/list'
        , limit: 5000
        , page: false
        , where: {
            idPlan: idPlan
        }
    });//执行渲染
    table.render({
        elem: '#assistantTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'assistantTableId'
        , toolbar: true
        , title: '考官'
        , height: 580 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'realName',  width: 150, title: '姓名', fixed: true},
            {field: 'phoneNo', width: 120, title: '联系方式'},
            {field: 'naArea', width: 100, title: '考场'},
            {field: 'naStation', width: 160, title: '考站'},
            {field: 'sdStationCaText', width: 100, title: '基地'},
            {field: 'sdSkillCaText', width: 100, title: '技能'},
            {field: 'naRoom', width: 170, title: '房间'},
            {field: 'planTime', width: 150, title: '日期'},
        ]] //设置表头
        , url: basePath + '/pf/p/plan/publish/item/assistant/list'
        , limit: 5000
        , page: false
        , where: {
            idPlan: idPlan
        }
    });


});



