package kr.co.sloop.member.service.impl;

import kr.co.sloop.member.domain.MemberDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface MemberService {
    int signup(MemberDTO memberDTO);

    boolean login(MemberDTO memberDTO);

    String emailCheck(String memberEmail);

    List<MemberDTO> findMemberList(Model model);

    String nicknameCheck(String memberNickname);
}
