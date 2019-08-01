<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>排考预览</title>
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/template/preview1.css">
    <script>
        var basePath = '${basePath!}';
        var idPlan = '${idPlan!}';
        var idModel = '${idModel!}';
    </script>
</head>

<body>

<div class="wrapper" style="height: 670px;">
    <table cellpadding="10000" class="table-my" style="width: 100%; height: 650px;">
        <thead>
        <tr id="days" class="tr-my" style="height: 50px;">
            <th class="th-my"></th>
        </tr>
        </thead>
        <tbody>
        <tr id="area-am" class="tr-my" style="height: 300px;">
            <td class="font-title td-my">
                <p>&emsp;上&emsp;</p>
                <p style="margin-top: 20px;">午</p>
            </td>
        </tr>

        <tr id="area-pm" class="tr-my" style="height: 300px;">
            <td class="font-title td-my">
                <p>下</p>
                <p style="margin-top: 20px;">午</p>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/stationPreview.js"></script>

</body>
</html>