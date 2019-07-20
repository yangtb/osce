<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var contextPath = '${contextPath}';
    </script>

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

<body>

<div class="wrapper-content">
    <form id="exportForm" action="${basePath}/pf/p/user/download/userExcel" method="post">

    </form>
    <form class="layui-form" style="margin-left: -10px">
        <div class="layui-inline">
            <div class="layui-input-inline layui-btn-sm" style="width: 90px;">
                <select name="type">
                    <option value="0">请选择</option>
                    <option value="1">账号</option>
                    <option value="2">姓名</option>
                    <option value="3">手机号</option>
                </select>
            </div>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="queryCondition" autocomplete="off" class="layui-input  btn-sm-my">
            </div>
        </div>
        <div class="layui-input-inline">
            <select name="idOrg">
                <option value="">请选择</option>
                <#list allOrg as element>
                    <option value="${element.idOrg}">${element.naOrg}</option>
                </#list>
            </select>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="userSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-primary layui-btn-sm">
                    <i class="iconfont icon-reset"></i> 重新填写
                </button>
            </div>
        </div>
    </form>

    <table id="userTable" lay-filter="userTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/user/userController.js"></script>

<script type="text/html" id="userBar">
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="resetPass"><i class="iconfont icon-resetpass"></i> 密码重置</a>
</script>

<script type="text/html" id="switchTpl">
    <input type="checkbox" name="status" value="{{d.enabled}}" lay-skin="switch" lay-text="正常|锁定" lay-filter="statusSwitch" {{ d.enabled == 1 ? 'checked' : '' }}>
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
<script type="text/html" id="roleTypeTpl">
    {{#  if(d.role_type == 1){ }}
    超级管理员
    {{#  } else { }}
    普通管理员
    {{#  } }}
</script>

<script type="text/html" id="enabledTpl">
    {{#  if(d.enabled == 1){ }}
    <button class="layui-btn layui-btn-xs">正常</button>
    {{#  } else { }}
    <button class="layui-btn layui-btn-xs layui-btn-danger">锁定</button>
    {{#  } }}
</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button type="button" class="layui-btn layui-btn-sm" lay-event="add">
            <i class="iconfont icon-add"></i> 增加
        </button>
        <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="del" lay-event="del">
            <i class="iconfont icon-batch-del"></i> 删除
        </button>
        <button type="button" class="layui-btn layui-btn-sm" id="freeze" lay-event="freeze">
            <i class="iconfont icon-freeze"></i> 冻结
        </button>
        <#--<button type="button" class="layui-btn layui-btn-sm" lay-event="downloadExcel">
            <i class="iconfont icon-Excel"></i> 下载模板
        </button>
        <button type="button" class="layui-btn layui-btn-sm" id="uploadUser">
            <i class="layui-icon">&#xe67c;</i>上传文件
        </button>-->
    </div>
</script>

</body>
</html>