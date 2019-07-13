<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
        <span class="test-num">考站1-1</span>
        <p class="test-room">${naRoom!}</p>
        <div class="header-main">
            <p class='text'>古美社区卫生服务中心</p>
            <p class='details'>2019内科期末测试 | 考场1 | 普考 | 2019-05-1 08:30~11:30</p>
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
                    <span class="text-info">内科</span>
                </p>
                <p class="text-item">
                    <span class="text-name">类型</span>
                    <span class="text-info">技能操作</span>
                </p>
                <p class="text-item text-item-last">
                    <span class="text-name">试题</span>
                    <span class="text-info">腹部穿刺训练</span>
                </p>
            </div>
            <div class='test-desc'>
                腹腔穿刺术是通过穿刺针或导管直接从腹前壁刺入腹膜腔抽取腹腔积液，用以协助诊断和治疗疾病的一项技术。该技术是确定有无腹水及鉴别腹水性质的简易方法，分为诊断性腹腔穿刺和治疗性腹腔穿刺。
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
                        <span class="tester">学员</span>
                        <span class="tester-name">张三</span>
                        <span class="tester-num">08</span>
                        <button type="button" class="layui-btn layui-btn-normal identify-btn">身份认证</button>
                    </div>
                    <div class="details-info">
                        <p class="tel-text"><span class="text-header">联系方式：</span><span class="tel">18279188319</span></p>
                        <p class="id-text"><span class="text-header">身份证：</span><span class="cardid">622727199501184116</span></p>
                        <p class="test-time"><span class="text-header">考时：</span><span class="time">9:15-9:25</span></p>
                        <p class="status"><span class="text-header">状态：</span><span class="status-text">叫号待认证</span></p>
                        <div class="left-time">
                            <p class='left-text'>剩余时间</p>
                            <p class="left-second">218 s</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-content">
                <p class='await-info'>
                    <span class="await-text">等待学员</span>
                </p>
                <div class="await-box">
                    <p class="await-num">等待人数 12</p>
                    <div class="await-item actived">
                        <p class="awaiter-left">
                            <span class="awaiter-name">李四</span>
                            <span class="awaiter-num">09</span>
                            <span class="awaiter-tel">18279183319</span>
                        </p>
                        <p class="awaiter-right">
                            <span class="awaiter-id">622727199501184116</span>
                            <span class="join-time">08:30:58</span>
                        </p>
                    </div>
                    <div class="await-item">
                        <p class="awaiter-left">
                            <span class="awaiter-name">李四</span>
                            <span class="awaiter-num">09</span>
                            <span class="awaiter-tel">18279183319</span>
                        </p>
                        <p class="awaiter-right">
                            <span class="awaiter-id">622727199501184116</span>
                            <span class="join-time">08:30:58</span>
                        </p>
                    </div>
                    <div class="await-item">
                        <p class="awaiter-left">
                            <span class="awaiter-name">李四</span>
                            <span class="awaiter-num">09</span>
                            <span class="awaiter-tel">18279183319</span>
                        </p>
                        <p class="awaiter-right">
                            <span class="awaiter-id">622727199501184116</span>
                            <span class="join-time">08:30:58</span>
                        </p>
                    </div>
                    <div class="await-item">
                        <p class="awaiter-left">
                            <span class="awaiter-name">李四</span>
                            <span class="awaiter-num">09</span>
                            <span class="awaiter-tel">18279183319</span>
                        </p>
                        <p class="awaiter-right">
                            <span class="awaiter-id">622727199501184116</span>
                            <span class="join-time">08:30:58</span>
                        </p>
                    </div>
                    <div class="await-item">
                        <p class="awaiter-left">
                            <span class="awaiter-name">李四</span>
                            <span class="awaiter-num">09</span>
                            <span class="awaiter-tel">18279183319</span>
                        </p>
                        <p class="awaiter-right">
                            <span class="awaiter-id">622727199501184116</span>
                            <span class="join-time">08:30:58</span>
                        </p>
                    </div>
                    <div class="await-item">
                        <p class="awaiter-left">
                            <span class="awaiter-name">李四</span>
                            <span class="awaiter-num">09</span>
                            <span class="awaiter-tel">18279183319</span>
                        </p>
                        <p class="awaiter-right">
                            <span class="awaiter-id">622727199501184116</span>
                            <span class="join-time">08:30:58</span>
                        </p>
                    </div>

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