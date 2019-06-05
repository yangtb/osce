<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>实训计划编辑</title>
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

    <style>
        .pageHeight {
            min-height: 1050px
        }
    </style>
</head>

<body class="body-bg">

<div class="wrapper-content-new">
    <div class="layui-fluid pageHeight">
        <div class="layui-card">
            <div class="layui-card-body" style="padding-top: 5px;">
                <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">
                    <div carousel-item>
                        <div>
                            <hr>
                            <div class="layui-card">
                                <div class="layui-card-header">卡片面板</div>
                                <div class="layui-card-body">
                                    卡片式面板面板通常用于非白色背景色的主体内<br>
                                    从而映衬出边框投影
                                </div>
                            </div>
                            <form class="layui-form">
                                <hr>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">考试模板<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <select name="sdScoreItemCa">
                                                <option value="1">todo分类</option>
                                            </select>
                                        </div>
                                        <button class="layui-btn layui-btn-normal"><i class="iconfont icon-edit"></i> 编辑模板
                                        </button>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">考试名称<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <input type="text" placeholder="请输入考试名称" class="layui-input"/>

                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">开考时间<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <input type="number" class="layui-input">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">考试类别<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <select name="sdScoreItemCa">
                                                <option value="1">普考</option>
                                                <option value="2">补考</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">补考来源<i class="iconfont icon-required"
                                                                               style="color: #f03f2d"></i></label>
                                        <div class="layui-input-inline">
                                            <select name="sdScoreItemCa">
                                                <option value="1">todo分类</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item layui-form-text">
                                    <label class="layui-form-label">考试描述</label>
                                    <div class="layui-input-block" style="width: 514px;">
                                        <textarea name="desRoomDevice" class="layui-textarea" lay-verify="desRoomDevice"></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" lay-submit lay-filter="formStep">
                                            &emsp;下一步 ：分配学员
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
                                <iframe id="assignedStudentIframe" class='layui-col-xs12' frameborder="0" style="height: 700px;"
                                        src="${basePath}/pf/p/plan/manage/assigned/student/page"></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button class="layui-btn" lay-submit lay-filter="formStep1">
                                            &emsp;下一步 ：排考预览&emsp;
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form">
                                <hr>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注说明:</label>
                                    <div class="layui-input-block">
                                        <div class="layui-form-mid layui-word-aux">备注说明</div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button class="layui-btn" lay-submit lay-filter="formStep2">
                                            下一步 ：分配SP
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form">
                                <hr>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注说明:</label>
                                    <div class="layui-input-block">
                                        <div class="layui-form-mid layui-word-aux">备注说明</div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button class="layui-btn" lay-submit lay-filter="formStep3">
                                            下一步 ：分配考官
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form">
                                <hr>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注说明:</label>
                                    <div class="layui-input-block">
                                        <div class="layui-form-mid layui-word-aux">备注说明</div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button class="layui-btn" lay-submit lay-filter="formStep4">
                                            下一步 ：领料计划
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form">
                                <hr>
                                <iframe id="assignedStudentIframe" class='layui-col-xs12' frameborder="0" style="height: 700px;"
                                        src="${basePath}/pf/p/plan/manage/tpPicking/page"></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button class="layui-btn" lay-submit lay-filter="formStep5">
                                            下一步 ：发布清单
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <hr>
                            <div style="text-align: center;margin-top: 90px;">
                                <i class="layui-icon layui-circle"
                                   style="color: white;font-size:30px;font-weight:bold;background: #52C41A;padding: 20px;line-height: 80px;">&#xe605;</i>
                                <div style="font-size: 24px;color: #333;font-weight: 500;margin-top: 30px;">
                                    入款成功
                                </div>
                                <div style="font-size: 14px;color: #666;margin-top: 20px;">预计两小时到账</div>
                            </div>
                            <div style="text-align: center;margin-top: 50px;">
                                <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                <button class="layui-btn"><i class="iconfont icon-save"></i> 发布</button>
                                <button class="layui-btn layui-btn-normal"><i class="iconfont icon-daochuExcel"></i> 导出</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/system/param/paramFormController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/layui/build/js/step.js"></script>

<script>
    layui.config({
        base: basePath + '/layui/build/js/'
    }).use([ 'form', 'step'], function () {
        var $ = layui.$
            , form = layui.form
            , step = layui.step;

        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '850px',
            height: '1000px',
            stepItems: [{
                title: '考试定义'
            }, {
                title: '分配学员'
            }, {
                title: '排考预览'
            }, {
                title: '分配SP'
            }, {
                title: '分配考官'
            }, {
                title: '领料计划'
            }, {
                title: '发布清单'
            }]
        });


        form.on('submit(formStep)', function (data) {
            step.next('#stepForm');
            return false;
        });

        form.on('submit(formStep1)', function (data) {
            step.next('#stepForm');
            return false;
        });

        form.on('submit(formStep2)', function (data) {
            step.next('#stepForm');
            return false;
        });

        form.on('submit(formStep3)', function (data) {
            step.next('#stepForm');
            return false;
        });

        form.on('submit(formStep4)', function (data) {
            step.next('#stepForm');
            return false;
        });

        form.on('submit(formStep5)', function (data) {
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