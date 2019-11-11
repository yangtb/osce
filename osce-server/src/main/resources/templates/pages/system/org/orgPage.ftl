<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>机构管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" href="${contextPath}/biz/css/mouseRight.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">

    <style>

    </style>
</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
</script>

<body>

<div class="layui-card layadmin-header" style="display: block;">
    <div class="layui-breadcrumb-my-title">
        <span>机构管理</span>
    </div>
    <div class="layui-breadcrumb-my">
        <a lay-href="${basePath}/main">主页</a><span lay-separator="">/</span>
        <a>系统管理</a><span lay-separator="">/</span>
        <a><cite>机构管理</cite></a>
    </div>
</div>

<#--右键菜单-->
<div id="rMenu" class="wrap-ms-right">
    <li id="m_add" class="ms-item"><i class="layui-icon layui-icon-add-1"></i>添加</li>
    <li id="m_del" class="ms-item"><i class="layui-icon layui-icon-delete"></i>删除</li>
</div>

<div class="wrapper-content">
    <div class="layui-col-xs3">
        <div id="treeTitle">
            <form class="layui-form" style="margin: 5px 0px -10px 0px;">
                <div class="layui-form-item">
                    <div class="layui-col-md12">
                        <input type="text" id="key" name="name" placeholder="请输入机构名称（自动搜索）" class="layui-input"
                               autocomplete="off">
                    </div>
                </div>
            </form>

            <div id="treeDiv" style="overflow:auto; border: 1px solid #dddddd">
                <ul id="orgTree" class="ztree"></ul>
            </div>
        </div>
    </div>
    <div class="layui-col-xs9" style="padding-left: 10px; padding-top: 20px;">
        <form class="layui-form" id="orgForm" lay-filter="orgFormFilter">
            <div hidden>
                <input id="idOrg" name="idOrg" hidden>
                <input id="idOrgPar" name="idOrgPar" hidden>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构名称<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-block" style="width: 514px;">
                    <input type="text" name="naOrg" id="naOrg" lay-verify="required|commonLength" lay-vertype="tips"
                           autocomplete="off"
                           class="layui-input" placeholder="机构名称">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构描述</label>
                <div class="layui-input-block" style="width: 514px;">
                    <input type="text" name="desOrg" id="desOrg" autocomplete="off" lay-verify="commonLength255" lay-vertype="tips"
                           class="layui-input" placeholder="机构描述">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">上级机构</label>
                <div class="layui-input-block" style="width: 514px;">
                    <input type="text" name="idOrgParName" id="idOrgParName" autocomplete="off"
                           class="layui-input" placeholder="上级机构（点击左侧选择）">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">机构地址</label>
                <div class="layui-input-block" style="width: 514px;">
                    <input type="text" name="address" id="address" lay-verify="commonLength255" lay-vertype="tips" autocomplete="off"
                           class="layui-input" placeholder="机构地址">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tell" lay-verify="commonLength" lay-vertype="tips" autocomplete="off"
                               class="layui-input" placeholder="电话">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" lay-verify="commonLength" lay-vertype="tips" autocomplete="off"
                               class="layui-input" placeholder="邮箱">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">布局图</label>
                <div class="layui-input-inline" style="vertical-align: middle;">
                    <input id="examRoomUrl" name="examRoomUrl" placeholder="请上传文件" autocomplete="off"
                           class="layui-input">
                </div>
                <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                    <i class="layui-icon">&#xe681;</i>上传
                </button>
                <button type="button" class="layui-btn layui-btn-primary" id="reviewPhoto">
                    <i class="iconfont icon-detail"></i> 预览
                </button>
            </div>
            <div class="layui-form-item">

            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">排序</label>
                    <div class="layui-input-inline">
                        <input type="text" name="sort" id="sort" autocomplete="off" class="layui-input"
                               lay-verify="sort" lay-vertype="tips">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">激活状态</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" checked="" name="fgActive" lay-skin="switch"
                               lay-filter="fgActiveSwitch" value="1" lay-text="激活|停用">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">创建人</label>
                    <div class="layui-input-inline">
                        <input type="text" name="creator" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtCreate" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">修改人</label>
                    <div class="layui-input-inline">
                        <input type="text" name="operator" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">修改时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtModify" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" id="save" lay-submit="" lay-filter="addOrg">
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
<script src="${contextPath}/ztree/js/jquery.ztree.exhide.js"></script>
<script src="${contextPath}/ztree/js/jquery.ztree.exedit.js"></script>
<script src="${contextPath}/ztree/js/fuzzysearch.js"></script>
<script src="${contextPath}/biz/js/system/org/orgController.js"></script>

</body>
</html>