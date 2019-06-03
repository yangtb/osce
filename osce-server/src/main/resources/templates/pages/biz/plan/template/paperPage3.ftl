<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>病史采集</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/layui/build/css/step.css">

    <script>
        var basePath = '${basePath}';
    </script>

</head>

<body>

<div>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body" style="padding-top: 5px;">
                <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">

                    <div carousel-item>
                        <div>
                            <form class="layui-form">
                                <hr>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">试卷名称<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <input type="text" placeholder="请输入试卷名称" class="layui-input"/>

                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">及格分数<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <input type="number" placeholder="请输入及格分数" class="layui-input">
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
                                            <select name="sdScoreItemCa">
                                                <option value="1">todo分类</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">病例类别<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <select name="sdScoreItemCa">
                                                <option value="1">todo分类</option>
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
                                    <div class="layui-input-inline" style="vertical-align: middle;width: 317px;">
                                        <input id="docSp" name="docSp" placeholder="请上传文件" autocomplete="off"
                                               class="layui-input layui-disabled" disabled>
                                    </div>
                                    <button type="button" class="layui-btn layui-btn-primary" id="test3">
                                        <i class="layui-icon" style="color: #009688; font-weight: bold">&#xe608;</i>上传
                                    </button>
                                    <button type="button" class="layui-btn layui-btn-primary" id="preview">
                                        <i class="iconfont icon-look1" style="color: #009688; font-weight: bold"></i> 预览
                                    </button>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" lay-submit lay-filter="formStep">
                                            &emsp;下一步 ：评分表&emsp;
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div>
                            <form class="layui-form">
                                <hr>
                                <div hidden>
                                    <input id="idSkillCase" name="idSkillCase" value="1" hidden>
                                </div>
                                <iframe id="paperTag" class='layui-col-xs12' frameborder="0" style="height: 430px;"
                                        src="${basePath}/pf/p/case/item/page?idCase=1"></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn"><i class="iconfont icon-save"></i> 完成</button>
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
                    <table id="itemTable" lay-filter="itemTableFilter">
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/paperThird.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/layui/build/js/step.js"></script>

<script>
    layui.config({
        base: basePath + '/layui/build/js/'
    }).use(['form', 'step', 'element'], function () {
        var $ = layui.$
            , form = layui.form
            , step = layui.step
            , element = layui.element;

        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '680px',
            height: '560px',
            stepItems: [{
                title: 'SP病例'
            }, {
                title: '评分表'
            }]
        });


        form.on('submit(formStep)', function (data) {
            step.next('#stepForm');
            return false;
        });

        $('.pre').click(function () {
            step.pre('#stepForm');
        });

        $('.next').click(function () {
            step.next('#stepForm');
        });
    })
</script>


</body>
</html>