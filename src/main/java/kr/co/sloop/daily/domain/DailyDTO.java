package kr.co.sloop.daily.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DailyDTO {

    private int postIdx;               //게시글 idx
    private String postDailyTitle;     //제목
    private String postDailyContents;  //내용
    private int memberIdx;             //작성자 회원 idx
    private  String postDailyRegDate;  //작성일시
    private String postDailyEditDate;  //수정일시
    private int postDailyHits;         //조회수

    private String memberNickname; //작성자 회원 닉네임
    private int boardIdx;          //게시판idx
}
