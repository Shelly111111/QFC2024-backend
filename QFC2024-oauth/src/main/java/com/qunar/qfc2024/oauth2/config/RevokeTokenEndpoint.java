package com.qunar.qfc2024.oauth2.config;

import com.qunar.qfc2024.oauth2.res.Result;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 删除token服务
 *
 * @author zhangge
 * @date 2024/3/18
 */
@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    /**
     * 删除token
     *
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @author zhangge
     * @date 2024/3/18
     */
    @RequestMapping(method = RequestMethod.POST, value = "/oauth/revoke")
    @ResponseBody
    public Result revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            if (tokenServices.revokeToken(tokenId)) {
                return new Result(Result.SUCCESS, "删除token成功！");
            }
            return new Result(Result.NOT_FOUND, "token未存在，可能您输入的token错误！");
        }
        return new Result(Result.ERROR, "您输入的token格式错误！");
    }

}