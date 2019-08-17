<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>评量表明细</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <style>
        .label-align {
            text-align: center;
        }
    </style>
    <script>
        var basePath = '${basePath!}';
        var idCobEvaluate = '${idCobEvaluate!}';

    </script>

</head>

<body class="wrapper-content">

<div>
    <table class="layui-table" lay-size="sm">
        <colgroup>
            <col width="250">
            <col>
            <col>
            <col>
            <col>
            <col>
        </colgroup>
        <thead>
        <tr>
            <th style="text-align: center; color: #0C0C0C">与操作相关的知识</th>
            <th style="text-align: center; color: red">未完成</th>
            <th style="text-align: center; color: #FF5722">不适用</th>
            <th style="text-align: center; color: #FFB800">有欠缺</th>
            <th style="text-align: center; color: #009688">完成</th>
            <th style="text-align: center; color: #0C0C0C">操作</th>
        </tr>
        </thead>
        <tbody id="cobDetailTbody">
        <#--<tr>
            <td>操作部位的解剖</td>
            <td class="label-align"><input type="radio" name="test" value="1"></td>
            <td class="label-align"><input type="radio" name="test" value="2"></td>
            <td class="label-align"><input type="radio" name="test" value="3"></td>
            <td class="label-align"><input type="radio" name="test" value="4"></td>
            <td class="label-align" width="80">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="add"><i class="iconfont icon-add"></i></a>
                <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i></a>
            </td>
        </tr>-->
        </tbody>
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/case/cobEvaluateDetailController.js"></script>

</body>
</html>