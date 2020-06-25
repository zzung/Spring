package com.coderby.myapp.util;

public class PagingManager {

	private int nowPage;
	private int totalPage;
	private int totalBlock;
	private int nowBlock;
	private int startPage;
	private int endPage;
	private int totalCount;
	
	public PagingManager(int totalCount, int nowPage) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		setTotalPage();
		setTotalBlock();
		setNowBlock();
		setStartPage();
		setEndPage();
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public void setTotalPage() {
		this.totalPage = (int)Math.ceil(totalCount/10.0);
	}

	public void setTotalBlock() {
		this.totalBlock = (int)Math.ceil(totalPage/10.0);
	}

	public void setNowBlock() {
		this.nowBlock = (int)Math.ceil(this.nowPage/10.0);
	}

	public void setStartPage() {
		this.startPage = (int)((this.nowBlock-1)*10+1);
	}

	public void setEndPage() {
		if(nowBlock<totalBlock) {
			this.endPage = nowBlock*10;
		}else {
			this.endPage = totalPage;
		}
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNowPage() {
		return nowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	
	
}
