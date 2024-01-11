package kr.co.sloop.postAssignment.controller;


import kr.co.sloop.common.AlertUtils;
import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.post.service.SearchServiceImpl;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postAssignment.service.PostAssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.File;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/postassignment")
public class PostAssignmentController {
    private final PostAssignmentService postAssignmentService; // 과제 게시판 service
    private final SearchServiceImpl searchServiceImpl; // 페이징 + 검색
    @Resource(name="uploadPathForAssignment")
    private String uploadPath; // 업로드된 첨부 파일이 저장될 서버 경로 (디렉터리 경로)
    List<MultipartFile> multipartFileList; // ajax를 통해 임시 저장될 첨부파일 리스트

    // 글 목록 조회
    // /postassignment/list?page={현재페이지}&searchType={검색유형}&keyword={검색어}
    @GetMapping("/list")
    public String listForum(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                            @RequestParam(value = "searchType", defaultValue = "0", required = false) int searchType,
                            @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                            Model model){
        // 게시판 idx
        // [*****] 쿼리 스트링으로 가져오도록 수정
        // [*****] public String List(@PathVariable("boardIdx") int boardIdx)
        int boardIdx = 2;

        // 검색어 앞뒤 공백 제거
        keyword = keyword.trim();

        // 검색 + 페이징을 위한 객체
        // boardType 2 [*****]
        SearchDTO searchDTO = searchServiceImpl.initialize(boardIdx, page, searchType, keyword, 2);
        model.addAttribute("searchDTO", searchDTO);

        ArrayList<PostAssignmentDTO> postAssignmentDTOList = postAssignmentService.list(searchDTO);
        model.addAttribute("postAssignmentDTOList", postAssignmentDTOList);

        return "postAssignment/list";
    }

    // 글 작성하기
    @GetMapping("/write")
    public String writeForm(Model model){
        PostAssignmentDTO postAssignmentDTO = new PostAssignmentDTO();
        model.addAttribute("postAssignmentDTO", postAssignmentDTO);
        return "postAssignment/write";
    }

    // 글 작성하기
    @PostMapping("/write")
    public String write(@ModelAttribute("postAssignmentDTO") PostAssignmentDTO postAssignmentDTO){
        /*
        // 문자열 -> sql.Timestamp 형식으로 변환
        Timestamp assignmentEndDate = Timestamp.valueOf(postAssignmentDTO.getAssignmentEndDateString() + ":00");
        postAssignmentDTO.setAssignmentEndDate(assignmentEndDate);
        */
        log.info("postAssignmentDTO@@ : " + postAssignmentDTO);
        log.info("multipartFileList@@: " + multipartFileList);

        return "redirect:/postassignment/list";
        // return "redirect:postassignment/detail?postIdx=" + postIdx;
    }

    // 실험 페이지
    @GetMapping("/test")
    public String test(Model model){
        PostAssignmentDTO postAssignmentDTO = new PostAssignmentDTO();
        //postAssignmentDTO.setCategoryPostIdx(1);
        model.addAttribute("postAssignmentDTO", postAssignmentDTO);
        return "postAssignment/write_attachment2";
    }

    @ResponseBody
    @PostMapping("/uploadAttachments")
    public String fileUpload(
            @RequestParam("article_file") List<MultipartFile> multipartFile
            , HttpServletRequest request) {

        String strResult = "{ \"result\":\"FAIL\" }";
        String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
        String fileRoot;
        try {
            // 첨부파일이 있을 때
            if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
                for(MultipartFile file:multipartFile) {
                    fileRoot = contextRoot + "resources/upload/";
                    System.out.println("fileRoot" + fileRoot);

                    String originalFileName = file.getOriginalFilename();	//오리지날 파일명
                    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
                    String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

                    File targetFile = new File(fileRoot + savedFileName);
                    try {
                        InputStream fileStream = file.getInputStream();
                        FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
                    } catch (Exception e) {
                        //파일삭제
                        FileUtils.deleteQuietly(targetFile);	//저장된 현재 파일 삭제
                        e.printStackTrace();
                        break;
                    }
                }
                strResult = "{ \"result\":\"OK\" }";
            }else { // 첨부파일이 없을 때
                strResult = "{ \"result\":\"OK\" }";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return strResult;
    }

    // 첨부파일들을 multipartFileList에 추가
    // 확장자 유효성 검사를 시행한다.
    @ResponseBody
    @PostMapping("/uploadAttachmentsUsingAjax")
    public String fileUploadUsingAjax (
            @RequestParam("article_file") List<MultipartFile> multipartFile, HttpServletResponse response) {
        String strResult = "{ \"result\":\"FAIL\" }";

        try {
            // 첨부파일이 있을 때
            if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
                for(MultipartFile file:multipartFile) {
                    String originalFileName = file.getOriginalFilename();	//오리지날 파일명
                    String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);	//파일 확장자
                    extension = extension.toLowerCase(); // 소문자로 변경

                    // 허용되는 첨부파일 확장자
                    String allowedExtensions = "(xls|xlsx|txt|png|jpg|jpeg|html|htm|mpg|mp4|mp3|pdf|zip)";

                    // 현재 첨부된 파일의 확장자가 허용되는 확장자 목록에 없는 경우, 오류 메세지 반환
                    if(!extension.matches(allowedExtensions)){
                        strResult = "{ \"result\":\"TYPEERROR\" }";
                        //AlertUtils.alertAndBackPage(response, "허용되는 확장자는 xls,xlsx,txt,png,jpg,jpeg,html,htm,mpg,mp4,mp3,pdf,zip 입니다.");
                        return strResult;
                    }
                }
                // 모든 첨부 파일의 확장자가 허용된다면
                // multipartFileList에 추가
                multipartFileList = new ArrayList<>();
                multipartFileList.addAll(multipartFile);

                strResult = "{ \"result\":\"OK\" }";
            }else { // 첨부파일이 없을 때
                strResult = "{ \"result\":\"OK\" }";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return strResult;
    }

}
