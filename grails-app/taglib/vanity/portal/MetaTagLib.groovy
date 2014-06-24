package vanity.portal

import org.apache.commons.lang.StringUtils
import vanity.article.Article
import vanity.article.Tag

class MetaTagLib {

    private static final int DESCRIPTION_TITLE_SIZE = 100

    static namespace = 'v'

    def articleTitle = { attrs ->
        Article article = attrs.remove('article')
        out << "${article.title} - ${getRequiredTags(article).join(',')}"
    }

    def articleDescription = { attrs ->
        Article article = attrs.remove('article')
        out << "${getRequiredTags(article).join(',')} - ${StringUtils.abbreviate(article.title, DESCRIPTION_TITLE_SIZE)}"
    }

    private List<String> getRequiredTags(final Article article) {
        List<Tag> publicTags = article.publicTags

        if (!publicTags) {
            return Collections.emptyList()
        }

        if (publicTags.size() == 1) {
            return [publicTags.first().name]
        }

        return publicTags[0..1]*.name
    }

}
