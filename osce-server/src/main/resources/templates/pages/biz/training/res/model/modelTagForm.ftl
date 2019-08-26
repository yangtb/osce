<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>教学模型tab</title>
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
    </script>
</head>

<body class="body-my">
<div>
    <div class="layui-tab layui-col-xs12" lay-filter="tagTabFilter" style="margin: 0px">
        <ul class="layui-tab-title">
            <li class="layui-this">设备定义</li>
            <#if (formType == 'edit') && (fgConsumables != '1') >
                <li>设备实例</li>
            </#if>
        </ul>
        <div class="layui-tab-content" style="margin: 0px;">
            <div class="layui-tab-item layui-show">
                <form class="layui-form" id="modelForm">
                    <div hidden>
                        <input name="idDevice" id="idDevice" hidden>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">设备名称<i class="iconfont icon-required"
                                                                   style="color: #f03f2d"></i></label>
                            <div class="layui-input-inline">
                                <input type="text" name="naDevice" lay-verify="required|naDevice" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">单位<i class="iconfont icon-required"
                                                                   style="color: #f03f2d"></i></label>
                            <div class="layui-input-inline">
                                <select name="sdDeviceUnit" lay-verify="required" lay-vertype="tips">
                                    <option value="">请选择</option>
                                    <#if deviceUnitList?? && (deviceUnitList?size > 0)>
                                        <#list deviceUnitList as enum>
                                            <option value="${enum.dictCode!}">${enum.dictName!}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">设备类型</label>
                            <div class="layui-input-block">
                                <input type="radio" name="fgConsumables" value="1" title="消耗品" lay-filter="fgConsumablesFilter" checked>
                                <input type="radio" name="fgConsumables" value="0" title="非耗品" lay-filter="fgConsumablesFilter">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">库存数量</label>
                            <div class="layui-input-inline">
                                <input type="text" id="unmStock" name="unmStock" lay-verify="numCheck" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">警戒数量</label>
                            <div class="layui-input-inline">
                                <input type="text" id="numWarn" name="numWarn" lay-verify="numCheck" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">描述</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="desDevice" class="layui-textarea"
                                          autocomplete="off" lay-verify="desDevice" lay-vertype="tips"/></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">激活状态</label>
                            <div class="layui-input-inline">
                                <input type="checkbox" checked="" name="fgActive" lay-skin="switch"
                                       lay-filter="fgActiveSwitch" value="1" lay-text="激活|停用">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">排序</label>
                            <div class="layui-input-inline">
                                <input type="text" name="sort" id="sort" autocomplete="off" class="layui-input"
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

                    <div class="layui-form-item" style="padding-top: 5px">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="" lay-filter="addModel">
                                <i class="iconfont icon-save-copy"></i> 保存
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
            <#if formType == 'edit'>
                <div class="layui-tab-item">
                    <iframe id="deviceTag" class='layui-col-xs12' frameborder="0"
                            src=""></iframe>
                </div>
            </#if>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/res/model/modelTabController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    function fullForm(data) {
        $(document).ready(function(){
            $("#modelForm").autofill(data);
            layui.use('form',function(){
                if (data.fgConsumables && data.fgConsumables == '0') {
                    $('#unmStock').attr("readonly","readonly");
                    $('#unmStock').addClass("layui-disabled");
                    $('#numWarn').attr("disabled","disabled");
                    $('#numWarn').addClass("layui-disabled");
                }
                layui.form.render();

                if (data.unmStock && data.numWarn) {
                    if (parseInt(data.unmStock) < parseInt(data.numWarn)) {
                        $('#unmStock').css("color", "red");
                    }
                }
            });

        });
    }
</script>

</body>
</html>