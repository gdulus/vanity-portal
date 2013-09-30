class UrlMappings {

    static mappings = {

        /**
         * API
         */
        "/api/search/byTerm"(controller: 'api', action: 'searchByTerm')

        /**
         * Portal
         */
        "/"(controller: 'home', action: 'main')
        "/artykul/$hash/$title"(controller: 'result', action: 'showArticle')
        "/artykul/$hash/$tag/$title"(controller: 'result', action: 'showArticle') {
            constraints {
                hash(blank: false, nullable: false)
            }
        }
        "/vip/$hash/$tagName/$startElement?"(controller: 'result', action: 'showByTag') {
            constraints {
                hash(blank: false, nullable: false)
                tagName(blank: false, nullable: false)
                startElement(validator: {
                    it.isNumber() && it.toInteger() >= 0
                })
            }
        }

        "/szukaj/$startElement?"(controller: 'result', action: 'showByTerm') {
            constraints {
                startElement(validator: {
                    it.isNumber() && it.toInteger() >= 0
                })
            }
        }

        "500"(controller: 'error', action: 'serverError')
        "404"(controller: 'error', action: 'notFound')
    }
}
