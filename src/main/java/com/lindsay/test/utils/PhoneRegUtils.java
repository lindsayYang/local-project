package com.lindsay.test.utils;

import java.util.regex.Pattern;

/**
 * @author: Lindsay
 * @Date: 2018/9/19 16:23
 * @Description: 手机号正则
 */
public class PhoneRegUtils {

    /**
     * 是否是合法的手机号
     *
     * @param phone
     * @return
     */
    public static boolean isLegalPhoneNumber(String phone) {
        /**
         * 中国电信：China Telecom
         * 133、149、153、173、177、180、181、189、199
         */
        String ct = "^1((33|49|53|73|77|80|81|89|99)[0-9])\\d{7}$";
        /**
         * 中国联通：China Unicom
         * 130、131、132、145、155、156、166、171、175、176、185、186
         */
        String cu = "^1(30|31|32|45|55|56|66|71|75|76|85|86)\\d{8}$";
        /**
         * 中国移动：China Mobile
         * 134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
         */
        String cm = "^1(34[0-8]|(3[5-9]|47|5[012789]|78|8[23478]|98)[0-9])\\d{7}$";
        /**
         * 其他号段
         * 14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
         */
        String co = "^14\\d{9}$";
        /**
         * 虚拟运营商
         * 电信：1700、1701、1702
         * 移动：1703、1705、1706
         * 联通：1704、1707、1708、1709、171
         */
        String cx = "^1(700|701|702|703|705|706|66|704|707|708|709)\\d{7}$";
        /**
         * 卫星通信：1349
         */
        String cw = "^1349\\d{7}$";
        if (Pattern.matches(cm, phone)) {
            return true;
        } else if (Pattern.matches(cu, phone)) {
            return true;
        } else if (Pattern.matches(ct, phone)) {
            return true;
        } else if (Pattern.matches(co, phone)) {
            return true;
        } else if (Pattern.matches(cx, phone)) {
            return true;
        } else if (Pattern.matches(cw, phone)) {
            return true;
        }
        return false;
    }

}
