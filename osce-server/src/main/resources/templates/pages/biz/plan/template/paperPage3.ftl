<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>病史采集</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/common/css/link.css" media="all">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

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

                <div class="rebinding-box" style="height: 600px; margin: 0 auto;">
                    <div class="box-timeline">
                        <ul class="text-center" style="padding-left: 200px">
                            <li>
                                SP病例
                                <div id="step1">
                                    <div class="box-num box-num1" id="stepNum1" data-index="1">
                                        1
                                    </div>
                                </div>
                            </li>
                            <li class="ml45">
                                评分表
                                <div class="outside2a" id="step2">
                                    <div class="box-num3 num3a" id="stepNum2" data-index="2">
                                        2
                                    </div>
                                </div>
                            </li>
                            <div class="clear">
                            </div>
                        </ul>
                    </div>

                    <div id="stepDiv1" class="stepDiv">
                        <form class="layui-form" lay-filter="step1FormFilter">
                            <input id="id" name="id" hidden/>
                            <input id="idCase" name="idCase" hidden/>
                            <hr>
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">试卷名称<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="naSpCase" id="naSpCase" placeholder="请输入试卷名称" autocomplete="off"
                                               class="layui-input" lay-verify="required" lay-vertype="tips"/>

                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">及格分数<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input type="number" name="scorePass" placeholder="请输入及格分数"  autocomplete="off"
                                               class="layui-input" lay-verify="required" min="0" lay-vertype="tips">
                                    </div>
                                </div>

                            </div>
                            <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px;">
                                <legend style="font-size: 14px; font-weight: bold">选择病例</legend>
                            </fieldset>
                            <div class="layui-form-item form-item-my">
                                <div class="layui-inline">
                                    <label class="layui-form-label">选择病例</label>
                                    <div class="layui-input-inline">
                                        <input id="idFrom" name="idFrom" hidden>
                                        <input type="text" name="naSpCaseFrom" id="naSpCaseFrom" autocomplete="off"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">病例类别<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <select name="sdSpCaseCa">
                                            <#if sdStationCaList?? && (sdStationCaList?size > 0)>
                                                <#list sdStationCaList as enum>
                                                    <option value="${enum.dictCode!}">${enum.dictName!}</option>
                                                </#list>
                                            </#if>
                                        </select>
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
                                <div class="layui-input-inline" style="vertical-align: middle;width: 225px;">
                                    <input id="docSp" name="docSp" placeholder="请上传文件" autocomplete="off"
                                           class="layui-input">
                                </div>
                                <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                                    <i class="layui-icon">&#xe681;</i>上传
                                </button>
                                <button type="button" class="layui-btn layui-btn-primary" id="preview">
                                    <i class="iconfont icon-detail"></i> 预览
                                </button>
                                <button type="button" class="layui-btn layui-btn-primary" id="downLoadDocSp">
                                    <i class="layui-icon">&#xe601;</i>下载
                                </button>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label"></label>
                                <div class="layui-inline layui-word-aux">
                                    支持格式：doc,docx,jpg,jpeg,png,mp3,wav,ogg,mp4,avi,wmv,3gp,mkv,f4v,rmvb
                                </div>
                            </div>


                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button id="addSpCase" class="layui-btn" lay-submit lay-filter="formStep">
                                        &emsp;下一步 ：评分表&emsp;
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

                    <div id="stepDiv2" class="stepDiv" style="display: none">
                        <form class="layui-form">
                            <hr>
                            <iframe id="scoreTag" class='layui-col-xs12' frameborder="0" style="height: 430px;"></iframe>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button type="button" class="layui-btn layui-btn-primary pre" data-index="1">上一步</button>
                                    <button id="finish-btn" class="layui-btn"><i class="iconfont icon-save"></i> 完成</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div>
                    <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px;">
                        <legend style="font-size: 14px; font-weight: bold">技能列表</legend>
                    </fieldset>
                    <form class="layui-form">
                        <div class="layui-inline">
                            <button type="button" class="layui-btn layui-btn-sm" id="addSp">
                                <i class="layui-icon layui-icon-add-1"></i>增加
                            </button>
                            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="delSp">
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
<script src="${contextPath}/biz/js/biz/plan/template/paperThird.js"></script>

<script type="text/html" id="fgActiveTpl">
    <input type="checkbox" name="fgActive" value="{{d.id}}"
           lay-skin="switch" lay-text="NO|OFF" lay-filter="fgActiveCheckFilter" {{ d.fgActive== '1' ? 'checked' : '' }}>
</script>

<script type="text/html" id="spBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="preview" style="margin-left: 0px;"><i class="layui-icon layui-icon-search"></i>预览</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" style="margin-left: 0px;"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-danger" lay-event="del" style="margin-left: 0px;"><i class="layui-icon layui-icon-delete"></i>删除</a>
</script>

</body>
</html>