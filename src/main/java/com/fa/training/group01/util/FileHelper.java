package com.fa.training.group01.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileHelper {
	public static final boolean isFileOverSize(File file, long maxiumSize) {
		return file.length() > maxiumSize;
	}

	public static final boolean isFileOverSize(MultipartFile file, long maxiumSize) {
		return file.getSize() > maxiumSize;
	}

	public static final boolean hasData(MultipartFile file) {
		return !(file == null || file.isEmpty());
	}
}
