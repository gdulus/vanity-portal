package vanity.portal

import grails.converters.JSON
import grails.validation.ValidationException
import vanity.user.Gender
import vanity.user.UserService

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Response

@Path('/api/user')
class UserResource {

    UserService userService

    @POST
    @Consumes(['application/json'])
    public Response create(Map dto) {
        try {
            JSON result = userService.create(dto.username, dto.password) {
                gender = Gender.parseStr(dto.gender)
                email = dto.email
            } as JSON
            return Response.ok(result).build()
        } catch (ValidationException exp) {
            JSON errors = exp.errors.fieldErrors.collect { "${it.field}.${it.code}" } as JSON
            return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
        }
    }

}
