package com.hds;

import java.io.*;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Created by japark on 1/26/15.
 */
public class Utils {

    public static byte[] fileToByteArray(File file) throws IOException{
        byte []buffer = new byte[(int) file.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(file);
            if ( ios.read(buffer) == -1 ) {
                throw new IOException("EOF reached while trying to read the whole file");
            }
        } finally {
            try {
                if ( ios != null )
                    ios.close();
            } catch ( IOException e) {
            }
        }
        return buffer;
    }

    public static String getBase64Value(String input){
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(
                input.getBytes(StandardCharsets.UTF_8) );
        return encodedString;
    }

    public static String getMD5Value(String input){
        String md5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            md5 = sb.toString();
            }
        catch (NoSuchAlgorithmException e) {
            System.err.println("I'm sorry, but MD5 is not a valid message digest algorithm");
        }
        return md5;
    }
}
