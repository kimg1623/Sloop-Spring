package kr.co.sloop.reply.controller;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
@Log4j
public class ReplyController {

	private final ReplyService replyService;

	// 댓글 작성 폼 요청
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
}