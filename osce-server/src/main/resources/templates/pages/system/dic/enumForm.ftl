<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}枚举</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var formType = '${formType}';
    </script>
</head>

<body class="body-my">

<div class="wrapper-content">
    <form class="layui-form" id="enumform">
        <div hidden>
            <input name="id" hidden>
        </div>
        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">字典名称<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="groupName" lay-verify="required" autocomplete="off" disabled class="layui-input layui-disabled">
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">字典编码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="groupCode" lay-verify="required" autocomplete="off" disabled class="layui-input layui-disabled">
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">枚举名称<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="dictName" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">枚举编码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="dictCode" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="text" name="sortNum" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">扩展字段</label>
            <div class="layui-input-block">
                <input type="text" name="extValue" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text form-item-my5">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addEnum">
                    <i class="iconfont icon-save-copy"></i> 保存
                </button>
            </div>
        </div>
    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/system/dic/dicEnumFormController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function(){
            $("#enumform").autofill(data);
            layui.use('form',function(){
                layui.form.render();
            });

        });
    }
</script>

</body>
</html>