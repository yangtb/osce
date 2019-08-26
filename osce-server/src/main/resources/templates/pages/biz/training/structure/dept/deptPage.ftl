<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>部门管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/mouseRight.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <style>

    </style>
</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
    var currentGrade = '${currentGrade!}';
</script>

<body>

<#--右键菜单-->
<div id="rMenu" class="wrap-ms-right">
    <li id="m_add" class="ms-item"><i class="layui-icon layui-icon-add-1"></i>添加</li>
    <li id="m_del" class="ms-item"><i class="layui-icon layui-icon-delete"></i>删除</li>
</div>

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
                <button id="queryDept" type="button" class="layui-btn" lay-submit lay-filter="deptSearchFilter">
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
    <div class="layui-col-xs9" style="padding-left: 10px; padding-top: 20px;">
        <form class="layui-form" id="departForm">
            <div hidden>
                <input id="idDepart" name="idDepart" hidden>
                <input id="idDepartPar" name="idDepartPar" hidden>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">部门名称<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-block" >
                    <input type="text" name="naDepart" id="naDepart" lay-verify="required|commonLength" lay-vertype="tips"
                           autocomplete="off"
                           class="layui-input" placeholder="部门名称">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">部门类型<i class="iconfont icon-required"
                                                           style="color: #f03f2d"></i></label>
                    <div class="layui-input-block">
                        <select name="sdDepartCa" lay-verify="required">
                            <option value="1">院系</option>
                            <option value="2">专业</option>
                            <option value="3">班级</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">学届<i class="iconfont icon-required"
                                                         style="color: #f03f2d"></i></label>
                    <div class="layui-input-block">
                        <select name="idGrade" lay-verify="required">
                            <#if allGrade?? && (allGrade?size > 0)>
                                <#list allGrade as grade >
                                    <option value="${grade.idGrade!}" <#if grade.fgActive='1'>selected</#if>>${grade.naGrade!}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">部门描述</label>
                <div class="layui-input-block" >
                    <input type="text" name="desDepart" id="desDepart"
                           autocomplete="off" lay-verify="commonLength255" lay-vertype="tips"
                           class="layui-input" placeholder="部门描述">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">上级部门</label>
                <div class="layui-input-block" >
                    <input type="text" name="idDepartParName" id="idDepartParName" autocomplete="off"
                           class="layui-input" placeholder="上级部门（左侧树右键点'添加'选择）">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">排序</label>
                    <div class="layui-input-block">
                        <input type="text" name="sort" id="sort" autocomplete="off" class="layui-input"
                               lay-verify="sort" lay-vertype="tips">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">激活状态</label>
                    <div class="layui-input-block">
                        <input type="checkbox" checked="" name="fgActive" lay-skin="switch"
                               lay-filter="fgActiveSwitch" value="1" lay-text="激活|停用">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">创建人</label>
                    <div class="layui-input-block">
                        <input type="text" name="creator" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="gmtCreate" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">修改人</label>
                    <div class="layui-input-block">
                        <input type="text" name="operator" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">修改时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="gmtModify" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" id="save" lay-submit="" lay-filter="addDepart">
                        <i class="iconfont icon-save-copy"></i> 保存
                    </button>
                    <button id="reset" type="reset" class="layui-btn layui-btn-danger">
                        <i class="iconfont icon-reset"></i> 重新填写
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/ztree/js/jquery.ztree.core.js"></script>
<script src="${contextPath}/ztree/js/jquery.ztree.exedit.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/biz/training/structure/dept/deptController.js"></script>

</body>
</html>