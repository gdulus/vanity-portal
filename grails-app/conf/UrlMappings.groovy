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
        "/artykul/$id/$title"(controller: 'result', action: 'showArticle')
        "/artykul/$id/$tag/$title"(controller: 'result', action: 'showArticle')

        /**
         * Portal search result pages
         */
        "/vip/$tagName/$offset?/$max?"(controller: 'search', action: 'searchByTag') {
            constraints {
                tagName(blank: false, nullable: false)
            }
        }

        "/szukaj/$startElement?"(controller: 'search', action: 'searchByTerm') {
            constraints {
                startElement(validator: {
                    !it || (it.isNumber() && it.toInteger() >= 0)
                })
            }
        }

        /**
         * Top results
         */
        "/najnowsze-plotki/$offset?/$max?"(controller: 'top', action: 'newestArticles')
        "/najwazniejsze-plotki/$offset?/$max?"(controller: 'top', action: 'mostPopularArticles')
        "/najpopularniejsi-celebryci"(controller: 'top', action: 'mostPopularTags')

        /**
         * Error pages
         */
        "500"(controller: 'error', action: 'serverError')
        "404"(controller: 'error', action: 'notFound')
    }
}
