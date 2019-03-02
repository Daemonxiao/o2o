package com.wu.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    //验证输入的验证码是否正确
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request,
                "verifyCodeActual");
        if (verifyCodeActual == null
                || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}
