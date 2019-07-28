layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'form', 'jquery', 'element', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , element = layui.element
        , common = layui.common;

    $(document).ready(function () {
        loadTab1();
    });


    element.on('tab(scoreTabFilter)', function (data) {
        if (data.index == 0) {
            loadTab1();
        } else if (data.index == 1) {
            loadTab2();
        } else if (data.index == 2) {
            loadTab3();
        }
    });

    function loadTab1() {
        table.render({
            elem: '#score1Table'
            , id: 'score1TableId'
            , size : 'sm'
            , url: basePath + '/pf/p/monitor/area/list/end'
            , height: 'full-180'
            //, skin: 'line'
            , cols: [[
                {type: 'numbers', title: 'R'}
                , {field: 'realName', minWidth: 150, title: '评分项'}
                , {field: 'noReg', minWidth: 100, title: '分类'}
                , {field: 'gmtReg', minWidth: 140, title: '内容'}
                , {field: 'phoneNo', minWidth: 140, title: '评分'}
            ]]
            , where: {
                idPlan: 1,
                idArea: 1,
                timeSection: 1
            }
        });
    }

    function loadTab2() {
        table.render({
            elem: '#score2Table'
            , id: 'score2TableId'
            , size : 'sm'
            , url: basePath + '/pf/p/monitor/area/list/end'
            , height: 'full-180'
            //, skin: 'line'
            , cols: [[
                {type: 'numbers', title: 'R'}
                , {field: 'realName', minWidth: 150, title: '评分项'}
                , {field: 'noReg', minWidth: 100, title: '分类'}
                , {field: 'gmtReg', minWidth: 140, title: '内容'}
                , {field: 'phoneNo', minWidth: 140, title: '评分'}
            ]]
            , where: {
                idPlan: 1,
                idArea: 1,
                timeSection: 1
            }
        });
    }

    function loadTab3() {
        table.render({
            elem: '#score3Table'
            , id: 'score3TableId'
            , size : 'sm'
            , url: basePath + '/pf/p/monitor/area/list/end'
            , height: 'full-180'
            //, skin: 'line'
            , cols: [[
                {type: 'numbers', title: 'R'}
                , {field: 'realName', minWidth: 150, title: '评分项'}
                , {field: 'noReg', minWidth: 100, title: '分类'}
                , {field: 'gmtReg', minWidth: 140, title: '内容'}
                , {field: 'phoneNo', minWidth: 140, title: '评分'}
            ]]
            , where: {
                idPlan: 1,
                idArea: 1,
                timeSection: 1
            }
        });
    }

});



