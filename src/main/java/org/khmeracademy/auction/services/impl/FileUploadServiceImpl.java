package org.khmeracademy.auction.services.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.khmeracademy.auction.entities.UploadedFileInfo;
import org.khmeracademy.auction.services.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/***
 * Create Date: 8/8/2016 02:00PM
 * @author PHEARUN
 */

@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	@Override
	public UploadedFileInfo upload(List<MultipartFile> files, String folder, HttpServletRequest request) {
		
		String fileName = "";
		UploadedFileInfo fileUpload = new UploadedFileInfo();
		
		if(files.isEmpty()){
			fileUpload.setMessage("File is not present! Please choose file to upload!!!");
			
		}else{
			
			if(folder=="" || folder==null)
				folder = "default";
			String UPLOAD_PATH = "/opt/AUCTION/" + folder;
			
			java.io.File path = new java.io.File(UPLOAD_PATH);
			if(!path.exists())
				path.mkdirs();
			
			List<String> names = new ArrayList<>();
			for(MultipartFile file: files){
				fileName = file.getOriginalFilename();
				fileName = UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
				try {
					Files.copy(file.getInputStream(), Paths.get(UPLOAD_PATH, fileName));
					names.add("http://" + request.getRemoteAddr()+ ":" + request.getServerPort() + "/resources/" + folder + "/" + fileName);
				} catch (Exception e) { 
					fileUpload.setMessage("Fail to upload!, " + e.getMessage());
				}
			}
			fileUpload.setProjectPath("/resources/" + folder);
			fileUpload.setServerPath(UPLOAD_PATH);
			fileUpload.setNames(names);
			fileUpload.setMessage("File has been uploaded successfully!!!");
		}
		return fileUpload;
	}

	@Override
	public UploadedFileInfo uploadSingle(MultipartFile file, String folder, HttpServletRequest request) {
		List<MultipartFile> files = new ArrayList<>();
		files.add(file);
		return this.upload(files, folder, request);
	}

}
