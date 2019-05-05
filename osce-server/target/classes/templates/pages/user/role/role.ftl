<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>用户角色</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
</script>

<body>

<div class="wrapper-content">
    <div class="layui-col-xs8">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-btn-group">
                    <button type="button" class="layui-btn" id="add">增加</button>
                    <button type="button" class="layui-btn" id="edit">编辑</button>
                    <button type="button" class="layui-btn" id="cancel">作废</button>
                    <button type="button" class="layui-btn layui-btn-danger" id="del">删除</button>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="queryCondition" placeholder="请输入角色名称" class="layui-input"
                           autocomplete="off" style="width: 130px;">
                </div>
            </div>
            <div class="layui-input-inline" style="width: 70px;">
                <select name="state">
                    <option value="0" selected>有效</option>
                    <option value="1">无效</option>
                </select>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <button type="button" class="layui-btn" lay-submit lay-filter="roleSearchFilter">
                        <i class="iconfont icon-query"></i> 查询
                    </button>
                    <button type="reset" class="layui-btn layui-btn-primary">
                        <i class="iconfont icon-reset"></i> 重新填写
                    </button>
                </div>
            </div>
        </form>

        <table id="roleTable" lay-filter="roleTableFilter">
        </table>
    </div>
    <div class="layui-col-xs4">
        <div id="treeTitle">
            <fieldset class="layui-elem-field layui-field-title" style="margin:5px 0 0;">
                <legend>
                    <span id="treeRoleName"></span>授权
                    <button class="layui-btn layui-btn-sm" id="saveSet">保存设置</button>
                </legend>
            </fieldset>
        </div>
        <div id="treeDiv" style="overflow:auto; border-bottom: 1px solid #dddddd">
            <ul id="roleTree" class="ztree"></ul>
        </div>
    </div>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/ztree/js/jquery.ztree.all.min.js"></script>
<script src="${contextPath}/biz/js/user/role/roleController.js"></script>

<script type="text/html" id="stateTpl">
    {{#  if(d.state == 0){ }}
    <span class="label label-info">有效</span>
    {{#  } else { }}
    <span class="label label-warning">无效</span>
    {{#  } }}
</script>

<script type="text/html" id="viewTreeBar">
    <a class="layui-btn layui-btn-xs" lay-event="editAuth">
        <i class="iconfont icon-quanxian"></i> 查看权限
    </a>
</script>


</body>
</html>