layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer', 'index', 'table', 'form', 'jquery', 'common', 'tableSelect', "upload"], function () {
    var $ = layui.$
        , table = layui.table
        , common = layui.common
        , form = layui.form
        , tableSelect = layui.tableSelect
        , upload = layui.upload;


    // 给step添加click事件
    function bachAddStepEventListener(currentNum) {
        // 最大步骤数
        var maxNum = 2;
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
        document.getElementById("stepNum" + stepNum).addEventListener('click', stepSkipClickListener);
        $("#stepNum" + stepNum).addClass("box-num");
    }

    function stepSkipClickListener() {
        stepSkip(this.getAttribute('data-index'))
    }

    // 步骤跳转
    function stepSkip(stepNum) {
        if (stepNum <= 1) {
            document.getElementById("step" + stepNum).classList.remove("outside0ab");
        } else {
            document.getElementById("step" + stepNum).classList.remove("outside2a");
        }

        $("#stepDiv" + stepNum).show();
        $("#stepDiv" + stepNum).siblings(".stepDiv").hide();

        loadStepData(stepNum);
    }

    function loadStepData(stepNum) {
        if (stepNum == 2) {
            loadSheet();
        }
    }

    // 获取当前执行步骤
    function getCurrentStep() {
        // 此表单第一步过后数据全部生成
        bachAddStepEventListener(2);
    }

    tableSelect.render({
        elem: '#naSpCaseFrom',
        checkedKey: 'id',
        searchKey: 'naSpCase',
        searchPlaceholder: '请输入病例名称',
        table: {
            url: basePath + '/pf/p/case/list',
            height: 260,
            cols: [[
                {type: 'radio'},
                {field: 'naSpCase', minWidth: 170, title: '病例名称'},
                {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            ]]
            , limits: [10, 20, 50]
            , page: true
        },
        done: function (elem, data) {
            var bizData = data.data[0];
            if (bizData) {
                $('#idFrom').val(bizData.id);
                $('#naSpCaseFrom').val(bizData.naSpCase);
                $('#naSpCaseFrom').attr("ts-selected", bizData.id);
            }
        }
    });

    form.on('submit(formStep)', function (data) {
        data.field.idModel = idModel;
        data.field.idFrom = $('#idFrom').val();
        data.field.fgActive = 1;
        console.log(JSON.stringify(data.field))
        return common.commonPost(basePath + '/pf/p/plan/paper/add/sp/case', data.field, null, 'addSpCase',
            function (data) {
                var sucData = data.data;
                $('#id').val(sucData.id);
                $('#idCase').val(sucData.idCase);
                reloadSpTable();
                copyCase(sucData.id);
            }, true);
    });

    function copyCase(id) {
        var bizData = {
            parId: id
        }
        common.commonPost(basePath + '/pf/p/plan/paper/copy/sp/case', bizData, null, 'addSpCase',
            function (data) {
                stepSkip(2);
                addStepEventListener(2);
                loadSheet();
            }, true);
    }

    function loadSheet() {
        $('#scoreTag').attr('src', basePath + "/pf/p/plan/paper/item/page?idCase=" + $("#idCase").val());
    }

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
            {field: 'fgActive', width: 100, title: '状态', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naSpCase', minWidth: 170, title: '试卷名称', fixed: true},
            {field: 'sheetNum', minWidth: 100, title: '评分表数量', align: "right"},
            {field: 'sheetTotalScore', minWidth: 100, title: '评分表总分', align: "right"},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#spBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/plan/paper/sp/list'
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
            $('#naSpCaseFrom').attr("ts-selected", data.idFrom);
            $('#naSpCase').focus();
        } else if (obj.event === 'del') {
            var currentData = new Array();
            currentData.push(data)
            _delSp(currentData);
        }
    });

    $('#addSp').on('click', function () {
        // 表单跳到第一步
        stepSkip(1);
        bachAddStepEventListener(1);

        $('#naSpCaseFrom').attr("ts-selected", "");
        $('#reset').trigger('click');
        $('#naSpCase').focus();
    });

    $("#delSp").on('click', function () {
        var checkStatus = table.checkStatus('skillTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#delSp', {tips: 1});
            return;
        }
        _delSp(data);
    });

    var _delSp = function (currentData) {
        var url = basePath + '/pf/p/plan/paper/sp/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naSpCase + '】';
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

    form.on('switch(fgActiveCheckFilter)', function (obj) {
        var reqData = new Array();
        reqData.push(this.value);
        var bizData = {
            list : reqData ,
            status : obj.elem.checked == true ? '1' : '0'
        };
        $.ajax({
            url: basePath + '/pf/p/plan/paper/sp/updateStatus',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.tips(data.msg, obj.othis);
                    return false;
                } else {
                    layer.tips("设置成功", obj.othis);
                    return true;
                }
            },
            error: function () {
                common.errorMsg("设置失败");
                return false;
            }
        });
    });

    $('#finish-btn').on('click',function () {
        reloadSpTable();
        layer.tips('已完成', '#finish-btn');
        return false;
    });

    function reloadSpTable() {
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

    // 上传剧本
    upload.render({
        elem: '#LAY_avatarUpload'
        , url: basePath + '/upload'
        , field: 'file'
        , accept: 'file' //普通文件
        //, exts: 'jpg|png|bmp|jpeg'
        , before: function (obj) {
            layer.msg('正在上传剧本', {icon: 16, shade: 0.01});
        }
        , done: function (res) {
            if (res.code != '0') {
                layer.tips(res.msg, '#LAY_avatarUpload', {
                    tips: [1, '#FF5722'],
                    time: 5000
                });
                return;
            }
            $('#docSp').val(res.data.path);
            layer.closeAll('loading');
        }
        , error: function () {
            layer.closeAll('loading');
        }
    });

    $('#preview').on('click', function () {
        var i = $("#docSp").val();
        if (!i) {
            layer.tips("请先上传剧本", '#preview', {
                tips: [1, '#FF5722']
            });
            return;
        }
        layui.layer.photos({
            photos: {
                title: "查看布局图",
                data: [{
                    src: i
                }]
            },
            shade: .01,
            closeBtn: 1,
            anim: 5
        })
    });

});

