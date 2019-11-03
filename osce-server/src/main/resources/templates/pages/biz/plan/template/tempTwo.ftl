<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>排站</title>
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/template/preview1.css">

    <script>
        var basePath = '${basePath!}';
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
            <td style="padding: 10px">
                <div class="room-name">
                    <span class="room-num">上午</span>
                </div>
            </td>
        </tr>

        <tr id="area-pm" class="tr-my" style="height: 300px;">
            <td style="padding: 10px">
                <div class="room-name">
                    <span class="room-num">下午</span>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/templateTwo.js"></script>

</body>
</html>