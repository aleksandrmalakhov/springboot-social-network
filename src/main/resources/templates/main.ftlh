<#import "parts/common.ftl" as c>
<#include "parts/security.ftlh">

<@c.page>
    <div class="input-group mb-3">
        <form class="row row-cols-lg-auto g-3 align-items-center">
            <div class="col-12">
                <label class="visually-hidden" for="inlineFormInputGroupSearchByTag">Username</label>
                <input class="form-control" type="text" name="filter" value="${filter!}" placeholder="Search by tag"/>
            </div>
            <div class="col-12">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
        </form>
    </div>
    <#include "parts/messageEdit.ftlh"/>

    <div class="row row-cols-1 row-cols-md-3 g-3" id="message-list">
        <#list messages as message>
            <div class="col" data-id="${message.id}">
                <div class="card my-3">
                    <#if message.filename??>
                        <img class="card-img-top" src="/img/${message.filename}"/>
                    </#if>
                    <div class="m-2">
                        <span>${message.text}</span>
                        <br/>
                        <i>#${message.tag}</i>
                    </div>
                    <div class="card-footer text-muted">
                        <a href="user-messages/${message.author.id}">${message.authorName}</a>
                        <#if message.author.id == currentUserId>
                            <a class="btn btn-primary"
                               href="user-messages/${message.author.id}?message=${message.id}">Edit</a>
                        </#if>
                    </div>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>