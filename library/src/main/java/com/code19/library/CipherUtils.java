/*
 * Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.code19.library;

import android.util.Base64;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create by h4de5ing 2016/5/7 007
 */
public class CipherUtils {

    public static String md5Encode(InputStream in) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[8192];
            int byteCount;
            while ((byteCount = in.read(bytes)) > 0) {
                digester.update(bytes, 0, byteCount);
            }
            byte[] digest = digester.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int r = b & 0xff;
                String hex = Integer.toHexString(r);
                if (hex.length() == 1) {
                    hex = 0 + hex;
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String md5Encode(String pwd) {
        try {
            MessageDigest instance = MessageDigest.getInstance("md5");
            byte[] digest = instance.digest(pwd.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int r = b & 0xff;
                String hex = Integer.toHexString(r);
                if (hex.length() == 1) {
                    hex = 0 + hex;
                }
                sb.append(r);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String base64Encode(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }


    public static String base64Decode(String str) {
        byte[] decode = Base64.decode(str, Base64.DEFAULT);
        return String.valueOf(decode);
    }


    public static String XorEncode(String pwd, int key) {
        key = key % 128;
        byte[] bytes = pwd.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= key;
        }
        return new String(bytes);
    }
}
