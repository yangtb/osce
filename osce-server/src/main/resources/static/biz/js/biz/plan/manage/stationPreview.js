layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    formSelects: 'formSelects-v4'
}).use(['layer', 'index', 'form', 'tableSelect', 'common'], function () {
    var $ = layui.$
        , form = layui.form
        , common = layui.common
        , tableSelect = layui.tableSelect;

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
            idPlan: idPlan
        }
        $.ajax({
            url: basePath + '/pf/r/plan/station/selectStationInfo',
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
                $('#' + elemId).append('<td class="td-my"></td>');
            }
        }
    }

    function buildDayHtml(days) {
        var html = '';
        for (var i = 1; i <= days; i++) {
            html += '<th class="th-my">第' + i + '天</th>';
        }
        return html;
    }

    function buildAreaHtml(areaData) {
        var html = '';
        $.each(areaData, function (index, content) {
            html += '<td class="td-my">\n' +
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
                            buildRoomDataHtml(content.roomData, content.sdSkillCa) +
                '       </div>\n' +
                '    </div>\n';
        });
        return html;
    }

    function buildRoomDataHtml(roomData, sdSkillCa) {
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
            var idPaperText = content.idPaperText ? content.idPaperText : '请选择试卷';
            html += '    >\n' +
                '        <img class="edit-btn edit-btn-paper" id="stable-' + content.idInsStation +'" data-id="' + content.idInsStation + '-' + sdSkillCa + '" src="' + basePath + '/biz/img/template/edit_btn.png" alt="编辑">\n' +
                '        <p class="item-text item-text-paper" id="paper-' + content.idInsStation +'" data-id="' + content.idInsStation  + '-' + sdSkillCa + '">'+ idPaperText +'</p>\n' +
                '      </div>\n';
        });

        html += '</div>\n';
        return html;
    }

    function changeRoom(elem, sq, idRoom) {
        var roomContent = document.querySelectorAll(".content-item" + sq);
        for (var i = 0; i < roomContent.length; i++) {
            $(".content-item" + sq).hide();
        }
        $("#tab-" + sq + "-" + idRoom + "-main").show();
    }

    // 编辑试卷监听
    function addEditEventListener() {
        var editBtnPapers = document.querySelectorAll(".edit-btn-paper");
        var itemTextPapers = document.querySelectorAll(".item-text-paper");
        for (var i = 0; i < editBtnPapers.length; i++) {
            editBtnPapers[i].addEventListener('click', function () {
                clickPaper(this.getAttribute('data-id'))
            });
        }

        for (var j = 0; j < itemTextPapers.length; j++) {
            itemTextPapers[j].addEventListener('click', function () {
                clickPaper(this.getAttribute('data-id'))
            });
        }
    }

    var formBox;
    function clickPaper(data) {
        var arr = data.split("-");
        var idInsStation = arr[0];
        var sdSkillCa = arr[1];

        var bizData = {
            sdSkillCa: sdSkillCa ,
            idModel : idModelFrom
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
                    popSelectPaper(data.data, idInsStation, sdSkillCa);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function popSelectPaper(paperData, idInsStation, sdSkillCa) {
        var elem = $('#stable-' + idInsStation);
        var t = elem.offset().top + elem.outerHeight() + "px";
        var l = elem.offset().left + "px";

        var html =
            '<div id="div-add-paper" class="layui-anim layui-anim-upbit" style="left:'+l+';top:'+t+';border: 1px solid #d2d2d2;background-color: #fff;box-shadow: 0 2px 4px rgba(0,0,0,.12);padding:10px 10px 0 0px;position: absolute;z-index:666;margin: 5px 0;border-radius: 2px;min-width:200px;">'+
            '   <form class="layui-form" id="addPaperForm">\n' +
            '        <div hidden>\n' +
            '            <input name="idInsStation" value="'+ idInsStation +'" hidden>\n' +
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

        html +='               </select>\n' +
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
        form.render();

        // 取消
        $('#closeWin').on("click",function(e) {
            $('#div-add-paper').remove();
        });

        form.on('select(idPaperFilter)', function(data){
            if (!data.value) {
                $('#idScoreSheet').empty();
                $('#idScoreSheet').append('<option value="">请选择</option>');
                form.render();
                return;
            }
            if (sdSkillCa != '1') {
                var bizData = {
                    sdSkillCa: sdSkillCa ,
                    idPaper : data.value
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
        });

        form.on('submit(addPaper)', function (data) {
            var bizData = data.field;
            // 参数校验
            if (!bizData.idPaper) {
                layer.tips('请选泽试卷', '#addPaper');
                return false;
            }
            if (sdSkillCa != '1' && !bizData.idScoreSheet) {
                layer.tips('请选择评分表', '#addPaper', {zIndex:1000});
                return false;
            }

            bizData.paperName = $('#idPaper option:selected').text();

            $('#div-add-paper').remove();
            layer.confirm('是否应用到该考站的所有时段？', {
                btn: ['是', '否']
            }, function(index, layero){
                layer.close(index);
                addPaper(bizData, true);
            }, function(index){
                addPaper(bizData, false);
            });
            return false;
        });
    }

    //点击其他区域关闭
    $(document).mouseup(function(e){
        var userSet_con = $('#div-add-paper');
        if(!userSet_con.is(e.target) && userSet_con.has(e.target).length === 0){
            $('#div-add-paper').remove();
        }
    });


    function addPaper(data, flag) {
        var idInsStation = data.idInsStation;
        var bizData = {
            idInsStation: data.idInsStation,
            idPaper: data.idPaper,
            idScoreSheet: data.idScoreSheet,
            allFlag : flag
        }
        common.commonPost(basePath + '/pf/r/plan/paper/save/paper',
            bizData, '保存', 'stable-' + idInsStation, null, true);

        if (flag) {
            window.location.reload();
        } else {
            $('#paper-' + idInsStation).text(data.paperName);
        }
    }

});




