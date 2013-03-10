modules = {
    base {
        resource url:'http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js', defaultBundle: false
        resource url:'http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js', defaultBundle: false
        resource url:'http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/themes/base/jquery-ui.css', defaultBundle: false
        resource url:'js/vanity/base.js'
        resource url:'css/libs/bootstrap.min.css'

    }

    mainPage {
        dependsOn 'base'
        resource url:'css/vanity/mainPage.css'
        resource url:'js/vanity/mainPage.js'
    }
}