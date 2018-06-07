package com.robot.common.utils.ftp;

import java.io.IOException;
import java.util.List;

public class test {

	public static void main(String[] args) throws Exception, IOException {
		  
			FtpUtil ftpUtil=new FtpUtil();
		    ftpUtil.connectServer("10.130.242.100", 2121, "admin", "123456", null);
		    
			List<String> fileList = ftpUtil.getFileList();
			
		    //获得ftp服务器上目录名称为DF4下的所有文件名称
		    //List<String> list=ftpUtil.getFileList("/FTPServerPath");
		   // System.out.println("文件名称列表为:"+list);
		    //上传本地D盘文件aaa.txt到服务器，服务器上名称为bbb.txt
		   // ftpUtil.uploadFile("d:" + File.separator + "aaa.txt", "eee.txt");
		    //从服务器上下载文件test.txt到本地d盘名称为atest.txt
		  // ftpUtil.download("/res/home", "e:" + File.separator +"test.docx");
		    //删除ftp服务器上文件:bbb.txt
		    //ftpUtil.deleteFile("bbb.txt");
		/*    ftpUtil.List("/");
			Iterator<String> it = ftpUtil.arFiles.iterator();  
			while(it.hasNext()){  
				System.out.println(it.next());  
			}*/
	}
	

}
