layui.config({
    base: basePath + '/layui/build/js/'
}).use(['table', 'form', 'jquery', 'treeSelect', 'common'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , treeSelect = layui.treeSelect
        , common = layui.common;

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
        elem: '#spTable' //指定原始表格元素选择器（推荐id选择器）
        , id: 'spTableId'
        , height: 'full-50' //容器高度
        , cols: [[
            {type: 'numbers', fixed: true, title: 'R'},
            {field: 'checkSp', width: 50, fixed: true, templet: '#checkSpTpl'},
            {field: 'realName', minWidth: 140, title: '姓名', fixed: true},
            {field: 'sex', width: 80, title: '性别', templet: '#sexTpl'},
            {field: 'phoneNo', minWidth: 150, title: '手机号'},
            {field: 'idcard', minWidth: 200, title: '身份证号'},
            {field: 'gmtCreate', minWidth: 170, title: '创建时间'},
        ]] //设置表头
        , url: basePath + '/pf/p/sp/cache/list?idPlan=' + idPlan
        , limit: 50
        , even: true
        , limits: [50, 100]
        , page: true
        , size: 'sm'
    });

    //监听提交
    form.on('submit(spSearchFilter)', function (data) {
        table.reload('spTableId', {
            where: {
                keywords: data.field.keywords
            }
            , height: 'full-50'
        });
    });

    //监听删除操作
    form.on('checkbox(spCheckFilter)', function (obj) {
        var arr = this.value.split("-");
        var userId = arr[0];
        var realName = arr[1];
        if (obj.elem.checked) {
            if ($('#div-sp-' + userId).length > 0) {
                return;
            }
            var html = '<div class="div-sp" id="div-sp-' + userId + '">\n' +
                '          <button type="button" class="layui-btn layui-btn-sm">\n' +
                '              <span class="spRealName" id="spRealName-' + userId + '" data-id="' + userId + '">'+ realName +'</span> <i class="layui-icon" id="delSp' + userId + '">&#x1007;</i>\n' +
                '          </button>\n' +
                '       </div>';
            $('#sp-ed').append(html);
            addDelSpListen(userId);
        } else {
            $('#div-sp-' + userId).remove();
        }
    });

    function addDelSpListen(userId) {
        document.getElementById('delSp' + userId).addEventListener('click', function () {
            $('#div-sp-' + userId).remove();
        });
    }

    $(document).ready(function(){
        loadSp();
    });

    function loadSp() {
        var arr = sq.split("-");
        var bizData = {
            idPlan: idPlan,
            idArea: arr[0],
            idStation: arr[1],
            timeSection: arr[2],
            idRoom: arr[3]
        }
        common.commonPost(basePath + '/pf/r/plan/station/select/sp',
            bizData, null, null, fullLeft, true);
    }

    function fullLeft(data) {
        var sucData = data.data;
        if (sucData) {
            $.each(sucData, function (index, content) {
                var html = '<div class="div-sp" id="div-sp-' + content.idUser + '">\n' +
                    '          <button type="button" class="layui-btn layui-btn-sm">\n' +
                    '              <span class="spRealName" id="spRealName-' + content.idUser + '" data-id="' + content.idUser + '">'+ content.realName +'</span> <i class="layui-icon" id="delSp' + content.idUser + '">&#x1007;</i>\n' +
                    '          </button>\n' +
                    '       </div>';
                $('#sp-ed').append(html);
                addDelSpListen(content.idUser);
            });

        }
    }

});


