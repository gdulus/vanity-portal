package vanity.portal

import org.springframework.http.HttpStatus

import javax.servlet.http.HttpServletResponse

class AbstractController {

    public Map<String, ?> getModelOrNotFound(final Object model, final HttpServletResponse response) {
        if (model) {
            return [viewModel: model]
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value())
            return Collections.emptyMap()
        }
    }
}
