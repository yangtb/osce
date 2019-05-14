<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>维修记录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <style>

    </style>
</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
    var idDeviceCase = '${idDeviceCase!}';
</script>

<body>

<div style="margin: 0px 5px 0px 5px">
    <table id="repairTable" lay-filter="repairTableFilter">
    </table>
    <form class="layui-form" style="text-align: right">
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-sm" id="add">
                <i class="iconfont icon-add"></i> 增加
            </button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="del">
                <i class="layui-icon layui-icon-delete"></i>删除
            </button>
            <button type="button" class="layui-btn layui-btn-sm" id="save">
                <i class="iconfont icon-save-copy"></i> 保存
            </button>
        </div>
    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/res/model/repairController.js"></script>


</body>
</html>