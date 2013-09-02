class UrlMappings {

    static mappings = {

        /**
         * API
         */
        "/api/search/byTerm"(controller: 'api', action: 'searchByTerm')

        /**
         * Portal
         */
        "/"(controller: 'home', action: 'index')
        "/a/$hash/$title"(controller: 'result', action: 'showArticle')
        "/a/$hash/$tag/$title"(controller: 'result', action: 'showArticle'){
            constraints {
                hash(blank: false, nullable: false)
            }
        }
        "/t/$hash/$tagName"(controller: 'result', action: 'showTag'){
            constraints {
                hash(blank: false, nullable: false)
                tagName(blank: false, nullable: false)
            }
        }

        "500"(controller: 'error', action: 'http500')
        "404"(controller: 'error', action: 'http404')
	}
}
