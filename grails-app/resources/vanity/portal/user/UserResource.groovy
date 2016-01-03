package vanity.portal.user

import grails.converters.JSON
import grails.validation.ValidationException
import groovy.util.logging.Slf4j
import vanity.portal.rest.AbstractResource
import vanity.portal.rest.RestConst
import vanity.portal.utils.JSONUtils
import vanity.user.Gender
import vanity.user.User

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/user')
class UserResource extends AbstractResource {

    UserService userService

    UserActivityService userActivityService

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final Map json) {
        try {
            User user = userService.create(json.username, json.password) {
                gender = Gender.parseStr(json.gender)
                email = json.email
            }
            UserDto dto = UserDto.build(user, userActivityService.get(user))
            log.info('User created {}', user.id)
            return Response.ok(dto as JSON).build()
        } catch (ValidationException exp) {
            log.warn('Error while creating of the user: {}', exp.errors)
            return Response.status(Response.Status.BAD_REQUEST).entity(JSONUtils.convert(User, exp.errors)).build();
        }
    }


    @GET
    public Response get(@HeaderParam(RestConst.X_AUTH_TOKEN) String token, @QueryParam('id') final Long id) {
        secured(token) {
            User user = userService.read(id)

            if (!user) {
                log.warn('User with id {} not found', id)
                return Response.status(Response.Status.NOT_FOUND).entity(JSONUtils.EMPTY).build();
            }

            log.info('User {} found', user.id)
            UserDto dto = UserDto.build(user, userActivityService.get(user))
            return Response.ok(dto as JSON).build()
        }
    }

}
