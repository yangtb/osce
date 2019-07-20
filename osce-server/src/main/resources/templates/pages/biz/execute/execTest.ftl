<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <#--<link href="${basePath!}/biz/img/logo/favicon.ico" rel="shortcut icon">-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>理论考试</title>
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/biz/css/show/common.css">
    <link rel="stylesheet" href="${contextPath!}/biz/css/exec/exec-test.css">

    <script>
        var basePath = '${basePath!}';
        var idExecQueue = '${idExecQueue!}';
    </script>
</head>

<body class="wrapper-content" style="overflow:-Scroll;overflow-x:hidden">

<div style="margin: 10px">

    <div class="layui-card card-border">
        <input id="sdExecQueue" hidden>
        <div class="layui-card-header card-font"
             style="border-bottom: 1px solid #d2d2d2; font-weight: bold; text-align: center; height: 50px;">
            <span style="float: left; font-size: 25px;">
                学员 <label style="color: #1E9FFF" id="name-queue"></label>
            </span>
            <span><i id="countdown" style="display: none"></i></span>
            <span id="answerResult" style="float: right; font-size: 16px; display: none">
                <label style="color: #5FB878">正确（<span id="rightNum"></span>）</label>
                <label style="color: #FF5722">错误（<span id="errorNum"></span>）</label>
                <label style="color: #2F4056; padding-right: 30px">未做（<span id="noDoneNum"></span>）</label>
                <label class="card-font" style="border-bottom:2px double #000000; text-align: center" id="totalScore"></label>
            </span>
        </div>
        <div class="layui-card-body">
            <div class="details-info">
                <span class="tel-text">
                    <label class="text-header">联系方式：</label>
                    <label class="tel" id="currStudentPhoneNo"></label>
                </span>
                <span class="tel-text" style="padding-left: 80px;">
                    <label class="text-header">身份证：</label>
                    <label class="cardid" id="currStudentIdCard"></label>
                </span>
            </div>

            <table class="layui-table" lay-size="sm" style="text-align: center;">
                <tbody id="items">
                    <#--<tr>
                        <td class="item-question item-right">1</td>
                        <td class="item-question item-error">2</td>
                        <td class="item-question item-not-done">3</td>
                    </tr>-->
                </tbody>
            </table>
        </div>
    </div>

    <div class="layui-card card-border">
        <div class="layui-card-body card-font">
            <div class="layui-row" style="color: #0C0C0C">
                <input id="idWeItem" hidden>
                <input id="idItem" hidden>
                <div class="layui-col-md12" style="margin-bottom: 10px;">
                    <div>第<span id="currentRownum"></span>/<span id="totalItem"></span>题</div>
                </div>
                <div class="layui-col-md12 space-20">
                    <div id="mainItem"></div>
                </div>
                <form class="layui-form" id="options">
                    <#--题目选项-->
                </form>
                <div class="layui-col-md12" style="padding-left: 100px; padding-top: 10px; padding-bottom: 10px">
                    <button type="button" class="layui-btn" id="pre">
                        <i class="layui-icon">&#xe603;</i>上一题
                    </button>
                    <button type="button" class="layui-btn layui-btn-normal" id="next">
                        <i class="layui-icon">&#xe602;</i>下一题
                    </button>
                    <button type="button" class="layui-btn" id="done" style="display: none">
                        <i class="layui-icon">&#x1005;</i>交卷
                    </button>
                </div>
            </div>

        </div>
    </div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/execute/execTestController.js"></script>

</body>
</html>