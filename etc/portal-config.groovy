portal.search.box.tags.max = 5
portal.search.box.articles.max = 10
portal.search.page.articles.max = 10

portal.mainPage.newestArticles.max = 10
portal.mainPage.hottestArticles.max = 10
portal.mainPage.hottestArticles.dateWindow = 7
portal.mainPage.hottestTags.max = 10
portal.mainPage.hottestTags.dateWindow = 14

portal.top.articles.max = 10
portal.top.articles.days = 30
portal.top.tags.max = 100

portal.contact.to = 'gdula.pawel@gmail.com'

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "pawel.gdula@burningice.pl"
        password = "12ab34q"
        props = ["mail.smtp.auth": "true",
            "mail.smtp.socketFactory.port": "465",
            "mail.smtp.socketFactory.class": "javax.net.ssl.SSLSocketFactory",
            "mail.smtp.socketFactory.fallback": "false"]
    }
}