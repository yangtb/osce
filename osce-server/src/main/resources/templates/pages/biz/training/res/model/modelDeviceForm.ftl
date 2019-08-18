<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${contextPath}/layui/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${contextPath}/biz/css/common.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/biz/iconfont/iconfont.css">

    <script>
        var basePath = '${basePath}';
        var contextPath = '${contextPath}';
        var idDevice = '${idDevice!}';
    </script>
</head>

<body>
<div style="margin: -10px 0px 0px 0px">
    <div class="layui-col-xs4">
        <table id="deviceTable" lay-filter="deviceTableFilter">
        </table>
    </div>

    <div class="layui-col-xs8">
        <form class="layui-form" id="deviceForm" style="padding-top: 10px;">
            <div hidden>
                <input name="idDeviceCase" id="idDeviceCase" hidden>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">设备编码<i class="iconfont icon-required"
                                                         style="color: #f03f2d"></i></label>
                    <div class="layui-input-inline" style="width: 514px;">
                        <input type="text" name="cdDeviceCase" lay-verify="required" lay-vertype="tips"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">

                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">品牌</label>
                    <div class="layui-input-inline">
                        <input type="text" name="brand" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">型号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="model" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">单价（￥）</label>
                    <div class="layui-input-inline">
                        <input type="text" name="price" autocomplete="off" class="layui-input"
                               placeholder="￥"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">购入时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtStoreIn" id="gmtStoreIn" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">保修截止</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtRepairEnd" id="gmtRepairEnd" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">维修电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tellRepair" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图片</label>
                <div class="layui-input-inline" style="vertical-align: middle;width: 325px;">
                    <input id="picDiviceCase" name="picDiviceCase" placeholder="请上传文件" autocomplete="off"
                           class="layui-input layui-disabled" disabled/>
                </div>
                <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                    <i class="layui-icon">&#xe681;</i>上传
                </button>
                <button type="button" class="layui-btn layui-btn-primary" id="reviewPhoto">
                    <i class="iconfont icon-detail"></i> 预览
                </button>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">设备报废</label>
                    <div class="layui-input-inline">
                        <input type="checkbox" checked="" name="fgScrap" lay-skin="switch"
                               lay-filter="fgScrapSwitch" value="1" lay-text="NO|OFF"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">报废时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtScrap" id="gmtScrap" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">报废描述</label>
                    <div class="layui-input-inline" style="width: 514px;">
                                <textarea name="desScrap" class="layui-textarea"
                                          autocomplete="off" lay-verify="commonLength255" lay-vertype="tips"/></textarea>
                    </div>
                </div>
            </div>

            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">创建人</label>
                    <div class="layui-input-inline">
                        <input type="text" name="creator" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtCreate" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
            </div>
            <div class="layui-form-item form-item-my">
                <div class="layui-inline">
                    <label class="layui-form-label">修改人</label>
                    <div class="layui-input-inline">
                        <input type="text" name="operator" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">修改时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gmtModify" autocomplete="off" class="layui-input layui-disabled"
                               disabled>
                    </div>
                </div>
            </div>

            <div class="layui-form-item" style="padding-top: 5px">
                <div class="layui-input-block">
                    <div class="layui-btn-group">
                        <button id="add" type="button" class="layui-btn">
                            <i class="layui-icon layui-icon-add-1"></i>增加
                        </button>
                        <button class="layui-btn" lay-submit="" id="save" lay-filter="addModelDevice">
                            <i class="iconfont icon-save-copy"></i>保存
                        </button>
                        <#--<button type="button" class="layui-btn layui-btn-normal" id="QRCode">
                            <i class="iconfont icon-erweima"></i>生成二维码
                        </button>-->
                        <button type="button" class="layui-btn layui-btn-danger" id="fault">
                            <i class="iconfont icon-guzhang"></i> 故障登记
                        </button>
                        <button type="button" class="layui-btn layui-btn-normal" id="repair">
                            <i class="iconfont icon-weixiu"></i> 维修登记
                        </button>
                        <div hidden>
                            <button id="reset" type="reset" class="layui-btn layui-btn-danger">
                                <i class="iconfont icon-reset"></i> 重新填写
                            </button>
                        </div>

                    </div>
                </div>
            </div>

        </form>
    </div>

</div>

<script src="${contextPath}/layui/plugins/layui/layui.js"></script>
<script src="${contextPath}/common/js/jquery.min.js"></script>
<script src="${contextPath}/common/js/jquery.formautofill.js"></script>
<script src="${contextPath}/biz/js/biz/training/res/model/modelDeviceController.js"></script>

<script type="text/html" id="deviceBar">
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i></a>
</script>

</body>
</html>