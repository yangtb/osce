<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>平台会话超时</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <style type="text/css">
        .icon {
            width: 1em; height: 1em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
        }
    </style>
</head>

<body class="childrenBody">
<div style="text-align: center;padding:2% 0;">
    <i class="iconfont icon-timeout" style="line-height:12rem; font-size:12rem; color: #393D50;"></i>
    <p style="font-size: 20px; color: #000000;">登录过期</p>
    <p style="font-size: 20px; font-weight: 300; color: #999;">温馨提示：对不起，您的账号已登录超时，请进行如下操作：</p>
    <p style="font-size: 20px; color: #108ee9; margin-top: 1rem;cursor: pointer;"
       onclick="goLogin();">重新登录</p>
</div>
</body>
<script type="text/javascript">
    var basePath = '${basePath}';
    function goLogin(){
        if(window!=top){
            top.location.href = basePath + "/login";
        }else{
            window.location.href = basePath + "/login";
        }
    }
</script>
</html>