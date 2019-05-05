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
                <button type="button" class="layui-btn" id="add">
                    <i class="iconfont icon-add"></i> 增加
                </button>
                <button type="button" class="layui-btn" id="edit">
                    <i class="iconfont icon-edit"></i> 编辑
                </button>
                <button type="button" class="layui-btn layui-btn-danger" id="del">
                    <i class="iconfont icon-delete"></i> 删除
                </button>
            </div>
            <div class="layui-input-inline">
                <input type="text" name="naGrade" class="layui-input"
                       placeholder="请输入学届名称">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" lay-submit lay-filter="gradeSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">
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

<script type="text/html" id="gradeBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
</script>

</body>

</html>