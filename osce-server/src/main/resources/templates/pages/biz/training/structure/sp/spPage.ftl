<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>SP管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">

</head>

<script>
    var basePath = '${basePath!}';
    var contextPath = '${contextPath!}';
    var showFlag = ${showFlag!};
</script>

<body>

<#if showFlag == 1>
    <div class="layui-card layadmin-header" style="display: block;">
        <div class="layui-breadcrumb-my-title">
            <span>SP管理</span>
        </div>
        <div class="layui-breadcrumb-my">
            <a lay-href="${basePath!}/main">主页</a><span lay-separator="">/</span>
            <a>实训配置</a>
            <span lay-separator="">/</span>
            <a>组织结构</a>
            <span lay-separator="">/</span>
            <a><cite>SP管理</cite></a>
        </div>
    </div>
</#if>

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
            <div class="layui-input-inline">
                <input type="text" name="keywords" class="layui-input btn-sm-my" autocomplete="off"
                       placeholder="请输入姓名或手机号">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="spSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                    <i class="iconfont icon-reset"></i> 重置
                </button>
            </div>
        </div>
    </form>

    <table id="spTable" lay-filter="spTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/ztree/js/jquery.ztree.core.js"></script>
<script src="${contextPath}/biz/js/biz/training/structure/sp/spController.js"></script>

<script type="text/html" id="enabledTpl">
    {{#  if(d.enabled == true){ }}
    <i class="iconfont icon-gou" style="color: #5FB878"></i>
    {{#  } else { }}
    <i class="iconfont icon-chacha" style="color: #FF5722"></i>
    {{#  } }}
</script>

<script type="text/html" id="sexTpl">
    {{#  if(d.sex == 1){ }}
    男
    {{#  } }}
    {{#  if(d.sex == 2){ }}
    <span style="color: #F581B1;">女</span>
    {{#  } }}
    {{#  if(d.sex == ''){ }}

    {{#  } }}
</script>

<script type="text/html" id="spBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
</script>

</body>
</html>