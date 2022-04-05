<#import "parts/common.ftl" as c>

<@c.page>
    <div class="input-group mb-3">
        <form class="row row-cols-lg-auto g-3 align-items-center">
            <div class="col-12">
                <label class="visually-hidden" for="inlineFormInputGroupSearchByTag">Username</label>
                <input class="form-control" type="text" name="filter" value="${filter!}" placeholder="Search by tag">
            </div>
            <div class="col-12">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
        </form>
    </div>

    <div class="input-group mb-3">
        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample"
                aria-expanded="false" aria-controls="collapseExample">
            Add New Message
        </button>
    </div>

    <div class="collapse" id="collapseExample">
        <form method="post" enctype="multipart/form-data">
            <div class="input-group mb-3">
                <input class="form-control" type="text" name="text" placeholder="Введите сообщение">
            </div>
            <div class="input-group mb-3">
                <input class="form-control" type="text" name="tag" placeholder="Тэг">
            </div>
            <div class="input-group mb-3">
                <input class="form-control" type="file" name="file">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit">Добавить</button>
        </form>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-3">
        <#list messages as message>
            <div class="col">
                <div class="card my-3">
                    <#if message.filename??>
                        <img class="card-img-top" src="/img/${message.filename}">
                    </#if>
                    <div class="m-2">
                        <span>${message.text}</span>
                        <i>${message.tag}</i>
                    </div>
                    <div class="card-footer text-muted">
                        ${message.authorName}
                    </div>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>