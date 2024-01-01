package kr.co.sloop.postForum.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class PostForumDTO {
    private int postIdx; // 게시글 idx
    @NotEmpty(message = "제목을 입력해 주세요.")
    @Size(max = 100, message = "100자 이하의 제목을 입력해 주세요.")
    private String postForumTitle; // '제목'
    @NotEmpty(message = "내용을 입력해 주세요.")
    @Size(max = 65535, message = "65,535자 이하의 내용을 입력해 주세요.")
    private String postForumContents; // '내용'
    private int memberIdx; // '작성자 회원 idx'
    private Timestamp postForumRegDate; // '작성일시'
    private Timestamp postForumEditDate; // '수정일시'
    private int postForumHits; // '조회수'
    private int categoryPostIdx; // '카테고리 idx'

    private String memberEmail; // 작성자 회원 email (DB postForum 테이블에 속성 존재하지 않음)
    private int boardIdx; // 게시판 idx (DB postForum 테이블에 속성 존재하지 않음)
    private String memberNickname; // 작성자 회원 닉네임
    private String categoryPostName; // 카테고리 설명 (카테고리 idx에 대응됨)
}
