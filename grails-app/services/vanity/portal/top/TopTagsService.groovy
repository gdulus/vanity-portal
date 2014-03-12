package vanity.portal.top

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.transaction.annotation.Transactional
import vanity.article.Tag
import vanity.article.TagService
import vanity.article.TagStatus
import vanity.stats.PopularityDTO
import vanity.stats.PopularityService
import vanity.utils.ConfigUtils

class TopTagsService {

    GrailsApplication grailsApplication

    PopularityService popularityService

    TagService tagService

    @Transactional(readOnly = true)
    public List<Tag> getPromotedTags() {
        return tagService.findAllByStatus(TagStatus.PROMOTED)
    }

    @Transactional(readOnly = true)
    public List<PopularTagDTO> getHottestTags() {
        Date fromDate = (new Date() - ConfigUtils.$as(grailsApplication.config.portal.mainPage.hottestTags.dateWindow, Integer))
        Integer max = ConfigUtils.$as(grailsApplication.config.portal.mainPage.hottestTags.max, Integer)
        List<PopularityDTO> popularTags = popularityService.findTopTagsFromDate(fromDate, max)
        Integer maxRank = popularTags*.rank.max()
        popularTags.collect { new PopularTagDTO(Tag.read(it.elementId), it.rank, maxRank) } sort { it.tag.name }
    }

}
