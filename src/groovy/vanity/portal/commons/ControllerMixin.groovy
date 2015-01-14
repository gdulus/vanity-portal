package vanity.portal.commons

import org.springframework.http.HttpStatus

class ControllerMixin {

    public Map<String, ?> getModelOrNotFound(final Object model) {
        if (model) {
            return [viewModel: model]
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value())
        }
    }

}
