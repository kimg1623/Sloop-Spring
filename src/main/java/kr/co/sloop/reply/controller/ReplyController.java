package kr.co.sloop.reply.controller;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.json.HTTP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

	private final ReplyService replyService;

	// 댓글 작성 폼 요청
	// reply.jsp 로 이동
	@GetMapping
	public String requestCommentForm() {
		return "reply/reply"; // jsp 페이지 경로
	}

	// 댓글 저장
	// 댓글이 저장된 후 해당 게시물의 모든 댓글 목록 반환
	@PostMapping(value = "/save", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<List<ReplyDTO>> save(@ModelAttribute ReplyDTO replyDTO) {
		replyService.save(replyDTO);
		List<ReplyDTO> replyDTOList = replyService.findAll(replyDTO.getPostIdx());
		return ResponseEntity.ok(replyDTOList);
	}

	// 댓글 수정
	// 댓글이 수정된 후 해당 게시물의 모든 댓글 목록 반환
	@PostMapping(value = "/update", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<List<ReplyDTO>> update(@ModelAttribute ReplyDTO replyDTO) {
		replyService.update(replyDTO);
		List<ReplyDTO> replyDTOList = replyService.findAll(replyDTO.getPostIdx());
		return ResponseEntity.ok(replyDTOList);
	}

	// 댓글 삭제
	// 삭제할 댓글의 번호, 댓글이 속한 게시물의 번호를 파라미터로 요청
	// 댓글이 삭제된 후 해당 게시물의 모든 댓글 목록 반환
	@PostMapping("/delete")
	public ResponseEntity<List<ReplyDTO>> deleteReply(@RequestParam int replyIdx, @RequestParam int postIdx) {
		replyService.deleteReply(replyIdx);
		List<ReplyDTO> replyDTOList = replyService.findAll(postIdx);
		return ResponseEntity.ok(replyDTOList);
	}

	// 댓글 목록 조회
	// @PathVariable 애너테이션을 사용하여 postIdx 값을 받아옴
	@GetMapping(value = "/list/{postIdx}")
	public ResponseEntity<List<ReplyDTO>> getReplyList(@PathVariable int postIdx) {

		// replyService의 findAll() 메서드를 호출하여 postIdx에 해당하는 게시물의 모든 댓글 목록을 가져옴.
		// 가져온 댓글 목록은 replyDTOList 변수에 저장
		List<ReplyDTO> replyDTOList = replyService.findAll(postIdx);

		// 댓글 목록 반환
		return ResponseEntity.ok(replyDTOList);
	}
}