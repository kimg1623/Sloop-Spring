package kr.co.sloop.member.service.impl;

import kr.co.sloop.member.domain.MemberDTO;

public interface MemberService {
    int signup(MemberDTO memberDTO);

    boolean login(MemberDTO memberDTO);

    String emailCheck(String memberEmail);
}
