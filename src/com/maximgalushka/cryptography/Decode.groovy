package com.maximgalushka.cryptography

/**
 * @since 7/4/2014.
 */

def string_to_array = { String s ->
    def A = s.toCharArray();
    def result = new byte[2 * s.length()] as byte[];
    for (int i = 0; i < s.length(); i++) {
        result[2 * i] = (byte) ((int) A[i]) >> 4;
        result[2 * i + 1] = (byte) (((int) A[i])) & 0xf;
    }
    return result;
}

def xor = { String a, String b ->
    def a_arr = a.toCharArray();
    def b_arr = b.toCharArray();
    int max = Math.min(a_arr.length, b_arr.length);
    def result = new byte[2 * max] as byte[];
    for (int i = 0; i < max; i++) {
        result[2 * i] = (byte) (((int) a_arr[i]) ^ ((int) b_arr[i])) >> 4;
        result[2 * i + 1] = (byte) (((int) a_arr[i]) ^ ((int) b_arr[i])) & 0xf;
    }
    return result;
}

def xor_arrays = { byte[] a, byte[] b ->
    int max = Math.min(a.length, b.length);
    def result = new byte[max] as byte[];
    for (int i = 0; i < max; i++) {
        result[i] = a[i] ^ b[i];
    }
    return result;
}

def to_array = { s ->
    def A = s.toCharArray();
    def result = new byte[A.length] as byte[];
    for (int i = 0; i < A.length; i++) {
        result[i] = (byte) Byte.parseByte(String.valueOf(A[i]), 16);
    }
    return result;
}

def print_arr = { byte[] bt ->
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bt.length; i++) {
        sb.append(Integer.toString((int) bt[i], 16));
    }
    return sb.toString();
}


def T1 = "315c4eeaa8b5f8aaf9174145bf43e1784b8fa00dc71d885a804e5ee9fa40b16349c146fb778cdf2d3aff021dfff5b403b510d0d0455468aeb98622b137dae857553ccd8883a7bc37520e06e515d22c954eba5025b8cc57ee59418ce7dc6bc41556bdb36bbca3e8774301fbcaa3b83b220809560987815f65286764703de0f3d524400a19b159610b11ef3e";
def T2 = "234c02ecbbfbafa3ed18510abd11fa724fcda2018a1a8342cf064bbde548b12b07df44ba7191d9606ef4081ffde5ad46a5069d9f7f543bedb9c861bf29c7e205132eda9382b0bc2c5c4b45f919cf3a9f1cb74151f6d551f4480c82b2cb24cc5b028aa76eb7b4ab24171ab3cdadb8356f";
def T3 = "32510ba9a7b2bba9b8005d43a304b5714cc0bb0c8a34884dd91304b8ad40b62b07df44ba6e9d8a2368e51d04e0e7b207b70b9b8261112bacb6c866a232dfe257527dc29398f5f3251a0d47e503c66e935de81230b59b7afb5f41afa8d661cb";

println(print_arr(xor_arrays(to_array(T1), to_array(T2))));

println print_arr(string_to_array(" "));
println print_arr(string_to_array("a"));

for (char c = 'A'; c <= 'Z'; c++) {
    println "${c} -> \"${print_arr(xor(" ", String.valueOf(c)))}\"";
}

for (char c = 'a'; c <= 'z'; c++) {
    println "${c} -> \"${print_arr(xor(" ", String.valueOf(c)))}\"";
}