<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>病例库管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">

    <style>

    </style>
</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
</script>

<body>

<div class="layui-card layadmin-header" style="display: block;">
    <div class="layui-breadcrumb-my-title">
        <span>SP病例库</span>
    </div>
    <div class="layui-breadcrumb-my">
        <a lay-href="${basePath!}/main">主页</a><span lay-separator="">/</span>
        <a>实训配置</a>
        <span lay-separator="">/</span>
        <a><cite>SP病例库</cite></a>
    </div>
</div>

<div class="wrapper-content">

    <form class="layui-form">
        <div class="layui-inline">
            <div class="layui-btn-group">
                <button type="button" class="layui-btn layui-btn-sm" id="add">
                    <i class="layui-icon layui-icon-add-1"></i>增加
                </button>
                <button type="button" class="layui-btn layui-btn-sm" id="edit">
                    <i class="layui-icon layui-icon-edit"></i>编辑
                </button>
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="del">
                    <i class="iconfont icon-batch-del"></i> 删除
                </button>
            </div>
        </div>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="MiniCEX">
                <i class="layui-icon">&#xe642;</i>Mini-CEX
            </button>
        </div>

        <div>
            <button id="MiniCEXHidden" type="button" class="layui-btn layui-btn-normal"
                    lay-href="" style="display: none">Mini-CEX
            </button>
        </div>

    </form>

    <table id="caseTable" lay-filter="caseTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/case/caseController.js"></script>

<script type="text/html" id="fgActiveTpl">
    <input type="checkbox" name="fgActive" value="{{d.idCase}}"
           lay-skin="switch" lay-text="NO|OFF" lay-filter="fgActiveCheckFilter" {{ d.fgActive== '1' ? 'checked' : '' }}>
</script>


<script type="text/html" id="caseBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
</script>

</body>
</html>