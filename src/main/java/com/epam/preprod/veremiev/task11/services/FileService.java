package com.epam.preprod.veremiev.task11.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;

import com.epam.preprod.veremiev.task11.constants.ExceptionMessages;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.files.FileManager;

/**
 * Work with files service
 * 
 * @author Illia_Veremiev
 *
 */
public class FileService {

	private static final String FILENAME_FORMAT_SPLITTER = ".";

	/**
	 * Uploads image and returns generated name
	 * 
	 * @param photoPart - photo part from request
	 * @param parentDir - dir to save photo
	 * @return - photo name
	 * @throws ServiceException if errors occurs
	 */
	public String upload(Part photoPart, String parentDir) throws ServiceException {
		if (photoPart == null || photoPart.getSize() == 0) {
			return null;
		}

		File rootFolder = new File(parentDir);
		String filename = FileManager.generateName(rootFolder) + FILENAME_FORMAT_SPLITTER
				+ FileManager.getFileFormat(photoPart.getSubmittedFileName());
		File targetFile = new File(parentDir, filename);

		try (InputStream fileContent = photoPart.getInputStream();
				OutputStream outStream = new FileOutputStream(targetFile)) {

			byte[] buffer = new byte[fileContent.available()];
			fileContent.read(buffer);
			outStream.write(buffer);
			return filename;
		} catch (IOException e) {
			throw new ServiceException(ExceptionMessages.UNABLE_TO_UPLOAD_FILE, e);
		}
	}
}
