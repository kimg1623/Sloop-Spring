package kr.co.sloop.attachment.mapper;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachmentMapper {
    // 첨부파일 list insert
    int insertAttachmentList(List<AttachmentDTO> attachmentDTOList);
    // postIdx 게시글의 첨부파일 목록 불러오기
    List<AttachmentDTO> findAttachmentByPostIdx(int postIdx);
    // 파일명으로 디렉터리 경로 조회
    String findDirPathByName(String attachmentName);
}
