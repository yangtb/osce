<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>房间管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">

</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
</script>

<body>

<div class="layui-card layadmin-header" style="display: block;">
    <div class="layui-breadcrumb-my-title">
        <span>房间管理</span>
    </div>
    <div class="layui-breadcrumb-my">
        <a lay-href="${basePath!}/main">主页</a><span lay-separator="">/</span>
        <a>实训配置</a>
        <span lay-separator="">/</span>
        <a>资源管理</a>
        <span lay-separator="">/</span>
        <a><cite>房间管理</cite></a>
    </div>
</div>

<div class="wrapper-content">
    
    <div style="width: 100%;" id="orgRoomUrl">
        <#--<img src="https://jy-osce.oss-cn-beijing.aliyuncs.com/23A1A8C95A394371BDAE17A5F1686190.jpg">-->
        <#--<img style="width: 100%; max-height: 515px;" >-->
    </div>

    <form class="layui-form" style="padding-top: 5px;">
        <div class="layui-inline">
            <div class="layui-btn-group">
                <button type="button" class="layui-btn layui-btn-sm" id="add">
                    <i class="layui-icon layui-icon-add-1"></i>增加
                </button>
                <button type="button" class="layui-btn layui-btn-sm" id="edit">
                    <i class="layui-icon layui-icon-edit"></i>编辑
                </button>
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="del">
                    <i class="iconfont icon-batch-del"></i> 删除
                </button>
                <#--<button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="record">
                    <i class="layui-icon layui-icon-table"></i>使用记录
                </button>-->
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="roomSearchFilter">
                    <i class="iconfont icon-refresh"></i> 刷新
                </button>
            </div>
        </div>
    </form>

    <table id="roomTable" lay-filter="roomTableFilter">
    </table>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/res/room/roomController.js"></script>

<script type="text/html" id="fgActiveTpl">
    <input type="checkbox" name="fgActive" value="{{d.idRoom}}"
           lay-skin="switch" lay-text="是|否" lay-filter="fgActiveCheckFilter" {{ d.fgActive== '1' ? 'checked' : '' }}>
</script>

<script type="text/html" id="roomBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    {{#  if(d.roomFlag == '0'){ }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="roomLink"><i class="layui-icon layui-icon-link"></i>房间链接</a>
    {{#  } }}
    {{#  if(d.roomFlag == '1'){ }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="testLink"><i class="layui-icon layui-icon-link"></i>理论考试链接</a>
    {{#  } }}
    {{#  if(d.roomFlag == ''){ }}
    {{#  } }}
</script>

</body>
</html>