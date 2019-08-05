<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>学生成绩详情-理论试题</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath!}';
        var idExec = '${idExec!}';
    </script>
</head>

<body>
<div style="padding-left: 10px; padding-right: 10px;">
    <table id="stuScore1Table" lay-filter="stuScore1TableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/statistics/testStuScoreDetail1Controller.js"></script>


</body>

</html>