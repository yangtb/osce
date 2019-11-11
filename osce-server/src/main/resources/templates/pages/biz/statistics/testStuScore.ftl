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
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">

    <script>
        var basePath = '${basePath!}';
        var idPlan = '${idPlan!}';
    </script>
</head>

<body>

<div class="layui-card layadmin-header" style="display: block;">
    <div class="layui-breadcrumb-my-title">
        <span>学生成绩</span>
    </div>
    <div class="layui-breadcrumb-my">
        <a lay-href="${basePath!}/main">主页</a><span lay-separator="">/</span>
        <a>成绩管理</a>
        <span lay-separator="">/</span>
        <a lay-href="${basePath!}/pf/p/statistics/test/page">实训记录</a>
        <span lay-separator="">/</span>
        <a><cite>学生成绩</cite></a>
    </div>
</div>

<div style="padding: 0px 10px 0px 10px">
    <table id="testScoreTable" lay-filter="testScoreTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/statistics/testStuScoreController.js"></script>

<script type="text/html" id="testBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail"><i class="iconfont icon-detail"></i> 详情</a>
</script>


</body>

</html>