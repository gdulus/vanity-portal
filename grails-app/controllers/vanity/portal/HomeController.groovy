package vanity.portal

class HomeController {

    def promotionService

    def index() {
        [promotedTags:promotionService.getPromotedTags()]
    }
}
