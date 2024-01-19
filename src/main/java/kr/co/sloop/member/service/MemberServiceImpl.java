package kr.co.sloop.member.service;

import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.repository.impl.MemberRepository;
import kr.co.sloop.member.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // 암호화를 위한 의존성 주입
    private final BCryptPasswordEncoder bcrypt;

    @Override
    public int signup(MemberDTO memberDTO){
        // 유효성 검사 진행 ----------
        try {
            if (memberDTO.getMemberSubjectCode() == null){
                return -1;
            } else {
                /* bcrypt 암호화 */
                String inputPass = memberDTO.getMemberPassword();
                String pwd = bcrypt.encode(inputPass);
                memberDTO.setMemberPassword(pwd);
                return memberRepository.signup(memberDTO);
            }
        } catch (DataIntegrityViolationException e){
            return -1;
        }
    }
    /** 로그인 bcrypt로 하는 메서드 */
    @Override
    public Map<String, String> login(MemberDTO memberDTO) {

        MemberDTO loginMember = memberRepository.login(memberDTO);
        if (loginMember == null){
            return null;
        } else {
            /* bcrypt 암호화된 pwd 와 입력받은 pwd 값 참,거짓 확인 */
            boolean pwdMatch = bcrypt.matches(memberDTO.getMemberPassword(), loginMember.getMemberPassword());
            log.info("pwdMatch" + pwdMatch);
            if (loginMember != null && pwdMatch == true){
                Map<String, String> loginSessionMap = new HashMap<String, String>();
                loginSessionMap.put("loginEmail", loginMember.getMemberEmail());
                loginSessionMap.put("loginMemberIdx", String.valueOf(loginMember.getMemberIdx())); // 지원 추가
                loginSessionMap.put("loginMemberNickname", loginMember.getMemberNickname());
                return loginSessionMap;
            } else {
                return null;
            }
        }
    }
    /** 로그인 bcrypt로 하는 메서드 */

    /** 로그인 bcrypt 없이 하는 메서드 */
    /*@Override
    public Map<String, String> login(MemberDTO memberDTO) {
        MemberDTO loginMember = memberRepository.login(memberDTO);
        if (loginMember != null){
            Map<String, String> loginSessionMap = new HashMap<String, String>();
            loginSessionMap.put("loginEmail", loginMember.getMemberEmail());
            loginSessionMap.put("loginMemberIdx", String.valueOf(loginMember.getMemberIdx())); // 지원 추가
            loginSessionMap.put("loginMemberNickname", loginMember.getMemberNickname());
            return loginSessionMap;
        } else {
            return null;
        }
    }*/
    /** 로그인 bcrypt 없이 하는 메서드 */




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
    public String emailCheck(String memberEmail) {
        MemberDTO memberDTO = memberRepository.emailCheck(memberEmail);
        log.info("findByMemberEmail aaaaa"+memberDTO);
        if (memberDTO == null) {
            return "ok";
        } else {
            return "no";
        }
    }
    /*@Override
    public String emailCheck(String memberEmail) {
        MemberDTO memberDTO = memberRepository.findByMemberEmail(memberEmail);
        if (memberDTO == null) {
            return "ok";
        } else {
            return "no";
        }
    }*/

    @Override
    public boolean update(MemberDTO memberDTO) {
        try {
            int result = memberRepository.update(memberDTO);
            boolean subResult = (memberDTO.getMemberSubjectCode() != null);

            if (subResult && result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (DataIntegrityViolationException e){
            return false;

        }
    }

    @Override
    public MemberDTO findByIdx(int memberIdx) {
        return memberRepository.findByIdx(memberIdx);
    }

    @Override
    public int deleteByUser(int memberIdx) {

        return memberRepository.deleteByUser(memberIdx);
    }

    @Override
    public void uploadProfile(MemberDTO memberDTO) {
        memberRepository.uploadProfile(memberDTO);
    }

    @Override
    public String phoneNumbCheck(String memberPhonenumber) {
        MemberDTO memberDTO = memberRepository.findByMemberPhoneNumb(memberPhonenumber);
        if (memberDTO == null) {
            return "ok";
        } else {
            return "no";
        }
    }

    @Override
    public List<MemberDTO> findStudyByIdx(String sessionIdx) {
        return memberRepository.findStudyByIdx(sessionIdx);
    }


/*    @Override
    public void uploadProfileByIdx(int memberIdx) {
        memberRepository.uploadProfileByIdx(memberIdx);
    }*/






    /*@Override
    public int signup(RegisterFormDTO registerFormDTO) {

        if (registerFormDTO.getMemberEmail() == null) {
            return -1;
        } else {
            return memberRepository.signup(registerFormDTO);
        }
    }*/


}
