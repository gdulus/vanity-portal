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
        "/artykul/$hash/$title"(controller: 'result', action: 'showArticle')
        "/artykul/$hash/$tag/$title"(controller: 'result', action: 'showArticle') {
            constraints {
                hash(blank: false, nullable: false)
            }
        }

        /**
         * Portal search result pages
         */
        "/vip/$hash/$tagName/$startElement?"(controller: 'search', action: 'searchByTag') {
            constraints {
                hash(blank: false, nullable: false)
                tagName(blank: false, nullable: false)
                startElement(validator: {
                    it.isNumber() && it.toInteger() >= 0
                })
            }
        }

        "/szukaj/$startElement?"(controller: 'search', action: 'searchByTerm') {
            constraints {
                startElement(validator: {
                    it.isNumber() && it.toInteger() >= 0
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
