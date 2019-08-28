<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>理论试题</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/common/css/link.css" media="all">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <#--<link rel="stylesheet" type="text/css" href="${contextPath}/layui/build/css/step.css">-->
    <link rel="stylesheet" href="${contextPath}/layui/expand/css/formSelects-v4.css">

    <script>
        var basePath = '${basePath}';
        var idModel = '${idModel!}';
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
            background: #1E9FFF
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

        .total-color {
            color: #1E9FFF;
        }
    </style>

    </style>


    </style
</head>

<body>

<div>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body" style="padding-top: 5px;">

                <div class="rebinding-box" style="height: 520px; margin: 0 auto;">
                    <div class="box-timeline" >
                        <ul class="text-center" style="padding-left: 200px">
                            <li>
                                选择题集
                                <div id="step1">
                                    <div class="box-num box-num1" id="stepNum1" data-index="1">
                                        1
                                    </div>
                                </div>
                            </li>
                            <li class="ml45">
                                试卷参数
                                <div class="outside0ab" id="step2">
                                    <div class="box-num2 num2ab" id="stepNum2" data-index="2">
                                        2
                                    </div>
                                </div>
                            </li>
                            <li class="ml45">
                                设置必考题
                                <div class="outside0ab" id="step3">
                                    <div class="box-num2 num2ab" id="stepNum3" data-index="3">
                                        3
                                    </div>
                                </div>
                            </li>
                            <li class="ml45">
                                生成试卷
                                <div class="outside2a" id="step4">
                                    <div class="box-num3 num3a" id="stepNum4" data-index="4">
                                        4
                                    </div>
                                </div>
                            </li>
                            <div class="clear">
                            </div>
                        </ul>
                    </div>

                    <div id="stepDiv1" class="stepDiv">
                        <form class="layui-form" id="stepForm0" lay-filter="step1FormFilter">
                            <input id="idItemStore" name="idItemStore" hidden/>
                            <hr>
                            <div class="layui-form-item">
                                <label class="layui-form-label">试卷名称<i class="iconfont icon-required"
                                                                       style="color: #f03f2d"></i></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="naItemStore" id="naItemStore" placeholder="请输入试卷名称" autocomplete="off"
                                           class="layui-input" lay-verify="required" lay-vertype="tips"/>

                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">及格分数<i class="iconfont icon-required"
                                                                       style="color: #f03f2d"></i></label>
                                <div class="layui-input-inline">
                                    <input type="number" name="scorePass" id="scorePass" placeholder="请输入及格分数"  autocomplete="off"
                                           class="layui-input" lay-verify="required" min="0" lay-vertype="tips">
                                </div>
                            </div>
                            <fieldset class="layui-elem-field layui-field-title">
                                <legend style="font-size: 14px; font-weight: bold">选择题集</legend>
                            </fieldset>
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">选择题集</label>
                                    <div class="layui-input-inline">
                                        <input id="idItemStoreFrom" name="idItemStoreFrom" hidden>
                                        <input type="text" name="naItemStoreFrom" id="naItemStoreFrom" autocomplete="off"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">题集范围</label>
                                <div class="layui-input-block" id="tjfw">
                                    <input type="checkbox" name="fgItemFromPublic" title="题库试题">
                                    <input type="checkbox" name="fgItemFromPrivate" title="私有试题">
                                    <input type="checkbox" name="fgItemFromImport" title="导入试题" lay-filter="fgItemFromImportFilter">
                                    <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-btn-disabled"
                                            id="importItem" disabled>
                                        &nbsp;<i class="iconfont icon-add"></i> 导入试题&nbsp;
                                    </button>
                                    <button id="importItemHidden" type="button" class="layui-btn layui-btn-normal"
                                            lay-href="" style="display: none">题库管理
                                    </button>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">排除条件</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="fgChooseHist" value="0" title="不排除以前使用过的试题" checked="">
                                    <input type="radio" name="fgChooseHist" value="1" title="排除以前使用过的试题">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit lay-filter="formStep" id="addTdItemStore">
                                        下一步 ：试卷参数
                                    </button>
                                    <div hidden>
                                        <button id="reset" type="reset" class="layui-btn layui-btn-danger" hidden>
                                            <i class="iconfont icon-reset"></i> 重新填写
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>

                    <div id="stepDiv2" class="stepDiv" style="display: none">
                        <form class="layui-form" lay-filter="step2FormFilter">
                            <hr>
                            <div class="layui-form-item" style="margin-bottom: -5px;">
                                <div class="layui-inline" style="width: 650px;">
                                    <label class="layui-form-label">选择目录</label>
                                    <div class="layui-input-block" id="idItemSectionTips">
                                        <select name="idItemSection" id="idItemSection" xm-select="select1">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 0px">
                                <legend style="font-size: 14px; font-weight: bold;">选择条件</legend>
                            </fieldset>

                            <div class="layui-form-item">
                                <label class="layui-form-label">选择题型</label>
                                <div class="layui-input-block" id="sdItemCaTips">
                                    <input type="checkbox" name="sdItemCa_A1" lay-skin="primary" title="A1" lay-filter="switch_A1">
                                    <input type="checkbox" name="sdItemCa_A2" lay-skin="primary" title="A2" lay-filter="switch_A2">
                                    <input type="checkbox" name="sdItemCa_B1" lay-skin="primary" title="B1" lay-filter="switch_B1">
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
                                    <th style="text-align: center; font-weight: bold;">B1</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr hidden>
                                    <td>题型参数ID</td>
                                    <td><input id="idItemArgType_A1" name="idItemArgType_A1" disabled/></td>
                                    <td><input id="idItemArgType_A2" name="idItemArgType_A2" disabled/></td>
                                    <td><input id="idItemArgType_B1" name="idItemArgType_B1" disabled/></td>
                                </tr>
                                <tr>
                                    <td>数量</td>
                                    <td><input style="width: 50px; height: 20px;" id="numType_A1" name="numType_A1" autocomplete="off" disabled/> / <span id="total_1" class="total-color"></span></td>
                                    <td><input style="width: 50px; height: 20px;" id="numType_A2" name="numType_A2" autocomplete="off" disabled/> / <span id="total_2" class="total-color"></span></td>
                                    <td><input style="width: 50px; height: 20px;" id="numType_B1" name="numType_B1" autocomplete="off" disabled/> / <span id="total_3" class="total-color"></span></td>
                                </tr>
                                <tr>
                                    <td>分值</td>
                                    <td><input style="width: 50px; height: 20px;" id="scoreType_A1" name="scoreType_A1" autocomplete="off" disabled/></td>
                                    <td><input style="width: 50px; height: 20px;" id="scoreType_A2" name="scoreType_A2" autocomplete="off" disabled/></td>
                                    <td><input style="width: 50px; height: 20px;" id="scoreType_B1" name="scoreType_B1" autocomplete="off" disabled/></td>
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
                                                    <input id="idItemArgLevel_1" name="idItemArgLevel_1" hidden>
                                                    <span id="sdItemLevel_1" class="f-valMax">82</span>%
                                                </span>
                                        </p>
                                        <p class="f-range-msg">
                                            <span class="f-color f-color2">&nbsp;</span>
                                            <span class="f-color-text">较易</span>
                                            <span class="f-color-area">
                                                    <input id="idItemArgLevel_2" name="idItemArgLevel_2" hidden>
                                                    <span id="sdItemLevel_2" class="f-valMax">82</span>%
                                                </span>
                                        </p>
                                        <#--<div class="clear"></div>-->
                                        <p class="f-range-msg">
                                            <span class="f-color f-color3">&nbsp;</span>
                                            <span class="f-color-text">中</span>
                                            <span class="f-color-area">
                                                    <input id="idItemArgLevel_3" name="idItemArgLevel_3" hidden>
                                                    <span id="sdItemLevel_3" class="f-valMax">65</span>%
                                                </span>
                                        </p>
                                        <p class="f-range-msg">
                                            <span class="f-color f-color4">&nbsp;</span>
                                            <span class="f-color-text">较难</span>
                                            <span class="f-color-area">
                                                    <input id="idItemArgLevel_4" name="idItemArgLevel_4" hidden>
                                                    <span id="sdItemLevel_4" class="f-valMax">44</span>%
                                                </span>
                                        </p>
                                        <p class="f-range-msg">
                                            <span class="f-color f-color5">&nbsp;</span>
                                            <span class="f-color-text">难</span>
                                            <span class="f-color-area">
                                                    <input id="idItemArgLevel_5" name="idItemArgLevel_5" hidden>
                                                    <span id="sdItemLevel_5" class="f-valMax">44</span>%
                                        </p>
                                        <div class="clear"></div>
                                    </div>
                                    <div class="f-range">
                                        <div class="f-hk f-hk1" src="${basePath}/biz/img/huakuai/hk1.png"
                                             style="left: 84px;"><span
                                                    class="f-range-tips" style="display: none;" id="sdItemLevel1Per">83</span></div>
                                        <div class="f-hk f-hk2" src="${basePath}/biz/img/huakuai/hk2.png"
                                             style="left: 173px;"><span
                                                    class="f-range-tips" style="display: none;" id="sdItemLevel2Per">66</span></div>
                                        <div class="f-hk f-hk3" src="${basePath}/biz/img/huakuai/hk3.png"
                                             style="left: 282px;"><span
                                                    class="f-range-tips" style="display: none;" id="sdItemLevel3Per">46</span></div>
                                        <div class="f-hk f-hk4" src="${basePath}/biz/img/huakuai/hk4.png"
                                             style="left: 409px;"><span
                                                    class="f-range-tips" style="display: none;" id="sdItemLevel4Per">10</span></div>
                                    </div>

                                    <#--<p class="f-rangeArea clearfix">
                                        <span class="left">100%</span>
                                        <span class="right">0%</span>
                                    </p>-->

                                </div>
                            </div>

                            <div class="layui-form-item" style="margin-top: 30px;">
                                <div class="layui-input-block">
                                    <button type="button" class="layui-btn layui-btn-primary pre" data-index="1">上一步</button>
                                    <button class="layui-btn" lay-submit lay-filter="formStep2" id="addPaperParam">
                                        下一步 ：设置必考题
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div id="stepDiv3" class="stepDiv" style="display: none">
                        <form class="layui-form">
                            <table id="setItemTable" lay-filter="setItemTableFilter">
                            </table>
                            <div style="text-align: center;margin-top: 5px;">
                                <button type="button" class="layui-btn layui-btn-primary pre" data-index="2">上一步</button>
                                <button class="layui-btn" lay-submit lay-filter="formStep3">
                                    下一步 ：生成试卷
                                </button>
                            </div>
                        </form>
                    </div>

                    <div id="stepDiv4" class="stepDiv" style="display: none">
                        <table id="itemTableResult" lay-filter="itemTableResultFilter">
                        </table>
                        <div style="text-align: center;margin-top: 5px;">
                            <button type="button" class="layui-btn layui-btn-primary pre" data-index="3">上一步</button>
                            <button class="layui-btn layui-btn-normal" id="generatePaper">
                                <i class="iconfont icon-shengcheng"></i> 重新生成试卷
                            </button>
                        </div>
                    </div>
                </div>

                <div>
                    <fieldset class="layui-elem-field layui-field-title" style="margin-bottom: 10px;">
                        <legend style="font-size: 14px; font-weight: bold">试卷列表</legend>
                    </fieldset>
                    <form class="layui-form">
                        <div class="layui-inline">
                            <button type="button" class="layui-btn layui-btn-sm" id="addPaper">
                                <i class="layui-icon layui-icon-add-1"></i>增加
                            </button>
                            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" id="delPaper">
                                <i class="iconfont icon-batch-del"></i> 删除
                            </button>
                        </div>
                    </form>
                    <table id="paperTable" lay-filter="paperTableFilter">
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/paperOne.js"></script>

<script type="text/html" id="paperBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" style="margin-left: 0px;"><i class="layui-icon layui-icon-edit"></i></a>
    <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-danger" lay-event="del" style="margin-left: 0px;"><i class="layui-icon layui-icon-delete"></i></a>
</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button type="button" class="layui-btn layui-btn-sm" id="checkAllBtn" lay-event="checkAll">全选</button>
        <button type="button" class="layui-btn layui-btn-sm" id="notCheckAllBtn" lay-event="notCheckAll">全不选</button>
    </div>
</script>

<script type="text/html" id="checkboxTpl">
    <input type="checkbox" lay-skin="primary" value="{{d.idItem}}" lay-filter="checkItemFilter" {{ d.fgMust == '1' ? 'checked' : '' }}>
</script>


</body>
</html>