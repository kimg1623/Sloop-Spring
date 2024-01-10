package kr.co.sloop.member.service.impl;


import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.security.RegisterFormDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberService {
    /*int signup(MemberDTO memberDTO);*/

/*
    boolean login(MemberDTO memberDTO);
*/



    List<MemberDTO> findMemberList(Model model);

    String nicknameCheck(String memberNickname);

    MemberDTO findByMemberEmail(String loginEmail);

    String emailCheck(String memberEmail);

    boolean update(MemberDTO memberDTO);

    MemberDTO findByIdx(int memberIdx);

    int deleteByUser(int memberIdx);


    int signup(RegisterFormDTO registerFormDTO);
}
