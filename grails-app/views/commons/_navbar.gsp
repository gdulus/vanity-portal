<div id="fb-root"></div>
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/pl_PL/sdk.js#xfbml=1&version=v2.5";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<nav id="main-menu">
    <div class="container main-section">
        <div class="row">
            <div class="col-md-12 navabar">
                <button id="sub-menu-button" type="button" class="dropdown-toggle pull-left menu mobile">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <g:link controller="home" action="main" class="logo pull-left">
                    <asset:image src="logo-header-full.png"/>
                </g:link>

                <button id="search-button" type="button" class="pull-right search icon">
                    <span class="glyphicon glyphicon-search"></span>
                </button>

                <button id="fb-button" type="button" class="pull-right facebook icon">
                    <svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="22" height="23.105263052224473" xml:space="preserve">
                        <rect id="backgroundrect" width="100%" height="100%" x="0" y="0" fill="none" stroke="none" class=""/>
                        <g class="currentLayer">
                            <title>
                                Layer 1
                            </title>
                            <g id="svg_1" class="selected" fill="#ffffff" fill-opacity="1">
                                <g id="post-facebook" fill="#ffffff" fill-opacity="1">
                                    <path d="M19.421076779480018 0H2.1578976613042755C0.9710538389740009 0 0 1.0184223058677162 0 2.263160340396354v18.105284468192053c0 1.244738143592464 0.9710538389740009 2.263160340396354 2.1578976613042755 2.263160340396354h17.263181290434204c1.1868437137173515 0 2.1578976613042755-1.0184223058677162 2.1578976613042755-2.263160340396354V2.263160340396354C21.57897748194614 1.0184223058677162 20.607920601810292 0 19.421076779480018 0zM18.342128166053726 2.263160340396354v3.3947407287221836h-2.1578976613042755c-0.6473693526977442 0-1.0789488306521378 0.45263213897075794-1.0789488306521378 1.131580170198177v2.263160340396354h3.236846274730567v3.3947407287221836H15.105282325774851V20.3684443723331h-3.236846274730567V12.44738296281821h-2.1578976613042755V9.052642234096027h2.1578976613042755v-2.8289509708145744C11.868436485495977 4.073689223470865 13.594753745636012 2.263160340396354 15.644756632487997 2.263160340396354H18.342128166053726z" id="svg_2" fill="#ffffff" fill-opacity="1"/>
                                </g>
                            </g>

                        </g>
                    </svg>
                </button>

                <v:withFeature name="social">
                    <button id="user-button" type="button" class="pull-right user icon">
                        <span class="glyphicon glyphicon-user"></span>
                    </button>
                </v:withFeature>

                <button type="button" class="pull-left menu-item desktop">
                    <h2>celebryci <span class="glyphicon glyphicon-menu-down"></span></h2>
                    <ul class="navigation pull-right">
                        <li><v:menuItem controller="top" action="mostPopularTags" code="portal.menu.mostPopularCelebrities"/></li>
                        <li><v:menuItem controller="biography" action="index" code="portal.menu.biographies"/></li>
                    </ul>
                </button>

                <button type="button" class="pull-left menu-item desktop">
                    <h2>plotki  <span class="glyphicon glyphicon-menu-down"></span></h2>
                    <ul class="navigation pull-right">
                        <li><v:menuItem controller="top" action="mostPopularArticles" code="portal.menu.mostImportantArticles"/></li>
                        <li><v:menuItem controller="top" action="newestArticles" code="portal.menu.newestArticles"/></li>
                    </ul>
                </button>

            </div>
        </div>
    </div>
</nav>

<div id="search-menu" class="hidden">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 search">
                <form action="${createLink(uri: '/api/search')}" method="get">
                    <div class="input-group">
                        <input type="search" class="form-control"
                               placeholder="${g.message(code: 'portal.searchForm.searchPlaceholder')}"/>
                        <span class="input-group-btn">
                            <input class="btn btn-default "
                                   type="submit"
                                   href="${createLink(controller: 'search', action: 'searchByTerm')}"
                                   value="${g.message(code: 'portal.searchForm.searchButton')}"/>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
<div id="fb-widget" >
    <div class="container">
        <div class="row">
            <div class="col-xs-12" id="fb-small">
                <div class="fb-page" style="float:right" data-href="https://www.facebook.com/tanilans/" data-width="270" data-height="230" data-small-header="false" data-adapt-container-width="false" data-hide-cover="false" data-show-facepile="true" data-show-posts="false"><div class="fb-xfbml-parse-ignore"><blockquote cite="https://www.facebook.com/tanilans/"><a href="https://www.facebook.com/tanilans/">TaniLans.pl</a></blockquote></div></div>
            </div>
<div class="col-xs-12" id="fb-big">
            <div class="fb-page" style="float:right" data-href="https://www.facebook.com/tanilans/" data-width="340" data-height="230" data-small-header="true" data-adapt-container-width="false" data-hide-cover="false" data-show-facepile="true" data-show-posts="false"><div class="fb-xfbml-parse-ignore"><blockquote cite="https://www.facebook.com/tanilans/"><a href="https://www.facebook.com/tanilans/">TaniLans.pl</a></blockquote></div></div>
            </div>
        </div>
    </div>
        </div>
        </div>
        </div>


<div id="sub-menu" class="mobile navigation hidden">
    <ul>
        <li><h2>celebryci <span class="glyphicon glyphicon-menu-down"></span></h2></li>
        <li><v:menuItem controller="top" action="mostPopularTags" code="portal.menu.mostPopularCelebrities"/></li>
        <li><v:menuItem controller="biography" action="index" code="portal.menu.biographies"/></li>
        <li><h2>plotki <span class="glyphicon glyphicon-menu-down"></span></h2></li>
        <li><v:menuItem controller="top" action="mostPopularArticles" code="portal.menu.mostImportantArticles"/></li>
        <li><v:menuItem controller="top" action="newestArticles" code="portal.menu.newestArticles"/></li>
        <li><h2>centrala <span class="glyphicon glyphicon-menu-down"></span></h2></li>
        <li><g:link controller="info" action="contact" class="muted"><g:message code="portal.footer.contact"/></g:link></li>
    </ul>
</div>



