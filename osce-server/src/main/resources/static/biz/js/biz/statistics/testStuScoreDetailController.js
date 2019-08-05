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
        loadScoreHead();
        loadTab1();
    });

    function loadScoreHead() {
        var bizData = {
            idExec: idExec
        }
        $.ajax({
            url: basePath + '/pf/r/statistics/test/student/result',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.msg(data.msg, {icon: 5});
                    return false;
                } else {
                    var sucData = data.data;
                    if (sucData) {
                        if (sucData.weightManager) {
                            $('#weightManager').text(sucData.weightManager);
                            $('#scoreManager').text(sucData.scoreManager);
                        }
                        if (sucData.weightAssistant) {
                            $('#weightAssistant').text(sucData.weightAssistant);
                            $('#scoreAssistant').text(sucData.scoreAssistant);
                        }
                        if (sucData.weightRemote) {
                            $('#weightRemote').text(sucData.weightRemote);
                            $('#scoreRemote').text(sucData.scoreRemote);
                        }
                        if (sucData.scoreSum) {
                            $('#scoreSum').text(sucData.scoreSum);
                        }
                    }
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    };

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
            , size: 'sm'
            , url: basePath + '/pf/p/statistics/assistant/score/list'
            , height: 'full-180'
            //, skin: 'line'
            , cols: [[
                {type: 'numbers', title: 'R'}
                , {field: 'naScoreItem', minWidth: 150, title: '评分项'}
                , {field: 'sdScoreItemCa', minWidth: 100, title: '分类'}
                , {field: 'desScoreItem', minWidth: 140, title: '内容'}
                , {field: 'scoreResult', minWidth: 140, title: '评分', align: 'right'}
            ]]
            , where: {
                idExec: idExec,
                cdAssistantCa: 1
            }
        });
    }

    function loadTab2() {
        table.render({
            elem: '#score2Table'
            , id: 'score2TableId'
            , size: 'sm'
            , url: basePath + '/pf/p/monitor/area/list/end'
            , height: 'full-180'
            //, skin: 'line'
            , cols: [[
                {type: 'numbers', title: 'R'}
                , {field: 'naScoreItem', minWidth: 150, title: '评分项'}
                , {field: 'sdScoreItemCa', minWidth: 100, title: '分类'}
                , {field: 'desScoreItem', minWidth: 140, title: '内容'}
                , {field: 'scoreResult', minWidth: 140, title: '评分', align: 'right'}
            ]]
            , where: {
                idExec: idExec,
                cdAssistantCa: 2
            }
        });
    }

    function loadTab3() {
        table.render({
            elem: '#score3Table'
            , id: 'score3TableId'
            , size: 'sm'
            , url: basePath + '/pf/p/monitor/area/list/end'
            , height: 'full-180'
            //, skin: 'line'
            , cols: [[
                {type: 'numbers', title: 'R'}
                , {field: 'naScoreItem', minWidth: 150, title: '评分项'}
                , {field: 'sdScoreItemCa', minWidth: 100, title: '分类'}
                , {field: 'desScoreItem', minWidth: 140, title: '内容'}
                , {field: 'scoreResult', minWidth: 140, title: '评分', align: 'right'}
            ]]
            , where: {
                idExec: idExec,
                cdAssistantCa: 3
            }
        });
    }

});



