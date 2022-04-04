<#macro login path>
    <form method="post" action="${path}">
        <div><label>User Name<input type="text" name="username"/></label></div>
        <div><label>Password<input type="text" name="password"/></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Войти"/></div>
    </form>
</#macro>

<#macro logout>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Выйти">
    </form>
</#macro>