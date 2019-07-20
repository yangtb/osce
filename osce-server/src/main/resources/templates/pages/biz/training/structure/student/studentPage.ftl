<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>学员管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <style>
        .layui-input, .layui-select {
            height: 30px;
            line-height: 1.3;
            background-color: rgb(255, 255, 255);
            border-width: 1px;
            border-style: solid;
            border-radius: 2px;
        }
    </style>
</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
    var currentGrade = '${currentGrade!}';
</script>

<body>

<div class="wrapper-content">
    <form class="layui-form" style="margin: 5px 0px 5px 0px; padding-bottom: 5px; border-bottom: 1px solid #d2d2d2">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <select name="idGrade" lay-verify="required">
                    <#if allGrade?? && (allGrade?size > 0)>
                        <#list allGrade as grade >
                            <option value="${grade.idGrade!}" <#if grade.fgActive='1'>selected</#if>>${grade.naGrade!}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button id="queryDept" type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="deptSearchFilter">
                    <i class="iconfont icon-query"></i> 查询
                </button>
            </div>
        </div>
    </form>

    <div class="layui-col-xs3">
        <div id="treeTitle">
            <div id="treeDiv" style="overflow:auto; border: 1px solid #dddddd">
                <ul id="departTree" class="ztree"></ul>
            </div>
        </div>
    </div>
    <div class="layui-col-xs9" style="padding-left: 5px;">
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
                    <input type="text" name="keywords" class="layui-input" autocomplete="off"
                           placeholder="请输入姓名或手机号" style="height:30px;">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <button type="button" class="layui-btn layui-btn-sm" lay-submit lay-filter="studentSearchFilter">
                        <i class="iconfont icon-query"></i> 查询
                    </button>
                    <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
                        <i class="iconfont icon-reset"></i> 重置
                    </button>
                </div>
            </div>
        </form>

        <table id="studentTable" lay-filter="studentTableFilter">
        </table>
    </div>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/ztree/js/jquery.ztree.core.js"></script>
<script src="${contextPath}/biz/js/biz/training/structure/student/studentController.js"></script>

<script type="text/html" id="enabledTpl">
    {{#  if(d.enabled == true){ }}
    <i class="iconfont icon-gou" style="color: #5FB878"></i>
    {{#  } else { }}
    <i class="iconfont icon-chacha" style="color: #FF5722"></i>
    {{#  } }}
</script>

<script type="text/html" id="sexTpl">
    {{#  if(d.sex == 1){ }}
    男
    {{#  } }}
    {{#  if(d.sex == 2){ }}
    <span style="color: #F581B1;">女</span>
    {{#  } }}
    {{#  if(d.sex == ''){ }}

    {{#  } }}
</script>

<script type="text/html" id="studentBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i> 编辑</a>
</script>

</body>
</html>