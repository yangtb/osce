<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>理论考试首页</title>
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/biz/css/show/common.css">
    <link rel="stylesheet" href="${contextPath!}/biz/css/exec/exec-main.css">

    <script>
        var basePath = '${basePath!}';
        var idOrg = '${idOrg!}';
        var idRoom = '${idRoom!}';
    </script>
</head>

<body class="wrapper-content" style="overflow:-Scroll;overflow-x:hidden">

<div style="margin: 10px">
    <button type="button" class="layui-btn layui-btn-normal" id="auth">身份认证</button>


    <div class="layui-row layui-col-space15">

        <div class="layui-row layui-col-space15">

            <div class="layui-col-md12">
                <div class="layui-card card-border">
                    <div class="layui-card-header card-font" style="border-bottom: 1px solid #d2d2d2; font-weight: bold">
                        <span>
                            学员 <label style="color: #1E9FFF" id="name-queue"></label>
                        </span>
                    </div>
                    <div class="layui-card-body">
                        <div class="details-info">
                            <p class="tel-text">
                                <span class="text-header">联系方式：</span>
                                <span class="tel" id="currStudentPhoneNo"></span>
                            </p>
                            <p class="id-text">
                                <span class="text-header">身份证：</span>
                                <input class="cardid" id="idCard" autocomplete="off"
                                       style="color: #9a9a9a; border:0; text-align: left; width: 250px; height: 20px"/>
                            </p>
                            <p class="test-time">
                                <span class="text-header">考时：</span>
                                <span class="time" id="currStudentTime"></span>
                            </p>
                            <p class="status">
                                <span class="text-header">状态：</span>
                                <span class="status-text" id="currStudentStatus"></span>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

    <div class="layui-row layui-col-space15" id="testCard">

    </div>
</div>


<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/execute/execMainController.js"></script>

</body>
</html>