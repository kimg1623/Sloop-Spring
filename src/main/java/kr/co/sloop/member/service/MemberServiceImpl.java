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
        // 유효성 검사 진행 ----------
        if (memberDTO.getMemberSubjectCode() == null){
            return -1;
        } else {
            return memberRepository.signup(memberDTO);
        }
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

    @Override
    public MemberDTO findByMemberEmail(String loginEmail) {
        return memberRepository.findByMemberEmail(loginEmail);
    }

    @Override
    public boolean update(MemberDTO memberDTO) {
        int result = memberRepository.update(memberDTO);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MemberDTO findByIdx(int memberIdx) {
        return memberRepository.findByIdx(memberIdx);
    }

    @Override
    public int deleteMember(String memberPassword) {
        return memberRepository.deleteMember(memberPassword);
    }

    @Override
    public int deleteByUser(int memberIdx) {
        return memberRepository.deleteById(memberIdx);
    }


}
