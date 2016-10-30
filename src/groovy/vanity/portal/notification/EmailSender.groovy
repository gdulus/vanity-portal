package vanity.portal.notification

import grails.gsp.PageRenderer
import grails.plugin.mail.MailService
import org.apache.commons.lang.Validate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import vanity.user.User

@Component
class EmailSender {

    private static final Locale PL_LOCALE = new Locale('pl', 'PL')

    @Autowired
    MessageSource messageSource

    @Autowired
    PageRenderer groovyPageRenderer

    @Autowired
    MailService mailService

    public void sendUserRegistrationEmail(final User user, final String token) {
        Validate.notNull(user?.profile?.email)

        mailService.sendMail {
            async true
            to user.profile.email
            subject getTitle('email.user.registration.title', [user.username])
            html getBody('userRegistrationEmail', [user: user, token: token])
        }
    }

    private String getTitle(final String code, final List<?> params) {
        messageSource.getMessage(code, params as Object[], PL_LOCALE)
    }

    private String getBody(final String template, final Map<String, ?> model) {
        groovyPageRenderer.render(view: "/emails/${template}", model: model)
    }
}
