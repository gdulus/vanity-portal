package vanity.portal.rest

import grails.converters.JSON
import grails.validation.ValidationException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartRequest
import vanity.portal.celebirty.PortalCelebrityService
import vanity.portal.security.AuthDto
import vanity.portal.security.AuthService
import vanity.portal.security.exceptions.AccountLockedSecurityException
import vanity.portal.utils.JSONUtils
import vanity.portal.validation.exceptions.CustomValidationException
import vanity.user.User

// TODO: unify REST
@Slf4j
class VipImageResourceController {

    @Autowired
    AuthService authService

    @Autowired
    PortalCelebrityService portalCelebrityService

    public def upload(final Long vipId) {
        $(request.getHeader(RestConst.X_AUTH_TOKEN)) { AuthDto auth ->
            MultipartRequest multipartRequest = (MultipartRequest) request
            MultipartFile image = multipartRequest.getFile('image')
            portalCelebrityService.saveImage(auth.userId, vipId, image)
            return new ResponseDto(HttpStatus.CREATED)
        }
    }

    protected def $(final String token, final Closure<ResponseDto> worker) {
        $ {
            AuthDto authResult = authService.auth(token)
            response.setHeader(RestConst.X_AUTH_TOKEN, authResult.token)
            return worker.call(authResult)
        }
    }

    protected def $(final Closure<ResponseDto> worker) {
        ResponseDto responseDto = exec worker
        response.status = responseDto.status.value();
        response.contentType = MediaType.APPLICATION_JSON
        render(responseDto.result)
    }

    private ResponseDto exec(final Closure<ResponseDto> worker) {
        try {
            return worker.call()
        } catch (AccountLockedSecurityException exp) {
            log.warn('Account locked / disabled: {}', exp.message)
            return new ResponseDto(HttpStatus.FORBIDDEN)
        } catch (SecurityException exp) {
            log.warn('User unauthorized: {}', exp.message)
            return new ResponseDto(HttpStatus.UNAUTHORIZED)
        } catch (ValidationException exp) {
            log.warn('There was validation error while executing an action', exp.errors)
            return new ResponseDto(HttpStatus.BAD_REQUEST, JSONUtils.convert(User, exp.errors))
        } catch (CustomValidationException exp) {
            log.warn('There was validation error while executing an action', exp)
            return new ResponseDto(HttpStatus.BAD_REQUEST, JSONUtils.convert(exp))
        } catch (Throwable exp) {
            log.warn('There was an error while executing action', exp)
            return new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    static class ResponseDto {

        final HttpStatus status

        final JSON result

        ResponseDto(HttpStatus status, JSON result) {
            this.status = status
            this.result = result
        }

        ResponseDto(HttpStatus status) {
            this(status, ([:] as JSON))
        }
    }

}
