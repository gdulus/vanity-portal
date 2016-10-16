package vanity.portal.rest

import grails.converters.JSON
import grails.validation.ValidationException
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartRequest
import vanity.portal.security.AuthDto
import vanity.portal.security.AuthService
import vanity.portal.security.exceptions.AccountLockedSecurityException
import vanity.portal.validation.exceptions.CustomValidationException

// TODO: unify REST
@Slf4j
class VipImageResourceController {

    AuthService authService

    public def upload(final Long vipId) {
        $(request.getHeader(RestConst.X_AUTH_TOKEN)) { AuthDto auth ->
            MultipartRequest multipartRequest = (MultipartRequest) request
            MultipartFile image = multipartRequest.getFile('image')

            if (!image) {
                throw new CustomValidationException(this.class, [image: 'null'])
            }

            if (!vipId) {
                throw new CustomValidationException(this.class, [vipId: 'null'])
            }

            return new ResponseDto(status: HttpStatus.CREATED, result: [:])
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
        ResponseDto responseDto = worker.call()
        response.status = responseDto.status.value();
        response.contentType = MediaType.APPLICATION_JSON
        render(responseDto.result as JSON)
    }

    private ResponseDto exec(final Closure<ResponseDto> worker) {
        try {
            return worker.call()
        } catch (AccountLockedSecurityException exp) {
            log.warn('Account locked / disabled: {}', exp.message)
            return new ResponseDto(status: HttpStatus.FORBIDDEN, result: [:])
        } catch (SecurityException exp) {
            log.warn('User unauthorized: {}', exp.message)
            return new ResponseDto(status: HttpStatus.UNAUTHORIZED, result: [:])
        } catch (ValidationException exp) {
            log.warn('There was validation error while executing an action', exp.errors)
            return new ResponseDto(status: HttpStatus.BAD_REQUEST, result: [:])
        } catch (CustomValidationException exp) {
            log.warn('There was validation error while executing an action', exp)
            return new ResponseDto(status: HttpStatus.BAD_REQUEST, result: [:])
        } catch (Throwable exp) {
            log.warn('There was an error while executing action', exp)
            return new ResponseDto(status: HttpStatus.INTERNAL_SERVER_ERROR, result: [:])
        }
    }

    static class ResponseDto {

        HttpStatus status

        Map<String, ?> result

    }

}
