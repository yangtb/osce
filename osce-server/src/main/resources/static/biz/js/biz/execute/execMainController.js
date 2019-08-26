layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['layer', 'index'], function () {
    var $ = layui.$
        , layer = layui.layer;

    $(document).ready(function () {
        clearHead();
    });

    function clearHead() {
        $('#name-queue').text('');
        $('#currStudentPhoneNo').text('');
        $('#currStudentTime').text('');
        $("#currStudentStatus").text('');
        $("#idCard").val('');

    }

    $("#auth").on('click', function () {
        var idCard = $("#idCard").val().trim();
        if (!idCard) {
            $("#idCard").focus();
            //layer.tips('请填写身份证', '#idCard', {tips: [3, '#FF5722']});
            layer.msg('请填写身份证');
            return;
        } else if (idCard.length != 15 && idCard.length != 18) {
            $("#idCard").focus();
            layer.msg('身份证不正确');
            //layer.tips('身份证不正确', '#idCard', {tips: [3, '#FF5722']});
            return;
        }
        authStudent(idCard);
    });

    function authStudent(idCard) {
        var bizData = {
            idOrg: idOrg,
            idRoom: idRoom,
            idCard: idCard
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

    function fullPageHead(data) {
        clearHead();
        $('#name-queue').text(data.realName + " " + data.noReg);
        $('#currStudentPhoneNo').text(data.phoneNo);
        $('#currStudentTime').text(data.planBegin + "~" + data.planEnd);
        var statusText = '';
        if (data.sdExecQueue == 2) {
            statusText = '叫号待认证';
        } else if (data.sdExecQueue == 3) {
            statusText = '叫号已认证';
        } else if (data.sdExecQueue == 4) {
            statusText = '考试开始';
        } else if (data.sdExecQueue == 5) {
            statusText = '已交卷';
        }
        $("#currStudentStatus").text(statusText);
        $("#idCard").val(data.idCard);
        queryTest(data.userId);
    }

    function queryTest(userId) {
        var bizData = {
            idOrg: idOrg,
            idRoom: idRoom,
            userId: userId
        }
        $.ajax({
            url: basePath + '/r/exec/list/test',
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
                    fullTest(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function fullTest(data) {
        // console.log(data)
        $("#testCard").empty();
        $("#testCard").append(buildTestCardHtml(data));
        testEventListener();
    }

    function buildTestCardHtml(data) {
        var html = '';

        $.each(data, function (index, content) {
            var cardBorder = content.status == 1 ? 'card-border-big-active' : 'card-border-big';
            var sdSkillCaText = '';
            if (content.sdSkillCa == '1') {
                sdSkillCaText = '理论试题';
            } else if (content.sdSkillCa == '2') {
                sdSkillCaText = '技能操作';
            } else if (content.sdSkillCa == '3') {
                sdSkillCaText = '病史采集';
            }
            var testStatus1 = content.status == 1 ? '开考' : '查看';
            var testStatus2 = content.status == 1 ? '待考' : '已交卷';
            html += '<div class="layui-col-md4">\n' +
                '       <div class="layui-card ' + cardBorder + '">\n' +
                '           <div class="layui-card-header card-font card-title">测试</div>\n' +
                '           <div class="layui-card-body card-font">\n' +
                '              <ul>\n' +
                '                  <li class="li-normal"><span class="left-label">科目</span><span class="right-label">' + content.sdStationCaText + '</span></li>\n' +
                '                  <li class="li-normal"><span class="left-label">类型</span><span class="right-label">' + sdSkillCaText + '</span></li>\n' +
                '                  <li class="li-normal"><span class="left-label">试题</span><span class="right-label">' + content.naPaper + '</span></li>\n' +
                '                  <li class="li-normal"><span class="left-label">限时</span><span class="right-label">' + content.minCost + 'min</span></li>\n' +
                '                  <li class="li-bottom"><span class="left-label">状态</span><span class="right-label">' + testStatus2 + '</span></li>\n' +
                '                  <li class="li-last" data-queue="' + content.idExecQueue + '" data-index="' + content.status + '">\n' +
                '                      <img class="img-query" src="' + basePath + '/biz/img/exec/u14593.png">\n' +
                '                      <p class="left-label">' + testStatus1 + '</p>\n' +
                '                  </li>\n' +
                '               </ul>\n' +
                '           </div>\n' +
                '      </div>\n' +
                '   </div>';
        });

        return html;
    }

    function testEventListener() {
        var tests = document.querySelectorAll(".li-last");
        for (var i = 0; i < tests.length; i++) {
            tests[i].addEventListener('click', function () {
                test(this.getAttribute('data-index'), this.getAttribute('data-queue'));
            });
        }
    }

    function test(status, idExecQueue) {
        if (status == 1) {
            // 开考
            startTest(status, idExecQueue);
        } else {
            openExecPage(idExecQueue);
        }

    }

    function startTest(status, idExecQueue) {
        var bizData = {
            parSdExecQueue: 4,
            parIdExecQueue: idExecQueue
        }
        $.ajax({
            url: basePath + '/r/exec/start',
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
                    openExecPage(idExecQueue);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function openExecPage(idExecQueue) {
        window.open(basePath + "/m/exec/test?idExecQueue=" + idExecQueue);
    }



});





