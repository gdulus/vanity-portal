package vanity.portal.info

import grails.plugin.mail.MailService
import org.springframework.beans.factory.annotation.Value

class InfoController {

    MailService mailService

    @Value('${portal.contact.to}')
    String contactSendTo

    def aboutUs() {
    }

    def regulations() {
    }

    def contact() {
    }

    def contactSend(final ContactCmd cmd) {
        if (!cmd.validate()) {
            return render(view: 'contact', model: [cmd: cmd])
        }

        mailService.sendMail {
            async true
            to contactSendTo
            subject "Vanity contact request from ${cmd.fullName}"
            html g.render(template: "/info/contactMail", model: [cmd: cmd])
        }

        flash.success = 'portal.info.contact.sent.success'
        redirect(action: 'contact')
    }
}
