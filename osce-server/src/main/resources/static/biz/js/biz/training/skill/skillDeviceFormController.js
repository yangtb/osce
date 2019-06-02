layui.config({
    base: basePath + '/layui/build/js/'
}).extend({
    numinput: 'numinput.min'
}).use(['form', 'layer', 'jquery', 'common', 'numinput', 'tableSelect'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common
        , numinp = layui.numinput
        , tableSelect = layui.tableSelect;

    numinp.init({
        rightBtns: true
    });


    tableSelect.render({
        elem: '#naDevice',
        checkedKey: 'naDevice',
        searchKey: 'naDevice',
        searchPlaceholder: '请输入设备名称',
        table: {
            url: basePath + '/pf/p/model/list',
            height: 260,
            cols: [[
                {type: 'radio'},
                {field: 'naDevice', minWidth: 170, title: '设备名称'},
                {field: 'desDevice', minWidth: 250, title: '设备描述'},
                {field: 'sdDeviceUnit', minWidth: 120, title: '单位'}
            ]]
            , limits: [10, 20, 50]
            , page: true
        },
        done: function (elem, data) {
            var bizData = data.data[0];
            if (bizData) {
                $('#idDevice').val(bizData.idDevice);
                $('#naDevice').val(bizData.naDevice);
            }
        }
    });


    //监听提交
    form.on('submit(addSkillDevice)', function (data) {
        data.field.idSkillCase = idSkillCase;
        layer.load(2);
        $.ajax({
            url: basePath + '/pf/r/skill/device/' + formType,
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    common.sucMsg("保存成功");
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        parent.layui.table.reload('skillDeviceTableId', {
                            height: 'full-68'
                        });
                    }
                    return true;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg("保存失败");
                return false;
            }
        });
        return false;
    });
});



function fullForm(data) {
    $(document).ready(function(){
        $("#skillDeviceForm").autofill(data);
        layui.use('form',function(){
            layui.form.render();
        });

    });
}

