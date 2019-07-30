<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>菜单管理</title>
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

<div class="wrapper-content">
    <form class="layui-form">
        <div class="layui-inline">
            <div class="layui-btn-group">
                <button type="button" class="layui-btn" id="add">
                    <i class="layui-icon layui-icon-add-1"></i>增加
                </button>
                <button type="button" class="layui-btn" id="edit">
                    <i class="layui-icon layui-icon-edit"></i>编辑
                </button>
                <button type="button" class="layui-btn" id="bach-invalid">
                    <i class="iconfont icon-batch-reduce"></i> 批量停用
                </button>
            </div>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="queryType">
                    <option value="1" selected>菜单名称</option>
                    <option value="2">父编码</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <input type="text" name="keyword" id="keyword" class="layui-input" autocomplete="off"
                       placeholder="请输入关键字">
            </div>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="menuLevel">
                    <option value="">请选择</option>
                    <option value="1">一级</option>
                    <option value="2">二级</option>
                    <option value="3">三级</option>
                </select>
            </div>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="status">
                    <option value="enabled" selected>启用</option>
                    <option value="disabled">停用</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button id="menuQuery" type="button" class="layui-btn" lay-submit lay-filter="menuSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">
                    <i class="iconfont icon-reset"></i> 重新填写
                </button>
            </div>
        </div>
    </form>

    <table id="menuTable" lay-filter="menuTableFilter">
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/user/menu/menuController.js"></script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    {{#  if(d.status == 'enabled'){ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="stop"><i class="iconfont icon-stop"></i> 停用</a>
    {{#  } else { }}
    <a class="layui-btn  layui-btn-xs" lay-event="recover"><i class="iconfont icon-save"></i> 启用</a>
    {{#  } }}
</script>

<script type="text/html" id="levelTpl">
    {{#  if(d.level == 1){ }}
    <span class="label label-info">一级</span>
    {{#  } }}
    {{#  if(d.level == 2){ }}
    <span class="label label-warning">二级</span>
    {{#  } }}
    {{#  if(d.level == 3){ }}
    <span class="label label-danger">三级</span>
    {{#  } }}
</script>

<script type="text/html" id="functionTypeTpl">
    {{#  if(d.functionType == 1){ }}
    <span class="label label-info">菜单权限功能</span>
    {{#  } }}
    {{#  if(d.functionType == 2){ }}
    <span class="label label-warning">按钮、链接功能权限资源</span>
    {{#  } }}
    {{#  if(d.functionType == 3){ }}
    <span class="label label-danger">接口功能权限资源</span>
    {{#  } }}
</script>

<script type="text/html" id="iconTypeTpl">
    {{#  if(d.iconType == 0){ }}
    <span class="label label-info">无</span>
    {{#  } }}
    {{#  if(d.iconType == 1){ }}
    <span class="label label-info">iconfont矢量图标</span>
    {{#  } }}
    {{#  if(d.iconType == 2){ }}
    <span class="label label-warning">url图标</span>
    {{#  } }}
</script>

<script type="text/html" id="statusTpl">
    {{#  if(d.status == 'enabled'){ }}
    <span class="label label-info">有效</span>
    {{#  } else { }}
    <span class="label label-danger">无效</span>
    {{#  } }}
</script>

<script type="text/html" id="imgTpl">
    {{#  if(d.iconType != 0){ }}
        {{#  if(d.iconType == '1'){ }}
        <span><i class="iconfont {{d.iconSource}}"></i> {{d.iconSource}}</span>
        {{#  } else { }}
        <span>{{d.iconSource}}</span>
        {{#  } }}
    {{#  } }}
</script>

</body>

</html>