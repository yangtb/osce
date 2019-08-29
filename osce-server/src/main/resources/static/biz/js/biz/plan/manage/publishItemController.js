layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    tableMerge: 'tableMerge',
}).use(['layer', 'index', 'table', 'tableMerge'], function () {
    var $ = layui.$
        , table = layui.table
        , tableMerge = layui.tableMerge;

    table.render({
        elem: '#studentTable' //指定原始表格元素选择器（推荐id选择器）
        , toolbar: true
        , title: '学员'
        , height: 580 //容器高度
        //, size: 'sm'
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'planTime', width: 150, title: '日期', merge: true},
            {field: 'naArea', width: 100, title: '考场', merge: ['planTime','naArea']},
            {field: 'naStation', width: 120, title: '考站', merge: ['planTime', 'naArea' ,'naStation']},
            {field: 'sdStationCaText', width: 100, title: '基地', merge: ['planTime', 'naArea' ,'naStation', 'sdStationCaText']},
            {field: 'naRoom', width: 80, title: '房间', merge: ['planTime', 'naArea' ,'naStation', 'sdStationCaText', 'naRoom']},
            {field: 'timeBegin', width: 120, title: '开始时间'},
            {field: 'timeEnd', width: 120, title: '结束时间'},
            {field: 'sdSkillCaText', width: 100, title: '技能'},
            {field: 'realName',  width: 150, title: '姓名'},
            {field: 'phoneNo', width: 120, title: '联系方式'},
        ]] //设置表头
        , url: basePath + '/pf/p/plan/publish/item/student/list?idPlan=' + idPlan
        , limit: 5000
        , page: false
        , done: function () {
            tableMerge.render(this)
        }
    });


    //执行渲染
    table.render({
        elem: '#spTable' //指定原始表格元素选择器（推荐id选择器）
        , toolbar: true
        , height: 580 //容器高度
        , title: 'SP'
        //, size: 'sm'
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'planTime', width: 150, title: '日期', merge: true},
            {field: 'naArea', width: 100, title: '考场', merge: ['planTime','naArea']},
            {field: 'naStation', width: 120, title: '考站', merge: ['planTime', 'naArea' ,'naStation']},
            {field: 'sdStationCaText', width: 100, title: '基地', merge: ['planTime', 'naArea' ,'naStation', 'sdStationCaText']},
            {field: 'naRoom', width: 80, title: '房间', merge: ['planTime', 'naArea' ,'naStation', 'sdStationCaText', 'naRoom']},
            {field: 'sdSkillCaText', width: 100, title: '技能'},
            {field: 'realName',  width: 150, title: '姓名'},
            {field: 'phoneNo', width: 120, title: '联系方式'},
        ]] //设置表头
        , url: basePath + '/pf/p/plan/publish/item/sp/list?idPlan=' + idPlan
        , limit: 5000
        , page: false
        , done: function () {
            tableMerge.render(this)
        }
    });
    //执行渲染
    table.render({
        elem: '#assistantTable' //指定原始表格元素选择器（推荐id选择器）
        , toolbar: true
        , title: '考官'
        , height: 580 //容器高度
        //, size: 'sm'
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'planTime', width: 150, title: '日期', merge: true},
            {field: 'naArea', width: 100, title: '考场', merge: ['planTime','naArea']},
            {field: 'naStation', width: 120, title: '考站', merge: ['planTime', 'naArea' ,'naStation']},
            {field: 'sdStationCaText', width: 100, title: '基地', merge: ['planTime', 'naArea' ,'naStation', 'sdStationCaText']},
            {field: 'naRoom', width: 80, title: '房间', merge: ['planTime', 'naArea' ,'naStation', 'sdStationCaText', 'naRoom']},
            {field: 'sdSkillCaText', width: 100, title: '技能'},
            {field: 'realName',  width: 150, title: '姓名'},
            {field: 'manageType', width: 100, title: '类别'},
            {field: 'phoneNo', width: 120, title: '联系方式'},
        ]] //设置表头
        , url: basePath + '/pf/p/plan/publish/item/assistant/list?idPlan=' + idPlan
        , limit: 5000
        , page: false
        , done: function () {
            tableMerge.render(this)
        }
    });


});



