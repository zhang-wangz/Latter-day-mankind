<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#-- 边栏-->
    <#include "../common/nav.ftl">


    <#-- 主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/ch/weapons/save">
                        <div class="form-group">
                            <label>名字</label>
                            <input name="Name" type="text" class="form-control" value="${(weaponsInfo.name)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>价格</label>
                            <input name="price" type="text" class="form-control" value="${(weaponsInfo.price)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>库存</label>
                            <input name="weaponsStock" type="number" class="form-control" value="${(weaponsInfo.weaponsStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="description" type="text" class="form-control" value="${(weaponsInfo.description)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>武器类型</label>
                            <select name="weaponsType" class="form-control">

                                <#list weaponsList as enumType>
                                    <option value="${enumType.code}"
                                            <#if (weaponsInfo.weaponsType)?? && weaponsInfo.weaponsType == enumType.code>
                                                selected
                                            </#if>
                                    >${enumType.msg}
                                    </option>

                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <input hidden type="text"  name="weaponId"  value="${(weaponsInfo.id)!''}" />
                        </div>

                        <label>上次更新时间</label>
                        <p>${(weaponsInfo.updateTime)!''}</p>

                        <#--                        <div class="form-group">-->
                        <#--                            <label>图片</label>-->
                        <#--                            <input id="productIcon" name="productIcon" type="text" hidden="hidden" value="${(productInfo.productIcon)!''}"/>-->

                        <#--                            <div class="file-loading">-->
                        <#--                                <input id="input-id" type="file">-->
                        <#--                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>-->
                        <#--                            </div>-->
                        <#--                        </div>-->

                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

</html>