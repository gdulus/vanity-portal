package vanity.portal.error

import org.springframework.http.HttpStatus

class ErrorController {

    private static final Random RANDOM = new Random()

    private static final int MAX_ERROR_IMAGES = 2

    def notFound() {
        render(view: 'error', model: [status: HttpStatus.NOT_FOUND.value(), errorImage: errorImage])
    }

    def serverError() {
        render(view: 'error', model: [status: HttpStatus.INTERNAL_SERVER_ERROR.value(), errorImage: errorImage])
    }

    private int getErrorImage() {
        return ++(RANDOM.nextInt(MAX_ERROR_IMAGES))
    }

}
