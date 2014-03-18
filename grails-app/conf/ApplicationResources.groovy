modules = {

    base {
        resource url: 'https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js', defaultBundle: false, attrs: [type: 'js']
        resource url: 'http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css', defaultBundle: false, attrs: [type: 'css']
        resource url: 'http://fonts.googleapis.com/css?family=Archivo+Narrow:400,700&subset=latin,latin-ext', defaultBundle: false, attrs: [type: 'css']

        resource url: 'js/vanity/base.js'
    }

    searchWidget {
        dependsOn 'base'
        resource url: 'css/vanity/widget/searchWidget.css'
        resource url: 'js/vanity/widget/searchWidget.js'
    }

    home {
        dependsOn 'base, searchWidget'
        resource url: 'js/vanity/home.js'
        resource url: 'less/main.less', attrs: [rel: "stylesheet/less", type: 'css']
    }

    results {
        dependsOn 'base, searchWidget'
        resource url: 'less/results.less', attrs: [rel: "stylesheet/less", type: 'css']
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
    }

    errors {
        dependsOn 'base, searchWidget'
        resource url: 'less/errors.less', attrs: [rel: "stylesheet/less", type: 'css']
    }
}