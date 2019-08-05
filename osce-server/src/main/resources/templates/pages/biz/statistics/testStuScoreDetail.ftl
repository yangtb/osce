<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>学生成绩详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <style>
        .label-td {
            text-align: center;
            color: #0C0C0C;
            min-width: 25px;
        }
    </style>

    <script>
        var basePath = '${basePath!}';
        var idExec = '${idExec!}';
    </script>
</head>

<body class="body-my">

<div class="wrapper-content">

    <table class="layui-table">

        <thead>
        <tr>
            <th colspan="4" style="text-align: center; font-weight: bold; color: #0C0C0C">主考官</th>
            <th colspan="4" style="text-align: center; font-weight: bold; color: #0C0C0C">考官</th>
            <th colspan="4" style="text-align: center; font-weight: bold; color: #0C0C0C">中控考官</th>
            <th style="text-align: center; font-weight: bold; color: #0C0C0C">总分</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="label-td">权重</td>
            <td class="label-td"><span id="weightManager"></span></td>
            <td class="label-td">得分</td>
            <td class="label-td"><span id="scoreManager"></span></td>
            <td class="label-td">权重</td>
            <td class="label-td"><span id="weightAssistant"></span></td>
            <td class="label-td">得分</td>
            <td class="label-td"><span id="scoreAssistant"></span></td>
            <td class="label-td">权重</td>
            <td class="label-td"><span id="weightRemote"></span></td>
            <td class="label-td">得分</td>
            <td class="label-td"><span id="scoreRemote"></span></td>
            <td class="label-td" style="color: #1E9FFF; font-weight: bold;"><span id="scoreSum"></span></td>
        </tr>
        </tbody>
    </table>


    <div class="layui-tab layui-tab-brief" lay-filter="scoreTabFilter">
        <ul class="layui-tab-title">
            <li class="layui-this">主考官</li>
            <li>考官</li>
            <li>中控考官</li>
        </ul>
        <div class="layui-tab-content" style="margin: -10px">
            <div class="layui-tab-item layui-show">
                <table class="layui-hide" id="score1Table" lay-filter="score1TableFilter"></table>

                </table>
            </div>
            <div class="layui-tab-item">
                <table class="layui-hide" id="score2Table" lay-filter="score2TableFilter"></table>

                </table>
            </div>
            <div class="layui-tab-item">
                <table class="layui-hide" id="score3Table" lay-filter="score3TableFilter"></table>

                </table>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/statistics/testStuScoreDetailController.js"></script>


</body>

</html>