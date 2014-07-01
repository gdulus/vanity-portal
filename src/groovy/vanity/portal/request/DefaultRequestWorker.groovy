package vanity.portal.request

import javax.servlet.http.HttpServletRequest

class DefaultRequestWorker implements RequestWorker {

    @Override
    boolean isUrlInvalid(final HttpServletRequest request) {
        return request.forwardURI.endsWith('/') && request.forwardURI != '/'
    }

    @Override
    String correctInvalidUrl(final HttpServletRequest request) {
        return "${request.forwardURI.split('/').grep().join('/')}"
    }
}
