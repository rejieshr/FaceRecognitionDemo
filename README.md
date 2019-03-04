# FaceRecognitionDemo
A PreAssignment created to demonstrate the Face Recognition API to recognize cats in the file passed as Request Param.

# Implementation Details

1.	Client sends the Image/ video frame as 2D matrix text file to the FaceRecognition webservices with threshold_match as header param.

Request Method	Request params	Content Type	Multiple Files supported	Headers	Review
Post	Multipart File	Text/Plain	Yes	Threshold_match(Optional)
Default value is set as the config file or constant	

2.	The Webservices checks for basic condition like content type and size of the content to ensure that the file is valid.

3.	The Business Layer in the webservice reads the files and send to the Face Recognition client to process the steps to detect and recognize the image based on the preset of accepted images for the object(cats).

PseudoCode Proposal:
1.	Using JavaCV library we get the frames from the Video.
2.	Each frame can be send as a frame itself or a 2D RGB/Grayscale Matrix to a Web service as a Request Param
3.	Face  Recognition Abstract class can instantiate corresponding face Recognition implementation class. Say Cat Recognition Manager implementation class will be auto wired for the Cat Face recognition API request.
4.	In the CatRecognitionManager class, a Mat class of OpneCV library will be created and then send to the Classifier which has the matrix of actual image to compare.
5.	The CatRecognitionManager class will return the FacRecognition Response object with all the Data.
6.	The Service client class will model the response as per the service response to be and will return the Response object.
7.	The Webservice business layer will get the Positions of the image to detect from the request param file and the threshold_match as an Object. Model the Response object as per the requirement and return to the Client with status code 200.
 
# Detailed Design:<br />
 1.	Api: /faceRecognition  <br />
**Description:** To compare and verify whether the Image/ Frame passed as request param is detected and recognized using preset of data.
<br />**Method**: Post
<br />**Request Body:** Passed as Multipart text file 
<br />**Headers:** Threshold_Match_Percentage
<br />**Response: **
```
[
  {
    "position": {
      "x": 0,
      "y": 0,
      "endX": 100,
      "endY": 100
    },
    "threshold": 87
  },
errors: null
  ...
  ...
  ...
  //Multiple face Recognition
]
```

<br />**2	Api: /faceRecognition/bulk**
<br />**Description:** To compare and verify whether the multiple Image/ Frame passed as request param is detected and recognized using preset of data.
Also this should be a concurrent operation to process the images/ frames concurrently and to store in a concurrent HashMap with image/frame name as the Key to the response object.
<br />**Method:** Post
<br />**Request Body:** Passed as Multipart text files
<br />**Headers:** Threshold_Match_Percentage
<br />**Response:** 
```{
  "imageFileName": [
  {
    "position": {
      "x": 0,
      "y": 0,
      "endX": 100,
      "endY": 100
    },
    "threshold": 87,
“errors” : null
  },
  ...
  ...
  ...
  //Multiple face Recognition
]
...
...
...
}
```

Error Scenarios:
1.	Input file should be on content Type Text/Plain, for other content Types, API will return 405 status code

2.	Create an Error Object to throw Business exception based on the exception thrown by the Face Recognition Client class.

References:
https://www.researchgate.net/publication/247934605_Principles_and_Methods_for_Face_Recognition_and_Face_Modelling
https://towardsdatascience.com/face-recognition-how-lbph-works-90ec258c3d6b

