package generic;

public class ColorData {
	public int r, g, b, a;
	public ColorData() {
		r = g = b = 255;
		a = 255;
	}
	public ColorData(int R, int G, int B, int A) {
		r = R; g = G; b = B; a = A;
	}
	public ColorData(int[] RGBA) {
		r = RGBA[0]; g = RGBA[1]; b = RGBA[2]; a = RGBA[3];
	}
}