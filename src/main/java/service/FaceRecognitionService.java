package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.FaceRecognitionResponse;
import service.FaceRecognitionManager.FaceType;

/**
 * This Client class will map the header and params of controller to valid types and pass teh same to the Face Recognition Manager class.
 * @author rejieshr
 *
 */
@Service
public class FaceRecognitionService {
	Logger logger = LogManager.getLogger(FaceRecognitionService.class);
	
	private final long DEFAULT_THRESHOLDMATCH_PERCENTAGE = 85;
	private long thresholdMatchPercentage = 0;
	private final List<String> ALLOWED_FILE_TYPES_LIST = new ArrayList<>(Arrays.asList("text/plain"));
	
	private FaceRecognitionManager faceRecognitionManager;
	private FaceRecognitionResponse faceRecognitionResponse;
	
	public FaceRecognitionService() {
		faceRecognitionManager = new FaceRecognitionManager();
	}
	
	/**
	 * This method validate the params and makes a call to the Manager to call the corresponding FaceType implementation to call the method.
	 * @param faceType
	 * @param matrixFile
	 * @param strThresholdMatchPercentage
	 * @return
	 */
	public ResponseEntity<Object> recognizeFaceInFile(String faceType, MultipartFile matrixFile, String strThresholdMatchPercentage) {
		String contentType = matrixFile.getContentType().toLowerCase();
		FaceType type = FaceRecognitionManager.getFaceType(faceType);
		
		if (type == null) {
			logger.log(Level.ERROR, String.format("faceType is not supported %s", faceType));
			return new ResponseEntity<>(
					String.format("faceType is not supported %s", faceType), 
					HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		if (!ALLOWED_FILE_TYPES_LIST.contains(contentType)) {
			logger.log(Level.ERROR, String.format("%s contentType is not allowed only allowed types are %s", contentType, ALLOWED_FILE_TYPES_LIST.toString()));
			return new ResponseEntity<>(
					String.format("%s contentType is not allowed only allowed types are %s", contentType, ALLOWED_FILE_TYPES_LIST.toString()), 
					HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		if (matrixFile.isEmpty() || matrixFile.getSize() <= 0) {
			logger.log(Level.WARN, "File should not be empty");
			return new ResponseEntity<>(
					String.format("File  %s is empty", matrixFile.getOriginalFilename()), 
					HttpStatus.OK);
		}
		
		/**
		 * Already Handled by the Controller
		* if (matrixFile == null) { 
			logger.log(Level.ERROR, "File name cannot be null");
			return new ResponseEntity<>("File should be passed as a FormData", HttpStatus.BAD_REQUEST);
		}
		 */
		
		if (strThresholdMatchPercentage == null || strThresholdMatchPercentage.isEmpty()) {
			thresholdMatchPercentage = DEFAULT_THRESHOLDMATCH_PERCENTAGE;
			
			logger.info(String.format("ThresholdMatch percentage is null or empty, falling back to default configuration value %d", thresholdMatchPercentage));
		} else {
			thresholdMatchPercentage = Long.valueOf(strThresholdMatchPercentage);
		}
		
		logger.log(Level.INFO, "Fetching the image matrix from the file: " + matrixFile.getOriginalFilename());
		faceRecognitionResponse = faceRecognitionManager.recognizeFaceInFile(type, matrixFile, thresholdMatchPercentage);
		return new ResponseEntity<>(faceRecognitionResponse, HttpStatus.OK);
	}

}
