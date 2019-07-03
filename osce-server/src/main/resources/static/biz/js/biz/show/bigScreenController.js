layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['layer', 'index', 'admin'], function () {
    var $ = layui.$
        , admin = layui.admin;

    $(document).ready(function () {
        // 模拟排考
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
                    layer.msg(data.msg, {icon: 5});
                    return false;
                } else {
                    //console.log("模拟排考返回data: " + JSON.stringify(data.data))
                    fullPageHead(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("获取展示信息失败", {icon: 5});
                return false;
            }
        });
    }

    var idPlan, idArea, timeSection, itemNum, limit;

    function fullPageHead(data) {
        $("#naOrg").text(data.naOrg);
        if (data.naPlan) {
            $("#headInfo").text(data.naPlan + " | " + data.naArea + " | "
                + data.fgReplan + " | " + data.planDay + " " + data.timeBegin + "~" + data.timeEnd + "");
        }
        // 加载考试列表
        if (data.idPlan) {
            idPlan = data.idPlan;
            idArea = data.idArea;
            timeSection = data.timeSection;
            itemNum = data.itemNum;
            limit = data.limit;
            data.page = 1;
            loadStudentInfo(data);
        }

        admin.fullScreen();
    }

    function loadStudentInfo(data) {
        if (!data.idPlan) {
            return;
        }
        var bizData = {
            idPlan: data.idPlan,
            idArea: data.idArea,
            timeSection: data.timeSection,
            page: data.page,
            limit: data.limit
        }
        $.ajax({
            url: basePath + '/pf/r/show/big/screen/detail',
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
                    fullPageBottom(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("服务器貌似连不到啦", {icon: 5});
                return false;
            }
        });
    }

    function fullPageBottom(data) {
        $("#student").empty();
        if (data.length > 0) {
            $("#student").append(buildHtml(data));
        }
    }

    function buildHtml(data) {
        var html = '<tr>\n' +
            '          <th>序号</th>\n' +
            '          <th>学号</th>\n' +
            '          <th>姓名</th>\n' +
            '          <th>站点</th>\n' +
            '          <th>状态</th>\n' +
            '       </tr>';
        $.each(data, function (index, content) {
            html += '<tr>\n' +
                '     <td>S' + content.rownum + '</td>\n' +
                '     <td>' + content.userCd + '</td>\n' +
                '     <td>' + content.realName + '</td>\n' +
                '     <td>' + content.siteName + '</td>\n';
            var styleCss = '';
            if (content.status === '学案进场待确认') {
                styleCss = 'sure-text';
            } else if (content.status === '考试考核') {
                styleCss = 'test-text';
            } else if (content.status === '本场提交，下场未开始') {
                styleCss = 'await-text';
            } else if (content.status === '考试结束') {
                styleCss = 'end-text';
            } else if (content.status === '故障') {
                styleCss = 'fault-text';
            }
            html += '<td class="' + styleCss + '">' + content.status + '</td>\n';
            html += '</tr>';
        });
        return html;
    }

    setInterval(function () {
        timeoutPage();
    }, 10000);

    function timeoutPage() {
        var nowTime = nowTimeStr(2);
        if (nowTime === "00:00" || nowTime === "12:00") {
            queryHeader();
        } else {
            refreshStudent();
        }
    }

    function refreshStudent() {
        var pageNum = Math.ceil(itemNum / limit);
        var bizData = {
            idPlan: idPlan,
            idArea: idArea,
            timeSection: timeSection,
            page: pageNum,
            limit: limit
        }
        loadStudentInfo(bizData);
    }

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



