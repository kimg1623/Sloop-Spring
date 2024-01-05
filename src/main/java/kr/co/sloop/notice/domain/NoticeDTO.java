package kr.co.sloop.notice.domain;

import lombok.Data;

@Data
public class NoticeDTO {

    /*** 테이블 변수***/
    private Integer postIdx;					/*게시판 키*/
    private Integer prevPostIdx;				/*이전글 게시판 키*/
    private Integer nextPostIdx;				/*다음글 게시판 키*/
    private String 	postNoticeTitle;			/*게시판 제목*/
    private String 	postNoticeContents;			/*게시판 내용*/
    private Integer memberIdx;					/*회원 키*/
    private String 	postNoticeRegDate;			/*등록일*/
    private String 	postNoticeEditDate;			/*수정일*/
    private Integer postNoticeHits;				/*조회수*/
    private Integer categoryPostIdx;			/*카테고리 키*/
    private String 	postNoticePinned;			/*상단 고정여부*/
    private String  categoryPostName;			/*카테고리 이름*/

    /*** 페이징 관련 ***/
    /** 현재페이지 */
    private int page = 1;
    /** 페이지당 출력 개수 */
    private int row = 10;
    //mySql용 변수
    private int startIndex = 0;
    /*검색조건*/
    private String searchType;
    /*검색 값*/
    private String searchTxt;
	
	
	/*       
		LIMIT #{startIndex}, #{row}
		0, 10
		10, 10
		20, 10
		30, 10
		이런식으로 증가
	 */
}
