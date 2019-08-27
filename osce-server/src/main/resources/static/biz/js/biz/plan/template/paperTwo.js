layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'form', 'jquery', 'common', 'tableSelect'], function () {
    var $ = layui.$
        , table = layui.table
        , common = layui.common
        , form = layui.form
        , tableSelect = layui.tableSelect;


    // 给step添加click事件
    function bachAddStepEventListener(currentNum) {
        // 最大步骤数
        var maxNum = 3;
        for (var i = 1; i <= maxNum; i++) {
            if (maxNum == i) {
                $("#step" + i).addClass("outside2a");
            } else {
                $("#step" + i).addClass("outside0ab");
            }
            $("#stepNum" + i).removeClass("box-num");
            // 先移除点击点击事件
            document.getElementById("stepNum" + i).removeEventListener('click', stepSkipClickListener);
        }
        for (var i = 1; i <= currentNum; i++) {
            addStepEventListener(i);
            if (maxNum == i) {
                $("#step" + i).removeClass("outside2a");
            } else {
                $("#step" + i).removeClass("outside0ab");
            }
        }
    }

    function addStepEventListener(stepNum) {
        $("#stepNum" + stepNum).addClass("box-num");
        document.getElementById("stepNum" + stepNum).addEventListener('click', stepSkipClickListener);
    }

    function stepSkipClickListener() {
        stepSkip(this.getAttribute('data-index'));
        addBoxNumStyle();
        this.style.backgroundColor = "#5FB878";
    }

    function addBoxNumStyle() {
        var boxNum = document.querySelectorAll(".box-num");
        for (var i = 0; i < boxNum.length; i++) {
            boxNum[i].style.backgroundColor = "#39f";
        }
    }

    // 步骤跳转
    function stepSkip(stepNum) {
        if (stepNum <= 2) {
            document.getElementById("step" + stepNum).classList.remove("outside0ab");
        } else {
            document.getElementById("step" + stepNum).classList.remove("outside2a");
        }

        $("#stepDiv" + stepNum).show();
        $("#stepDiv" + stepNum).siblings(".stepDiv").hide();

        addBoxNumStyle();
        $("#stepNum" + stepNum).css("background-color", "#5FB878");
        loadStepData(stepNum);
    }

    function loadStepData(stepNum) {
        if (stepNum == 2) {
            loadZd();
        } else if (stepNum == 3) {
            loadSheet();
        }
    }

    // 获取当前执行步骤
    function getCurrentStep() {
        // 此表单第一步过后数据全部生成
        bachAddStepEventListener(3);
    }


    tableSelect.render({
        elem: '#naSkillCaseFrom',
        checkedKey: 'id',
        searchKey: 'naSkillCase',
        searchPlaceholder: '请输入病例名称',
        table: {
            url: basePath + '/pf/p/skill/list',
            height: 260,
            cols: [[
                {type: 'radio'},
                {field: 'naSkillCase', minWidth: 170, title: '病例名称'},
                {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            ]]
            , limits: [10, 20, 50]
            , page: true
        },
        done: function (elem, data) {
            var bizData = data.data[0];
            if (bizData) {
                $('#idFrom').val(bizData.id);
                $('#naSkillCaseFrom').val(bizData.naSkillCase);
                $('#naSkillCaseFrom').attr("ts-selected", bizData.id);
            }
        }
    });


    form.on('submit(formStep)', function (data) {
        data.field.idModel = idModel;
        data.field.idFrom = $('#idFrom').val();
        data.field.fgActive = 1;
        console.log(JSON.stringify(data.field))
        return common.commonPost(basePath + '/pf/p/plan/paper/add/skill/case', data.field, null, 'addSkillCase',
            function (data) {
                var sucData = data.data;
                $('#id').val(sucData.id);
                $('#idSkillCase').val(sucData.idSkillCase);
                reloadSkillTable();
                copyCase(sucData.id);
            }, true);
    });

    function copyCase(id) {
        var bizData = {
            parId: id
        }
        common.commonPost(basePath + '/pf/p/plan/paper/copy/skill/case', bizData, null, 'addSkillCase',
            function (data) {
                stepSkip(2);
                addStepEventListener(2);
                loadZd();
            }, true);
    }

    function loadZd() {
        $('#zdTag').attr('src', basePath + "/pf/p/exam/skill/device/page?idSkillCase=" + $("#idSkillCase").val());
    }

    function loadSheet() {
        $('#scoreTag').attr('src', basePath + "/pf/p/plan/paper/item/page?idCase=" + $("#idSkillCase").val());
    }

    form.on('submit(formStep1)', function (data) {
        stepSkip(3);
        addStepEventListener(3);
        loadSheet();
        return false;
    });

    $('.pre').click(function () {
        stepSkip(this.getAttribute('data-index'));
    });


    //执行渲染
    table.render({
        elem: '#skillTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'skillTableId'
        , height: 360 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            //{field: 'fgActive', width: 100, title: '状态', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naSkillCase', minWidth: 170, title: '技能操作', fixed: true},
            {field: 'sdSkillCaseCa', minWidth: 170, title: '病例类别', fixed: true},
            {field: 'sheetNum', minWidth: 100, title: '评分表数量', align: "right"},
            {field: 'sheetTotalScore', minWidth: 100, title: '评分表总分', align: "right"},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#skillBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/plan/paper/skill/list'
        , even: true
        , limits: [20, 30, 100]
        , limit: 20
        , even: true
        , page: {
            layout: ['prev', 'page', 'next', 'skip', 'refresh', 'count', 'limit']
        }
        , where : {
            idModel: idModel
        }
    });

    //监听工具条
    table.on('tool(skillTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            // 表单跳到第一步
            stepSkip(1);
            // 当前步骤
            getCurrentStep(data.id);
            form.val("step1FormFilter", data);
            $('#naSkillCaseFrom').attr("ts-selected", data.idFrom);
            $('#naSkillCase').focus();
        } else if (obj.event === 'del') {
            var currentData = new Array();
            currentData.push(data)
            _delSkill(currentData);
        }
    });

    $('#add').on('click', function () {
        // 表单跳到第一步
        stepSkip(1);
        bachAddStepEventListener(1);

        $('#naSkillCaseFrom').attr("ts-selected", "");
        $('#reset').trigger('click');
        $('#naSkillCase').focus();
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('skillTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delSkill(data);
    });

    var _delSkill = function (currentData) {
        var url = basePath + '/pf/p/plan/paper/skill/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naSkillCase + '】';
            reqData.push(content.id);

        });

        var data = {
            list : reqData,
            status : '1'
        };

        layer.confirm('确定试卷' + messageTitle + '么？', {
            title: '删除试卷提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            return common.commonPost(url, data, '删除', null,
                function (data) {
                    table.reload('skillTableId', {
                    });
                }, true);
        })
    }

    $('#finish-btn').on('click',function () {
        reloadSkillTable();
        layer.tips('已完成', '#finish-btn');
        return false;
    });

    function reloadSkillTable() {
        table.reload('skillTableId', {
            height: 360
            , page: {
                curr: 1
            }
            , where : {
                idModel: idModel
            }
        });
    }

});

