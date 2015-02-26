package com.instari.encryption;

import com.instari.encoding.Base64;
import com.instari.hashing.Hash;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
import sun.security.rsa.RSAKeyPairGenerator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by arik on 2/23/15.
 */
public class RSA {

    private static final String ALGORITHM = "RSA";
    private static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    private static final String PRIVATE_KEY_PREFIX = "-----BEGIN RSA PRIVATE KEY-----\n";
    private static final String PRIVATE_KEY_SUFFIX = "\n-----END RSA PRIVATE KEY-----\n";

    private static final String PUBLIC_KEY_PREFIX = "-----BEGIN PUBLIC KEY-----\n";
    private static final String PUBLIC_KEY_SUFFIX = "\n-----END PUBLIC KEY-----\n";

    public static RSAKeyPair generateKeyPair() throws NoSuchAlgorithmException, IOException, NoSuchProviderException {

        return generateKeyPair(4096);
        // return null;

    }

    public static RSAKeyPair generateKeyPair(int size) throws IOException {

        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // this should never happen because the algorithm is fixed
        }

        keyGen.initialize(size);
        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKeyInfo pki = PrivateKeyInfo.getInstance(keyPair.getPrivate().getEncoded());
        RSAPrivateKeyStructure cryptographyStandard1PrivateKey = RSAPrivateKeyStructure.getInstance(pki.getPrivateKey());
        byte[] cryptographyStandard1PrivateKeyBytes = cryptographyStandard1PrivateKey.getEncoded();

        String privateKey = PRIVATE_KEY_PREFIX + Base64.encode(cryptographyStandard1PrivateKeyBytes) + PRIVATE_KEY_SUFFIX;
        String publicKey = PUBLIC_KEY_PREFIX + Base64.encode(keyPair.getPublic().getEncoded()) + PUBLIC_KEY_SUFFIX;

        return new RSAKeyPair(privateKey, publicKey);

    }

    public static String encryptWithPublic(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, InvalidKeyException {

        // initialize
        byte[] byteData = data.getBytes(); // convert string to byte array
        PublicKey keyObject = extractPublicKey(publicKey);

        // encrypt
        Cipher cipher = null; // create conversion processing object
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }

        cipher.init(Cipher.ENCRYPT_MODE, keyObject); // initialize object's mode and key
        byte[] encryptedByteData = cipher.doFinal(byteData); // use object for encryption

        // return
        return Base64.encode(encryptedByteData);

    }

    public static String decryptWithPrivate(String encryptedData, String privateKey) throws IOException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException {

        // initialize
        byte[] encryptedByteData = Base64.decode(encryptedData);
        PrivateKey keyObject = extractPrivateKey(privateKey);

        // encrypt
        Cipher cipher = null; // create conversion processing object
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }

        cipher.init(Cipher.DECRYPT_MODE, keyObject); // initialize object's mode and key
        byte[] byteData = cipher.doFinal(encryptedByteData); // use object for encryption

        // return
        return new String(byteData);

    }

    public static String encryptWithPrivate(String data, String privateKey){
        return null;
    }

    public static String decryptWithPublic(String data, String publicKey){
        return null;
    }

    public static String sign(String data, String privateKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = signatureHash(data);
        return encryptWithPrivate(hash, privateKey);
    }

    public static boolean isSignatureValid(String data, String signature, String publicKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = signatureHash(data);
        String foundValue = decryptWithPublic(signature, publicKey);
        return hash.equals(foundValue);
    }

    private static PublicKey extractPublicKey(String publicKey) throws IOException, InvalidKeySpecException {

        String filteredKey = publicKey.replaceFirst(PUBLIC_KEY_PREFIX, "").replaceFirst(PUBLIC_KEY_SUFFIX, "");
        byte[] publicKeyBytes = Base64.decode(filteredKey);

        PublicKey keyObject = null;
        try {
            keyObject = KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        return keyObject;

    }

    private static PrivateKey extractPrivateKey(String privateKey) throws IOException, InvalidKeySpecException {

        String filteredKey = privateKey.replaceFirst(PRIVATE_KEY_PREFIX, "").replaceFirst(PRIVATE_KEY_SUFFIX, "");
        byte[] privateKeyBytes = Base64.decode(filteredKey);


        // PrivateKeyInfo pki = PrivateKeyInfo.getInstance(privateKeyBytes);

        // PKCS1EncodedKeySpec

        // byte [] asn1PrivateKeyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(filteredKey.getBytes("US-ASCII"));
        byte [] asn1PrivateKeyBytes = Base64.decode(filteredKey);
        RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(asn1PrivateKeyBytes));
        RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());
        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // should never happen
        }
        return kf.generatePrivate(rsaPrivKeySpec);




        /*PrivateKey keyObject = null;
        try {
            keyObject = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        return keyObject;*/

    }

    private static String signatureHash(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return Hash.sha512(input);
    }

    static class RSAKeyPair{

        private String privateKey;
        private String publicKey;

        private RSAKeyPair(String privateKey, String publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }
    }

}


