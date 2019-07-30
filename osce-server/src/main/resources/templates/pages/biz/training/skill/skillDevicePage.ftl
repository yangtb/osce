<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>站点配置</title>
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
    var idSkillCase = '${idSkillCase!}';
</script>

<body>

<div>

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
                    <i class="iconfont icon-batch-del"></i> 删除
                </button>
            </div>
            <div class="layui-input-inline">
                <input type="text" name="naDevice" class="layui-input btn-sm-my" autocomplete="off"
                       placeholder="请输入病例名称">
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="skillDeviceSearchFilter">
                        <i class="iconfont icon-query"></i> 查询
                    </button>
                    <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                        <i class="iconfont icon-reset"></i> 重置
                    </button>
                </div>
            </div>
        </div>
    </form>

    <table id="skillDeviceTable" lay-filter="skillDeviceTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/skill/skillDeviceController.js"></script>

<script type="text/html" id="skillDeviceBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
</script>


</body>
</html>