package tax_project.authenticationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import tax_project.authenticationservice.dto.request.AuthRequest;
import tax_project.authenticationservice.dto.response.AuthResponse;
import tax_project.authenticationservice.dto.response.ResponseUserAuthDetailsDTO;
import tax_project.authenticationservice.proxy.UserProxy;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtUtils jwtUtils;
    private final UserProxy userProxy;
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "YourSecretKey123";

    public AuthResponse authenticate(AuthRequest authRequest){

        ResponseUserAuthDetailsDTO userByUserName = userProxy.getUserByUserName(authRequest.getUsername(), authRequest.getWalletaddress());
        boolean checkpw = BCrypt.checkpw(authRequest.getPassword(), userByUserName.getPassword());

        if (checkpw){
            String access_token = jwtUtils.generate(userByUserName.getUsername(),userByUserName.getJob_title(),userByUserName.getPrivateKey());
            String refresh_token = jwtUtils.generate(userByUserName.getUsername(),userByUserName.getJob_title(),userByUserName.getPrivateKey());

            return new AuthResponse(encrypt(access_token),encrypt(refresh_token));
        }
        else {
            return null;
        }
    }
    private static String encrypt(String plaintext) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] iv = cipher.getIV();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

            byte[] combined = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
