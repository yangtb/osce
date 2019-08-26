layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['layer', 'index', 'form', 'util'], function () {
    var $ = layui.$
        , layer = layui.layer
        , form = layui.form
        , util = layui.util;


    $(document).ready(function () {
        queryStuInfo(false);
    });

    function queryStuInfo(endFlag) {
        var bizData = {
            idExecQueue: idExecQueue
        }
        $.ajax({
            url: basePath + '/r/exec/stu/info',
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
                    fullPageHead(data.data, endFlag);
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }

    var idExec;

    function fullPageHead(data, endFlag) {
        // console.log(data);
        idExec = data.idExec;
        $('#name-queue').text(data.realName + " " + data.noReg);
        $('#currStudentPhoneNo').text(data.phoneNo);
        $("#currStudentIdCard").text(data.idCard);
        $("#sdExecQueue").val(data.sdExecQueue);

        if (data.sdExecQueue == 4) {
            $("#done").css("display", "inline-block");
        }

        if (data.sdExecQueue == 5) {
            $("#answerResult").css("display", "block");
            $("#rightNum").text(data.rightNum);
            $("#errorNum").text(data.errorNum);
            $("#noDoneNum").text(data.noDoneNum);
            $("#totalScore").text(data.totalScore + '分');
        }

        queryItemInfo(data.idPaper, endFlag);
        // 倒计时
        if (data.sdExecQueue == 4) {
            $("#countdown").css("display", "block");
            countdown(data);
        }
    }

    function queryItemInfo(idPaper, endFlag) {
        /*var msg = endFlag ? '加载考试结果中' : '加载中';
        layer.msg(msg, {
            icon: 16
            , shade: 0.01
        });*/
        var bizData = {
            idExecQueue: idExecQueue,
            idPaper: idPaper
        }
        $.ajax({
            url: basePath + '/r/exec/item/info',
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
                    fullItem(data.data);
                    return false;
                }
            },
            error: function () {
                layer.closeAll('loading');
                layer.msg("网络异常");
                return false;
            }
        });
    }

    /**
     * 题目
     * @param data
     */
    var itemData;

    function fullItem(data) {
        itemData = data;
        // console.log(data);
        $("#items").empty();
        if (data.length == 0) {
            return;
        }
        $("#items").append(buildItemTable(data));
        $("#currentRownum").text(1);
        $("#totalItem").text(data.length);
        testEventListener();
        // 默认加载第一题
        changeItem(1);
    }

    function buildItemTable(data) {
        var html = '', sdExecQueue = $('#sdExecQueue').val();
        $.each(data, function (index, content) {
            var itemStyle = content.answer ? 'item-right' : '';
            if (sdExecQueue == 5) {
                if (!content.scoreResult) {
                    itemStyle = '';
                }
                if (content.scoreResult == 0) {
                    itemStyle = 'item-error';
                }
                if (content.scoreResult > 0) {
                    itemStyle = 'item-right';
                }
            }
            console.log("itemStyle=====" + itemStyle)
            var rownum = index + 1;
            if (rownum == 1 || (rownum % 20 == 1)) {
                html += '<tr>';
            }
            html += ' <td id="item-' + rownum + '" data-index="' + rownum + '" class="item-question ' + itemStyle + '">' + rownum + '</td>';
            if (rownum / 20 == 0) {
                html += '</tr>';
            }
        });
        return html;
    }

    function testEventListener() {
        var itemQuestions = document.querySelectorAll(".item-question");
        for (var i = 0; i < itemQuestions.length; i++) {
            itemQuestions[i].addEventListener('click', function () {
                changeItem(this.getAttribute('data-index'))
            });
            itemQuestions[i].addEventListener('mouseover', function () {
                $(this).addClass("mouseOver").siblings().removeClass("mouseOver");
            });
            itemQuestions[i].addEventListener('mouseout', function () {
                $(this).removeClass("mouseOver");
            });
        }
    }

    /**
     * 切换题目
     */
    function changeItem(rownum) {
        if (!itemData) {
            return;
        }

        console.log(itemData[rownum - 1]);

        $("table tbody tr").find('td').each(function () {
            if ($(this).html() == rownum) {
                $(this).addClass("select");
            } else {
                $(this).removeClass("select");
            }
        })

        var currentItemData = itemData[rownum - 1];
        $("#idItem").val(currentItemData.idItem)
        $("#currentRownum").text(rownum);
        $("#mainItem").text(currentItemData.mainItem);
        $("#idWeItem").val(currentItemData.idWeItem);

        var myAnswer = currentItemData.answer;

        $("#options").empty();
        if (currentItemData.itemOptions) {
            var optionHtml = '';
            $.each(currentItemData.itemOptions, function (index, content) {
                var optionLabel = content.cdIteStr + '.' + content.naOption,
                    myCheck = myAnswer == content.cdIteStr ? 'checked' : '';
                optionHtml += '<div class="layui-col-md12">\n' +
                    '              <input type="radio" name="itemOption" lay-filter="radioFilter" value="' + content.cdIteStr + '" title="' + optionLabel + '" ' + myCheck + '>\n' +
                    '          </div>';
            });
            $("#options").append(optionHtml);
            form.render();
        }
        // 按钮控制
        buttonControl(rownum);
    }

    /**
     * 按钮控制
     * @param rownum
     */
    function buttonControl(rownum) {
        // 上一步按钮
        if (rownum == 1) {
            $("#pre").addClass("layui-btn-disabled");
            $('#pre').attr("disabled", "true");
        } else {
            $("#pre").removeClass("layui-btn-disabled");
            $('#pre').removeAttr("disabled", "true");
        }
        // 下一步按钮
        if (rownum == $('#totalItem').text()) {
            $("#next").addClass("layui-btn-disabled");
            $('#next').attr("disabled", "true");
        } else {
            $("#next").removeClass("layui-btn-disabled");
            $('#next').removeAttr("disabled", "true");
        }
    }

    $("#pre").on('click', function () {
        var rownum = parseInt($("#currentRownum").text());
        //saveItem(rownum);
        changeItem(rownum - 1);
    });

    $("#next").on('click', function () {
        var rownum = parseInt($("#currentRownum").text());
        //saveItem(rownum);
        changeItem(rownum + 1);
    });

    form.on('radio(radioFilter)', function (data) {
        if ($("#sdExecQueue").val() == 4) {
            var rownum = parseInt($("#currentRownum").text());
            saveItem(rownum);
        }
    });


    function saveItem(rownum) {
        var cdIteStr = $('input[name="itemOption"]:checked ').val();
        console.log("当前题目选项cdIteStr====：" + cdIteStr)
        if (!cdIteStr) {
            return;
        }
        var bizData = {
            idWeItem: $("#idWeItem").val(),
            idExec: idExec,
            idItem: $("#idItem").val(),
            cdIteStr: cdIteStr
        }
        $.ajax({
            url: basePath + '/r/exec/item/save',
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
                    itemData[rownum - 1].idWeItem = data.data;
                    itemData[rownum - 1].answer = cdIteStr;
                    $('#item-' + rownum).addClass('item-right');
                    return false;
                }
            },
            error: function () {
                layer.closeAll('loading');
                layer.msg("网络异常");
                return false;
            }
        });
    }

    $("#done").on('click', function () {
        layer.confirm('确定要交卷么？交卷后不可修改', {
            title: '提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3,
        }, function (index) {
            layer.close(index);
            endTest(idExecQueue);
        });
    });

    function endTest(idExecQueue) {
        var bizData = {
            parSdExecQueue: 5,
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
                    layer.msg("交卷成功");
                    queryStuInfo(true);
                    $("#sdExecQueue").val(5);
                    $("#done").css("display", "none");
                    $("#countdown").css("display", "none");
                    return false;
                }
            },
            error: function () {
                layer.msg("网络异常");
                return false;
            }
        });
    }


    /**
     * 倒计时
     * @param data
     */
    function countdown(data) {
        var serverTimeStr = data.nowTime;
        var serverTimeArr = serverTimeStr.split(" ");
        var ymd = serverTimeArr[0];
        var hms = serverTimeArr[1];

        var arrYmd = ymd.split("-"),
            arrHms = hms.split(":");
        var actBegin = data.countdownTime, arrActBegin;
        if (actBegin) {
            arrActBegin = actBegin.split(":");
            var endTime = new Date(arrYmd[0], arrYmd[1] - 1, arrYmd[2], arrActBegin[0], arrActBegin[1], arrActBegin[2])
                , serverTime = new Date(arrYmd[0], arrYmd[1] - 1, arrYmd[2], arrHms[0], arrHms[1], arrHms[2]);

            util.countdown(endTime, serverTime, function (date, serverTime, timer) {
                var str = '';
                if (date[0] != 0) {
                    str += date[0] + '天';
                }
                if (date[1] != 0) {
                    str += date[1] + '时';
                }
                if (date[2] != 0) {
                    str += date[2] + '分';
                }
                if (date[3] != 0) {
                    str += date[3] + '秒';
                }
                $('#countdown').html(str);
            });
        }
    }

});





