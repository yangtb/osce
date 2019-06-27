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
            $('#idReplanFrom').addClass("layui-disabled");
            $('#idReplanFrom').attr("disabled", "true");
        } else {
            $('#idReplanFrom').removeClass("layui-disabled");
            $('#idReplanFrom').removeAttr("disabled", "true");
            $('#idReplanFrom').val("");
        }
    });

    //时间选择器
    laydate.render({
        elem: '#gmtPlanBegin'
        //,value: new Date()
        ,type: 'datetime'
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
        }
    });

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
                    // 表单值
                    form.val("step1FormFilter", sucData);
                    $('#naModel').attr("ts-selected", bizData.idModelFrom);

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

    tableSelect.render({
        elem: '#naModel',
        checkedKey: 'idModel',
        searchKey: 'naModel',
        searchPlaceholder: '请输入模板名称',
        table: {
            url: basePath + '/pf/p/plan/template/list',
            height: 260,
            cols: [[
                {type: 'radio'},
                {field: 'naModel', minWidth: 170, title: '模板名称'},
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
        $('#stationPreviewIframe').attr("src", basePath + "/pf/p/plan/station/order?idPlan=" + $('#idPlan').val())
    }

    function callStationPickCallback() {
        step.next('#stepForm');
        $('#pickIframe').attr("src", basePath + "/pf/p/plan/manage/tpPicking/page?idPlan=" + $('#idPlan').val())
    }


});




