package kr.co.sloop.attachment.controller;

import kr.co.sloop.attachment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;

@Controller
@RequestMapping("/attachment")
@RequiredArgsConstructor
@Log4j2
public class AttachmentController {
    private final AttachmentService attachmentService;

    // 첨부파일 다운로드
    @GetMapping("/download/{attachmentName}")
    public void downloadAttachment(@PathVariable("attachmentName") String attachmentName,
                             HttpServletResponse response) throws Exception {
        try {
            String attachmentDirPath = attachmentService.findDirPathByName(attachmentName);
            attachmentDirPath += "/";
            String path = attachmentDirPath + attachmentName; // 경로에 접근할 때 역슬래시('\') 사용

            File file = new File(path);
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

            FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
            OutputStream out = response.getOutputStream();

            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                out.write(buffer, 0, read);
            }

        } catch (Exception e) {
            throw new Exception("download error");
        }
    }
}
