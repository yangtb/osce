<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
   <#-- <link href="${basePath!}/biz/img/logo/favicon.ico" rel="shortcut icon">-->
    <title>${homeInfo.websiteName!}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath!}/layui/plugins/style/admin.css" media="all">
    <link rel="stylesheet" type="text/css" href="${contextPath!}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath!}';
        var isAnonymousUser = '${homeInfo.anonymousUser? string ("true","false")!}';
        var fgActive = '<#if (homeInfo.sysOrg)??>${homeInfo.sysOrg.fgActive!}</#if>';
    </script>

</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible"  lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" id="spreadMenu" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
                <#if homeInfo.topMenus?? && (homeInfo.topMenus?size > 0)>
                    <#list homeInfo.topMenus as element>
                        <li class="layui-nav-item" lay-unselect>
                            <a <#if element.parentUrl??> href="javascript:;" <#else> lay-href="${contextPath!}$element.parentUrl </#if> ">
                                <i class="iconfont $element.parentImg"></i> <cite>${element.parentMenuName!}</cite>
                                <#if element.groupList?? && (element.groupList?size > 0)>&nbsp;&nbsp;&nbsp;&nbsp;</#if>
                            </a>
                            <#if element.groupList?? && (element.groupList?size > 0)>
                            <dl class="layui-nav-child">
                                <#list element.groupList as menu>
                                <dd>
                                    <a lay-href="${contextPath!}${menu.url!}">&nbsp;&nbsp;&nbsp;<i class="iconfont ${menu.img!}"></i> ${menu.name!}&nbsp;&nbsp;&nbsp;</a>
                                </dd>
                                </#list>
                            </dl>
                            </#if>
                        </li>
                    </#list>
                </#if>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

                <#if showMessage == true>
                    <#--<li class="layui-nav-item" lay-unselect>
                        <a lay-href="${contextPath!}/pf/p/notice/page?type=see" layadmin-event="message" lay-text="消息中心">
                            <i class="layui-icon layui-icon-notice"></i>
                            <!-- 如果有新消息，则显示小圆点 &ndash;&gt;
                            <span class="layui-badge-dot"></span>
                        </a>
                    </li>-->
                </#if>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="note">
                        <i class="layui-icon layui-icon-note"></i>
                    </a>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="fullscreen">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>

                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <img src="${basePath!}/biz/img/user-head-default.png" class="layui-nav-img">
                        <cite>${homeInfo.username!}</cite>
                    </a>
                    <#if homeInfo.anonymousUser == false>
                        <dl class="layui-nav-child">
                            <#if homeInfo.sysOrg?? && showOrgPage==true>
                                <dd>
                                    <a id="orgInfo" lay-href="${basePath!}/pf/p/org/form?formType=edit&position=index&idOrg=${homeInfo.sysOrg.idOrg!}">
                                        <i class="iconfont icon-organization"></i><span> 机构资料</a>
                                </dd>
                            </#if>
                            <dd id="modifyPass"><a href="javascript:;">
                                <i class="iconfont icon-modifyPass"></i><span> 修改密码</span></a></dd>
                        </dl>
                    </#if>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a id="logout" href="javascript:;">
                        <i class="iconfont icon-log-out1" style="color: #f03f2d"></i> 注销
                    </a>
                </li>

            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo">
                    <#--<strong>${homeInfo.websiteName!}</strong>-->
                    <strong>Medmod</strong>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">
                    <#if homeInfo.leftMenus?? && (homeInfo.leftMenus?size > 0)>
                        <#list homeInfo.leftMenus as element>
                            <li data-name="${element.parentMenuId!}"
                                class="layui-nav-item <#if element_index == 0>layui-nav-itemed</#if>">
                                <a href="javascript:;" lay-tips="${element.parentMenuName!}" lay-direction="2">
                                    <i class="layui-icon iconfont ${element.parentImg!}"></i>
                                    <cite>${element.parentMenuName!}</cite>
                                </a>
                                <#if element.groupList?? && (element.groupList?size > 0)>
                                <dl class="layui-nav-child">
                                    <#list element.groupList as menu>
                                        <#if menu.children?? && (menu.children?size > 0)>
                                            <dd data-name="${menu.name!}">
                                                <a href="javascript:;" ><i class="iconfont ${menu.img!}"></i> ${menu.name!}</a>
                                                <dl class="layui-nav-child">
                                                    <#list menu.children as thirdMenu>
                                                    <dd data-name="${thirdMenu.name!}">
                                                        <#if (menu.target = 'blank')>
                                                            <a href="${contextPath!}${thirdMenu.url!}" target="_blank">${thirdMenu.name!}</a>
                                                        <#else>
                                                            <a lay-href="${contextPath!}${thirdMenu.url!}">${thirdMenu.name!}</a>
                                                        </#if>
                                                    </dd>
                                                    </#list>
                                                </dl>
                                            </dd>
                                        <#else>
                                            <dd data-name="${menu.name!}">
                                                <#if (menu.target = 'blank')>
                                                    <a href="${contextPath!}${menu.url!}" target="_blank"><i class="iconfont ${menu.img!}"></i> ${menu.name!}</a>
                                                <#else>
                                                    <a lay-href="${contextPath!}${menu.url!}"><i class="iconfont ${menu.img!}"></i> ${menu.name!}</a>
                                                </#if>
                                            </dd>
                                        </#if>
                                    </#list>
                                </dl>
                                </#if>
                            </li>
                        </#list>
                    </#if>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i
                            class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="main" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script src="${contextPath!}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath!}/common/js/jquery.min.js"></script>
<script src="${contextPath!}/common/js/moment.js"></script>
<script src="${contextPath!}/biz/js/index/indexLayuiController.js"></script>

</body>
</html>


