<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="${basePath!}/biz/img/logo/favicon.ico" rel="shortcut icon">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>待考区一体机首页</title>
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/biz/css/show/await-test.css">
    <link rel="stylesheet" href="${contextPath!}/biz/css/show/common.css">
    <link rel="stylesheet" href="${contextPath!}/biz/css/show/await-test.css">
    <link rel="stylesheet" href="${contextPath!}/biz/css/show/test-manage.css">
    <link rel="stylesheet" type="text/css" href="${contextPath!}/biz/css/show/component.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath!}/biz/css/show/animations.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath!}/biz/css/print/hiprint.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath!}/biz/css/print/print-lock.css"/>

    <style>
        html, body {
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Lato', Calibri, Arial, sans-serif;
            color: #fff;
            background: #333;
            overflow: hidden;
        }

        .layui-layer-adminRight {
            top: 96px !important;
            bottom: 0;
            box-shadow: 1px 1px 10px rgba(0, 0, 0, .1);
            border-radius: 0;
            overflow: auto
        }
    </style>

    <script>
        var basePath = '${basePath!}';
    </script>
</head>

<body class="no-js" style="overflow: hidden">

<div id="pt-main" class="pt-perspective">
    <div class="pt-page pt-page-1">
        <div class="wrapper">
            <header>
                <div class="header-top-main">
                    <span class="time" id="nowtime"></span>
                    <p>
                        <a href="javascript:;" layadmin-event="fullscreen" id="full">
                            <i class="layui-icon layui-icon-screen-full" style="color: #f8f8f8"></i>
                        </a>
                    </p>
                </div>
                <div class="header-main">
                    <img src="${contextPath}/biz/img/show/header_icon.png" alt="header-icon" class="header-icon">
                    <div class="main-text">
                        <p class="name" id="naOrg"></p>
                        <p class="text-info" id="headInfo"></p>
                    </div>
                    <p class="more" id="more">
                        <span class="more-text">更多</span>
                        <img src="${contextPath}/biz/img/show/link_arrow.png" alt="更多" class="link-arrow">
                    </p>
                </div>
            </header>
            <div class="main">
                <a id="manage" class="manage-btn layui-btn layui-btn-primary">待考区管理</a>
            </div>
            <footer>
                版权所有 Copyright © Since2019 上海嘉奕医学科技有限公司
            </footer>
        </div>
    </div>
    <div class="pt-page pt-page-2">
        <div class="wrapper">
            <div class="header">
                <div class="header-top">
                    <span class="time" id="nowTime-2"></span>
                    <#--<p>
                        <img src="${contextPath}/biz/img/show/screen_icon.png" alt="icon" class="icon">
                        <span class="num">41ms</span>
                    </p>-->
                </div>
                <p class="name name-1" id="naOrg-1"></p>
                <p class="text-info text-info-1" id="headInfo-1"></p>
                <a id="back" class="arrow-back"></a>
            </div>
            <p class="manage-btns">
                <a href="javascript:;" class='register-btn actived register-stu' id='registerBtn' data-index="1">入场登记</a>
                <a href="javascript:;" class='registered-btn register-stu' id='registeredBtn' data-index="2">
                    已登记<span class='registered-num' id="registerNum"></span>/<span id="stuTotalNum"></span>
                </a>
            </p>
            <div class="await-register" id='awaitRegister'>
                <div class="tester-info">
                    <p class="face-area" id="headPhoto">
                        <img src="${contextPath}/biz/img/morentouxiang.png" class="face-img">
                    </p>
                    <div class="tester-main">
                        <#--<p class="self-tester" onclick="onAuthenticate()">身份识别</p>-->
                        <button id="identification" type="button"
                                class="layui-btn layui-btn-normal self-tester">身份识别</button>

                        <p class="name-area">
                            <span class='name-text'>姓名</span>
                            <span class="tester-name" id="realName"></span>
                        </p>
                        <p class="chat-info">
                            <span class="chat-type">联系方式</span>
                            <span class="tel" id="phoneNo"></span>
                        </p>
                        <p class="card-info">
                            <span class="card">身份证</span>
                            <span class="card-num">
                                <input id="idCard" autocomplete="off" style="color: #9a9a9a; border:0; text-align: right; width: 250px; height: 20px"/>
                            </span>
                            <#--<a href="javascript:;" class="identity-btn" onclick="onIdentity()"></a>-->
                        </p>
                    </div>
                </div>
                <p class="gap"></p>
                <div class="await-register-main">
                    <div class="main-header">
                        <p style="font-size: 16px" id="naOrg-2"></p>
                        <p class="main-text">
                            <span id="headInfo-2"></span>
                            <span class="main-user-info" id="student-queue"></span>
                        </p>
                    </div>
                    <div class="tester-info-wrapper" id="studentQueue">
                    </div>
                </div>
                <p class="notice-text"><span style="padding-left: 50px;"/>请学员按照考站安排计划，提前在考站前等候考试。</p>
                <p class="gap"></p>
                <p class="control-area">
                    <button id="printQueue" type="button" class="layui-btn layui-btn-normal print-btn">打印</button>
                </p>
            </div>
            <div class="has-register" id='hasRegister'>
                <div class="has-register-main">
                    <table id="registerTable" lay-filter="registerTableFilter"></table>

                    </table>

                    <div style="padding-right: 10px;">
                        <button id="reprint" type="button" class="layui-btn layui-btn-normal self-tester"
                                style="float: right;">补印</button>
                    </div>

                </div>

            </div>

            <footer>
                版权所有 Copyright © Since2019 上海嘉奕医学科技有限公司
            </footer>
        </div>
    </div>
</div>


<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/transition.js"></script>
<script src="${contextPath}/common/js/jquery.hiwprint.js"></script>
<script src="${contextPath}/common/js/hiprint.bundle.js"></script>
<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/show/aioMainController.js"></script>

</body>
</html>