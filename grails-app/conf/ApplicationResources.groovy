modules = {
    base {
        resource url:'http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js', defaultBundle: false
        resource url:'http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js', defaultBundle: false
        resource url:'http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/themes/base/jquery-ui.css', defaultBundle: false
        resource url:'js/vanity/base.js'
        resource url:'css/libs/bootstrap.min.css'
        resource url:'css/vanity/base.css'

    }

    home {
        dependsOn 'base'
        resource url:'css/vanity/home.css'
        resource url:'js/vanity/home.js'
    }

    results {
        dependsOn 'base'
        resource url:'css/vanity/results.css'
        resource url:'js/vanity/results.js'
    }

    showTags {
        dependsOn 'results'
        resource url:'css/vanity/showTags.css'
        resource url:'js/vanity/showTags.js'
    }
}