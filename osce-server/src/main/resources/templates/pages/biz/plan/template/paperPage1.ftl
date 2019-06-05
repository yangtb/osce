<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>理论试题</title>
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
        @charset "utf-8";
        .clearfix {
            *zoom: 1
        }

        .clearfix:after {
            display: block;
            overflow: hidden;
            clear: both;
            height: 0;
            visibility: hidden;
            content: ""
        }

        .clear {
            clear: both
        }

        .left {
            float: left
        }

        .right {
            float: right
        }

        .f-w700 {
            width: 700px
        }

        .f-range {
            height: 20px;
            width: 700px;
            position: relative;
            background: #70baff
        }

        .f-hk {
            position: absolute;
            top: -3px;
            height: 26px;
            width: 8px;
            z-index: 1;
            cursor: move
        }

        .f-hk1 {
            left: 0;
            background-image: url(${basePath}/biz/img/huakuai/hk1.png);
            background-repeat: no-repeat;
            background-size: cover
        }

        .f-hk2 {
            left: 8px;
            background-image: url(${basePath}/biz/img/huakuai/hk2.png);
            background-repeat: no-repeat;
            background-size: cover
        }

        .f-hk3 {
            left: 16px;
            background-image: url(${basePath}/biz/img/huakuai/hk3.png);
            background-repeat: no-repeat;
            background-size: cover
        }

        .f-hk4 {
            left: 24px;
            background-image: url(${basePath}/biz/img/huakuai/hk4.png);
            background-repeat: no-repeat;
            background-size: cover
        }

        .f-rangeArea {
            margin-top: 10px;
            color: #333;
            font-size: 16px;
            width: 700px
        }

        .f-mt60 {
            margin-top: 1px
        }

        .f-color {
            border-radius: 50%;
            width: 10px;
            height: 10px;
            display: inline-block;
            position: relative;
            top: 7px;
            *top: -7px
        }

        .f-color1 {
            background-color: #2fb9c5
        }

        .f-color2 {
            background-color: #e3b21e
        }

        .f-color3 {
            background-color: #a246db
        }

        .f-color4 {
            background-color: #db5d44
        }

        .f-color5 {
            background-color: red;
        }

        .f-range-tips {
            display: none;
            color: #fff;
            width: 35px;
            font-size: 12px;
            height: 22px;
            line-height: 17px;
            z-index: 2;
            position: relative;
            top: -30px;
            left: -15px;
            text-align: center;
            background-image: url(${basePath}/biz/img/huakuai/rangeNum.png);
            background-size: cover;
            background-repeat: no-repeat
        }

        .f-rangeArea {
            margin-bottom: 10px
        }

        .f-range-msg {
            /*margin-top: 10px;*/
            font-size: 16px;
            text-align: left;
            color: #666;
            float: left
        }

        .f-color-text {
            display: inline-block;
            margin-left: 10px;
        }

        .f-color-area {
            display: inline-block;
            color: #ff7916;
            padding-right: 20px;
            width: 76px;
        }

        .f-mb40 {
            margin-bottom: 5px
        }
    </style>
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
                                    <label class="layui-form-label">试卷名称<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input type="text" placeholder="请输入试卷名称" class="layui-input"/>

                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">及格分数<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input type="number" placeholder="请输入及格分数" class="layui-input">
                                    </div>
                                </div>
                                <fieldset class="layui-elem-field layui-field-title">
                                    <legend style="font-size: 14px; font-weight: bold">选择题集</legend>
                                </fieldset>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">选择题集</label>
                                        <div class="layui-input-inline">
                                            <select name="sdScoreItemCa">
                                                <option value="1">todo分类</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">题集范围</label>
                                    <div class="layui-input-block">
                                        <input type="checkbox" name="like1[write]" title="题库试题"
                                               checked="">
                                        <input type="checkbox" name="like1[read]" title="私有试题">
                                        <input type="checkbox" name="like1[game]" title="导入试题">
                                        <button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                                                id="importItem">
                                            &nbsp;<i class="iconfont icon-add"></i> 导入试题&nbsp;
                                        </button>
                                        <button id="importItemHidden" type="button" class="layui-btn layui-btn-normal"
                                                lay-href="${contextPath!}/pf/p/item/page" style="display: none">题库管理
                                        </button>
                                    </div>

                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">选择目录</label>
                                        <div class="layui-input-inline">
                                            <select name="sdScoreItemCa">
                                                <option value="1">todo分类</option>
                                            </select>
                                        </div>
                                        <button class="layui-btn layui-btn-normal layui-btn-radius">
                                            &nbsp;<i class="iconfont icon-add"></i> 选择&nbsp;
                                        </button>
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">排除条件</label>
                                    <div class="layui-input-block">
                                        <input type="radio" name="sex" value="男" title="不排除以前使用过的试题" checked="">
                                        <input type="radio" name="sex" value="女" title="排除以前使用过的试题">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" lay-submit lay-filter="formStep">
                                            下一步 ：试卷参数
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form">
                                <hr>
                                <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px">
                                    <legend style="font-size: 14px; font-weight: bold;">选择条件</legend>
                                </fieldset>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">选择题型</label>
                                    <div class="layui-input-block">
                                        <input type="checkbox" name="like1[write]" lay-skin="primary" title="A1"
                                               checked="">
                                        <input type="checkbox" name="like1[read]" lay-skin="primary" title="A2">
                                        <input type="checkbox" name="like1[game]" lay-skin="primary" title="B1">
                                    </div>
                                </div>

                                <table lay-even class="layui-table" style="text-align: center;margin-top: -10px;">
                                    <colgroup style="text-align: center">
                                        <col>
                                        <col width="30%">
                                        <col width="30%">
                                        <col width="30%">
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th style="text-align: center; font-weight: bold;">题型</th>
                                        <th style="text-align: center; font-weight: bold;">A1</th>
                                        <th style="text-align: center; font-weight: bold;">A2</th>
                                        <th style="text-align: center; font-weight: bold;">A3</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>数量</td>
                                        <td><input style="width: 50px; height: 20px;"/> / 800</td>
                                        <td><input style="width: 50px; height: 20px;"/> / 500</td>
                                        <td><input style="width: 50px; height: 20px;"/> / 100</td>
                                    </tr>
                                    <tr>
                                        <td>分值</td>
                                        <td><input style="width: 50px; height: 20px;"/></td>
                                        <td><input style="width: 50px; height: 20px;"/></td>
                                        <td><input style="width: 50px; height: 20px;"/></td>
                                    </tr>
                                    </tbody>
                                </table>


                                <fieldset class="layui-elem-field layui-field-title">
                                    <legend style="font-size: 14px; font-weight: bold">选择难易程度比例</legend>
                                </fieldset>

                                <div class="layui-form-item" style="padding-left: 25px">
                                    <div class="left f-mt60">
                                        <div class="f-mb40">
                                            <p class="f-range-msg">
                                                <span class="f-color f-color1">&nbsp;</span>
                                                <span class="f-color-text">易</span>
                                                <span class="f-color-area">
                                                    <span class="f-valMax">82%</span>
                                                </span>
                                            </p>
                                            <p class="f-range-msg">
                                                <span class="f-color f-color2">&nbsp;</span>
                                                <span class="f-color-text">较易</span>
                                                <span class="f-color-area">
                                                    <span class="f-valMax">82%</span>
                                                </span>
                                            </p>
                                            <#--<div class="clear"></div>-->
                                            <p class="f-range-msg">
                                                <span class="f-color f-color3">&nbsp;</span>
                                                <span class="f-color-text">中</span>
                                                <span class="f-color-area">
                                                    <span class="f-valMax">65%</span>
                                                </span>
                                            </p>
                                            <p class="f-range-msg">
                                                <span class="f-color f-color4">&nbsp;</span>
                                                <span class="f-color-text">较难</span>
                                                <span class="f-color-area">
                                                    <span class="f-valMax">44%</span>
                                                </span>
                                            </p>
                                            <p class="f-range-msg">
                                                <span class="f-color f-color5">&nbsp;</span>
                                                <span class="f-color-text">难</span>
                                                <span class="f-color-area">
                                                    <span class="f-valMax">44%</span></span>
                                            </p>
                                            <div class="clear"></div>
                                        </div>
                                        <div class="f-range">
                                            <div class="f-hk f-hk1" src="${basePath}/biz/img/huakuai/hk1.png"
                                                 style="left: 84px;"><span
                                                        class="f-range-tips" style="display: none;">83%</span></div>
                                            <div class="f-hk f-hk2" src="${basePath}/biz/img/huakuai/hk2.png"
                                                 style="left: 173px;"><span
                                                        class="f-range-tips" style="display: none;">66%</span></div>
                                            <div class="f-hk f-hk3" src="${basePath}/biz/img/huakuai/hk3.png"
                                                 style="left: 282px;"><span
                                                        class="f-range-tips" style="display: none;">46%</span></div>
                                            <div class="f-hk f-hk4" src="${basePath}/biz/img/huakuai/hk4.png"
                                                 style="left: 409px;"><span
                                                        class="f-range-tips" style="display: none;">10%</span></div>
                                        </div>

                                        <#--<p class="f-rangeArea clearfix">
                                            <span class="left">100%</span>
                                            <span class="right">0%</span>
                                        </p>-->

                                    </div>
                                </div>

                                <div class="layui-form-item" style="margin-top: 50px;">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button class="layui-btn" lay-submit lay-filter="formStep2">下一步 ：设置必考题</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form">
                                <table id="itemTable1" lay-filter="itemTableFilter1">
                                </table>
                                <div style="text-align: center;margin-top: 5px;">
                                    <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                    <button class="layui-btn" lay-submit lay-filter="formStep3">下一步 ：生成试卷</button>

                                </div>
                            </form>
                        </div>
                        <div>
                            <table id="itemTable2" lay-filter="itemTableFilter2">
                            </table>
                            <div style="text-align: center;margin-top: 5px;">
                                <#--<button class="layui-btn layui-btn-primary next">重来一次</button>-->
                                <button class="layui-btn layui-btn-normal">
                                    <i class="iconfont icon-shengcheng"></i> 生成试卷
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px;">
                        <legend style="font-size: 14px; font-weight: bold">试卷列表</legend>
                    </fieldset>
                    <table id="itemTable" lay-filter="itemTableFilter">
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/paperOne.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>

<script>
    var index, maxLeft;
    $(function () {
        //range值提示
        $(".f-hk").mouseenter(function () {
            $(this).find(".f-range-tips").css("display", "block");
        });
        $(".f-hk").mouseleave(function () {
            $(this).find(".f-range-tips").css("display", "none");
        });

        //拖动开始X值
        var startX, preLeft, minLeft;
        var v_index = new Array()
        var handle = false;

        $(".f-hk").mousedown(function (e) {
            //拖动开始的X坐标
            startX = e.pageX;
            //判断是否拖动的变量
            handle = true;
            index = $(".f-hk").index(this);
            //获取滑块下标
            preLeft = parseInt($(".f-hk").eq(index).css("left"));
            //获取滑块最左的值
            minLeft = parseInt($(".f-hk").eq(index - 1).css("left")) + 8;
            console.log("startX:" + startX + ', index:' + index + ', preLeft:' + preLeft + ', minLeft' + minLeft)
        })

        $(document).mousemove(function (e) {
            e.stopPropagation();
            //是否点击滑块
            if (handle) {
                //显示提示值
                $(".f-hk").eq(index).find(".f-range-tips").css("display", "block");
                //是否第一个
                if (index == 0) {
                    //是否最后一个
                    if (index != $(".f-hk").length - 1) {
                        maxLeft = parseInt($(".f-hk").eq(index + 1).css("left")) - 8;
                    } else {
                        maxLeft = 700;
                    }

                    var newLeft = e.pageX - startX + preLeft;
                    //设置边界
                    if (newLeft > maxLeft) {
                        newLeft = maxLeft;
                    }
                    if (newLeft < 0) {
                        newLeft = 0;
                    }
                    //执行拖动
                    $(".f-hk").eq(index).css("left", newLeft);
                    //动态改变提示的值
                    var myVal = parseInt((1 - (parseFloat($(".f-hk").eq(index).css("left")) - 8 * (index)) / (700 - 8 * (index + ($(".f-hk ").length - index - 1)))) * 100);
                    $(".f-hk").eq(index).find(".f-range-tips").html(myVal);
                    //改变 信息表最小值
                    v_index[index] = myVal;
                    console.log(index + "----" + v_index[index])
                    $(".f-range-msg").eq(index).find(".f-valMax").html((100 - myVal) + "%");
                    //$(".f-range-msg").eq(index).find(".f-valMax").html(myVal);
                    //改变信息表最大值
                    if (index != $(".f-hk ").length - 1) {
                        var max = $(".f-range-msg").eq(index + 1).find(".f-valMax").html(v_index[0] - v_index[1] + "%");
                    }
                } else {
                    //是否最后一个
                    if (index != $(".f-hk").length - 1) {
                        maxLeft = parseFloat($(".f-hk").eq(index + 1).css("left")) - 8;
                    } else {
                        maxLeft = 700;
                    }

                    var newLeft = e.pageX - startX + preLeft;
                    //设置边界
                    if (newLeft > maxLeft) {
                        newLeft = maxLeft;
                    }
                    if (newLeft < minLeft) {
                        newLeft = minLeft;
                    }

                    //执行拖动
                    $(".f-hk").eq(index).css("left", newLeft);
                    //动态改变提示的值
                    var myVal = parseInt((1 - (parseFloat($(".f-hk").eq(index).css("left")) - 8 * (index)) / (700 - 8 * (index + ($(".f-hk ").length - index - 1)))) * 100);
                    console.log("index====" + index)
                    v_index[index] = myVal;
                    $(".f-hk").eq(index).find(".f-range-tips").html(myVal + "%");

                    console.log(v_index[0] + "----" + v_index[1]+ "----" + v_index[2]+ "----" + v_index[3])
                    //改变信息表最小值
                    $(".f-range-msg").eq(index).find(".f-valMax").html(v_index[index - 1] - v_index[index] + "%");
                    //改变信息表最大值
                    if (index == $(".f-hk ").length - 1) {
                        $(".f-range-msg").eq(index + 1).find(".f-valMax").html(v_index[index] + "%");
                    } else {
                        $(".f-range-msg").eq(index + 1).find(".f-valMax").html(v_index[index] - v_index[index + 1] + "%");
                    }
                }


            }
        })
        $(document).mouseup(function () {
            handle = false;
            //隐藏值
            $(".f-range-tips").css("display", "none");
        })

        //初始化
        for (var i = 0; i < $(".f-hk").length; i++) {
            //获取百分比
            var getVal = parseInt($(".f-hk").eq(i).find(".f-range-tips").html());
            var totalWidth = 700 - 8 * (i + 4 - 1 - i);
            var setLeft = parseInt((1 - getVal / 100) * (totalWidth)) + 8 * (i);
            //初始化left值
            $(".f-hk").eq(i).css("left", setLeft);
            v_index[i] = getVal;

            //初始化最小值
            var v_set;
            if (i == 0) {
                v_set = 100 - v_index[i];
            } else {
                v_set = v_index[i - 1] - v_index[i];
            }
            $(".f-range-msg").eq(i).find(".f-valMax").html(v_set + "%");
            //初始化最大值
            if (i == $(".f-hk").length - 1) {
                $(".f-range-msg").eq(i + 1).find(".f-valMax").html(v_index[i] + "%");
            }

        }

    })
</script>


</body>
</html>