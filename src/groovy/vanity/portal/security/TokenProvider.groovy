package vanity.portal.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import sun.misc.BASE64Decoder
import sun.misc.BASE64Encoder
import vanity.portal.security.token.RegistrationToken
import vanity.portal.security.token.UserToken
import vanity.user.User

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Component
class TokenProvider {

    @Value('${token.algorithm}')
    private String algorithm;

    @Value('${token.secretKey}')
    private String key;

    @Value('${token.timeToLive}')
    private Long timeToLive;

    public UserToken encodeUserToken(final String publicToken) throws Exception {
        String serializedToken = decrypt(publicToken);
        UserToken userToken = UserToken.buildFromSerializedString(serializedToken);

        if (!userToken.isValid(timeToLive)) {
            throw new IllegalStateException('Token is invalidated')
        }

        return userToken
    }

    public String decodeAsUserToken(final User user) throws Exception {
        UserToken userToken = UserToken.buildFromUser(user);
        return encrypt(userToken.serialize());
    }

    public RegistrationToken encodeRegistrationToken(final String publicToken) throws Exception {
        String serializedToken = decrypt(publicToken);
        return RegistrationToken.buildFromSerializedString(serializedToken);
    }

    public String decodeAsRegistrationToken(final User user) throws Exception {
        RegistrationToken token = RegistrationToken.buildFromUser(user);
        return encrypt(token.serialize());
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
