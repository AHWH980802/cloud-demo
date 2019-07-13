package cn.itcast.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 萧一旬
 * @Description:
 * @Date: Create in 14:38 2019/4/13
 */
@Component
public class LoginFilter extends ZuulFilter {


    @Override
    public String filterType() {
        // 登录校验，肯定是在前置拦截
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        // 返回true，代表过滤器生效。
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取请求上下文
        RequestContext context = RequestContext.getCurrentContext();

        //获取request
        HttpServletRequest request = context.getRequest();

        //获取请求参数
        String token = request.getParameter("access-token");

        //判断是否存在
        if (StringUtils.isBlank(token)) {
            //不存在，未登录 拦截
            context.setSendZuulResponse(false);
            //返回403
            context.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
