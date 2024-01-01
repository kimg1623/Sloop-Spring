package kr.co.sloop.comment.controller;

import kr.co.sloop.comment.domain.CommentDTO;
import kr.co.sloop.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	public ResponseEntity<Void> saveComment(@RequestBody CommentDTO commentDTO) {
		commentService.saveComment(commentDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping
	public ResponseEntity<Void> updateComment(@RequestBody CommentDTO commentDTO) {
		commentService.updateComment(commentDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{replyIdx}")
	public ResponseEntity<Void> deleteComment(@PathVariable int replyIdx) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setReplyIdx(replyIdx);
		commentService.deleteComment(commentDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{postIdx}")
	public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable int postIdx) {
		List<CommentDTO> comments = commentService.getCommentsByPostId(postIdx);
		return ResponseEntity.ok(comments);
	}
}
