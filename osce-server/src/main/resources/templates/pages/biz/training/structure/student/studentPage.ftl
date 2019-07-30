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
                <select id="idGrade" name="idGrade" lay-verify="required">
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
        <form id="exportForm" action="${basePath}/pf/p/student/template/download" method="post">
        </form>
        <form class="layui-form">
            <div class="layui-row">
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
            </div>

            <div class="layui-row" style="padding-top: 2px;">
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="moveStudent">
                        <i class="iconfont icon-renyuanqianyi"></i>往届学员迁移
                    </button>
                </div>
                <div class="layui-input-inline" style="padding-left: 10px;">
                    <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="templateDownLoad">
                        <i class="iconfont icon-mobanxiazai"></i> 模板下载
                    </button>
                    &nbsp;批量导入
                </div>
                <div class="layui-input-inline">
                    <div class="layui-form-block">
                        <input type="file" name="file" class="layui-btn layui-btn-primary"
                               id="itemImport">
                    </div>
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
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
</script>

<script type="text/html" id="LAY-excel-export-ans">
    {{# layui.each(d.data, function(index, item){ }}
    <blockquote class="layui-elem-quote">{{d.files[index].name}}</blockquote>
    <div class="layui-tab">
        <ul class="layui-tab-title">
            {{# layui.each(item, function(sheetname, content) { }}
            <li class="layui-this">{{sheetname}}</li>
            {{# }); }}
        </ul>

        <div class="layui-tab-content" style="overflow: auto">
            {{# layui.each(item, function(sheetname, content) { }}
            <div class="layui-tab-item layui-show">
                <table class="layui-table" style="width: 900px; height: auto">
                    {{# layui.each(content, function(index, value) { }}
                    <tr>
                        {{#  if(index == 0){ }}
                        <td style="color: #009688">学届</td>
                        <td style="color: #009688">班级</td>
                        {{#  } else { }}
                        <td style="color: #009688">{{d.naGrade}}</td>
                        <td style="color: #009688">{{d.naDepart}}</td>
                        {{#  } }}
                        {{# layui.each(value, function(key, val) { }}
                        <td>{{val}}</td>
                        {{# });}}
                    </tr>
                    {{# });}}
                </table>
                <#--<pre class="layui-code">{{JSON.stringify(content, null, 2)}}</pre>-->
            </div>
            {{# }); }}
        </div>
    </div>
    {{# }) }}
</script>


</body>
</html>