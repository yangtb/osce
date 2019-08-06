<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增题目</title>
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
        var idItemStore = '${idItemStore!}';
        var idItemSection = '${idItemSection!}';
    </script>

    <style>
        .div-left{ float:left;width:645px;padding-top: 10px;}
        .div-right{ float:left;width:370px; margin-left: -10px;}
    </style>
</head>

<body>
<div>
    <div class="div-left">
        <form class="layui-form" id="itemForm">
            <div hidden>
                <input name="idItem" id="idItem" hidden>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">目录<i class="iconfont icon-required"
                                                         style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline">
                        <select name="idItemSection" lay-verify="required" lay-vertype="tips">
                            <option value="">请选择</option>
                            <#if idItemStoreList?? && (idItemStoreList?size > 0)>
                                <#list idItemStoreList as element>
                                    <option value="${element.idItemSection!}">${element.naItemSection!}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="padding-left:40px; width: 230px; float: right">
                        <#--<input type="radio" name="sdItemFrom" value="1" title="题库试题" checked>
                        <input type="radio" name="sdItemFrom" value="2" title="私有试题">-->
                        <select name="sdItemFrom" lay-verify="required" lay-vertype="tips">
                            <option value="1">题库试题</option>
                            <option value="2">私有试题</option>
                            <option value="3">导入试题</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">题目类型<i class="iconfont icon-required"
                                                           style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline">
                        <select name="sdItemCa" lay-verify="required" lay-vertype="tips">
                            <option value="1">A1</option>
                            <option value="2">A2</option>
                            <option value="3">B1</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">题目难度</label>
                    <div class="layui-input-inline">
                        <select name="sdItemLevel" lay-verify="required" lay-vertype="tips">
                            <option value="1">易</option>
                            <option value="2">较易</option>
                            <option value="3">中</option>
                            <option value="4">难</option>
                            <option value="5">较难</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">默认分值</label>
                    <div class="layui-input-inline">
                        <input class="layui-input layui-input-number" min="1" step="1"
                               name="scoreDefault"  autocomplete="off" lay-vertype="tips">
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">题干<i class="iconfont icon-required"
                                                         style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="mainItem" class="layui-textarea"
                                          autocomplete="off" lay-verify="required|commonLength255" lay-vertype="tips"/></textarea>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">题目解析</label>
                    <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="itemAnalysis" class="layui-textarea"
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
            </div>


            <div class="layui-form-item" style="text-align: right; padding-top: 5px;">
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

    <div class="div-right">
        <form class="layui-form" style="padding-top: 10px;">
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-sm" id="addOption">
                    <i class="layui-icon layui-icon-add-1"></i>增加
                </button>
                <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="delOption">
                    <i class="layui-icon layui-icon-delete"></i>删除
                </button>
            </div>
        </form>
        <table id="itemOptionTable" lay-filter="itemOptionTableFilter">
        </table>
    </div>
</div>

<script src="${contextPath}/layui/plugins/layui/layui.all.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/biz/training/item/td/itemFormController.js"></script>



<script type="text/html" id="fgRightTpl">
    <input type="checkbox" name="fgRight" value="{{ d.LAY_INDEX }}"
           lay-skin="switch" lay-text="正确|错误" lay-filter="fgRightCheckFilter" {{ d.fgRight== '1' ? 'checked' : '' }}>
</script>


</body>
</html>