package vanity.portal.request.filter

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * Created by gdulus on 10/18/14.
 */
public class HystrixRequestContextServletFilter implements Filter {

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    void destroy() {
    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            chain.doFilter(request, response);
        } finally {
            context.shutdown();
        }

    }
}
