<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 헤더 임포트 -->
<jsp:include page="../include/header.jsp"></jsp:include>
<style>
	.text_box {position:relative; display:inline-block; width:100%;}
	.text_box textarea {width:100%; height:152px; color:#666; font-family:"ht_r"; font-size:18px; line-height:28px; padding:20px; border:1px solid #e4dcd3; outline:0; resize:none}
	.text_box .count {position:absolute; right:20px; bottom:20px; color:#666; font-family:"ht_r"; font-size:15px;}
</style>
<section>
	<div class="container px-5 my-5 px-5">
		<h2>글쓰기</h2>
		<form name="form" id="form" role="form" method="post" action="">
			<input type="hidden" name="postIdx" id="postIdx" value="${noticeDTO.postIdx}"/>
			<!-- 상단고정 selectbox -->
			<div class="mb-3">
				<label for="topFixed">상단고정</label>
				<select class="form-select" id="postNoticePinned" name="postNoticePinned">
					<option value="0" ${empty noticeDTO.postNoticePinned or noticeDTO.postNoticePinned == 0 ? 'selected' : ''}>사용안함</option>
					<option value="1" ${noticeDTO.postNoticePinned == 1 ? 'selected' : ''}>사용</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="postNoticeTitle" id="postNoticeTitle" maxlength="50" placeholder="제목을 입력해 주세요" value="${noticeDTO.postNoticeTitle}">
			</div>
			<!-- 카테고리 라디오 버튼 추가 -->
			<div class="mb-3">
				<label>카테고리</label>
				<div class="row">
					<div class="col-4">
						<div class="form-check">
							<input type="radio" class="form-check-input" name="categoryPostIdx" id="category1" value="1" ${empty noticeDTO.categoryPostIdx or noticeDTO.categoryPostIdx == 1 ? 'checked' : ''}>
							<label class="form-check-label" for="category1">일반</label>
						</div>
					</div>
					<div class="col-4">
						<div class="form-check">
							<input type="radio" class="form-check-input" name="categoryPostIdx" id="category2" value="2" ${noticeDTO.categoryPostIdx == 2 ? 'checked' : ''}>

							<label class="form-check-label" for="category2">중요</label>
						</div>
					</div>
					<div class="col-4">
						<div class="form-check">
							<input type="radio" class="form-check-input" name="categoryPostIdx" id="category3" value="2" ${noticeDTO.categoryPostIdx == 3 ? 'checked' : ''}>
							<label class="form-check-label" for="category3">이벤트</label>
						</div>
					</div>
				</div>
			</div>
			<div class="mb-3 text_box">
				<label for="content">내용</label>
				<textarea class="form-control" rows="5" name="postNoticeContents" id="postNoticeContents" placeholder="내용을 입력해 주세요" >${noticeDTO.postNoticeContents}</textarea>
				<div class="count"><span>0</span>/300</div>
			</div>
		</form>
		<div >
			<button type="button" class="btn btn-sm btn-success" id="writeBtn">저장</button>
			<button type="button" class="bn btn-sm btn-primary" onclick="javascript:location.href='/notice/list';">목록</button>
		</div>

		<div style='width:80px;float: right;'>
			<input type='button' class="btn btn-sm btn-primary" onclick="location.href='/'" name='btn2' value='홈'>

		</div>
</section>
<!-- 푸터 임포트 -->
<jsp:include page="../include/footer.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {

		$('.text_box .count span').html($('.text_box textarea').val().length || 0);

		//textArea 글자수 체크
		$('.text_box textarea').keyup(function(){
			var content = $(this).val();
			$('.text_box .count span').html(content.length);
			if (content.length > 300){
				alert("작성 글자수를 초과하였습니다.");
				$(this).val(content.substring(0, 300));
				$('.text_box .count span').html(300);
			}
		});

		//게시판 저장버튼
		$("#writeBtn").on("click", function(){
			let form = $("#form");
			//게시판번호가 있으면 기존수정모드 없으면 신규글쓰기모드.
			$('#postIdx').val() != "" ? form.attr("action", "/notice/updateNotice") : form.attr("action", "/notice/insertNotice")
			form.attr("method", "post");
			//게시판 유효성 체크
			if(formCheck()){
				form.submit();
			}
		});

	});

	//게시판 입력폼 체크
	let formCheck = function() {
		let form = document.getElementById("form");
		if(form.postNoticeTitle.value=="") {
			alert("제목을 입력해 주세요.");
			form.postNoticeTitle.focus();
			return false;
		}
		if(form.postNoticeContents.value=="") {
			alert("내용을 입력해 주세요.");
			form.postNoticeContents.focus();
			return false;
		}
		return true;
	}
</script>