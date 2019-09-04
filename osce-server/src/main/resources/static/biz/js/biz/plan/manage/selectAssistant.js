layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , common = layui.common;

    //执行渲染
    table.render({
        elem: '#assistantTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'assistantTableId'
        , height: 'full-50' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'idUserManagerCheck', width: 70, title: '主考官', align:'center',  fixed: true, templet: '#idUserManagerTpl'},
            {field: 'idUserAssistantCheck', width: 60, title: '考官', align:'center', fixed: true, templet: '#idUserAssistantTpl'},
            {field: 'idUserRemoteCheck', width: 85, title: '中控考官', align:'center', fixed: true, templet: '#idUserRemoteTpl'},
            {field: 'realName', minWidth: 110, title: '姓名', fixed: true},
            {field: 'sex', width: 80, title: '性别', templet: '#sexTpl'},
            {field: 'phoneNo', minWidth: 150, title: '手机号'},
            {field: 'idcard', minWidth: 200, title: '身份证号'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
        ]] //设置表头
        , url: basePath + '/pf/p/assistant/list'
        , limit: 15
        , even: true
        , limits: [15, 30, 50, 100]
        , page: true
        , size: 'sm'
    });

    //监听提交
    form.on('submit(assistantSearchFilter)', function (data) {
        table.reload('assistantTableId', {
            where: {
                keywords: data.field.keywords
            }
            , height: 'full-50'
        });
    });

    //监听操作
    form.on('checkbox(idUserManagerCheckFilter)', function (obj) {
        var arr = this.value.split("-");
        var userId = arr[0];
        var realName = arr[1];
        if (obj.elem.checked) {
            if ($('#managerBtn').length > 0) {
                $('#managerBtn').remove();
            }
            var html = '<button id="managerBtn" type="button" class="layui-btn layui-btn-sm">\n' +
                '          <input id="idUserManager" hidden>\n' +
                '          <span id="idUserManagerText">'+ realName +'</span> <i class="layui-icon" id="managerClose">&#x1007;</i>\n' +
                '       </button>';
            $('#manager').append(html);
            $('#idUserManager').val(userId);
            addDelListen('manager');
        } else {
            $('#managerBtn').remove();
        }
    });

    form.on('checkbox(idUserAssistantCheckFilter)', function (obj) {
        var arr = this.value.split("-");
        var userId = arr[0];
        var realName = arr[1];
        if (obj.elem.checked) {
            if ($('#assistantBtn').length > 0) {
                $('#assistantBtn').remove();
            }
            var html = '<button id="assistantBtn" type="button" class="layui-btn layui-btn-sm">\n' +
                '          <input id="idUserAssistant" hidden>\n' +
                '          <span id="idUserAssistantText">'+ realName +'</span> <i class="layui-icon" id="assistantClose">&#x1007;</i>\n' +
                '       </button>';
            $('#assistant').append(html);
            $('#idUserAssistant').val(userId);
            addDelListen('assistant');
        } else {
            $('#assistantBtn').remove();
        }
    });

    form.on('checkbox(idUserRemoteCheckFilter)', function (obj) {
        var arr = this.value.split("-");
        var userId = arr[0];
        var realName = arr[1];
        if (obj.elem.checked) {
            if ($('#remoteBtn').length > 0) {
                $('#remoteBtn').remove();
            }
            var html = '<button id="remoteBtn" type="button" class="layui-btn layui-btn-sm">\n' +
                '          <input id="idUserRemote" hidden>\n' +
                '          <span id="idUserRemoteText">'+ realName +'</span> <i class="layui-icon" id="remoteClose">&#x1007;</i>\n' +
                '       </button>';
            $('#remote').append(html);
            $('#idUserRemote').val(userId);
            addDelListen('remote');
        } else {
            $('#remoteBtn').remove();
        }
    });

    function addDelListen(id) {
        document.getElementById(id + 'Close').addEventListener('click', function () {
            $('#' + id + 'Btn').remove();
        });
    }

    $(document).ready(function(){
        loadAssistant();
    });

    function loadAssistant() {
        var arr = sq.split("-");
        var bizData = {
            idPlan: idPlan,
            idArea: arr[0],
            idStation: arr[1],
            timeSection: arr[2],
            idRoom: arr[3]
        }
        common.commonPost(basePath + '/pf/r/plan/station/select/assistant',
            bizData, null, null, fullLeft, true);
    }

    function fullLeft(data) {
        var sucData = data.data;
        if (sucData && sucData.idUserManager) {
            if ($('#managerBtn').length > 0) {
                $('#managerBtn').remove();
            }
            var html = '<button id="managerBtn" type="button" class="layui-btn layui-btn-sm">\n' +
                '          <input id="idUserManager" hidden>\n' +
                '          <span id="idUserManagerText">'+ sucData.managerName +'</span> <i class="layui-icon" id="managerClose">&#x1007;</i>\n' +
                '       </button>';
            $('#manager').append(html);
            $('#idUserManager').val(sucData.idUserManager);
            addDelListen('manager');
        }
        if (sucData && sucData.idUserAssistant) {
            var html = '<button id="assistantBtn" type="button" class="layui-btn layui-btn-sm">\n' +
                '          <input id="idUserAssistant" hidden>\n' +
                '          <span id="idUserAssistantText">'+ sucData.assistantName +'</span> <i class="layui-icon" id="assistantClose">&#x1007;</i>\n' +
                '       </button>';
            $('#assistant').append(html);
            $('#idUserAssistant').val(sucData.idUserAssistant);
            addDelListen('assistant');
        }
        if (sucData && sucData.idUserRemote) {
            addDelListen('assistant');
            var html = '<button id="remoteBtn" type="button" class="layui-btn layui-btn-sm">\n' +
                '          <input id="idUserRemote" hidden>\n' +
                '          <span id="idUserRemoteText">'+ sucData.remoteName +'</span> <i class="layui-icon" id="remoteClose">&#x1007;</i>\n' +
                '       </button>';
            $('#remote').append(html);
            $('#idUserRemote').val(sucData.idUserRemote);
            addDelListen('remote');
        }
    }

});


