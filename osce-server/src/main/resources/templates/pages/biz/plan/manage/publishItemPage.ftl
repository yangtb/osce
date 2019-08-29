<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>发布清单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var idPlan = '${idPlan!}';
    </script>
</head>

<body>

<div style="margin-top: -10px;">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">学员</li>
            <li>SP</li>
            <li>考官</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <table id="studentTable" lay-filter="studentTableFilter">
                </table>
            </div>
            <div class="layui-tab-item">
                <table id="spTable" lay-filter="spTableFilter">
                </table>
            </div>
            <div class="layui-tab-item">
                <table id="assistantTable" lay-filter="assistantTableFilter">
                </table>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/publishItemController.js"></script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

</body>

</html>