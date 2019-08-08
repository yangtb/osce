layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'carousel', 'form', 'jquery', 'common', 'element'], function () {
    var $ = layui.$
        , table = layui.table
        , carousel = layui.carousel
        , form = layui.form
        , element = layui.element
        , common = layui.common;

    $(document).ready(function () {
        queryMonitorDetail();
    });

    function queryMonitorDetail() {
        layer.load(2);
        var bizData = {
            idInsStation: idInsStation
        }
        $.ajax({
            url: basePath + '/pf/r/monitor/area/selectDetail',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.msg(data.msg);
                    return false;
                } else {
                    qrCode(data.data);
                    fullRight(data.data);
                    return false;
                }
            },
            error: function () {
                layer.closeAll('loading');
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function qrCode(data) {
        if (!data) {
            return;
        }
        var qrcode = new QRCode(document.getElementById("qrcode"), {
            width: 160,
            height: 160
        });
        qrcode.makeCode(data.stationQrCodeUrl);
    }

    function fullRight(data) {
        $("#idScoreSheet").empty();
        if (!data || !data.idScoreSheet) {
            return;
        }
        $('#idScoreSheet').append("<option value='" + data.idScoreSheet + "'>" + data.naScoreSheet + "</option>");
        form.render();

        // 加载评分表
        loadSheetTable(data.idScoreSheet);
    }

    function loadSheetTable(idScoreSheet) {
        table.render({
            elem: '#sheetTable'
            , id: 'sheetTableId'
            , url: basePath + '/pf/p/monitor/area/case/item/list'
            , height: '740'
            , cols: [[
                {type: 'numbers', title: 'R'}
                , {field: 'naScoreItem', minWidth: 150, title: '指标类型'}
                , {field: 'desScoreItem', minWidth: 230, title: '内容'}
                , {field: 'score', width: 100, title: '评分'}
            ]]
            , where: {
                idScoreSheet: idScoreSheet
            }
            , limit: 500
            , page: false
        });
    }


});



