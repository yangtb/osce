layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['index', 'layer', 'form', 'table', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        table = layui.table,
        common = layui.common;

    //监听提交
    form.on('submit(spSearchFilter)', function (data) {
        table.reload('spTableId', {
            where: {
                keywords: data.field.keywords
            }
            , height: 'full-110'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    //执行渲染
    table.render({
        elem: '#spTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'spTableId'
        , height: 'full-110' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'realName', minWidth: 140, title: '姓名', fixed: true},
            {field: 'sex', minWidth: 80, title: '性别', templet: '#sexTpl'},
            {field: 'phoneNo', minWidth: 150, title: '手机号'},
            {field: 'idcard', minWidth: 200, title: '身份证号'},
            {field: 'enabled', width: 80, title: '状态', align: 'center', templet: '#enabledTpl'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#spBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/sp/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

    //监听工具条
    table.on('tool(spTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    $('#add').on('click', function () {
        _addOrEdit("add");
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('spTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#edit', {tips: 1});
            return;
        }
        if (data.length > 1) {
            layer.tips('请选中一行记录进行编辑', '#edit', {tips: 1});
            return;
        }
        var currentEditData = data[0];
        _addOrEdit("edit", currentEditData);
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('spTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delSp(data);
    });

    var _delSp = function (currentData) {
        var url = basePath + '/pf/r/sp/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.realName + '】';
            reqData.push(content.userId);
        });

        var data = {};
        data.list = reqData;
        data.status = '1';
        layer.confirm('确定删除' + messageTitle + '么？', {
            title: '删除SP提示',
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            icon: 3
        }, function (index) {
            _commonAjax(index, url, data, "删除");
        })
    }

    var _commonAjax = function (index, url, reqData, msg) {
        layer.load(2);
        $.ajax({
            url: url,
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
                    common.sucChildMsg(msg + "成功");
                    if (index) {
                        layer.close(index);
                    }
                    _spTableReload();
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg(msg + "失败");
                return false;
            }
        });
    }

    var _spTableReload = function () {
        table.reload('spTableId', {
            where: {
                //type: type
            },
            height: 'full-110'
        });
    }

    var _addOrEdit = function (formType, currentEditData) {
        if (showFlag == 1) {
            if (formType == 'add') {
                parent.layui.index.openTabsPage(basePath + '/pf/p/sp/form?formType=' + formType, "新增SP"); //这里要注意的是 parent 的层级关系
            } else {
                parent.layui.index.openTabsPage(basePath + '/pf/p/sp/form?formType=' + formType + '&userId=' + currentEditData.userId , "编辑SP"); //这里要注意的是 parent 的层级关系
            }
        } else {
            if (formType == 'add') {
                var index = common.open('新增SP', basePath + '/pf/p/sp/form?formType=' + formType, 700, 420);
                layer.full(index)
            } else {
                var index = common.open('编辑SP', basePath + '/pf/p/sp/form?formType=' + formType + '&userId=' + currentEditData.userId, 700, 420);
                layer.full(index)
            }
        }

    };

    //监听行双击事件
    table.on('rowDouble(spTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

});

