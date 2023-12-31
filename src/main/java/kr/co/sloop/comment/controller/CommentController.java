package kr.co.sloop.comment.controller;

import kr.co.sloop.comment.service.CommentService;
import kr.co.sloop.comment.domain.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/save")
	public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO) {
		System.out.println("commentDTO = " + commentDTO);
		commentService.save(commentDTO);
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
		return commentDTOList;
	}

	@PostMapping("/update")
	public @ResponseBody CommentDTO update(@ModelAttribute CommentDTO commentDTO) {
		commentService.update(commentDTO);
		// 수정된 댓글 정보 반환
		CommentDTO updatedCommentDTO = commentService.findById(commentDTO.getId());
		return updatedCommentDTO;
	}

	@PostMapping("/delete")
	public @ResponseBody List<CommentDTO> delete(@RequestParam Long commentId, @RequestParam Long boardId) {
		commentService.delete(commentId);
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		List<CommentDTO> commentDTOList = commentService.findAll(boardId);
		return commentDTOList;
	}

	@GetMapping("/list")
	public @ResponseBody List<CommentDTO> getCommentList(@RequestParam Long boardId, @RequestParam(defaultValue = "1") int page) {
		int pageSize = 10; // 한 페이지에 보여줄 댓글 수
		int offset = (page - 1) * pageSize; // 페이지의 시작 인덱스

		List<CommentDTO> commentDTOList = commentService.findPagedComments(boardId, offset, pageSize);
		return commentDTOList;
	}
}