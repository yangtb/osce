<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>教学模型管理</title>
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
                <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="record">
                    <i class="layui-icon layui-icon-table"></i>使用记录
                </button>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="modelSearchFilter">
                    <i class="iconfont icon-refresh"></i> 刷新
                </button>
            </div>
        </div>
    </form>

    <table id="modelTable" lay-filter="modelTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/res/model/modelController.js"></script>

<script type="text/html" id="fgConsumablesTpl">
    {{#  if(d.fgConsumables == '1'){ }}
    消耗品
    {{#  } else { }}
    非耗品
    {{#  } }}
</script>


<script type="text/html" id="modelBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
</script>

</body>
</html>