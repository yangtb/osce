<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>实训模板管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
    </script>
</head>

<body>

<div class="wrapper-content">
    <form class="layui-form">
        <div class="layui-inline">
            <div class="layui-btn-group">
                <button type="button" class="layui-btn layui-btn-sm" id="add">
                    <i class="iconfont icon-add"></i> 增加
                </button>
                <button type="button" class="layui-btn layui-btn-sm" id="edit">
                    <i class="iconfont icon-edit"></i> 编辑
                </button>
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="del">
                    <i class="layui-icon layui-icon-delete"></i>删除
                </button>
            </div>
            <div class="layui-input-inline">
                <input type="text" name="naModel" class="layui-input btn-sm-my" autocomplete="off"
                       placeholder="请输入模板名称">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="templateSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
                <button type="reset" class="layui-btn layui-btn-sm layui-btn-danger">
                    <i class="iconfont icon-reset"></i> 重置
                </button>
                <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="designExamPaper">
                    <i class="iconfont icon-kaojuan"></i> 设计考卷
                </button>
                <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="createExam">
                    <i class="iconfont icon-kaoshi"></i> 创建考试
                </button>
            </div>
        </div>
        <div>
            <button id="editTemplate" type="button" class="layui-btn layui-btn-normal"
                    lay-href="" style="display: none">实训模板编辑
            </button>
            <button id="designExamPaperHidden" type="button" class="layui-btn layui-btn-normal"
                    lay-href="" style="display: none">设计考卷
            </button>
            <button id="editPlan" type="button" class="layui-btn layui-btn-normal"
                    lay-href="" style="display: none">实训计划编辑
            </button>
        </div>
    </form>

    <table id="templateTable" lay-filter="templateTableFilter">
    </table>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/templateController.js"></script>

<script type="text/html" id="fgActiveTpl">
    <input type="checkbox" name="fgActive" value="{{d.idModel}}"
           lay-skin="switch" lay-text="NO|OFF" lay-filter="fgActiveCheckFilter" {{ d.fgActive== '1' ? 'checked' : '' }}>
</script>

<script type="text/html" id="templateBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
</script>

</body>

</html>