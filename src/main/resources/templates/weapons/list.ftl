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
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>description</th>
                            <th>weaponsStock</th>
                            <th>weaponsType</th>
                            <th>uploadTime</th>
                            <th>updateTime</th>
                            <th colspan="2">Status</th>
                        </tr>
                        </thead>

                        <tbody>

                        <#list weaponsPage.content as weapon>
                            <tr>
                                <td>${weapon.id}</td>
                                <td>${weapon.name}</td>
                                <td>${weapon.price}</td>
                                <td>${weapon.description}</td>
                                <td>${weapon.weaponsStock}</td>
                                <td>${weapon.getWeaponsTypeEnum().msg}</td>
                                <td>${weapon.uploadTime}</td>
                                <td>${weapon.updateTime}</td>
                                <td><a href="/ch/weapons/index?weaponsId=${weapon.id}">详情</a></td>
                            </tr>
                        </#list >
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-Right">

                    <#if  currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/ch/weapons/list?page=${currentPage - 1}&size=${size}#">上一页</a></li>
                    </#if>

                    <#list 1..weaponsPage.getTotalPages() as index>

                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/ch/weapons/list?page=${index}&size=${size}#">${index}</a></li>
                        </#if>

                    </#list>

                    <#if currentPage gte weaponsPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/ch/weapons/list?page=${currentPage + 1}&size=${size}#">下一页</a></li>
                    </#if>
                    </ul>
                </div>

            </div>
        </div>
        </div>

    </div>
    </body>

</html>