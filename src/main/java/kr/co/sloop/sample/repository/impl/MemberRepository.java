package kr.co.sloop.sample.repository.impl;

import kr.co.sloop.sample.domain.MemberDTO;

public interface MemberRepository {
    int signup(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    MemberDTO findByMemberEmail(String memberEmail);
}
