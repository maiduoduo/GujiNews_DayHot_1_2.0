
package com.cnews.guji.smart.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;

/**
 * 字符串工具
 */
public class StringUtils {
    public static String toURLDecoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLDecoder.decode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
        }
        return "";
    }

    public static String toUtf8(String str) {
        String result = null;
        try {
            result = new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 加密
     *
     * @param plaintext 明文
     * @return ciphertext 密文
     */
    public final static String encrypt(String plaintext) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 四舍五入
     *      * 保留小数点
     * @param num  double类型数字
     * @param decimal 保留几位小数
     * @return
     */
    public final static String encrypt(double num,int decimal) {
        //利用字符串格式化的方式实现四舍五入,保留1位小数
        String result1 = String.format("%.1f", num);
        //1代表小数点后面的位数, 不足补0。f代表数据是浮点类型。保留2位小数就是“%.2f”。
//        System.out.println(result1);//输出3.0

        //利用BigDecimal来实现四舍五入.保留一位小数
//        double result2 = new BigDecimal(data).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        //1代表保留1位小数,保留两位小数就是2
        //BigDecimal.ROUND_HALF_UP 代表使用四舍五入的方式
//        System.out.println(result2);//输出3.0
        return  result1;
    }


    /**
     * 四舍五入
     *      以万未单位保留小数点后几位
     * @param number 要转化的数字
     * @param decimal 小数点后几位
     * @param isThouthand 是否需要千分位分隔
     * @return
     */
    public final static String getDecimalWithW(long number,int decimal,boolean isThouthand) {
        String str = "";
        if (number <= 0) {
            str = "";
        } else if (number < 10000) {
            str = number+"";
        } else {
            double d = (double) number;
            double num = d / 10000;//1.将数字转换成以万为单位的数字

            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(decimal,BigDecimal.ROUND_HALF_UP).doubleValue();//2.转换后的数字四舍五入保留小数点后一位;
            if (isThouthand) {
                //保留小数并展示千分位符
                DecimalFormat df1 = new DecimalFormat("##,##0.0");
                str = df1.format(f1);
            }else {
                str = f1 + "";
            }
        }
        return str;
    }
}
