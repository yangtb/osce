<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>技能操作</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/layui/build/css/step.css">

    <script>
        var basePath = '${basePath}';
        var idModel = '${idModel!}';
    </script>

</head>

<body>

<div>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body" style="padding-top: 5px;">
                <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">

                    <div carousel-item>
                        <div class="step-skip">
                            <form class="layui-form" lay-filter="step1FormFilter">
                                <input id="id" name="id" hidden/>
                                <input id="idSkillCase" name="idSkillCase" hidden/>
                                <hr>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">试卷名称<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="naSkillCase" id="naSkillCase" placeholder="请输入试卷名称" autocomplete="off"
                                               class="layui-input" lay-verify="required" lay-vertype="tips"/>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">及格分数<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input type="number" name="scorePass" placeholder="请输入及格分数"  autocomplete="off"
                                               class="layui-input" lay-verify="required" min="0" lay-vertype="tips">
                                    </div>
                                </div>
                                <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px;">
                                    <legend style="font-size: 14px; font-weight: bold">选择病例</legend>
                                </fieldset>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">选择病例</label>
                                        <div class="layui-input-inline">
                                            <input id="idFrom" name="idFrom" hidden>
                                            <input type="text" name="naSkillCaseFrom" id="naSkillCaseFrom" autocomplete="off"
                                                   autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">病例类别<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <select name="sdSkillCaseCa">
                                                <#if sdStationCaList?? && (sdStationCaList?size > 0)>
                                                    <#list sdStationCaList as enum>
                                                        <option value="${enum.dictCode!}">${enum.dictName!}</option>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item layui-form-text">
                                    <label class="layui-form-label">病例描述</label>
                                    <div class="layui-input-block">
                                        <textarea name="desSkillCase" class="layui-textarea" lay-verify="desSkillCase"></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button id="addSkillCase" class="layui-btn" lay-submit lay-filter="formStep">
                                            下一步 ：站点配置
                                        </button>
                                    </div>
                                    <div hidden>
                                        <button id="reset" type="reset" class="layui-btn layui-btn-danger" hidden>
                                            <i class="iconfont icon-reset"></i> 重新填写
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="step-skip">
                            <form class="layui-form">
                                <hr>
                                <div hidden>
                                    <input id="idSkillCase" name="idSkillCase" value="1" hidden>
                                </div>
                                <iframe id="zdTag" class='layui-col-xs12' frameborder="0" style="height: 400px;"
                                        src="${basePath}/pf/p/plan/device/page?idSkillCase=1"></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button class="layui-btn" lay-submit lay-filter="formStep1">下一步 ：评分表</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="step-skip">
                            <form class="layui-form">
                                <hr>
                                <div hidden>
                                    <input id="idSkillCase" name="idSkillCase" value="1" hidden>
                                </div>
                                <iframe id="scoreTag" class='layui-col-xs12' frameborder="0" style="height: 400px;"></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button id="finish-btn" class="layui-btn"><i class="iconfont icon-save"></i> 完成</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
                <div>
                    <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px;">
                        <legend style="font-size: 14px; font-weight: bold">技能列表</legend>
                    </fieldset>
                    <form class="layui-form">
                        <div class="layui-inline">
                            <button type="button" class="layui-btn layui-btn-sm" id="add">
                                <i class="iconfont icon-add"></i> 增加
                            </button>
                            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="del">
                                <i class="iconfont icon-batch-del"></i> 删除
                            </button>
                        </div>
                    </form>
                    <table id="skillTable" lay-filter="skillTableFilter">
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/paperTwo.js"></script>

<script type="text/html" id="skillBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="iconfont icon-edit"></i></a>
    <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i></a>
</script>

</body>
</html>