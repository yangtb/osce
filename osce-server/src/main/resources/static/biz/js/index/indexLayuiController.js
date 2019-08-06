/**
 * 首页
 */
layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'common'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        common = layui.common;

    $("#modifyPass").on('click', function () {
        var index = layui.layer.open({
            title: '<b>' + '<i class="iconfont icon-modifyPass"></i>' + ' 修改密码' + '</b>',
            //skin: 'layui-layer-molv', //样式类名
            type: 2,
            area: ['450px', '280px'],
            anim: 0,
            fixed: false, //不固定
            maxmin: false,
            content: basePath + '/pf/p/user/modifyPass',
            shadeClose: true
        });
        return index;
    });

    var url = basePath + '/openplatformlogout';
    $('#logout').on('click', function () {
        parent.layer.confirm('你真的确定要退出系统吗？', {
            title: '<b>' + '退出登录提示！' + '</b>',
            resize: false,
            btn: ['确定退出系统', '不，我点错了！'],
            btnAlign: 'c',
            icon: 3
        }, function () {
            location.href = url
        }, function () {

        })
    })

    if (isAnonymousUser == 'true') {
        layer.msg('您当前已进入游客模式！', {
            time: 0 //不自动关闭
            , btn: ['知道了', '已有账号，点击登陆']
            , yes: function (index) {
                layer.close(index);
            }, btn2: function () {
                if (window != top) {
                    top.location.href = basePath + "/login";
                } else {
                    window.location.href = basePath + "/login";
                }
            }
        });
    }

});


function spreadMenu() {
    var dpiWidth = window.screen.width;
    if (dpiWidth < 1440) {
        if (!$('div').is('.layadmin-side-shrink')) {
            $('#spreadMenu').click();
        }
    }

}