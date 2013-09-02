package vanity.portal.home

import org.springframework.transaction.annotation.Transactional
import vanity.article.Status
import vanity.article.Tag
import vanity.article.TagService

class TopTagsService {

    TagService tagService

    @Transactional(readOnly = true)
    public List<Tag> getPromotedTags() {
        return tagService.getAllTagsByStatus(Status.Tag.PROMOTED)
    }
}
