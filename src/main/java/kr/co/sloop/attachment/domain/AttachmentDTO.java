package kr.co.sloop.attachment.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttachmentDTO {
    private int attachmentIdx;  // 첨부파일 idx
    private int postIdx;    // 게시글 idx
    private String attachmentOrgName;   // 원래 파일명
    private String attachmentDirPath;   // 저장 디렉터리 경로
    private String attachmentName;  // 파일명
    private String attachmentThumbnail; // 썸네일 이미지 파일명
}
