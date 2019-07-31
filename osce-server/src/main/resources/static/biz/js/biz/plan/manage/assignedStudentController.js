layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , common = layui.common;

    //执行渲染
    table.render({
        elem: '#assignedStudentTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'assignedStudentTableId'
        , height: 600 //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {checkbox: true, fixed: true},
            {field: 'naDepart', width: 140, title: '班级'},
            {field: 'username', minWidth: 150, title: '账号'},
            {field: 'realName', minWidth: 120, title: '姓名'},
            {field: 'sex', width: 80, title: '性别'},
            {field: 'phoneNo', minWidth: 120, title: '手机号'},
            {field: 'idcard', width: 180, title: '身份证号'},
            {fixed: 'right', width: 100, title: '操作', align: 'center', toolbar: '#studentBar'}
        ]] //设置表头
        , url: basePath + '/pf/p/assigned/student/list'
        , limit: 1000
        , even: true
        , page: false
        , where :{
            idPlan : idPlan
        }
    });

    //监听工具条
    table.on('tool(assignedStudentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            var currentData = new Array();
            currentData.push(data)
            _delStudent(currentData);
        }
    });

    $("#del").on('click', function () {
        var checkStatus = table.checkStatus('assignedStudentTableId')
            , data = checkStatus.data;
        if (data.length == 0) {
            layer.tips('请先选中一行记录', '#del', {tips: 1});
            return;
        }
        _delStudent(data);
    });

    var _delStudent = function (currentData) {
        var url = basePath + '/pf/r/plan/assigned/student/del';
        var reqData = new Array();
        var messageTitle = '';
        $.each(currentData, function (index, content) {
            if (messageTitle) {
                messageTitle += ', ';
            }
            messageTitle += '【' + content.realName + '】';
            reqData.push(content.idUser);
        });

        var data = {
            list : reqData,
            extId : idPlan
        };
        layer.confirm('确定删除' + messageTitle + '么？', {
            title: '删除学生提示',
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
                    _assignedStudentTableReload();
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

    var _assignedStudentTableReload = function () {
        table.reload('assignedStudentTableId');
    }

    $("#selectStudent").on('click', function () {
        var y = $("#selectStudent").offset().top + $("#selectStudent").outerHeight() + "px";
        var x = ($("#selectStudent").offset().left + 1) + "px";
        var index = layui.layer.open({
            title: '<b>选择学员</b>',
            type: 2,
            area: ['800px', '500px'],
            offset: [y , x],
            fixed: false,
            maxmin: false,
            content: basePath + '/pf/p/plan/manage/student/select?idPlan=' + idPlan,
            shadeClose: true
        });
    });

});



