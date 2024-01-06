package kr.co.sloop.notice;

public class Paging {

	public String getPagingElement(long total, int page, int row, String pageUrl){

		String pageData = "";

		int pages = (total == 0) ? 1 : (int) ((total - 1) / row) + 1;
		int blocks;
		int block;
		int firstPage;
		int lastPage;

		blocks = (int) Math.ceil(1.0 * pages / 10.0);
		block = (int) Math.ceil(1.0 * page / 10.0);
		firstPage = (block - 1) * 10 + 1;
		lastPage = block * 10;
		if (lastPage > pages)
			lastPage = pages;

		pageData = "<a href="+pageUrl+"&page=1  title=\"첫페이지\" class=\"pbtn\">[ 처음 ]</a>";

		if(block > 1){
			pageData += "<a href="+pageUrl+"&page="+(firstPage-1)+" title=\"이전페이지\" class=\"pbtn\">[ 이전 ]</a>";
		}

		for(int i=firstPage; i <= lastPage; i++){
			if(page == i) {
				pageData += "<a href="+pageUrl+"&page="+page+" title=\"<s:message code='common.page.now'/>\" class=\"numon\"><strong>[ "+page+" ]</strong></a>";
			}else{
				pageData += "<a href="+pageUrl+"&page="+i+" title=\""+i+" 페이지\" class=\"num\">[ "+i+" ]</a>";
			}
		}

		if(block < blocks){
			pageData += "<a href="+pageUrl+"&page="+(lastPage+1)+" title=\"다음페이지\" class=\"pbtn\">[ 다음 ]</a>";
		}

		pageData += "<a href="+pageUrl+"&page="+pages+" title=\"마지막페이지\" class=\"pbtn\">[ 마지막 ]</a>";

		return pageData;

	}
}
