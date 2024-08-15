package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;

@WebFilter("/*")
public class ContextPathFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(httpResponse) {
            @Override
            public void sendRedirect(String location) throws IOException {
                if (!location.startsWith("http://") && !location.startsWith("https://")) {
                    location = httpRequest.getContextPath() + location;
                }
                super.sendRedirect(location);
            }
        };

        filterChain.doFilter(servletRequest, wrappedResponse);
    }
}
