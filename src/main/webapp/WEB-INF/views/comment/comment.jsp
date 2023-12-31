<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>댓글 페이지</title>
	<style>
		/* 댓글 스타일링 */
		.comment-item {
			margin-bottom: 10px;
		}
		.comment-info {
			font-weight: bold;
			margin-bottom: 5px;
		}
		.comment-contents-textarea {
			width: 300px;
			height: 100px;
			margin-bottom: 5px;
		}
		.comment-buttons button {
			margin-right: 5px;
		}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		// 댓글 저장 함수
		function saveComment() {
			var commentWriter = $("#commentWriter").val();
			var commentContents = $("#commentContents").val();
			var boardId = $("#boardId").val();

			$.ajax({
				type: "POST",
				url: "/comment/save",
				data: {
					commentWriter: commentWriter,
					commentContents: commentContents,
					boardId: boardId
				},
				success: function (data) {
					// 댓글 저장 성공 시, 댓글 목록을 갱신하고 입력 필드 초기화
					refreshCommentList(data);
					$("#commentWriter").val("");
					$("#commentContents").val("");
				},
				error: function (xhr, status, error) {
					console.error(error);
				}
			});
		}

		// 댓글 수정 함수
		function updateComment(commentId) {
			var commentContents = $("#commentContents_" + commentId).val();

			$.ajax({
				type: "POST",
				url: "/comment/update",
				data: {
					id: commentId,
					commentContents: commentContents
				},
				success: function (data) {
					// 댓글 수정 성공 시, 해당 댓글 내용 갱신
					$("#commentContents_" + commentId).val(data.commentContents);
				},
				error: function (xhr, status, error) {
					console.error(error);
				}
			});
		}

		// 댓글 삭제 함수
		function deleteComment(commentId, boardId) {
			$.ajax({
				type: "POST",
				url: "/comment/delete",
				data: {
					commentId: commentId,
					boardId: boardId
				},
				success: function (data) {
					// 댓글 삭제 성공 시, 댓글 목록을 갱신
					refreshCommentList(data);
				},
				error: function (xhr, status, error) {
					console.error(error);
				}
			});
		}

		// 댓글 목록 갱신 함수
		function refreshCommentList(commentList) {
			var commentListHtml = "";

			$.each(commentList, function (index, comment) {
				commentListHtml += "<div class='comment-item'>";
				commentListHtml += "<div class='comment-info'>";
				commentListHtml += "<span class='comment-writer'>" + comment.commentWriter + "</span>";
				commentListHtml += "<span class='comment-created-time'>" + comment.commentCreatedTime + "</span>";
				commentListHtml += "</div>";
				commentListHtml += "<div class='comment-contents'>";
				commentListHtml += "<textarea id='commentContents_" + comment.id + "' class='comment-contents-textarea'>" + comment.commentContents + "</textarea>";
				commentListHtml += "</div>";
				commentListHtml += "<div class='comment-buttons'>";
				commentListHtml += "<button class='comment-update-button' onclick='updateComment(" + comment.id + ")'>수정</button>";
				commentListHtml += "<button class='comment-delete-button' onclick='deleteComment(" + comment.id + ", " + comment.boardId + ")'>삭제</button>";
				commentListHtml += "</div>";
				commentListHtml += "</div>";
			});

			$("#commentList").html(commentListHtml);
		}

		// 페이지 로드 시 초기 댓글 목록 요청
		$(document).ready(function () {
			$.ajax({
				type: "GET",
				url: "/comment/list",
				success: function (data) {
					refreshCommentList(data);
				},
				error: function (xhr, status, error) {
					console.error(error);
				}
			});
		});
	</script>
</head>
<body>
<h1>댓글 페이지</h1>
<input type="hidden" id="boardId" value="게시글 아이디">

<!-- 댓글 입력 폼 -->
<div id="commentForm">
	<input type="text" id="commentWriter" placeholder="작성자">
	<textarea id="commentContents" placeholder="댓글 내용"></textarea>
	<button onclick="saveComment()">댓글 작성</button>
</div>

<!-- 댓글 목록 -->
<div id="commentList">
	<!-- 댓글 목록이 여기에 동적으로 추가됨 -->
</div>
</body>
</html>