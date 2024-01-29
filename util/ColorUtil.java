package moonlight.util;

import java.awt.Color;

public class ColorUtil {

	public static int getRainbow(float seconds, float saturation, float brightness) {
		//float seconds = 3.5f;
		float hue = ((System.currentTimeMillis() % (int)(seconds * 1000)) / (float)(seconds * 1000f));
		int rgbColor= Color.HSBtoRGB(hue, saturation, 1);
		return rgbColor;
	}
	
	public static int getRainbow(float seconds, float saturation, float brightness, float index) {
		//float seconds = 3.5f;
		float hue = ((System.currentTimeMillis() + (long)index) % (int)(seconds * 1000)) / (float)(seconds * 1000);
		int rgbColor= Color.HSBtoRGB(hue, saturation, brightness);
		return rgbColor;
	}

}
