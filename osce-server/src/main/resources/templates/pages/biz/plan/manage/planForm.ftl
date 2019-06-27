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
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/css/template/progress.css" />

    <script>
        var basePath = '${basePath}';
        var idPlan = '${idPlan!}';
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
                                                <span class="circle"></span>
                                                <p class="text">考试发布</p>
                                                <div class="modal">
                                                    <p class="modal-item">发布时间&emsp;<span class="item-time">2019-04-01</span></p>
                                                </div>
                                            </li>
                                            <li class="list-item space wait-finish">
                                                <span class="circle cur-circle"></span>
                                                <p class="text">物料准备</p>
                                                <div class="modal">
                                                    <p class="modal-item">领料完成时间&emsp;<span class="item-time">2019-04-20</span></p>
                                                    <p class="modal-item">领料完成度 &emsp;&emsp;<span class="item-time">80%</span></p>
                                                </div>
                                            </li>
                                            <li class="list-item space wait-finish">
                                                <span class="circle unfinished"></span>
                                                <p class="text">开始考试</p>
                                                <div class="modal">
                                                    <p class="modal-item">计划开考事件&emsp;<span class="item-time">2019-05-01</span></p>
                                                    <p class="modal-item">实际开考时间&emsp;<span class="item-time">2019-05-01</span></p>
                                                </div>
                                            </li>
                                            <li class="list-item last space wait-finish">
                                                <span class="circle unfinished"></span>
                                                <p class="text">完成考试</p>
                                                <diiv class="modal">
                                                    <p class="modal-item">计划结束时间&emsp;<span class="item-time">2019-05-01</span></p>
                                                    <p class="modal-item">实际结束时间&emsp;<span class="item-time">2019-05-01</span></p>
                                                </diiv>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <form class="layui-form" lay-filter="step1FormFilter">
                                <input id="idPlan" name="idPlan" hidden>
                                <hr>
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
                                            <i class="iconfont icon-edit"></i> 编辑模板
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
                                                   lay-verify="required" lay-vertype="tips" autocomplete="off" placeholder="请输入开考时间"/>

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
                                            <input type="text" class="layui-input layui-disabled" id="idReplanFrom" name="idReplanFrom"
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
                        <div>
                            <form class="layui-form">
                                <hr>
                                <iframe id="assignedStudentIframe" class='layui-col-xs12' frameborder="0" style="height: 700px;"
                                        src=""></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button id="planOrder" class="layui-btn" lay-submit lay-filter="formStep1">
                                            &emsp;下一步 ：排考&emsp;
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form">
                                <hr>
                                <iframe id="stationPreviewIframe" class='layui-col-xs12' frameborder="0" style="height: 670px;"
                                        src="" scrolling="no"></iframe>
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
                                <iframe id="spIframe" class='layui-col-xs12' frameborder="0" style="height: 670px;"
                                        src="" scrolling="no"></iframe>
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
                                <iframe id="assistantIframe" class='layui-col-xs12' frameborder="0" style="height: 670px;"
                                        src="" scrolling="no"></iframe>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                        <button id="pickStep" class="layui-btn" lay-submit lay-filter="formStep4">
                                            下一步 ：领料计划
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <form class="layui-form">
                                <hr>
                                <iframe id="pickIframe" class='layui-col-xs12' frameborder="0" style="height: 700px;"
                                        src=""></iframe>
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
<script src="${contextPath}/biz/js/biz/plan/manage/planStep.js"></script>

</body>
</html>