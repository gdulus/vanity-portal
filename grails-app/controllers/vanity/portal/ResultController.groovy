package vanity.portal

class ResultController {

    def searchService

    def tagService

    def celebrityService

    def showArticle() {

    }

    def showTag(final String hash){
        def showTag = tagService.readByHash(hash)

        if (!showTag){
            redirect(controller: 'error', action: 'http404')
            return
        }

        def articles = searchService.findRelatedArticles(showTag)

        if (!articles){
            redirect(controller: 'error', action: 'http404')
            return
        }

        [
            showTagPrettyUrl:showTag.name.encodeAsPrettyUrl(),
            articles:articles,
            celebirty:celebrityService.findByTag(showTag)
        ]
    }
}
