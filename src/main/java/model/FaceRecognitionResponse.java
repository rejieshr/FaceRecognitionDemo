package model;

public class FaceRecognitionResponse {
	Position position;
	int thresholdMatch;
	public FaceRecognitionResponse(Position position, int thresholdMatch) {
		this.position = position;
		this.thresholdMatch = thresholdMatch;
	}
	public FaceRecognitionResponse() {
		// TODO Auto-generated constructor stub
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public int getThresholdMatch() {
		return thresholdMatch;
	}
	public void setThresholdMatch(int thresholdMatch) {
		this.thresholdMatch = thresholdMatch;
	}
	
}
