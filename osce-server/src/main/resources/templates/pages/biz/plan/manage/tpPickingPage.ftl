<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>分配学员</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
    </script>
</head>

<body>

<div>

    <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px;">
        <legend style="font-size: 14px; font-weight: bold">领料清单</legend>
    </fieldset>

    <table id="tpPickingTable" lay-filter="tpPickingTableFilter">
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/tpPickingController.js"></script>

</body>

</html>