<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>考站壁挂式一体机-非理论</title>
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/css/screen.css">

    <style>
        p {
            margin: 0;
        }
    </style>

    <script>
        var basePath = '${basePath!}';
        var idRoom = '${idRoom!}';
        var naRoom = '${naRoom!}';
    </script>
</head>
<body style="overflow: hidden">
<div class='wrapper'>
    <header>
        <span class='cur-time' id="nowtime"></span>
        <p class='icon-box'>
            <a href="javascript:;" layadmin-event="fullscreen" id="full">
                <i class="layui-icon layui-icon-screen-full" style="color: #f8f8f8"></i>
            </a>
        </p>
        <p class='header-text' id="naOrg"></p>
        <div class='header-details' id="headInfo"></div>
    </header>
    <section class='main layui-anim' data-anim="layui-anim-fadein">
        <table border="0" cellspacing="0" cellpadding="0" id="student">

        </table>
    </section>
    <footer>版权所有 Copyright © Since2019 上海嘉奕医学科技有限公司</footer>
</div>


<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/show/bigScreenController.js"></script>

</body>
</html>