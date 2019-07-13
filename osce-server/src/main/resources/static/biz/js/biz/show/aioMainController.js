layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['layer', 'index', 'admin', 'table'], function () {
    var $ = layui.$
        , admin = layui.admin
        , layer = layui.layer
        , table = layui.table;



    $("#more").on('click', function () {
        admin.popupRight({
            id: 'LAY-popup-right-aio' //定义唯一ID，防止重复弹出
            , success: function () {
                //将 views 目录下的某视图文件内容渲染给该面板
                layui.view(this.id).render('system/aio');
            }
        });
    });

    $(document).ready(function () {
        // 模拟排考
        queryHeader();
        registerStuEventListener();
    });

    function registerStuEventListener() {
        var registerStu = document.querySelectorAll(".register-stu");
        for (var i = 0; i < registerStu.length; i++) {
            registerStu[i].addEventListener('click', function () {
                var dataIndex = this.getAttribute('data-index');
                onChangeTab(dataIndex)
            });
        }
    }

    function onChangeTab(tabId) {
        if (tabId === "1") {
            $('#registerBtn').addClass('actived')
            $('#registeredBtn').removeClass('actived')
            $('#awaitRegister').css('display', 'flex')
            $('#hasRegister').css('display', 'none')
        } else {
            $('#registeredBtn').addClass('actived')
            $('#registerBtn').removeClass('actived')
            $('#awaitRegister').css('display', 'none')
            $('#hasRegister').css('display', 'flex')
            loadRegisterTable();
            /*if (reprintData) {
                reprintData = null;
            }*/
        }
    }

    function queryHeader() {
        var bizData = {}
        $.ajax({
            url: basePath + '/pf/r/aio/main',
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

    var idPlan, idArea, timeSection,
        currentNaOrg = '',
        currentNaPlan = '',
        currentNaArea = '',
        currentFgReplan = '',
        currentPlanDay = '';
    function fullPageHead(data) {
        // todo
        data.idPlan = 1;
        data.idArea = 10;
        data.timeSection = 1;
        data.naPlan = "测试";
        // todo
        $("#naOrg").text(data.naOrg);
        $("#naOrg-1").text(data.naOrg);
        $("#naOrg-2").text(data.naOrg);
        if (data.naPlan) {
            $("#headInfo").text(data.naPlan + " | " + data.naArea + " | "
                + data.fgReplan + " | " + data.planDay + " " + data.timeBegin + "~" + data.timeEnd + "");

            $("#headInfo-1").text(data.naPlan + " | " + data.naArea + " | "
                + data.fgReplan + " | " + data.planDay + " " + data.timeBegin + "~" + data.timeEnd + "");

            $("#headInfo-2").text(data.naPlan + " | " + data.naArea + " | "
                + data.fgReplan + " | " + data.planDay);

            idPlan = data.idPlan;
            idArea = data.idArea;
            timeSection = data.timeSection;
            currentNaOrg = data.naOrg;
            currentNaPlan = data.naPlan;
            currentNaArea = data.naArea;
            currentFgReplan = data.fgReplan;
            currentPlanDay = data.planDay;
            queryStudentRegisterNum();
        }
        admin.fullScreen();
    }


    // ===================身份识别 begin===================
    $("#identification").on('click', function () {
        clearStudentInfo();
        clearStudentQueue();
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
        // 学生登记
        aioStudentRegister(idCard);
    });

    // 登记
    function aioStudentRegister(idCard) {
        var bizData = {
            parIdPlan: idPlan,
            parIdArea: idArea,
            parTimeSection: timeSection,
            parIdCard: idCard
        }
        $.ajax({
            url: basePath + '/pf/r/aio/student/register',
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
                    fullStudentInfo(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    var currentUserId;
    function fullStudentInfo(data) {
        if (data) {
            $("#realName").text(data.realName);
            $("#phoneNo").text(data.phoneNo);
            $("#headPhoto").empty();
            $("#headPhoto").append("<img class=\"face-img\" src='" + data.photoAddr + "'/>");

            $("#student-queue").text(data.realName + ' ' + data.noReg);

            // 统计登记数目
            queryStudentRegisterNum();
            // 查询待考队列
            currentUserId = data.userId;
            selectStudentQueue(data.userId);
        }
    }

    function clearStudentInfo() {
        $("#realName").text('');
        $("#phoneNo").text('');
        $("#headPhoto").empty();
        $("#headPhoto").text('学员头像');
        currentUserId = null;
    }

    function clearStudentQueue() {
        $("#studentQueue").empty();
    }

    function queryStudentRegisterNum() {
        var bizData = {
            idPlan: idPlan,
            idArea: idArea,
            timeSection: timeSection
        }
        $.ajax({
            url: basePath + '/pf/r/aio/student/register/num',
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
                    if (sucData) {
                        $("#registerNum").text(sucData.registerNum);
                        $("#stuTotalNum").text(sucData.stuTotalNum);
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

    // 学员的待考队列
    function selectStudentQueue(userId) {
        var bizData = {
            idPlan: idPlan,
            idArea: idArea,
            timeSection: timeSection,
            idUserStudent: userId
        }
        $.ajax({
            url: basePath + '/pf/r/aio/student/exec/queue',
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
                    fullStudentQueue(data.data);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    function fullStudentQueue(data) {
        $("#studentQueue").empty();
        $("#studentQueue").append(buildStudentQueueHtml(data));
    }

    function buildStudentQueueHtml(data) {
        var html = '';
        if (data.length == 0) {
            return html;
        }
        $.each(data, function (index, content) {
            html += '<div class="tester-info-item">\n' +
                '       <div class="ietm-left">\n' +
                '           <span class="item-left-1">' + content.naStation + '</span>\n' +
                '           <span class="item-left-2">' + content.naRoom + '</span>\n' +
                '           <span class="item-left-3">' + content.idPaperText + '</span>\n' +
                '       </div>\n' +
                '       <p class="tester-time">' + content.planBegin + '-' + content.planEnd + '</p>\n' +
                '    </div>';
        });
        return html;
    }

    // ===================身份识别 end===================

    function loadRegisterTable() {
        table.render({
            elem: '#registerTable'
            , id: 'registerTableId'
            , url: basePath + '/pf/p/aio/student/registered'
            , height: 'full-250'
            , skin: 'line'
            , cols: [[
                {field: 'realName', minWidth: 150, title: '姓名'}
                , {field: 'noReg', minWidth: 100, title: '入场序号'}
                , {field: 'phoneNo', minWidth: 140, title: '手机号'}
                , {field: 'idcard', minWidth: 180, title: '身份证号'}
                , {field: 'gmtReg', minWidth: 140, title: '入场时间'}
            ]]
            , where : {
                idPlan: idPlan,
                idArea: idArea,
                timeSection: timeSection
            }
        });
    }

    var _registerTableReload = function () {
        table.reload('registerTableId', {
            height: 'full-250'
        });
    }


    // ===================定时器 begin===================
    setInterval(function () {
        timeoutPage();
    }, 10000);

    function timeoutPage() {
        var nowTime = nowTimeStr(2);
        if (nowTime === "00:00" || nowTime === "12:00") {
            queryHeader();
        }
    }

    setInterval(function () {
        setNowTime();
    }, 1000);

    function setNowTime() {
        document.getElementById("nowtime").innerHTML = nowTimeStr(1);
        document.getElementById("nowTime-2").innerHTML = nowTimeStr(1);
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

    // ===================定时器 end ===================



    // ===================打印 begin ===================
    $("#printQueue").on('click', function () {
        if (!$('#realName').text()) {
            layer.msg("请先进行身份识别");
            return;
        }
        var printData = {
            naOrg : currentNaOrg,
            naPlan : currentNaPlan,
            naArea : currentNaArea,
            fgReplan : currentFgReplan,
            planDay : currentPlanDay,
            studentQueue : $("#student-queue").text()
        };
        commonPrint(printData, currentUserId);
        printAfterClear();
    });

    function printAfterClear() {
        clearStudentInfo();
        clearStudentQueue();
        $("#student-queue").text('');
        $("#idCard").val('');
        currentUserId = null;
    }

    //初始化打印插件
    hiprint.init();

    var customPrintJson = {"panels":[{"index":0,"paperType":"A4","height":297,"width":210,"paperHeader":43.5,"paperFooter":801,"printElements":[{"options":{"left":124.5,"top":61.5,"height":15,"width":207,"field":"naOrg","testData":"古美社区卫生服务中心","fontSize":14.25,"fontWeight":"bold","textAlign":"center","hideTitle":true},"printElementType":{"title":"文本","type":"text"}},{"options":{"left":253.5,"top":87,"height":12,"width":9},"printElementType":{"title":"竖线","type":"vline"}},{"options":{"left":261,"top":87,"height":12,"width":36,"field":"fgReplan","testData":"普考","textAlign":"center","hideTitle":true},"printElementType":{"title":"文本","type":"text"}},{"options":{"left":201,"top":87,"height":12,"width":46.5,"field":"naArea","testData":"考场1","textAlign":"center","hideTitle":true},"printElementType":{"title":"文本","type":"text"}},{"options":{"left":303,"top":87,"height":12,"width":9},"printElementType":{"title":"竖线","type":"vline"}},{"options":{"left":196.5,"top":87,"height":12,"width":9},"printElementType":{"title":"竖线","type":"vline"}},{"options":{"left":310.5,"top":87,"height":12,"width":60,"field":"planDay","testData":"2019-07-12","hideTitle":true},"printElementType":{"title":"文本","type":"text"}},{"options":{"left":70.5,"top":87,"height":12,"width":120,"field":"naPlan","testData":"2019内科期末测试","textAlign":"right","hideTitle":true},"printElementType":{"title":"文本","type":"text"}},{"options":{"left":405,"top":87,"height":12,"width":80,"field":"studentQueue","testData":"张三 08","hideTitle":true},"printElementType":{"title":"文本","type":"text"}},{"options":{"left":19.5,"top":118.5,"height":38,"width":487.5,"field":"table","columns":[[{"title":"考站","field":"naStation","width":105.56425437062937,"colspan":1,"rowspan":1,"textAlign":"center"},{"title":"房间号","field":"naRoom","width":90.31446372377624,"colspan":1,"rowspan":1,"textAlign":"center"},{"title":"科目","field":"idPaperText","width":191.7786306818182,"colspan":1,"rowspan":1,"textAlign":"center"},{"title":"时间","field":"planTime","width":99.84265122377619,"colspan":1,"rowspan":1,"textAlign":"center"}]]},"printElementType":{"title":"表格","type":"tableCustom"}}],"paperNumberLeft":565,"paperNumberTop":819}]}
    var printTemplate = new hiprint.PrintTemplate({ template: customPrintJson });

    var reprintData = {};

    table.on('row(registerTableFilter)', function(obj) {
        reprintData.naOrg = currentNaOrg;
        reprintData.naPlan = currentNaPlan;
        reprintData.naArea = currentNaArea;
        reprintData.fgReplan = currentFgReplan;
        reprintData.planDay = currentPlanDay;
        reprintData.studentQueue = obj.data.realName + ' ' + obj.data.noReg;
        reprintData.userId = obj.data.userId;
        //标注选中样式
        obj.tr.addClass('layui-bg-blue').siblings().removeClass('layui-bg-blue');
    });

    $("#reprint").on('click', function () {
        if (!reprintData) {
            layer.msg("请先选中您的登记信息");
            return;
        }
        commonPrint(reprintData, reprintData.userId);
    });


    function commonPrint(printData, userId) {
        var bizData = {
            idPlan: idPlan,
            idArea: idArea,
            timeSection: timeSection,
            idUserStudent: userId
        }
        $.ajax({
            url: basePath + '/pf/r/aio/student/exec/queue',
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
                    var sucList = data.data
                    for (var i = 0; i < sucList.length; i++) {
                        sucList[i].planTime = sucList[i].planBegin + '~' + sucList[i].planEnd;
                    }
                    printData.table = sucList;
                    printTemplate.print(printData);
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



var translate = new transition({
    $main: $('#pt-main'),
    loop: true,
    callback: function (index) {

    }
});

$('#back').click(function () {
    translate.nextPage(2);
});

$('#manage').click(function () {
    translate.nextPage(1);
});



