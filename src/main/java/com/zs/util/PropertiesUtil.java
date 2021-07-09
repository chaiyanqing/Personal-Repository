package com.zs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * properties文件操作工具类
 */
public class PropertiesUtil {
	/**
	 * 读取properties文件
	 */
	public static Properties getProperties(String propertiesFilePath){
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(PropertiesUtil.class.getClassLoader().getResource(propertiesFilePath).toURI().getPath());
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}
	/**
	 * 保存properties文件
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 */
	@SuppressWarnings("resource")
	public static void saveProperties(Properties props, String propertiesFilePath) throws FileNotFoundException, URISyntaxException{
		String path = PropertiesUtil.class.getClassLoader().getResource(propertiesFilePath).toURI().getPath();
		FileOutputStream outputfile = new FileOutputStream(path);
		try {
			props.store(outputfile, "modify");
			outputfile.close();
			outputfile.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
