<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>分配SP</title>
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/template/preview2.css">
    <script>
        var basePath = '${basePath!}';
        var idPlan = '${idPlan!}';
        var idModelFrom = '${idModelFrom!}';
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

<div class="wrapper" style="height: 950px;">
    <table cellpadding="10000" style="width: 100%; height: 900px;">
        <thead>
        <tr id="days" style="height: 50px;">
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr id="area-am" style="height: 400px">
            <td class="font-title" style="padding-left: 10px; padding-right: 10px">
                <p>上</p>
                <p style="margin-top: 20px;">午</p>
            </td>
        </tr>
        <tr id="area-pm" style="height: 400px">
            <td class="font-title">
                <p>下</p>
                <p style="margin-top: 20px;">午</p>
            </td>
        </tr>
        </tbody>
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/planSp.js"></script>

</body>
</html>