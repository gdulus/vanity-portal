class UrlMappings {

	static mappings = {

        "/"(controller: 'mainPage')
        "/searchAjax"(controller: 'search', action: 'ajaxSearchResults')
        "/r/$id/$contentSourceTarget/$title"(controller: 'resultPage', action: 'showArticle')
        "/r/$id/$tagName"(controller: 'resultPage', action: 'showTag')

        "500"(controller: 'error', action: 'http500')
        "404"(controller: 'error', action: 'http404')
	}
}
