package vanity.portal.domain

class ArticlePage {

    public static final ArticlePage EMPTY = new ArticlePage(number: 0, totalPages: 0, totalElements: 0, content: [])

    public Integer number

    public Integer totalPages

    public Integer totalElements

    public List<Article> content

    public boolean isEmpty(){
        return !content
    }

}
