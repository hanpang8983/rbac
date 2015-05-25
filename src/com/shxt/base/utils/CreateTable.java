package com.shxt.base.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CreateTable {

	/** 
	 * @描述:
	 * @作者: 刘文铭
	 * @时间:2014-12-3 下午06:35:09
	 * @参数:@param args 
	 * @返回值：void
	 */

	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		cfg.configure();
		
		try {
			SchemaExport export = new SchemaExport(cfg);
			//script 是否在控制太上显示生成的SQL语句
			//export 是否在数据库当中执行前面的脚本
			export.create(true, true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
