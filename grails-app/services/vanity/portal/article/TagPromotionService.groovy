package vanity.portal.article

import vanity.article.Status
import vanity.article.Tag
import vanity.article.TagService

class TagPromotionService {

    static transactional = false

    TagService tagService

    public List<Tag> getPromotedTags() {
        return tagService.getAllTagsByStatus(Status.Tag.PROMOTED)
    }
}
