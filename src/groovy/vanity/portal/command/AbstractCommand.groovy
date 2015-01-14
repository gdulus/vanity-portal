package vanity.portal.command

import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey
import vanity.portal.ms.ServiceEndpointsRepository

public abstract class AbstractCommand<T> extends HystrixCommand<T> {

    protected final ServiceEndpointsRepository serviceRepository

    public AbstractCommand(final ServiceEndpointsRepository serviceRepository, final String group) {
        super(HystrixCommandGroupKey.Factory.asKey(group));
        this.serviceRepository = serviceRepository
    }

}
