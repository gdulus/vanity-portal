package vanity.portal.top

import vanity.article.Tag

class PopularTagDTO {

    private static final Integer MAX_SCALE = 10

    final Tag tag

    final Integer rank

    PopularTagDTO(Tag tag, Integer rank, Integer maxRank) {
        this.tag = tag
        this.rank = Math.floor(MAX_SCALE * (rank / maxRank))
    }
}
