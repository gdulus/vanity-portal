<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.searchResult.foundTag.title" args="[viewModel.tag.name]"/></title>
    <meta name="description" content="${message(code: 'portal.searchResult.foundTag.description', args: [viewModel.tag.name])}"/>
    <meta name="layout" content="list"/>
    <asset:stylesheet href="vanity/list.css"/>
</head>

<body>

<div id="summary" class="row">
    <div class="col-md-12">
        <h2><g:message code="portal.searchResult.foundTag.h1" args="[viewModel.tag.name]"/></h2>
    </div>
</div>

<v:withFeature name="social">
    <div id="social"></div>
</v:withFeature>

<v:withFeature name="biography">
    <g:if test="${viewModel.celebrity}">
        <div id="quotations" class="row desktop">
            <g:if test="${viewModel.celebrity.hasImage()}">
                <div class="col-md-3 picture">
                    <v:celebrityImg bean="${viewModel.celebrity}" class="img-responsive"/>
                </div>

                <div class="col-md-9 description">
                    <g:each in="${viewModel.celebrity.quotations}" var="quotation">
                        <li>
                            <p>${quotation.content}</p>
                        </li>
                    </g:each>
                </div>
            </g:if>
            <g:else>
                <ul>
                    <g:each in="${viewModel.celebrity.quotations}" var="quotation">
                        <li>
                            <p>${quotation.content}</p>
                        </li>
                    </g:each>
                </ul>
            </g:else>
        </div>

        <div id="biography" class="row desktop">
            <div class="col-md-4">
                <h3>Pełne imię i nazwisko</h3>

                <p>${viewModel.celebrity.fullName}</p>

                <h3>Płeć</h3>

                <p><g:message code="${viewModel.celebrity.gender}"/></p>

                <h3>Wzrost</h3>

                <p>${viewModel.celebrity.height}</p>

                <h3>Znak zodiaku</h3>

                <p>${viewModel.celebrity.zodiacSign}</p>
            </div>

            <div class="col-md-4">
                <h3>Data urodzin</h3>

                <p><g:formatDate format="dd-MM-yyyy" date="${viewModel.celebrity.birth.date}"/></p>

                <h3>Miejsce urodzin</h3>

                <p>${viewModel.celebrity.birth.location}</p>

                <h3>Wiek</h3>

                <p>${viewModel.celebrity.age}</p>
                <g:if test="${!viewModel.celebrity.alive}">
                    <h3>Data śmierci</h3>

                    <p><g:formatDate format="dd-MM-yyyy" date="${viewModel.celebrity.death.date}"/></p>

                    <h3>Miejsce śmierci</h3>

                    <p>${viewModel.celebrity.death.location}</p>
                </g:if>
            </div>

            <div class="col-md-4">
                <h3>Zawód</h3>

                <p>${viewModel.celebrity.jobs*.name?.join(', ')}</p>

                <h3>Kraj</h3>

                <p>${viewModel.celebrity.countries*.name?.join(', ')}</p>
            </div>
        </div>
    </g:if>
</v:withFeature>

<div id="articles" class="row">
    <div class="col-md-12">
        <ul>
            <g:each in="${viewModel.articles}" var="article">
                <g:render template="/commons/article" model="[article: article]"/>
            </g:each>
        </ul>
    </div>
</div>

<div id="pagination" class="row">
    <div class="col-md-12">
        <v:paginateDefault next="${g.message(code: 'portal.paginate.next')}"
                           prev="${g.message(code: 'portal.paginate.prev')}"
                           params="[tagName: viewModel.tag.normalizedName]"
                           total="${viewModel.numFound}"/>
    </div>
</div>

<v:withFeature name="social">
    <content tag="javascript">
        <script src="//cdnjs.cloudflare.com/ajax/libs/react/0.11.2/react.js"></script>
        <asset:javascript src="social/out/goog/base.js"/>
        <asset:javascript src="social/app.js"/>
        <g:javascript>
            goog.require("social_app.dev");
            V.Tracking.tag(${viewModel.tag.id});
        </g:javascript>
    </content>
</v:withFeature>

</body>
</html>

