package kr.co.sloop.member.service;

import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.repository.impl.MemberRepository;
import kr.co.sloop.member.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public int signup(MemberDTO memberDTO){
        log.info("service memberDTO ==== " +memberDTO);
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

    @Override
    public List<MemberDTO> findMemberList(Model model) {
        return memberRepository.findMemberList(model);
    }

    @Override
    public String nicknameCheck(String memberNickname) {
        MemberDTO memberDTO = memberRepository.findByMemberNickname(memberNickname);
        if (memberDTO == null ){
            return "ok";
        } else {
            return "no";
        }
    }
}
