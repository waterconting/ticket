package com.ticket.ticket.common;/*package com.shulian.tmsmanage.common;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

*/
/**
 * 接口响应花费时间
 *//*

@WebFilter(urlPatterns = "/*",filterName = "logCostFilter")
public class LogCostFilter implements Filter {

    private HttpServletRequest request;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        this.request = (HttpServletRequest) servletRequest;
//        String path = request.getRequestURI();
//        System.out.println("请求IP地址是："+request.getRemoteAddr());
//        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
//        long endTime = System.currentTimeMillis();
//        DecimalFormat df=new DecimalFormat("0.000");
//       System.out.println(path + " "+ df.format((float)(endTime-startTime)/1000) +"m ");
    }
}
*/
