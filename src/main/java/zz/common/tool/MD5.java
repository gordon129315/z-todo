package zz.common.tool;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5 {
    public static String md5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = new byte[0];
        try {
            byteArray = inStr.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = "123";
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5(str));
        str = "123";
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5(str));
        str = "1";
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5(str));
        str = "1";
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5(str));
    }
}

