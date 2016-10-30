package vanity.portal.rest

import grails.validation.ValidationException
import groovy.util.logging.Slf4j
import vanity.portal.security.AuthDto
import vanity.portal.security.AuthService
import vanity.portal.security.exceptions.AccountLockedSecurityException
import vanity.portal.utils.JSONUtils
import vanity.portal.validation.exceptions.CustomValidationException
import vanity.user.User

import javax.ws.rs.core.Response

@Slf4j
class AbstractResource {

    AuthService authService

    protected Response $(final String token, final Closure<Response> worker) {
        $ {
            AuthDto result = authService.auth(token)
            Response response = worker.call(result)
            return Response.status(response.status).header(RestConst.X_AUTH_TOKEN, result.token).entity(response.entity).build();
        }
    }

    protected Response $(final Closure<Response> worker) {
        try {
            return worker.call()
        } catch (AccountLockedSecurityException exp) {
            log.warn('Account locked / disabled: {}', exp.message)
            return Response.status(Response.Status.FORBIDDEN).entity(JSONUtils.EMPTY).build();
        } catch (SecurityException exp) {
            log.warn('User unauthorized: {}', exp.message)
            return Response.status(Response.Status.UNAUTHORIZED).entity(JSONUtils.EMPTY).build();
        } catch (ValidationException exp) {
            log.warn('There was validation error while executing an action', exp.errors)
            return Response.status(Response.Status.BAD_REQUEST).entity(JSONUtils.convert(User, exp.errors)).build();
        } catch (CustomValidationException exp) {
            log.warn('There was validation error while executing an action', exp)
            return Response.status(Response.Status.BAD_REQUEST).entity(JSONUtils.convert(exp)).build();
        } catch (Throwable exp) {
            log.warn('There was an error while executing action', exp)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSONUtils.EMPTY).build();
        }
    }
}
