/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Rejiesh
 *
 */
public class ResourceHelper {
	public String readContentsFromFile(MultipartFile file) throws IOException {
		BufferedReader bufferedReader = null;
		
		String line;
		try {
			 bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			while ((line = bufferedReader.readLine()) != null)
			{
			  // do your processing       
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return file.getName();
	}

}
