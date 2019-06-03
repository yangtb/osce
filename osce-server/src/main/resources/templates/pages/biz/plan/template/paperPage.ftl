<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>考卷设计</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath!}';
        var idModel = '${idModel!}';
    </script>

    <style>
        ul li {
            list-style-type: disc;
            font-size: 13px;
        }

        ul {
            cursor: pointer;
            padding-bottom: 5px;
            font-family: & quot;
            Arial Normal & quot;, Arial;
            border-right: 1px solid rgb(217, 217, 217);
        }

        .select {
            font-weight: bold;
            color: rgb(24, 144, 255);
            border-right: 2px solid rgb(24, 144, 255);
        }

        .mouseOver {
            font-weight: bold;
            color: rgb(24, 144, 255);
            border-right: 2px solid rgb(24, 144, 255);
        }

        .left {
            padding-left: 35px;
            padding-top: 15px;
            width: 200px
        }

        .pageHeight {
            min-height: 1050px
        }


        #div1, #div2 {
            display: inline-block;

        }

        #wrap {
            display: flex;
        }
    </style>
</head>

<body class="body-bg">

<div class="wrapper-content-new">

    <div id="wrap" class="pageHeight">
        <div id="div1" class="left">
            <ul class="paper-ul select" data-index="1">
                <li>内科</li>
                <li>机考考站</li>
                <li>房间：201、202、204</li>
            </ul>

            <ul class="paper-ul" data-index="2">
                <li>内科</li>
                <li>技能操作</li>
                <li>房间：201、202、204</li>
            </ul>

            <ul class="paper-ul" data-index="3">
                <li>内科</li>
                <li>问诊查体</li>
                <li>房间：201、202、204</li>
            </ul>
        </div>
        <div id="div2" style="width: 100%">
            <iframe id="paperTag" class='layui-col-xs12 pageHeight' frameborder="0" src=""></iframe>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/plan/template/templateController.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>

<script>
    var ul = document.querySelectorAll(".paper-ul");
    for (var i = 0; i < ul.length; i++) {
        ul[i].addEventListener('click', function () {
            console.log(this)
            $(this).addClass("select").siblings().removeClass("select");
            loadIframe(this.getAttribute('data-index'));
        });
        ul[i].addEventListener('mouseover', function () {
            $(this).addClass("mouseOver").siblings().removeClass("mouseOver");
        });
        ul[i].addEventListener('mouseout', function () {
            $(this).removeClass("mouseOver");
        });
    }
    ul[0].click();


    function loadIframe(dataIndex) {
        if (dataIndex == 1) {
            $('#paperTag').attr('src', basePath + '/pf/p/plan/paper/skill/one?idModel=' + 1);
        } else if (dataIndex == 2) {
            $('#paperTag').attr('src', basePath + '/pf/p/plan/paper/skill/two?idModel=' + 1);
        } else if (dataIndex == 3) {
            $('#paperTag').attr('src', basePath + '/pf/p/plan/paper/skill/third?idModel=' + 1);
        }
    }
</script>

</body>

</html>