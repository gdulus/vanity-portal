class UrlMappings {

	static mappings = {

        "/"(controller: 'mainPage')
        "/searchAjax"(controller: 'search', action: 'ajaxSearchResults')
        "/r/$id/$source/$title"(controller: 'resultPage', action: 'showArticle')

        "500"(controller: 'error', action: 'http500')
        "404"(controller: 'error', action: 'http404')
	}
}
