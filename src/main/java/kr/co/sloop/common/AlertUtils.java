package kr.co.sloop.common;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Controller 레이어에서 View 레이어의 alert 창을 출력하기 위한 객체
public class AlertUtils {
    // contentType 설정
    public static void init(HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
    }

    // message alert 창을 출력한다.
    public static void alert(HttpServletResponse response, String message) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + message + "');</script> ");
        out.flush();
    }

    // message alert 창을 출력한 다음, nextPageUrl으로 이동(redirect)한다.
    public static void alertAndMovePage(HttpServletResponse response, String message, String nextPageUrl)
            throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + message + "'); location.href='" + nextPageUrl + "';</script> ");
        out.flush();
    }

    // message alert 창을 출력한 다음, 이전 페이지로 이동한다.
    public static void alertAndBackPage(HttpServletResponse response, String message) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + message + "'); history.go(-1);</script>");
        out.flush();
    }
}
