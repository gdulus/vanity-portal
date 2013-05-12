package vanity.portal.page

class ResultPageController {

    def showTagService

    def showArticle() {

    }

    def showTag(final Long id, final String tagName){
        [articles:showTagService.findRelatedArticles(id)]
    }
}
