package vanity.portal.security

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.beans.factory.annotation.Autowired

class AuthService {

    @Autowired
    SpringSecurityService springSecurityService

    public String auth(final String login, final String password) {
        springSecurityService.reauthenticate()
    }

}
