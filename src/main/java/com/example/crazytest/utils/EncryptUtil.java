package com.example.crazytest.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author
 * @name Menghui
 * @date 2025/4/9 16:28
 * @DESRIPTION
 */

public class EncryptUtil {

  public static AES encrypt(String scrypt) {
    return SecureUtil.aes(scrypt.getBytes());
  }

}
