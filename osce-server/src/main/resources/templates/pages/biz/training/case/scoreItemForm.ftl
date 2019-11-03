<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增评分项</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var contextPath = '${contextPath}';
        var formType = '${formType!}';
        var idCase = '${idCase!}';
        var idScoreSheet = '${idScoreSheet!}';
    </script>
</head>

<body class="wrapper-content">
<div>
    <form class="layui-form" id="itemForm">
            <div hidden>
                <input name="idScoreItem" id="idScoreItem" hidden>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">指标类型</label>
                    <div class="layui-input-inline">
                        <input type="text" name="sdScoreItemCaText" id="sdScoreItemCaText" class="layui-input"  style="position:absolute;z-index:2;width:80%;" autocomplete="off">
                        <select type="text" name="sdScoreItemCa" id="sdScoreItemCa" lay-filter="sdScoreItemCaFilter" autocomplete="off" class="layui-select" lay-search>
                            <option value=""></option>
                            <#if scoreItemCaList?? && (scoreItemCaList?size > 0)>
                                <#list scoreItemCaList as enum>
                                    <option value="${enum.dictCode!}">${enum.dictName!}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">评分项<i class="iconfont icon-required"
                                                          style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline">
                        <input type="text" name="naScoreItem" autocomplete="off" class="layui-input"
                               lay-verify="required|commonLength64" lay-vertype="tips"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">参考分值<i class="iconfont icon-required"
                                                           style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline">
                        <input class="layui-input layui-input-number" min="1" step="1"
                               name="score" autocomplete="off" lay-verify="required" lay-vertype="tips">

                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">内容<i class="iconfont icon-required"
                                                         style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="desScoreItem" class="layui-textarea"
                                          autocomplete="off" lay-verify="required|commonLength255" lay-vertype="tips"/></textarea>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">解析</label>
                    <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="scoreItemAnalysis" class="layui-textarea"
                                          autocomplete="off" lay-verify="commonLength255" lay-vertype="tips"/></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">参考问题</label>
                    <div class="layui-input-inline" style="width: 514px;">
                                    <textarea name="refQuestion" class="layui-textarea"
                                              autocomplete="off" lay-verify="commonLength255" lay-vertype="tips"/></textarea>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">激活</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" checked="" name="fgActive" lay-skin="switch"
                               lay-filter="fgActiveSwitch" value="1" lay-text="NO|OFF">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">排序</label>
                    <div class="layui-input-inline">
                        <input type="number" name="sort" id="sort" autocomplete="off" class="layui-input"
                               lay-verify="sort" lay-vertype="tips">
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
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
            <div class="layui-form-item form-item-my">
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
                    <button class="layui-btn" lay-submit="" id="save" lay-filter="addItem">
                        <i class="iconfont icon-save-copy"></i>保存
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
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/biz/training/case/socreItemFormController.js"></script>

</body>
</html>