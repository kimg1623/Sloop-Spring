package kr.co.sloop.postForum.controller;

import kr.co.sloop.common.AlertUtils;
import kr.co.sloop.post.domain.SearchDTO;
import kr.co.sloop.post.service.PageServiceImpl;
import kr.co.sloop.post.service.SearchServiceImpl;
import kr.co.sloop.postForum.domain.PostForumDTO;
import kr.co.sloop.postForum.service.PostForumServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

@Log4j2
@Controller
@RequestMapping("/postforum")
@RequiredArgsConstructor
public class PostForumController {
    @Resource(name="uploadPath")
    private String uploadPath; // 업로드된 사진이 저장될 서버 경로 (디렉터리 경로)
    private final PostForumServiceImpl postForumServiceImpl;
    private final PageServiceImpl pageServiceImpl; // 페이징
    private final SearchServiceImpl searchServiceImpl; // 페이징 + 검색

    // 글 작성하기 : 화면 출력
    @GetMapping("/write")
    public String writeForm(Model model){
        PostForumDTO postForumDTO = new PostForumDTO();
        postForumDTO.setCategoryPostIdx(1);
        model.addAttribute("postForumDTO", postForumDTO);
        return "postForum/write";
    }

    // 글 작성하기
    @PostMapping("/write")
    public String write(@Valid @ModelAttribute("postForumDTO") PostForumDTO postForumDTO, BindingResult errors, HttpSession session){
        log.info(errors);
        log.info(postForumDTO);

        // 객체 바인딩에 유효성 오류가 존재한다면, 작성 페이지로 돌아가서 오류 메세지를 출력한다.
        if(errors.hasErrors()){
            return "postForum/write";
        }

        // 로그인 되어 있는 사용자 email을 세션에서 가져온다. [*****]
        // String memberEmail = (String) session.getAttribute("memberEmail");
        String memberEmail = (String)session.getAttribute("loginEmail");
        postForumDTO.setMemberEmail(memberEmail);

        // 게시판 idx(boardIdx)를 쿼리 스트링을 통해 가져와야 한다. [*****]
        // @RequestParam("boardIdx") int boardIdx
        int boardIdx = 3;
        postForumDTO.setBoardIdx(boardIdx);

        boolean result = postForumServiceImpl.write(postForumDTO);

        if(result){ // 글 작성 성공
            // 해당 글 상세 조회 페이지로 이동
            return "redirect:/postforum/detail?postIdx=" + postForumDTO.getPostIdx();
        }else {     // 글 작성 실패
            return "postForum/write";
        }
    }

    // 글 작성하기 : 이미지 업로드
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

            //이미지 경로 생성
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
            String fileUrl = "/postforum/ckImgSubmit?uid=" + uuid + "&fileName=" + fileName; // 작성화면

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

    // 서버로 전송된 이미지 가져오기
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

    // 글 목록 조회
    // /postforum/list?page={현재페이지}&searchType={검색유형}&keyword={검색어}
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                       @RequestParam(value = "searchType", defaultValue = "0", required = false) int searchType,
                       @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                       Model model){
        // 게시판 idx
        // [*****] 쿼리 스트링으로 가져오도록 수정
        // [*****] public String List(@RequestParam("boardIdx") int boardIdx)
        int boardIdx = 3;

        // 검색어 앞뒤 공백 제거
        keyword = keyword.trim();

        // 검색 + 페이징을 위한 객체
        SearchDTO searchDTO = searchServiceImpl.initialize(boardIdx, page, searchType, keyword, 3);
        model.addAttribute("searchDTO", searchDTO);

        // 글 목록 조회 + 겸색 + 페이징
        ArrayList<PostForumDTO> postForumDTOList = postForumServiceImpl.list(searchDTO);
        model.addAttribute("postForumDTOList", postForumDTOList);

        return "postForum/list";
    }

    // 글 상세 조회
    @GetMapping("/detail")
    public String detailForm(@RequestParam("postIdx") int postIdx, Model model){
        // 글 정보 불러오기
        PostForumDTO postForumDTO = postForumServiceImpl.detailForm(postIdx);
        model.addAttribute("postForumDTO", postForumDTO);
        return "postForum/detail";
    }

    // 글 수정하기 : 화면 출력
    @GetMapping("/update")
    public String updateForm(@RequestParam("postIdx") int postIdx, Model model){
        PostForumDTO postForumDTO = postForumServiceImpl.findByPostIdx(postIdx);
        model.addAttribute("postForumDTO", postForumDTO);
        return "postForum/update";
    }

    // 글 수정하기
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("postForumDTO") PostForumDTO postForumDTO, BindingResult errors, HttpSession session, HttpServletResponse response){
        // 로그인 된 회원과 글 작성자가 동일한지 검사
        if(!postForumDTO.getMemberEmail().equals(session.getAttribute("loginEmail"))){
            // 동일하지 않다면 수정하지 않고 글 목록 페이지로 리다이렉트
            return "redirect:/postforum/list";
        }

        // 객체 바인딩에 유효성 오류가 존재한다면, 작성 페이지로 돌아가서 오류 메세지를 출력한다.
        if(errors.hasErrors()){
            return "redirect:/postForum/update?postIdx=" + postForumDTO.getPostIdx();
        }

        try {
            // 글 수정하기
            boolean result = postForumServiceImpl.update(postForumDTO);

            if (result) { // 수정 성공
                return "redirect:/postforum/detail?postIdx=" + postForumDTO.getPostIdx();
            } else { // 수정 실패
                AlertUtils.alertAndBackPage(response, "수정에 실패하였습니다.");
                return "redirect:/postforum/list";
            }
        }catch (Exception e){
            return "redirect:/postforum/list";
        }
    }

    // 글 삭제하기
    @GetMapping("/delete")
    public String delete(@RequestParam("postIdx") int postIdx, HttpSession session, HttpServletResponse response){
        try {
            // 로그인 된 회원과 글 작성자가 동일한지 검사
            String writerEmail = postForumServiceImpl.findWriterEmailByPostIdx(postIdx);
            if (!writerEmail.equals(session.getAttribute("loginEmail"))) {
                // 동일하지 않다면 삭제하지 않고 글 목록 페이지로 리다이렉트
                AlertUtils.alertAndMovePage(response, "본인의 글만 삭제할 수 있습니다.", "/postforum/list");
                return "";
            }

            postForumServiceImpl.delete(postIdx);
        }catch (Exception e){
            // 목록 조회 페이지로 돌아간다.
            return "redirect:/postforum/list";
        }
        // 삭제 후, 목록 조회 페이지로 돌아간다.
        return "redirect:/postforum/list";
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
