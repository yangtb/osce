<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>题目定义</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=itemManage-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <style>
        .layui-input, .layui-select {
            height: 30px;
            line-height: 1.3;
            background-color: rgb(255, 255, 255);
            border-width: 1px;
            border-style: solid;
            border-radius: 2px;
        }
    </style>
</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
    var idItemStore = '${idItemStore!}';
</script>

<body class="body-my">

<div>
    <form class="layui-form">
        <div class="layui-input-inline">
            <select id="idItemSection" lay-filter="idItemSectionFilter" lay-verify="required">
            </select>
        </div>
        <div class="layui-inline">
            <div class="layui-btn-group">
                <button type="button" class="layui-btn layui-btn-sm" id="addSection">
                    <i class="layui-icon layui-icon-add-1"></i>增加目录
                </button>
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="delSection">
                    <i class="layui-icon layui-icon-delete"></i>删除目录
                </button>
            </div>
        </div>
        <div class="layui-inline" style="padding-left: 20px;">
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="addItem">
                <i class="layui-icon layui-icon-add-1"></i>增加题目
            </button>
        </div>
    </form>

    <table id="itemManageTable" lay-filter="itemManageTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/biz/js/biz/training/item/itemManageController.js"></script>

<script type="text/html" id="fgActiveTpl">
    <input type="checkbox" name="fgActive" value="{{d.idItem}}"
           lay-skin="switch" lay-text="NO|OFF" lay-filter="fgActiveCheckFilter" {{ d.fgActive== '1' ? 'checked' : '' }}>
</script>

<script type="text/html" id="sdItemCaTpl">
    {{#  if(d.sdItemCa == 1){ }}
    A1
    {{#  } }}
    {{#  if(d.sdItemCa == 2){ }}
    A2
    {{#  } }}
    {{#  if(d.sdItemCa == 3){ }}
    B1
    {{#  } }}
</script>

<script type="text/html" id="sdItemLevelTpl">
    {{#  if(d.sdItemLevel == 1){ }}
    易
    {{#  } }}
    {{#  if(d.sdItemLevel == 2){ }}
    较易
    {{#  } }}
    {{#  if(d.sdItemLevel == 3){ }}
    中
    {{#  } }}
    {{#  if(d.sdItemLevel == 4){ }}
    难
    {{#  } }}
    {{#  if(d.sdItemLevel == 5){ }}
    较难
    {{#  } }}
</script>

<script type="text/html" id="itemManageBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="add" style="margin-left: 0px">
        <i class="layui-icon layui-icon-add-1"></i>
    </a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" style="margin-left: 0px">
        <i class="layui-icon layui-icon-edit"></i>
    </a>
    <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-danger" lay-event="del" style="margin-left: 0px">
        <i class="layui-icon layui-icon-delete"></i>
    </a>
</script>

</body>
</html>