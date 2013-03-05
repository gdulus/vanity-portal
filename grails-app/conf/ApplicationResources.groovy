modules = {
    base {
        resource url:'http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'
        resource url:'js/bootstrap.min.js'
        resource url:'css/bootstrap.min.css'
    }

    mainPage {
        dependsOn 'base'
        resource url: 'css/main.css'
    }
}