package kr.co.sloop.member.service.impl;


import kr.co.sloop.member.domain.MemberDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface MemberService {
    int signup(MemberDTO memberDTO);


    Map<String, String> login(MemberDTO memberDTO);


    List<MemberDTO> findMemberList(Model model);

    String nicknameCheck(String memberNickname);

    MemberDTO findByMemberEmail(String loginEmail);

    String emailCheck(String memberEmail);

    boolean update(MemberDTO memberDTO);

    MemberDTO findByIdx(int memberIdx);

    int deleteByUser(int memberIdx);

        void uploadProfile(MemberDTO memberDTO);

    String phoneNumbCheck(String memberPhonenumber);

    /*    void uploadProfileByIdx(int memberIdx);*/








    /* int signup(RegisterFormDTO registerFormDTO);*/
}
