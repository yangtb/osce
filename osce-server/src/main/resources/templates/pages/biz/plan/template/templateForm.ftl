<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>实训模板编辑</title>
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
                            <form class="layui-form">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">游戏ID:</label>
                                    <div class="layui-input-block">
                                        <input type="text" placeholder="请填写入款人游戏ID" class="layui-input" lay-verify="number" required />

                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">入款金额:</label>
                                    <div class="layui-input-block">
                                        <input type="number" placeholder="请填写入款金额" value="" class="layui-input" lay-verify="number" required>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">入款类型:</label>
                                    <div class="layui-input-block">
                                        <select lay-verify="required">
                                            <option value="1" selected>保险箱</option>
                                            <option value="2">现金</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">入款方式:</label>
                                    <div class="layui-input-block">
                                        <select lay-verify="required">
                                            <option value="1" selected>人工入款</option>
                                            <option value="2">修正</option>
                                            <option value="3">活动</option>
                                            <option value="4">佣金</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注说明:</label>
                                    <div class="layui-input-block">
                                        <textarea placeholder="入款备注" value="" class="layui-textarea"></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" lay-submit lay-filter="formStep">
                                            &emsp;下一步&emsp;
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form" style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">游戏ID:</label>
                                    <div class="layui-input-block">
                                        <div class="layui-form-mid layui-word-aux">639537</div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">账户余额:</label>
                                    <div class="layui-input-block">
                                        <div class="layui-form-mid layui-word-aux">3000 元（保险箱：1000，现金：2000）</div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">入款金额:</label>
                                    <div class="layui-input-block">
                                        <div class="layui-form-mid layui-word-aux">
                                            <span style="font-size: 18px;color: #333;">1800 元</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">入款类型:</label>
                                    <div class="layui-input-block">
                                        <div class="layui-form-mid layui-word-aux">保险箱</div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">入款方式:</label>
                                    <div class="layui-input-block">
                                        <div class="layui-form-mid layui-word-aux">人工入款</div>
                                    </div>
                                </div>
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
                                            &emsp;确认入款&emsp;
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <div style="text-align: center;margin-top: 90px;">
                                <i class="layui-icon layui-circle"
                                   style="color: white;font-size:30px;font-weight:bold;background: #52C41A;padding: 20px;line-height: 80px;">&#xe605;</i>
                                <div style="font-size: 24px;color: #333;font-weight: 500;margin-top: 30px;">
                                    入款成功
                                </div>
                                <div style="font-size: 14px;color: #666;margin-top: 20px;">预计两小时到账</div>
                            </div>
                            <div style="text-align: center;margin-top: 50px;">
                                <button class="layui-btn next">再入一笔</button>
                                <button class="layui-btn layui-btn-primary">查看账单</button>
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
            stepWidth: '800px',
            height: '1000px',
            stepItems: [{
                title: '考站定义'
            }, {
                title: '排站'
            }, {
                title: '模拟排考'
            }]
        });


        form.on('submit(formStep)', function (data) {
            step.next('#stepForm');
            return false;
        });

        form.on('submit(formStep2)', function (data) {
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