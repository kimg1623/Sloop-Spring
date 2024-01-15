package kr.co.sloop.common;

import kr.co.sloop.study.service.StudyGroupService;
import lombok.extern.log4j.Log4j2;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Log4j2
public class MenuPreparer implements ViewPreparer {

    @Autowired
    private StudyGroupService studyGroupService;

    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {
        Map<String, Object> model = tilesContext.getContext("request"); // 사용자의 요청 얻어오기

        /* request 객체 가져오는 테스트 코드 */
        List<String> list = tilesContext.getAvailableScopes();
        System.out.println(list.size());
        for(String s : list){
            System.out.println(s);

        }
        System.out.println("model.size="+model.size());

        Map<String, Object> requestMap = tilesContext.getContext("request");

        log.info(requestMap);
        for( String strKey : requestMap.keySet() ){
            Object strValue = requestMap.get(strKey);
            System.out.println( strKey +":"+ strValue.getClass() );
        }
        System.out.println(requestMap.get("org.springframework.web.util.UrlPathHelper.PATH"));
        System.out.println(requestMap.get("studyGroupCode"));
        /* 테스트 코드 끝 */



        //model.put("data", "data"); // 타일즈 방식의 데이터 세팅 방식
        String uriPath = (String)model.get("org.springframework.web.util.UrlPathHelper.PATH");
        String [] vars = uriPath.split("/");
        String studyGroupCode = vars[2];
        System.out.println("studyGroupCode"+studyGroupCode);

        if(studyGroupCode.equals(null)) // request에서 가져오는 title이 없을 시 title 설정해주기
            System.out.println("code nulllllllllllllllllllll");

        attributeContext.putAttribute("studyGroupCode", new Attribute(studyGroupCode), true);

        String studyGroupName = studyGroupService.getGroupNameByGroupCode(studyGroupCode);
        attributeContext.putAttribute("studyGroupName", new Attribute(studyGroupName), true);

        List<HashMap<String,String>> groupBoardIdxs = studyGroupService.getBoardIdxsByGroupCode(studyGroupCode);
        attributeContext.putAttribute("groupBoardIdxs", new Attribute(groupBoardIdxs), true);



    }
    // https://lawsnland.tistory.com/52

}
