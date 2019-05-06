<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>参数管理</title>
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
                    <i class="iconfont icon-add"></i> 增加
                </button>
                <button type="button" class="layui-btn" id="edit">
                    <i class="iconfont icon-edit"></i> 编辑
                </button>
                <button type="button" class="layui-btn layui-btn-danger" id="stop">
                    <i class="iconfont icon-stop"></i> 停用
                </button>
                <button type="button" class="layui-btn" id="refreshCache">
                    <i class="iconfont icon-refresh"></i> 刷新缓存
                </button>
            </div>
            <div class="layui-input-inline">
                <input type="text" name="paramName" class="layui-input"
                       placeholder="请输入参数名称">
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
                <button type="button" class="layui-btn" lay-submit lay-filter="paramSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">
                    <i class="iconfont icon-reset"></i> 重新填写
                </button>
            </div>
        </div>
    </form>

    <table id="paramTable" lay-filter="paramTableFilter">
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/system/param/paramController.js"></script>

<script type="text/html" id="paramBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
    {{#  if(d.status == 'enabled'){ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="stop"><i class="iconfont icon-stop"></i> 停用</a>
    {{#  } else { }}
    <a class="layui-btn  layui-btn-xs" lay-event="recover"><i class="iconfont icon-save"></i> 启用</a>
    {{#  } }}
</script>

<script type="text/html" id="bizModuleTpl">
    <#if modelList?? && (modelList?size > 0)>
    <#list modelList as param>
        {{#  if(d.bizModule == '${param.dictCode}'){ }}
        <span class="label label-info">${param.dictName}</span>
        {{#  } }}
    </#list>
    </#if>
</script>

<script type="text/html" id="statusTpl">
    {{#  if(d.status == 'enabled'){ }}
    <span class="label label-info">有效</span>
    {{#  } else { }}
    <span class="label label-warning">无效</span>
    {{#  } }}
</script>

</body>

</html>