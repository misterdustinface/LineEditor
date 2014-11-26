package UI;

public interface Viewport {
	float getXPosition();
	float getYPosition();
	void  setPosition(float x, float y);
	void  translatePosition(float x, float y);
	void  resetToOrigin();
}
