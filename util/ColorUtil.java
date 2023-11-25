package moonlight.util;

import java.awt.Color;

public class ColorUtil {

	public static int getRainbow(float seconds, float saturation, float brightness) {
		//float seconds = 3.5f;
		float hue = ((System.currentTimeMillis() % (int)(seconds * 1000)) / (seconds * 1000f)) % 1;
		int rgbColor= Color.HSBtoRGB(hue, saturation, 1);
		return rgbColor;
	}

}
