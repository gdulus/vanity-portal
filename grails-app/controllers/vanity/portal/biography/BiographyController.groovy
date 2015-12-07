package vanity.portal.biography

import org.springframework.http.HttpStatus
import vanity.celebrity.Celebrity
import vanity.celebrity.CelebrityService

class BiographyController {

    private static final Range<String> ALLWOED_LETTERS = ('a'..'z')

    CelebrityService celebrityService

    def index() {
        [celebrities: celebrityService.findLastUpdated(15)]
    }

    def details(final String letter) {
        if (!ALLWOED_LETTERS.contains(letter)) {
            response.status = HttpStatus.NOT_FOUND.value()
            return forward(action: 'index')
        }

        List<Celebrity> celebrities = celebrityService.findByLastNameLike(letter, Integer.MAX_VALUE)

        if (!celebrities) {
            response.status = HttpStatus.NOT_FOUND.value()
            return forward(action: 'index')
        }

        [celebrities: celebrities, currentLetter: letter]
    }

}
