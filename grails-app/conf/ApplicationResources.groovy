modules = {
    base {
        resource url: 'http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js', defaultBundle: false
        resource url: 'http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js', defaultBundle: false
        resource url: 'http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/themes/base/jquery-ui.css', defaultBundle: false
        resource url: 'js/vanity/base.js'
        resource url: 'css/libs/bootstrap.min.css'
        resource url: 'css/vanity/page/base.css'

    }

    searchWidget {
        dependsOn 'base'
        resource url: 'css/vanity/widget/searchWidget.css'
        resource url: 'js/vanity/widget/searchWidget.js'
    }

    home {
        dependsOn 'base, searchWidget'
        resource url: 'css/vanity/page/home.css'
        resource url: 'js/vanity/home.js'
    }

    results {
        dependsOn 'base, searchWidget'
        resource url: 'css/vanity/page/results.css'
        resource url: 'js/vanity/results.js'
    }

    showArticle {
        dependsOn 'results'
    }

    showByTag {
        dependsOn 'results'
    }

    showByTerm {
        dependsOn 'results'
    }

    newestArticles {
        dependsOn 'results'
    }

    mostPopularArticles {
        dependsOn 'results'
    }

    mostPopularTags {
        dependsOn 'results'
        resource url: 'css/vanity/page/mostPopularTags.css'
    }
}