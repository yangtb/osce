<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>服务器信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">

</head>

<body>
<div class="wrapper-content">
    <form class="layui-form site-block">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px;">操作系统的名称：</label>
            <div class="layui-input-block" style="margin-left: 180px;">
                <input disabled type="text" value="${serverInfo.osName}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px;">操作系统的版本：</label>
            <div class="layui-input-block" style="margin-left: 180px;">
                <input disabled type="text" value="${serverInfo.osVersion}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px;">Java的运行环境版本：</label>
            <div class="layui-input-block" style="margin-left: 180px;">
                <input disabled type="text" value="${serverInfo.javaVersion}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px;">当前电脑CPU数量：</label>
            <div class="layui-input-block" style="margin-left: 180px;">
                <input disabled type="text" value="${serverInfo.cpuNum}个" autocomplete="off" class="layui-input">
            </div>
        </div>
        <#--<div class="layui-form-item">
            <label class="layui-form-label" style="width:150px;">虚拟机中的内存总量：</label>
            <div class="layui-input-block" style="margin-left: 180px;">
                <input disabled type="text" value="$number.format("#0.00", $math.div(${serverInfo.totalMemory}, $math.pow("1024","2")))M" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px;">虚拟机中的空闲内存：</label>
            <div class="layui-input-block" style="margin-left: 180px;">
                <input disabled type="text" value="$number.format("#0.00", $math.div(${serverInfo.freeMemory}, $math.pow("1024","2")))M" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px;">内存使用：</label>
            <div style="padding-top: 10px;">
                <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-left: 180px;">
                    <div class="layui-progress-bar layui-bg-green" lay-percent="$number.percent($math.div($math.sub(${serverInfo.totalMemory},${serverInfo.freeMemory}), ${serverInfo.totalMemory}))"></div>
                </div>
            </div>
        </div>-->
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px;">当前工作目录：</label>
            <div class="layui-input-block" style="margin-left: 180px;">
                <input disabled type="text" value="${serverInfo.userDir}" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </form>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>

<script>
    //注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>