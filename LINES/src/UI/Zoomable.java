package UI;

public interface Zoomable {
	float getZoom();
	void  setZoom(float ZOOM);
	void  increaseZoom(float delta);
	void  decreaseZoom(float delta);
	void  resetToDefaultZoom();
}
