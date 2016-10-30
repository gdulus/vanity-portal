package vanity.portal.biography

import grails.transaction.Transactional
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityService

class BiographyService {

    private static final Integer MAX_LAST_UPDATED = 15

    private static final Range<String> LETTERS = ('a'..'z')

    CelebrityService celebrityService

    @Transactional(readOnly = true)
    public BiographyViewModel buildMainModel() {
        getModel(celebrityService.findLastUpdated(MAX_LAST_UPDATED))
    }

    @Transactional(readOnly = true)
    public BiographyViewModel buildDetailedModel(final String letter) {
        if (!LETTERS.contains(letter)) {
            return null
        }

        getModel(celebrityService.findByLastNameLike(letter, Integer.MAX_VALUE))
    }

    private BiographyViewModel getModel(List<Celebrity> celebrities) {
        return new BiographyViewModel(
                counts: LETTERS.collectEntries { [(it): celebrityService.countByLastNameLike(it)] },
                celebrities: celebrities
        )
    }

}
