class UrlMappings {

    static mappings = {

        /**
         * API
         */
        "/api/search/byTerm"(controller: 'api', action: 'searchByTerm')

        /**
         * Portal main page
         */
        "/"(controller: 'home', action: 'main')

        /**
         * Portal result page
         */
        "/news/$id"(controller: 'result', action: 'showById')
        "/news/$id/$title"(controller: 'result', action: 'showPreview')
        "/czytaj/$id/$title"(controller: 'result', action: 'showArticle')

        /**
         * Portal search result pages
         */
        "/vip/$tagName/$offset?/$max?"(controller: 'search', action: 'searchByTag') {
            constraints {
                tagName(blank: false, nullable: false)
            }
        }

        "/szukaj/$offset?/$max?"(controller: 'search', action: 'searchByTerm')

        /**
         * Top results
         */
        "/najnowsze-plotki/$offset?/$max?"(controller: 'top', action: 'newestArticles')
        "/najwazniejsze-plotki/$offset?/$max?"(controller: 'top', action: 'mostPopularArticles')
        "/najpopularniejsi-celebryci"(controller: 'top', action: 'mostPopularTags')

        /**
         * Info page
         */
        "/o-nas"(controller: 'info', action: 'aboutUs')
        "/regulamin"(controller: 'info', action: 'regulations')
        "/kontakt"(controller: 'info', action: 'contact')
        "/kontakt/wyslij"(controller: 'info', action: 'contactSend')

        /**
         * Error pages
         */
        "500"(controller: 'error', action: 'serverError')
        "404"(controller: 'error', action: 'notFound')
    }
}
