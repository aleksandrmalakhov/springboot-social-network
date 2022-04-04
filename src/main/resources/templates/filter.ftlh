<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <a href="/main">Main</a>
    <br><br>
    <form method="post" action="filter">
        <input type="text" name="filter" placeholder="Поиск">
        <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
        <button type="submit">Найти</button>
    </form>
    <br>
    <div>Список сообщений</div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>