package com.company.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class UploadController {
	@RequestMapping("/upload")
 public String sample() {
	 return "upload";
 }
	@RequestMapping(path ="/form", method = RequestMethod.POST)
	public String upload(@RequestParam("image") CommonsMultipartFile file, HttpSession s, Model m) {
		System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getStorageDescription());
        byte[] data= file.getBytes();
        String path = s.getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"images"+File.separator+file.getOriginalFilename();
        System.out.println(path);
        try {
        	FileOutputStream fs = new FileOutputStream(path);
        	fs.write(data);
        	fs.close();
        	m.addAttribute("msg","Uploaded successfully");
        	m.addAttribute("fileName",file.getOriginalFilename());
        	System.out.println("File uploaded successfully");
        	
        }catch(Exception e){
        	e.printStackTrace();
        	System.out.println("Error in uploading");
        }
		return"success";
	}
}
