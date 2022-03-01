package com.fa.training.group01.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fa.training.group01.service.ICloudinaryService;

@RestController
public class FileController {
	@Autowired
	ICloudinaryService cloudinaryService;

	@RequestMapping(value = "/file/upload-audio", method = RequestMethod.POST)
	public String uploadAudio(@RequestPart(name = "file") MultipartFile file) {
		
		try {
			cloudinaryService.uploadAudio(file);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
