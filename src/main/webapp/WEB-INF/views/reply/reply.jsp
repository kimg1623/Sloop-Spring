<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>댓글 페이지</title>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

	<script>
		// 댓글 작성
		const replyWrite = () => {
			const post = document.getElementById("postIdx").value;
			const writer = document.getElementById("memberIdx").value;
			const contents = document.getElementById("replyContents").value;

			// 입력된 댓글 내용이 비어있는 경우, 경고창 표시 후 함수 종료
			if (contents.trim() === "") {
				alert("댓글 내용을 입력해주세요.");
				return;
			}

			// 서버로 댓글 데이터 전송
			$.ajax({
				type: "POST",
				url: "/reply/save",
				data: {
					memberIdx: writer,
					replyContents: contents,
					postIdx: post
				},
				dataType: "json",
				success: function(replyList) {
					// 댓글 작성 후, 페이지 업데이트
					alert("댓글 작성에 성공했습니다.");
					displayReplyList(replyList); // 수정된 댓글 목록을 표시하는 함수 호출
					cancelReply(); // 입력 필드 초기화
				},
				error: function () {
					alert("댓글 작성에 실패했습니다.");
					console.log("작성 실패");
				}
			});
		}

		// 댓글 수정
		const update = (replyIdx, memberIdx, replyContents) => {
			// 댓글 수정 폼을 동적으로 생성하여 출력
			let form = "<form id='updateForm'>";
			form += "<input type='text' id='updateWriter' value='" + memberIdx + "'>";
			form += "<input type='text' id='updateContents' value='" + replyContents + "'>";
			form += "<button type='button' onclick='updateAction(" + replyIdx + ")'>수정 완료</button>";
			form += "</form>";
			document.getElementById('reply-list').innerHTML = form;
		}

		const updateAction = (replyIdx) => {
			// 수정된 댓글 정보를 가져와서 수정 작업 수행
			const writer = document.getElementById("updateWriter").value;
			const contents = document.getElementById("updateContents").value;
			const post = document.getElementById("postIdx").value;
			$.ajax({
				type: "post",
				url: "/reply/update",
				data: {
					replyIdx: replyIdx,
					memberIdx: writer,
					replyContents: contents,
					postIdx: post
				},
				dataType: "json",
				success: function(replyList) {
					console.log("수정 성공");
					console.log(replyList);
					displayReplyList(replyList); // 수정된 댓글 목록을 표시하는 함수 호출
				},
				error: function() {
					console.log("수정 실패");
				}
			});
		}

		const deleteReply = (replyIdx, postIdx) => {
			// 댓글 삭제 작업을 수행
			$.ajax({
				type: "post",
				url: "/reply/delete",
				data: {
					replyIdx: replyIdx,
					postIdx: postIdx
				},
				dataType: "json",
				success: function(replyList) {
					console.log("삭제 성공");
					console.log(replyList);
					displayReplyList(replyList); // 삭제된 댓글 목록을 표시하는 함수 호출
				},
				error: function() {
					console.log("삭제 실패");
				}
			});
		}

		// 댓글 목록을 표시하는 함수
		const displayReplyList = (replyList) => {
			let output = "<table>";
			output += "<tr><th>댓글번호</th>";
			output += "<th>작성자</th>";
			output += "<th>내용</th>";
			output += "<th>작성시간</th>";
			output += "<th>수정</th>";
			output += "<th>삭제</th></tr>";
			for (let i in replyList){
				output += "<tr>";
				output += "<td>" + replyList[i].replyIdx + "</td>";
				output += "<td>" + replyList[i].memberIdx + "</td>";
				output += "<td>" + replyList[i].replyContents + "</td>";
				output += "<td>" + replyList[i].replyRegDate + "</td>";
				output += "<td><button onclick=\"update(" + replyList[i].replyIdx + ", '" + replyList[i].memberIdx + "', '" + replyList[i].replyContents + "')\">수정</button></td>";
				output += "<td><button onclick=\"deleteReply(" + replyList[i].replyIdx + ", " + replyList[i].postIdx + ")\">삭제</button></td>";
				output += "</tr>";
			}
			output += "</table>";
			document.getElementById('reply-list').innerHTML = output;
		}

		// 작성 취소
		const cancelReply = () => {
			document.getElementById("memberIdx").value = ''; // 댓글 작성자 입력 필드 초기화
			document.getElementById("replyContents").value = ''; // 댓글 내용 입력 필드 초기화
		}
	</script>
	<style>
		/* 버튼 스타일 */
		button {
			background-color: #4287f5;
			color: white;
			border: none;
			padding: 8px 16px;
			cursor: pointer;
			border-radius: 20px;
		}

		/* 폼 스타일 */
		input[type="text"] {
			padding: 8px;
			border: 1px solid lightgray;
			border-radius: 4px;
			outline: none;
		}

		input[type="text"]:hover {
			border-color: blue;
		}

		/* 댓글 목록 테이블 스타일 */
		table {
			border-collapse: collapse;
			width: 100%;
		}

		th, td {
			padding: 8px;
			text-align: left;
			border-bottom: 1px solid lightgray;
		}

		th {
			background-color: lightgray;
		}

		/* 기타 스타일 */
		h1 {
			color: #4287f5;
		}

		p {
			color: gray;
		}
	</style>
</head>
<body>
<h1>댓글 페이지</h1>

<!-- 댓글 입력 폼 -->
<div>
	<input type="text" id="memberIdx" placeholder="작성자">
	<input type="text" id="postIdx" placeholder="postIdx">
	<input type="text" id="replyContents" placeholder="댓글을 입력해주세요.">
	<button id="reply-write-btn" onclick="replyWrite()">댓글 작성</button>
	<button type="button" onclick="cancelReply();">작성 취소</button>
</div>

<!-- 댓글 목록 -->
<div id = "reply-list">
	<c:choose>
		<c:when test="${empty replyList}">
			<p>댓글이 없습니다.</p>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>댓글번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>작성시간</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
				<c:forEach items="${replyList}" var="reply">
					<tr>
						<td>${reply.replyIdx}</td>
						<td>${reply.memberIdx}</td>
						<td>${reply.replyContents}</td>
						<td>${reply.replyRegDate}</td>
						<td><button onclick="update(${reply.replyIdx}, '${reply.memberIdx}', '${reply.replyContents}')">수정</button></td>
						<td><button onclick="deleteReply(${reply.replyIdx}, ${reply.postIdx})">삭제</button></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>