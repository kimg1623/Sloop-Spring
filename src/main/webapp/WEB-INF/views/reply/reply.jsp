<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>댓글 페이지</title>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
	<style>
		.reply-info {
			display: flex;
			align-items: center;
			margin-bottom: 8px;
		}

		.reply-info p {
			margin: 0;
			margin-right: 16px;
		}
	</style>
	<script>
		// 댓글 작성
		function saveReply() {
			// 입력된 댓글 내용 가져오기
			var postIdx = ${param.postIdx};
			var replyContents = $("#replyContents").val();
			// 입력된 댓글 내용이 비어있는 경우, 경고창 표시 후 함수 종료
			if (replyContents.trim() === "") {
				alert("댓글 내용을 입력해주세요.");
				return;
			}

			// 서버로 댓글 데이터 전송
			$.ajax({
				type: "POST",
				url: "/replies",
				data: {
					replyIdx: replyIdx,
					postIdx: postIdx,
					replyContents: replyContents,
					memberIdx: memberIdx,
					replyRegDate: replyRegDate,
					replyEditDate: replyEditDate,
					replyGroup: replyGroup,
					replyGroupOrder: replyGroupOrder,
					replyGroupDepth: replyGroupDepth
				},
				dataType: "json",

				success: function() {
					// 댓글 작성 후, 페이지 새로고침
					alert("댓글 작성에 성공했습니다.");
					location.reload();
				},
				error: function() {
					alert("댓글 작성에 실패했습니다.");
				}
			});
		}

		// 댓글 수정
		function updateReply(replyIdx) {
			// 수정할 댓글 내용 가져오기
			var replyContents = $("#replyContents_" + replyIdx).val();
			// 입력된 댓글 내용이 비어있는 경우, 경고창 표시 후 함수 종료
			if (replyContents.trim() === "") {
				alert("댓글 내용을 입력해주세요.");
				return;
			}

			// 서버로 수정된 댓글 데이터 전송
			$.ajax({
				url: "/replies",
				type: "PUT",
				contentType: "application/json",
				data: JSON.stringify({
					replyIdx: replyIdx,
					replyContents: replyContents
				}),
				success: function() {
					// 댓글 수정 후, 페이지 새로고침
					location.reload();
				},
				error: function() {
					alert("댓글 수정에 실패했습니다.");
				}
			});
		}

		// 댓글 삭제
		function deleteReply(replyIdx) {
			// 서버로 삭제할 댓글 데이터 전송
			$.ajax({
				url: "/replies/" + replyIdx,
				type: "DELETE",
				success: function() {
					// 댓글 삭제 후, 페이지 새로고침
					location.reload();
				},
				error: function() {
					alert("댓글 삭제에 실패했습니다.");
				}
			});
		}

		// 작성 취소
		function cancelReply() {
			$("#replyContents").val(""); // 댓글 내용 입력 필드 초기화
			$("#replyContents").attr("placeholder", "댓글을 입력해주세요."); // placeholder 수정
		}
	</script>
</head>
<body>
<h1>댓글 페이지</h1>

<!-- 댓글 입력 폼 -->
<form onsubmit="event.preventDefault(); saveReply();">
	<textarea id="replyContents" rows="3" cols="50" placeholder="댓글을 입력해주세요."></textarea>
	<button type="submit">댓글 작성</button>
	<button type="button" onclick="cancelReply();">작성 취소</button>
</form>

<!-- 댓글 목록 -->
<c:forEach var="reply" items="${replies}">
	<div>
		<!-- 댓글 내용 -->
		<p>${reply.replyContents}</p>

		<!-- 댓글 수정 폼 -->
		<form id="updateForm_${reply.replyIdx}" onsubmit="event.preventDefault(); updateReply(${reply.replyIdx});" style="display: none;">
			<textarea id="replyContents_${reply.replyIdx}" rows="3" cols="50">${reply.replyContents}</textarea>
			<button type="submit">수정 완료</button>
		</form>

		<!-- 댓글 수정 버튼 -->
		<button onclick="$('#updateForm_${reply.replyIdx}').toggle();">수정</button>

		<!-- 댓글 삭제 버튼 -->
		<button onclick="deleteReply(${reply.replyIdx});">삭제</button>

		<!-- 댓글 정보 -->
		<div class="reply-info">
			<!-- 작성자 회원 ID -->
			<p>작성자 회원 ID: ${reply.memberIdx}</p>

			<!-- 작성 일시 -->
			<p>작성 일시: ${reply.replyRegDate}</p>

			<!-- 수정 일시 -->
			<p>수정 일시: ${reply.replyEditDate}</p>
		</div>

		<!-- 댓글의 원 댓글의 ID -->
		<p>원 댓글 ID: ${reply.replyGroup}</p>

		<!-- 댓글의 댓글 내 순서 -->
		<p>댓글 순서: ${reply.replyGroupOrder}</p>

		<!-- 댓글 들여쓰기 -->
		<p>댓글 들여쓰기: ${reply.replyGroupDepth}</p>
	</div>
</c:forEach>
</body>
</html>