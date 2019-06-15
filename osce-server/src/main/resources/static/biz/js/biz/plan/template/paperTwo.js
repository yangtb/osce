layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'form', 'jquery', 'step', 'common', 'tableSelect'], function () {
    var $ = layui.$
        , table = layui.table
        , common = layui.common
        , form = layui.form
        , step = layui.step
        , tableSelect = layui.tableSelect;


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

    var options = {
        elem: '#stepForm',
        filter: 'stepForm',
        width: '100%', //设置容器宽度
        stepWidth: '680px',
        height: '520px',
        indicator : 'none',  // 不显示指示器
        arrow : 'always',  // 始终显示箭头
        autoplay : false,  // 关闭自动播放
        stepItems: [{
            title: '病例选择'
        }, {
            title: '站点配置'
        }, {
            title: '评分表'
        }]
    };

    var ins = step.render(options);

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
                step.next('#stepForm');
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
        step.next('#stepForm');
        loadSheet();
        return false;
    });

    $('.pre').click(function () {
        step.pre('#stepForm');
    });

    $('.next').click(function () {
        step.next('#stepForm');
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
            step.goFirst(ins, options);
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
        step.goFirst(ins, options);
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

