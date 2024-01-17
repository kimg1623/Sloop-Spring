<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<link href="/resources/css/style_studygroup.css" rel="stylesheet">

<!-- MenuPreparer : tiles에서 인터셉터하여 속성 주입 -->
<tiles:importAttribute name="studyGroupCode"/>
<tiles:importAttribute name="studyGroupName"/>
<tiles:importAttribute name="groupBoardIdxs"/>

<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-title" aria-current="page" href="/study/${studyGroupCode}">
                    <span data-feather="home"></span>
                    ${studyGroupName}
                </a>
                <div class="sidebar-division-line"></div>
            </li>
            <li class="nav-item">

                <a class="nav-link active" aria-current="page" href="/study/${studyGroupCode}/notice/${groupBoardIdxs[0].boardIdx}/list">

                    <span data-feather="home"></span>
                    ${groupBoardIdxs[0].categoryName}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/study/${studyGroupCode}/postassignment/${groupBoardIdxs[1].boardIdx}/list">
                    <span data-feather="file"></span>
                    ${groupBoardIdxs[1].categoryName}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/study/${studyGroupCode}/postforum/${groupBoardIdxs[2].boardIdx}/list">
                    <span data-feather="shopping-cart"></span>
                    ${groupBoardIdxs[2].categoryName}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/study/${studyGroupCode}/daily/${groupBoardIdxs[3].boardIdx}/list">
                    <span data-feather="users"></span>
                    ${groupBoardIdxs[3].categoryName}
                </a>
            </li>
            <li class="nav-item nav-item-last">
                <a class="nav-link" href="/study/${studyGroupCode}/manage/info">
                    <span data-feather="bar-chart-2"></span>
                    스터디 관리
                    <svg width="18" height="18" viewBox="0 0 21 21" fill="none"
                         xmlns="http://www.w3.org/2000/svg">
                        <path d="M10.5095 8.0103C9.8963 8.0103 9.32208 8.24819 8.88732 8.68296C8.4546 9.11772 8.21466 9.69194 8.21466 10.3051C8.21466 10.9183 8.4546 11.4925 8.88732 11.9273C9.32208 12.36 9.8963 12.6 10.5095 12.6C11.1227 12.6 11.6969 12.36 12.1317 11.9273C12.5644 11.4925 12.8043 10.9183 12.8043 10.3051C12.8043 9.69194 12.5644 9.11772 12.1317 8.68296C11.9193 8.46897 11.6665 8.29932 11.3881 8.18384C11.1096 8.06837 10.811 8.00938 10.5095 8.0103V8.0103ZM18.9649 12.8399L17.6236 11.6935C17.6872 11.3039 17.72 10.906 17.72 10.5102C17.72 10.1144 17.6872 9.7145 17.6236 9.3269L18.9649 8.18052C19.0662 8.09378 19.1387 7.97826 19.1728 7.84931C19.2068 7.72037 19.2008 7.5841 19.1556 7.45864L19.1371 7.40532C18.768 6.37314 18.2149 5.41638 17.5047 4.5814L17.4678 4.53833C17.3815 4.43692 17.2666 4.36403 17.1381 4.32925C17.0096 4.29447 16.8736 4.29944 16.748 4.34351L15.0827 4.93618C14.4675 4.43169 13.7825 4.03384 13.0401 3.75698L12.7182 2.01587C12.6939 1.88471 12.6303 1.76404 12.5358 1.6699C12.4412 1.57576 12.3203 1.5126 12.1891 1.48882L12.1337 1.47856C11.0673 1.28579 9.94347 1.28579 8.87706 1.47856L8.82169 1.48882C8.69044 1.5126 8.56952 1.57576 8.47501 1.6699C8.38051 1.76404 8.31688 1.88471 8.29259 2.01587L7.96857 3.76519C7.23321 4.04425 6.54818 4.44114 5.94034 4.94028L4.26281 4.34351C4.13723 4.29909 4.00111 4.29394 3.87253 4.32874C3.74396 4.36354 3.62902 4.43664 3.54298 4.53833L3.50607 4.5814C2.7971 5.41727 2.24416 6.3738 1.87365 7.40532L1.85519 7.45864C1.7629 7.71499 1.83878 8.0021 2.04591 8.18052L3.40353 9.33921C3.33995 9.72476 3.30919 10.1185 3.30919 10.5082C3.30919 10.9019 3.33995 11.2957 3.40353 11.6771L2.05001 12.8358C1.9487 12.9225 1.87618 13.038 1.84211 13.167C1.80804 13.2959 1.81404 13.4322 1.85929 13.5577L1.87775 13.611C2.24894 14.6425 2.7965 15.5961 3.51017 16.4349L3.54708 16.478C3.63333 16.5794 3.74827 16.6523 3.87677 16.6871C4.00527 16.7218 4.14129 16.7169 4.26691 16.6728L5.94445 16.076C6.55558 16.5785 7.23644 16.9763 7.97267 17.2511L8.29669 19.0004C8.32098 19.1316 8.38461 19.2523 8.47912 19.3464C8.57362 19.4406 8.69454 19.5037 8.82579 19.5275L8.88116 19.5377C9.95806 19.7316 11.0609 19.7316 12.1378 19.5377L12.1932 19.5275C12.3244 19.5037 12.4453 19.4406 12.5399 19.3464C12.6344 19.2523 12.698 19.1316 12.7223 19.0004L13.0442 17.2593C13.7866 16.9804 14.4716 16.5846 15.0868 16.0801L16.7521 16.6728C16.8776 16.7172 17.0138 16.7224 17.1423 16.6876C17.2709 16.6528 17.3859 16.5797 17.4719 16.478L17.5088 16.4349C18.2225 15.592 18.77 14.6425 19.1412 13.611L19.1597 13.5577C19.2479 13.3034 19.172 13.0183 18.9649 12.8399ZM10.5095 13.9104C8.51818 13.9104 6.90421 12.2964 6.90421 10.3051C6.90421 8.31382 8.51818 6.69985 10.5095 6.69985C12.5008 6.69985 14.1148 8.31382 14.1148 10.3051C14.1148 12.2964 12.5008 13.9104 10.5095 13.9104Z"
                              fill="#BDBDBD"></path>

                </a>
            </li>
        </ul>


    </div>
</nav>

