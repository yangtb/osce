<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>${websiteName!}</title>
    <meta name="keywords" content="登录">
    <meta name="description" content="登录">
    <link rel="stylesheet" type="text/css" href="${basePath!}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${basePath!}/biz/css/style.css">
    <link rel="stylesheet" type="text/css" href="${basePath!}/biz/css/login.css">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>
    <script>
        var basePath = '${basePath!}';
        var publicKey = '${publicKey!}';
    </script>
</head>
<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <#--<div class="logopanel m-b">
                    <h1>${websiteName!}</h1>
                </div>-->
               <#-- <div class="m-b"></div>
                <h4>欢迎使用 <strong>${websiteName!}</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                </ul>
                <strong>还没有账号？ <a href="#" id="register">立即注册&raquo;</a></strong>-->
            </div>
        </div>
        <div class="col-sm-5">
            <div id="login-switch" class="login-switch">
                <div class="login-title"><div class="login-hidden" id="scanLogin">扫码<br>登录</div><div id="passwordLogin">密码<br>登录</div></div>
            </div>
            <div id="passwordLoginBody">
                <form method="post" id="loginForm" action="${contextPath!}/pfprodlogin" onSubmit="return submitForm()">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到${websiteName!}</p>
                    <input type="text" id="username" name="username" maxlength="11" class="form-control uname" placeholder="用户名" autocomplete="off"/>
                    <input type="hidden" name="password" id="encryptPassword" />
                    <input type="password" id="clearPassword" maxlength="16" class="form-control pword m-b" placeholder="密码" autocomplete="off" />
                    <input type="text" id="vcode" name="vcode" maxlength="4"
                           style="width:50%;float: left;margin-top: 0px;color: #333" class="form-control" autocomplete="off" placeholder="验证码" />
                    <image id="vcodeImage" style="width: 50%;height: 34px" src="${basePath!}/login/verificationCode" title="点击图片更换一张 "/>
                    <a id="loginwarning" style="margin-top: 5px;color: red">${errorMsg!}</a>
                    <input id="doLoginBtn" type="submit" data-loading-text="登录中..." class="btn btn-success btn-block" value="登录">
                </form>
            </div>
            <div class="login-hidden" id="scanLoginBody">
                <form method="post" id="scanForm" action="">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">app扫一扫，快速登录</p>
                    <div class="login-scancode" id="scanCode"></div>
                    <div id="scanCodeExpire" class="login-input login-hidden">
                        二维码失效
                        <br>
                        <input class="btn btn-primary btn-xs" onclick="retryScanCode()" value="刷新">
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<div  style="float: bottom; padding-top: 160px; text-align: center ">
    <div>
        <#-- 技术服务电话：XXXX-XXXXXXX-->
        <br>
        ${websiteCopyright!}.
    </div>
</div>

<script src="${contextPath!}/common/js/jquery.min.js"></script>
<script src="${contextPath!}/common/js/jquery.qrcode.min.js"></script>
<script src="${contextPath!}/common/js/jquery.jcryption.3.1.0.js"></script>
<script src="${contextPath!}/biz/js/user/login/loginController.js"></script>
<script src="${contextPath!}/bootstrap/js/bootstrap.min.js"></script>
<script src="${contextPath!}/biz/js/user/login/login.js"></script>

</body>
</html>
