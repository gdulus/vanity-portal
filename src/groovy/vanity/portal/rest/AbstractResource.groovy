package vanity.portal.rest

import groovy.util.logging.Slf4j
import vanity.portal.security.AuthDto
import vanity.portal.security.AuthService
import vanity.portal.utils.JSONUtils

import javax.ws.rs.core.Response

@Slf4j
class AbstractResource {

    AuthService authService

    protected Response secured(final String token, final Closure<Response> worker) {
        try {
            AuthDto result = authService.auth(token)
            Response response = worker.call()
            return Response.status(response.status).header(RestConst.X_AUTH_TOKEN, result.token).entity(response.entity).build();
        } catch (SecurityException exp) {
            log.warn('User unauthorized: {}', exp.message)
            return Response.status(Response.Status.UNAUTHORIZED).entity(JSONUtils.EMPTY).build();
        }
    }

}
