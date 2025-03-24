package com.example.Product_APII.Service;

import com.example.Product_APII.DTO.Request.AuthenticationRequest;
import com.example.Product_APII.DTO.Request.OtpRequest;
import com.example.Product_APII.DTO.Response.AuthenticationResponse;
import com.example.Product_APII.DTO.Response.OtpResponse;
import com.example.Product_APII.Entity.OTP;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Repository.UsersRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthenticationService {
    @Autowired
    private UsersRepository userRepository;

//    @Autowired
//    private Invalid_tokenRepository invalidTokenRepository;

//    private long expiryTime = 5;

    private String baseUrl = "http://localhost:8080";


    @NonFinal
//    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY = "f!92SN9R8KmbvWrR!VOFhL8u?RoFAAu9awbCuu=qwEIdpjRiD-L0Qxh-20gACItM";

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;


    @Autowired
    Cache<String, OTP> otpCache;

    @Autowired
    Cache<String, String> resetPasswordCache;

//    @Autowired
//    private EmailService emailService;  // Tiêm dịch vụ gửi email vào


//    public OtpResponse login(AuthenticationRequest request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
//        if (!authenticated) {
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
//        }
//
//        // Generate OTP
//        String otp = generateOtp();
//
//        // Save OTP to cache
//        Otp otpdb = Otp.builder()
//                .otpCode(otp)
//                .email(request.getEmail())
//                .expiryTime(LocalDateTime.now().plusMinutes(5))
//                .attempts(0)
//                .build();
//        otpCache.put(user.getEmail(), otpdb);
//
//        // Send OTP via email
//        emailService.sendOtpEmail(user.getEmail(), otp);
//
//        // Return response
//        return OtpResponse.builder()
//                .otp(otp) // Có thể bỏ nếu bạn không muốn trả OTP về response.
//                .email(request.getEmail())
//                .build();
//    }

    public OtpResponse login(AuthenticationRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        String otp = generateOtp();
        OTP otpdb = OTP.builder()
                .otpCode(otp)
                .email(request.getEmail())
                .expiryTime(LocalDateTime.now().plusMinutes(5))
                .attempts(0)
                .build();
        otpCache.put(user.getEmail(), otpdb);
        return OtpResponse.builder()
                .otp(otp)
                .email(request.getEmail())
                .build();
    }

    public AuthenticationResponse verifyOTP(OtpRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        OTP otp = otpCache.getIfPresent(request.getEmail());
        if (otp == null) {
            throw new AppException(ErrorCode.INVALID_OTP);
        }
        if (!otp.getOtpCode().equals(request.getOtp())) {
            otp.setAttempts(otp.getAttempts() + 1);
            if (otp.getAttempts() >= 5) {
                otpCache.invalidate(otp.getEmail());
                throw new AppException(ErrorCode.OTP_EXCEEDED_ATTEMPTS);
            }
            throw new AppException(ErrorCode.INVALID_OTP);
        }
        if (otp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
        otpCache.invalidate(request.getEmail());
        String token = generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

//    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
//        var signedJWT = verifyToken(request.getToken(), true);
//        System.out.println("JWT Claims: " + signedJWT.getJWTClaimsSet().toJSONObject());
//        var jit = signedJWT.getJWTClaimsSet().getJWTID();
//        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
//
//        Invalid_token invalidatedToken =
//                Invalid_token.builder().id(jit).expiryTime(expiryTime).build();
//
//        invalidTokenRepository.save(invalidatedToken);
//
//        String email = signedJWT.getJWTClaimsSet().getSubject();
//        System.out.println("Extracted email: " + email);
//        User user =
//                userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
//
//        String token = generateToken(user);
//
//        return AuthenticationResponse.builder().token(token).build();
//    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                .getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

//        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
//        if (expiryTime == null || !expiryTime.after(new Date())) {
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
//        }
        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

//        if (invalidTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
        return signedJWT;
    }

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("huyne")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("Role", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateOtp() {
        int otp = ThreadLocalRandom.current().nextInt(100000, 1000000); // OTP 6 chữ số
        return String.valueOf(otp);
    }

//    public String ResetPassword(ForgetPasswordRequest forgetPasswordRequest) {
//        String cachedToken = resetPasswordCache.getIfPresent(forgetPasswordRequest.getEmail());
//        if (cachedToken == null || !cachedToken.equals(forgetPasswordRequest.getToken())) {
//            throw new AppException(ErrorCode.INVALID_TOKEN);
//        }
//        if (!forgetPasswordRequest.getPassword().equals(forgetPasswordRequest.getPasswordAgaint())) {
//            throw new AppException(ErrorCode.PASSWORDS_DO_NOT_MATCH);
//        }
//        User user = userRepository.findByEmail(forgetPasswordRequest.getEmail())
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(passwordEncoder.encode(forgetPasswordRequest.getPassword()));
//        userRepository.save(user);
//        return "Đổi mật khẩu thành công";
//    }
//
//    public LinkResetResponse LinkForgetPassword(EmailRequest emailRequest) {
//        User newUser = userRepository.findByEmail(emailRequest.getEmail()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
//        String token = UUID.randomUUID().toString();
//        if (!newUser.getEmail().equals(emailRequest.getEmail())) {
//            throw new AppException(ErrorCode.CANNOT_RESET_PASSWORD);
//        }
//        resetPasswordCache.put(emailRequest.getEmail(), token);
//        String resetLink = baseUrl + "/reset-password?token=" + resetPasswordCache.getIfPresent(emailRequest.getEmail());
//        System.out.println("Cached OTP: " + resetPasswordCache.getIfPresent(emailRequest.getEmail()));
//        return LinkResetResponse.builder().link(resetLink).build();
//    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getRoleName());
                });

        return stringJoiner.toString();
    }
}
