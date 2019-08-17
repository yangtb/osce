<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增评量表明细</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var idCobEvaluate = '${idCobEvaluate!}';
    </script>
</head>

<body class="wrapper-content">
<div>
    <form class="layui-form" id="addCobEvaluateForm">
        <div hidden>
            <input name="idCobEvaluateDetail" id="idCobEvaluateDetail" hidden>
        </div>
        <div class="layui-form-item">
            <div class="layui-row">
                <label class="layui-form-label">评分项<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-block">
                    <input type="text" name="naCobEvaluateDetail" autocomplete="off" class="layui-input"
                           lay-verify="required|commonLength64" lay-vertype="tips"/>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-row">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-block">
                    <input type="number" name="sort" id="sort" autocomplete="off" class="layui-input"
                           lay-verify="sort" lay-vertype="tips">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" id="save" lay-filter="addCobEvaluateDetail">
                    <i class="iconfont icon-save-copy"></i>保存
                </button>
            </div>
        </div>

    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/case/cobEvaluateFormDetailController.js"></script>

</body>
</html>