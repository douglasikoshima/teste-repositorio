/**
 * 
 */
package br.com.vivo.catalogoPRS.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reinaldo.nunes
 * 
 */
public class FileHelper {

	private static final long QTD_LINHAS_SPLIT_FILE = 1800;
	
	public static List<byte[]> split(byte[] arquivo, long qtdLinhasParaSplit) {
		
		List<byte[]> fileSplited = new ArrayList<byte[]>();
		
		fileSplited.add(arquivo);
		
		return fileSplited;
		
	}
	
	public static List<byte[]> split(byte[] arquivo) {
		
		return split(arquivo, QTD_LINHAS_SPLIT_FILE);
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);
	    
	    byte[] bytes = null;
	    
	    try {
	        // Get the size of the file
	        long length = file.length();
	        
	        // You cannot create an array using a long type.
	        // It needs to be an int type.
	        // Before converting to an int type, check
	        // to ensure that file is not larger than Integer.MAX_VALUE.
	        if (length > Integer.MAX_VALUE) {
	            // File is too large
	        }
	    
	        // Create the byte array to hold the data
	        bytes = new byte[(int)length];
	    
	        // Read in the bytes
	        int offset = 0;
	        int numRead = 0;
	        while (offset < bytes.length
	               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	            offset += numRead;
	        }
	    
	        // Ensure all the bytes have been read in
	        if (offset < bytes.length) {
	            throw new IOException("Could not completely read file " + file.getName());
	        }
	    }
	    finally {
	        // Close the input stream and return bytes
	        is.close();
	    }
	    return bytes;
	}

	
}
