class UrlMappings {

	static mappings = {

        "/"(controller: 'mainPage')

        "500"(controller: 'error', action: 'http500')
        "404"(controller: 'error', action: 'http404')
	}
}
