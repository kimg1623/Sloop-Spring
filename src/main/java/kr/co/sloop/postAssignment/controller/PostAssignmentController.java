package kr.co.sloop.postAssignment.controller;


import kr.co.sloop.common.AlertUtils;
import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.post.service.SearchServiceImpl;
import kr.co.sloop.postAssignment.domain.PostAssignmentDTO;
import kr.co.sloop.postAssignment.service.PostAssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public String write(@ModelAttribute("postAssignmentDTO") PostAssignmentDTO postAssignmentDTO, HttpSession session){
        log.info("postAssignmentDTO@@ : " + postAssignmentDTO);
        log.info("multipartFileList@@: " + multipartFileList);

        // @Valid 유효성 검사 [*****]

        // 로그인 되어 있는 사용자 idx 세션에서 가져온다.
        String loginMemberIdx = (String)session.getAttribute("loginMemberIdx");
        postAssignmentDTO.setMemberIdx(Integer.parseInt(loginMemberIdx));

        // 게시판 idx(boardIdx)를 쿼리 스트링을 통해 가져와야 한다. [*****]
        // @RequestParam("boardIdx") int boardIdx
        int boardIdx = 2;
        postAssignmentDTO.setBoardIdx(boardIdx);

        // 첨부파일 DTO에 추가
        postAssignmentDTO.setMultipartFileList(multipartFileList);

        // 글 DB에 삽입
        boolean result = postAssignmentService.write(postAssignmentDTO, multipartFileList);

        if(result){ // 글 작성 성공
            // 해당 글 상세 조회 페이지로 이동
            // return "redirect:/postassignment/detail?postIdx=" + postForumDTO.getPostIdx();
            return "redirect:/postassignment/list";
        }else {     // 글 작성 실패
            return "postAssignment/write";
        }

        // return "redirect:/postassignment/list";
        // return "redirect:postassignment/detail?postIdx=" + postIdx;
    }

    // 파일업로드 샘플 코드 - 사용하지 않음
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

    // 글 작성하기 : ckeditor 이미지 업로드
    @PostMapping("/upload-image")
    public void imageUpload(HttpServletRequest request,
                            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload, @RequestParam(value="CKEditorFuncNum", required=false) String CKEditorFuncNum) throws Exception {
        OutputStream out = null;
        PrintWriter printWriter = null;

        // response 인코딩
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        try {
            // 파일 이름 가져오기 (확장자 포함)
            String fileName = upload.getOriginalFilename();

            // 파일 확장자 검사 [*****] window, mac 다른지 log를 통해 확인 필요
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            extension = extension.toLowerCase(); // 소문자로 변경

            // 허용되는 이미지 확장자
            String allowedExtensions = "(jpg|jpeg|gif|png)";

            // 현재 첨부된 파일의 확장자가 허용되는 확장자 목록에 없는 경우, 오류 메세지 반환
            if(!extension.matches(allowedExtensions)){
                printWriter = response.getWriter();
                printWriter.println(createJSON(0, new String[]{"jpg, jpeg, gif, png 이미지 파일만 지원합니다."}));
                printWriter.flush(); //초기화
                return;
            }

            // 첨부 파일 용량 검사
            // 10MB 초과하는 이미지 업로드 시, 오류 메세지 반환
            if(upload.getSize() > 10000000){
                printWriter = response.getWriter();
                printWriter.println(createJSON(0, new String[]{"10MB 이하의 이미지 파일만 첨부할 수 있습니다."}));
                printWriter.flush(); //초기화
                return;
            }

            if(fileName.length() > 63){ // 파일 이름이 63 초과 시, 0-62번째 문자까지만 저장. (DB에 저장할 수 있는 파일명은 100자까지, 100 - 37(uuid_)만큼 저장 가능)
                fileName = fileName.substring(0, 62);
            }
            byte[] bytes = upload.getBytes();

            // 이미지 경로 생성
            log.info("\n\n ===== 현재 경로 : " + request.getContextPath());
            String path = "/resources/uploads/";    // 이미지 경로 설정(폴더 자동 생성)

            // uuid 생성 (36자)
            UUID uuid = UUID.randomUUID();

            // ckeditor로 첨부한 이미지가 저장될 풀 경로 (서버경로/uploads/uuid_파일이름) [*****] -> 서버경로/스터디그룹idx/postForum/uuid_파일이름)
            String ckUploadPath = uploadPath + File.separator + "uploads" + File.separator + uuid + "_" + fileName;
            log.info("uploadPath : " + uploadPath);
            log.info("ckUploadPath : " + ckUploadPath);

            File folder = new File(path);
            log.info("path:" + path);    // 이미지 저장경로 console에 확인
            //해당 디렉토리 확인
            if (!folder.exists()) {
                try {
                    folder.mkdirs(); // 폴더 생성
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            out = new FileOutputStream(ckUploadPath);
            out.write(bytes);
            out.flush(); // outputStream에 저장된 데이터를 전송하고 초기화

            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/postassignment/ckImgSubmit?uid=" + uuid + "&fileName=" + fileName; // 작성화면

            // 업로드시 메시지 json 출력
            String[] message = {fileName, fileUrl};

            // printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
            printWriter.println(createJSON(1, message));
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    // 서버로 전송된 이미지 가져오기 for ckeditor
    @RequestMapping(value="/ckImgSubmit")
    public void ckSubmit(@RequestParam(value="uid") String uid
            , @RequestParam(value="fileName") String fileName
            , HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //서버에 저장된 이미지 경로
        String path = "/resources/uploads/";    // 이미지 경로 설정(폴더 자동 생성)
        System.out.println("path:" + path);
        String sDirPath = path + uid + "_" + fileName;

        sDirPath = uploadPath + File.separator + "uploads" + File.separator + uid + "_" + fileName;


        File imgFile = new File(sDirPath);

        //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
        if (imgFile.isFile()) {
            byte[] buf = new byte[1024];
            int readByte = 0;
            int length = 0;
            byte[] imgBuf = null;

            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            ServletOutputStream out = null;

            try {
                fileInputStream = new FileInputStream(imgFile);
                outputStream = new ByteArrayOutputStream();
                out = response.getOutputStream();

                while ((readByte = fileInputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, readByte);
                }

                imgBuf = outputStream.toByteArray();
                length = imgBuf.length;
                out.write(imgBuf, 0, length);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                outputStream.close();
                fileInputStream.close();
                out.close();
            }
        }
    }

    // 글 상세조회
    @GetMapping("/detail")
    public String detailForm(@RequestParam("postIdx") int postIdx, Model model){
        // 글 정보 불러오기
        PostAssignmentDTO postAssignmentDTO = postAssignmentService.detailForm(postIdx);
        model.addAttribute("postAssignmentDTO", postAssignmentDTO);

        log.info("글 상세조회 : " + postAssignmentDTO);

        return "postAssignment/detail";
    }

    // 글 수정하기 : 화면 출력


    // 글 수정하기


    // 글 삭제하기
    @GetMapping("/delete")
    public String delete(@RequestParam("postIdx") int postIdx, HttpSession session, HttpServletResponse response){
        try {
            // 로그인 된 회원과 글 작성자가 동일한지 검사
            String writerEmail = postAssignmentService.findWriterEmailByPostIdx(postIdx);
            if (!writerEmail.equals(session.getAttribute("loginEmail"))) {
                // 동일하지 않다면 삭제하지 않고 글 목록 페이지로 리다이렉트
                AlertUtils.alertAndMovePage(response, "본인의 글만 삭제할 수 있습니다.", "/postassignment/list");
                return "";
            }

            log.info("postIdx확인: " + postIdx);
            boolean result = postAssignmentService.delete(postIdx);
            log.info("result확인: " + result);
            if(!result){ // 삭제 실패
                AlertUtils.alert(response, "게시글 삭제를 실패하였습니다.");
            }
        }catch (Exception e){
            // 목록 조회 페이지로 돌아간다.
            return "redirect:/postassignment/list";
        }
        // 삭제 후, 목록 조회 페이지로 돌아간다.
        return "redirect:/postassignment/list";
    }

    // json 객체 생성
    public JSONObject createJSON(int uploaded, String[] message){
        JSONObject json = new JSONObject();

        json.put("uploaded", uploaded);
        if(uploaded == 0) { // 업로드 실패
            json.put("error", new JSONObject().put("message", message[0]));
        }else{ // 업로드 성공
            json.put("fileName", message[0]);
            json.put("url", message[1]);
        }

        return json;
    }

}
