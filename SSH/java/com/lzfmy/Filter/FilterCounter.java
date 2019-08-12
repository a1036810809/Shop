package com.lzfmy.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "FilterCounter")
public class FilterCounter implements Filter {
    //来访数量
    private int countUser;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        if(req.getSession().isNew()){
            countUser++;
        }
        ServletContext context = req.getSession().getServletContext();
        context.setAttribute("countUser", countUser);
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取配置文件中的初始化参数
        String param = filterConfig.getInitParameter("countUser");
        countUser = Integer.valueOf(param);

    }

}
