package ch.bbw.pr.sospri.member;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Lewin Gerber
 * @version 31.03.2021
 * 50_02_SpringBootChatAppSoSpriV01
 */
public class MemberGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 8835903531623403993L;
    private String authority;

    public MemberGrantedAuthority(String authority) {
        super();
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "MemberGrantedAuthority [authority=" + authority + "]";
    }
}
