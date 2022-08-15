package com.yichen.casetest.test;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/15 18:05
 * @describe 加解密 研究 测试
 *   =>  https://stackoverflow.com/questions/13500368/encrypt-and-decrypt-large-string-in-java-using-rsa
 *   =>  http://www.stellarbuild.com/blog/article/encrypting-and-decrypting-large-data-using-java-and-rsa
 */
public class EnDecryptTest {

    public static void main(String[] args) throws Exception {
        // Get an instance of the RSA key generator
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        // Generate the keys — might take sometime on slow computers
        KeyPair myPair = kpg.generateKeyPair();
        // Get an instance of the Cipher for RSA encryption/decryption
        Cipher c = Cipher.getInstance("RSA");
        // Initiate the Cipher, telling it that it is going to Encrypt, giving it the public key
        c.init(Cipher.ENCRYPT_MODE, myPair.getPublic());
        // Create a secret message
        String myMessage = new String("Secret Message");
        // Encrypt that message using a new SealedObject and the Cipher we created before
        SealedObject myEncryptedMessage= new SealedObject( myMessage, c);
        // Get an instance of the Cipher for RSA encryption/decryption
        Cipher dec = Cipher.getInstance("RSA");
        // Initiate the Cipher, telling it that it is going to Decrypt, giving it the private key
        dec.init(Cipher.DECRYPT_MODE, myPair.getPrivate());
        // Tell the SealedObject we created before to decrypt the data and return it
        String message = (String) myEncryptedMessage.getObject(dec);
        System.out.println("foo = "+message);
    }

}
