-- 회원 member --
insert into member(memberIdx, memberEmail, memberPassword, memberNickname, memberGender, memberPhonenumber, memberSchool, memberGradeCode, memberSubjectCode, memberRegionCode)
values(1, "test", "test", "test1", "test", "test", "test", "test", "test", "test");
insert into member(memberIdx, memberEmail, memberPassword, memberNickname, memberGender, memberPhonenumber, memberSchool, memberGradeCode, memberSubjectCode, memberRegionCode)
values(2, "test2", "test2", "test2", "test2", "test2", "test2", "test2", "test2", "test2");

-- 학교학년 카테고리 categoryGrade --
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode) values (1, '초등', '100');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '초등1학년', '101', '100');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '초등2학년', '102', '100');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '초등3학년', '103', '100');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '초등4학년', '104', '100');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '초등5학년', '105', '100');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '초등6학년', '106', '100');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '초등전학년', '109', '100');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode) values (1, '중등', '200');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '중등1학년', '201', '200');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '중등2학년', '202', '200');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '중등3학년', '203', '200');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '중등전학년', '209', '200');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode) values (1, '고등', '300');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '고등1학년', '301', '200');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '고등2학년', '302', '200');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '고등3학년', '303', '200');
insert into categoryGrade(categoryGradeTier, categoryGradeName, categoryGradeCode, categoryGradeParent) values (2, '고등전학년', '304', '200');

-- 과목 카테고리 categorySubject --
insert into categorySubject(categorySubjectTier, categorySubjectName, categorySubjectCode) values (1, '학생', '100');
insert into categorySubject(categorySubjectTier, categorySubjectName, categorySubjectCode, categorySubjectParent) values (2, '국어', '101', '100');
insert into categorySubject(categorySubjectTier, categorySubjectName, categorySubjectCode, categorySubjectParent) values (2, '영어', '102', '100');
insert into categorySubject(categorySubjectTier, categorySubjectName, categorySubjectCode, categorySubjectParent) values (2, '수학', '103', '100');
insert into categorySubject(categorySubjectTier, categorySubjectName, categorySubjectCode, categorySubjectParent) values (2, '사회', '104', '100');
insert into categorySubject(categorySubjectTier, categorySubjectName, categorySubjectCode, categorySubjectParent) values (2, '과학', '105', '100');
insert into categorySubject(categorySubjectTier, categorySubjectName, categorySubjectCode, categorySubjectParent) values (2, '기타', '106', '100');

-- 지역 카테고리 categoryRegion  --
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'서울특별시','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'경기도','200');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'경상남도','300');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'경상북도','400');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'광주광역시','500');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'대구광역시','600');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'대전광역시','700');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'부산광역시','800');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'강원특별자치도','900');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'세종특별자치시','1000');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'울산광역시','1100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'인천광역시','1200');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'전라남도','1300');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'전라북도','1400');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'제주특별자치도','1500');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'충청남도','1600');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode) value (1,'충청북도','1700');

insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'강남구	','101','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'강동구	','102','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'강북구	','103','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'강서구	','104','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'관악구	','105','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'광진구	','106','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'구로구	','107','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'금천구	','108','100');
insert into categoryRegion (categoryRegionTier, categoryRegionName, categoryRegionCode, categoryRegionParent) value (2,'노원구	','109','100');


-- 스터디 그룹 studygroup --
insert into studyGroup(studyGroupCode, studyGroupName, studyGroupMethod, studyGroupGradeCode, studyGroupSubjectCode, studyGroupRegionCode, studyGroupNum, studyGroupDuedate, studyGroupStartdate)
values('A1B2C3', '스터디그룹 테스트1', 1, '101', '101', '101', 10, '2023-12-31', '2024-01-01');




-- 게시판 카테고리 categoryBoard --
insert into categoryBoard(categoryBoardIdx, categoryBoard) values(1, "공지 게시판");
insert into categoryBoard(categoryBoardIdx, categoryBoard) values(2, "과제 게시판");
insert into categoryBoard(categoryBoardIdx, categoryBoard) values(3, "자유 게시판");
insert into categoryBoard(categoryBoardIdx, categoryBoard) values(4, "공부 인증 게시판");

-- 게시판 board --
insert into board(boardIdx, studyGroupIdx, categoryBoardIdx) values(1, 1, 1); -- 공지 게시판 --
insert into board(boardIdx, studyGroupIdx, categoryBoardIdx) values(2, 1, 2); -- 과제 게시판 --
insert into board(boardIdx, studyGroupIdx, categoryBoardIdx) values(3, 1, 3); -- 자유 게시판 --
insert into board(boardIdx, studyGroupIdx, categoryBoardIdx) values(4, 1, 4); -- 공부 인증 게시판 --

-- 게시글의 카테고리  categoryPost --
insert into categoryPost(categoryPostIdx, categoryPostName, categoryBoardIdx) values(1, '일상', 3);
insert into categoryPost(categoryPostIdx, categoryPostName, categoryBoardIdx) values(2, "취미", 3);
insert into categoryPost(categoryPostIdx, categoryPostName, categoryBoardIdx) values(3, "고민", 3);
insert into categoryPost(categoryPostIdx, categoryPostName, categoryBoardIdx) values(4, "진로", 3);
insert into categoryPost(categoryPostIdx, categoryPostName, categoryBoardIdx) values(5, "일반", 1);
insert into categoryPost(categoryPostIdx, categoryPostName, categoryBoardIdx) values(6, "중요", 1);
insert into categoryPost(categoryPostIdx, categoryPostName, categoryBoardIdx) values(7, "이벤트", 1);