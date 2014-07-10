package com.maximgalushka.cryptography.week1;

import static com.maximgalushka.cryptography.Tools.*;

/**
 * @since 7/9/2014.
 */
public class CharsOps {

    public String toString(String encoded) {
        return new String(chars(encoded));
    }

    public String print(char[] string) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (char c : string) {
            sb.append("");
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                sb.append(String.valueOf(c));
            } else {
                sb.append(Integer.toHexString((int) c));
            }
            sb.append("\t");
        }
        sb.append("");
        return sb.toString();
    }

    public String printEncoded(char[] string) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (char c : string) {
            sb.append("");
            sb.append(Integer.toHexString((int) c));
            sb.append("\t");
        }
        sb.append("");
        return sb.toString();
    }

    private static final String T1 = "315c4eeaa8b5f8aaf9174145bf43e1784b8fa00dc71d885a804e5ee9fa40b16349c146fb778cdf2d3aff021dfff5b403b510d0d0455468aeb98622b137dae857553ccd8883a7bc37520e06e515d22c954eba5025b8cc57ee59418ce7dc6bc41556bdb36bbca3e8774301fbcaa3b83b220809560987815f65286764703de0f3d524400a19b159610b11ef3e";
    private static final String T2 = "234c02ecbbfbafa3ed18510abd11fa724fcda2018a1a8342cf064bbde548b12b07df44ba7191d9606ef4081ffde5ad46a5069d9f7f543bedb9c861bf29c7e205132eda9382b0bc2c5c4b45f919cf3a9f1cb74151f6d551f4480c82b2cb24cc5b028aa76eb7b4ab24171ab3cdadb8356f";
    private static final String T3 = "32510ba9a7b2bba9b8005d43a304b5714cc0bb0c8a34884dd91304b8ad40b62b07df44ba6e9d8a2368e51d04e0e7b207b70b9b8261112bacb6c866a232dfe257527dc29398f5f3251a0d47e503c66e935de81230b59b7afb5f41afa8d661cb";
    private static final String T4 = "32510ba9aab2a8a4fd06414fb517b5605cc0aa0dc91a8908c2064ba8ad5ea06a029056f47a8ad3306ef5021eafe1ac01a81197847a5c68a1b78769a37bc8f4575432c198ccb4ef63590256e305cd3a9544ee4160ead45aef520489e7da7d835402bca670bda8eb775200b8dabbba246b130f040d8ec6447e2c767f3d30ed81ea2e4c1404e1315a1010e7229be6636aaa";
    private static final String T5 = "3f561ba9adb4b6ebec54424ba317b564418fac0dd35f8c08d31a1fe9e24fe56808c213f17c81d9607cee021dafe1e001b21ade877a5e68bea88d61b93ac5ee0d562e8e9582f5ef375f0a4ae20ed86e935de81230b59b73fb4302cd95d770c65b40aaa065f2a5e33a5a0bb5dcaba43722130f042f8ec85b7c2070";
    private static final String T6 = "32510bfbacfbb9befd54415da243e1695ecabd58c519cd4bd2061bbde24eb76a19d84aba34d8de287be84d07e7e9a30ee714979c7e1123a8bd9822a33ecaf512472e8e8f8db3f9635c1949e640c621854eba0d79eccf52ff111284b4cc61d11902aebc66f2b2e436434eacc0aba938220b084800c2ca4e693522643573b2c4ce35050b0cf774201f0fe52ac9f26d71b6cf61a711cc229f77ace7aa88a2f19983122b11be87a59c355d25f8e4";
    private static final String T7 = "32510bfbacfbb9befd54415da243e1695ecabd58c519cd4bd90f1fa6ea5ba47b01c909ba7696cf606ef40c04afe1ac0aa8148dd066592ded9f8774b529c7ea125d298e8883f5e9305f4b44f915cb2bd05af51373fd9b4af511039fa2d96f83414aaaf261bda2e97b170fb5cce2a53e675c154c0d9681596934777e2275b381ce2e40582afe67650b13e72287ff2270abcf73bb028932836fbdecfecee0a3b894473c1bbeb6b4913a536ce4f9b13f1efff71ea313c8661dd9a4ce";
    private static final String T8 = "315c4eeaa8b5f8bffd11155ea506b56041c6a00c8a08854dd21a4bbde54ce56801d943ba708b8a3574f40c00fff9e00fa1439fd0654327a3bfc860b92f89ee04132ecb9298f5fd2d5e4b45e40ecc3b9d59e9417df7c95bba410e9aa2ca24c5474da2f276baa3ac325918b2daada43d6712150441c2e04f6565517f317da9d3";
    private static final String T9 = "271946f9bbb2aeadec111841a81abc300ecaa01bd8069d5cc91005e9fe4aad6e04d513e96d99de2569bc5e50eeeca709b50a8a987f4264edb6896fb537d0a716132ddc938fb0f836480e06ed0fcd6e9759f40462f9cf57f4564186a2c1778f1543efa270bda5e933421cbe88a4a52222190f471e9bd15f652b653b7071aec59a2705081ffe72651d08f822c9ed6d76e48b63ab15d0208573a7eef027";
    private static final String T10 = "466d06ece998b7a2fb1d464fed2ced7641ddaa3cc31c9941cf110abbf409ed39598005b3399ccfafb61d0315fca0a314be138a9f32503bedac8067f03adbf3575c3b8edc9ba7f537530541ab0f9f3cd04ff50d66f1d559ba520e89a2cb2a83";
    private static final String X = "32510ba9babebbbefd001547a810e67149caee11d945cd7fc81a05e9f85aac650e9052ba6a8cd8257bf14d13e6f0a803b54fde9e77472dbff89d71b57bddef121336cb85ccb8f3315f4b52e301d16e9f52f904";
    private static final String SP = "3232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232323232";

    private static final String XE = "";
    private static final String E4 = "The ciphertext produced by a weak encryption algorythm looks as good as ciphertext";

    private static final String[] T = {T1, T2, T3, T4, T5, T6, T7, T8, T9, T10};

    public void process() {
        int N = T.length;
        char[] encrypted = chars(X);
        char[] code = new char[1000];
        for (int i = 0; i < N; i++) {
            char[] text = chars(T[i]);
            char[][] table = new char[N][];
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                table[j] = xor(text, chars(T[j]));
            }
            int min = minLength(i);

            for (int k = 0; k < min; k++) {
                int count = countVertical(table, k);
                if (count >= (N - 3)) {
                    code[k] = xor(text[k], ' ');
                }
            }
        }
        System.out.println(printEncoded(code));
        System.out.println(printSpaces(xor(code, encrypted)));
    }

    private int countVertical(char[][] table, int column) {
        int count = 0;
        int N = table.length;
        for (int i = 0; i < N; i++) {
            if (table[i] == null) continue;
            if (table[i].length > column) {
                char c = table[i][column];
                if (c >= 'A' && c <= 'Z') count++;
            }
        }
        return count;
    }

    private int minLength(int avoid) {
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < T.length; j++) {
            if (j == avoid) continue;
            if (min > T[j].length()) min = T[j].length();
        }
        return min;
    }

    public static void main(String[] args) {
        CharsOps ops = new CharsOps();

        //ops.process();

        char[] target = XE.toCharArray();
        char[] encrypted = chars(X);
        char[] cipher = xor(target, encrypted);
        char[] cipher2 = xor(chars(T4), E4.toCharArray());

        System.out.println(printSpaces(xor(chars(X), cipher2)));
        System.out.println(printSpaces(xor(chars(X), cipher)));

        for (int i = 0; i < T.length; i++) {
            System.out.println(printSpaces(xor(chars(T[i]), cipher)));
            System.out.println(printSpaces(xor(chars(T[i]), cipher2)));
        }

        /*
        for (char i = 'a'; i <= 'z'; i++) {
            System.out.printf("%s ^ ' ' -> %s\n", String.valueOf(i), String.valueOf(ops.xor(i, ' ')));
        }
        for (char i = 'a'; i <= 'z'; i++) {
            System.out.printf("%s ^ 'a' -> %d\n", String.valueOf(i), (int) ops.xor(i, 'a'));
        }

        //System.out.println(Arrays.toString(ops.chars(T1)));
        //System.out.printf("T1^T2 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T2))));
        System.out.printf("T1^T2  \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T2))));

        //System.out.printf("T1^T3 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T3))));
        System.out.printf("T1^T3  \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T3))));

        //System.out.printf("T1^T4 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T4))));
        System.out.printf("T1^T4  \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T4))));

        //System.out.printf("T1^T5 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T5))));
        System.out.printf("T1^T5  \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T5))));

        //System.out.printf("T1^T6 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T6))));
        System.out.printf("T1^T6  \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T6))));

        //System.out.printf("T1^T7 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T7))));
        System.out.printf("T1^T7  \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T7))));

        //System.out.printf("T1^T8 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T8))));
        System.out.printf("T1^T8  \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T8))));

        //System.out.printf("T1^T9 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T9))));
        System.out.printf("T1^T9  \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T9))));

        //System.out.printf("T1^T10 = %s\n", ops.print(ops.xor(ops.chars(T1), ops.chars(T10))));
        System.out.printf("T1^T10 \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(T10))));

        System.out.printf("T1^X   \t %s\n", ops.printSpaces(ops.xor(ops.chars(T1), ops.chars(X))));
        System.out.printf("T1^SP  \t %s\n", ops.printEncoded(ops.xor(ops.chars(T1), ops.chars(SP))));
        */
    }
}
