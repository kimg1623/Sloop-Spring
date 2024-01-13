package kr.co.sloop.member.repository.impl;

import kr.co.sloop.member.domain.AttachmentMemberDTO;
import kr.co.sloop.member.domain.MemberDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberRepository {
    int signup(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    MemberDTO findByMemberEmail(String loginEmail);

    List<MemberDTO> findMemberList(Model model);

    MemberDTO findByMemberNickname(String memberNickname);

    int update(MemberDTO memberDTO);

    MemberDTO findByIdx(int memberIdx);


    int deleteByUser(int memberIdx);

     AttachmentMemberDTO findImageByMemberIdx(int memberIdx);

    void uploadProfile(List<MultipartFile> multipartFile);






    /*int signup(RegisterFormDTO registerFormDTO);
*/
}
