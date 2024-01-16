package kr.co.sloop.attachment.repository;

import kr.co.sloop.attachment.domain.AttachmentDTO;
import kr.co.sloop.attachment.mapper.AttachmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttachmentRepositoryImpl implements AttachmentRepository{
    private final AttachmentMapper attachmentMapper;

    // 첨부파일 insert
    @Override
    public int uploadAttachmentListToDBTable(List<AttachmentDTO> attachmentDTOList) {
        return attachmentMapper.insertAttachmentList(attachmentDTOList);
    }

    // postIdx 게시글의 첨부파일 목록 불러오기
    @Override
    public List<AttachmentDTO> findAttachmentByPostIdx(int postIdx) {
        return attachmentMapper.findAttachmentByPostIdx(postIdx);
    }

    // 파일명으로 저장 디렉터리 조회
    @Override
    public String findDirPathByName(String attachmentName) {
        return attachmentMapper.findDirPathByName(attachmentName);
    }
}
