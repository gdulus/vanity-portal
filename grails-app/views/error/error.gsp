<%--
    This page incorporate full template due to issue with rendering layout for 500 error.
    When 500 error is rendered only template without layout is rendered.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="description" content="">
    <title><g:message code="error.${status}"/></title>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Archivo+Narrow:400,700&subset=latin,latin-ext"
          type="text/css"/>
    <asset:stylesheet href="vanity/errors.css"/>

</head>

<body>

<g:render template="/layouts/navbar"/>

<div id="main" class="container" role="main">
    <div class="row">
        <div id="error" class="col-md-10 col-md-offset-1 error-${errorImage}">
            <div class="row">
                <div class="col-md-7 col-md-offset-5">
                    <h1><g:message code="error.info"/> <span>${status}</span></h1>
                </div>
            </div>
        </div>
    </div>
</div>

<g:render template="/layouts/footer"/>

<%--
    JS assets
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
<asset:javascript src="vanity/results.js"/>
</body>
</html>