layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    formSelects: 'formSelects-v4'
}).use(['layer','index', 'common'], function () {
    var $ = layui.$
        , common = layui.common;

    $(document).ready(function () {
        // 模拟排考
        queryStationDetailInfo();
    });

    function queryStationDetailInfo() {
        var bizData = {
            idPlan: idPlan,
            spFlag: 'assistant'
        }
        $.ajax({
            url: basePath + '/pf/r/plan/station/select/sp/detail',
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
                    //console.log("模拟排考返回data: " + JSON.stringify(data.data))
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
        // 添加分配sp事件
        spClick();
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
        var html = '<div class="test-container">';
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
                buildRoomDataHtml(content.roomData, content.sdSkillCa) +
                '    </div></div>';
        });
        html += '</div>';
        return html;
    }

    function buildRoomDataHtml(roomData, sdSkillCa) {
        var html = '<div class="content">\n' +
            '           <div class="nav">\n';
        $.each(roomData, function (index, content) {
            var activeCss = index == 0 ? 'active' : '';
            html += '<a href="javascript:;" class="nav-item ' + activeCss + '" ' +
                'data-sq="' + content.sq + '" data-index="' + content.idRoom + '">' + content.idRoomText + '</a>\n';
        });
        html += '</div>\n' +
            '    <div class="content-container">\n';

        $.each(roomData, function (index, content) {
            html += '<div class="content-item content-item' + content.sq + '"';
            html += ' id="item-' + content.sq + '-' + content.idRoom + '-main"';
            if (index == 0) {
                html += '>\n';
            } else {
                html += ' style="display: none;">\n';
            }
            html += '<div class="tab-content tab-content-border">\n';

            var itemStyle = 'item-msg';
            $.each(content.spList, function (indexSp, contentSp) {
                html += '<p class="item-main">\n' +
                    '       <span class="' + itemStyle + ' item-msg-s">' + contentSp.cdInventedStudent + '</span>\n' +
                    '       <span class="' + itemStyle + '">' + contentSp.timeBegin + '</span>\n' +
                    '       <span class="' + itemStyle + '">' + contentSp.timeEnd + '</span>\n' +
                    '    </p>\n';
            });
            html += '</div>';
            html += '<div class="main-right">\n' +
                '        <p class="right-item right-item-sp" data-sq="' + content.sq.replace('.','_')  + '-' + content.idRoom + '">\n' +
                '           <img class="edit-btn edit" src="' + basePath + '/biz/img/template/edit_btn.png" alt="编辑">\n' +
                '           <span class="">考官</span>\n' +
                '        </p>\n' +
                '        <div id="assistant-' + content.sq.replace('.','_')  + '-' + content.idRoom + '">\n';
            var assistants = content.planAssistant;
            if (assistants) {
                if (assistants.managerName) {
                    html += '<p class="right-item">' + assistants.managerName + '</p>\n';
                }
                if (assistants.assistantName) {
                    html += '<p class="right-item">' + assistants.assistantName + '</p>\n';
                }
                if (assistants.remoteName) {
                    html += '<p class="right-item">' + assistants.remoteName + '</p>\n';
                }
            }
            html += '</div> </div>';
            html += '</div>';
        });

        html += '</div>';
        return html;
    }


    function roomStyle() {
        $(".nav-item").on('click', function () {
            changeRoom(this, $(this).attr("data-sq"), $(this).attr("data-index"));
            $(this).addClass("active").siblings().removeClass('active')
        })
    }

    function changeRoom(elem, sq, idRoom) {
        //console.log(sq)
        var roomContent = document.querySelectorAll(".content-item" + sq);
        for (var i = 0; i < roomContent.length; i++) {
            $(".content-item" + sq).hide()
        }
        $("#item-" + sq + "-" + idRoom + "-main").show();
    }

    function spClick() {
        $(".right-item-sp").on('click', function () {
            assignedAssistant($(this).attr("data-sq"));
        })
    }

    function assignedAssistant(sq) {
        var index = layui.layer.open({
            title: false,
            //skin: 'layui-layer-molv', //样式类名
            type: 2,
            area: ['850px', '480px'],
            //anim: anim,
            fixed: false, //不固定
            //maxmin: true,
            content: basePath + '/pf/p/plan/station/assigned/assistant?idPlan=' + idPlan + '&sq=' + sq.replace('_','.'),
            shadeClose: true,
            closeBtn: false,
            btn: ['确定', '关闭'],
            yes: function (index, layero) {
                var iframeWindow = window['layui-layer-iframe' + index];
                var d_idUserManager = iframeWindow.document.getElementById('idUserManager');
                var v_idUserManager = d_idUserManager ? d_idUserManager.value : null;
                if (!v_idUserManager) {
                    common.errorMsg('请先选择主考官');
                    return;
                }
                var d_idUserAssistant = iframeWindow.document.getElementById('idUserAssistant');
                var d_idUserRemote = iframeWindow.document.getElementById('idUserRemote');
                var v_idUserAssistant = d_idUserAssistant ? d_idUserAssistant.value : null;
                var v_idUserRemote = d_idUserRemote ? d_idUserRemote.value : null;

                var d_idUserManagerText = iframeWindow.document.getElementById('idUserManagerText');
                var d_idUserAssistantText = iframeWindow.document.getElementById('idUserAssistantText');
                var d_idUserRemoteText = iframeWindow.document.getElementById('idUserRemoteText');

                var v_idUserManagerText = d_idUserManagerText ? d_idUserManagerText.innerText : null;
                var v_idUserAssistantText = d_idUserAssistantText ? d_idUserAssistantText.innerText : null;
                var v_idUserRemoteText = d_idUserRemoteText ?  d_idUserRemoteText.innerText : null;

                console.log(v_idUserManagerText + v_idUserAssistantText + v_idUserRemoteText)
                saveSp(sq, v_idUserManager, v_idUserAssistant, v_idUserRemote,
                    v_idUserManagerText, v_idUserAssistantText, v_idUserRemoteText)
            }
        });
    }

    function saveSp(sq, v_idUserManager, v_idUserAssistant, v_idUserRemote,
                    v_idUserManagerText, v_idUserAssistantText, v_idUserRemoteText) {
        var arr = sq.split("-");
        var bizData = {
            idPlan: idPlan,
            idArea: arr[0],
            idStation: arr[1],
            timeSection: arr[2].replace('_','.'),
            idRoom: arr[3],
            idUserManager: v_idUserManager,
            idUserAssistant: v_idUserAssistant,
            idUserRemote: v_idUserRemote
        }
        common.commonPost(basePath + '/pf/r/plan/station/save/assistant',
            bizData, null, null, function () {
                //window.location.reload();
                layer.closeAll();
                full(sq, v_idUserManagerText, v_idUserAssistantText, v_idUserRemoteText);
            }, true);
    }

    function full(sq, v_idUserManagerText, v_idUserAssistantText, v_idUserRemoteText) {
        $('#assistant-' + sq).empty();
        var html = '';
        if (v_idUserManagerText) {
            html += '<p class="right-item">' + v_idUserManagerText + '</p>';
        }
        if (v_idUserAssistantText) {
            html += '<p class="right-item">' + v_idUserAssistantText + '</p>';
        }
        if (v_idUserRemoteText) {
            html += '<p class="right-item">' + v_idUserRemoteText + '</p>';
        }
        //console.log('#assistant-' + sq)
        //console.log($('#assistant-' + sq))
        //console.log(html)
        //console.log('--------------------------')
        $('#assistant-' + sq).append(html);
        //console.log('**************************')
    }

});






