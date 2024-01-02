package kr.co.sloop.reply.controller;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/replies")
public class ReplyController {

	private final ReplyService replyService;

	@Autowired
	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}

	@GetMapping
	public String requestCommentForm(){
		return "reply/reply";
	}

	@PostMapping
	public ResponseEntity<String> saveReply(@RequestBody ReplyDTO replyDTO) {
		replyService.saveReply(replyDTO);
		return ResponseEntity.ok("댓글이 성공적으로 저장되었습니다.");
	}

	@PutMapping
	public ResponseEntity<Void> updateReply(@RequestBody ReplyDTO commentDTO) {
		replyService.updateReply(commentDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{replyIdx}")
	public ResponseEntity<Void> deleteReply(@PathVariable int replyIdx) {
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setReplyIdx(replyIdx);
		replyService.deleteReply(replyDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{postIdx}")
	public ResponseEntity<List<ReplyDTO>> getRepliesByPostId(@PathVariable int postIdx) {
		List<ReplyDTO> replies = replyService.getRepliesByPostId(postIdx);
		return ResponseEntity.ok(replies);
	}
}
