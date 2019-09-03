<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>分配考官</title>

    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/template/preview.css">
    <script>
        var basePath = '${basePath!}';
        var idPlan = '${idPlan!}';
    </script>
</head>

<body>

<div class="wrapper" style="height: 670px;">
    <table cellpadding="10000" style="width: 100%; height: 650px;">
        <thead>
        <tr id="days" style="height: 50px;">
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr id="area-am" style="height: 300px">
            <td class="time-line font-title" style="padding-left: 10px; padding-right: 10px">
                <p>上</p>
                <p style="margin-top: 20px;">午</p>
            </td>
        </tr>
        <tr id="area-pm" style="height: 300px">
            <td class="time-line font-title">
                <p>下</p>
                <p style="margin-top: 20px;">午</p>
            </td>
        </tr>
        </tbody>
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/planAssistant.js"></script>

</body>
</html>