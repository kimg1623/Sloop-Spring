// 댓글 작성
const replyWrite = (postIdx, loginMemberIdx) => {
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
            memberIdx: loginMemberIdx,
            replyContents: contents,
            postIdx: postIdx
        },
        dataType: "json",
        success: function(replyList) {
            // 댓글 작성 후, 페이지 업데이트
            displayReplyList(replyList, loginMemberIdx); // 수정된 댓글 목록을 표시하는 함수 호출
            cancelReply(); // 입력 필드 초기화
        },
        error: function () {
            alert("댓글 작성에 실패했습니다.");
            console.log("작성 실패");
        }
    });
};

// 댓글 수정 입력폼 출력
const update = (replyIdx, memberIdx, replyContents, memberNickname, postIdx) => {
    // 댓글 수정 폼을 동적으로 생성하여 출력
    let form = "";
    form += "<div id='updateWriter' value='" + memberNickname + "'></div>"; // 댓글 작성자 닉네임
    form += "<div class='replyInput_content_box'><input class='replyInput_content' type='text' id='updateContents' value='" + replyContents + "'></div>";   // 수정 폼
    form += "<div class='replyButton_wrap'>";
    form += "<button class='btn_update_reply_form' type='button' onclick='updateAction(" + replyIdx + ", " + memberIdx + "," + postIdx +")'>수정완료</button>";
    form += "</div>";

    document.getElementById('reply_' + replyIdx).innerHTML = form;
}

// 댓글 수정
const updateAction = (replyIdx, loginMemberIdx, postIdx) => {
    // 수정된 댓글 정보를 가져와서 수정 작업 수행
    const contents = document.getElementById("updateContents").value;

    $.ajax({
        type: "post",
        url: "/reply/update",
        data: {
            replyIdx: replyIdx,
            memberIdx: loginMemberIdx,
            replyContents: contents,
            postIdx: postIdx
        },
        dataType: "json",
        success: function(replyList) {
            console.log(replyList);
            displayReplyList(replyList, loginMemberIdx); // 수정된 댓글 목록을 표시하는 함수 호출
        },
        error: function() {
            alert("댓글 수정에 실패했습니다.");
            console.log("수정 실패");
        }
    });
};

// 댓글 삭제
const deleteReply = (replyIdx, postIdx, loginMemberIdx) => {
    // 삭제 전에 팝업을 띄우고 사용자의 선택에 따라 작업을 수행
    if (confirm("댓글을 삭제하시겠습니까?")) {
        // 사용자가 확인을 선택한 경우 삭제 작업 수행
        $.ajax({
            type: "post",
            url: "/reply/delete",
            data: {
                replyIdx: replyIdx,
                postIdx: postIdx
            },
            dataType: "json",
            success: function(replyList) {
                displayReplyList(replyList, loginMemberIdx); // 삭제된 댓글 목록을 표시하는 함수 호출
            },
            error: function() {
                alert("댓글 삭제에 실패했습니다.");
                console.log("삭제 실패");
            }
        });
    } else {
        // 사용자가 취소를 선택한 경우 아무 작업도 수행하지 않음
        console.log("삭제 취소");
    }
};

// 댓글 목록 출력
const displayReplyList = (replyList, loginMemberIdx) => {
    let output = "<div class='reply_section_wrap'>";
    for (let i in replyList){
        output += "<div class='reply_section_top'>";
        output += "<div id='reply_" + replyList[i].replyIdx +"' />";
        output += "<div class='reply_nickname'>" + replyList[i].memberNickname + "</div>";
        output += "<div class='reply_date'>" + replyList[i].replyRegDate + "</div>";
        output += "</div>";
        output += "<section class='reply_section_bottom'>";
        output += "<p class='reply_section_bottom'>" + replyList[i].replyContents + "</p>";

        if(replyList[i].memberIdx == loginMemberIdx) {    // 작성자와 로그인된 사용자가 동일한 경우에만 수정, 삭제 버튼 출력

            output += "<div class='replyButton_wrap'>";
            output += "<button class='btn_update_reply' onclick=\"update(" + replyList[i].replyIdx + ", '" + replyList[i].memberIdx + "', '" + replyList[i].replyContents + "', '" + replyList[i].memberNickname + "', " + replyList[i].postIdx + ")\">수정</button>";
            output += "<button class='btn_delete_reply' onclick=\"deleteReply(" + replyList[i].replyIdx + ", " + replyList[i].postIdx + ", " + loginMemberIdx + ")\">삭제</button>";
            output += "</div>";
        }
        output += "</section>";
    output += "</div>";
    }
    document.getElementById('reply-list').innerHTML = output;
};

// 작성 취소
const cancelReply = () => {
    document.getElementById("replyContents").value = ''; // 댓글 내용 입력 필드 초기화
};

// 댓글 목록 조회
const loadReplyList = (postIdx, loginMemberIdx) =>{
    $.ajax({
        type: "get",
        url: "/reply/list/" + postIdx,
        dataType: "json",
        success: function(replyList) {
            console.log("조회 성공");
            console.log(replyList);
            displayReplyList(replyList, loginMemberIdx); // 댓글 목록 출력
        },
        error: function() {
            console.log("조회 실패");
        }
    });

};