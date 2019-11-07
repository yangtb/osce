layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['layer','index', 'form', 'common'], function () {
    var $ = layui.$
        , form = layui.form
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
        // 编辑试卷监听
        addEditEventListener();
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

            var studentHtml = '';
            $.each(content.spList, function (indexSp, contentSp) {
                studentHtml += '<li class="li-sp">' + contentSp.cdInventedStudent + '</li>\n';
            });

            var managerName = content.planAssistant && content.planAssistant.managerName ? content.planAssistant.managerName : '';
            var assistantName = content.planAssistant && content.planAssistant.assistantName ? content.planAssistant.assistantName : '';
            var remoteName = content.planAssistant && content.planAssistant.remoteName ? content.planAssistant.remoteName : '';
            var managerNameHtml = '<div class="item-text1"  id="managerName-' + content.sq.replace('.','_')  + '-' + content.idRoom + '">' + managerName + '</div>';
            var assistantNameHtml = '<div class="item-text1" style="width: 65px;" id="assistantName-' + content.sq.replace('.','_')  + '-' + content.idRoom + '">' + assistantName + '</div>';
            var remoteNameHtml = '<div class="item-text1" id="remoteName-' + content.sq.replace('.','_')  + '-' + content.idRoom + '">' + remoteName + '</div>';


            var planSpHtml = '<div id="asSp-' + content.sq  + '-' + content.idRoom + '">',
                spHtml = '';
            $.each(content.planSp, function (index, contentPlanSp) {
                planSpHtml += '<div class="item-text1" style="margin-top: 3px;">' + contentPlanSp.realName + '</div>\n';
            });
            planSpHtml += '</div>\n';
            if (sdSkillCa == 3) {
                spHtml = '<img class="edit-btn1" src="' + basePath + '/biz/img/template/edit_btn.png">\n' +
                    '<div class="item-text1" style="height: 18px; background-color: #009688;">SP</div>';
            }

            html += '<div class="layui-row" style="width: 305px; border-bottom: 1px solid #e2e2e2; overflow-x: auto;">\n' +
                '        <div class="card-col">\n' +
                '           <ul style="font-size: 13px;">\n' +
                                studentHtml +
                '           </ul>\n'+
                '        </div>\n' +
                '        <div class="sp-div">\n' +
                '           <div class="sp-div-cell">' +
                                managerNameHtml +
                '               <img class="edit-btn1 right-item-examiner" src="' + basePath + '/biz/img/template/edit_btn.png" data-sq="' + content.sq.replace('.','_')  + '-' + content.idRoom + '">\n' +
                '               <div class="item-text1" style="background-color: #5FB878">主考官</div>'+
                '           </div>' +
                '        </div>\n' +
                '        <div class="sp-div">\n' +
                '           <div class="sp-div-cell">' +
                                assistantNameHtml +
                '               <img class="edit-btn1 right-item-examiner" src="' + basePath + '/biz/img/template/edit_btn.png" data-sq="' + content.sq.replace('.','_')  + '-' + content.idRoom + '">\n' +
                '               <div class="item-text1" style="width: 65px; background-color: #FFB800">远程考官</div>'+
                '           </div>' +
                '        </div>\n' +
                '        <div class="sp-div">\n' +
                '           <div class="sp-div-cell">' +
                                remoteNameHtml +
                '               <img class="edit-btn1 right-item-examiner" src="' + basePath + '/biz/img/template/edit_btn.png" data-sq="' + content.sq.replace('.','_')  + '-' + content.idRoom + '">\n' +
                '               <div class="item-text1" style="background-color: #1E9FFF">考官</div>'+
                '           </div>' +
                '        </div>\n' +
                '        <div class="sp-div">\n' +
                '           <div class="sp-div-cell right-item-sp" data-sq="' + content.sq  + '-' + content.idRoom + '">' +
                                planSpHtml +
                                spHtml +
                '           </div>' +
                '        </div>\n' +
                '    </div>';

            var idPaperText = content.idPaperText ? content.idPaperText : '添加考卷';
            html += '<div style="padding-top: 10px;" class="item-text edit-paper" id="stable-' + content.idInsStation + '" data-id="' + content.idInsStation + '-' + sdSkillCa + '-' + content.idPaper + '-' + content.idScoreSheet + '">\n' +
                '        <img class="edit-btn" style="margin-top: -3px;" src="' + basePath + '/biz/img/template/edit_btn.png">\n' +
                '        <span class="item-text" id="paper-' + content.idInsStation + '" data-id="' + content.idInsStation + '-' + sdSkillCa + '-' + content.idPaper + '-' + content.idScoreSheet + '">' + idPaperText + '</span>\n' +
                '    </div>';

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

        $(".right-item-examiner").on('click', function () {
            assignedAssistant($(this).attr("data-sq"));
        })
    }

    // 编辑试卷监听
    function addEditEventListener() {
        var editPapers = document.querySelectorAll(".edit-paper");
        for (var i = 0; i < editPapers.length; i++) {
            editPapers[i].addEventListener('click', function () {
                clickPaper(this.getAttribute('data-id'))
            });
        }
    }

    function tpSp(sq) {
        console.log("------------")
        console.log(sq)
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
        console.log(sq)
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

    //==============考官保存begin=====================
    function assignedAssistant(sq) {
        var index = layui.layer.open({
            title: false,
            //skin: 'layui-layer-molv', //样式类名
            type: 2,
            area: ['900px', '480px'],
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
                    common.errorMsg('需要分配一位主考官');
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
                saveExaminer(sq, v_idUserManager, v_idUserAssistant, v_idUserRemote,
                    v_idUserManagerText, v_idUserAssistantText, v_idUserRemoteText)
            }
        });
    }

    function saveExaminer(sq, v_idUserManager, v_idUserAssistant, v_idUserRemote,
                    v_idUserManagerText, v_idUserAssistantText, v_idUserRemoteText) {
        console.log(sq)
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
                fullExaminer(sq, v_idUserManagerText, v_idUserAssistantText, v_idUserRemoteText);
            }, true);
    }

    function fullExaminer(sq, v_idUserManagerText, v_idUserAssistantText, v_idUserRemoteText) {
        $('#managerName-' + sq).text(v_idUserManagerText ? v_idUserManagerText : '');
        $('#assistantName-' + sq).text(v_idUserAssistantText ? v_idUserAssistantText : '');
        $('#remoteName-' + sq).text(v_idUserRemoteText ? v_idUserRemoteText : '');
    }
    //==============考官保存end=====================



    //==============试卷保存begin=====================
    var formBox;

    function clickPaper(data) {
        var arr = data.split("-");
        var idInsStation = arr[0];
        var sdSkillCa = arr[1];
        var idPaper = arr[2] ? arr[2] : null;
        var idScoreSheet = arr[3] ? arr[3] : null;


        var bizData = {
            sdSkillCa: sdSkillCa,
            idModel: idModelFrom
        }
        $.ajax({
            url: basePath + '/pf/r/plan/exam/paper/list',
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
                    popSelectPaper(data.data, idInsStation, sdSkillCa, idPaper, idScoreSheet);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function popSelectPaper(paperData, idInsStation, sdSkillCa, idPaper, idScoreSheet) {
        var elem = $('#stable-' + idInsStation);
        var t = elem.offset().top + elem.outerHeight() + "px";
        var l = elem.offset().left + "px";

        var html =
            '<div id="div-add-paper" class="layui-anim layui-anim-upbit" style="left:' + l + ';top:' + t + ';border: 1px solid #d2d2d2;background-color: #fff;box-shadow: 0 2px 4px rgba(0,0,0,.12);padding:5px 5px 0 0px;position: absolute;z-index:666;margin: 2px 0;border-radius: 2px;min-width:200px;">' +
            '   <form class="layui-form" id="addPaperForm">\n' +
            '        <div hidden>\n' +
            '            <input name="idInsStation" value="' + idInsStation + '" hidden>\n' +
            '        </div>\n' +
            '\n' +
            '        <div class="layui-form-item form-item-my">\n' +
            '           <label class="layui-form-label">试卷<i class="iconfont icon-required" style="color: #f03f2d"></i></label>\n' +
            '           <div class="layui-input-block">\n' +
            '               <select id="idPaper" name="idPaper" lay-verify="required" lay-vertype="tips" lay-filter="idPaperFilter">\n' +
            '                   <option value="">请选择</option>\n';
        $.each(paperData, function (index, context) {
            html += '<option value="' + context.id + '">' + context.paperName + '</option>\n';
        });

        html += '               </select>\n' +
            '           </div>\n' +
            '        </div>\n' +
            '\n' +
            '        <div class="layui-form-item form-item-my">\n' +
            '           <label class="layui-form-label">评分表</label>\n' +
            '           <div class="layui-input-block">\n' +
            '               <select id="idScoreSheet" name="idScoreSheet">\n' +
            '                   <option value="">请选择</option>\n' +
            '               </select>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '\n' +
            '        <div class="layui-form-item form-item-my">\n' +
            '            <div class="layui-input-block">\n' +
            '                <button class="layui-btn layui-btn-sm" id="addPaper" lay-submit="" lay-filter="addPaper">\n' +
            '                    <i class="layui-icon">&#x1005;</i>保存\n' +
            '                </button>\n' +
            '                <button type="button" id="closeWin" class="layui-btn layui-btn-sm layui-btn-danger">\n' +
            '                    <i class="layui-icon">&#x1006;</i>取消\n' +
            '                </button>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </form>'
        ' </div>';

        formBox = $(html);
        $('body').append(formBox);
        if (idPaper) {
            $('#idPaper').val(idPaper);
            form.render();
            loadScoreSheet(sdSkillCa, idPaper, idScoreSheet);
        }

        // 取消
        $('#closeWin').on("click", function (e) {
            $('#div-add-paper').remove();
        });

        form.on('select(idPaperFilter)', function (data) {
            if (!data.value) {
                $('#idScoreSheet').empty();
                $('#idScoreSheet').append('<option value="">请选择</option>');
                form.render();
                return;
            }
            loadScoreSheet(sdSkillCa, data.value, null);
        });

        form.on('submit(addPaper)', function (data) {
            var bizData = data.field;
            // 参数校验
            if (!bizData.idPaper) {
                layer.tips('请选泽试卷', '#addPaper');
                return false;
            }
            if (sdSkillCa != '1' && !bizData.idScoreSheet) {
                layer.tips('请选择评分表', '#addPaper', {zIndex: 1000});
                return false;
            }

            bizData.paperName = $("#idPaper").find("option:selected").text();

            $('#div-add-paper').remove();
            layer.confirm('是否应用到该考站的所有时段？', {
                btn: ['是', '否']
            }, function (index, layero) {
                layer.close(index);
                addPaper(bizData, true);
            }, function (index) {
                addPaper(bizData, false);
            });
            return false;
        });
    }

    function loadScoreSheet(sdSkillCa, idPaper, idScoreSheet) {
        //console.log(idPaper)
        if (idPaper == "undefined") {
            return;
        }
        if (sdSkillCa != '1') {
            var bizData = {
                sdSkillCa: sdSkillCa,
                idPaper: idPaper
            }
            $.ajax({
                url: basePath + '/pf/r/plan/exam/paper/sheet/list',
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
                        //console.log(data.data)
                        var sheetHtml = '<option value="">请选择</option>';
                        if (data.data && data.data.length >= 1) {
                            $.each(data.data, function (index, context) {
                                sheetHtml += '<option value="' + context.idScoreSheet + '">' + context.naScoreSheet + '</option>\n';
                            });
                        }
                        $('#idScoreSheet').empty();
                        $('#idScoreSheet').append(sheetHtml);
                        if (idScoreSheet) {
                            $('#idScoreSheet').val(idScoreSheet);
                        }
                        form.render();
                        return false;
                    }
                },
                error: function () {
                    layer.msg("网络异常");
                    return false;
                }
            });
        }
    }

    //点击其他区域关闭
    $(document).mouseup(function (e) {
        var userSet_con = $('#div-add-paper');
        if (!userSet_con.is(e.target) && userSet_con.has(e.target).length === 0) {
            $('#div-add-paper').remove();
        }
    });


    function addPaper(fromData, flag) {
        var idInsStation = fromData.idInsStation;
        var bizData = {
            idInsStation: fromData.idInsStation,
            idPaper: fromData.idPaper,
            idScoreSheet: fromData.idScoreSheet,
            allFlag: flag
        }

        $.ajax({
            url: basePath + '/pf/r/plan/paper/save/paper',
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
                    if (flag) {
                        window.location.reload();
                    } else {
                        $('#paper-' + idInsStation).text(fromData.paperName);
                    }
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }
    //==============试卷保存end=====================


});






