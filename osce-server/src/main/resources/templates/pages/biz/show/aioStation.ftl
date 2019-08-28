<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="${basePath!}/biz/img/logo/favicon.ico" rel="shortcut icon">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>考站壁挂式一体机-非理论</title>
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/biz/css/show/common.css">
    <link rel="stylesheet" href="${contextPath!}/biz/css/show/screen-2.css">
</head>
<script>
    var basePath = '${basePath!}';
    var idRoom = '${idRoom!}';
    var naRoom = '${naRoom!}';
</script>
<body>
<div class="wrapper">
    <header>
        <span class="test-num" id="naStation"></span>
        <p class="test-room">${naRoom!}</p>
        <div class="header-main">
            <input id="idOrg" hidden>
            <p class='text' id="naOrg"></p>
            <p class='details' id="headInfo"></p>
            <span class="cur-time" id="nowtime"></span>
            <span class="notice">
                 <a href="javascript:;" layadmin-event="fullscreen" id="full">
                     <i class="layui-icon layui-icon-screen-full" style="color: #f8f8f8"></i>
                 </a>
            </span>
        </div>
    </header>
    <section>
        <div class="main-left">
            <div class="header-text">
                <p class="text-item">
                    <span class="text-name">科目</span>
                    <span class="text-info" id="sdStationCaText"></span>
                </p>
                <p class="text-item">
                    <span class="text-name">类型</span>
                    <span class="text-info" id="sdSkillCa"></span>
                </p>
                <p class="text-item text-item-last">
                    <span class="text-name">试题</span>
                    <span class="text-info" id="naPaper"></span>
                </p>
            </div>
            <div class='test-desc' id="desPaper">
            </div>
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
        <p class='gap-line'></p>
        <!-- 右边 -->
        <div class="main-right">
            <div class="right-header">
                <p class="cur-text">
                    <span class="cur-tester">当前学员</span>
                </p>
                <div class="tester-info">
                    <div class="primary-info">
                        <span class="tester" >学员</span>
                        <span class="tester-name" id="currStudentName"></span>
                        <span class="tester-num" id="currStudentCd"></span>
                        <button type="button" class="layui-btn layui-btn-normal identify-btn" id="authentication">身份认证</button>
                    </div>
                    <div class="details-info">
                        <p class="tel-text"><span class="text-header">联系方式：</span>
                            <span class="tel" id="currStudentPhoneNo"></span>
                        </p>
                        <p class="id-text"><span class="text-header">身份证：</span>
                            <span class="cardid" id="currStudentIdCard"></span>
                        </p>
                        <p class="test-time"><span class="text-header">考时：</span><span class="time" id="currStudentTime"></span></p>
                        <p class="status"><span class="text-header">状态：</span><span class="status-text" id="currStudentStatus"></span></p>
                        <div class="left-time">
                            <p class='left-text'>剩余时间</p>
                            <p class="left-second"><span id="countdown"></span> s</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-content">
                <p class='await-info'>
                    <span class="await-text">等待学员</span>
                </p>
                <div class="await-box" id="rightWait">
                    <p class="await-num">等待人数 <span id="studentNum"></span></p>
                </div>
            </div>
        </div>
    </section>
    <footer>
        版权所有 Copyright © Since2019 上海嘉奕医学科技有限公司
    </footer>
</div>

<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/qrcode.min.js"></script>
<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/show/aioStationController.js"></script>

<script type="text/javascript">

</script>

</body>
</html>