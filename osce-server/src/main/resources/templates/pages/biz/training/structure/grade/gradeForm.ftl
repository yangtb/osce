<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}学届</title>
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
    <form class="layui-form" id="gradeForm">
        <div hidden>
            <input name="idGrade" hidden>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">学届名称<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="naGrade" lay-verify="required|naGrade" lay-vertype="tips"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text form-item-my5">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="desGrade" class="layui-textarea" lay-verify="desGrade"></textarea>
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <div class="layui-inline">
                <label class="layui-form-label">当前学届</label>
                <div class="layui-input-block">
                    <input type="checkbox" checked="" name="fgActive" lay-skin="switch"
                           lay-filter="userEnabledSwitch" value="1" lay-text="是|否">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addGrade">
                    <i class="iconfont icon-save-copy"></i> 保存
                </button>
                <#if (formType == 'add')>
                    <button type="reset" class="layui-btn layui-btn-danger">
                        <i class="iconfont icon-reset"></i> 重新填写
                    </button>
                </#if>
            </div>
        </div>
    </form>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/structure/grade/gradeFormController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function(){
            $("#gradeForm").autofill(data);
            layui.use('form',function(){
                layui.form.render();
            });

        });
    }
</script>

</body>
</html>