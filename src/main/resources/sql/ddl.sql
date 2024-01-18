-- DDL--
use sloop;

create table member(
                       memberIdx              int AUTO_INCREMENT  not null comment '회원 index',
                       memberEmail            varchar(50) unique  not null comment '회원 이메일',
                       memberPassword         varchar(100)        not null comment '회원 비밀번호',
                       memberNickname         varchar(30) unique  not null comment '회원 닉네임',
                       memberGender		       varchar(30)			   not null comment '회원 성별',
                       memberRegdate          date DEFAULT now()           comment '회원가입 날짜',
                       memberPhonenumber      varchar(30) unique  not null comment '회원 핸드폰번호',
                       memberSchool           varchar(30)         not null comment '회원 학교',
                       memberGradeCode        varchar(30)         not null comment '학년 카테고리 코드',
                       memberSubjectCode      varchar(30)         not null comment '과목 카테고리 코드',
                       memberRegionCode       varchar(30)         not null comment '지역 카테고리 코드',
                       authority			         varchar(30)		default 'ROLE_MEMBER' comment '회원 권한',
                       memberProfile		       varchar(100) 			     null comment '멤버 프로필',
                       PRIMARY KEY (memberIdx)
);

create table categoryGrade (
                               categoryGradeTier tinyint not null comment '분류 단계',
                               categoryGradeName varchar(20) not null comment '학교학년 코드 이름',
                               categoryGradeCode varchar(5) not null comment '학교학년 코드',
                               categoryGradeParent varchar(5) comment '상위 단계의 카테고리 코드',
                               primary key (categoryGradeCode)
);

create table categorySubject (
                                 categorySubjectTier tinyint not null comment '분류 단계',
                                 categorySubjectName varchar(20) not null comment '과목 코드 이름',
                                 categorySubjectCode varchar(5) not null comment '과목 코드',
                                 categorySubjectParent varchar(5) comment '상위 단계의 카테고리 코드',
                                 primary key (categorySubjectCode)
);

create table categoryRegion (
                                categoryRegionTier tinyint not null comment '분류 단계',
                                categoryRegionName varchar(20) not null comment '지역 코드 이름',
                                categoryRegionCode varchar(5) not null comment '지역 코드',
                                categoryRegionParent varchar(5) comment '상위 단계의 카테고리 코드',
                                primary key (categoryRegionCode)
);

create table studyGroup(
                           studyGroupIdx			int	auto_increment	not null comment '스터디그룹 index',
                           studyGroupCode		varchar(20)			not null comment '스터디그룹 코드 랜덤값',
                           studyGroupName		varchar(20)			not null comment '스터디그룹 이름',
                           studyGroupMethod		tinyint				not null comment '스터디그룹 방식(1:온,2:오프,3:동시)',
                           studyGroupGradeCode	varchar(5)				not null comment '학년 카테고리 코드',
                           studyGroupSubjectCode	varchar(5)				not null comment '과목 카테고리 코드',
                           studyGroupRegionCode	varchar(5)				not null comment '지역 카테고리 코드',
                           studyGroupNum			tinyint	default 2	not null comment '모집인원',
                           studyGroupDuedate		date				not null comment '모집 마감일',
                           studyGroupStartdate	date				not null comment '스터디 시작일',
                           studyGroupIntrotxt	varchar(500)				 comment '소개글',
                           studyGroupOImg		varchar(50) 				 comment '스터디 이미지 원본',
                           studyGroupSImg		varchar(50)					 comment '스터디 이미지 저장본',
                           studyGroupHits		int	default 0				 comment '조회수',
                           primary key (studyGroupIdx),
                           FOREIGN KEY (studyGroupGradeCode) REFERENCES categoryGrade(categoryGradeCode) on update CASCADE on delete cascade,
                           FOREIGN KEY (studyGroupSubjectCode) REFERENCES categorySubject(categorySubjectCode) on update CASCADE on delete cascade,
                           FOREIGN KEY (studyGroupRegionCode) REFERENCES categoryRegion(categoryRegionCode) on update CASCADE on delete cascade
);

create table studyMem (
                          studyGroupIdx		int		not null comment '스터디그룹 index',
                          memberIdx		int		not null comment '회원 index',
                          studyMemRole	varchar(20) not null comment '스터디 그룹 내 권한',
                          FOREIGN KEY (studyGroupIdx) REFERENCES studyGroup(studyGroupIdx) on update CASCADE on delete cascade,
                          FOREIGN KEY (memberIdx) REFERENCES member(memberIdx) on update CASCADE on delete cascade
);

create table categoryBoard(
                              categoryBoardIdx int not null AUTO_INCREMENT comment '게시판 유형 idx',
                              categoryBoard varchar(20) not null comment '게시판 설명',
                              PRIMARY KEY (categoryBoardIdx)
);

create table board(
                      boardIdx int not null AUTO_INCREMENT COMMENT '게시판 idx',
                      studyGroupIdx int not null comment '스터디 그룹 idx',
                      categoryBoardIdx int null comment '게시판 유형 idx',
                      PRIMARY KEY (boardIdx),
                      Foreign Key (studyGroupIdx) REFERENCES studyGroup(studyGroupIdx),
                      Foreign Key (categoryBoardIdx) REFERENCES categoryBoard(categoryBoardIdx)
);


create table post(
                     postIdx int not null AUTO_INCREMENT comment '게시글 idx',
                     boardIdx int not null comment '게시판 idx',
                     PRIMARY KEY (postIdx),
                     Foreign Key (boardIdx) REFERENCES board (boardIdx) on update CASCADE on delete CASCADE
);

create table categoryPost(
                             categoryPostIdx int not null AUTO_INCREMENT COMMENT '게시글 카테고리 idx',
                             categoryPostName varchar(40) not null COMMENT '게시글 카테고리 이름',
                             categoryBoardIdx int not null comment '게시판 유형 idx',
                             PRIMARY KEY (categoryPostIdx),
                             Foreign Key (categoryBoardIdx) REFERENCES categoryBoard(categoryBoardIdx)
);

create table postNotice(
                           postIdx int not null comment '공지게시글 idx',
                           postNoticeTitle varchar(100) not null comment '제목',
                           postNoticeContents text not null comment '내용',
                           memberIdx int not null comment '작성자 회원 idx',
                           postNoticeRegDate DATETIME DEFAULT now() comment '작성일시',
                           postNoticeEditDate DATETIME null comment '수정일시',
                           postNoticeHits int default 0 comment '조회수',
                           categoryPostIdx int null comment '카테고리 idx',
                           postNoticePinned TINYINT DEFAULT 0 comment '상단 고정 여부',
                           PRIMARY KEY (postIdx),
                           Foreign Key (postIdx) REFERENCES post(postIdx) on update CASCADE on delete CASCADE,
                           Foreign Key (memberIdx) REFERENCES member(memberIdx) on update CASCADE on delete CASCADE,
                           Foreign Key (categoryPostIdx) REFERENCES categoryPost(categoryPostIdx) on update CASCADE on delete CASCADE
);

create table assignment(
                           assignmentIdx int not null AUTO_INCREMENT comment '과제 idx',
                           assignmentBeginDate datetime comment '시작일시',
                           assignmentEndDate datetime comment '마감일시',
                           assignmentScore TINYINT UNSIGNED comment '만점 점수',
                           PRIMARY KEY (assignmentIdx)
);

create table postAssignment(
                               postIdx int not null comment '게시글 idx',
                               postAssignmentTitle VARCHAR(100) null COMMENT '제목',
                               postAssignmentContents text not null comment '내용',
                               memberIdx int not null comment '작성자 회원 idx',
                               postAssignmentRegDate DATETIME DEFAULT now() comment '작성일시',
                               postAssignmentEditDate DATETIME null comment '수정일시',
                               postAssignmentHits int default 0 comment '조회수',
                               assignmentIdx int not null comment '과제 idx',
                               postAssignmentGroup int comment '그룹 번호',
                               postAssignmentGroupOrder int default 0 comment '그룹 내 순서',
                               postAssignmentGroupDepth int default 0 comment '깊이',
                               PRIMARY KEY (postIdx),
                               Foreign Key (postIdx) REFERENCES post(postIdx) on update CASCADE on delete CASCADE,
                               Foreign Key (memberIdx) REFERENCES member(memberIdx) on update CASCADE on delete CASCADE,
                               Foreign Key (assignmentIdx) REFERENCES assignment(assignmentIdx) on update CASCADE on delete CASCADE
);

create table assignmentMember(
                                 assignmentIdx int not null comment '과제 idx',
                                 memberIdx int not null comment '회원 idx',
                                 assignmentMemberScore TINYINT UNSIGNED null comment '회원의 과제 점수',
                                 Foreign Key (assignmentIdx) REFERENCES assignment(assignmentIdx),
                                 Foreign Key (memberIdx) REFERENCES member(memberIdx),
                                 PRIMARY KEY (assignmentIdx, memberIdx)
);

create table postForum(
                          postIdx int not null comment '게시글 idx',
                          postForumTitle VARCHAR(100) null COMMENT '제목',
                          postForumContents text not null comment '내용',
                          memberIdx int not null comment '작성자 회원 idx',
                          postForumRegDate DATETIME DEFAULT now() comment '작성일시',
                          postForumEditDate DATETIME null comment '수정일시',
                          postForumHits int default 0 comment '조회수',
                          categoryPostIdx int null comment '카테고리 idx',
                          PRIMARY KEY (postIdx),
                          Foreign Key (postIdx) REFERENCES post(postIdx) on update CASCADE on delete CASCADE,
                          Foreign Key (memberIdx) REFERENCES member(memberIdx) on update CASCADE on delete CASCADE,
                          Foreign Key (categoryPostIdx) REFERENCES categoryPost(categoryPostIdx) on update CASCADE on delete CASCADE
);

create table postDaily(
                          postIdx int not null comment '게시글 idx',
                          postDailyTitle VARCHAR(100) null COMMENT '제목',
                          postDailyContents text not null comment '내용',
                          memberIdx int not null comment '작성자 회원 idx',
                          postDailyRegDate DATETIME DEFAULT now() comment '작성일시',
                          postDailyEditDate DATETIME null comment '수정일시',
                          postDailyHits int default 0 comment '조회수',
                          PRIMARY KEY (postIdx),
                          Foreign Key (postIdx) REFERENCES post(postIdx) on update CASCADE on delete CASCADE,
                          Foreign Key (memberIdx) REFERENCES member(memberIdx) on update CASCADE on delete CASCADE
);

create table viewHistory(
                            postIdx int not null comment '게시글 idx',
                            memberIdx int not null comment '작성자 회원 idx',
                            Foreign Key (postIdx) REFERENCES post(postIdx) on update CASCADE on delete CASCADE,
                            Foreign Key (memberIdx) REFERENCES member(memberIdx) on update CASCADE on delete CASCADE,
                            PRIMARY KEY (postIdx, memberIdx)
);

create table reply(
                      replyIdx int not null AUTO_INCREMENT comment '댓글 idx',
                      postIdx int not null comment '게시글 idx',
                      replyContents VARCHAR(200) not null comment '내용',
                      memberIdx int not null comment '작성자 회원 idx',
                      replyRegDate datetime default now() comment '작성일시',
                      replyEditDate datetime null comment '수정일시',
                      replyGroup int default 0 comment '그룹 번호',
                      replyGroupOrder int comment '그룹 내 순서',
                      replyGroupDepth int comment '깊이',
                      PRIMARY KEY (replyIdx),
                      Foreign Key (postIdx) REFERENCES post(postIdx) on update CASCADE on delete CASCADE,
                      Foreign Key (memberIdx) REFERENCES member(memberIdx) on update CASCADE on delete CASCADE
);

create table attachment(
                           attachmentIdx int not null AUTO_INCREMENT comment '첨부파일 idx',
                           postIdx int comment '게시글 idx',
                           attachmentOrgName varchar(100) not null comment '원래 파일명',
                           attachmentDirPath varchar(100) not null comment '저장 디렉토리 경로',
                           attachmentName varchar(100) not null comment '파일명',
                           attachmentThumbnail VARCHAR(102) null comment '썸네일 이미지 파일명',
                           PRIMARY KEY(attachmentIdx),
                           Foreign Key (postIdx) REFERENCES post(postIdx) on update CASCADE on delete CASCADE
);

create table likes(
                      postIdx int not null comment '게시글 idx',
                      memberIdx int not null comment '회원 idx',
                      Foreign Key (postIdx) REFERENCES post(postIdx) on update CASCADE on delete CASCADE,
                      Foreign Key (memberIdx) REFERENCES member(memberIdx) on update CASCADE on delete CASCADE,
                      PRIMARY KEY(postIdx, memberIdx)
);



-- drop --
/*
drop table if exists likes;
drop table if exists attachment;
drop table if exists reply;
drop table if exists viewHistory;
drop table if exists postDaily;
drop table if exists postForum;
drop table if exists assignmentMember;
drop table if exists postAssignment;
drop table if exists assignment;
drop table if exists postNotice;
drop table if exists categoryPost;
drop table if exists post;
drop table if exists board;
drop table if exists categoryBoard;

drop table if exists studyMem;
drop table if exists studyGroup;
drop table if exists categoryRegion;
drop table if exists categorySubject;
drop table if exists categoryGrade;
drop table if exists member;
*/