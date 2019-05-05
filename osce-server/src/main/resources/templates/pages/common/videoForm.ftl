<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Ckyplayer组件</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="Author" content="larry"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <style>
        .video {
            display: block;
            width: 100%;
            height: auto;
        }
    </style>
    <script>
        var basePath = '${basePath}';
        var contextPath = '${contextPath}';
    </script>
</head>

<body class="body-my">
<div>
    <div class="layui-row">
                <div class="video" id="video"
                     data-url="${path}"
                     style="width: 100%;"></div>

    </div>
</div>
<script src="${contextPath}/layui/plugins/layui/layui.js"></script>

<script type="text/javascript">
    layui.config({
        base: basePath + '/layui/build/js/'
    }).extend({
        ckplayer: 'ckplayer/ckplayer'
    }).use(['jquery', 'ckplayer'], function () {
        var $ = layui.$,
                table = layui.table,
                form = layui.form,
                upload = layui.upload,
                common = layui.common,
                ckplayer = layui.ckplayer;

        var vUrl = $('#video').data('url'),
                videoObject = {
                    container: '#video',
                    loop: true,
                    autoplay: true,
                    video: [
                        [vUrl, 'video/mp4']
                    ]
                };
        var player = new ckplayer(videoObject);
    });
</script>
</body>

</html>