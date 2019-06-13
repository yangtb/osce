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
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/css/template/test_define.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/css/template/reset.css">
    <link rel="stylesheet" href="${contextPath}/layui/expand/css/formSelects-v4.css">

    <script>
        var basePath = '${basePath!}';
        var idModel = '${idModel!}';
    </script>

    <style>
        .pageHeight {
            min-height: 850px
        }
    </style>
</head>

<body class="body-bg">

<div style="margin-top: 15px;">
    <div class="layui-fluid pageHeight">
        <div class="layui-card">
            <div class="layui-card-body" style="padding-top: 5px;">
                <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">
                    <div carousel-item>
                        <div>
                            <form class="layui-form" lay-filter="step1FormFilter">
                                <div class="layui-form-item">
                                <div class="conatiner">
                                    <div class="header-table">
                                        <div class="layui-row form-item-my5">
                                            <div class="layui-col-md4">
                                                <input id="idModel" name="idModel" hidden>
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">模板名称<i class="iconfont icon-required"
                                                                                       style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <input type="text" class="layui-input" id="naModel" name="naModel"
                                                           lay-verify="required" lay-vertype="tips" autocomplete="off"/>                                                </div>
                                            </div>
                                            <div class="layui-col-md4">
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">考场数<i class="iconfont icon-required"
                                                                                      style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <input type="number" class="layui-input" id="numArea" name="numArea" min="1"
                                                           lay-verify="required" lay-vertype="tips" autocomplete="off" value="1"/>
                                                </div>
                                            </div>
                                            <div class="layui-col-md4">
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">最大学生数<i class="iconfont icon-required"
                                                                                        style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <input type="number" class="layui-input" id="numStudentMax" name="numStudentMax" min="0"
                                                           lay-verify="required" lay-vertype="tips" autocomplete="off"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-row form-item-my5">
                                            <div class="layui-col-md4">
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">主考官权重<i class="iconfont icon-required"
                                                                                       style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <input type="number" class="layui-input" id="weightManager" name="weightManager" min="0"
                                                           lay-verify="required" lay-vertype="tips" autocomplete="off" />
                                                    <i style="position: absolute;top:8px;right: 15px;">%</i>
                                                </div>
                                            </div>
                                            <div class="layui-col-md4">
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">考官权重<i class="iconfont icon-required"
                                                                                      style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <input type="number" class="layui-input" id="weightAssistant" name="weightAssistant" min="0"
                                                           lay-verify="required" lay-vertype="tips" autocomplete="off" />
                                                    <i style="position: absolute;top:8px;right: 15px;">%</i>
                                                </div>
                                            </div>
                                            <div class="layui-col-md4">
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">中控考官权重<i class="iconfont icon-required"
                                                                                                             style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <input type="number" class="layui-input" id="weightRemote" name="weightRemote" min="0"
                                                           lay-verify="required" lay-vertype="tips" autocomplete="off" />
                                                    <i style="position: absolute;top:8px;right: 15px;">%</i>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-row form-item-my5">
                                            <div class="layui-col-md4">
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">考试时隔时间<i class="iconfont icon-required"
                                                                                                                                      style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <input type="text" class="layui-input" id="minInterval" name="minInterval"
                                                           lay-verify="required" lay-vertype="tips" autocomplete="off"/>
                                                    <i style="position: absolute;top:8px;right: 15px;">min</i>
                                                </div>
                                            </div>
                                            <div class="layui-col-md4">
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">排站策略<i class="iconfont icon-required"
                                                                                                                                     style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <select name="sdModelStrategy" lay-verify="required" lay-vertype="tips" autocomplete="off">
                                                        <option value="1">普考</option>
                                                        <option value="2">补考</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="layui-col-md4">
                                                <label class="layui-form-label" style="width: 100px; padding: 9px 5px 0px 0px">考试类型<i class="iconfont icon-required"
                                                                                                                                       style="color: #f03f2d"></i></label>
                                                <div class="layui-input-inline">
                                                    <select name="sdModelCa" lay-verify="required" lay-vertype="tips" autocomplete="off">
                                                        <option value="1">出课考</option>
                                                        <option value="2">月考</option>
                                                        <option value="3">季考</option>
                                                        <option value="4">入学考</option>
                                                        <option value="5">毕业考</option>
                                                        <option value="6">期中考</option>
                                                        <option value="7">期末考</option>
                                                        <option value="8">全科考试</option>
                                                        <option value="9">综合考试</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-row form-item-my5">
                                            <div class="layui-col-md12">
                                                <label class="layui-form-label">模板描述</label>
                                                <div class="layui-input-block">
                                                    <input type="text" class="layui-input" id="desModel" name="desModel"
                                                           autocomplete="off" style="margin-left: -5px;"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="main">
                                        <!-- 结束时间 -->
                                        <div class="main-left">
                                            <div class="left-header">
                                                <p class='date-text'>OSCE日期</p>
                                                <#--<div class="save-btn">保存</div>-->
                                            </div>
                                            <div class='time-line'>
                                                <p class='time-header'>开始时间<i class="iconfont icon-required"
                                                                              style="color: #f03f2d"></i></p>
                                                <input type="text" class="layui-input time-box" id="morningBegin" name="morningBegin"
                                                       lay-verify="required" lay-vertype="tips" autocomplete="off">

                                            </div>
                                            <div class='time-line'>
                                                <p class='time-header'>中休开始时间<i class="iconfont icon-required"
                                                                                style="color: #f03f2d"></i></p>
                                                <input type="text" class="layui-input time-box" id="morningEnd" name="morningEnd"
                                                       lay-verify="required" lay-vertype="tips" autocomplete="off">
                                            </div>
                                            <div class='time-line'>
                                                <p class='time-header'>中休结束时间<i class="iconfont icon-required"
                                                                                style="color: #f03f2d"></i></p>
                                                <input type="text" class="layui-input time-box" id="afternoonBegin" name="afternoonBegin"
                                                       lay-verify="required" lay-vertype="tips" autocomplete="off">
                                            </div>
                                            <div class='time-line'>
                                                <p class='time-header'>结束时间<i class="iconfont icon-required"
                                                                              style="color: #f03f2d"></i></p>
                                                <input type="text" class="layui-input time-box" id="afternoonEnd" name="afternoonEnd"
                                                       lay-verify="required" lay-vertype="tips" autocomplete="off">
                                            </div>
                                        </div>

                                        <!-- 考场信息 -->
                                        <div class="main-right" id="area-main">
                                            <div class='room-main' id="area_1">
                                                <div class="room-msg">
                                                    <div class="room-name" id='room1'>
                                                        <span id="naArea_1" class="room-num">考场1</span>
                                                        <img src="${contextPath!}/biz/img/template/set_btn.png"
                                                             id="set-btn1" alt="设置" class='set-btn'>
                                                        <input id="currStationNum" hidden>
                                                    </div>
                                                </div>
                                                <div class="content" id="station-main">
                                                    <div class="room-content" id="station_1">
                                                        <div class="content-main">
                                                            <div class="content-header">
                                                                <span id="naStation_1">考站1-1</span>
                                                                <a href="javascript:;" class='delete-btn' id="delsStation_1"></a>
                                                            </div>
                                                            <div class="content-text" style="margin-top: 5px;">
                                                                <div class='text-item'>
                                                                    <img src="${contextPath!}/biz/img/template/edit_btn.png" alt="编辑" class="edit-btn">
                                                                    <span class="item-desc">请添加基地类型</span>
                                                                </div>
                                                                <div class='text-item'>
                                                                    <img src="${contextPath!}/biz/img/template/edit_btn.png" alt="编辑" class="edit-btn">
                                                                    <span class="item-desc">请添加技能类型</span>
                                                                </div>
                                                                <div class='text-item' id='test1-3'>
                                                                    <img src="${contextPath!}/biz/img/template/edit_btn.png"
                                                                         alt="编辑" class="edit-btn">
                                                                    <span class="item-desc">请添加站点</span>
                                                                </div>
                                                                <div class='text-item' style="padding-left: 10px;">
                                                                    <input type="checkbox" name="" title="必过考站" lay-skin="primary">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="add-box" id="addStation">
                                                        <div class="add-main">
                                                            <img src="${contextPath!}/biz/img/template/add_btn.png"
                                                                 alt="add icon" class='add-btn'>
                                                            添加考站
                                                        </div>
                                                    </div>
                                                </div>

                                                <#--<div class="bottom-line"></div>-->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-input-block" style="text-align: right; margin-top: 30px; margin-right: 100px;">
                                        <button id="step1" class="layui-btn" lay-submit lay-filter="formStep">
                                            &emsp;下一步：排站&emsp;
                                        </button>
                                    </div>
                                </div>
                                </div>

                            </form>
                        </div>
                        <div>
                            <form class="layui-form" style="margin: 0">
                                <iframe id="pz" class='layui-col-xs12' frameborder="0" style="height: 670px;"
                                        src="${basePath}/pf/p/plan/template/two?idModel=${idModel!}" scrolling="no"></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block" style="text-align: right; margin-right: 100px;">
                                        <button id="cancelStation" type="button" class="layui-btn layui-btn-primary">撤销排站</button>
                                        <button class="layui-btn" lay-submit lay-filter="formStep2">
                                            &emsp;下一步 ：模拟排考&emsp;
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form" style="margin: 0">
                                <iframe id="mnpk" class='layui-col-xs12' frameborder="0" style="height: 670px;"
                                        src="${basePath}/pf/p/plan/template/third?idModel=${idModel!}" scrolling="no"></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block" style="text-align: right; margin-right: 100px;">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/templateOne.js"></script>


</body>
</html>