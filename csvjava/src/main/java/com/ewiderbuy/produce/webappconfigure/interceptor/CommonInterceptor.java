package com.ewiderbuy.produce.webappconfigure.interceptor;

import com.ewiderbuy.produce.common.tool.Jwt;
import com.ewiderbuy.produce.common.tool.ServiceUtil;
import com.ewiderbuy.produce.common.tool.Jwt;
import com.ewiderbuy.produce.common.tool.ServiceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wkm
 */
public class CommonInterceptor implements HandlerInterceptor {

    final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public synchronized boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("LfsInterceptor ********* 启动拦截器");
        String servletPath = httpServletRequest.getServletPath();
        logger.info("servletPath ********* : " + servletPath);
        if ("/ifcUser/login".equals(servletPath) ) {
            //登录时不拦截
            return true;
        } else {
            String token = "";
            token = httpServletRequest.getHeader("Token");
            String userId = httpServletRequest.getHeader("Token-Userid");
            String restType = httpServletRequest.getHeader("restType");
            logger.info("Token ********* : " + token);
            logger.info("Token-Userid ********* : " + userId);
            int tokenState = Jwt.validIsToken(token);
            switch (tokenState) {
                case 1:
                    //如果校验通过重置token时间
                    String new_token = ServiceUtil.getToken(userId);
                    httpServletResponse.setHeader("new_token",new_token);
                    httpServletResponse.setHeader("Access-Control-Expose-Headers", "new_token");
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    logger.info("token验证成功*************");
                    return true;
                case -1:

                    if (restType == "app"){
                        //app访问
                        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                        logger.info("token验证成功*************");
                        return true;
                    }else{
                        //网页访问
                        httpServletResponse.setStatus(999);
                        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                        logger.info("成功拦截************* servletPath：" + servletPath + " token:"+token);
                        return false;
                    }
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
