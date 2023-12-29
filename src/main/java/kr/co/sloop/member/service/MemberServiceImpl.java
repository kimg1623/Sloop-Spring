package kr.co.sloop.member.service;

import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.repository.impl.MemberRepository;
import kr.co.sloop.member.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public int signup(MemberDTO memberDTO){
        log.info("service memberDTO ==== " +memberDTO);

        /*String[] cho = {"초등학생 1학년","초등학생 2학년","초등학생 3학년","초등학생 4학년","초등학생 5학년","초등학생 6학년"};*/

        /*if ("초등학생".equals(memberDTO.getMemberGradeCode())){
            memberDTO.setMemberGradeCode("100");
        }*/
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
