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
        "/a/$hash/$articleTitle"(controller: 'result', action: 'showArticle')
        "/a/$hash/$tag/$title"(controller: 'result', action: 'showArticle')
        "/t/$hash/$tagName"(controller: 'result', action: 'showTag')

        "500"(controller: 'error', action: 'http500')
        "404"(controller: 'error', action: 'http404')
	}
}
