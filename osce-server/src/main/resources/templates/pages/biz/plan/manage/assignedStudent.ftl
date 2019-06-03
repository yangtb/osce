<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>分配学员</title>
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
            <button type="button" class="layui-btn layui-btn-sm" id="selectStudent">
                <i class="iconfont icon-add"></i> 选择学员
            </button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="del">
                <i class="iconfont icon-batch-del"></i> 删除
            </button>
        </div>
    </form>

    <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px;">
        <legend style="font-size: 14px; font-weight: bold">已分配学员</legend>
    </fieldset>

    <table id="assignedStudentTable" lay-filter="assignedStudentTableFilter">
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/assignedStudentController.js"></script>

</body>

</html>