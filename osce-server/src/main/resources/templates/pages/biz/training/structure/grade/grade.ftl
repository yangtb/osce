<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>学届管理</title>
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
                <input type="text" name="naGrade" class="layui-input btn-sm-my" autocomplete="off"
                       placeholder="请输入学届名称">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="gradeSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                    <i class="iconfont icon-reset"></i> 重新填写
                </button>
            </div>
        </div>
    </form>

    <table id="gradeTable" lay-filter="gradeTableFilter">
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/structure/grade/gradeController.js"></script>

<script type="text/html" id="fgActiveTpl">
    <input type="checkbox" name="fgActive" value="{{d.idGrade}}"
           lay-skin="switch" lay-text="是|否" lay-filter="fgActiveCheckFilter" {{ d.fgActive== '1' ? 'checked' : '' }}>
</script>
<#--
<script type="text/html" id="currentGradeTpl">
    {{#  if(d.currentGrade == true){ }}
    <i class="iconfont icon-gou" style="color: #5FB878"></i>
    {{#  } else { }}
    <i class="iconfont icon-chacha" style="color: #FF5722"></i>
    {{#  } }}
</script>-->

<script type="text/html" id="gradeBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
</script>

</body>

</html>