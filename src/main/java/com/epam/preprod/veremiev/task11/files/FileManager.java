package com.epam.preprod.veremiev.task11.files;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

public class FileManager {

	private static final Logger log = Logger.getLogger(FileManager.class);

	public static String generateName(File folder) {
		if (!folder.exists()) {
			if (!folder.mkdirs()) {
				log.error("Unable to make dirs");
			}
		}
		int filesCount = folder.listFiles().length;
		String filename;
		do {
			filename = generateName(filesCount++);
		} while (new File(folder, filename).exists());
		return filename;
	}

	private static String generateName(int filesCount) {
		try {
			return new Hasher().hash("SHA-256", String.valueOf(filesCount));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getFileFormat(String name) {
		return FilenameUtils.getExtension(name);
	}

	public static String getComplexPath(String realPath, String folderPath) {
		return realPath + folderPath;
	}
}