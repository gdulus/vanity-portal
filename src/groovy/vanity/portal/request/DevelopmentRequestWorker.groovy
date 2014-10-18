package vanity.portal.request

import javax.servlet.http.HttpServletRequest

class DevelopmentRequestWorker implements RequestWorker {

    @Override
    boolean isUrlInvalid(HttpServletRequest request) {
        return request.forwardURI.replace("${request.contextPath}/", '').endsWith('/')
    }

    @Override
    String correctInvalidUrl(HttpServletRequest request) {
        String cleanURI = request.forwardURI.replace("${request.contextPath}/", '')
        return "/${cleanURI.split('/').grep().join('/')}"
    }
}
