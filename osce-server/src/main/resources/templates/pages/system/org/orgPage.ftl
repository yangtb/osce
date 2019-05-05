<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>机构管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/public/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/public/ztree/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${contextPath}/public/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/public/biz/iconfont/iconfont.css">
    <style>

    </style>
</head>

<script>
    var basePath = '${basePath}';
    var contextPath = '${contextPath}';
</script>

<body>

<div class="wrapper-content">
    <div class="layui-col-xs5">
        <div id="treeTitle">
            <fieldset class="layui-elem-field" style="border: 1px solid #dddddd">

                <form class="layui-form form-item-my2">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" id="key" name="name" placeholder="输入机构名称" class="layui-input"
                                   autocomplete="off">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <button type="button" id="queryBtn" class="layui-btn" lay-submit
                                    lay-filter="diseaseSearchFilter">
                                <i class="iconfont icon-query"></i>查询
                            </button>
                        </div>
                    </div>
                </form>
            </fieldset>

            <div id="treeDiv" style="overflow:auto; border: 1px solid #dddddd">
                <ul id="orgTree" class="ztree"></ul>
            </div>
        </div>
    </div>
    <div class="layui-col-xs7" style="padding-left: 10px; padding-top: 20px;">
        <form class="layui-form" id="orgForm">
            <div hidden>
                <input name="idDieclass" hidden>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">目录名称<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-block" style="width: 440px;">
                    <input type="text" name="name" id="name" lay-verify="required|commonLength" lay-vertype="tips"
                           autocomplete="off"
                           class="layui-input" placeholder="疾病名称">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">目录编码<i class="iconfont icon-required"
                                                       style="color: #f03f2d"></i></label>
                <div class="layui-input-block" style="width: 440px;">
                    <input type="text" name="cd" id="cd" autocomplete="off" lay-verify="commonLength" lay-vertype="tips"
                           class="layui-input" placeholder="疾病目录">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">上级编码</label>
                <div class="layui-input-block" style="width: 440px;">
                    <input type="text" name="cdPar" id="cdPar" lay-verify="commonLength" lay-vertype="tips" autocomplete="off"
                           class="layui-input" placeholder="上级编码">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">ICD编码_起</label>
                    <div class="layui-input-block">
                        <input type="text" name="icdB" lay-verify="commonLength" lay-vertype="tips" autocomplete="off"
                               class="layui-input" placeholder="ICD编码_起">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">ICD编码_终</label>
                    <div class="layui-input-block">
                        <input type="text" name="icdE" lay-verify="commonLength" lay-vertype="tips" autocomplete="off"
                               class="layui-input" placeholder="ICD编码_终">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">

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
                    <label class="layui-form-label">激活状态<i class="iconfont icon-required"
                                                           style="color: #f03f2d"></i></label>
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
                    <button class="layui-btn" id="addDiseaseInfo">
                        <i class="iconfont icon-jibingxinxi1"></i> 疾病维护
                    </button>
                    <button class="layui-btn" id="save" lay-submit="" lay-filter="addDiseaseCatalogue">
                        <i class="iconfont icon-save-copy"></i> 保存
                    </button>
                    <button id="reset" type="reset" class="layui-btn layui-btn-danger">
                        <i class="iconfont icon-reset"></i> 重置
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="${contextPath}/public/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/public/common/js/jquery.min.js"></script>
<script src="${contextPath}/public/ztree/js/jquery.ztree.core.js"></script>
<script src="${contextPath}/public/ztree/js/jquery.ztree.excheck.js"></script>
<script src="${contextPath}/public/ztree/js/jquery.ztree.exhide.js"></script>
<script src="${contextPath}/public/ztree/js/fuzzysearch.js"></script>
<script src="${contextPath}/public/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/biz/org/orgController.js"></script>

</body>
</html>