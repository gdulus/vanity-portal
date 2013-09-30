package vanity.portal

import vanity.utils.ConfigUtils

class PaginateTagLib {

    static namespace = 'v'

    def paginate = { attrs ->
        String controller = attrs.remove('controller')
        String action = attrs.remove('action')
        Map<String, ?> params = attrs.remove('params')
        String startParamName = attrs.remove('startParamName')
        Long start = attrs.remove('start')
        Long total = attrs.remove('total')
        Integer step = ConfigUtils.$as(grailsApplication.config.portal.search.page.articles.max, Integer)
        Long pages = Math.ceil(total / step)
        Integer current = start / step
        Integer previous = current - 1
        Integer next = current + 1

        out << '<div class="paginate">'

        if (previous >= 0) {
            Map<String, ?> pageParams = params + [(startParamName): (previous * step)]
            out << g.link(controller: controller, action: action, params: pageParams) {
                g.message(code: 'portal.paginate.prev')
            }
        }

        pages.times { Integer pageNumber ->
            Integer displayPageNumber = pageNumber + 1

            if (pageNumber == current) {
                out << """<span class="current">${displayPageNumber}</span>"""
            } else {
                Map<String, ?> pageParams = params + [(startParamName): (pageNumber * step)]
                out << g.link(controller: controller, action: action, params: pageParams) { displayPageNumber }
            }
        }

        if (next < pages) {
            Map<String, ?> pageParams = params + [(startParamName): (next * step)]
            out << g.link(controller: controller, action: action, params: pageParams) {
                g.message(code: 'portal.paginate.next')
            }
        }

        out << '</div>'
    }

}
