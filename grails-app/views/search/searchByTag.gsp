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
        <h1>
            <g:javascript>
                VIP_DATA = {
                    id: ${viewModel.tag.id},
                    name: "${viewModel.tag.name}"
                };
            </g:javascript>
            <span id="vip-name">
                <g:message code="portal.searchResult.foundTag.h1" args="[viewModel.tag.name]"/>
            </span>
            <a href="#/szklarnia" id="user-button" type="button" class="user-action-button pull-right">
                <span class="glyphicon glyphicon-cloud-upload"></span>
            </a>
        </h1>
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

        <div class="row">
            <div class="col-md-12">
                <h2><g:message code="vanity.biography"/></h2>

                <div id="biography">
                    <div class="col-sm-6 col-md-4">
                        <h3><g:message code="vanity.biography.fullName"/></h3>

                        <p>${viewModel.celebrity.fullName}</p>
                    </div>
                    <g:if test="${viewModel.celebrity.nickName}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.nick"/></h3>

                            <p>${viewModel.celebrity.nickName}</p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.gender}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.gender"/></h3>

                            <p><g:message code="vanity.user.Gender.${viewModel.celebrity.gender.name()}"/></p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.height}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.height"/></h3>

                            <p>${viewModel.celebrity.height} cm</p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.zodiacSign}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.zodiacSign"/></h3>

                            <p><g:message
                                    code="vanity.celebrity.ZodiacSign.${viewModel.celebrity.zodiacSign.name()}"/></p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.birthDate}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.birthDate"/></h3>

                            <p><g:formatDate format="d MMMM yyyy 'r.'" locale="pl"
                                             date="${viewModel.celebrity.birthDate}"/></p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.birthLocation}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.birthPlace"/></h3>

                            <p>${viewModel.celebrity.birthLocation}</p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.age}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.age"/></h3>

                            <p>${viewModel.celebrity.age}</p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.dead}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.deathDate"/></h3>

                            <p><g:formatDate format="d MMMM yyyy 'r.'" locale="pl"
                                             date="${viewModel.celebrity.deathDate}"/></p>
                        </div>

                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.deathPlace"/></h3>

                            <p>${viewModel.celebrity.deathLocation}</p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.jobs}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.job"/></h3>

                            <p>
                                <g:each in="${viewModel.celebrity.jobs}" var="job" status="status">
                                    <translate:job job="${job}" gender="${viewModel.celebrity.gender}"/>
                                    <g:if test="${job != viewModel.celebrity.jobs.last()}">,</g:if>
                                </g:each>
                            </p>
                        </div>
                    </g:if>
                    <g:if test="${viewModel.celebrity.countries}">
                        <div class="col-sm-6 col-md-4">
                            <h3><g:message code="vanity.biography.country"/></h3>

                            <p>
                                <g:each in="${viewModel.celebrity.countries}" var="country" status="status">
                                    <translate:country country="${country}"/>
                                    <g:if test="${country != viewModel.celebrity.countries.last()}">,</g:if>
                                </g:each>
                            </p>
                        </div>
                    </g:if>
                </div>
            </div>
        </div>
    </g:if>
</v:withFeature>

<div id="articles" class="row">
    <div class="col-md-12">
        <h2><g:message code="vanity.gossips"/></h2>
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
    <v:outsideFeatureRequest>
        <g:javascript>
            V.Tracking.tag(${viewModel.tag.id});
        </g:javascript>
    </v:outsideFeatureRequest>
</content>

</body>
</html>