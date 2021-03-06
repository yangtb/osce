/**
 * 首页
 */
var message;
layui.config({
    base: basePath + '/layui/build/js/'
}).use(['app', 'message', 'common'], function () {
    var app = layui.app,
        $ = layui.jquery,
        layer = layui.layer,
        common = layui.common;
    //将message设置为全局以便子页面调用
    message = layui.message;
    //主入口
    app.set({
        type: 'iframe'
    }).init();


    $('dl.skin > dd').on('click', function () {
        var $that = $(this);
        var skin = $that.children('a').data('skin');
        switchSkin(skin);
    });

    var setSkin = function (value) {
            layui.data('kit_skin', {
                key: 'skin',
                value: value
            });
        },
        getSkinName = function () {
            return layui.data('kit_skin').skin;
        },
        switchSkin = function (value) {
            var _target = $('link[kit-skin]')[0];
            _target.href = _target.href.substring(0, _target.href.lastIndexOf('/') + 1) + value + _target.href.substring(_target.href.lastIndexOf('.'));
            setSkin(value);
        },
        initSkin = function () {
            var skin = getSkinName();
            switchSkin(skin === undefined ? 'default' : skin);
        }();

    // 只展开一个二级菜单
    $(".kit-side ul li.layui-nav-item").each(function () {
        $(this).on('click', function () {
            $(this).siblings().removeClass('layui-nav-itemed');
        });
    });

    $("#modifyPass").on('click', function () {
        common.open('<i class="iconfont icon-modifyPass"></i>' + ' 修改密码',
            basePath + '/pf/p/user/modifyPass', 450, 280, null, '3');
    });

    var url = basePath + '/openplatformlogout';
    $('#logout').on('click', function () {
        common.logOut('退出登录提示！', '你真的确定要退出系统吗？', url)
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


    $('#fullScreen').on('click', function () {
        $("#exitScreen").css("display","block");
        $("#fullScreen").css("display","none");
        fullScreen();
    });

    $('#exitScreen').on('click', function () {
        $("#exitScreen").css("display","none");
        $("#fullScreen").css("display","block");
        exitScreen();
    });


    //全屏
    function fullScreen() {
        var el = document.documentElement;
        var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullscreen;
        if (typeof rfs != "undefined" && rfs) {
            rfs.call(el);
        }
        return;
    }

    //退出全屏
    function exitScreen() {
        if (document.exitFullscreen) {
            document.exitFullscreen();
        }
        else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        }
        else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }
        else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
        if (typeof cfs != "undefined" && cfs) {
            cfs.call(el);
        }
    }

});
