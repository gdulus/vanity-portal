package vanity.portal

class ResultPageController {

    def tagSearchService

    def showArticle() {

    }

    def showTag(final Long id, final String tagName){
        [articles:tagSearchService.findRelatedArticles(id)]
    }
}
