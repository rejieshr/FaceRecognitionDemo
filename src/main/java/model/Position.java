package model;

public class Position {
	int x;
	int y;
	int endX;
	int endY;
	public Position(int x, int y, int endX, int endY) {
		this.x = x;
		this.y = y;
		this.endX = endX;
		this.endY  = endY;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getEndX() {
		return endX;
	}
	public void setEndX(int endX) {
		this.endX = endX;
	}
	public int getEndY() {
		return endY;
	}
	public void setEndY(int endY) {
		this.endY = endY;
	}
}
