package com.instari.encryption;

/**
 * Created by arik on 2/23/15.
 */
public class RSA {

    public static RSAKeyPair generateKeyPair(){

        String privateKey = null;
        String publicKey = null;

        return new RSAKeyPair(privateKey, publicKey);

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


