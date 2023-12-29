package kr.co.sloop.member.repository.impl;

import kr.co.sloop.member.domain.MemberDTO;

public interface MemberRepository {
    int signup(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    MemberDTO findByMemberEmail(String memberEmail);
}
