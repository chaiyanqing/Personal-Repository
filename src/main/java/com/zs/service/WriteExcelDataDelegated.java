package com.zs.service;

import com.zs.entity.ExcelWriteBean;

public interface WriteExcelDataDelegated {
	 /**
     * EXCEL写数据委托类  针对不同的情况自行实现
     * @throws Exception
     */
    abstract void writeExcelData(ExcelWriteBean excelWriteBean) throws Exception;

}
