layui.config({
    base: basePath + '/layui/build/js/'
}).use(['layer', 'form', 'table', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        table = layui.table,
        common = layui.common;

    // ===============  ztree =========================//
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true
        },
        check: {
            enable: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };

    var zNodes = [], zTree;
    $(document).ready(function () {
        layer.load(2);
        var bizData = {
            idGrade: currentGrade
        }
        $.ajax({
            url: basePath + '/pf/r/dept/tree',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                zNodes = data.data;
                $.fn.zTree.init($("#departTree"), setting, zNodes);
                zTree = $.fn.zTree.getZTreeObj("departTree");
                return true;
            },
            error: function () {
                layer.closeAll('loading');
                return false;
            }
        });
        var bodyHeight = $(this).height() - $("#treeTitle").height() - 70;
        $("#departTree").css("min-height", bodyHeight);
        $("#departTree").css("max-height", bodyHeight);
    });

    //监听提交
    form.on('submit(deptSearchFilter)', function (data) {
        layer.load(2);
        var bizData = {
            idGrade: data.field.idGrade
        }
        $.ajax({
            url: basePath + '/pf/r/dept/tree',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                zNodes = data.data;
                $.fn.zTree.init($("#departTree"), setting, zNodes);
                return true;
            },
            error: function () {
                layer.closeAll('loading');
                return false;
            }
        });
    });

    // 选中tree
    function onClick(e, treeId, treeNode) {

    };

    //监听提交
    form.on('submit(studentSearchFilter)', function (data) {
        table.reload('studentTableId', {
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
        elem: '#studentTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'studentTableId'
        , height: 'full-110' //容器高度
        , cols: [[
            {checkbox: true, fixed: true},
            {field: 'naGrade', minWidth: 120, title: '班级', fixed: true},
            {field: 'username', minWidth: 130, title: '账号'},
            {field: 'realName', minWidth: 140, title: '姓名'},
            {field: 'sex', minWidth: 80, title: '性别', templet: '#sexTpl'},
            {field: 'phoneNo', minWidth: 150, title: '手机号'},
            {field: 'idcard', minWidth: 200, title: '身份证号'},
            {field: 'enabled', width: 80, title: '状态',align : 'center', templet: '#enabledTpl'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
            {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#studentBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/student/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 100]
        , page: true
    });

    //监听工具条
    table.on('tool(studentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            _addOrEdit("edit", data);
        }
    });

    $('#add').on('click', function () {
        _addOrEdit("add");
    });

    $('#edit').on('click', function () {
        var checkStatus = table.checkStatus('studentTableId')
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

    var _addOrEdit = function (formType, currentEditData) {
        if (formType == 'add') {
            common.open('新增学员', basePath + '/pf/p/student/form?formType=' + formType, 700, 420);
        } else {
            common.open('编辑学员', basePath + '/pf/p/student/form?formType=' + formType, 700, 295, _successFunction(currentEditData));
        }
    };

    var _successFunction = function (data) {
        return function (layero, index) {
            var iframe = window['layui-layer-iframe' + index];
            //调用子页面的全局函数
            iframe.fullForm(data);
        }
    };

    //监听行双击事件
    table.on('rowDouble(studentTableFilter)', function (obj) {
        _addOrEdit("edit", obj.data);
    });

});

