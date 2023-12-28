package kr.co.sloop.sample.repository;

import kr.co.sloop.sample.domain.MemberDTO;
import kr.co.sloop.sample.repository.impl.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final SqlSessionTemplate sql;

    @Override
    public int signup(MemberDTO memberDTO){
        return sql.insert("Member.signup",memberDTO);
    }
}
