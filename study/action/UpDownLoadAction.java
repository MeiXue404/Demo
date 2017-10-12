package com.study.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller("UpDownLoadAction")
public class UpDownLoadAction {
	//单文件上传
	@RequestMapping("/oneUpLoad")
	public String oneUpLoad(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		String path=request.getSession().getServletContext().getRealPath("/Files/demo/");
		System.out.println("打印路径:"+path);
		String fileName=file.getOriginalFilename();
		File dir=new File(path);
		if (!dir.exists()) {
			dir.mkdirs();  //若不存在则创建目录
		}
		System.out.println("文件上传路径:"+path+fileName);
		File targetFile=new File(path+"/"+fileName);
		try {
			if (!targetFile.exists()) {
				targetFile.createNewFile();
				file.transferTo(targetFile); //将文件传送到/复制到目标地址
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/Jsp/Message.jsp";
	}
	
	//多文件上传
	@RequestMapping("/moreUpLoad")
	public String moreUpLoad(HttpServletRequest request){
		MultipartHttpServletRequest mulRequest=(MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap =mulRequest.getFileMap();
		String path=request.getSession().getServletContext().getRealPath("/Files/demo/");
		System.out.println("打印路径:"+path);
		File dir=new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		List<String> fileList=new ArrayList<String>();
		for ( MultipartFile file : fileMap.values()) {
			File targetFile=new File(path+"/"+file.getOriginalFilename());
			try {
				if (!targetFile.exists()) {
					targetFile.createNewFile();
					file.transferTo(targetFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
		return "/Jsp/Message.jsp";
	}
	
	/*************1. 显示所有要下载文件的列表*********************/
	@RequestMapping("/listShow")
	 public String list(HttpServletRequest request) throws Exception {
		System.out.println("进入Action");
	  //得到upload目录路径
	  String path = request.getSession().getServletContext().getRealPath("/Files/demo/");
	  // 目录对象
	  File file = new File(path);
	  // 得到所有要下载的文件的文件名
	  String[] fileNames = file.list();  //返回目标目录里的多文件名的数组
	  
	  for (String string : fileNames) {
		System.out.println("文件名:"+string.toString());
	}
	  
	  request.setAttribute("fileNames", fileNames);
	  return "/index.jsp";
	 }
	
	//文件下载
	@RequestMapping("/fileDown")
	public void fileDown(HttpServletRequest request,HttpServletResponse response){
		
		response.setContentType("text/html;charset=utf-8");  //通知服务器发送数据时的码表
		try {  
			request.setCharacterEncoding("UTF-8");  //通知浏览器解析时使用的码表
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BufferedInputStream buin=null;
		BufferedOutputStream buou=null;
		
		String path=request.getSession().getServletContext().getRealPath("/Files/demo/1.jpg");
		try {
			long fileLength=new java.io.File(path).length();
			response.setContentType("application/x-msdownload");
		//	response.setHeader("Content-type", "text/html;charset=utf-8");
			response.setHeader("Context-length", String.valueOf(fileLength));
			//attachment为以附件方式下载 
		    response.setHeader("Content-Disposition", "attachment; filename="+"1.jpg");
		  
			buin=new BufferedInputStream(new FileInputStream(path));
			buou=new BufferedOutputStream(response.getOutputStream());
			
			
			byte[] buff=new byte[2048];
			
			int bytesRead;
			while ((bytesRead=buin.read(buff, 0, buff.length))!=-1) {
				buou.write(buff,0,bytesRead);	//将数组中的数据写入到缓冲区里，写入时最大为bytesRead个字节或者字符
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (buin!=null) {
					buin.close();
				}
				if (buou!=null) {
					buou.close();
				}
			} catch (Exception e2) {
				
			}
		}
	}
}
