package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import junit.framework.Assert;
import model.FaceRecognitionResponse;
import model.Position;
import service.FaceRecognitionManager;
import service.FaceRecognitionManager.FaceType;
import service.FaceRecognitionService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FaceRecognitionServiceTests {
	
	@Mock
	FaceRecognitionManager faceRecognitionManagerMock;

	@InjectMocks
	FaceRecognitionService faceRecognitionService;
	
    MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

	
	@Test
	public void testCatFaceRecognitionFromFile() {
		FaceRecognitionResponse expectedFaceRecognitionResponse = createFaceRecognitionResponse();
		when(faceRecognitionManagerMock.recognizeFaceInFile(FaceType.CAT, firstFile, 100))
		.thenReturn(expectedFaceRecognitionResponse);
		ResponseEntity<Object> response = faceRecognitionService.recognizeFaceInFile("CAT", firstFile, "100");
		Assert.assertEquals(expectedFaceRecognitionResponse, response.getBody());
	}
	
	@Test
	public void testCatFaceRecognitionFromFile_InvalidFaceType() {
		
	}
	
	@Test
	public void testCatFaceRecognitionFromFile_NotSupportedContentType() {
		
	}
	
	@Test
	public void testCatFaceRecognitionFromFile_FileisEmpty() {
		
	}
	
	@Test
	public void testHandlingErrorThrowedByManager() {
		
	}
	
	private FaceRecognitionResponse createFaceRecognitionResponse() {
		Position position = new Position(0, 0, 100, 100);
		return  new FaceRecognitionResponse(position, 100);	
	}

}
