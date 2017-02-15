package br.com.vivo.catalogoPRS.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.upload.FormFile;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

public class ImagemUtil {
	
	public static String salvarImagem(FormFile imagem) throws CatalogoPRSException {
		String path = null;
		try {
			String fileName;
			String caminhoImagens = ApplicationConfiguration.getCaminhoSalvarImagensModelo();
			byte[] data = imagem.getFileData();
			fileName = String.valueOf(System.currentTimeMillis());
			String suffix = imagem.getFileName().substring(imagem.getFileName().lastIndexOf("."));
			fileName += "_" + imagem.getFileName().substring(0, imagem.getFileName().lastIndexOf(".")).replaceAll("[^(0-9)|^(a-z)|^(A-Z)]", "") + suffix;
			path = caminhoImagens + fileName;
			File file = new File(path);
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			if (file.exists())
				fileName = String.valueOf(System.currentTimeMillis());
			file.createNewFile();

			FileOutputStream fo = new FileOutputStream(file);
			fo.write(data);
			fo.close();
			return fileName;
		} catch (IOException ex) {
			String message = "";
			if (path != null)
				message = "caminho: " + path;
			throw new CatalogoPRSException(message);
		}
	}
	
	public static String salvarImagem(FileItem item) throws CatalogoPRSException {
		String path = null;
		try {
			String uploadedFileName = item.getName();
			int index = uploadedFileName.lastIndexOf(File.separatorChar);
			if (index != -1) {
				uploadedFileName = uploadedFileName.substring(index + 1);
			}
			index = uploadedFileName.lastIndexOf(".");
			String caminhoImagens = ApplicationConfiguration.getCaminhoSalvarImagensModelo();
			String fileName = String.valueOf(System.currentTimeMillis()) + "_" + uploadedFileName.substring(0, index).replaceAll("[^(0-9)|^(a-z)|^(A-Z)]", "") + uploadedFileName.substring(index);
			path = caminhoImagens + fileName;
			File file = new File(path);
			file.setExecutable(true);
			file.setReadable(true);
			file.setWritable(true);
			item.write(file);
			return fileName;
		} catch (Exception ex) {
			String message = "";
			if (path != null)
				message = "caminho: " + path;
			throw new CatalogoPRSException(message);
		}
	}
	
}