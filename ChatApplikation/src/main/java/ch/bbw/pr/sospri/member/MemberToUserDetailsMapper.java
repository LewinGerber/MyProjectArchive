package ch.bbw.pr.sospri.member;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Lewin Gerber
 * @version 31.03.2021
 * 50_02_SpringBootChatAppSoSpriV01
 */
public class MemberToUserDetailsMapper {
    static public UserDetails toUserDetails(Member member) {
        User user = null;
        if(member != null) {
            System.out.println("MemberToUserDetailsMapper.toUserDetails(): member: " + member);
            Collection<MemberGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new MemberGrantedAuthority(member.getAuthority()));
            user = new User(
                (member.getPrename() + "." + member.getLastname()).toLowerCase(),
                member.getPassword(),
                true,
                true,
                true,
                true,
                    authorities
            );
        }
        return user;
    }
}
