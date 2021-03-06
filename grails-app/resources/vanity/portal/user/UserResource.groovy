package vanity.portal.user

import grails.converters.JSON
import groovy.util.logging.Slf4j
import vanity.portal.rest.AbstractResource
import vanity.portal.rest.RestConst
import vanity.portal.rest.commands.UpdateUserCommand
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

    @GET
    public Response get(@HeaderParam(RestConst.X_AUTH_TOKEN) String token, @QueryParam('id') final Long id) {
        $(token) {
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final Map json) {
        $ {
            User user = userService.create(json.username, json.password) {
                gender = Gender.parseStr(json.gender)
                email = json.email
            }
            UserDto dto = UserDto.build(user, userActivityService.get(user))
            log.info('User created {}', user.id)
            return Response.ok(dto as JSON).build()
        }
    }

    @PUT
    @Path('/{userId}')
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam(RestConst.X_AUTH_TOKEN) final String token, @PathParam('userId') final Long userId, final Map json) {
        $(token) {
            UpdateUserCommand cmd = new UpdateUserCommand(json)

            User user = userService.update(userId) {
                dateOfBirth = cmd.dateOfBirth
                avatar = cmd.avatar
                city = cmd.city
            }

            UserDto dto = UserDto.build(user, userActivityService.get(user))
            log.info('User created {}', user.id)
            return Response.ok(dto as JSON).build()
        }
    }

    @PUT
    @Path('/active')
    @Consumes(MediaType.APPLICATION_JSON)
    public Response activate(final Map json) {
        $ {
            User user = userService.activate(json.token)
            UserDto dto = UserDto.build(user, userActivityService.get(user))
            log.info('User activated {}', user.id)
            return Response.ok(dto as JSON).header(RestConst.X_AUTH_TOKEN, authService.buildToken(user)).build()
        }
    }
}