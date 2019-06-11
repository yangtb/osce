layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    formSelects: 'formSelects-v4'
}).use(['layer', 'index', 'table', 'form', 'jquery', 'step', 'element'
    , 'common', 'tableSelect', 'formSelects', 'laydate'], function () {
    var $ = layui.$
        , table = layui.table
        , common = layui.common
        , form = layui.form
        , step = layui.step
        , element = layui.element
        , tableSelect = layui.tableSelect
        , formSelects = layui.formSelects
        , laydate = layui.laydate;

    $(document).ready(function(){
        $("#area-main").empty();
        $("#area-main").append(bulidAreaHtml(1));
        $("#currStationNum_1").val(1);
        form.render();
    });

    // *******************日期 begin**************************
    lay('.time-box').each(function(){
        laydate.render({
            elem: this
            , type: 'time'
            , format: 'HH:mm'
            ,trigger: 'click'
        });
    });
    // *******************日期 end**************************


    // *******************step begin**************************
    step.render({
        elem: '#stepForm',
        filter: 'stepForm',
        width: '100%', //设置容器宽度
        stepWidth: '800px',
        height: '800px',
        stepItems: [{
            title: '考站定义'
        }, {
            title: '排站'
        }, {
            title: '模拟排考'
        }]
    });


    form.on('submit(formStep)', function (data) {
        saveThisPage(data.field)
        return false;
    });

    form.on('submit(formStep2)', function (data) {
        step.next('#stepForm');
        return false;
    });

    $('.pre').click(function () {
        step.pre('#stepForm');
    });

    $('.next').click(function () {
        step.next('#stepForm');
    });
    // *******************step end**************************


    // *******************考站操作 begin************************

    function bulidAreaHtml(areaIndex) {
        var html = '<div class=\'room-main\' id="area_' + areaIndex + '">\n' +
            '            <div class="room-msg">\n' +
            '                <div class="room-name">\n' +
            '                    <span id="naArea_' + areaIndex + '" class="room-num">考场' + areaIndex + '</span>\n' +
            '                    <img src="/osce/biz/img/template/set_btn.png"\n' +
            '                         id="set-btn-' + areaIndex + '" alt="设置" class=\'set-btn\' onclick="setAreaLoop(' + areaIndex + ')" style="display: none">\n' +
            '                    <input id="idArea_' + areaIndex + '" hidden>\n' +
            '                    <input id="currStationNum_' + areaIndex + '" hidden>\n' +
            '                    <input id="sdAreaLoopBegin_' + areaIndex + '" hidden>\n' +
            '                    <input id="sdAreaLoopEnd_' + areaIndex + '" hidden>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="content" id="station-main">\n' +
            bulidStationHtml(areaIndex, 1) +
            '                <div class="add-box" id="addStation_' + areaIndex + '" onclick="addStation(' + areaIndex + ')">\n' +
            '                    <div class="add-main">\n' +
            '                        <img src="/osce/biz/img/template/add_btn.png"\n' +
            '                             alt="add icon" class=\'add-btn\'>\n' +
            '                        添加考站\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '        </div>';
        return html;
    }

    function bulidStationHtml(areaIndex, stationIndex) {
        var html = '<div class="room-content" id="station_'  + areaIndex + '_' + stationIndex +  '">\n' +
            '       <div class="content-main">\n' +
            '          <div class="content-header">\n' +
            '              <input id="idStation_' + areaIndex + '_' + stationIndex + '" hidden>' +
            '              <span id="naStation_'  + areaIndex + '_' + stationIndex +  '">考站' + areaIndex + '-' + stationIndex + '</span>\n' +
            '              <a href="javascript:;" class=\'delete-btn\' id="delStation_' + areaIndex + '_' + stationIndex + '" onclick="delStation('+ areaIndex +', '+ stationIndex +')"></a>\n' +
            '          </div>\n' +
            '          <div class="content-text" style="margin-top: 5px;">\n' +
            '              <div class=\'text-item\' onclick="addSdStationCa('+ areaIndex +', '+ stationIndex +')">\n' +
            '                  <img src="/osce/biz/img/template/edit_btn.png" alt="编辑" class="edit-btn">\n' +
            '                  <span class="item-desc" id="sdStationCaText_' + areaIndex + '_' + stationIndex + '">请添加基地类型</span>\n' +
            '                  <input id="sdStationCa_' + areaIndex + '_' + stationIndex + '" hidden>' +
            '              </div>\n' +
            '              <div class=\'text-item\' onclick="addSdSkillCa('+ areaIndex +', '+ stationIndex +')">\n' +
            '                  <img src="/osce/biz/img/template/edit_btn.png" alt="编辑" class="edit-btn">\n' +
            '                  <span class="item-desc" id="sdSkillCaText_' + areaIndex + '_' + stationIndex + '">请添加技能类型</span>\n' +
            '                  <input id="sdSkillCa_' + areaIndex + '_' + stationIndex + '" hidden>' +
            '                  <input id="minCost_' + areaIndex + '_' + stationIndex + '" hidden>' +
            '              </div>\n' +
            '              <div class=\'text-item\'  onclick="addSite('+ areaIndex +', '+ stationIndex +')">\n' +
            '                  <img src="/osce/biz/img/template/edit_btn.png"\n' +
            '                       alt="编辑" class="edit-btn">\n' +
            '                  <span class="item-desc" id="siteText_' + areaIndex + '_' + stationIndex + '">请添加站点</span>\n' +
            '                  <input id="site_' + areaIndex + '_' + stationIndex + '" hidden>' +
            '              </div>\n' +
            '              <div class=\'text-item\' style="padding-left: 10px;">\n' +
            '                  <input type="checkbox" id="fgMust_' + areaIndex + '_' + stationIndex + '" title="必过考站" lay-skin="primary">\n' +
            '              </div>\n' +
            '          </div>\n' +
            '      </div>\n' +
            '      </div>';
        return html;
    }
    
    // 动态添加考场
    $("#numArea").on('blur', function () {
        var areaNum = $("#numArea").val();
        $("#area-main").empty();

        for(var i = 1; i <= areaNum; i++) {
            $("#area-main").append(bulidAreaHtml(i));
            $("#currStationNum_"+ i).val(1);
        }
        form.render();
        $('#stepForm').height(800 + i * 280);

        if (areaNum > 1) {
            for (var i = 1; i <= areaNum; i++) {
                $('#set-btn-' + i).css('display','block');
            }
        } else {
            $('#set-btn-1').css('display','none');
        }

    });

    // *******************考站操作 end**************************



    // *******************保存操作 begin************************
    function saveThisPage(data) {
        data.fgActive = '1';
        // 构建考场数据
        var tdAreaList = new Array(),
            areaNumSave = parseInt($("#numArea").val());

        console.log(areaNumSave);
        for (var i = 1; i <= areaNumSave; i++) {
            // 考站
            var tdStationList = new Array(),
                stationNumSave =  parseInt($("#currStationNum_" + i).val());
            console.log(stationNumSave);
            for (var j = 1; j <= stationNumSave; j++) {
                // 站点
                var tdSiteList = new Array();
                var siteData = $('#site_' + i + '_' + j).val();
                if (siteData) {
                    var arr = siteData.split("|");
                    for (var k = 0; k < arr.length; k++) {
                        var roomArr = arr[k].split("-");
                        var tdSite = {
                            idRoom : roomArr[0] ,        // 房间ID
                            numConcur : roomArr[1]       // 并发人数
                        }
                        tdSiteList.push(tdSite);
                    }
                }

                var tdStation = {
                    idStation : $("#idStation_" + i + '_' + j).val(),       // 考站ID
                    idArea : $("#idArea_" + i).val(),                       // 考场ID
                    naStation : $("#naStation_" + i + '_' + j).html(),      // 考站名称
                    sdStationCa : $("#sdStationCa_" + i + '_' + j).val(),   // 基地类型
                    sdSkillCa : $("#sdSkillCa_" + i + '_' + j).val(),       // 技能类型
                    minCost : $("#minCost_" + i + '_' + j).val(),           // 站点耗时
                    fgMust : $("#fgMust_" + i + '_' + j).val() == 'on' ? '1' : '0',             // 必过标志
                    tdSites : tdSiteList                                    // 站点
                }
                tdStationList.push(tdStation);
            }

            // 考场
            var tdArea = {
                idArea : $("#idArea_" + i).val(),                       // 考场ID
                idModel : idModel,                                      // 模板ID
                naArea : $("#naArea_" + i).html(),                      // 考场名称
                sdAreaLoopBegin : $("#sdAreaLoopBegin_" + i).val(),     // 1 首次循环半天 2 首次循环一天
                sdAreaLoopEnd : $("#sdAreaLoopEnd_" + i).val(),         // 1 末次循环半天 2 末次循环一天
                tdStations : tdStationList
            }
            tdAreaList.push(tdArea);
        }

        var bizData = {
            tdModel : data,         // 模板
            tdAreas : tdAreaList    // 考场
        }

        console.log(JSON.stringify(bizData))
        //return;

        $.ajax({
            url: basePath + '/pf/r/plan/template/add',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.tips(data.msg, '#step1', {tips: [4, '#FF5722']});
                    return false;
                } else {
                    // 重新设置表单数据
                    step.next('#stepForm');
                    return false;
                }
            },
            error: function () {
                layer.tips("提交失败", '#step1', {tips: [2, '#FF5722']});
                return false;
            }
        });
    }

    // *******************板寸操作 end**************************



});


function addStation(areaIndex) {
    var stationIndex = $("#currStationNum_"+ areaIndex).val();
    $(bulidStationHtml(areaIndex, parseInt(stationIndex) + 1)).insertBefore($("#addStation_"+ areaIndex + ""));
    $("#currStationNum_"+ areaIndex).val(parseInt(stationIndex) + 1);

    layui.use('form',function(){
        layui.form.render();
    });
}

function delStation(areaIndex, stationIndex) {
    layui.use('layer',function() {
        /*var layer = layui.layer;
        var url = basePath + '/pf/r/item/exam/del';
        var data = {
            list: reqData
        };
        layer.confirm('确定要删除考站【'+ currentData.mainItem +'】？', {
            title: '提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3,
        }, function (index) {
            layer.close(index);
            //return common.commonPost(url, data, '删除', null, _delItemBack, true);
        });*/
        $('#station_' + areaIndex + '_' + stationIndex).remove();
        $("#currStationNum_"+ areaIndex).val(parseInt(stationIndex) - 1);

    });

}

// 请添加基地类型
function addSdStationCa(areaIndex, stationIndex) {
    layui.use('layer',function(){
        var layer = layui.layer;
        var y = event.clientY;
        var x = event.clientX;
        layer.open({
            title: false,
            type: 1,
            closeBtn: 0, //不显示关闭按钮
            anim: 5,
            shadeClose: true, //开启遮罩关闭
            resize : false,
            area: ['260px', 'auto'],
            offset: [y + 'px', x + 'px'],
            btn: ['确认', '关闭'],
            content: '<select class="modal-select" id="sdStationCaWin">\n' +
                '         <option value="1">内科</option>\n' +
                '         <option value="2">外科</option>\n' +
                '         <option value="3">妇产科</option>\n' +
                '         <option value="4">儿科</option>\n' +
                '         <option value="5">急诊</option>\n' +
                '         <option value="6">医患沟通</option>\n' +
                '         <option value="7">神经内科</option>\n' +
                '         <option value="8">眼鼻喉</option>\n' +
                '         <option value="9">皮肤科</option>\n' +
                '         <option value="10">肿瘤</option>\n' +
                '         <option value="11">传染</option>\n' +
                '         <option value="12">心理精神</option>\n' +
                '         <option value="13">循证医学</option>\n' +
                '         <option value="14">康复</option>\n' +
                '         <option value="15">伦理</option>\n' +
                '     </select>\n'
            ,yes: function(){
                console.log(areaIndex + '---' +stationIndex + '---' + $('#sdStationCaWin').val())
                $('#sdStationCaText_' + areaIndex + '_' + stationIndex).html($("#sdStationCaWin option:selected").text());
                $('#sdStationCa_' + areaIndex + '_' + stationIndex).val($('#sdStationCaWin').val());
                layer.closeAll();
            }, success: function(layero, index){
                var v = $('#sdStationCa_' + areaIndex + '_' + stationIndex).val();
                $("#sdStationCaWin option[value='"+ v +"']").attr("selected",true)

            }
        });
    });
}

// 请添加技能类型
function addSdSkillCa(areaIndex, stationIndex) {
    layui.use('layer',function(){
        var layer = layui.layer;
        var y = event.clientY;
        var x = event.clientX;
        layer.open({
            title: false,
            type: 1,
            closeBtn: 0, //不显示关闭按钮
            anim: 5,
            shadeClose: true, //开启遮罩关闭
            area: ['260px', 'auto'],
            offset: [y + 'px', x + 'px'],
            btn: ['确认', '关闭'],
            resize : false,
            content: '<select class="modal-select" id="sdSkillCaWin">\n' +
                '         <option value="1">理论试题</option>\n' +
                '         <option value="2">技能操作</option>\n' +
                '         <option value="3">病史采集</option>\n' +
                '     </select>\n' +
                '     <input type="number" min="1" id="minCostWin" ' +
                '           style="margin: 10px 0px 0px 34px; height: 30px; width: 190px; text-indent: 5px;" placeholder="耗时(min)"/>'
            ,yes: function(){
                if (!$('#minCostWin').val()) {
                    layer.tips("请填写耗时", '#minCostWin');
                    return false;
                }
                console.log(areaIndex + '---' +stationIndex + '---' + $('#sdSkillCaWin').val())
                $('#sdSkillCaText_' + areaIndex + '_' + stationIndex).html($("#sdSkillCaWin option:selected").text() + ' ' + $('#minCostWin').val() + 'min');
                $('#sdSkillCa_' + areaIndex + '_' + stationIndex).val($('#sdSkillCaWin').val());
                $('#minCost_' + areaIndex + '_' + stationIndex).val($('#minCostWin').val());
                layer.closeAll();
            }, success: function(layero, index){
                var v = $('#sdSkillCa_' + areaIndex + '_' + stationIndex).val();
                //console.log($("#sdStationCaWin").find("option[value='"+ v +"']"))
                $("#sdSkillCaWin option[value='"+ v +"']").attr("selected",true)
                $('#minCostWin').val($('#minCost_' + areaIndex + '_' + stationIndex).val())
            }
        });
    });
}

// 请添加站点
function addSite(areaIndex, stationIndex) {
    layui.use('layer',function(){
        var layer = layui.layer;
        var y = event.clientY;
        var x = event.clientX;
        layer.open({
            title: false,
            type: 1,
            closeBtn: 0, //不显示关闭按钮
            anim: 5,
            shadeClose: true, //开启遮罩关闭
            area: ['300px', '165px'],
            offset: [y + 'px', x + 'px'],
            btn: ['确认', '关闭'],
            resize : false,

            content: '<table id="roomTable" border="0" width="100%" style="text-align: center; vertical-align: center; padding-top: 10px;">\n' +
                '        <input id="rowNum" hidden>\n' +
                buildTr(1) +
                '    </table>'
            ,yes: function() {
                var rowNum = $('#rowNum').val();
                console.log('rowNum:' + rowNum)
                var roomData = '', siteText = '', idRoomArr = new Array();
                for (var i = 1; i <= rowNum; i++) {
                    console.log($('#idRoom_' + i).val() + '----' + $('#numConcur_' + i).val())
                    var idRoom = $('#idRoom_' + i).val();
                    if (idRoom) {

                        console.log(idRoomArr)
                        console.log($.inArray(idRoom, idRoomArr))
                        if ($.inArray(idRoom, idRoomArr) != -1) {
                            layer.tips("该房间号重复", '#idRoom_' + i);
                            return false;
                        }
                        if (!$('#numConcur_' + i).val()) {
                            layer.tips("请填写并发人数", '#numConcur_' + i);
                            return false;
                        } else {
                            if (i > 1) {
                                roomData += '|';
                            }
                            roomData += $('#idRoom_' + i).val() + '-' + $('#numConcur_' + i).val();
                            siteText += $("#idRoom_" + i + " option:selected").text() + ' ';
                        }
                        idRoomArr.push(idRoom);
                    }

                }
                console.log("roomData:" + roomData + ', siteText: ' + siteText)
                $('#site_' + areaIndex + '_' + stationIndex).val(roomData);
                $('#siteText_' + areaIndex + '_' + stationIndex).html(siteText);
                layer.closeAll();
            }, success: function(layero, index){
                $('#rowNum').val(1);
                var siteData = $('#site_' + areaIndex + '_' + stationIndex).val();
                console.log("站点数据：" + siteData);
                if (siteData) {
                    var arr = siteData.split("|");
                    for (var i = 0; i < arr.length; i++) {
                        console.log(arr[i])
                        var roomArr = arr[i].split("-");
                        if (i != 0) {
                            addTr();
                        }
                        $("#idRoom_" + (i + 1) + " option[value='"+ roomArr[0] +"']").attr("selected",true)
                        $('#numConcur_' + (i + 1)).val(roomArr[1]);
                    }
                }
            }
        });
    });
}

function buildTr(i) {
    return '  <tr id="tableTr_' + i + '">\n' +
        '            <td width="100" style="padding-left: 5px;">' +
        '               <select class="modal-select" id="idRoom_' + i + '" style="width: 100px; margin-top:5px;">\n' +
        '                   <option value="1">101</option>\n' +
        '                   <option value="2">102</option>\n' +
        '                   <option value="3">103</option>\n' +
        '               </select>\n' +
        '            </td>\n' +
        '            <td width="100">' +
        '               <input type="number" min="1" id="numConcur_' + i + '" ' +
        '                   style="height: 27px; width: 100px; text-indent: 5px; margin-left: 15px; margin-top:5px;" placeholder="并发人数"/>' +
        '            </td>\n' +
        '            <td width="100">' +
        '               <a style="cursor: pointer; font-size: 20px" onclick="addTr()">+</a>&nbsp;' +
        '               <a style="cursor: pointer; font-size: 20px" onclick="delTr(' + i + ')">-</a>' +
        '            </td>\n' +
        '        </tr>\n';
}

function addTr() {
    var rowNum = parseInt($('#rowNum').val()) + 1;
    $("#roomTable").append(buildTr(rowNum));
    $('#rowNum').val(rowNum);
}

function delTr(i){
    $("#tableTr_" + i).remove();
}

function bulidStationHtml(areaIndex, stationIndex) {
    var html = '<div class="room-content" id="station_'  + areaIndex + '_' + stationIndex +  '">\n' +
        '       <div class="content-main">\n' +
        '          <div class="content-header">\n' +
        '              <input id="idStation_' + areaIndex + '_' + stationIndex + '" hidden>' +
        '              <span id="naStation_'  + areaIndex + '_' + stationIndex +  '">考站' + areaIndex + '-' + stationIndex + '</span>\n' +
        '              <a href="javascript:;" class=\'delete-btn\' id="delStation_' + areaIndex + '_' + stationIndex + '" onclick="delStation('+ areaIndex +', '+ stationIndex +')"></a>\n' +
        '          </div>\n' +
        '          <div class="content-text" style="margin-top: 5px;">\n' +
        '              <div class=\'text-item\' onclick="addSdStationCa('+ areaIndex +', '+ stationIndex +')">\n' +
        '                  <img src="/osce/biz/img/template/edit_btn.png" alt="编辑" class="edit-btn">\n' +
        '                  <span class="item-desc" id="sdStationCaText_' + areaIndex + '_' + stationIndex + '">请添加基地类型</span>\n' +
        '                  <input id="sdStationCa_' + areaIndex + '_' + stationIndex + '" hidden>' +
        '              </div>\n' +
        '              <div class=\'text-item\' onclick="addSdSkillCa('+ areaIndex +', '+ stationIndex +')">\n' +
        '                  <img src="/osce/biz/img/template/edit_btn.png" alt="编辑" class="edit-btn">\n' +
        '                  <span class="item-desc" id="sdSkillCaText_' + areaIndex + '_' + stationIndex + '">请添加技能类型</span>\n' +
        '                  <input id="sdSkillCa_' + areaIndex + '_' + stationIndex + '" hidden>' +
        '                  <input id="minCost_' + areaIndex + '_' + stationIndex + '" hidden>' +
        '              </div>\n' +
        '              <div class=\'text-item\'  onclick="addSite('+ areaIndex +', '+ stationIndex +')">\n' +
        '                  <img src="/osce/biz/img/template/edit_btn.png"\n' +
        '                       alt="编辑" class="edit-btn">\n' +
        '                  <span class="item-desc" id="siteText_' + areaIndex + '_' + stationIndex + '">请添加站点</span>\n' +
        '                  <input id="site_' + areaIndex + '_' + stationIndex + '" hidden>' +
        '              </div>\n' +
        '              <div class=\'text-item\' style="padding-left: 10px;">\n' +
        '                  <input type="checkbox" id="fgMust_' + areaIndex + '_' + stationIndex + '" title="必过考站" lay-skin="primary">\n' +
        '              </div>\n' +
        '          </div>\n' +
        '      </div>\n' +
        '      </div>';
    return html;
}

// 循环策略
function setAreaLoop(areaIndex) {
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            title: false,
            type: 1,
            closeBtn: 0, //不显示关闭按钮
            anim: 5,
            shadeClose: true, //开启遮罩关闭
            resize: false,
            area: ['260px', 'auto'],
            offset: [event.clientY + 'px', event.clientX + 'px'],
            btn: ['确认', '关闭'],
            content: '<select class="modal-select" id="sdAreaLoopBegin">\n' +
                '           <option value="1">第一次循环半天</option>\n' +
                '           <option value="2">第一次循环一天</option>\n' +
                '       </select>\n' +
                '       <select class="modal-select" id="sdAreaLoopEnd">\n' +
                '           <option value="1">最后一次循环半天</option>\n' +
                '           <option value="2">最后一次循环一天</option>\n' +
                '       </select>'
            , yes: function(){
                $('#sdAreaLoopBegin_' + areaIndex).val($('#sdAreaLoopBegin').val());
                $('#sdAreaLoopEnd_' + areaIndex).val($('#sdAreaLoopEnd').val());
                layer.closeAll();
            }, success: function(layero, index){
                $("#sdAreaLoopBegin option[value='"+ $('#sdAreaLoopBegin_' + areaIndex).val() +"']").attr("selected",true)
                $("#sdAreaLoopEnd option[value='"+ $('#sdAreaLoopEnd_' + areaIndex).val() +"']").attr("selected",true)
            }
        });
    })
}
