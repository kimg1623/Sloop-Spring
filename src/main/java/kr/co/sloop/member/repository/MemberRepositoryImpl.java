package kr.co.sloop.member.repository;

import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.repository.impl.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

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
    public MemberDTO findByMemberEmail(String memberEmail) {
        return sql.selectOne("Member.findByMemberEmail", memberEmail);
    }

    @Override
    public List<MemberDTO> findMemberList(Model model) {
        return sql.selectList("Member.findMemberList",model);
    }

    @Override
    public MemberDTO findByMemberNickname(String memberNickname) {
        return sql.selectOne("Member.findByMemberNickname",memberNickname);
    }
}
