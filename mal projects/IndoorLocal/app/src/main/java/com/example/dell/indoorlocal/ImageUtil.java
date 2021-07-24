/**
 * <a url=http://www.jdeskew.com/>JDeskew</a>
 */
package com.example.dell.indoorlocal;

//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.geom.AffineTransform;

import android.graphics.Bitmap;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;

public class ImageUtil {

//    public static BufferedImage readImageFile(File imageFile) throws IOException {
//        return ImageIO.read(imageFile);
//    }


    public static boolean isBlack(Bitmap image, int x, int y) {
        if (image.getType() == BufferedImage.TYPE_BYTE_BINARY) {
            WritableRaster raster = image.getRaster();
            int pixelRGBValue = raster.getSample(x, y, 0);
            if (pixelRGBValue == 0) {
                return true;
            } else {
                return false;
            }
        }

        int luminanceValue = 140;
        return isBlack(image, x, y, luminanceValue);
    }

    public static boolean isBlack(Bitmap image, int x, int y, int luminanceCutOff) {
        int pixelRGBValue;
        int r;
        int g;
        int b;
        double luminance = 0.0;

        // return white on areas outside of image boundaries
        if (x < 0 || y < 0 || x > image.getWidth() || y > image.getHeight()) {
            return false;
        }

        try {
            pixelRGBValue = image.getRGB(x, y);
            r = (pixelRGBValue >> 16) & 0xff;
            g = (pixelRGBValue >> 8) & 0xff;
            b = (pixelRGBValue) & 0xff;
            luminance = (r * 0.299) + (g * 0.587) + (b * 0.114);
        } catch (Exception e) {
            // ignore.
        }

        return luminance < luminanceCutOff;
    }

}
