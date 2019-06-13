layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
    formSelects: 'formSelects-v4'
}).use(['layer', 'index', 'table', 'form', 'jquery', 'step', 'element', 'common', 'tableSelect', 'formSelects'], function () {
    var $ = layui.$
        , table = layui.table
        , common = layui.common
        , form = layui.form
        , step = layui.step
        , element = layui.element
        , tableSelect = layui.tableSelect
        , formSelects = layui.formSelects;

    tableSelect.render({
        elem: '#naItemStoreFrom',
        checkedKey: 'idItemStore',
        searchKey: 'naItemStore',
        searchPlaceholder: '请输入题集名称',
        table: {
            url: basePath + '/pf/p/item/list',
            height: 260,
            cols: [[
                {type: 'radio'},
                {field: 'naItemStore', minWidth: 170, title: '题集'},
                {field: 'itemNum', minWidth: 100, title: '题目数量', align: "right"},
                {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            ]]
            , limits: [10, 20, 50]
            , page: true
        },
        done: function (elem, data) {
            var bizData = data.data[0];
            if (bizData) {
                $('#idItemStoreFrom').val(bizData.idItemStore);
                $('#naItemStoreFrom').val(bizData.naItemStore);
            }
        }
    });

    var options = {
        elem: '#stepForm',
        filter: 'stepForm',
        width: '100%', //设置容器宽度
        stepWidth: '680px',
        height: '500px',
        indicator : 'none',  // 不显示指示器
        arrow : 'always',  // 始终显示箭头
        autoplay : false,  // 关闭自动播放
        stepItems: [{
            title: '选择题集'
        }, {
            title: '试卷参数'
        }, {
            title: '设置必考题'
        }, {
            title: '生成试卷'
        }]
    };
    var ins = step.render(options);

    // 第1步 ： 选择题集
    form.on('submit(formStep)', function (data) {
        data.field.idModel = idModel;
        data.field.fgItemFromPublic = data.field.fgItemFromPublic ? '1' : '0';
        data.field.fgItemFromPrivate = data.field.fgItemFromPrivate ? '1' : '0';
        data.field.fgItemFromImport = data.field.fgItemFromImport ? '1' : '0';
        if (data.field.fgItemFromPublic == '0' && data.field.fgItemFromPrivate == '0'
            && data.field.fgItemFromImport == '0') {
            layer.tips('题集范围至少选中一个', '#tjfw', {tips: 1});
            return false;
        }
        // alert(JSON.stringify(data.field))
        return common.commonPost(basePath + '/pf/p/plan/paper/add/item', data.field, null, 'addTdItemStore',
            function (data) {
                $('#idItemStore').val(data.data);
                copyItem(data.data);
            }, true);
    });

    function copyItem(idItemStore) {
        var bizData = {
            parIdItemStore: idItemStore
        }
        common.commonPost(basePath + '/pf/p/plan/paper/copy/item', bizData, null, 'addTdItemStore',
            function (data) {
                step.next('#stepForm');
                selectPaperParam();
            }, true);
    }

    // 第2步 ： 试卷参数
    function selectPaperParam() {
        var bizData = {
            idItemStore: $('#idItemStore').val()
        };
        $.ajax({
            url: basePath + '/pf/p/plan/paper/select/param',
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
                    fillPaperParamForm(data.data);
                    return true;
                }
            },
            error: function () {
                common.errorMsg("查询失败");
                return false;
            }
        });
    }

    function fillPaperParamForm(data) {
        // 题目总数
        var itemTotals = data.itemTotals;
        if (itemTotals.length > 0) {
            $.each(itemTotals, function (index, content) {
                $('#total_' + content.sdItemCa).html(content.total);
            });
        }

        // 目录
        var tdItemSections = data.tdItemSections;
        if (tdItemSections.length > 0) {
            var formSelectsData = new Array();
            $.each(tdItemSections, function (index, content) {
                var checkStatus = content.fgActive == '1' ? 'selected' : '';
                var tdItemSection = {
                    "value": content.idItemSection,
                    "name": content.naItemSection,
                    "selected": checkStatus
                };
                formSelectsData.push(tdItemSection);
            });
            formSelects.data('select1', 'local', {
                arr: formSelectsData
            });
        }

        var paramData = {};
        // 题型参数
        var tdItemArgTypeList = data.tdItemArgTypes;
        if (tdItemArgTypeList.length > 0) {
            $.each(tdItemArgTypeList, function (index, content) {
                var key;
                if (content.sdItemCa == '1') {
                    key = 'A1';
                    setFormStatus(true, ['numType_A1', 'scoreType_A1']);
                } else if (content.sdItemCa == '2') {
                    key = 'A2';
                    setFormStatus(true, ['numType_A2', 'scoreType_A2']);
                } else if (content.sdItemCa == '3') {
                    key = 'B1';
                    setFormStatus(true, ['numType_B1', 'scoreType_B1']);
                }
                paramData['idItemArgType_' + key] = content.idItemArgType;
                paramData['sdItemCa_' + key] = content.sdItemCa;
                paramData['numType_' + key] = content.numType;
                paramData['scoreType_' + key] = content.scoreType;
            });
        }
        // 初始化比例尺
        var sdItemLevels = data.sdItemLevels, v_1, v_2, v_3, v_4;
        if (sdItemLevels.length > 0) {
            $.each(sdItemLevels, function (index, content) {
                if (content.sdItemLevel == '1') {
                    v_1 = 100 - content.weightLevel;
                    $('#sdItemLevel1Per').html(v_1);
                } else if (content.sdItemLevel == '2') {
                    v_2 = v_1 - content.weightLevel;
                    $('#sdItemLevel2Per').html(v_2);
                } else if (content.sdItemLevel == '3') {
                    v_3 = v_2 - content.weightLevel;
                    $('#sdItemLevel3Per').html(v_3);
                } else if (content.sdItemLevel == '4') {
                    v_4 = v_3 - content.weightLevel;
                    $('#sdItemLevel4Per').html(v_4);
                }
                paramData['idItemArgLevel_' + content.sdItemLevel] = content.idItemArgLevel;
            });
        }
        if (paramData) {
            form.val("step2FormFilter", paramData);
        }
        initPer();
    }

    form.on('checkbox(switch_A1)', function (data) {
        var status = this.checked ? true : false;
        setFormStatus(status, ['numType_A1', 'scoreType_A1']);
    });
    form.on('checkbox(switch_A2)', function (data) {
        var status = this.checked ? true : false;
        setFormStatus(status, ['numType_A2', 'scoreType_A2']);
    });
    form.on('checkbox(switch_B1)', function (data) {
        var status = this.checked ? true : false;
        setFormStatus(status, ['numType_B1', 'scoreType_B1']);
    });
    form.on('submit(formStep2)', function (data) {
        // 目录信息
        var idItemSectionList = formSelects.value('select1', 'val');
        if (idItemSectionList.length == 0) {
            $('#idItemSection').trigger('click');
            layer.tips('请选择目录', '#idItemSectionTips', {tips: 1});
            return false;
        }
        // 题型校验
        data.field.sdItemCa_A1 = data.field.sdItemCa_A1 ? '1' : '0';
        data.field.sdItemCa_A2 = data.field.sdItemCa_A2 ? '1' : '0';
        data.field.sdItemCa_B1 = data.field.sdItemCa_B1 ? '1' : '0';
        if (data.field.sdItemCa_A1 == '0' && data.field.sdItemCa_A2 == '0'
            && data.field.sdItemCa_B1 == '0') {
            layer.tips('请选择题型', '#sdItemCaTips', {tips: 1});
            return false;
        }

        if (data.field.sdItemCa_A1 == '1') {
            if (!$('#numType_A1').val()) {
                $('#numType_A1').focus();
                layer.tips('请填写数量', '#numType_A1', {tips: 1});
                return false;
            }
            if (parseInt($('#numType_A1').val()) > parseInt($("#total_1").html())) {
                layer.tips('数量不能大于' + $("#total_1").html(), '#numType_A1', {tips: 1});
                return false;
            }
            if (!$('#scoreType_A1').val()) {
                $('#scoreType_A1').focus();
                layer.tips('请填写分值', '#scoreType_A1', {tips: 1});
                return false;
            }
        }
        if (data.field.sdItemCa_A2 == '1') {
            if (!$('#numType_A2').val()) {
                $('#numType_A2').focus();
                layer.tips('请填写数量', '#numType_A2', {tips: 1});
                return false;
            }
            if (parseInt($('#numType_A2').val()) > parseInt($("#total_2").html())) {
                layer.tips('数量不能大于' + $("#total_2").html(), '#numType_A2', {tips: 1});
                return false;
            }
            if (!$('#scoreType_A2').val()) {
                $('#scoreType_A2').focus();
                layer.tips('请填写分值', '#scoreType_A2', {tips: 1});
                return false;
            }
        }
        if (data.field.sdItemCa_B1 == '1') {
            if (!$('#numType_B1').val()) {
                $('#numType_B1').focus();
                layer.tips('请填写数量', '#numType_B1', {tips: 1});
                return false;
            }
            if (parseInt($('#numType_B1').val()) > parseInt($("#total_3").html())) {
                layer.tips('数量不能大于' + $("#total_3").html(), '#numType_B1', {tips: 1});
                return false;
            }
            if (!$('#scoreType_B1').val()) {
                $('#scoreType_B1').focus();
                layer.tips('请填写分值', '#scoreType_B1', {tips: 1});
                return false;
            }
        }


        // 题型参数组装
        var tdItemArgTypeList = new Array();
        if (data.field.sdItemCa_A1 == '1') {
            var tdItemArgType = {
                idItemArgType: $('#idItemArgType_A1').val(),
                idItemStore: $('#idItemStore').val(),
                sdItemCa: '1',
                numType: $('#numType_A1').val(),
                scoreType: $('#scoreType_A1').val()
            }
            tdItemArgTypeList.push(tdItemArgType);
        }
        if (data.field.sdItemCa_A2 == '1') {
            var tdItemArgType = {
                idItemArgType: $('#idItemArgType_A2').val(),
                idItemStore: $('#idItemStore').val(),
                sdItemCa: '2',
                numType: $('#numType_A2').val(),
                scoreType: $('#scoreType_A2').val()
            }
            tdItemArgTypeList.push(tdItemArgType);
        }
        if (data.field.sdItemCa_B1 == '1') {
            var tdItemArgType = {
                idItemArgType: $('#idItemArgType_B1').val(),
                idItemStore: $('#idItemStore').val(),
                sdItemCa: '3',
                numType: $('#numType_B1').val(),
                scoreType: $('#scoreType_B1').val()
            }
            tdItemArgTypeList.push(tdItemArgType);
        }

        // 难易比例参数组装 sdItemLevel_1
        var sdItemLevelList = new Array();
        for (var i = 1; i <= 5; i++) {
            var sdItemLevel = {
                idItemArgLevel: $('#idItemArgLevel_' + i).val(),
                idItemStore: $('#idItemStore').val(),
                sdItemLevel: i,
                weightLevel: $('#sdItemLevel_' + i).text()
            }
            sdItemLevelList.push(sdItemLevel);
        }

        var bizData = {
            idItemSections: idItemSectionList,
            tdItemArgTypes: tdItemArgTypeList,
            sdItemLevels: sdItemLevelList
        }

        return common.commonPost(basePath + '/pf/p/plan/paper/add/param', bizData, null, 'addPaperParam',
            function (data) {
                // 重置表单数据
                fillPaperParamForm(data.data);
                step.next('#stepForm');
                // 加载【设置必考题】列表
                loadSetItemTable();
            }, true);
    });

    // 第3步 ： 设置必考题
    form.on('submit(formStep3)', function (data) {
        step.next('#stepForm');
        itemTableResult();
        return false;
    });

    $('.pre').click(function () {
        step.pre('#stepForm');
    });

    $('.next').click(function () {
        step.next('#stepForm');
    });

    $("#importItem").on('click', function () {
        $('#importItemHidden').click();
    });

    // 输入框控制
    function setFormStatus(status, arr) {
        if (!status) {
            $.each(arr, function (index, value) {
                $('#' + value).attr("disabled", "true");
                $('#' + value).val('');
            });
        } else {
            $.each(arr, function (index, value) {
                $('#' + value).removeAttr("disabled", "true");
            });
        }
    };

    function editPaper(data) {
        var bizData = {
            idModel: idModel,
            idItemStore : data.idItemStore
        };
        $.ajax({
            url: basePath + '/pf/p/plan/paper/select/item',
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
                    sucData.fgItemFromPublic = sucData.fgItemFromPublic == '1' ? true : false;
                    sucData.fgItemFromPrivate = sucData.fgItemFromPrivate == '1' ? true : false;
                    sucData.fgItemFromImport = sucData.fgItemFromImport == '1' ? true : false;
                    form.val("step1FormFilter", sucData);
                    $('#naItemStoreFrom').attr("ts-selected", sucData.idItemStoreFrom);
                    $('#naItemStoreFrom').val(sucData.naItemStoreFrom)
                    return true;
                }
            },
            error: function () {
                common.errorMsg("查询失败");
                return false;
            }
        });
    }

    //执行渲染
    table.render({
        elem: '#paperTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'paperTableId'
        , height: 440 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            //{field: 'fgActive', width: 100, title: '状态', fixed: true, templet: '#fgActiveTpl'},
            {field: 'naItemStore', minWidth: 170, title: '试卷名称'},
            {field: 'naItemStoreFrom', minWidth: 170, title: '题集'},
            {field: 'itemNum', width: 100, title: '题目数量', align: "right"},
            {field: 'itemNum', width: 100, title: '总分值', align: "right"},
            {field: 'gmtCreate', width: 170, title: '创建时间'},
            {fixed: 'right', width: 100, title: '操作', align: 'center', toolbar: '#paperBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/plan/paper/list'
        , limit: 20
        , even: true
        , limits: [20, 50, 100]
        , page: {
            layout: ['prev', 'page', 'next', 'skip', 'refresh', 'count', 'limit']
        }, where : {
            idModel: idModel
        }
    });

    //监听工具条
    table.on('tool(paperTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            // 表单跳到第一步
            step.goFirst(ins, options);
            editPaper(data);
            $('#naItemStore').focus();
        } else if (obj.event === 'del') {
            var currentData = new Array();
            currentData.push(data)
            _delPaper(currentData);
        }
    });

    $('#addPaper').on('click', function () {
        // 表单跳到第一步
        step.goFirst(ins, options);
        $('#reset').trigger('click');
        $('#naItemStore').focus();
    });

    $("#delPaper").on('click', function () {
        var checkStatus = table.checkStatus('paperTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#delPaper', {tips: 1});
            return;
        }
        _delPaper(data);
    });

    var _delPaper = function (currentData) {
        var url = basePath + '/pf/p/plan/paper/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.naItemStore + '】';
            reqData.push(content.idItemStore);

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
                    table.reload('paperTableId', {
                    });
                }, true);
        })
    }


    function loadSetItemTable() {
        //执行渲染
        table.render({
            elem: '#setItemTable' //指定原始表格元素选择器（推荐id选择器）
            , id: 'setItemTableId'
            , size : 'sm'
            , toolbar: '#toolbarDemo'
            , defaultToolbar: []
            , height: 390 //容器高度
            , cols: [[
                {type: 'numbers', fixed: true, title: 'R'},
                {field:'fgMust', width: 50, templet: '#checkboxTpl', unresize: true, fixed: true},
                {field: 'mainItem', minWidth: 240, title: '题干'},
                {field: 'sdItemCa', width: 100, title: '题目类型', align:'center'},
                {field: 'sdItemLevel', width: 100, title: '难度', align:'center'},
                {field: 'sdItemFrom', width: 120, title: '来源', align:'center'},
            ]] //设置表头
            , url: basePath + '/pf/p/plan/paper/item/list'
            , limit: 20
            , even: true
            , page: {
                layout: ['prev', 'page', 'next', 'skip', 'refresh', 'count', 'limit']
            }
            , where : {
                idItemStore: $('#idItemStore').val()
            }
        });
    }

    // 头工具栏事件
    table.on('toolbar(setItemTableFilter)', function(obj){
        var status_v, tips_v;
        switch(obj.event){
            case 'checkAll':
                status_v = 1;
                tips_v = 'checkAllBtn';
                break;
            case 'notCheckAll':
                status_v = 0;
                tips_v = 'notCheckAllBtn';
                break;
        };
        var biaData = {
            idItemStore : $('#idItemStore').val(),
            status : status_v
        }
        return setPaperParam(biaData, true, tips_v);;
    });

    form.on('checkbox(checkItemFilter)', function(obj){
        var idItemList = new Array();
        idItemList.push(this.value);
        var biaData = {
            idItems : idItemList,
            status : obj.elem.checked ? 1 : 0
        }
        return setPaperParam(biaData, false, obj.othis);
    });

    function setPaperParam(bizData, reloadTableFlag, selectId) {
        $.ajax({
            url: basePath + '/pf/p/plan/paper/set/param/must',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    if (typeof(selectId) == 'string') {
                        layer.tips(data.msg, '#' + selectId, {tips: [2, '#FF5722']});
                    } else {
                        layer.tips(data.msg, selectId);
                    }
                    return false;
                } else {
                    if (typeof(selectId) == 'string') {
                        layer.tips("设置成功", '#' + selectId, {tips: 1});
                    } else {
                        layer.tips("设置成功", selectId);
                    }
                    if (reloadTableFlag) {
                        reloadSetItemTable();
                    }
                    return false;
                }
            },
            error: function () {
                layer.tips("提交失败", '#' + selectId, {tips: [2, '#FF5722']});
                return false;
            }

        });
    }

    function reloadSetItemTable(){
        table.reload('setItemTableId', {
        });
    }

    function itemTableResult()  {
        //执行渲染
        table.render({
            elem: '#itemTableResult' //指定原始表格元素选择器（推荐id选择器）
            , id: 'itemTableResultId'
            , height: 390 //容器高度
            , cols: [[
                {type: 'numbers', fixed: true, title: 'R'},
                {field: 'mainItem', minWidth: 240, title: '题干'},
                {field: 'sdItemCa', width: 100, title: '题目类型', align:'center'},
                {field: 'sdItemLevel', width: 100, title: '难度', align:'center'},
                {field: 'sdItemFrom', width: 120, title: '来源', align:'center'},
            ]] //设置表头
            , url: basePath + '/pf/p/plan/paper/item/list'
            , limit: 20
            , even: true
            , page: {
                layout: ['prev', 'page', 'next', 'skip', 'refresh', 'count', 'limit']
            }
            , where : {
                idItemStore: $('#idItemStore').val(),
                fgActive : '1'
            }
        });
    }

    $("#generatePaper").on('click', function () {
        var bizData = {
            parIdItemStore: $('#idItemStore').val()
        }
        return common.commonPost(basePath + '/pf/p/plan/paper/generate', bizData, '生成试卷', 'generatePaper',
            function (data) {
                itemTableResult();
            }, true);
    });

    // 导入试题
    $("#importItem").on('click', function () {
        var url = basePath + 'pf/p/item/page';
    });


    /*************************************
     *               比例尺
     *************************************/

    var index, maxLeft;
    $(function () {
        //range值提示
        $(".f-hk").mouseenter(function () {
            $(this).find(".f-range-tips").css("display", "none");
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
            //console.log("startX:" + startX + ', index:' + index + ', preLeft:' + preLeft + ', minLeft' + minLeft)
        })

        $(document).mousemove(function (e) {
            e.stopPropagation();
            //是否点击滑块
            if (handle) {
                //显示提示值
                $(".f-hk").eq(index).find(".f-range-tips").css("display", "none");
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
                    //console.log(index + "----" + v_index[index])
                    $(".f-range-msg").eq(index).find(".f-valMax").html((100 - myVal));
                    //$(".f-range-msg").eq(index).find(".f-valMax").html(myVal);
                    //改变信息表最大值
                    if (index != $(".f-hk ").length - 1) {
                        var max = $(".f-range-msg").eq(index + 1).find(".f-valMax").html(v_index[0] - v_index[1]);
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
                    //console.log("index====" + index)
                    v_index[index] = myVal;
                    $(".f-hk").eq(index).find(".f-range-tips").html(myVal + "%");

                    //console.log(v_index[0] + "----" + v_index[1] + "----" + v_index[2] + "----" + v_index[3])
                    //改变信息表最小值
                    $(".f-range-msg").eq(index).find(".f-valMax").html(v_index[index - 1] - v_index[index]);
                    //改变信息表最大值
                    if (index == $(".f-hk ").length - 1) {
                        $(".f-range-msg").eq(index + 1).find(".f-valMax").html(v_index[index]);
                    } else {
                        $(".f-range-msg").eq(index + 1).find(".f-valMax").html(v_index[index] - v_index[index + 1]);
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
            //console.log(getVal + '=======')
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
            $(".f-range-msg").eq(i).find(".f-valMax").html(v_set);
            //初始化最大值
            if (i == $(".f-hk").length - 1) {
                $(".f-range-msg").eq(i + 1).find(".f-valMax").html(v_index[i]);
            }

        }

    })

    function initPer() {
        var v_index = new Array()
        //初始化
        for (var i = 0; i < $(".f-hk").length; i++) {
            //获取百分比
            var getVal = parseInt($(".f-hk").eq(i).find(".f-range-tips").html());
            //console.log(getVal + '=======')
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
            $(".f-range-msg").eq(i).find(".f-valMax").html(v_set);
            //初始化最大值
            if (i == $(".f-hk").length - 1) {
                $(".f-range-msg").eq(i + 1).find(".f-valMax").html(v_index[i]);
            }

        }
    }
});
