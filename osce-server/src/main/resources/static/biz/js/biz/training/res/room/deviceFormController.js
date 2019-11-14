layui.config({
    base: basePath + '/layui/build/js/'
}).use(['form', 'layer', 'jquery', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common;

    form.verify({
        cdRoomDevice: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        desDevice: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        },
        roomDeviceAddress: function (value) {
            if (value && value.length > 512) {
                return '长度不能超过512个字';
            }
        },
    });

    //监听提交
    form.on('submit(addDevice)', function (data) {
        data.field.idRoom = idRoom;
        var url = basePath + '/pf/r/device/';
        if (formType == 'add') {
            url += 'add';
        } else if (formType == 'edit') {
            url += 'edit';
        }
        return common.commonParentFormPost(url, data.field, formType, 'deviceTableId', '保存');
    });
});

