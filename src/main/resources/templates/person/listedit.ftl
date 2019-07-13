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
                            <th>Address</th>
                            <th>BirthDate</th>
                            <th>PersonStatus</th>
                            <th colspan="3">Edit</th>
                        </tr>
                        </thead>

                        <tbody>

                        <#list personDTOPage.content as personDTO>
                            <tr>
                                <td>${personDTO.userid}</td>
                                <td>${personDTO.name}</td>
                                <td>${personDTO.address}</td>
                                <td>${personDTO.birthdate}</td>
                                <td>${personDTO.getPersonEnum().getMsg()}</td>
                                <td><a href="/ch/person/index?personId=${personDTO.userid}">编辑</a></td>

                                <#if personDTO.getPersonEnum().msg == "死亡人类">
                                    <td>已抹除</td>
                                <#else>
                                    <td><a href="/ch/person/kill/${personDTO.userid}" onclick="return alertWaring()">抹除</a></td>
                                </#if>
                                <td><a href="/ch/person/delete?personId=${personDTO.userid}" style="color: #ac2925" onclick=" return alertWaring()">删除</a></td>
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
                        <li><a href="/ch/person/listedit?page=${currentPage - 1}&size=${size}#">上一页</a></li>
                    </#if>

                    <#list 1..personDTOPage.getTotalPages() as index>

                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/ch/person/listedit?page=${index}&size=${size}#">${index}</a></li>
                        </#if>

                    </#list>

                    <#if currentPage gte personDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/ch/person/listedit?page=${currentPage + 1}&size=${size}#">下一页</a></li>
                    </#if>
                    </ul>
                </div>

            </div>
        </div>
        </div>

    </div>
    </body>

</html>