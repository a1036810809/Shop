package com.lzfmy.Filter;

import com.lzfmy.model.Adminuser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PrivilegeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        //判断session中是否保存了后台用户信息
        Adminuser exitAdminUser = (Adminuser) request.getSession()
                .getAttribute("exitAdminUser");
        if (exitAdminUser == null) {
            //没有登录就进行访问
            request.getRequestDispatcher("/admin/index.jsp").forward(request,resp);
        }else{
            chain.doFilter(request, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
