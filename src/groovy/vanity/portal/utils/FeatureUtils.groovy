package vanity.portal.utils

import grails.util.Holders
import org.codehaus.groovy.grails.web.util.WebUtils

final class FeatureUtils {

    public static boolean isEnabled(final String featureName) {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        return (webUtils.getCurrentRequest().parameterMap.containsKey("fetures.${featureName}".toString()) ||
                Holders.config.fetures."${featureName}")
    }

}
