layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    formSelects: 'formSelects-v4'
}).use(['layer', 'index', 'form'], function () {
    var $ = layui.$
        , form = layui.form;

    $(document).ready(function () {
        // 加载排站信息
        queryStationInfo();
    });

    function roomStyle() {
        $(".nav-item").on('click', function () {
            changeRoom(this, $(this).attr("data-sq"), $(this).attr("data-index"));
            $(this).addClass("active").siblings().removeClass('active')
        })
    }

    function queryStationInfo() {
        var bizData = {
            idModel: idModel
        }
        $.ajax({
            url: basePath + '/pf/r/plan/template/selectStationInfo',
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
                    //console.log(JSON.stringify(data.data))
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
        // 编辑试卷监听
        addEditEventListener();
    };

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

    function buildDayHtml(days) {
        var html = '';
        for (var i = 1; i <= days; i++) {
            html += '<th>第' + i + '天</th>';
        }
        return html;
    }

    function buildAreaHtml(areaData) {
        var html = '';
        $.each(areaData, function (index, content) {
            html += '<td>\n' +
                '       <div class="main-wrapper">\n' +
                '          <div class=\'header\' id="area-' + content.idArea + '">' + content.naArea + '</div>\n' +
                           buildStationDataHtml(content.stationData) +
                '       </div>\n' +
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
            html += '<div class="main-content" id="idStation-' + content.idStation + '">\n' +
                '       <p class="header-text" id="sdSkillCa-' + content.sdSkillCa + '">' + content.naStation + ' ' + content.sdStationCaText + ' ' + sdSkillCaText + '</p>\n' +
                            buildRoomDataHtml(content.roomData) +
                '       </div>\n' +
                '    </div>\n';
        });
        return html;
    }

    function buildRoomDataHtml(roomData) {
        var html = '<div class="content">\n' +
            '        <div class="nav">\n' ;

        $.each(roomData, function (index, content) {
            var activeCss = index == 0 ? 'active' : '';
            html += '<a href="javascript:;" class="nav-item ' + activeCss + '" ' +
                'data-sq="' + content.sq + '" data-index="' + content.idRoom + '">' + content.idRoomText + '</a>\n';
        });

        html += '   </div>\n' +
            '<div class="content-container">\n';
        $.each(roomData, function (index, content) {
            html += '  <div class="content-item content-item' + content.sq
                + '" id="tab-' + content.sq + '-' + content.idRoom + '-main"';
            if (index == 0){
                html +=' style="display: flex;"';
            } else {
                html +=' style="display: none;"';
            }
            html += '    >\n' +
                '        <img class="edit-btn edit-btn-paper" data-id="' + content.idInsStation + '" src="' + basePath + '/biz/img/template/edit_btn.png" alt="编辑">\n' +
                '        <p class="item-text item-text-paper" data-id="' + content.idInsStation + '">考试'+ content.idRoomText +'卷</p>\n' +
                '      </div>\n';
        });

        html += '</div>\n';
        return html;
    }

    function changeRoom(elem, sq, idRoom) {
        var roomContent = document.querySelectorAll(".content-item" + sq);
        for (var i = 0; i < roomContent.length; i++) {
            $(".content-item" + sq).hide()
        }
        $("#tab-" + sq + "-" + idRoom + "-main").show();
    }

    // 编辑试卷监听
    function addEditEventListener() {
        var editBtnPapers = document.querySelectorAll(".edit-btn-paper");
        var itemTextPapers = document.querySelectorAll(".item-text-paper");
        for (var i = 0; i < editBtnPapers.length; i++) {
            editBtnPapers[i].addEventListener('click', function () {
                alert(this.getAttribute('data-id'));
            });
        }

        for (var j = 0; j < itemTextPapers.length; j++) {
            itemTextPapers[j].addEventListener('click', function () {
                alert(this.getAttribute('data-id'));
            });
        }
    }

});



