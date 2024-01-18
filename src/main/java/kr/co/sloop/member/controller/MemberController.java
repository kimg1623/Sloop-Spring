package kr.co.sloop.member.controller;


import kr.co.sloop.common.AlertUtils;
import kr.co.sloop.member.domain.MemberDTO;
import kr.co.sloop.member.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import java.util.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    // 파일업로드
    @Resource(name="uploadPathforMember")
    String uploadPath;
    private final MemberService memberService;



    // signupForm.jsp 로 이동
    @GetMapping("/signup")
    public String signupForm(Model model){
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);
        return "member/signupForm";
    }
/** bcrypt 없이 하는 회원가입 */
    // signupForm.jsp -> form method = Post 로 데이터 받아옴
    /*@PostMapping("/signup")
    public String signup(@Validated @ModelAttribute MemberDTO memberDTO , BindingResult errors , HttpServletResponse response) throws IOException{

        if (errors.hasErrors()){
            AlertUtils.alert(response , "회원가입에 실패하였습니다.");
            return "member/signupForm";
        }

        int signupResult = memberService.signup(memberDTO);

        if (signupResult > 0) {
            AlertUtils.alertAndMovePage(response, "회원가입 되었습니다.", "login");// update 성공시 redirect로 상세보기 화면 출력
            *//*return "member/signupSuccess"; // 회원가입 성공 시 이동*//*
        }
        return "member/signupForm";    // 회원가입 실패 시 이동

    }*/
    /** bcrypt 없이 하는 회원가입 */

    /** bcrypt 회원가입 */
    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute MemberDTO memberDTO , BindingResult errors , HttpServletResponse response) throws IOException{

        if (errors.hasErrors()){
            AlertUtils.alert(response , "회원가입에 실패하였습니다.");
            return "member/signupForm";
        }

        int signupResult = memberService.signup(memberDTO);
        try {
            if (signupResult > 0) {

                AlertUtils.alertAndMovePage(response, "회원가입 되었습니다.", "login");// update 성공시 redirect로 상세보기 화면 출력
                /*return "member/signupSuccess"; // 회원가입 성공 시 이동*/
            } else {
                AlertUtils.alertAndMovePage(response , "이메일,핸드폰번호,닉네임은 중복검사를 꼭 해주세요.","signup");
            }
        } catch (DuplicateKeyException e) {
        }
        return "member/signupForm";

        }

    /** bcrypt 회원가입 */


    // 회원가입 성공 후 로그인 하러 가기 버튼 클릭시 LoginForm.jsp 로 이동
    @GetMapping("/login")
    public String loginForm(@ModelAttribute MemberDTO memberDTO){
        return "member/loginForm";
    }


    // form method = post 로 데이터 받아옴
    // login 성공시 세션에 "loginEmail" & 게시판Idx 추가
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute MemberDTO memberDTO, BindingResult errors ,HttpSession session , HttpServletResponse response) throws IOException {

        if (errors.hasGlobalErrors()){      // 해당 메서드 내의 에러발생시
            AlertUtils.alertAndMovePage(response, "로그인 도중 에러발생!!", "login");
        }

        Map<String, String> loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            log.info("로그인 성공");
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            session.setAttribute("loginMemberIdx", loginResult.get("loginMemberIdx")); // 지원 추가
            session.setAttribute("loginMemberNickname", loginResult.get("loginMemberNickname")); // 지원 추가
            return "redirect:/"; // 로그인 성공시 세션에 "loginEmail"이란 이름으로 저장 후 studyList or mypage 로  // 지원 수정
        } else{
            AlertUtils.alertAndMovePage(response, "로그인에 실패하였습니다..","/member/login");
        } return "redirect:/member/login";
    }

    // 이메일 중복확인 AJAX
    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmailddd"+memberEmail);
        log.info("emailCheck=====" + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        log.info("checkResult CONTROLLER" + checkResult );
        return checkResult;
    }
    // 닉네임 중복확인 AJAX
    @PostMapping("/nickname-check")
    public @ResponseBody String nicknameCheck(@RequestParam("memberNickname") String memberNickname){
        String checkResult2 = memberService.nicknameCheck(memberNickname);
        log.info("memberNickname == "+memberNickname);
        return checkResult2;
    }
    @PostMapping("/phoneNumb-check")
    public @ResponseBody String phoneNumbCheck(@RequestParam("memberPhonenumber") String memberPhonenumber){
        String checkResult3 = memberService.phoneNumbCheck(memberPhonenumber);
        log.info("memberPhonenumber == "+memberPhonenumber);
        return checkResult3;
    }
    // 회원 목록 보기 추후에 관리자 권한으로만 갈 수 있게하기
    @GetMapping("memberList")
    public String memberList(Model model){
        List<MemberDTO> memberDTOList = memberService.findMemberList(model);
        model.addAttribute("memberList", memberDTOList);
        return "member/memberList";    // 관리자 일 때 memberList 이동 가능
    }

    // update.jsp의 Form 출력
    @GetMapping("update")
    public String updateForm( HttpSession session , HttpServletResponse response,
                              Model model) throws IOException {
        // 세션에 저장된 이메일 가져오기

        String loginEmail = (String) session.getAttribute("loginEmail");    // 세션에 저장된 이메일로 정보 가져오기
        if (loginEmail != null) {
            MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
            model.addAttribute("memberDTO",memberDTO);
            return "member/update";

        } else{
            AlertUtils.alertAndMovePage(response, "로그인에 실패하였습니다..","/member/login");
        } return "redirect:/member/login";


    }


    // update.jsp 의 Form method = Post로 데이터 받아옴
    @PostMapping("update")
    public String update (@Validated @ModelAttribute("memberDTO") MemberDTO memberDTO,
                          BindingResult errors,
                          @RequestParam("memberIdx") int memberIdx,
                          HttpSession session,
                          HttpServletResponse response) throws IOException{
        log.info("수정실패---------");
        if (errors.hasGlobalErrors()){
           AlertUtils.alert(response,"수정에 실패했습니다.");
           return "member/update";
        }

        boolean result = memberService.update(memberDTO);

        boolean idxMatch = (memberIdx == memberDTO.getMemberIdx());

        if (result == idxMatch) {

            AlertUtils.alertAndMovePage(response,"수정되었습니다." ,"/member?memberIdx="+memberDTO.getMemberIdx());// update 성공시 redirect로 상세보기 화면 출력
        }
        AlertUtils.alertAndMovePage(response , "수정에 실패하였습니다.","update");
        return "member/update";

    }


    // 회원 리스트에서 회원 정보 페이지로 이동 -> 관리자의 기능 ( 회원페이지 페이징도 추후 진행 )
    @GetMapping
    public String findByIdx(@RequestParam("memberIdx") int memberIdx , Model model , HttpSession session , HttpServletResponse response) throws IOException {
        int sessionIdx = Integer.parseInt((String)session.getAttribute("loginMemberIdx"));
        boolean matchIdx = sessionIdx == memberIdx;

        if (!matchIdx) {

            AlertUtils.alertAndMovePage(response, "해당 아이디로 로그인 먼저 하세요.", "member/login");

        } else {
            MemberDTO memberDTO = memberService.findByIdx(memberIdx);   // memberIdx 파라미터 값을 가져온 뒤 해당 domain 정보를 불러온다.
            model.addAttribute("member", memberDTO);
        } return "member/mypage";


    }

    // 꼭 로그인 후 마이페이지로 이동 ( 회원의 기능 )
    @GetMapping("mypage")
    public String mypage(Model model , HttpSession session ,
                         HttpServletResponse response) throws IOException {

        String loginEmail = (String) session.getAttribute("loginEmail");    // 세션에 저장된 이메일로 정보 가져오기
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member",memberDTO);

        if (loginEmail != null ){

            log.info("mypage data ........" + memberDTO);

            return "redirect:/member?memberIdx="+memberDTO.getMemberIdx();  // 세션에 저장된 아이디에 맞는 마이페이지로 이동
        } else{
            AlertUtils.alertAndMovePage(response, "로그인 후 마이페이지로 이동하실 수 있습니다.","/member/login");
        } return "redirect:/member/login";
    }
    
    // 로그아웃 버튼 누를 시
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";    // 로그아웃 페이지로 이동
    }

    // 회원 탈퇴 시
    @GetMapping("/delete")
    public String deleteByUser(@RequestParam ("memberIdx") int memberIdx ,HttpSession session ,
                               HttpServletResponse response) throws IOException{

        int getMemberIdx = Integer.parseInt((String) session.getAttribute("loginMemberIdx"));
        int deleteResult = memberService.deleteByUser(memberIdx);

        boolean result = getMemberIdx == memberIdx;


        if (deleteResult > 0 && result){     // 성공시
            AlertUtils.alertAndMovePage(response,"회원 탈퇴 되었습니다..","/");
        } else {

          AlertUtils.alertAndMovePage(response,"탈퇴에 실패하였습니다.","/member/mypage");
        } return "redirect:/member/mypage";


    }

/** 프로필 사진 업로드 */
    @GetMapping("/profile")
    public String profileUploadForm(@ModelAttribute("member") MemberDTO memberDTO ,
                                    @RequestParam("memberIdx") int memberIdx ,
                                    HttpSession session ,
                                    HttpServletResponse response) throws IOException{

        String loginMemberIdx = (String) session.getAttribute("loginMemberIdx");

        // 세션에 memberIdx 와 해당 회원 정보의 memberIdx 가 맞을 시 프로필 진입 가능
        if (memberIdx == Integer.parseInt(loginMemberIdx)) {
            memberService.findByIdx(memberIdx);
            return "member/profile";
        }
        AlertUtils.alert(response, "로그인 후 프로필 사진을 수정할 수 있습니다.");
        return "redirect:/member/login";
    }


    @ResponseBody
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("memberProfile") List<MultipartFile> multipartFile,
                             HttpServletRequest request , HttpSession session) {
        log.info("포스트업로드!!!!!!!!");

        // 기본적으로 JSON 객체로 "{"result":"FAIL"}" 이렇게 설정한다.
        String strResult = "{ \"result\":\"ok\" }";



        // 세션에서 memberIdx 값을 가져온다.
        int memberIdx = Integer.parseInt((String) session.getAttribute("loginMemberIdx"));
        // memberDTO 내부 정보를 idx를 통해 불러온다.
        MemberDTO memberDTO = memberService.findByIdx(memberIdx);
        log.info("업로드 객체 안에 memberDTO 정보 불러오기" + memberDTO);

        // 서버 저장 경로
        String sDirPath = uploadPath + File.separator + "uploads";


        try {
            // 파일이 있을때 탄다.
            if (multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
                log.info("파일이 있음");

                // for문을 통해 file 객체 안에 정보를 대입
                for (MultipartFile file : multipartFile) {
                    String originalFileName = file.getOriginalFilename();    //오리지날 파일명
                    String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);    //파일 확장자
                    extension = extension.toLowerCase(); // 소문자로 변경
                    String savedFileName = UUID.randomUUID() + "." + extension;    //저장될 파일 명

                    String allowedExtensions = "(jpg|jpeg|gif|png)"; // 허용되는 확장자

                    sDirPath += File.separator + savedFileName;

                    // targetFile은 굳이 필요 없지만 나중에 쓰기 위해 객체 만들어줌
                    File targetFile = new File(sDirPath);

                    // memberDTO에 savedFileName으로 저장된 파일명을 넣어줌
                    memberDTO.setMemberProfile(savedFileName);

                    log.info("파일 저장 =====" + savedFileName);
                    log.info("타겟 파일 객체 ====" + targetFile);
                    log.info("dddddddddd" +extension.matches(allowedExtensions));
                    // 현재 첨부된 파일의 확장자가 허용되는 확장자 목록에 없는 경우, 오류 메세지 반환
                    if (!extension.matches(allowedExtensions)){
                        strResult = "{ \"result\":\"FAIL\" }";
                        //파일삭제
                        FileUtils.deleteQuietly(targetFile);    //저장된 현재 파일 삭제
                    } else {
                        try {
                            // 서버에 파일 저장하기 위해 쓰는 함수.
                            // getInputStream 메서드는 multipartFile 객체에서 데이터를 읽어오기 위한 InputStream을 반환.
                            InputStream fileStream = file.getInputStream();
                            // fileStream , targetFile는 commmons 라이브러리인 FileUtils 클래스를 통해 서버에 특정 디렉토리에 저장.
                            FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
                            // uploadProfile 메서드를 이용해서 service -> repository -> mapper 순으로 DB에 update문을 통해 저장.
                            memberService.uploadProfile(memberDTO);

                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
                /*strResult = "{ \"result\":\"ok\" }";*/
            }
            // 파일 아무것도 첨부 안했을때 탄다.(게시판일때, 업로드 없이 글을 등록하는경우)
            else
                strResult = "{ \"result\":\"ok\" }";
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("strResult =======" + strResult);
        return strResult;
    }
/** 프로필 사진 업로드 끝 */

/** 스터디 가입 목록 */
    @GetMapping("/home")
    public String joinStudy(HttpServletResponse response,
                            HttpSession session , Model model , MemberDTO memberDTO
                            ) throws IOException{



        String sessionIdx =((String) session.getAttribute("loginMemberIdx"));
        String loginEmail = (String) session.getAttribute("loginEmail");


        if (sessionIdx != null) {
            List<MemberDTO> memberDTOList = memberService.findStudyByIdx(sessionIdx);
            memberDTO = memberService.findByMemberEmail(loginEmail);
            log.info("스터디그룹아이디엑스"+memberDTO.getStudyGroupIdx());

            model.addAttribute("myStudy", memberDTOList);
            model.addAttribute("member" , memberDTO);
            return "member/home";
        } else {
            AlertUtils.alertAndMovePage(response,"로그인을 해야 목록을 확인할 수 있습니다." , "login");
            return "redirect:/member/login";
        }
    }

    // 사진 출력
    @GetMapping("/image")
    public void printImage(@RequestParam(value="fileName") String fileName,
                           HttpServletResponse response)
            throws IOException{
        //서버에 저장된 이미지 경로
        String sDirPath = uploadPath + File.separator + "uploads" + File.separator + fileName;

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
}
