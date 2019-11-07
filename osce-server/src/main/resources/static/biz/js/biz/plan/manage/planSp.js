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
            idPlan: idPlan
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
            //html += '<div class="tab-content ';
            if (sdSkillCa == 3) {
                //html += 'tab-content-border ';
            }
            //html += '">\n';

            var test = index == 0 ? '1' : '';


            html += '<div class="layui-row" style="width: 305px; border-bottom: 1px solid #e2e2e2; overflow-x: auto;">\n' +
                '        <div class="card-col">\n' +
                '           <ul style="font-size: 13px;">\n' +
                '               <li class="li-sp">咖啡远程'+test+'</li>\n' +
                '               <li class="li-sp">茶'+test+'</li>\n' +
                '               <li class="li-sp">牛奶'+test+'</li>\n' +
                '               <li class="li-sp">牛奶'+test+'</li>\n' +
                '           </ul>\n'+
                '        </div>\n' +
                '        <div class="sp-div">\n' +
                '           <div class="sp-div-cell">' +
                '               <img class="edit-btn1" src="' + basePath + '/biz/img/template/edit_btn.png">\n' +
                '               <div class="item-text1" style="background-color: #5FB878">主考官</div>'+
                '           </div>' +
                '        </div>\n' +
                '        <div class="sp-div">\n' +
                '           <div class="sp-div-cell">' +
                '               <img class="edit-btn1" src="' + basePath + '/biz/img/template/edit_btn.png">\n' +
                '               <div class="item-text1" style="width: 65px; background-color: #FFB800">远程考官</div>'+
                '           </div>' +
                '        </div>\n' +
                '        <div class="sp-div">\n' +
                '           <div class="sp-div-cell">' +
                '               <img class="edit-btn1" src="' + basePath + '/biz/img/template/edit_btn.png">\n' +
                '               <div class="item-text1" style="background-color: #1E9FFF">考官</div>'+
                '           </div>' +
                '        </div>\n' +
                '        <div class="sp-div">\n' +
                '           <div class="sp-div-cell">' +
                '               <div class="item-text1" style="margin-top: 3px;">SP'+test+'</div>'+
                '               <div class="item-text1" style="margin-top: 3px;">SP'+test+'</div>'+
                '               <div class="item-text1" style="margin-top: 3px;">SP'+test+'</div>'+
                '               <img class="edit-btn1" src="' + basePath + '/biz/img/template/edit_btn.png">\n' +
                '               <div class="item-text1" style="margin-top: 3px; background-color: #009688;">SP</div>'+
                '           </div>' +
                '        </div>\n' +
                '    </div>';

            html += '<div style="padding-top: 10px;" class="item-text">\n' +
                '        <img class="edit-btn" style="margin-top: -3px;" src="' + basePath + '/biz/img/template/edit_btn.png">\n' +
                '        <span class="item-text">添加试卷</span>\n' +
                '    </div>';

            var itemStyle = 'item-msg';
            /*$.each(content.spList, function (indexSp, contentSp) {
                html += '<p class="item-main">\n' +
                    '       <span class="' + itemStyle + ' item-msg-s">' + contentSp.cdInventedStudent + '</span>\n' +
                    '       <span class="' + itemStyle + '">' + contentSp.timeBegin + '</span>\n' +
                    '       <span class="' + itemStyle + '">' + contentSp.timeEnd + '</span>\n' +
                    '    </p>\n';
            });*/
            //html += '</div>';
            /*if (sdSkillCa == 3) {
                html += '<div class="main-right">\n' +
                    '        <p class="right-item right-item-sp" data-sq="' + content.sq  + '-' + content.idRoom + '">\n' +
                    '           <img class="edit-btn edit" src="' + basePath + '/biz/img/template/edit_btn.png" alt="编辑">\n' +
                    '           <span class="">SP</span>\n' +
                    '        </p>\n' +
                    '        <div id="asSp-' + content.sq  + '-' + content.idRoom + '">\n';
                $.each(content.planSp, function (index, content) {
                    if (content) {
                        html += '<p class="right-item">' + content.realName + '</p>\n';
                    }
                });
                html += '</div> </div>';
            }*/
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
            tpSp($(this).attr("data-sq"));
        })
    }

    function tpSp(sq) {
        var index = layui.layer.open({
            title: false,
            //skin: 'layui-layer-molv', //样式类名
            type: 2,
            area: ['850px', '480px'],
            //anim: anim,
            fixed: false, //不固定
            //maxmin: true,
            content: basePath + '/pf/p/plan/station/assigned/sp?idPlan=' + idPlan + '&sq=' + sq,
            shadeClose: true,
            closeBtn : false,
            btn: ['确定', '关闭'],
            yes: function(index, layero){
                var iframeWindow = window['layui-layer-iframe' + index];
                var sp = iframeWindow.document.querySelectorAll(".spRealName");
                if (!sp) {
                    common.errorMsg('请先选中一行记录');
                    return;
                }
                var idUsers = new Array(), idUserNames = new Array();
                for (var i = 0; i < sp.length; i++) {
                    idUsers.push(sp[i].getAttribute('data-id'))
                    var d_spRealNameText = iframeWindow.document.getElementById('spRealName-' + sp[i].getAttribute('data-id'));
                    var v_spRealNameText = d_spRealNameText ? d_spRealNameText.innerText : null;
                    idUserNames.push(v_spRealNameText);
                }
                if (idUsers.length == 0) {
                    common.errorMsg('请先选中一行记录');
                    return;
                }
                saveSp(idUsers, sq, idUserNames)
                //layer.close(index); //如果设定了yes回调，需进行手工关闭
            }
        });
    }

    function saveSp(idUsers, sq, idUserNames) {
        //console.log(sq)
        var arr = sq.split("-");
        var bizData = {
            idPlan: idPlan,
            idUsers: idUsers,
            idArea: arr[0],
            idStation: arr[1],
            timeSection: arr[2],
            idRoom: arr[3]
        }
        common.commonPost(basePath + '/pf/r/plan/station/save/sp',
            bizData, null, null, function () {
                //window.location.reload();
                full(sq, idUserNames);
                layer.closeAll();
            }, true);
    }

    function full(sq, idUserNames) {
        $('#asSp-' + sq).empty();
        var html = '';
        $.each(idUserNames, function (index, content) {
            html += '<p class="right-item">' + content + '</p>';
        });
        $('#asSp-' + sq).append(html);
    }

});






