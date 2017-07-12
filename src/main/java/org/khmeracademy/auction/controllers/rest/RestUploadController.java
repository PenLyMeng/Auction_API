package org.khmeracademy.auction.controllers.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@RestController
@RequestMapping(value="/api/v1/uploading")
public class RestUploadController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> uploading(@RequestParam("image") CommonsMultipartFile file, HttpServletRequest request) {
		//Image image = new Image();
		System.out.println("FILE=" + file.getOriginalFilename());
		
		String filename = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (!file.isEmpty()) {
			
			String originalSavePath = "/opt/images/";
			String thumbnailSavePath = "/opt/images/thumbnails/";
			try {
				filename = file.getOriginalFilename();
				
				byte[] bytes = file.getBytes();
				
				UUID uuid = UUID.randomUUID();
				
				String randomUUIDFileName = uuid.toString();
				
				String extension = filename.substring(filename.lastIndexOf(".") + 1);
				// A.jpeg
				
				System.out.println(originalSavePath);
				
				File originalPath = new File(originalSavePath);
				
				if (!originalPath.exists()) {
					System.out.println("NOT EXISTS");
					originalPath.mkdirs();
				}
				
				File thumbnailPath = new File(thumbnailSavePath);
				
				if (!thumbnailPath.exists()) {
					System.out.println("NOT EXISTS");
					thumbnailPath.mkdirs();
				}
				
				filename = randomUUIDFileName + "." + extension;
				
				// "/opt/images/" + 123e4567-e89b-12d3-a456-426655440000.jpeg
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(originalSavePath + filename)));
				stream.write(bytes);
				stream.close();

				try {
					//TODO: USING THUMBNAILS TO RESIZE THE IMAGE
					Thumbnails.of(originalSavePath + filename)
						.forceSize(24, 24)
						.toFiles(originalPath, Rename.SUFFIX_HYPHEN_THUMBNAIL);					
					
				} catch (Exception ex) {
					stream = new BufferedOutputStream(new FileOutputStream(new File(originalPath +  filename)));
					stream.write(bytes);
					stream.close();
				}
				//image.setOriginalImage("http://localhost:9999" + "/files/images/" + filename);
				//image.setThumbnailImage("http://localhost:9999" + "/files/images/" + randomUUIDFileName + "-thumbnail" + "." + extension);
				System.out.println("You successfully uploaded " + originalSavePath + filename + "!");
				//return image;
				map.put("DATA", "http://localhost:9999" + "/files/images/" + filename);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
				
			} catch (Exception e) {
				System.out.println("You failed to upload " + filename + " => " + e.getMessage());
				map.put("IMAGE_URL", originalSavePath + File.separator + filename);
				//return image;
				map.put("MESSAGE", "ERROR");
				map.put("STATUS", false);
			}
		} else {
			System.out.println("You failed to upload " + filename + " because the file was empty.");
			//return image;
			map.put("MESSAGE", "UNSUCCESS");
			map.put("STATUS", true);
		}
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
