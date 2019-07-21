<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>病例管理tab</title>
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

<body style="overflow-x: hidden">
<div>
    <div class="layui-tab layui-col-xs12" lay-filter="tagTabFilter" style="margin: 0px">
        <ul class="layui-tab-title">
            <li class="layui-this">病例定义</li>
            <#if formType == 'edit'>
                <li>评分表</li>
            </#if>
        </ul>
        <div class="layui-tab-content" style="margin: 0px;">
            <div class="layui-tab-item layui-show">
                <form class="layui-form" id="spCaseForm">
                    <div hidden>
                        <input name="idCase" id="idCase" hidden>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">病例名称<i class="iconfont icon-required"
                                                                   style="color: #f03f2d"></i></label>
                            <div class="layui-input-inline">
                                <input type="text" name="naSpCase" lay-verify="required|commonLength64" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">病例类别<i class="iconfont icon-required"
                                                                 style="color: #f03f2d"></i></label>
                            <div class="layui-input-inline">
                                <select name="sdSpCaseCa" lay-verify="required" lay-vertype="tips">
                                    <option value="">请选择</option>
                                    <option value="1">内科</option>
                                    <option value="2">外科</option>
                                    <option value="3">妇科</option>
                                    <option value="4">儿科</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">病例简述</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="desSpCase" class="layui-textarea"
                                          autocomplete="off" lay-verify="commonLength255" lay-vertype="tips"/></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">患者主诉</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <input type="text" name="desProb" lay-verify="commonLength255" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">现病史</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <input type="text" name="desCurDie" lay-verify="commonLength255" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">既往史</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <input type="text" name="desHisDie" lay-verify="commonLength255" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">个人史</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <input type="text" name="desPatDie" lay-verify="commonLength255" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">家族史</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <input type="text" name="desFamDie" lay-verify="commonLength255" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">表演要点</label>
                            <div class="layui-input-inline" style="width: 514px;">
                                <input type="text" name="desPoints" lay-verify="commonLength255" lay-vertype="tips"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">剧本</label>
                        <div class="layui-input-inline" style="vertical-align: middle;width: 317px;">
                            <input id="docSp" name="docSp" placeholder="请上传文件" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                            <i class="layui-icon" style="color: #009688; font-weight: bold">&#xe608;</i>上传
                        </button>
                        <button type="button" class="layui-btn layui-btn-primary" id="preview">
                            <i class="iconfont icon-look1" style="color: #009688; font-weight: bold"></i> 预览
                        </button>
                    </div>

                    <div class="layui-form-item form-item-my">
                        <div class="layui-inline">
                            <label class="layui-form-label">激活状态</label>
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

                    <div class="layui-form-item" style="padding-top: 5px">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="" lay-filter="addItem">
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
                    <iframe id="scoreSheetTag" class='layui-col-xs12' frameborder="0" src=""></iframe>
                </div>
            </#if>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/training/case/caseTabController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

</body>
</html>