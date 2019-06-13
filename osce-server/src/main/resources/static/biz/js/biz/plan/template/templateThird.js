layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    formSelects: 'formSelects-v4'
}).use(['layer', 'index'], function () {
    var $ = layui.$;

    $(document).ready(function () {
        // 模拟排考
        queryStationDetailInfo();
    });

    function queryStationDetailInfo() {
        var bizData = {
            idModel: idModel
        }
        $.ajax({
            url: basePath + '/pf/r/plan/template/selectStationDetail',
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
                    // console.log("模拟排考返回data: " + JSON.stringify(data.data))
                    fullPageInfo(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("获取排站信息失败", {icon: 5});
                return false;
            }
        });
    }

    function fullPageInfo(data) {
        var days, dayDataAM = new Array(), dayDataPM = new Array();
        $.each(data, function (index, content) {
            if (content.timeFlag == 1) {
                dayDataAM = content.dayData;
            } else {
                dayDataPM = content.dayData;
            }
        });
        days = dayDataAM.length > dayDataPM.length ? dayDataAM.length : dayDataPM.length;
        // 设置表头
        $('#days').append(buildDayHtml(days));
        // 上午
        if (dayDataAM) {
            addAreaHtml('area-am', dayDataAM, days);
        }
        // 下午
        if (dayDataPM) {
            addAreaHtml('area-pm', dayDataPM, days);
        }

        // 添加房间样式
        roomStyle();
    };

    function buildDayHtml(days) {
        var html = '';
        for (var i = 1; i <= days; i++) {
            html += '<th>第' + i + '天</th>';
        }
        return html;
    }

    function addAreaHtml(elemId, data, days) {
        for (var i = 1; i <= days; i++) {
            var flag = false;
            $.each(data, function (index, content) {
                if (i == content.dayNum) {
                    flag = true;
                    $('#' + elemId).append(buildAreaHtml(content.areaData));
                    return;
                }
            });
            if (!flag) {
                $('#' + elemId).append('<td></td>');
            }
        }
    }

    function buildAreaHtml(areaData) {
        var html = '';
        $.each(areaData, function (index, content) {
            html += '<td>\n' +
                '       <div class="main-wrapper">\n' +
                '           <div class="header">' + content.naArea + '</div>' +
                            buildStationDataHtml(content.stationData) +
                '       </div>' +
                '    </td>';
        });
        return html;
    }

    function buildStationDataHtml(stationData) {
        var html = '';
        $.each(stationData, function (index, content) {
            var sdSkillCaText;
            if (content.sdSkillCa == 1) {
                sdSkillCaText = '理论试题';
            } else if (content.sdSkillCa == 2) {
                sdSkillCaText = '技能操作';
            } else if (content.sdSkillCa == 3) {
                sdSkillCaText = '病史采集';
            }

            html += '<div class="main-content">\n' +
                '        <p class="header-text">' + content.naStation + ' ' + content.sdStationCaText + ' ' + sdSkillCaText + '</p>\n' +
                         buildRoomDataHtml(content.roomData) +
                '    </div></div>';
        });
        return html;
    }

    function buildRoomDataHtml(roomData) {
        var html = '<div class="content">\n' +
            '           <div class="nav">\n';
        $.each(roomData, function (index, content) {
            var activeCss = index == 0 ? 'active' : '';
            html += '<a href="javascript:;" class="nav-item ' + activeCss + '" ' +
                'data-sq="' + content.sq + '" data-index="' + content.idRoom + '">' + content.idRoomText + '</a>\n';
        });
        html += '</div>\n' +
            '    <div class="content-container">\n' +
            '       <div class="content-item">\n';

        $.each(roomData, function (index, content) {
            html += '<div class="tab-content tab-content' + content.sq + '" id="tab-' + content.sq + '-' + content.idRoom + '-main"';
            if (index == 0){
                html +='>\n';
            } else {
                html +=' style="display: none;">\n';
            }
            $.each(content.spList, function (indexSp, contentSp) {
                html += '<p class="item-main">\n' +
                '       <span class="item-msg">S' + contentSp.cdInventedStudent + '</span>\n' +
                '       <span class="item-msg">' + contentSp.timeBegin + '</span>\n' +
                '       <span class="item-msg">' + contentSp.timeEnd + '</span>\n'+
                '    </p>\n';
            });
            html += '</div>';
        });

        html += '</div></div>';
        return html;
    }


    function roomStyle() {
        $(".nav-item").on('click', function () {
            changeRoom(this, $(this).attr("data-sq"), $(this).attr("data-index"));
            $(this).addClass("active").siblings().removeClass('active')
        })
    }

    function changeRoom(elem, sq, idRoom) {
        var roomContent = document.querySelectorAll(".tab-content" + sq);
        for (var i = 0; i < roomContent.length; i++) {
            $(".tab-content" + sq).hide()
        }
        $("#tab-" + sq + "-" + idRoom + "-main").show();
    }

});




