package vanity.portal.location

import grails.converters.JSON
import groovy.util.logging.Slf4j
import vanity.portal.rest.AbstractResource

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Response

@Slf4j
@Path('/api/country')
class CountryResource extends AbstractResource {

    @GET
    public Response list() {
        $ {
            return Response.ok([[id: 1851073, name: 'Polska']] as JSON).build()
        }
    }

}
