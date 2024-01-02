package kr.co.sloop.reply.controller;

import kr.co.sloop.reply.domain.ReplyDTO;
import kr.co.sloop.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

	private final ReplyService replyService;

	@GetMapping
	public String requestCommentForm() {
		return "reply/reply";
	}

	@PostMapping("/save")
	public @ResponseBody List<ReplyDTO> save(@ModelAttribute ReplyDTO replyDTO, ReplyDTO ReplyDTO) {
		System.out.println("replyDTO = " + replyDTO);
		replyService.save(ReplyDTO);
		List<ReplyDTO> replyDTOList = replyService.findAll(replyDTO.getPostIdx());
		return replyDTOList;
	}

	@PostMapping("/update")
	public @ResponseBody List<ReplyDTO> update(@ModelAttribute ReplyDTO replyDTO) {
		replyService.update(replyDTO);
		List<ReplyDTO> replyDTOList = replyService.findAll(replyDTO.getPostIdx());
		return replyDTOList;
	}

	@PostMapping("/delete")
	public @ResponseBody List<ReplyDTO> deleteReply(@RequestParam int replyIdx, @RequestParam int postIdx) {
		replyService.deleteReply(replyIdx);
		List<ReplyDTO> replyDTOList = replyService.findAll(postIdx);
		return replyDTOList;
	}
}
