package vanity.portal.error

import org.springframework.http.HttpStatus

class ErrorController {

    def notFound() {
        render(view: 'error', model: [status: HttpStatus.NOT_FOUND.value()])
    }

    def serverError() {
        render(view: 'error', model: [status: HttpStatus.INTERNAL_SERVER_ERROR.value()])
    }

}
