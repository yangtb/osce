<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>字典管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
    </script>
    <style>
        table{padding-bottom: 2px;}
    </style>

</head>

<body class="wrapper-content">
<div>
    <div class="layui-col-xs3" style="padding-right: 5px;">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-btn-group">
                    <button type="button" class="layui-btn layui-btn-sm" id="addDic">
                        <i class="iconfont icon-add"></i>
                    </button>
                    <button type="button" class="layui-btn layui-btn-sm" id="editDic">
                        <i class="iconfont icon-edit"></i>
                    </button>
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="delDic">
                        <i class="layui-icon layui-icon-delete"></i>
                    </button>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="dicName" placeholder="字典名称" class="layui-input"
                           style="height:30px; width: 94px;">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="dicSearchFilter">
                        <i class="iconfont icon-query"></i>
                    </button>
                </div>
            </div>
        </form>

        <table id="dicTable" lay-filter="dicTableFilter">
        </table>
    </div>

    <div class="layui-col-xs9">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-btn-group">
                    <button type="button" class="layui-btn layui-btn-sm" id="addEnum">
                        <i class="iconfont icon-add"></i> 增加
                    </button>
                    <button type="button" class="layui-btn layui-btn-sm" id="editEnum">
                        <i class="iconfont icon-edit"></i> 编辑
                    </button>
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="delEnum">
                        <i class="layui-icon layui-icon-delete"></i>删除
                    </button>
                    <button type="button" class="layui-btn layui-btn-sm" id="refreshCache">
                        <i class="iconfont icon-refresh"></i> 刷新缓存
                    </button>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="enumName" placeholder="请输入枚举名称" class="layui-input"
                           style="height:30px;">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="enumSearchFilter">
                        <i class="iconfont icon-query"></i> 查询
                    </button>
                    <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                        <i class="iconfont icon-reset"></i> 重新填写
                    </button>
                </div>
            </div>
        </form>

        <table id="enumTable" lay-filter="enumTableFilter">
        </table>
    </div>
</div>


<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/system/dic/dicController.js"></script>

<script type="text/html" id="dicBar">
    <a class="layui-btn layui-btn-xs" lay-event="detail"><i class="iconfont icon-detail"></i> 详情</a>
</script>

<script type="text/html" id="enumBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
</script>

</body>

</html>