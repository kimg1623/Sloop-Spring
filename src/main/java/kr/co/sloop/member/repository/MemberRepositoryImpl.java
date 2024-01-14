package kr.co.sloop.member.repository;


import kr.co.sloop.member.domain.AttachmentMemberDTO;
import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.mapper.MemberMapper;
import kr.co.sloop.member.repository.impl.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class MemberRepositoryImpl implements MemberRepository {

    /*private final SqlSessionTemplate sql;*/
    private final MemberMapper memberMapper;

    @Override
    public int signup(MemberDTO memberDTO){
        return memberMapper.signup(memberDTO);
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        return memberMapper.login(memberDTO);
    }

    @Override
    public MemberDTO findByMemberEmail(String loginEmail) {
        return memberMapper.findByMemberEmail(loginEmail);
    }

    @Override
    public List<MemberDTO> findMemberList(Model model) {
        return memberMapper.findMemberList(model);
    }

    @Override
    public MemberDTO findByMemberNickname(String memberNickname) {
        return memberMapper.findByMemberNickname(memberNickname);
    }

    @Override
    public int update(MemberDTO memberDTO) {
        return memberMapper.update(memberDTO);
    }

    @Override
    public MemberDTO findByIdx(int memberIdx) {
        return memberMapper.findByIdx(memberIdx);
    }

    @Override
    public int deleteByUser(int memberIdx) {
        return memberMapper.deleteByUser(memberIdx);
    }

    @Override
    public AttachmentMemberDTO findImageByMemberIdx(int memberIdx) {
        return memberMapper.findImageByMemberIdx(memberIdx);
    }


    @Override
    public void uploadProfile(MemberDTO memberDTO) {
        memberMapper.uploadProfile(memberDTO);
    }

    /*@Override
    public void uploadProfileByIdx(int memberIdx) {
        memberMapper.uploadProfileByIdx(memberIdx);
    }*/





    /*@Override
    public int signup(RegisterFormDTO registerFormDTO) {
        return memberMapper.signup(registerFormDTO);
    }*/




}
