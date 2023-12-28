package kr.co.sloop.sample.service;

import kr.co.sloop.sample.domain.MemberDTO;
import kr.co.sloop.sample.repository.impl.MemberRepository;
import kr.co.sloop.sample.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public int signup(MemberDTO memberDTO){
        return memberRepository.signup(memberDTO);
    }

    @Override
    public boolean login(MemberDTO memberDTO) {
        MemberDTO loginMember = memberRepository.login(memberDTO);
        if (loginMember != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String emailCheck(String memberEmail) {
        MemberDTO memberDTO = memberRepository.findByMemberEmail(memberEmail);
        if (memberDTO == null) {
            return "ok";
        } else {
            return "no";
        }
    }
}
