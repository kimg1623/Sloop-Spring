package kr.co.sloop.attachment.repository;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository {
    // 첨부파일 insert
    int uploadAttachmentListToDBTable(List<AttachmentDTO> attachmentDTOList);
    // postIdx 게시글의 첨부파일 목록 불러오기
    List<AttachmentDTO> findAttachmentByPostIdx(int postIdx);
    // 파일명으로 저장 디렉터리 조회
    String findDirPathByName(String attachmentName);
}
