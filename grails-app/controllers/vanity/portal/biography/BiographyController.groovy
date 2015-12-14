package vanity.portal.biography

import org.springframework.http.HttpStatus

class BiographyController {

    BiographyService biographyService

    def index() {
        [viewModel: biographyService.buildMainModel()]
    }

    def details(final String letter) {
        BiographyViewModel model = biographyService.buildDetailedModel(letter)

        if (!model?.celebrities) {
            response.status = HttpStatus.NOT_FOUND.value()
            return forward(action: 'index')
        }

        [viewModel: model, currentLetter: letter]
    }

}
