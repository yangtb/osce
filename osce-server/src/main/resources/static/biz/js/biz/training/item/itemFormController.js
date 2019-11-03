layui.config({
    base: basePath + '/layui/build/js/'
}).extend({
    numinput: 'numinput.min'
}).use(['table', 'form', 'jquery', 'element', 'common', 'numinput'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common
        , element = layui.element
        , numinp = layui.numinput;


    $(document).ready(function(){
        if (formType == 'edit') {
            queryCdGroup();
        }
    });

    form.on('select(cdGroupFilter)', function (data) {   //选择 赋值给input框
        $("#cdGroupText").val($("#cdGroup").find("option:selected").text());
        $("#cdGroup").next().find("dl").css({ "display": "none" });
        form.render();
    });

    $("#cdGroupText").on('keyup', function () {
        search()
    });

    window.search = function () {
        var value = $("#cdGroupText").val();
        $("#cdGroup").val(value);
        form.render();
        $("#cdGroup").next().find("dl").css({ "display": "block" });
        var dl = $("#cdGroup").next().find("dl").children();
        var j = -1;
        for (var i = 0; i < dl.length; i++) {
            if (dl[i].innerHTML.indexOf(value) <= -1) {
                dl[i].style.display = "none";
                j++;
            }
            if (j == dl.length-1) {
                $("#hc_select").next().find("dl").css({ "display": "none" });
            }
        }
    }

    form.on('select(sdItemCaFilter)', function (data) {
        if (data.value == 3) {
            $("#queryCommonItem").removeClass("layui-btn-disabled");
            $('#queryCommonItem').removeAttr("disabled", "true");
            $('#cdGroup').removeAttr("disabled", "true");
            $('#cdGroup').removeClass("layui-disabled");
            $('#cdGroupText').removeAttr("disabled", "true");
            $('#cdGroupText').removeClass("layui-disabled");
            queryCdGroup();
        } else {
            $("#queryCommonItem").addClass("layui-btn-disabled");
            $('#queryCommonItem').attr("disabled", "true");
            $('#cdGroup').attr("disabled", "true");
            $('#cdGroup').addClass("layui-disabled");
            $('#cdGroupText').attr("disabled", "true");
            $('#cdGroupText').addClass("layui-disabled");
            $('#cdGroup').val("");
            $('#cdGroupText').val("");
        }
        form.render();
    });

    function queryCdGroup() {
        var bizData = {
            "idItemStore": idItemStore,
            "idItemSection": idItemSection
        };
        $.ajax({
            url: basePath + '/pf/r/item/cd/group/list',
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
                    var listData = data.data;
                    $("#cdGroup").empty();
                    $('#cdGroup').append("<option value=''></option>");
                    $.each(listData, function (index, content) {
                        $('#cdGroup').append("<option value='" + content + "'>" + content + "</option>");
                    });
                    form.render();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                layer.msg("网络异常");
                return false;
            }
        });
    }

    numinp.init({
        rightBtns: true
    });

    form.verify({
        commonLength64: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        commonLength255: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        }
    });

    form.on('submit(addItem)', function (data) {
        var url = basePath + '/pf/r/item/exam/save';
        data.field.idItemStore = idItemStore;
        data.field.idItemSection = idItemSection;
        var oldData = table.cache["itemOptionTableId"];
        data.field.itemOptions = oldData;

        data.field.cdGroup = data.field.cdGroupText;
        if (!oldData || oldData.length == 0) {
            layer.confirm('题目选项未填写，点击【确定】将继续保存', {
                title: '提示',
                resize: false,
                btn: ['确定', '取消'],
                btnAlign: 'c',
                icon: 3,
            }, function (index) {
                layer.close(index);
                data.field.itemOptions = oldData;
                if (!data.field.fgActive) {
                    data.field.fgActive = '0';
                }
                return _addItem(url, data.field, formType, 'itemManageTableId', '保存');
            });
            return false;
        }
        // 1、选项编码重复校验
        var map = {}, fgRightNum = 0;
        $.each(oldData, function (index, content) {
            if (map[content.cdIte]) {
                map[content.cdIte] = map[content.cdIte] + 1;
            } else {
                map[content.cdIte] = 1;
            }

            if (content.fgRight == 1) {
                fgRightNum = fgRightNum + 1;
            }
        });

        var repeatCode = '';
        for (var key in map) {
            if (map[key] > 1) {
                if (!repeatCode) {
                    repeatCode += key;
                } else {
                    repeatCode = repeatCode + "、" + key;
                }
            }
        }

        if (repeatCode) {
            layer.alert('题目选项编码【<span style="color: red; font-weight: bold">' + repeatCode + '</span>】重复，请修改', {
                title: '提示',
                resize: false,
                btn: ['确定']
                , icon: 5
            });
            return false;
        }
        // 2、正确答案校验
        if (data.field.sdItemCa == 2) {
            // 单选
            if (fgRightNum > 1) {
                layer.alert('【<span style="color: red; font-weight: bold">此题目为单选题</span>】，正确答案只有1个，请修改', {
                    title: '提示',
                    resize: false,
                    btn: ['确定']
                    , icon: 5
                });
                return false;
            }
        }
        return _addItem(url, data.field, formType, 'itemManageTableId', '保存');
    });

    var _addItem = function (url, bizData, formType, tableId, msg) {
        layer.load(2);
        $.ajax({
            url: url,
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
                    common.sucMsg(msg + "成功");
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        $('#idItem').val(data.data);
                        parent.layui.table.reload(tableId, {
                            height: 'full-68'
                        });
                    }
                    return false;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg(msg + "失败");
                return false;
            }
        });
        return false;
    }

    if (formType == 'add') {
        //执行渲染
        table.render({
            elem: '#itemOptionTable' //指定原始表格元素选择器（推荐id选择器）
            , id: 'itemOptionTableId'
            , height: '467' //容器高度
            , cols: [[
                {checkbox: true},
                {field: 'cdIte', width: 88, edit: 'text', align: 'center', title: '选项编码'},
                {field: 'naOption', minWidth: 100, edit: 'text', title: '选项内容'},
                {field: 'fgRight', width: 120, title: '正确答案', align: 'center', templet: '#fgRightTpl'}
            ]] //设置表头
            , limit: 500
            , even: true
            , page: false
            , data: []
        });
    }


    //监听删除操作
    form.on('switch(fgRightCheckFilter)', function (obj) {
        var oldData = table.cache["itemOptionTableId"];
        oldData[obj.value - 1].fgRight = obj.elem.checked ? '1' :'0';
        table.reload('itemOptionTableId', {
            data: oldData
        });
    });

    $('#addOption').on('click', function () {
        var oldData = table.cache["itemOptionTableId"];
        if (!oldData) {
            oldData = [];
        }
        var bizData = {
            "cdIte": "",
            "naOption": "",
            "fgRight": "0"
        }
        oldData.push(bizData);
        table.reload('itemOptionTableId', {
            data: oldData
        });
    });

    $("#delOption").on('click', function () {
        var checkStatus = table.checkStatus('itemOptionTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#delOption', {tips: 1});
            return;
        }
        _delOption(data);
    });

    var _delOption = function (currentData) {
        var url = basePath + '/pf/r/item/option/del';
        var reqData = new Array();
        $.each(currentData, function (index, content) {
            if (content.idItemOption) {
                reqData.push(content.idItemOption);
            }
        });

        if (!reqData || reqData.length == 0) {
            _delOptionBack();
            return false;
        }

        var data = {
            list: reqData
        };
        common.commonPost(url, data, '删除', 'delOption', _delOptionBack, true);
        return false;
    };
    
    var _delOptionBack = function () {
        // 加载表格
        var reqData = {
            idItem: $('#idItem').val()
        }
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/item/option/list',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    //执行渲染
                    table.render({
                        elem: '#itemOptionTable' //指定原始表格元素选择器（推荐id选择器）
                        , id: 'itemOptionTableId'
                        , height: '467' //容器高度
                        , cols: [[
                            {checkbox: true},
                            {field: 'cdIte', width: 88, edit: 'text', align: 'center', title: '选项编码'},
                            {field: 'naOption', minWidth: 100, edit: 'text', title: '选项内容'},
                            {field: 'fgRight', width: 120, title: '正确答案', align: 'center', templet: '#fgRightTpl'}
                        ]] //设置表头
                        , limit: 500
                        , even: true
                        , page: false
                        , data: data.data
                    });
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("获取题目选项失败");
                return false;
            }
        });
    }


    $('#queryCommonItem').on('click', function () {
        if (!$("#cdGroupText").val()) {
            layer.tips('请先填写或选择分组编码', '#cdGroupText', {tips: 1});
            return;
        }
        layer.open({
            type: 1,
            title: '【' + $("#cdGroupText").val() + '】同组题目',
            shadeClose: true,
            shade: 0.8,
            area: ['860px', '450px'],
            content: '<div style="padding: 0px 10px 0px 10px"><table id="itemManageTable1">\n' +
                '     </table></div>',
            success: function(layero, index){
                table.render({
                    elem: '#itemManageTable1' //指定原始表格元素选择器（推荐id选择器）
                    , id: 'itemManageTable1Id'
                    , height: '385' //容器高度
                    , cols: [[
                        {type: 'numbers', fixed: true, title: 'R'},
                        {field: 'mainItem', minWidth: 250, title: '题干'},
                        {field: 'sdItemCa', width: 100, title: '题目类型', templet: '#sdItemCaTpl'},
                        {field: 'sdItemLevel', width: 100, title: '难度', templet: '#sdItemLevelTpl'},
                        {field: 'gmtCreate', width: 170, title: '创建时间'}
                    ]] //设置表头
                    , url: basePath + '/pf/p/item/manage/list'
                    , limit: 15
                    , even: true
                    , limits: [15, 30, 100]
                    , page: true
                    , where: {
                        idItemStore: idItemStore,
                        idItemSection: idItemSection,
                        cdGroup : $("#cdGroupText").val()
                    }
                });
            }
        });
    });

});

function fullForm(data) {
    $(document).ready(function(){
        if (data.sdItemCa == 3) {
            $("#queryCommonItem").removeClass("layui-btn-disabled");
            $('#queryCommonItem').removeAttr("disabled", "true");
            $('#cdGroup').removeAttr("disabled", "true");
            $('#cdGroup').removeClass("layui-disabled");
            $('#cdGroupText').removeAttr("disabled", "true");
            $('#cdGroupText').removeClass("layui-disabled");
            data.cdGroupText =  data.cdGroup;
        }
        $("#itemForm").autofill(data);
        layui.use(['table', 'form', 'common'],function(){
            var $ = layui.$
                , form = layui.form
                , table = layui.table
                , common = layui.common;
            // 渲染表单
            form.render();

            // 加载表格
            var reqData = {
                idItem: data.idItem
            }
            layer.load(2);
            $.ajax({
                url: basePath + '/pf/r/item/option/list',
                type: 'post',
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify(reqData),
                success: function (data) {
                    layer.closeAll('loading');
                    if (data.code != 0) {
                        common.errorMsg(data.msg);
                        return false;
                    } else {
                        //执行渲染
                        if (formType == 'edit') {
                            table.render({
                                elem: '#itemOptionTable' //指定原始表格元素选择器（推荐id选择器）
                                , id: 'itemOptionTableId'
                                , height: '467' //容器高度
                                , cols: [[
                                    {checkbox: true},
                                    {field: 'cdIte', width: 88, edit: 'text', align: 'center', title: '选项编码'},
                                    {field: 'naOption', minWidth: 100, edit: 'text', title: '选项内容'},
                                    {field: 'fgRight', width: 120, title: '正确答案', align: 'center', templet: '#fgRightTpl'}
                                ]] //设置表头
                                , limit: 500
                                , even: true
                                , page: false
                                , data: data.data
                            });
                        }
                        return true;
                    }
                },
                error: function () {
                    layer.closeAll('loading');
                    common.errorMsg("查询题目选项失败");
                    return false;
                }
            });
        });
    });
}

