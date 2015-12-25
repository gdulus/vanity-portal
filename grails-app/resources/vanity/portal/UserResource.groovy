package vanity.portal

import grails.converters.JSON
import grails.validation.ValidationException
import groovy.util.logging.Slf4j
import vanity.portal.utils.JSONUtils
import vanity.user.Gender
import vanity.user.User
import vanity.user.UserService

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/user')
class UserResource {

    UserService userService

    @POST
    @Consumes(['application/json'])
    public Response create(Map dto) {
        try {
            User user = userService.create(dto.username, dto.password) {
                gender = Gender.parseStr(dto.gender)
                email = dto.email
            }
            log.info('User created {}', user.id)
            return Response.ok((user as JSON)).build()
        } catch (ValidationException exp) {
            log.warn('Error while creating of the user', exp)
            return Response.status(Response.Status.BAD_REQUEST).entity(JSONUtils.convert(User, exp.errors)).build();
        }
    }

}
