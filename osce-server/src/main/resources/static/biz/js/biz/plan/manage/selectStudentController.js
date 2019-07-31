layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'treeSelect'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , treeSelect = layui.treeSelect;

    treeSelect.render({
        elem: '#idDepart',
        data: basePath + '/pf/r/dept/tree/select',
        type: 'post',
        placeholder: '请选择班级',
        click: function (d) {
            $("#idDepart").val(d.current.id)
        },
        // 加载完成后的回调函数
        success: function (d) {

        }
    });

    //执行渲染
    table.render({
        elem: '#selectStudentTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'selectStudentTableId'
        , height: 'full-105' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'naDepart', minWidth: 130, title: '班级'},
            {field: 'username', minWidth: 120, title: '账号'},
            {field: 'realName', minWidth: 130, title: '姓名'},
            {field: 'sex', width: 80, title: '性别', templet: '#sexTpl'},
            {field: 'phoneNo', minWidth: 130, title: '手机号'},
            {field: 'idcard', minWidth: 190, title: '身份证号'},
        ]] //设置表头
        , url: basePath + '/pf/p/student/list'
        , limit: 50
        , even: true
        , limits: [50, 100]
        , page: true
        , size: 'sm'
    });

    //监听提交
    form.on('submit(selectStudentSearchFilter)', function (data) {
        table.reload('selectStudentTableId', {
            where: {
                idDepart: data.field.idDepart,
                keywords : data.field.realName
            }
            , height: 'full-120'
        });
    });

    $("#selectStudent").on('click', function () {
        var checkStatus = table.checkStatus('selectStudentTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#selectStudent', {tips: 1});
            return;
        }
        saveTpStudent(data);
    });

    function saveTpStudent(data) {
        var reqData = new Array();
        $.each(data, function (index, content) {
            reqData.push(content.idStudentDepart);
        });
        var bizData = {
            idUsers : reqData,
            idPlan : idPlan
        };
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/plan/manage/add/student',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    layer.tips(data.msg, '#selectStudent', {tips: 1});
                    return false;
                } else {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    //刷新父页面table
                    parent.layui.table.reload("assignedStudentTableId");
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                layer.tips("保存失败", '#selectStudent', {tips: 1});
                return false;
            }
        });
    }

});



