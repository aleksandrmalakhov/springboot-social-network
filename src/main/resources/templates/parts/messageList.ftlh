<#include "security.ftlh">

<div class="row row-cols-1 row-cols-md-3 g-3">
    <#list messages as message>
        <div class="col">
            <div class="card my-3">
                <#if message.filename??>
                    <img class="card-img-top" src="/img/${message.filename}"/>
                </#if>
                <div class="m-2">
                    <span>${message.text}</span>
                    <br/>
                    <i>#${message.tag}</i>
                </div>
                <div class="card-footer text-muted container">
                    <div class="row">
                        <a class="col align-self-center"
                           href="${message.author.id}">${message.authorName}</a>
<#--                        <a class="col align-self-center" href="#">-->
<#--                            <#if message.meLiked>-->
<#--                                <i class="fas fa-heart"></i>-->
<#--                            <#else>-->
<#--                                <i class="far fa-heart"></i>-->
<#--                            </#if>-->
<#--                            ${message.likes}-->
<#--                        </a>-->
                        <#if message.author.id == currentUserId>
                            <a class=" col btn btn-primary"
                               href="${message.author.id}?message=${message.id}">Edit</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        No message
    </#list>
</div>