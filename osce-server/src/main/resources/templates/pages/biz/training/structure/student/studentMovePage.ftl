<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>往届学员迁移</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" href="${contextPath}/layui/expand/css/formSelects-v4.css">

</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
</script>

<body class="body-my">

<div class="wrapper-content">

    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs3">
            <div id="treeTitle">
                <div id="treeDiv" style="overflow:auto; border: 1px solid #dddddd">
                    <ul id="departTree" class="ztree"></ul>
                </div>
            </div>
        </div>
        <div class="layui-col-xs9">
            <div id="test4" class="demo-transfer">

            </div>
        </div>
    </div>

    <div style="float: right; padding-right: 15px; padding-top: 5px;">
        <form class="layui-form">
            <div class="layui-inline" id="idDepart" style="width: 350px;">
                <select  name="idDepart" xm-select="select1" xm-select-height="36px">
                    <option value="">请选择迁移班级</option>
                </select>
            </div>

            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-normal" id="moveStudent">
                    <i class="iconfont icon-renyuanqianyi"></i>迁移
                </button>
                <button type="button" class="layui-btn layui-btn-warm" id="closeMoveWind">
                    <i class="layui-icon">&#x1006;</i> 取消
                </button>
            </div>
        </form>
    </div>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/ztree/js/jquery.ztree.core.js"></script>
<script src="${contextPath}/biz/js/biz/training/structure/student/studentMoveController.js"></script>


</body>
</html>