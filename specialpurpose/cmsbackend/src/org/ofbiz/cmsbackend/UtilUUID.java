package org.ofbiz.cmsbackend;

import java.util.UUID;

/**
 * 由UUID和短域名想到的

文章分类:Java编程
帖子地址：http://www.iteye.com/topic/1017979
UUID是把128个二进制数，转换成32个16进制数的，每4个二进制数转换成一个16进制数。

如果是64（2的6次方）进制的话，应该是6个二进制数转换一个64进制数。

我们可以在UUID前面补加一个16进行数，让它成为33位的16进制数，共是132位二进制数。

这样就可以用22个64进制数表示132（22*6）位的二进制数。

结论是，可以把36位的UUID，去掉“-”变成32位的16进制数。
在这个数前面补一个16进制数，比如“0”，就变成了33位（132个二进制数，33*4）。
再把这个33位的16进制数，转换成22位的64进制数。

最终是把36位的UUID，变成一个22位的数表示，短了14位。
 */
/**
 * 
 * @author hezengyao
 * @since 2010-08
 */
public class UtilUUID {

    public static void main(String[] args) {
        //String uuid = "1838efee-893d-3778-b480-43d6457767ea";
        String uuid = "1a2d1f73-f38e-4603-a577-e3676257a70c";
        
        System.out.println("原始uuid：" + uuid);

        uuid = uuid.replaceAll("-", "").toUpperCase();
        System.out.println("去掉'-'变大写:" + uuid);

        uuid = "0" + uuid;
        System.out.println("前补16进制'0':" + uuid);
        
        uuid = hexToBinary(uuid);
        System.out.println("转换为132位2进制数:" + uuid);
        
        uuid = binaryTo64(uuid);
        System.out.println("转换为64进制数:" + uuid);
    }

    public static String uuidTomini() {
        String uuid=UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "").toUpperCase();
        uuid = "0" + uuid;
        uuid = hexToBinary(uuid);
        uuid = binaryTo64(uuid);
        return uuid;
    }
    public static String hexToBinary(String hex) {
        StringBuffer sb = new StringBuffer();
        if (hex == null || "".equals(hex))
            return "";

        for (int i = 0; i < hex.length(); i++) {
            sb.append(hexToBinary(hex.charAt(i)));
        }

        return sb.toString();
    }

    public static String hexToBinary(char hex) {
        String binary = null;

        switch (hex) {
        case '0':
            binary = "0000";
            break;
        case '1':
            binary = "0001";
            break;
        case '2':
            binary = "0010";
            break;
        case '3':
            binary = "0011";
            break;
        case '4':
            binary = "0100";
            break;
        case '5':
            binary = "0101";
            break;
        case '6':
            binary = "0110";
            break;
        case '7':
            binary = "0111";
            break;
        case '8':
            binary = "1000";
            break;
        case '9':
            binary = "1001";
            break;
        case 'A':
            binary = "1010";
            break;
        case 'B':
            binary = "1011";
            break;
        case 'C':
            binary = "1100";
            break;
        case 'D':
            binary = "1101";
            break;
        case 'E':
            binary = "1110";
            break;
        case 'F':
            binary = "1111";
            break;
        default:
            binary = "";
        }
        return binary;
    }

    public static String binaryTo64(String binary) {
        StringBuffer sb = new StringBuffer();
        if (binary == null || "".equals(binary))
            return "";
        if ((binary.length() % 6) != 0)
            return "";

        for (int i = 0; i < binary.length(); i += 6) {
            sb.append(binaryTo64a(binary.substring(i, i + 6)));
        }

        return sb.toString();
    }

    public static String binaryTo64a(String binary) {
        String result = null;
        if ("000000".equals(binary)) {
            result = "0";
        } else if ("000001".equals(binary)) {
            result = "1";
        } else if ("000010".equals(binary)) {
            result = "2";
        } else if ("000011".equals(binary)) {
            result = "3";
        } else if ("000100".equals(binary)) {
            result = "4";
        } else if ("000101".equals(binary)) {
            result = "5";
        } else if ("000110".equals(binary)) {
            result = "6";
        } else if ("000111".equals(binary)) {
            result = "7";
        } else if ("001000".equals(binary)) {
            result = "8";
        } else if ("001001".equals(binary)) {
            result = "9";
        } else if ("001010".equals(binary)) {
            result = "a";
        } else if ("001011".equals(binary)) {
            result = "b";
        } else if ("001100".equals(binary)) {
            result = "c";
        } else if ("001101".equals(binary)) {
            result = "d";
        } else if ("001110".equals(binary)) {
            result = "e";
        } else if ("001111".equals(binary)) {
            result = "f";
        } else if ("010000".equals(binary)) {
            result = "g";
        } else if ("010001".equals(binary)) {
            result = "h";
        } else if ("010010".equals(binary)) {
            result = "i";
        } else if ("010011".equals(binary)) {
            result = "j";
        } else if ("010100".equals(binary)) {
            result = "k";
        } else if ("010101".equals(binary)) {
            result = "l";
        } else if ("010110".equals(binary)) {
            result = "m";
        } else if ("010111".equals(binary)) {
            result = "n";
        } else if ("011000".equals(binary)) {
            result = "o";
        } else if ("011001".equals(binary)) {
            result = "p";
        } else if ("011010".equals(binary)) {
            result = "q";
        } else if ("011011".equals(binary)) {
            result = "r";
        } else if ("011100".equals(binary)) {
            result = "s";
        } else if ("011101".equals(binary)) {
            result = "t";
        } else if ("011110".equals(binary)) {
            result = "u";
        } else if ("011111".equals(binary)) {
            result = "v";
        } else if ("100000".equals(binary)) {
            result = "w";
        } else if ("100001".equals(binary)) {
            result = "x";
        } else if ("100010".equals(binary)) {
            result = "y";
        } else if ("100011".equals(binary)) {
            result = "z";
        } else if ("100100".equals(binary)) {
            result = "A";
        } else if ("100101".equals(binary)) {
            result = "B";
        } else if ("100110".equals(binary)) {
            result = "C";
        } else if ("100111".equals(binary)) {
            result = "D";
        } else if ("101000".equals(binary)) {
            result = "E";
        } else if ("101001".equals(binary)) {
            result = "F";
        } else if ("101010".equals(binary)) {
            result = "G";
        } else if ("101011".equals(binary)) {
            result = "H";
        } else if ("101100".equals(binary)) {
            result = "I";
        } else if ("101101".equals(binary)) {
            result = "J";
        } else if ("101110".equals(binary)) {
            result = "K";
        } else if ("101111".equals(binary)) {
            result = "L";
        } else if ("110000".equals(binary)) {
            result = "M";
        } else if ("110001".equals(binary)) {
            result = "N";
        } else if ("110010".equals(binary)) {
            result = "O";
        } else if ("110011".equals(binary)) {
            result = "P";
        } else if ("110100".equals(binary)) {
            result = "Q";
        } else if ("110101".equals(binary)) {
            result = "R";
        } else if ("110110".equals(binary)) {
            result = "S";
        } else if ("110111".equals(binary)) {
            result = "T";
        } else if ("111000".equals(binary)) {
            result = "U";
        } else if ("111001".equals(binary)) {
            result = "V";
        } else if ("111010".equals(binary)) {
            result = "W";
        } else if ("111011".equals(binary)) {
            result = "X";
        } else if ("111100".equals(binary)) {
            result = "Y";
        } else if ("111101".equals(binary)) {
            result = "Z";
        } else if ("111110".equals(binary)) {
            result = "_";
        } else if ("111111".equals(binary)) {
            result = "-";
        } else {
            result = "";
        }

        return result;
    }
}


