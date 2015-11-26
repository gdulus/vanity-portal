<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.biography.title"/></title>
    <meta name="layout" content="list"/>
    <meta name="robots" content="index,follow"/>
    <asset:stylesheet href="vanity/biography.less"/>
</head>

<body>
<div id="summary" class="row">
    <div class="col-md-12">
        <h2><g:message code="portal.biography.header"/></h2>
    </div>
</div>

<div class="row section">
    <div class="col-md-12">
        <p><g:message code="portal.biography.description" default=""/></p>
    </div>

    <div class="col-md-12">
        <ul class="list-inline text-center alphabet">
            <g:each in="${('a'..'z')}" var="letter">
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
        <g:each in="${celebrities}" var="celebrity">
            <g:link controller="search" action="searchByTag"
                    params="${[tagName: celebrity.tag.normalizedName]}">${celebrity.fullName}</g:link>
        </g:each>
    </div>
</div>
</body>