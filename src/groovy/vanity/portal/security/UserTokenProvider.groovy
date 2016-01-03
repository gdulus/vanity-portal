package vanity.portal.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import sun.misc.BASE64Decoder
import sun.misc.BASE64Encoder
import vanity.user.User

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Component
class UserTokenProvider {

    @Value('${token.algorithm}')
    private String algorithm;

    @Value('${token.secretKey}')
    private String key;

    @Value('${token.timeToLive}')
    private Long timeToLive;

    public UserToken encode(final String publicToken) throws Exception {
        String serializedToken = decrypt(publicToken);
        return UserToken.buildFromSerializedString(serializedToken);
    }

    public String decode(final User user) throws Exception {
        UserToken userToken = UserToken.buildFromPrincipal(user);
        return encrypt(userToken.serlialize());
    }

    private String encrypt(final String token) throws Exception {
        byte[] hasil = getCipher(Cipher.ENCRYPT_MODE).doFinal(token.getBytes());
        return new BASE64Encoder().encode(hasil);
    }

    private String decrypt(String string) throws Exception {
        byte[] hasil = getCipher(Cipher.DECRYPT_MODE).doFinal(new BASE64Decoder().decodeBuffer(string));
        return new String(hasil);
    }

    private Cipher getCipher(final int mode) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.bytes, algorithm)
        Cipher cipher = Cipher.getInstance(algorithm)
        cipher.init(mode, secretKeySpec)
        return cipher
    }
}
