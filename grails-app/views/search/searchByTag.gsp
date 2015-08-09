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
                    <ul>
                        <g:each in="${viewModel.celebrity.quotations}" var="quotation">
                            <li><p>${quotation.content}</p></li>
                        </g:each>
                    </ul>
                </div>
            </g:if>
            <g:else>
                <ul>
                    <g:each in="${viewModel.celebrity.quotations}" var="quotation">
                        <li><p>${quotation.content}</p></li>
                    </g:each>
                </ul>
            </g:else>
        </div>

        <div id="biography" class="row desktop">
            <div class="col-md-4">
                <h3>Pełne imię i nazwisko</h3>
                <p>${viewModel.celebrity.fullName}</p>

                <h3>Nick name</h3>

                <p>${viewModel.celebrity.nickName}</p>
                <h3>Płeć</h3>
                <p><g:message code="vanity.user.Gender.${viewModel.celebrity.gender.name()}"/></p>
                <h3>Wzrost</h3>
                <p>${viewModel.celebrity.height} cm</p>
                <h3>Znak zodiaku</h3>
                <p><g:message code="vanity.celebrity.ZodiacSign.${viewModel.celebrity.zodiacSign.name()}"/></p>
            </div>

            <div class="col-md-4">
                <h3>Data urodzin</h3>

                <p><g:formatDate format="d MMMM yyyy 'r.'" locale="pl" date="${viewModel.celebrity.birthDate}"/></p>
                <h3>Miejsce urodzin</h3>
                <p>${viewModel.celebrity.birthLocation}</p>
                <h3>Wiek</h3>
                <p>${viewModel.celebrity.age}</p>
                <g:if test="${viewModel.celebrity.dead}">
                    <h3>Data śmierci</h3>

                    <p><g:formatDate format="d MMMM yyyy 'r.'" locale="pl" date="${viewModel.celebrity.deathDate}"/></p>
                    <h3>Miejsce śmierci</h3>
                    <p>${viewModel.celebrity.deathLocation}</p>
                </g:if>
            </div>

            <div class="col-md-4">
                <h3>Zawód</h3>
                <g:each in="${viewModel.celebrity.jobs}" var="job" status="status">
                    <translate:job job="${job}" gender="${viewModel.celebrity.gender}"/>
                    <g:if test="${job != viewModel.celebrity.jobs.last()}">,</g:if>
                </g:each>
                <h3>Kraj</h3>
                <g:each in="${viewModel.celebrity.countries}" var="country" status="status">
                    <translate:country country="${country}"/>
                    <g:if test="${country != viewModel.celebrity.countries.last()}">,</g:if>
                </g:each>
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

<content tag="javascript">
    <v:withFeature name="social">
        <script src="//cdnjs.cloudflare.com/ajax/libs/react/0.11.2/react.js"></script>
        <asset:javascript src="social/out/goog/base.js"/>
        <asset:javascript src="social/app.js"/>
        <g:javascript>
            goog.require("social_app.dev");
        </g:javascript>
    </v:withFeature>

    <v:outsideFeatureRequest>
        <g:javascript>
            V.Tracking.tag(${viewModel.tag.id});
        </g:javascript>
    </v:outsideFeatureRequest>
</content>

</body>
</html>

