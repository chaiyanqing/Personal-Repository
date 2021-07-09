package com.zs.entity;

import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

public class ExcelWriteBean {
	/*
	 * @param map 参数集合
     * @param eachSheet     指定SHEET
     * @param startRowCount 写入开始行
     * @param endRowCount   写入结束行
     * @param currentPage   分批查询开始页
     * @param pageSize      分批查询数据量
     * @param startIndex	查询起始索引
     */
	private Map<String, String> map;
	private Sheet eachSheet;
	private Integer startRowCount;
	private Integer endRowCount;
	private Integer currentPage;
	private Integer pageSize;
	private Integer startIndex;
	
	public ExcelWriteBean(Map<String, String> map, Sheet eachSheet, Integer startRowCount, Integer endRowCount, Integer currentPage, Integer pageSize, Integer startIndex) {
		this.map =  map;
		this.eachSheet = eachSheet;
		this.startRowCount = startRowCount;
		this.endRowCount = endRowCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.startIndex = startIndex;
	}
	
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public Sheet getEachSheet() {
		return eachSheet;
	}
	public void setEachSheet(Sheet eachSheet) {
		this.eachSheet = eachSheet;
	}
	public Integer getStartRowCount() {
		return startRowCount;
	}
	public void setStartRowCount(Integer startRowCount) {
		this.startRowCount = startRowCount;
	}
	public Integer getEndRowCount() {
		return endRowCount;
	}
	public void setEndRowCount(Integer endRowCount) {
		this.endRowCount = endRowCount;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	
}
