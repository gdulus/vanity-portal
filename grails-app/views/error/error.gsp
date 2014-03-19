<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="error.${status}"/></title>
    <meta name="layout" content="result"/>
    <asset:stylesheet href="vanity/errors.css"/>
</head>

<body>

<div class="row">
    <div id="error" class="col-md-10 col-md-offset-1 error-1">
        <div class="row">
            <div class="col-md-7 col-md-offset-5">
                <h1><g:message code="error.info"/> <span>${status}</span></h1>
            </div>
        </div>
    </div>
</div>

</body>
</html>