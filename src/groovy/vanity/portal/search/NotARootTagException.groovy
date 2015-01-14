package vanity.portal.search

import vanity.article.Tag

class NotARootTagException extends RuntimeException {

    final Tag relatedRoot

    NotARootTagException(Tag relatedRoot) {
        this.relatedRoot = relatedRoot
    }
}
