package vanity.portal

import org.springframework.web.servlet.support.RequestContextUtils as RCU
import vanity.utils.ConfigUtils

class PaginateTagLib {

    static namespace = 'v'

    /**
     * https://github.com/groovydev/twitter-bootstrap-grails-plugin/blob/master/grails-app/taglib/org/groovydev/TwitterBootstrapTagLib.groovy
     */
    def paginateDefault = { attrs ->
        def writer = out
        if (attrs.total == null) {
            throwTagError("Tag [paginate] is missing required attribute [total]")
        }
        def messageSource = grailsAttributes.messageSource
        def locale = RCU.getLocale(request)

        def total = attrs.int('total') ?: 0
        def action = (attrs.action ? attrs.action : (params.action ? params.action : "index"))
        def offset = params.int('offset') ?: 0
        def max = params.int('max')
        def maxsteps = (attrs.int('maxsteps') ?: 10)

        if (!offset) offset = (attrs.int('offset') ?: 0)
        if (!max) max = (attrs.int('max') ?: 10)

        def linkParams = [:]
        if (attrs.params) linkParams.putAll(attrs.params)
        linkParams.offset = offset - max
        linkParams.max = max
        if (params.sort) linkParams.sort = params.sort
        if (params.order) linkParams.order = params.order

        def linkTagAttrs = [action: action]
        if (attrs.controller) {
            linkTagAttrs.controller = attrs.controller
        }
        if (attrs.id != null) {
            linkTagAttrs.id = attrs.id
        }
        if (attrs.fragment != null) {
            linkTagAttrs.fragment = attrs.fragment
        }
        //add the mapping attribute if present
        if (attrs.mapping) {
            linkTagAttrs.mapping = attrs.mapping
        }

        linkTagAttrs.params = linkParams

        def cssClasses = "pagination"
        if (attrs.class) {
            cssClasses = "pagination " + attrs.class
        }

        // determine paging variables
        def steps = maxsteps > 0
        int currentstep = (offset / max) + 1
        int firststep = 1
        int laststep = Math.round(Math.ceil(total / max))

        writer << "<ul class=\"${cssClasses}\">"
        // display previous link when not on firststep
        if (currentstep > firststep) {
            linkParams.offset = offset - max
            writer << '<li class="prev">'
            writer << '<span>'
            writer << link(linkTagAttrs.clone()) {
                (attrs.prev ?: messageSource.getMessage('paginate.prev', null, '&laquo;', locale))
            }
            writer << '</span>'
            writer << '</li>'
        } else {
            writer << '<li class="prev disabled">'
            writer << '<span>'
            writer << (attrs.prev ?: messageSource.getMessage('paginate.prev', null, '&laquo;', locale))
            writer << '</span>'
            writer << '</li>'
        }

        // display steps when steps are enabled and laststep is not firststep
        if (steps && laststep > firststep) {
            linkTagAttrs.class = 'step'

            // determine begin and endstep paging variables
            int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2)
            int endstep = currentstep + Math.round(maxsteps / 2) - 1

            if (beginstep < firststep) {
                beginstep = firststep
                endstep = maxsteps
            }
            if (endstep > laststep) {
                beginstep = laststep - maxsteps + 1
                if (beginstep < firststep) {
                    beginstep = firststep
                }
                endstep = laststep
            }

            // display firststep link when beginstep is not firststep
            if (beginstep > firststep) {
                linkParams.offset = 0
                writer << '<li>'
                writer << '<span>'
                writer << link(linkTagAttrs.clone()) { firststep.toString() }
                writer << '</li>'
                writer << '</span>'
                writer << '<li class="disabled"><span>...</span></li>'
            }

            // display paginate steps
            (beginstep..endstep).each { i ->
                if (currentstep == i) {
                    writer << "<li class=\"active\">"
                    writer << "<span>${i}</span>"
                    writer << "</li>";
                } else {
                    linkParams.offset = (i - 1) * max
                    writer << "<li>";
                    writer << '<span>'
                    writer << link(linkTagAttrs.clone()) { i.toString() }
                    writer << '</span>'
                    writer << "</li>";
                }
            }

            // display laststep link when endstep is not laststep
            if (endstep < laststep) {
                writer << '<li class="disabled"><span>...</span></li>'
                linkParams.offset = (laststep - 1) * max
                writer << '<li>'
                writer << '<span>'
                writer << link(linkTagAttrs.clone()) { laststep.toString() }
                writer << '</span>'
                writer << '</li>'
            }
        }

        // display next link when not on laststep
        if (currentstep < laststep) {
            linkParams.offset = offset + max
            writer << '<li class="next">'
            writer << '<span>'
            writer << link(linkTagAttrs.clone()) {
                (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, '&raquo;', locale))
            }
            writer << '</span>'
            writer << '</li>'
        } else {
            linkParams.offset = offset + max
            writer << '<li class="disabled">'
            writer << '<span>'
            writer << (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, '&raquo;', locale))
            writer << '</span>'
            writer << '</li>'
        }

        writer << '</ul>'
    }

    def paginateSearch = { attrs ->
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
