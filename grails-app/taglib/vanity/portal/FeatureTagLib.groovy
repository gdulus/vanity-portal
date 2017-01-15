package vanity.portal

import vanity.portal.utils.FeatureUtils

class FeatureTagLib {

    static namespace = 'v'

    def withFeature = { attrs, body ->
        String name = attrs.remove('name')
        if (FeatureUtils.isEnabled(name)) {
            out << body()
        }
    }

    def outsideFeatureRequest = { attrs, body ->
        if (!FeatureUtils.isPreviewRequested()) {
            out << body()
        }
    }

}
