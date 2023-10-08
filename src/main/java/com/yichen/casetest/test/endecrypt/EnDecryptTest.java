package com.yichen.casetest.test.endecrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;
import java.nio.charset.StandardCharsets;
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

    private static KeyPair keypair;
    private static Cipher cipher;

    public static void main(String[] args) throws Exception {
        normal2();
    }

    private static void normal2() throws Exception{
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        keypair = kpg.generateKeyPair();
//        System.out.println("public key => " + new String(keypair.getPublic().getEncoded()));
//        System.out.println("private key => " + new String(keypair.getPrivate().getEncoded()));

        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keypair.getPublic());
//        String encrypt = new String(blockCipher("hello wrold".getBytes(StandardCharsets.UTF_8), Cipher.ENCRYPT_MODE), "utf8");
        String encrypt = new String(blockCipher(Base64.encodeBase64("hello world".getBytes(StandardCharsets.UTF_8)), Cipher.ENCRYPT_MODE), "utf8");
        System.out.println(encrypt);
        cipher.init(Cipher.DECRYPT_MODE, keypair.getPrivate());
//        String originText = new String(blockCipher(encrypt.getBytes(StandardCharsets.UTF_8), Cipher.DECRYPT_MODE), "utf8");
        String originText = new String(Base64.decodeBase64(blockCipher(encrypt.getBytes(StandardCharsets.UTF_8), Cipher.DECRYPT_MODE)), "utf8");
        System.out.println(originText);
    }

    private static byte[] blockCipher(byte[] bytes, int mode) throws IllegalBlockSizeException, BadPaddingException {
        // string initialize 2 buffers.
        // scrambled will hold intermediate results
        byte[] scrambled = new byte[0];

        // toReturn will hold the total result
        byte[] toReturn = new byte[0];
        // if we encrypt we use 100 byte long blocks. Decryption requires 128 byte long blocks (because of RSA)
        int length = (mode == Cipher.ENCRYPT_MODE)? 100 : 128;

        // another buffer. this one will hold the bytes that have to be modified in this step
        byte[] buffer = new byte[length];

        for (int i=0; i< bytes.length; i++){

            // if we filled our buffer array we have our block ready for de- or encryption
            if ((i > 0) && (i % length == 0)){
                //execute the operation
                scrambled = cipher.doFinal(buffer);
                // add the result to our total result.
                toReturn = append(toReturn,scrambled);
                // here we calculate the length of the next buffer required
                int newlength = length;

                // if newlength would be longer than remaining bytes in the bytes array we shorten it.
                if (i + length > bytes.length) {
                    newlength = bytes.length - i;
                }
                // clean the buffer array
                buffer = new byte[newlength];
            }
            // copy byte into our buffer.
            buffer[i%length] = bytes[i];
        }

        // this step is needed if we had a trailing buffer. should only happen when encrypting.
        // example: we encrypt 110 bytes. 100 bytes per run means we "forgot" the last 10 bytes. they are in the buffer array
        scrambled = cipher.doFinal(buffer);

        // final step before we can return the modified data.
        toReturn = append(toReturn,scrambled);

        return toReturn;
    }


    private static byte[] append(byte[] prefix, byte[] suffix){
        byte[] toReturn = new byte[prefix.length + suffix.length];
        for (int i=0; i< prefix.length; i++){
            toReturn[i] = prefix[i];
        }
        for (int i=0; i< suffix.length; i++){
            toReturn[i+prefix.length] = suffix[i];
        }
        return toReturn;
    }

    private static void normal1() throws Exception{
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
