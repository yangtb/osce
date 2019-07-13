layui.config({
    base: basePath + '/layui/plugins/'
}).extend({
    index: 'lib/index', //主入口模块
}).use(['layer', 'index', 'admin'], function () {
    var $ = layui.$
        , admin = layui.admin;

    $(document).ready(function () {
        admin.fullScreen();
    });

    setInterval(function () {
        setNowTime();
    }, 1000);

    function setNowTime() {
        document.getElementById("nowtime").innerHTML = nowTimeStr(1);
    }

    function nowTimeStr(flag) {
        //获取年月日
        var time = new Date();

        //获取时分秒
        var h = time.getHours();
        var m = time.getMinutes();
        var s = time.getSeconds();

        //检查是否小于10
        h = check(h);
        m = check(m);
        s = check(s);
        if (flag == 1) {
            return h + ":" + m + ":" + s;
        } else {
            return h + ":" + m;
        }
    }

    //时间数字小于10，则在之前加个“0”补位。
    function check(i) {
        //方法一，用三元运算符
        var num;
        i < 10 ? num = "0" + i : num = i;
        return num;
    }


    var qrcode = new QRCode(document.getElementById("qrcode"), {
        width : 160,
        height : 160
    });
    qrcode.makeCode("http://www.runoob.com");

});


