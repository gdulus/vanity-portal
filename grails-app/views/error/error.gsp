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
    <link href='https://fonts.googleapis.com/css?family=Roboto:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oswald&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <asset:stylesheet href="vanity/errors.css"/>

</head>

<body>

<g:render template="/commons/navbar"/>

<div id="error" class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="canvas">
                <h1><g:message code="error.info"/> <span>${status}</span></h1>

                <div>
                    <asset:image src="error-${errorImage}.jpg" class="img-responsive pull-right img-${errorImage}"/>
                </div>
                <div class="clearfix"></div>

            </div>
        </div>
    </div>
</div>
</div>

<g:render template="/commons/footer"/>

<%--
    JS assets
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<asset:javascript src="vanity/results.js"/>
</body>
</html>