package com.fa.training.group01.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fa.training.group01.service.ICloudinaryService;

@Service
public class CloudinaryService implements ICloudinaryService {
	@Autowired
	private Cloudinary cloudinary;

	@Override
	public String uploadAudio(MultipartFile file) {
		try {
			Map response = cloudinary.uploader().uploadLarge(file.getBytes(),
					ObjectUtils.asMap("folder", AUDIO_FOLDER, "overwrite", false, "resource_type", "video"));
//			response.forEach((key, value) -> {
//				System.out.println(key + ":" + value);
//			});
			return (String) response.get("secure_url");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String uploadImage(MultipartFile file) {
		try {
			Map response = cloudinary.uploader().upload(file.getBytes(),
					ObjectUtils.asMap("folder", IMAGE_FOLDER, "overwrite", false, "resource_type", "image"));
			return (String) response.get("secure_url");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String uploadVideo(MultipartFile file) {
		try {
			Map response = cloudinary.uploader().uploadLarge(file.getBytes(),
					ObjectUtils.asMap("folder", VIDEO_FOLDER, "overwrite", true, "resource_type", "video"));
			return (String) response.get("secure_url");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String extractImagePublicID(String url) {
		try {
			URI uri = new URI(url);
			String path = uri.getPath();
			String fileName = path.substring(path.lastIndexOf('/') + 1);
			String fileNameWithoutExtension = FilenameUtils.removeExtension(fileName);
			return IMAGE_FOLDER + "/" + fileNameWithoutExtension;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String extractAudioPublicID(String url) {
		try {
			URI uri = new URI(url);
			String path = uri.getPath();
			String fileName = path.substring(path.lastIndexOf('/') + 1);
			String fileNameWithoutExtension = FilenameUtils.removeExtension(fileName);
			return AUDIO_FOLDER + "/" + fileNameWithoutExtension;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String extractVideoPublicID(String url) {
		try {
			URI uri = new URI(url);
			String path = uri.getPath();
			String fileName = path.substring(path.lastIndexOf('/') + 1);
			String fileNameWithoutExtension = FilenameUtils.removeExtension(fileName);
			return VIDEO_FOLDER + "/" + fileNameWithoutExtension;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteImage(String publicID) {
		try {
			Map response = cloudinary.uploader().destroy(publicID, ObjectUtils.asMap("resource_type", "image"));
			return response.get("result").equals("ok");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAudio(String publicID) {
		try {
			Map response = cloudinary.uploader().destroy(publicID, ObjectUtils.asMap("resource_type", "video"));
			return response.get("result").equals("ok");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteVideo(String publicID) {
		try {
			Map response = cloudinary.uploader().destroy(publicID, ObjectUtils.asMap("resource_type", "video"));
			return response.get("result").equals("ok");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
