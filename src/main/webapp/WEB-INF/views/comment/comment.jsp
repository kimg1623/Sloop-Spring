<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>댓글 페이지</title>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
	<style>
		.comment-info {
			display: flex;
			align-items: center;
			margin-bottom: 8px;
		}

		.comment-info p {
			margin: 0;
			margin-right: 16px;
		}
	</style>
	<script>
		// 댓글 작성
		function saveComment() {
			// 입력된 댓글 내용 가져오기
			var replyContents = $("#replyContents").val();
			// 입력된 댓글 내용이 비어있는 경우, 경고창 표시 후 함수 종료
			if (replyContents.trim() === "") {
				alert("댓글 내용을 입력해주세요.");
				return;
			}

			// 서버로 댓글 데이터 전송
			$.ajax({
				url: "/comments",
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({
					replyContents: replyContents
				}),
				success: function() {
					// 댓글 작성 후, 페이지 새로고침
					location.reload();
				},
				error: function() {
					alert("댓글 작성에 실패했습니다.");
				}
			});
		}

		// 작성 취소
		function cancelComment() {
			$("#replyContents").val(""); // 댓글 내용 입력 필드 초기화
			$("#replyContents").attr("placeholder", "댓글을 입력해주세요."); // placeholder 수정
		}
	</script>
</head>
<body>
<h1>댓글 페이지</h1>

<!-- 댓글 입력 폼 -->
<form onsubmit="event.preventDefault(); saveComment();">
	<textarea id="replyContents" rows="3" cols="50" placeholder="댓글을 입력해주세요."></textarea>
	<button type="submit">댓글 작성</button>
	<button type="button" onclick="cancelComment();">작성 취소</button>
</form>

<!-- 댓글 목록 -->
<c:forEach var="comment" items="${comments}">
	<div>
		<!-- 댓글 내용 -->
		<p>${comment.replyContents}</p>

		<!-- 댓글 수정 폼 -->
		<form id="updateForm_${comment.replyIdx}" onsubmit="event.preventDefault(); updateComment(${comment.replyIdx});" style="display: none;">
			<textarea id="replyContents_${comment.replyIdx}" rows="3" cols="50">${comment.replyContents}</textarea>
			<button type="submit">수정 완료</button>
		</form>

		<!-- 댓글 수정 버튼 -->
		<button onclick="$('#updateForm_${comment.replyIdx}').toggle();">수정</button>

		<!-- 댓글 삭제 버튼 -->
		<button onclick="deleteComment(${comment.replyIdx});">삭제</button>

		<!-- 댓글 정보 -->
		<div class="comment-info">
			<!-- 작성자 회원 ID -->
			<p>작성자 회원 ID: ${comment.memberIdx}</p>

			<!-- 작성 일시 -->
			<p>작성 일시: ${comment.replyRegDate}</p>

			<!-- 수정 일시 -->
			<p>수정 일시: ${comment.replyEditDate}</p>
		</div>

		<!-- 댓글의 원 댓글의 ID -->
		<p>원 댓글 ID: ${comment.replyGroup}</p>

		<!-- 댓글의 댓글 내 순서 -->
		<p>댓글 순서: ${comment.replyGroupOrder}</p>

		<!-- 댓글 들여쓰기 -->
		<p>댓글 들여쓰기: ${comment.replyGroupDepth}</p>
	</div>
</c:forEach>
</body>
</html>