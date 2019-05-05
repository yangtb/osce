<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}参数</title>
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
    <form class="layui-form" id="paramform">
        <div hidden>
            <input name="id" hidden>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">参数编码<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="paramCode" lay-verify="required" autocomplete="off"
                       class="layui-input <#if (formType == 'edit')>layui-disabled</#if>"
                       <#if (formType == 'edit')>disabled</#if>/>
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">参数名称<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="paramName" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">作用业务<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <select name="sysType" lay-verify="required">
                    <option value="pf" selected>管理平台</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">作用模块<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <select name="bizModule" lay-verify="required">
                    <#if modelList?? && (modelList?size > 0)>
                        <#list modelList as param>
                            <option value="${param.dictCode}">${param.dictName}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">参数类型<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <select name="dataType" lay-verify="required">
                    <option value="string" selected>字符串</option>
                    <option value="int">整数</option>
                    <option value="float">小数</option>
                    <option value="datetime">日期与时间(yyyy-MM-dd HH:mm:ss)</option>
                    <option value="date">日期(yyyy-MM-dd)</option>
                    <option value="multi">多值</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">默认值<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="defaultValue" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">设定值</label>
            <div class="layui-input-block">
                <input type="text" name="paramValue" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text form-item-my5">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="remark" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addParam">
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
<script src="${contextPath}/biz/js/system/param/paramFormController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function(){
            $("#paramform").autofill(data);
            layui.use('form',function(){
                layui.form.render();
            });

        });
    }
</script>

</body>
</html>