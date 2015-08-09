package vanity.portal.utils

import grails.util.Holders
import org.codehaus.groovy.grails.web.util.WebUtils

final class FeatureUtils {

    public static boolean isEnabled(final String featureName) {
        return (params.containsKey("features.${featureName}".toString()) || Holders.config.features."${featureName}")
    }

    public static boolean isPreviewRequested() {
        return (params.keySet().find { it.contains("features.".toString()) })
    }

    private static Map<String, ?> getParams() {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        return webUtils.getCurrentRequest().parameterMap
    }

}
