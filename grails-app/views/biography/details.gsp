<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.biography.title"/></title>
    <meta name="layout" content="list"/>
    <meta name="robots" content="noindex,follow"/>
    <asset:stylesheet href="vanity/biography.less"/>
</head>

<body>
<div id="summary" class="row">
    <div class="col-md-12" id="biography-header">

        <h2><g:message code="portal.biography.header"/></h2>
    </div>
</div>

<div class="row section">
    <div class="col-md-12">
        <ul class="list-inline text-center alphabet">
            <g:each in="${viewModel.letters}" var="letter">
                <li class="${currentLetter == letter ? "selected" : ""}">
                    <g:link action="details" params="${[letter: letter]}">${letter}</g:link>
                </li>
            </g:each>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <h3><g:message code="portal.biography.letter" args="${[currentLetter]}"/></h3>
    </div>
</div>

<div class="row section">
    <div class="col-md-12">
        <g:each in="${viewModel.celebrities}" var="celebrity">
            <div class = "col-xs-12 col-sm-6 col-md-4 biography-entry">
            <g:link controller="search" action="searchByTag"
                    params="${[tagName: celebrity.tag.normalizedName]}">${celebrity.fullName}</g:link>
            </div>
        </g:each>
    </div>
</div>
</body>