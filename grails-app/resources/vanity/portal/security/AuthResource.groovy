package vanity.portal.security

import grails.converters.JSON
import groovy.util.logging.Slf4j
import vanity.portal.rest.AbstractResource
import vanity.portal.rest.RestConst
import vanity.portal.user.UserActivityService
import vanity.portal.user.UserDto
import vanity.portal.user.UserService
import vanity.user.User

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/auth')
class AuthResource extends AbstractResource {

    AuthService authService

    UserService userService

    UserActivityService userActivityService

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response auth(final Map json) {
        $ {
            AuthDto result = authService.auth(json.username, json.password)
            log.info('User {} authenticated', json.username)
            User user = userService.read(result.userId)
            UserDto dto = UserDto.build(user, userActivityService.get(user))
            return Response.ok(dto as JSON).header(RestConst.X_AUTH_TOKEN, result.token).build()
        }
    }

}
