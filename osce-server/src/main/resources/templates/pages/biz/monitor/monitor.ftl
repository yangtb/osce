<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>考场监控</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
    </script>

    <style>
        /*.right-label {
            display:inline-block;

        }*/

        .left-label {
            display:inline-block; width:80px;
        }
    </style>
</head>

<body>

<div class="wrapper-content" style="margin-top: -10px;">

    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">站点监控</li>
            <li>学员管控</li>
        </ul>
        <div class="layui-tab-content">

            <div class="layui-tab-item layui-show">

                <#--指示条区域-->
                <div class="layui-row">
                    <div class="layui-col-xs2">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-warm layui-btn-fluid" style="border-radius:0px;">学员进场待确认</button>
                    </div>
                    <div class="layui-col-xs2">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-fluid" style="background-color: #5FB878; border-radius:0px;">开始考核</button>
                    </div>
                    <div class="layui-col-xs3">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-normal layui-btn-fluid" style="border-radius:0px;">本场提交，下场未开始</button>
                    </div>
                    <div class="layui-col-xs3">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-fluid" style="background-color: #c2c2c2; border-radius:0px;">考试结束</button>
                    </div>
                    <div class="layui-col-xs2">
                        <button type="button" class="layui-btn layui-btn-sm layui-btn-danger layui-btn-fluid" style="border-radius:0px;">故障</button>
                    </div>
                </div>

                <#--轮播区域-->
                <div class="layui-carousel" id="test1" lay-filter="test1">
                    <div carousel-item="">
                        <div>
                            <div class="layui-row layui-col-space15" style="padding-top: 20px;">
                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #5FB878; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px; cursor: pointer"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #FFB800; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px; cursor: pointer"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #1E9FFF; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px; cursor: pointer"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #FF5722; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px; cursor: pointer"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-row layui-col-space15" style="padding-top: 20px;">
                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="layui-row layui-col-space15" style="padding-top: 20px;">
                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-row layui-col-space15" style="padding-top: 20px;">
                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md3">
                                    <div class="layui-card" style="border: 1px solid #d2d2d2; height: 275px;">
                                        <div class="layui-card-body card-font">
                                            <ul style="height: 230px;">
                                                <li class="li-normal"><span class="left-label">站点</span><span class="right-label">1</span></li>
                                                <li class="li-normal"><span class="left-label">房间号</span><span class="right-label">501</span></li>
                                                <li class="li-normal"><span class="left-label">考试时长</span><span class="right-label">10分钟</span></li>
                                                <li class="li-normal"><span class="left-label">考试内容</span><span class="right-label">胸穿</span></li>
                                                <li class="li-normal"><span class="left-label">监考员</span><span class="right-label">林俊森</span></li>
                                                <li class="li-normal"><span class="left-label">考试描述</span><span class="right-label">为第三季度以来学员们对胸穿程度所做考核</span></li>

                                            </ul>
                                            <div style="border-top: 1px dashed #e2e2e2; padding-top: 5px;">
                                                <i class="iconfont icon-wenjian" style="font-size:25px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="layui-tab-item" style="margin-top: -20px;">
                <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                    <ul class="layui-tab-title">
                        <li class="layui-this">待考学员</li>
                        <li>场内学员</li>
                        <li>结束学员</li>
                    </ul>
                    <div class="layui-tab-content"></div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/biz/js/biz/statistics/testResultController.js"></script>


<script>
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
            ,form = layui.form;

        //常规轮播
        carousel.render({
            elem: '#test1'
            , arrow: 'hover'
            , width: '100%' //设置容器宽度
            , height: '650px' //设置容器宽度
            , interval: 5000
        });

    });
</script>

</body>

</html>