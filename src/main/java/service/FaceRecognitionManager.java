package service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.FaceRecognitionResponse;
import model.Position;

@Service
public class FaceRecognitionManager {
	public enum FaceType {
		CAT,
		OTHER
	}
	
	public FaceRecognitionManager() {
//		FaceType type = FaceType.valueOf(faceType.toUpperCase());
//		switch(type) {
//		case CAT:
//			break;
//		default:
//			
//		}
	}
	
	public static FaceType getFaceType(String faceType) {
		try {
			if (faceType != null || !faceType.isEmpty()) {
				return FaceType.valueOf(faceType.toUpperCase());
			}
		} catch(Exception e) {
			return null;
		}
		return null;
		
	}
	
	public FaceRecognitionResponse recognizeFaceInFile(FaceType faceType, MultipartFile file, long thresholdMatchPercentage) {
		FaceRecognitionResponse faceRecognitionResponse = null;
		
		if (faceType == FaceType.CAT) {
			faceRecognitionResponse =  this.recognizeCatsInFile(file, thresholdMatchPercentage);
		}
		return faceRecognitionResponse;
		
	}
	
	public FaceRecognitionResponse recognizeCatsInFile(MultipartFile file, long thresholdMatchPercentage) {
		Position position = new Position(0, 0, 100, 100);
		return  new FaceRecognitionResponse(position, 100);
	}
}
