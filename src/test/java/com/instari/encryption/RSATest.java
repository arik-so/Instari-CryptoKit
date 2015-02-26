package com.instari.encryption;

import com.instari.encoding.Base64;
import org.junit.Test;

import static org.junit.Assert.*;

public class RSATest {

    @Test
    public void testGenerateKeyPair() throws Exception {

        RSA.RSAKeyPair keyPair = RSA.generateKeyPair(2048);

        assertEquals("Private key PKCS#1 conformity (length)", 1675, keyPair.getPrivateKey().length(), 30);
        assertEquals("Public key PKCS#1/PKCS#8 conformity (length)", 450, keyPair.getPublicKey().length(), 10);

    }

    @Test
    public void testEncryption() throws Exception {

        String doubleBase64PublicKey = "LS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS0KTUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FR\n" +
                "OEFNSUlCQ2dLQ0FRRUFoN3hNc0NwaTFXT1d5RysrcFd1N2xud3c1eWRsemhlZQpscjNIWkJLd0kx\n" +
                "SDFRcHhXTE5rVi9KME5CaEVLd0dSNmgwaG1pd0JheDhWcTU0YnV6aWN3bTBMMjl0RTU2WTRTYmFs\n" +
                "NFV6QXlxcDJjCnRIbk03aENyTGwvc1ZjVU01bllVLzFRMWUzNDZOOXpxZkpCY1VkcjdsUFZLWTdz\n" +
                "Qk9xdTk3ZVFwUDBMSVlHUGpUOGluT0FRM200MlYKRkFiZi9TQ0tVZnZjQjdBWlUrUklIOXhMREMx\n" +
                "MkE0RDNXQ3FINDdJRWJLdEdNbFR1aGxDWWZLM0JwNXRSdkk0NzdhV1BjMlVhVWZZeAo1RHVLQlhH\n" +
                "Y3VaYm05ZFRtTTdpODhiblJ4dENrTmU4bkdrNVNEL1NPRHY1NHRHR1ZSMVVMR3IrOHlicEkyQ0o2\n" +
                "bG5aZWVJVlNUaTg4ClpkU015UUlEQVFBQgotLS0tLUVORCBQVUJMSUMgS0VZLS0tLS0K";

        String doubleBase64PrivateKey = "LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBaDd4TXNDcGkx\n" +
                "V09XeUcrK3BXdTdsbnd3NXlkbHpoZWVscjNIWkJLd0kxSDFRcHhXTE5rVi9KME5CaEVLCndHUjZo\n" +
                "MGhtaXdCYXg4VnE1NGJ1emljd20wTDI5dEU1Nlk0U2JhbDRVekF5cXAyY3RIbk03aENyTGwvc1Zj\n" +
                "VU01bllVLzFRMWUzNDYKTjl6cWZKQmNVZHI3bFBWS1k3c0JPcXU5N2VRcFAwTElZR1BqVDhpbk9B\n" +
                "UTNtNDJWRkFiZi9TQ0tVZnZjQjdBWlUrUklIOXhMREMxMgpBNEQzV0NxSDQ3SUViS3RHTWxUdWhs\n" +
                "Q1lmSzNCcDV0UnZJNDc3YVdQYzJVYVVmWXg1RHVLQlhHY3VaYm05ZFRtTTdpODhiblJ4dENrCk5l\n" +
                "OG5HazVTRC9TT0R2NTR0R0dWUjFVTEdyKzh5YnBJMkNKNmxuWmVlSVZTVGk4OFpkU015UUlEQVFB\n" +
                "QkFvSUJBQkMzVU41MUtkekwKdWZzUFAvUlJVelhTTDVlWUU0S1JDQmZhWVY3aXFreC9RR3dtb3Jv\n" +
                "OU5lYjVWR3hGMXdRZ203SmphVFYvSXE3dGtoL1hUenVXT2ZjMQpCUU1hZXo1WEg4L2ZpaEQ4bzVD\n" +
                "OWZsbi9rZ3hUZ3pGZjROUWpRMVp5ZmwxL2VtS3VCZTl4Um5OMnQ5ZmcxeERlTUg0ZHZxWUNGbzdn\n" +
                "CmhXL2tBcGRVSjhvTUx6Z3hXVTlNZXRFSW5RMGZWM3JWVE1jeGNxWGJZR2N2NW1JMkFDd2NEazVa\n" +
                "algxeDMxVlYzNDdrWUFiRHAzckoKdHpVeTJoYWRMcUtIcXE4bGJCcGtRaG1BaTdobkszclBmZGxl\n" +
                "TFZDNFMzOUVBdlJLdUtSZmwrS1picENtNUpDN1B4WWxrMjFUT2trOQpkVUE5S2NGbnlPMytuZSs3\n" +
                "QnVWNitkVllHRjBDZ1lFQXl5QU1ja3JoM3lNaXJJRHh6VHlFcFFZMUtBK2RUenMrUGRxWlMrU2tQ\n" +
                "NFQ1Ck5VSjNuSGJkREhlRm0rTWl6NHRTUkpRZU9VWlRrSnBRdXZYbzR4K1h2ZVNidEovclozWEVN\n" +
                "czhEeDlMdXZ6ZldTZHN6M1FNRkRRV0oKSXFpVncreXF5OFhrelVzbXk3cVgxbGN5UDJNeGE1MHhZ\n" +
                "QkVxa2ZEanYzUytWcEpnUlNzQ2dZRUFxeEdDSHlmTG5IeHJ1c0c2QWJwWgpFOUpOSTJtRjl0K0Yx\n" +
                "bXkxdkhKcFFwdHpTVm51REVrYStuVHZOVDVyTjRheEhnRXpJd3JJTklybkdENE1Id2NDSWZuU3dC\n" +
                "TFg2akwzCnUrQ2wwSXJXK0J0MDgwN2U4NFNuYnYrcjRwQVF3bGdGZm10OU5UOUcycFh5eW4zNGJk\n" +
                "WWJiMzkzNVlhc28wREY3MU1OeEhyTm85c0MKZ1lFQW1YdHQ3QzZJM1lJL0FQbXR1OWF1bzhNOUJ6\n" +
                "aEZOVWtlanI1T2R2b2M0YUlvYkFYOU8welZXa1kxdnNTeDJRRDgrTFN5NGJReQpFR0dKL3JzeGJl\n" +
                "ekJTOHNobkNuNlJnTEdiVjNmRFc1azJEZm93NFhPZW9GOW40ay9SZUQ4eVQ1YkQvNUlGSmRraVN4\n" +
                "RTdzQ1VSekU4CmRvUWdldVo5dENqQTdqbllXTEhoN0dFQ2dZQXRJRWs2L3J4dlkwTG4yUUxBUWZB\n" +
                "TmtvUld1eGRqc0VNVjlVZGJscks5cDFRYUhXYjIKUnJLZkx3bUsraFErYVlGL2NNR1VsTnVMUUxr\n" +
                "RlpCaStkcExSYzUxc25Zdm5jOXAxN1NUUWhrOSswMzllVDRmd3BTV2wyanYxWUJ1TApxTHlLUk1D\n" +
                "YXN6NTFtdWlaRWZIZXg0UWxQWExiVVZOcEhVVVpoTWMwOVZBWjBRS0JnUURDWTZxM21QTnFCTXhF\n" +
                "ZGFXNGFkWHBIQkFyCm1aSjZmSW9kQTl6cEJEbDlDaVdERjl1V01vQndhRHFlQmhnWURFc1JXanpQ\n" +
                "andjc0o2eVFyKytacTlBNTQ5ZnBTNFk5RGwrOXpWL0UKalFLczF2b0l4QUNodlZhc2RVZXJ1ZUQ4\n" +
                "aWxSZnpkNXFjZUNmNnVhRWJZaEdKd2FDd0dFRzFreTdKWXBRNFduZi94bUJadz09Ci0tLS0tRU5E\n" +
                "IFJTQSBQUklWQVRFIEtFWS0tLS0tCg==";

        String publicKey = new String(Base64.decode(doubleBase64PublicKey));
        String privateKey = new String(Base64.decode(doubleBase64PrivateKey));

        String data = "Hello!";
        String encryptedData = RSA.encryptWithPublic(data, publicKey);

        String decryptedData = RSA.decryptWithPrivate(encryptedData, privateKey);

        assertEquals("RSA two-way encryption works", data, decryptedData);

    }

    @Test
    public void testSigning() throws Exception {

        RSA.RSAKeyPair keyPair = RSA.generateKeyPair(4096);

        String data = "World?!";
        String encryptedData = RSA.encryptWithPrivate(data, keyPair.getPrivateKey());
        String decryptedData = RSA.decryptWithPublic(encryptedData, keyPair.getPublicKey());

        assertEquals("RSA two-way signing works", data, decryptedData);

        String signature = RSA.sign(data, keyPair.getPrivateKey());
        boolean validity = RSA.isSignatureValid(data, signature, keyPair.getPublicKey());

        assertTrue("Signature verification works", validity);

    }
}