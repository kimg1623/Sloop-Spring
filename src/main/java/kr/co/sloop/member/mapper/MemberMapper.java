package kr.co.sloop.member.mapper;

import kr.co.sloop.member.domain.AttachmentMemberDTO;
import kr.co.sloop.member.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface MemberMapper {

    int signup(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    MemberDTO findByMemberEmail(String loginEmail);

    List<MemberDTO> findMemberList(Model model);

    MemberDTO findByMemberNickname(String memberNickname);

    int update(MemberDTO memberDTO);

    MemberDTO findByIdx(int memberIdx);

    int deleteByUser(int memberIdx);

    AttachmentMemberDTO findImageByMemberIdx(int memberIdx);

    void uploadProfile(String savedFileName);




    /*LoginUserDTO.MemberVO adminLogin(MemberDTO memberDTO);

    LoginUserDTO.MemberVO memberLogin(MemberDTO memberDTO);

    int signup(RegisterFormDTO registerFormDTO);

    MemberDTO findByUserName(String memberEmail);*/
}
