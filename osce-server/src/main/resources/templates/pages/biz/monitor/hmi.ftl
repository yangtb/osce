<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>考场监控画面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <style>
        .qr-qrea {
            flex: 1;
            box-sizing: border-box;
            display: flex;
            align-items: flex-start;
            padding-top: 30px;
            /*padding-bottom: 30px;*/
            justify-content: center;
            height: 250px;
        }
        .qr-left {
            margin-top: 20px;
        }
        .qr-icon {
            display: block;
            width: 160px;
            height: 160px;
            margin-left: 20px;
        }
        .scan-icon {
            display: block;
            width: 65px;
            height: 83px;
            margin: 0 auto;
            margin-top: 20px;
        }

    </style>
    <script>
        var basePath = '${basePath!}';
        var idInsStation = ${idInsStation!};
    </script>
</head>

<body style="overflow-x: hidden">

<div style="margin: 10px;">

    <div class="layui-row">
        <div class="layui-col-md8" style="padding-right: 5px;">
            <div class="layui-row">
                <div id="monitorDevice">
                    <blockquote class="layui-elem-quote">该站点暂无监控设备</blockquote>

                </div>

                <div class="layui-col-md12">
                    <div class="qr-qrea">
                        <div class='qr-left'>
                            <p>考官扫码</p>
                            <p>进入站点管理端</p>
                            <img src="${basePath}/biz/img/show/icon.png" alt="扫一扫" class='scan-icon'>
                        </div>
                        <div id="qrcode" class="qr-icon">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-row">
                <form class="layui-form">
                    <div class="layui-input-inline" style="width: 300px;">
                        <select id="idScoreSheet">
                        </select>
                    </div>
                </form>
                <table class="layui-hide" id="sheetTable" lay-filter="sheetTableFilter"></table>

                </table>
            </div>
        </div>
    </div>

</div>


<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/qrcode.min.js"></script>
<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/monitor/hmiController.js"></script>


</body>

</html>