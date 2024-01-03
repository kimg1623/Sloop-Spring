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

	@GetMapping
	public String requestCommentForm() {
		return "reply/reply";
	}

	@PostMapping(value = "/save", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<List<ReplyDTO>> save(@ModelAttribute ReplyDTO replyDTO) {
		replyService.save(replyDTO);
		List<ReplyDTO> replyDTOList = replyService.findAll(replyDTO.getPostIdx());
		return ResponseEntity.ok(replyDTOList);
	}

	@PostMapping(value = "/update", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<List<ReplyDTO>> update(@ModelAttribute ReplyDTO replyDTO) {
		replyService.update(replyDTO);
		List<ReplyDTO> replyDTOList = replyService.findAll(replyDTO.getPostIdx());
		return ResponseEntity.ok(replyDTOList);
	}

	@PostMapping("/delete")
	public ResponseEntity<List<ReplyDTO>> deleteReply(@RequestParam int replyIdx, @RequestParam int postIdx) {
		replyService.deleteReply(replyIdx);
		List<ReplyDTO> replyDTOList = replyService.findAll(postIdx);
		return ResponseEntity.ok(replyDTOList);
	}
}