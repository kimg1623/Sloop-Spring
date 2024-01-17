package kr.co.sloop.notice.domain;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data

public class NoticeDTO {
  private int postIdx;  // '공지게시글 idx'
  @NotEmpty(message = "제목을 입력해 주세요.")
  @Size(max = 100, message = "100자 이하의 제목을 입력해 주세요.")
  private String postNoticeTitle;  // '제목'

  @NotEmpty(message = "내용을 입력해 주세요.")
  @Size(max = 100, message = "100자 이하의 제목을 입력해 주세요.")
  private String postNoticeContents;  //  '내용'

  private int memberIdx;   // '작성자 회원 idx'
  private Timestamp postNoticeRegDate;  //  '작성일시'
  private Timestamp postNoticeEditDate;  // '수정일시'
  private int postNoticeHits;   // '조회수'

  @NotNull
  private int categoryPostIdx;   // '카테고리 idx'

  private int postNoticePinned;   // '상단 고정 여부'

  /** DB컬럼에 존재하지 않는 데이터 */

  private String memberEmail;
  private String memberNickname;
  private int boardIdx;
  private String categoryPostName;

}