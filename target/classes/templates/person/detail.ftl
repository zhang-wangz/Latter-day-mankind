<html>

<#include "../common/header.ftl">
<body>
    <div id="wrapper" class="toggled">

        <#-- 边栏-->
        <#include "../common/nav.ftl">

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-4 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>当前状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr >
                                <td>${personDTO.name}</td>
                                <td>${personDTO.getPersonEnum().msg}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-12 column">
                        <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>人类id</th>
                            <th>所在地址</th>
                            <th>出生日期</th>
                            <th>职业</th>
                            <#if personDTO.personstatus == 2 >
                                <th>死亡日期</th>
                            </#if>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${personDTO.userid}</td>
                            <td>${personDTO.address}</td>
                            <td>${personDTO.birthdate}</td>
                            <td>${personDTO.getPersonProfessionalEnums().msg}</td>
                            <#if personDTO.personstatus == 2 >
                                <th>${personDTO.deathdate}</th>
                            </#if>
                        </tr>
                        </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>