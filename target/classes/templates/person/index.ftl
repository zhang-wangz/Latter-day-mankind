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
                    <form role="form" method="post" action="/ch/person/save">
                        <div class="form-group">
                            <label>姓名</label>
                            <input name="Name" type="text" class="form-control" value="${(personInfo.name)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>地址</label>
                            <input name="Address" type="text" class="form-control" value="${(personInfo.address)!''}"/>
                        </div>

                        <div class="form-group">
                            <#if (personInfo.weaponsId)??>
                                <label>武器</label>
                            <#else>
                                <label>武器未选择</label>
                            </#if>
                            <select name="weaponsId" class="form-control">

                                <#list weaponsList as weapon>
                                    <option value="${weapon.id}"
                                            <#if (personInfo.weaponsId)?? && personInfo.weaponsId == weapon.id>
                                                selected
                                            </#if>
                                        >${weapon.name}
                                    </option>

                                </#list>
                            </select>
                        </div>
<#--                            <div class="file-loading">-->
<#--                                <input id="input-id" type="file">-->
<#--                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>-->
<#--                            </div>-->
<#--                        </div>-->
                        <div class="form-group">
                            <label>职业</label>
                            <select name="professionalStatus" class="form-control">

                                <#list personJob as enumJob>
                                    <option value="${enumJob.code}"
                                            <#if (personInfo.professionalStatus)?? && personInfo.professionalStatus == enumJob.code>
                                                selected
                                            </#if>
                                    >${enumJob.msg}
                                    </option>

                                </#list>
                            </select>
                        </div>

                        <div class="form-group">
                            <input hidden type="text"  name="Userid"  value="${(personInfo.userid)!''}" />
                        </div>


                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

</html>