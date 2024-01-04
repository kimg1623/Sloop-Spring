package kr.co.sloop.member.repository;

import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.repository.impl.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j
public class MemberRepositoryImpl implements MemberRepository {

    private final SqlSessionTemplate sql;

    @Override
    public int signup(MemberDTO memberDTO){
        return sql.insert("Member.signup",memberDTO);
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login", memberDTO);
    }

    @Override
    public MemberDTO findByMemberEmail(String loginEmail) {
        return sql.selectOne("Member.findByMemberEmail", loginEmail);
    }

    @Override
    public List<MemberDTO> findMemberList(Model model) {
        return sql.selectList("Member.findMemberList",model);
    }

    @Override
    public MemberDTO findByMemberNickname(String memberNickname) {
        return sql.selectOne("Member.findByMemberNickname",memberNickname);
    }

    @Override
    public int update(MemberDTO memberDTO) {
        return sql.update("Member.update", memberDTO);
    }

    @Override
    public MemberDTO findByIdx(int memberIdx) {
        return sql.selectOne("Member.findByIdx" , memberIdx);
    }

    @Override
    public int deleteById(int memberIdx) {
        return sql.delete("Member.deleteByUser",memberIdx);
    }




}
