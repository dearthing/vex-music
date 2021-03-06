package cn.dearth.vexmusic.core.constant;

/**
 * @author dearth
 */
public class AuthenticationConfigConstants {

    public static final String SECRET = "VexMusic";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/";
    public static final String CREATE_TOKEN_URL = "/tokens/**";

}
