<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>实训计划编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/common/css/link.css" media="all">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/layui/build/css/step.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/css/template/progress.css" />

    <script>
        var basePath = '${basePath}';
        var idPlan = '${idPlan!}';
        var idModelFrom = '${idModelFrom!}';
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

                <div class="rebinding-box" style="margin: 0 auto;">
                    <div class="box-timeline">
                        <ul class="text-center" style="padding-left: 80px">
                            <li>
                                考试定义
                                <div id="step1">
                                    <div class="box-num box-num1" id="stepNum1" data-index="1">
                                        1
                                    </div>
                                </div>
                            </li>
                            <li class="ml45">
                                分配学员
                                <div class="outside0ab" id="step2">
                                    <div class="box-num2 num2ab" id="stepNum2" data-index="2">
                                        2
                                    </div>
                                </div>
                            </li>
                            <li class="ml45">
                                标准化病人选
                                <div class="outside0ab" id="step3">
                                    <div class="box-num2 num2ab" id="stepNum3" data-index="3">
                                        3
                                    </div>
                                </div>
                            </li>
                            <li class="ml45">
                                编辑日程
                                <div class="outside0ab" id="step4">
                                    <div class="box-num2 num2ab" id="stepNum4" data-index="4">
                                        4
                                    </div>
                                </div>
                            </li>
                            <#--<li class="ml45">
                                分配考官
                                <div class="outside0ab" id="step5">
                                    <div class="box-num2 num2ab" id="stepNum5" data-index="5">
                                        5
                                    </div>
                                </div>
                            </li>-->
                            <li class="ml45">
                                领料计划
                                <div class="outside0ab" id="step5">
                                    <div class="box-num2 num2ab" id="stepNum5" data-index="5">
                                        5
                                    </div>
                                </div>
                            </li>
                            <li class="ml45">
                                发布清单
                                <div class="outside2a" id="step6">
                                    <div class="box-num3 num3a" id="stepNum6" data-index="6">
                                        6
                                    </div>
                                </div>
                            </li>
                            <div class="clear">
                            </div>
                        </ul>

                    </div>

                    <hr>

                    <div id="stepDiv1" class="stepDiv">
                        <div class="layui-card">
                            <#--<div class="layui-card-header">卡片面板</div>-->
                            <div class="layui-card-body">
                                <div class="container">
                                    <ul class="step-list">
                                        <li class="list-item">
                                            <span class="circle"></span>
                                            <p class="text">考试定义</p>
                                            <div class="modal">
                                            </div>
                                        </li>
                                        <li class="list-item space">
                                            <span class="circle" id="examPublish"></span>
                                            <p class="text">考试发布</p>
                                            <div class="modal">
                                                <p class="modal-item">发布时间&emsp;<span class="item-time" id="p_gmtRelease"></span></p>
                                            </div>
                                        </li>
                                        <li class="list-item space wait-finish">
                                            <span class="circle" id="gmtAct"></span>
                                            <p class="text">开始考试</p>
                                            <div class="modal">
                                                <p class="modal-item">计划开考事件&emsp;<span class="item-time" id="p_gmtPlanBegin"></span></p>
                                                <p class="modal-item">实际开考时间&emsp;<span class="item-time" id="p_gmtActBegin"></span></p>
                                            </div>
                                        </li>
                                        <li class="list-item last space wait-finish">
                                            <span class="circle" id="gmtPlan"></span>
                                            <p class="text">完成考试</p>
                                            <diiv class="modal">
                                                <p class="modal-item">计划结束时间&emsp;<span class="item-time" id="p_gmtPlanEnd"></span></p>
                                                <p class="modal-item">实际结束时间&emsp;<span class="item-time" id="p_gmtActEnd"></span></p>
                                            </diiv>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <input id="sdPlanStatus" name="sdPlanStatus" hidden>

                        <form class="layui-form" lay-filter="step1FormFilter">
                            <input id="idPlan" name="idPlan" hidden>
                            <hr>
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">学届<i class="iconfont icon-required"
                                                                         style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <select id="idGrade" name="idGrade" lay-verify="grade|required" lay-vertype="tips" disabled>
                                            <option value="">请选择学届</option>
                                            <#if allGrade?? && (allGrade?size > 0)>
                                                <#list allGrade as grade >
                                                    <option value="${grade.idGrade!}" <#if (idPlan=='' && grade.fgActive='1')>selected</#if>>${grade.naGrade!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">考试模板<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input id="idModelFrom" name="idModelFrom" hidden>
                                        <input id="idModel" name="idModel" hidden>
                                        <input type="text" class="layui-input" id="naModel" name="naModel"
                                               lay-verify="required" lay-vertype="tips" autocomplete="off" placeholder="请选择考试模板"/>
                                    </div>
                                    <button type="button" id="editTemplate" class="layui-btn layui-btn-normal"
                                            style="display: none">
                                        <i class="layui-icon layui-icon-edit"></i>编辑模板
                                    </button>
                                    <button type="button" id="editTemplateHidden" class="layui-btn layui-btn-normal"
                                            lay-href="" style="display: none">实训模板编辑
                                    </button>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">考试名称<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" id="naPlan" name="naPlan"
                                               lay-verify="required|naPlan" lay-vertype="tips" autocomplete="off" placeholder="请输入考试名称"/>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">开考时间<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" id="gmtPlanBegin" name="gmtPlanBegin"
                                               lay-verify="required" lay-vertype="tips" autocomplete="off" placeholder="yyyy-MM-dd"/>

                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">考试类别<i class="iconfont icon-required"
                                                                           style="color: #f03f2d"></i></label>
                                    <div class="layui-input-inline">
                                        <select name="fgReplan" lay-filter="fgReplanFilter">
                                            <option value="0" selected>普考</option>
                                            <option value="1">补考</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">补考来源</label>
                                    <div class="layui-input-inline">
                                        <input id="idReplanFrom" name="idReplanFrom" hidden>
                                        <input type="text" class="layui-input layui-disabled" id="idReplanFromText" name="idReplanFromText"
                                               placeholder="前选择补考来源" disabled/>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">考试描述</label>
                                <div class="layui-input-block" style="width: 514px;">
                                    <textarea name="desPlan" class="layui-textarea" lay-verify="desPlan" placeholder="请输入考试描述"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button id="savePlan" class="layui-btn" lay-submit lay-filter="formStep">
                                        &emsp;下一步 ：分配学员
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="stepDiv2" class="stepDiv" style="display: none">
                        <form class="layui-form">
                            <hr>
                            <iframe id="assignedStudentIframe" class='layui-col-xs12' frameborder="0" style="height: 700px;"
                                    src=""></iframe>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button type="button" class="layui-btn layui-btn-primary pre" data-index="1">上一步</button>
                                    <button id="planOrder" class="layui-btn" lay-submit lay-filter="formStep1">
                                        &emsp;下一步 ：标准化病人选
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="stepDiv3" class="stepDiv" style="display: none">
                        <form class="layui-form">
                            <hr>
                            <iframe id="stationPreviewIframe" class='layui-col-xs12' frameborder="0" style="height: 750px;"
                                    src="" scrolling="no"></iframe>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button type="button" class="layui-btn layui-btn-primary pre" data-index="2">上一步</button>
                                    <button id="saveSp" class="layui-btn" lay-submit lay-filter="formStep2">
                                        下一步 ：编辑日程
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="stepDiv4" class="stepDiv" style="display: none">
                        <form class="layui-form">
                            <hr>
                            <iframe id="spIframe" class='layui-col-xs12' frameborder="0" style="height: 1000px;"
                                    src="" scrolling="no"></iframe>
                            <div class="layui-form-item">
                                <div class="layui-input-block" style="padding-top: 15px;">
                                    <button type="button" class="layui-btn layui-btn-primary pre" data-index="3">上一步</button>
                                    <button id="pickStep" class="layui-btn" lay-submit lay-filter="formStep3">
                                        下一步 ：领料计划
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="stepDiv5" class="stepDiv" style="display: none">
                        <form class="layui-form">
                            <iframe id="pickIframe" class='layui-col-xs12' frameborder="0" style="height: 700px;"
                                    src=""></iframe>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button type="button" class="layui-btn layui-btn-primary pre" data-index="4">上一步</button>
                                    <button id="publishItem" class="layui-btn" lay-submit lay-filter="formStep4">
                                        下一步 ：发布清单
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="stepDiv6" class="stepDiv" style="display: none">
                        <hr>
                        <iframe id="publishItemIframe" class='layui-col-xs12' frameborder="0" style="height: 700px;"
                                src=""></iframe>
                        <div style="text-align: center;margin-top: 50px;">
                            <button type="button" class="layui-btn layui-btn-primary pre" data-index="5">上一步</button>
                            <button id="publishPlan" class="layui-btn"><i class="iconfont icon-save"></i> 发布</button>
                            <#--<button class="layui-btn layui-btn-normal"><i class="iconfont icon-daochuExcel"></i> 导出</button>-->
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/manage/planStep.js"></script>

<script type="text/html" id="fgChildTpl">
    {{#  if(d.fgChild == 0){ }}
    <span class="label label-info" style="background-color: #1E9FFF">父模板</span>
    {{#  } }}
    {{#  if(d.fgChild == 1){ }}
    <span class="label label-warning" style="background-color: #FFB800">子模板</span>
    {{#  } }}
</script>

</body>
</html>