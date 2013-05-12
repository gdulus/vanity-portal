package vanity.portal.page

class MainPageController {

    def tagPromotionService

    def index() {
        [promotedTags:tagPromotionService.promotedTags]
    }
}
