package vanity.portal.info

import grails.validation.Validateable

@Validateable
class ContactCmd {

    String firstName

    String lastName

    String email

    String message

    static constraints = {
        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        email(nullable: false, blank: false, email: true)
        message(nullable: false, blank: false, maxSize: 1000)
    }

    public String getFullName(){
        "${firstName} ${lastName}"
    }

}
