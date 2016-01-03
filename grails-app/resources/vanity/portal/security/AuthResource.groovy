package vanity.portal.security

import grails.converters.JSON
import groovy.util.logging.Slf4j
import vanity.portal.utils.JSONUtils

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/auth')
class AuthResource {

    AuthService authService

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response auth(final Map json) {
        try {
            AuthDto result = authService.auth(json.username, json.password)
            log.info('User {} authenticated', json.username)
            return Response.ok(result as JSON).build()
        } catch (SecurityException exp) {
            log.warn('User unauthorized: {}', exp.message)
            return Response.status(Response.Status.UNAUTHORIZED).entity(JSONUtils.EMPTY).build();
        }
    }

}
