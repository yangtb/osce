<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>实训计划管理</title>
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
                <input type="text" name="naModel" class="layui-input btn-sm-my" autocomplete="off"
                       placeholder="请输入考试名称">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="planSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-sm layui-btn-danger">
                    <i class="iconfont icon-reset"></i> 重置
                </button>
            </div>
        </div>
        <div>
            <button id="editPlan" type="button" class="layui-btn layui-btn-normal"
                    lay-href="" style="display: none">实训计划编辑
            </button>
        </div>
    </form>

    <table id="planTable" lay-filter="planTableFilter">
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/planController.js"></script>

<script type="text/html" id="fgReplanTpl">
    {{#  if(d.fgReplan == '1'){ }}
    <span style="color: red">补考</span>
    {{#  } else { }}
    普考
    {{#  } }}
</script>

<script type="text/html" id="sdPlanStatusTpl">
    {{#  if(d.sdPlanStatus == 1){ }}
    <button type="button" class="layui-btn layui-btn-xs" style="background-color: #FFB800">未发布</button>
    {{#  } }}
    {{#  if(d.sdPlanStatus == 2 || d.sdPlanStatus == 3){ }}
    <button type="button" class="layui-btn layui-btn-xs" style="background-color: #1E9FFF">已发布-待考</button>
    {{#  } }}
    {{#  if(d.sdPlanStatus == 4){ }}
    <button type="button" class="layui-btn layui-btn-xs" style="background-color: #5FB878">正在考试</button>
    {{#  } }}
    {{#  if(d.sdPlanStatus == 5){ }}
    <button type="button" class="layui-btn layui-btn-xs">考试完成</button>
    {{#  } }}
</script>

<script type="text/html" id="planBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
</script>

</body>

</html>