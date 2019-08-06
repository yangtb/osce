layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    formSelects: 'formSelects-v4'
}).use(['layer', 'index', 'form', 'jquery', 'step', 'element', 'common', 'laydate', 'tableSelect'], function () {
    var $ = layui.$
        , common = layui.common
        , form = layui.form
        , step = layui.step
        , element = layui.element
        , laydate = layui.laydate
        , tableSelect = layui.tableSelect;

    tableSelect.render({
        elem: '#idReplanFromText',
        checkedKey: 'idPlan',
        searchKey: 'naPlan',
        searchPlaceholder: '请输入考试名称',
        table: {
            url: basePath + '/pf/p/plan/manage/list1',
            height: 260,
            cols: [[
                {type: 'radio'},
                {field: 'naPlan', minWidth: 170, title: '考试名称'},
                {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            ]]
            , limits: [10, 20, 50]
            , page: true
        },
        done: function (elem, data) {
            var bizData = data.data[0];
            if (bizData) {
                $('#idReplanFrom').val(bizData.idPlan);
                $('#idReplanFromText').val(bizData.naPlan);
                $('#idReplanFromText').attr("ts-selected", bizData.idPlan);
            }
        }
    });

    form.verify({
        desPlan: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        },
        naPlan: function (value) {
            if (value && value.length > 64) {
                return '长度不能超过64个字';
            }
        },
    });

    form.on('select(fgReplanFilter)', function(data){
        if (data.value == 0) {
            $('#idReplanFromText').addClass("layui-disabled");
            $('#idReplanFromText').attr("disabled", "true");
        } else {
            $('#idReplanFromText').removeClass("layui-disabled");
            $('#idReplanFromText').removeAttr("disabled", "true");
            $('#idReplanFrom').val("");
            $('#idReplanFromText').val("");
        }
    });

    //时间选择器
    laydate.render({
        elem: '#gmtPlanBegin'
        //,value: new Date()
        , min: 0
        , type: 'datetime'
        , trigger: 'click'
    });

    $(document).ready(function () {
        var ul = document.querySelectorAll(".list-item");
        for (var i = 0; i < ul.length; i++) {
            if ( i >= 1) {
                ul[i].addEventListener('click', function () {
                    $("ul li").find(".modal").hide()
                    $(this).find(".modal").show()
                });
            }
        }
        if (idPlan) {
            // 加载计划信息
            loadPlanInfo();
        } else {
            if (idModelFrom) {
                // 默认考试模板
                defaultModel();
            }
        }
    });

    function defaultModel() {
        var bizData = {
            idModel: idModelFrom
        };
        $.ajax({
            url: basePath + '/pf/r/plan/select/model/name',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    var sucData = data.data;
                    if (sucData) {
                        $('#idModelFrom').val(idModelFrom);
                        $('#naModel').val(sucData);
                        $('#naModel').attr("ts-selected", idModelFrom);
                    }
                    return true;
                }
            },
            error: function () {
                common.errorMsg("查询失败");
                return false;
            }
        });
    }

    function loadPlanInfo(){
        var bizData = {
            idPlan: idPlan
        };
        $.ajax({
            url: basePath + '/pf/r/plan/manage/select',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    var sucData = data.data;
                    fullProgress(sucData);
                    // 表单值
                    form.val("step1FormFilter", sucData);
                    $('#naModel').attr("ts-selected", sucData.idModelFrom);

                    if ($("#idModel").val()) {
                        $("#editTemplate").css("display", "block");
                    } else {
                        $("#editTemplate").css("display", "none");
                    }
                    return true;
                }
            },
            error: function () {
                common.errorMsg("查询失败");
                return false;
            }
        });
    }

    function fullProgress(data){
        if (data.gmtRelease) {
            $("#gmtRelease").text(data.gmtRelease);
        } else {
            $("#examPublish").addClass("unfinished");
        }


        if (data.gmyPicking) {
            $("#gmyPicking").text(data.gmyPicking);
            $("#percentPicking").text(data.percentPicking + '%');
        }
        if (data.percentPicking != 100) {
            $("#pickingCircle").addClass("cur-circle");
        }


        if (data.gmtActBegin) {
            $("#gmtActBegin").text(data.gmtActBegin);
        }
        if (data.gmtActEnd) {
            $("#gmtActEnd").text(data.gmtActEnd);
        }
        if (data.gmtActBegin && !data.gmtActEnd) {
            $("#gmtAct").addClass("cur-circle");
        } else if (!data.gmtActBegin && !data.gmtActEnd) {
            $("#gmtAct").addClass("unfinished");
        }


        if (data.gmtPlanBegin) {
            $("#gmtPlanBeginBar").text(data.gmtPlanBegin);
        }
        if (data.gmtPlanEnd) {
            $("#gmtPlanEndBar").text(data.gmtPlanEnd);
        }
        if (!data.gmtPlanEnd) {
            $("#gmtPlan").addClass("unfinished");
        }
    }

    tableSelect.render({
        elem: '#naModel',
        checkedKey: 'idModel',
        searchKey: 'naModel',
        searchPlaceholder: '请输入模板名称',
        table: {
            url: basePath + '/pf/p/plan/template/list',
            height: 260,
            size:'sm',
            cols: [[
                {type: 'radio'},
                {field: 'naModel', minWidth: 170, title: '模板名称'},
                //{field: 'fgChild', width: 100, title: '模板类型', align: 'center', templet: '#fgChildTpl'},
                {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            ]]
            , limits: [10, 20, 50]
            , page: true
        },
        done: function (elem, data) {
            var bizData = data.data[0];
            if (bizData) {
                $('#idModelFrom').val(bizData.idModel);
                $('#naModel').val(bizData.naModel);
                $('#naModel').attr("ts-selected", bizData.idModel);
            }
        }
    });

    $("#editTemplate").on("click", function () {
        var idModel = $("#idModel").val();
        if (!idModel) {
            layer.tips("请先选择考试模板", '#editTemplate', {tips: [2, '#FF5722']});
            return;
        }

        $('#editTemplateHidden').attr('lay-href',
            basePath + '/pf/p/plan/template/form?idModel=' + idModel);
        $('#editTemplateHidden').click();
    });

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
        common.commonPost(basePath + '/pf/r/plan/manage/add',
            data.field, null, 'savePlan', savePlanCallback, true);
        return false;
    });

    form.on('submit(formStep1)', function (data) {
        var bizData = {
            parIdPlan : idPlan
        }
        common.commonPost(basePath + '/pf/r/plan/call/station/order',
            bizData, null, 'planOrder', callStationPlanOrderCallback, true);
        return false;
    });

    form.on('submit(formStep2)', function (data) {
        step.next('#stepForm');
        $('#spIframe').attr("src", basePath + "/pf/p/plan/station/sp?idPlan=" + $('#idPlan').val())
        return false;
    });

    form.on('submit(formStep3)', function (data) {
        step.next('#stepForm');
        $('#assistantIframe').attr("src", basePath + "/pf/p/plan/station/assistant?idPlan=" + $('#idPlan').val())
        return false;
    });

    form.on('submit(formStep4)', function (data) {
        var bizData = {
            parIdPlan : idPlan
        }
        common.commonPost(basePath + '/pf/r/plan/call/station/pick',
            bizData, null, 'pickStep', callStationPickCallback, true);
        return false;
    });

    form.on('submit(formStep5)', function (data) {
        step.next('#stepForm');
        $('#publishItemIframe').attr("src", basePath + "/pf/p/plan/station/publish/item/page?idPlan=" + $('#idPlan').val())
        return false;
    });

    $('.pre').click(function () {
        step.pre('#stepForm');
    });

    $('.next').click(function () {
        step.next('#stepForm');
    });


    function savePlanCallback(data){
        var sucData = data.data;
        //console.log(sucData)
        if (sucData) {
            idPlan = sucData.idPlan;
            $('#idPlan').val(sucData.idPlan);
            $('#idModel').val(sucData.idModel);
            if ($("#idModel").val()) {
                $("#editTemplate").css("display", "block");
            }
            $('#assignedStudentIframe').attr("src", basePath + "/pf/p/plan/manage/assigned/student/page?idPlan=" + sucData.idPlan)
        }
        step.next('#stepForm');
    }

    function callStationPlanOrderCallback() {
        step.next('#stepForm');
        $('#stationPreviewIframe').attr("src", basePath + "/pf/p/plan/station/order?idPlan=" + $('#idPlan').val() + '&idModel=' + $('#idModel').val())
    }

    function callStationPickCallback() {
        step.next('#stepForm');
        $('#pickIframe').attr("src", basePath + "/pf/p/plan/manage/tpPicking/page?idPlan=" + $('#idPlan').val())
    }

    // 计划发布
    $('#publishPlan').on('click', function () {
        var bizData = {
            idPlan : idPlan
        }
        common.commonPost(basePath + '/pf/r/plan/publish', bizData, '发布', 'publishPlan', null, true);
    });

});




