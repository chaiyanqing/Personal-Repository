package com.zs.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.zs.entity.ExcelWriteBean;
import com.zs.service.WriteExcelDataDelegated;
 
/**
 * Excel工具类
 * @author lp
 *
 */
public class ExcelUtil {
	
	private final static Logger logger = Logger.getLogger(ExcelUtil.class);
	
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String EMPTY = "";
	public static final String POINT = ".";
	public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");
	/**
	 * 获得path的后缀名
	 * @param path
	 * @return
	 */
	public static String getPostfix(String path){
		if(path==null || EMPTY.equals(path.trim())){
			return EMPTY;
		}
		if(path.contains(POINT)){
			return path.substring(path.lastIndexOf(POINT)+1,path.length());
		}
		return EMPTY;
	}
	/**
	 * 单元格格式
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings({ "static-access"})
	public static String getHValue(HSSFCell hssfCell){
		 if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			 return String.valueOf(hssfCell.getBooleanCellValue());
		 } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			 String cellValue = "";
			 if(HSSFDateUtil.isCellDateFormatted(hssfCell)){				
				 Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
				 cellValue = sdf.format(date);
			 }else{
				 DecimalFormat df = new DecimalFormat("#.##");
				 cellValue = df.format(hssfCell.getNumericCellValue());
				 String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
				 if(strArr.equals("00")){
					 cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
				 }	
			 }
			 return cellValue;
		 } else {
			return String.valueOf(hssfCell.getStringCellValue());
		 }
	}
	/**
	 * 单元格格式
	 * @param xssfCell
	 * @return
	 */
	public static String getXValue(XSSFCell xssfCell){
		if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		 } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			 String cellValue = "";
			 if(XSSFDateUtil.isCellDateFormatted(xssfCell)){
				 Date date = XSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue());
				 cellValue = sdf.format(date);
			 }else{
				 DecimalFormat df = new DecimalFormat("#.##");
				 cellValue = df.format(xssfCell.getNumericCellValue());
				 String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
				 if(strArr.equals("00")){
					 cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
				 }	
			 }
			 return cellValue;
		 } else {
			 xssfCell.setCellType(Cell.CELL_TYPE_STRING);
			 return String.valueOf(xssfCell.getStringCellValue());
		 }
	}	
	
	
	
	/**
     * 初始化EXCEL(sheet个数和标题)
     *
     * @param totalRowCount 总记录数
     * @param titles        标题集合
     * @return XSSFWorkbook对象
     */
	public static SXSSFWorkbook initExcel(Integer totalRowCount, String[] titles) {
        // 在内存当中保持 10 行 , 超过的数据放到硬盘中在内存当中保持 10 行 , 超过的数据放到硬盘中
        SXSSFWorkbook wb = new SXSSFWorkbook(10);
        Integer sheetCount = ((totalRowCount % ExcelConstant.PER_SHEET_ROW_COUNT == 0) ?
                (totalRowCount / ExcelConstant.PER_SHEET_ROW_COUNT) : (totalRowCount / ExcelConstant.PER_SHEET_ROW_COUNT + 1));
        logger.info("初始化EXCEL，共建" + sheetCount + "个sheet页。");
        // 根据总记录数创建sheet并分配标题
        for (int i = 0; i < sheetCount; i++) {
        	Sheet sheet = wb.createSheet("sheet" + (i + 1));
            Row headRow = sheet.createRow(0);

            for (int j = 0; j < titles.length; j++) {
                Cell headRowCell = headRow.createCell(j);
                headRowCell.setCellValue(titles[j]);
            }
        }
        return wb;
    }
	
	/**
     * 下载EXCEL
     *
     * @param wb       EXCEL对象XSSFWorkbook
     * @param response
     * @param fileName 文件名称
     * @throws IOException
     */
    public static void downLoadExcelToWebsite(SXSSFWorkbook wb, HttpServletResponse response, String fileName) throws IOException {
    	logger.info("进入下载文件");
        response.setHeader("Content-disposition", "attachment; filename=" + new String((fileName + ".xlsx").getBytes("utf-8"), "ISO8859-1"));//设置下载的文件名
        response.setContentType("application/json; charset=UTF-8");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            wb.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * 导出文件到浏览器
     * @param map 前台传入的参数集合
     * @param response
     * @param totalRowCount
     * @param fileName
     * @param titles
     * @param writeExcelDataDelegated
     * @throws Exception
     */
	public static void exportExcelToWebsite(Map<String, String> map, HttpServletResponse response, Integer totalRowCount, String fileName, String[] titles, WriteExcelDataDelegated writeExcelDataDelegated) throws Exception {
        logger.info("开始导出数据，开始时间" + ToolsUtils.formatDate(new Date(), ToolsUtils.dateFormatePattern));
        // 初始化EXCEL，根据记录总数及每一个sheet页面允许写入的行数创建sheet对象
        SXSSFWorkbook wb = ExcelUtil.initExcel(totalRowCount, titles);
        int sheetCount = wb.getNumberOfSheets();
        for (int i = 0; i < sheetCount; i++) {
            Sheet eachSheet = wb.getSheetAt(i);
            // 计算每个sheet页面写入次数
            Integer times = i < (sheetCount - 1) ? ExcelConstant.PER_SHEET_WRITE_COUNT : (totalRowCount - ExcelConstant.PER_SHEET_ROW_COUNT * i) / ExcelConstant.PER_WRITE_ROW_COUNT + 1; //直接+1可能不准确
            for (int j = 1; j <= times; j++) {
                int currentPage = i * ExcelConstant.PER_SHEET_WRITE_COUNT + j;
                int pageSize = ExcelConstant.PER_WRITE_ROW_COUNT;
                int startRowCount = (j - 1) * ExcelConstant.PER_WRITE_ROW_COUNT + 1;
                int endRowCount = startRowCount + pageSize - 1;
                int startIndex = (currentPage - 1) *  pageSize;
                // 封装委托类参数集
                ExcelWriteBean object = new ExcelWriteBean(map, eachSheet, startRowCount, endRowCount, currentPage, pageSize, startIndex);
                // 调用委托类分批写数据
                writeExcelDataDelegated.writeExcelData(object);
            }
        }
        // 下载EXCEL
        ExcelUtil.downLoadExcelToWebsite(wb, response, fileName);
        logger.info("数据导出完成，结束时间 " + ToolsUtils.formatDate(new Date(), ToolsUtils.dateFormatePattern));
    }

}
/**
 * 自定义xssf日期工具类
 * @author lp
 *
 */
class XSSFDateUtil extends DateUtil{
	protected static int absoluteDay(Calendar cal, boolean use1904windowing) {  
        return DateUtil.absoluteDay(cal, use1904windowing);  
    } 
}


/**
 * 定义批次数据量常量
 * @author Yanqing
 *
 */
class ExcelConstant {
    /**
     * 每个sheet存储的记录数 10W
     */
    public static final Integer PER_SHEET_ROW_COUNT = 100000;

    /**
     * 每次向EXCEL写入的记录数(查询每页数据大小) 2W
     */
    public static final Integer PER_WRITE_ROW_COUNT = 20000;

    /**
     * 每个sheet的写入次数 5
     */
    public static final Integer PER_SHEET_WRITE_COUNT = PER_SHEET_ROW_COUNT / PER_WRITE_ROW_COUNT;

}
