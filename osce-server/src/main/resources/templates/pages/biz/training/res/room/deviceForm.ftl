<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${formType}固定设备</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var formType = '${formType}';
        var idRoom = '${idRoom!}';
    </script>
    <style>
        .layui-unselect dl { max-height:180px; }
    </style>
</head>

<body class="body-my">

<div class="wrapper-content">
    <form class="layui-form" id="deviceForm">
        <div hidden>
            <input name="idRoomDevice" id="idRoomDevice" hidden>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">设备类型<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">

                <select name="sdRoomDeviceCa" lay-verify="required" lay-vertype="tips">
                    <option value="">请选择</option>
                    <#if roomDeviceList?? && (roomDeviceList?size > 0)>
                        <#list roomDeviceList as enum>
                            <option value="${enum.dictCode!}">${enum.dictName!}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>

        <div class="layui-form-item form-item-my5">
            <label class="layui-form-label">设备编号<i class="iconfont icon-required" style="color: #f03f2d"></i></label>
            <div class="layui-input-block">
                <input type="text" name="cdRoomDevice" lay-verify="required|cdRoomDevice" lay-vertype="tips"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text form-item-my5">
            <label class="layui-form-label">设备地址</label>
            <div class="layui-input-block">
                <textarea name="roomDeviceAddress" class="layui-textarea" lay-verify="roomDeviceAddress"></textarea>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="desRoomDevice" class="layui-textarea" lay-verify="desDevice"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addDevice">
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
<script src="${contextPath}/biz/js/biz/training/res/room/deviceFormController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function(){
            $("#deviceForm").autofill(data);
            layui.use('form',function(){
                layui.form.render();
            });

        });
    }
</script>

</body>
</html>