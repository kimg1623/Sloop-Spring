package kr.co.sloop.attachment.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// 첨부파일 객체
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

    private MultipartFile multipartFile; // 실제 첨부파일

    // 생성자 (게시글 idx, 원래 파일명, 저장 디렉터리 경로, 파일명)
    public AttachmentDTO(int postIdx, String attachmentOrgName, String attachmentDirPath, String attachmentName){
        this.postIdx = postIdx;
        this.attachmentOrgName = attachmentOrgName;
        this.attachmentDirPath = attachmentDirPath;
        this.attachmentName = attachmentName;
    }
}
