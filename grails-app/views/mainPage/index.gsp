<html>
<head>
    <title></title>
    <meta name="layout" content="portal"/>
    <r:require module="mainPage"/>
</head>
<body>
    <!--
        page header
    -->
    <div class="page-header top row">
        <div class="span12">
            <a href="./" class="logo offset3"><g:message code="portal.title" /></a>
            <div class="menu">
                <a href="#"><g:message code="portal.menu.newestArticles" /></a>
                <a href="#"><g:message code="portal.menu.mostPopularCelebrities" /></a>
                <a href="#"><g:message code="portal.menu.mostImportantArticles" /></a>
            </div>
        </div>
    </div>
    <!--
      search box
    -->
    <div class="search row">
        <div class="span12">
            <form>
                <input type="text" class="input-xxlarge" placeholder="${g.message(code:'portal.searchForm.searchPlaceholder')}"/>
                <button type="submit" class="btn btn-primary"><g:message code="portal.searchForm.searchButton" /></button>
            </form>
        </div>
    </div>
    <!--
      popular celebrites
    -->
    <div class="section popular row">
        <div class="span8  offset2">
            <h4 class="title muted"><g:message code="portal.mainPage.mostPopularSectionTitle" /></h4>
            <p class="tag-cloud">
                <a href="#Doda" class="tag scale1">Doda</a> <a href="#Rhiana" class="tag scale2">Rhiana</a>
                <a href="#Doda" class="tag scale3">Doda</a> <a href="#Rhiana" class="tag scale4">Rhiana</a>
                <a href="#Doda" class="tag scale5">Doda</a> <a href="#Rhiana" class="tag scale6">Rhiana</a>
                <a href="#Doda" class="tag scale7">Doda</a> <a href="#Rhiana" class="tag scale8">Rhiana</a>
                <a href="#Doda" class="tag scale9">Doda</a> <a href="#Rhiana" class="tag scale10">Rhiana</a>
                <a href="#Doda" class="tag scale1">Doda</a> <a href="#Rhiana" class="tag scale2">Rhiana</a>
                <a href="#Doda" class="tag scale3">Doda</a> <a href="#Rhiana" class="tag scale4">Rhiana</a>
                <a href="#Doda" class="tag scale5">Doda</a> <a href="#Rhiana" class="tag scale6">Rhiana</a>
                <a href="#Doda" class="tag scale7">Doda</a> <a href="#Rhiana" class="tag scale8">Rhiana</a>
                <a href="#Doda" class="tag scale9">Doda</a> <a href="#Rhiana" class="tag scale10">Rhiana</a>
            </p>
        </div>
    </div>
    <!--
      our icons
    -->
    <div class="section icons row">
        <div class="span8  offset2">
            <h4 class="title muted"><g:message code="portal.mainPage.ourIconsSectionTitle" /></h4>
            <p>
                <a href="#Joanna+Horodynska" class="tag-scale1">Joanna Horodyńska</a>
                <a href="#Kasia+Tusk" class="tag-scale1">Kasia Tusk</a>
                <a href="#Krzychu+Kachel" class="tag-scale1">Krzychu Kachel</a>
                <a href="#Joanna+Horodynska" class="tag-scale1">Joanna Horodyńska</a>
                <a href="#Kasia+Tusk" class="tag-scale1">Kasia Tusk</a>
            </p>
        </div>
    </div>
    <!--
      news
    -->
    <div class="section news row">
        <div class="span4  offset2">
            <h4 class="title muted"><g:message code="portal.mainPage.newestArticlesSectionTitle" /></h4>
            <ol>
                <li><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</a></li>
                <li><a href="#">Curabitur vestibulum porttitor augue, vitae fermentum nibh fringilla non. Duis quam sapien, pharetra vel consectetur id.</a></li>
                <li><a href="#">Phasellus at augue lorem. Integer arcu ipsum, dignissim vel consectetur bibendum, dictum in diam.</a></li>
                <li><a href="#">Aliquam suscipit libero ullamcorper sem faucibus eget accumsan dolor iaculis.</a></li>
                <li><a href="#">Vivamus enim libero, sodales et porttitor sit amet, tincidunt id ipsum. </a></li>
            </ol>
        </div>
        <div class="span4">
            <h4 class="title muted"><g:message code="portal.mainPage.mostImportantArticlesSectionTitle" /></h4>
            <ol>
                <li><a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</a></li>
                <li><a href="#">Curabitur vestibulum porttitor augue, vitae fermentum nibh fringilla non. Duis quam sapien, pharetra vel consectetur id.</a></li>
                <li><a href="#">Phasellus at augue lorem. Integer arcu ipsum, dignissim vel consectetur bibendum, dictum in diam.</a></li>
                <li><a href="#">Aliquam suscipit libero ullamcorper sem faucibus eget accumsan dolor iaculis.</a></li>
                <li><a href="#">Vivamus enim libero, sodales et porttitor sit amet, tincidunt id ipsum. </a></li>
            </ol>
        </div>
    </div>

</body>
</html>