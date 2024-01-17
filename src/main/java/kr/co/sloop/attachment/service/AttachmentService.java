package kr.co.sloop.attachment.service;

import org.springframework.stereotype.Service;

@Service
public interface AttachmentService {
    // 파일명으로 저장 디렉터리 조회
    String findDirPathByName(String attachmentName);
}
