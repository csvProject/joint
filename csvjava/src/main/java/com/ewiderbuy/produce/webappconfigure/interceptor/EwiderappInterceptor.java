package com.ewiderbuy.produce.webappconfigure.interceptor;

import com.ewiderbuy.produce.common.tool.Jwt;
import com.ewiderbuy.produce.common.tool.ServiceUtil;
import com.ewiderbuy.produce.common.tool.Jwt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wkm
 */
public class EwiderappInterceptor implements HandlerInterceptor {

    final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public synchronized boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("LfsInterceptor ********* 启动拦截器");
        String servletPath = httpServletRequest.getServletPath();
        logger.info("servletPath ********* : " + servletPath);
        if ("/ewiderapp/user/login".equals(servletPath) ) {
            //登录时不拦截
            return true;
        } else {
            String token = "";
            token = httpServletRequest.getHeader("Token");
            String userId = httpServletRequest.getHeader("Token-Userid");
            logger.info("Token ********* : " + token);
            logger.info("Token-Userid ********* : " + userId);
            int tokenState = Jwt.validIsToken(token);
            switch (tokenState) {
                case -1:
                case 1:
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    logger.info("token验证成功*************");
                    return true;
                default:
                    httpServletResponse.setStatus(999);
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    logger.info("成功拦截************* servletPath：" + servletPath + " token:"+token);
                    return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
