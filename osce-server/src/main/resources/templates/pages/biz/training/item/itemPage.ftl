<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>题库管理</title>
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
</script>

<body>

<div class="wrapper-content">

    <form class="layui-form">
        <div class="layui-inline">
            <div class="layui-btn-group">
                <button type="button" class="layui-btn layui-btn-sm" id="add">
                    <i class="iconfont icon-add"></i> 增加
                </button>
                <button type="button" class="layui-btn layui-btn-sm" id="edit">
                    <i class="iconfont icon-edit"></i> 编辑
                </button>
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="del">
                    <i class="layui-icon layui-icon-delete"></i>删除
                </button>

            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="bachEdit">
                    <i class="iconfont icon-piliangbianji"></i> 批量编辑
                </button>
            </div>
        </div>
        <div class="layui-input-inline">
            <input type="text" name="naItemStore" class="layui-input btn-sm-my" autocomplete="off"
                   placeholder="请输入题集名称">
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="itemSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                    <i class="iconfont icon-reset"></i> 重置
                </button>
            </div>
        </div>
    </form>

    <table id="itemTable" lay-filter="itemTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/item/itemController.js"></script>

<script type="text/html" id="fgActiveTpl">
    <input type="checkbox" name="fgActive" value="{{d.idItemStore}}"
           lay-skin="switch" lay-text="NO|OFF" lay-filter="fgActiveCheckFilter" {{ d.fgActive== '1' ? 'checked' : '' }}>
</script>

<script type="text/html" id="itemBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
</script>

</body>
</html>