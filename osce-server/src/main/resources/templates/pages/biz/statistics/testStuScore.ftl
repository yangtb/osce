<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>学生成绩</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath!}';
        var idPlan = '${idPlan!}';
    </script>
</head>

<body>

<div class="wrapper-content">
    <div class="layui-card">
        <div class="layui-card-header">学生成绩</div>
        <div class="layui-card-body">
            <table id="testScoreTable" lay-filter="testScoreTableFilter">
            </table>
        </div>
    </div>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/statistics/testStuScoreController.js"></script>

<script type="text/html" id="testBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail"><i class="iconfont icon-detail"></i> 详情</a>
</script>


</body>

</html>