package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.enums.ResultEnums;
import com.chuanfangn.sell.exception.ProductException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-03 13:32
 * @version:
 **/
@Controller
@RequestMapping("/weixin")
@Slf4j
public class WxController {

    @Autowired
    WxMpService wxMpService;

    @RequestMapping("/authorize")
    public String authrize(@RequestParam(value = "returnUrl") String returnUrl) throws UnsupportedEncodingException {
        //重定向到controller,传递code和state
        String url = "http://jayzhou.natapp1.cc/sell/weixin/userinfo";
        String s = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl,"UTF-8"));
        return "redirect:"+s;
    }
    @GetMapping("/userinfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response) throws UnsupportedEncodingException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信认证]"+e.getStackTrace());
            throw new ProductException(ResultEnums.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+ state+"?openid="+openId;
    }
}
