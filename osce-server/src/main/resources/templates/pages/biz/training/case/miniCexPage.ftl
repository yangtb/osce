<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>迷你临床演练评量</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <style>
        .label-1 {
            text-align: center; color: red; cursor: pointer
        }
        .label-2 {
            text-align: center; color: #1E9FFF; cursor: pointer
        }
        .label-3 {
            text-align: center; color: #009688; cursor: pointer
        }
        .label-align {
            text-align: center; cursor: pointer
        }
    </style>
    <script>
        var basePath = '${basePath!}';
        var cdCobEvaluate = '${cdCobEvaluate!}';

    </script>

</head>

<body class="wrapper-content">

<div>
    <div class="layui-row">
        <div class="layui-col-md12" style="text-align: center">
            <#if (cdCobEvaluate == '1')>
                <h1>临床技能操作评量 (Modified DOPS)报告</h1>
            <#else>
                <h1>迷你临床演练评量（Mini-CEX）报告</h1>
            </#if>

        </div>
    </div>

    <table class="layui-table" lay-size="sm">
        <colgroup>
            <col width="250">
            <col width="60">
            <col width="60">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th rowspan="3" style="text-align: center; color: #0C0C0C">评分项目</th>
            <th rowspan="3" style="text-align: center; color: #0C0C0C">未评或不适用</th>
            <th rowspan="2" style="text-align: center; color: #0C0C0C">未观察到</th>
            <th colspan="9" style="text-align: center; color: #0C0C0C">各项考评结果</th>
            <th rowspan="3" style="text-align: center; color: #0C0C0C">操作</th>
        </tr>
        <tr>
            <th colspan="3" style="text-align: center; color: #0C0C0C">尚需努力</th>
            <th colspan="3" style="text-align: center; color: #0C0C0C">合格达标</th>
            <th colspan="3" style="text-align: center; color: #0C0C0C">表现优异</th>
        </tr>
        <tr>
            <th style="text-align: center; color: #0C0C0C">0</th>
            <th style="text-align: center; color: red;">1</th>
            <th style="text-align: center; color: red;">2</th>
            <th style="text-align: center; color: red;">3</th>
            <th style="text-align: center; color: #1E9FFF;">4</th>
            <th style="text-align: center; color: #1E9FFF;">5</th>
            <th style="text-align: center; color: #1E9FFF;">6</th>
            <th style="text-align: center; color: #009688;">7</th>
            <th style="text-align: center; color: #009688;">8</th>
            <th style="text-align: center; color: #009688;">9</th>
        </tr>
        </thead>
        <tbody id="cobTbody">

        </tbody>
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.all.js"></script>
<script src="${contextPath}/biz/js/biz/training/case/cobEvaluateController.js"></script>

</body>
</html>