<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>题库-目录管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var idItemStore = '${idItemStore!}';
    </script>
</head>

<body class="body-my">

<div class="wrapper-content">
    <form class="layui-form" id="sectionForm">
        <div hidden>
            <input name="idItemSection" id="idItemSection" hidden>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">目录名称<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="naItemSection" lay-verify="required|naItemSection" lay-vertype="tips"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text form-item-my5">
            <label class="layui-form-label">排序<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="sort" id="sort" autocomplete="off" class="layui-input"
                       lay-verify="sort" lay-vertype="tips">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addSection">
                    <i class="iconfont icon-save-copy"></i> 保存
                </button>
                <button type="reset" class="layui-btn layui-btn-danger">
                    <i class="iconfont icon-reset"></i> 重新填写
                </button>
            </div>
        </div>
    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/item/td/sectionFormController.js"></script>


</body>
</html>