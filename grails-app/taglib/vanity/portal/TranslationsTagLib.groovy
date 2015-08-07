package vanity.portal

import vanity.i18n.TranslationKeyAware
import vanity.user.Gender

class TranslationsTagLib {

    static namespace = 'translate'

    def job = { attrs, body ->
        TranslationKeyAware job = attrs.remove('job')
        Gender gender = attrs.remove('gender')
        out << g.message(code: "vanity.job.${gender.name().toLowerCase()}.${job.translationKey}")
    }

    def country = { attrs, body ->
        TranslationKeyAware job = attrs.remove('country')
        out << g.message(code: "vanity.country.${job.translationKey}")
    }

}
