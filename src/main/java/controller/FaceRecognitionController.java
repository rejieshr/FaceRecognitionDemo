package controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import service.FaceRecognitionService;

/**
 * Controller class which exposes the face recognition apis to detect and recognize the face of the objects.
 * 
 * @author Rejiesh
 *
 */
@RestController
@RequestMapping("/faceRecognition")
public class FaceRecognitionController {
	
	@Autowired
	FaceRecognitionService faceRecognitionService;
	
	/**
	 * This demonstrating rest API will detect and recognize whether the FaceType is present in the file passed as Request Param.
	 * @param faceType
	 * @param file
	 * @param thresholdMatchPercentage
	 * @return
	 */
	@RequestMapping(value="/{faceType}/recognizeInFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> recognizeFaceInFile(@PathVariable String faceType,
			@RequestParam("file") MultipartFile file,
			@RequestHeader(name = "threshold_match_percentage", required = false) String thresholdMatchPercentage) {
		 
		return faceRecognitionService.recognizeFaceInFile(faceType, file, thresholdMatchPercentage);
				
	}
	
	
}
