package com.mycompany.cdiary.filter;

import com.mycompany.cdiary.constants.Constants;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/error.xhtml")
public class ErrorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        HttpSession session = request.getSession(false);
        boolean hasError = (session != null) && (session.getAttribute(Constants.ErrMsg_Key) != null);
        String loginURL = request.getContextPath() + "/login";
        if (hasError) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURL);
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
