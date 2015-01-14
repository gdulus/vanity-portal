package vanity.portal.social

class SocialController {

    def index(final String template){
        render(template: "/social/${template}")
    }
}
