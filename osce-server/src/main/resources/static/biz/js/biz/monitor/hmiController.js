layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'form', 'jquery', 'common', 'element'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , element = layui.element
        , common = layui.common;

    $(document).ready(function () {
        queryMonitorDevice();
        queryMonitorDetail();
    });

    function queryMonitorDevice() {
        let bizData = {
            idInsStation: idInsStation
        }
        $.ajax({
            url: basePath + '/pf/r/monitor/room/device/list',
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
                    showDevice(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function showDevice(deviceData) {
        if (!deviceData || deviceData.length == 0) {
            return;
        }
        $('#monitorDevice').empty();
        $.each(deviceData, function (index, item) {
            if (item.roomDeviceAddress) {
                let deviceHtml = '<div class="layui-col-md12" style="border: 1px solid #f2f2f2">\n' +
                    '                 <div style="float: right;">' +
                    '                       <button data-index="' + index + '" class="layui-btn layui-btn-normal layui-btn-xs newTab">新标签页打开</button>' +
                    '                       <button data-index="' + index + '" class="layui-btn layui-btn-normal layui-btn-xs fullScreen">全屏</button>' +
                    '                 </div>\n' +
                    '                 <hr>\n' +
                    '                 <iframe id="device-' + index + '" src="' + item.roomDeviceAddress + '" style="width: 100%; height: 580px;" frameborder="0"></iframe>\n' +
                    '             </div>';

                $('#monitorDevice').append(deviceHtml);
            }

            /*let deviceHtml = '<div class="layui-col-md12">\n' +
                '                 <div class="layui-card" style="height: 360px;border: 1px solid #dddddd">\n' +
                '                     <div class="layui-card-header" style="font-weight: bold;">设备：' + item.cdRoomDevice + '</div>\n' +
                '                        <div class="layui-card-body" style="margin: 0">\n' +
                '                           <div class="layui-row">\n' +
                '                           <div class="video" id="video' + item.idRoomDevice + '" style="width: 100%; height: 300px"></div>\n' +
                '                        </div>\n' +
                '                     </div>\n' +
                '                 </div>\n' +
                '             </div>';*/

            /*let videoObject = {
                container: '#video' + item.idRoomDevice,
                variable: 'player',
                loop: true,
                autoplay: true,
                live: true,//直播视频形式
                video: item.roomDeviceAddress
            };
            let player = new ckplayer(videoObject);*/
        });

        let newTabs = document.querySelectorAll(".newTab");
        for (let i = 0; i < newTabs.length; i++) {
            newTabs[i].addEventListener('click', function () {
                showNewTab(this.getAttribute('data-index'))
            });
        }

        let fullScreens = document.querySelectorAll(".fullScreen");
        for (let i = 0; i < fullScreens.length; i++) {
            fullScreens[i].addEventListener('click', function () {
                showFullScreen(this.getAttribute('data-index'))
            });
        }
    }

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


    function showNewTab(index) {
        let src = $('#device-' + index).attr("src");
        if (src) {
            window.open(src, '_blank')
        }
    }


    function showFullScreen(index) {
        var elm = document.getElementById("device-" + index);
        launchFullscreen(elm);
    }

// 全屏，退出按esc或参考参考参考注释代码写退出全屏按钮
    function launchFullscreen(element) {
        if (element.requestFullscreen) {
            element.requestFullscreen();
        } else if (element.mozRequestFullScreen) {
            element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
            element.msRequestFullscreen();//ie浏览器
        } else if (element.webkitRequestFullscreen) {
            element.webkitRequestFullScreen();//谷歌浏览器
        }
    }


});








