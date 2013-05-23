package vanity.portal

class MainPageController {

    def tagPromotionService

    def index() {
        [promotedTags:tagPromotionService.promotedTags]
    }
}
