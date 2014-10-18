package vanity.portal.request

import javax.servlet.http.HttpServletRequest

interface RequestWorker {

    public boolean isUrlInvalid(HttpServletRequest request)

    public String correctInvalidUrl(HttpServletRequest request)
}
