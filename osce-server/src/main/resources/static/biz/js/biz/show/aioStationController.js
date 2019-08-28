layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['layer', 'index', 'admin', 'util'], function () {
    var $ = layui.$
        , admin = layui.admin
        , util = layui.util;

    $(document).ready(function () {
        queryHeader();
    });

    function queryHeader() {
        var bizData = {}
        $.ajax({
            url: basePath + '/pf/r/show/big/screen/main',
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
                    fullPageHead(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    var idPlan, idArea, timeSection

    function fullPageHead(data) {
        $("#idOrg").val(data.idOrg);
        $("#naOrg").text(data.naOrg);
       /* data.idPlan = 1;
        data.idArea = 10;
        data.timeSection = 1;
        data.naPlan = "测试的是时候手动数据"*/
        if (data.naPlan) {
            $("#headInfo").text(data.naPlan + " | " + data.naArea + " | "
                + data.fgReplan + " | " + data.planDay + " " + data.timeBegin + "~" + data.timeEnd + "");

            idPlan = data.idPlan;
            idArea = data.idArea;
            timeSection = data.timeSection;
            queryLeft();
            queryRight();
        }
        admin.fullScreen();
    }

    function queryLeft() {
        var bizData = {
            idPlan: idPlan,
            idArea: idArea,
            timeSection: timeSection,
            idRoom: idRoom
        }
        $.ajax({
            url: basePath + '/pf/r/aio/station/room/info',
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
                    var sucData = data.data;
                    fullLeftInfo(sucData);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function fullLeftInfo(data) {
        if (!data) {
            return;
        }
        $('#naStation').text(data.naStation);
        $('#sdStationCaText').text(data.sdStationCaText);
        var sdSkillCaText = '';
        if (data.sdSkillCa == '1') {
            sdSkillCaText = '理论试题';
        } else if (data.sdSkillCa == '2') {
            sdSkillCaText = '技能操作';
        } else if (data.sdSkillCa == '3') {
            sdSkillCaText = '病史采集';
        }
        $('#sdSkillCa').text(sdSkillCaText);
        $('#naPaper').text(data.naPaper);
        $('#desPaper').text(data.desPaper);

        // 二维码生成
        if (data.qrCodeUrl) {
            var qrcode = new QRCode(document.getElementById("qrcode"), {
                width: 160,
                height: 160
            });
            qrcode.makeCode(data.qrCodeUrl);
        }

    }

    function queryRight() {
        if (!idPlan) {
            return;
        }
        var bizData = {
            idPlan: idPlan,
            idArea: idArea,
            timeSection: timeSection,
            idRoom: idRoom
        }
        $.ajax({
            url: basePath + '/pf/r/aio/station/room/student/list',
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
                    fullRight(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function fullRight(data) {
        var serverTimeStr = data.nowTime;
        var serverTimeArr = serverTimeStr.split(" ");
        var ymd = serverTimeArr[0];
        var hms = serverTimeArr[1];
        var countdown = $('#countdown').text();
        if (!countdown || countdown == 0) {
            clearCurrentStuInfo();
        }
        $("#rightWait").empty();

        var html = '<p class="await-num">等待人数 ' + data.studentNum + '</p>';
        var bottomHtml = html;
        var flag = false;
        $.each(data.roomStudents, function (index, content) {
            if (index == 0 && content.sdExecQueue >= 2) {
                flag = true;
                if (!countdown || countdown == 0) {
                    $("#currStudentName").text(content.realName);
                    $("#currStudentCd").text(check(content.cdStudent));
                    $("#currStudentPhoneNo").text(content.phoneNo);
                    $("#currStudentIdCard").text(content.idcard);
                    $("#currStudentTime").text(content.planBegin + '~' + content.planEnd);
                    var statusText = '';
                    if (content.sdExecQueue == 2) {
                        statusText = '叫号待认证';
                    } else if (content.sdExecQueue == 3) {
                        statusText = '叫号已认证';
                    } else if (content.sdExecQueue == 4) {
                        statusText = '考试开始';
                    }
                    $("#currStudentStatus").text(statusText);
                    if (content.sdExecQueue == 4) {
                        // 计算倒计时 00:01:32

                        var arrYmd = ymd.split("-"),
                            arrHms = hms.split(":");
                        var actBegin = content.countdownTime, arrActBegin;
                        if (actBegin) {
                            arrActBegin = actBegin.split(":");

                            var endTime = new Date(arrYmd[0], arrYmd[1] - 1, arrYmd[2], arrActBegin[0], arrActBegin[1], arrActBegin[2])
                                , serverTime = new Date(arrYmd[0], arrYmd[1] - 1, arrYmd[2], arrHms[0], arrHms[1], arrHms[2]);

                            util.countdown(endTime, serverTime, function(date, serverTime, timer){
                                //console.log(date[0] + '--' + date[1] + '--' + date[2] + '--' + date[3])
                                //var str = date[0] + '天' + date[1] + '时' +  date[2] + '分' + date[3] + '秒';
                                var countdownTime = date[0] * 24 * 60 * 60 + date[1] * 60 * 60 + date[2] * 60 + date[3];
                                $('#countdown').html(countdownTime);
                            });

                        }
                    }
                }
            } else {
                var activeStyle = '';
                if ((flag && index == 1) || ((!flag && index == 0))) {
                    activeStyle = 'actived';
                }
                var idCard = content.idcard;
                idCard = idCard.substring(0, 2) + "************" + idCard.substring(idCard.length - 4);
                bottomHtml += '<div class="await-item ' + activeStyle + '">\n' +
                    '       <p class="awaiter-left">\n' +
                    '           <span class="awaiter-name">' + content.realName + '</span>\n' +
                    '           <span class="awaiter-num">' + check(content.cdStudent) + '</span>\n' +
                    '           <span class="awaiter-tel">' + content.phoneNo + '</span>\n' +
                    '       </p>\n' +
                    '       <p class="awaiter-right">\n' +
                    '           <span class="awaiter-id">' + idCard + '</span>\n' +
                    '           <span class="join-time">' + content.planBegin + '</span>\n' +
                    '       </p>\n' +
                    '   </div>';
            }
        });
        $("#rightWait").append(bottomHtml);
    }

    function clearCurrentStuInfo() {
        $("#currStudentName").text('');
        $("#currStudentCd").text('');
        $("#currStudentPhoneNo").text('');
        $("#currStudentIdCard").val('');
        $("#currStudentTime").text('');
        $("#currStudentStatus").text('');
    }

    $("#authentication").on('click', function () {
        if (!$('#currStudentIdCard').text()) {
            layer.msg("当前无学员");
            return;
        }
        // 认证
        var bizData = {
            idOrg: $("#idOrg").val(),
            idRoom: idRoom,
            idCard: $('#currStudentIdCard').text()
        }
        $.ajax({
            url: basePath + '/r/exec/auth',
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
                    $("#currStudentStatus").text('叫号已认证');
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    });


    // ===================定时器 begin===================
    setInterval(function () {
        timeoutPage();
    }, 600000);

    function timeoutPage() {
        var nowTime = nowTimeStr(2);
        if (nowTime === "00:00" || nowTime === "13:00") {
            queryHeader();
            return;
        }
        if (!idPlan) {
            queryHeader();
        }
    }

    setInterval(function () {
        queryRight();
    }, 5000);

    setInterval(function () {
        setNowTime();
    }, 1000);

    function setNowTime() {
        document.getElementById("nowtime").innerHTML = nowTimeStr(1);
    }

    function nowTimeStr(flag) {
        //获取年月日
        var time = new Date();

        //获取时分秒
        var h = time.getHours();
        var m = time.getMinutes();
        var s = time.getSeconds();

        //检查是否小于10
        h = check(h);
        m = check(m);
        s = check(s);
        if (flag == 1) {
            return h + ":" + m + ":" + s;
        } else {
            return h + ":" + m;
        }
    }

    //时间数字小于10，则在之前加个“0”补位。
    function check(i) {
        //方法一，用三元运算符
        var num;
        i < 10 ? num = "0" + i : num = i;
        return num;
    }


});


