<div class="input-group mb-3">
    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample"
            aria-expanded="false" aria-controls="collapseExample">
        Message edit
    </button>
</div>

<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <form method="post" enctype="multipart/form-data">
        <div class="input-group mb-3">
            <input type="text" name="text" value="<#if message??>${message.text}</#if>"
                   class="form-control ${(textError??)?string('is-invalid', '')}"
                   placeholder="Введите сообщение">
        </div>
        <#if textError??>
            <div class="invalid-feedback">
                ${textError}
            </div>
        </#if>
        <div class="input-group mb-3">
            <input type="text" name="tag" value="<#if message??>${message.tag}</#if>"
                   class="form-control ${(tagError??)?string('is-invalid', '')}"
                   placeholder="Тэг">
        </div>
        <#if tagError??>
            <div class="invalid-feedback">
                ${tagError}
            </div>
        </#if>
        <div class="input-group mb-3">
            <input class="form-control" type="file" name="file">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" name="id" value="<#if message??>${message.id}<#else>-1</#if>"/>
        <button class="btn btn-primary" type="submit">Save message</button>
    </form>
</div>