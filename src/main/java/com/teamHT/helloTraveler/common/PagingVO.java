package com.teamHT.helloTraveler.common;

public class PagingVO {
	private int cntPage;
	private int startPage;
	private int endPage;
	private int total;
	private int cntPerPage;
	private int lastPage;
	private int start;
	private int end;
	private int pageSize = 10; // 화면 하단에 출력할 페이지 사이즈
	
	public PagingVO() {}
	
	// 페이징 처리해야하는 부분
	public PagingVO(int total, int cntPage) {
		setCntPage(cntPage);
		setTotal(total);
		setCntPerPage(20); // 한 페이지에 보이는 갯수
		calLastPage(getTotal(), getCntPerPage());
		calStartEndPage(getCntPage(), pageSize);
		calcStartEnd(getCntPage());
	}
	
	// 제일 마지막 페이지 계산
	public void calLastPage(int total, int cntPerPage) {
		setLastPage((int)Math.ceil((double)total / (double)cntPerPage));
	}
	
	// 시작, 끝 페이지 계산
	public void calStartEndPage(int cntPage, int pageSize) {
		setEndPage((int)Math.ceil((double)cntPage / (double)pageSize) * pageSize);
		if(getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		
		setStartPage(getEndPage() -pageSize + 1);
		if(getStartPage() < 1) {
			setStartPage(1);
		}
	}
	
	public void calcStartEnd(int cntPage) {
		setEnd(cntPage * getCntPerPage());
		setStart(getEnd() - getCntPerPage() + 1);
	}
	//
	
	public int getCntPage() {
		return cntPage;
	}
	
	public void setCntPage(int cntPage) {
		this.cntPage = cntPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getCntPerPage() {
		return cntPerPage;
	}
	
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	
	public int getLastPage() {
		return lastPage;
	}
	
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PagingVO [cntPage=" + cntPage + ", startPage=" + startPage + ", endPage=" + endPage + ", total=" + total
				+ ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end + "]";
	}
}
