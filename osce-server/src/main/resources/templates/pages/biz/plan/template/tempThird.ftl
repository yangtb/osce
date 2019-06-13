<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>分配SP</title>
    <link rel="stylesheet" href="${contextPath}/biz/css/template/preview.css">
    <script>
        var basePath = '${basePath!}';
        var idModel = '${idModel!}';
    </script>
</head>

<body>

<div class="wrapper" style="height: 650px;">
    <table cellpadding="10000" style="width: 100%; height: 630px;">
        <thead>
        <tr id="days">
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr id="area-am">
            <td class="time-line">
                <p>&emsp;上&emsp;</p>
                <p style="margin-top: 20px;">&emsp;午&emsp;</p>
            </td>
        </tr>
        <tr id="area-pm">
            <td class="time-line">
                <p>下</p>
                <p style="margin-top: 20px;">午</p>
            </td>
        </tr>
        </tbody>
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/templateThird.js"></script>

</body>
</html>