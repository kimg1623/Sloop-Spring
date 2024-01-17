package kr.co.sloop.attachment.service;

import kr.co.sloop.attachment.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService{
    private final AttachmentRepository attachmentRepository;

    // 파일명으로 저장 디렉터리 조회
    @Override
    public String findDirPathByName(String attachmentName) {
        return attachmentRepository.findDirPathByName(attachmentName);
    }
}
