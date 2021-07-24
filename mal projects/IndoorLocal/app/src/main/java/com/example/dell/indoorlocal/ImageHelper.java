/**
 * Copyright @ 2008 Quan Nguyen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.dell.indoorlocal;

import android.graphics.Bitmap;
import android.media.Image;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

import javax.imageio.IIOImage;

/**
 * Common image processing routines.
 */
public class ImageHelper {

    /**
     * Convenience method that returns a scaled instance of the provided
     * {@code BufferedImage}.
     *
     * @param image the original image to be scaled
     * @param targetWidth the desired width of the scaled instance, in pixels
     * @param targetHeight the desired height of the scaled instance, in pixels
     * @return a scaled version of the original {@code BufferedImage}
     */
    public static Bitmap getScaledInstance(Bitmap image, int targetWidth, int targetHeight) {
        int type = (image.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        Bitmap tmp = new Bitmap(targetWidth, targetHeight, type);
        Graphics2D g2 = tmp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(image, 0, 0, targetWidth, targetHeight, null);
        g2.dispose();
        return tmp;
    }

    /**
     * Convenience method that returns a scaled instance of the provided
     * {@code IIOImage}.
     *
     * @param iioSource the original image to be scaled
     * @param scale the desired scale
     * @return a scaled version of the original {@code IIOImage}
     */
    public static IIOImage getScaledInstance(IIOImage iioSource, float scale) {
        if (!(iioSource.getRenderedImage() instanceof Bitmap)) {
            throw new IllegalArgumentException("RenderedImage in IIOImage must be BufferedImage");
        }

        if (Math.abs(scale - 1.0) < 0.001) {
            return iioSource;
        }

        Bitmap source = (Bitmap) iioSource.getRenderedImage();
        Bitmap target = getScaledInstance(source, (int) (scale * source.getWidth()), (int) (scale * source.getHeight()));
        return new IIOImage(target, null, null);
    }

    /**
     * A replacement for the standard <code>BufferedImage.getSubimage</code>
     * method.
     *
     * @param image
     * @param x the X coordinate of the upper-left corner of the specified
     * rectangular region
     * @param y the Y coordinate of the upper-left corner of the specified
     * rectangular region
     * @param width the width of the specified rectangular region
     * @param height the height of the specified rectangular region
     * @return a BufferedImage that is the subimage of <code>image</code>.
     */
    public static Bitmap getSubImage(Bitmap image, int x, int y, int width, int height) {
        int type = (image.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        Bitmap tmp = new Bitmap(width, height, type);
        Graphics2D g2 = tmp.createGraphics();
        g2.drawImage(image.getSubimage(x, y, width, height), 0, 0, null);
        g2.dispose();
        return tmp;
    }

    /**
     * A simple method to convert an image to binary or B/W image.
     *
     * @param image input image
     * @return a monochrome image
     */
    public static Bitmap convertImageToBinary(Bitmap image) {
        Bitmap tmp = new Bitmap(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2 = tmp.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return tmp;
    }


    @Deprecated
    public static Bitmap convertImage2Binary(Bitmap image) {
        return convertImageToBinary(image);
    }

    /**
     * A simple method to convert an image to gray scale.
     *
     * @param image input image
     * @return a monochrome image
     */
    public static Bitmap convertImageToGrayscale(Bitmap image) {
        Bitmap tmp = new Bitmap(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2 = tmp.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return tmp;
    }

    private static final short[] invertTable;

    static {
        invertTable = new short[256];
        for (int i = 0; i < 256; i++) {
            invertTable[i] = (short) (255 - i);
        }
    }

    /**
     * Inverts image color.
     *
     * @param image input image
     * @return an inverted-color image
     */
    public static Bitmap invertImageColor(Bitmap image) {
        Bitmap tmp = new Bitmap(image.getWidth(), image.getHeight(), image.getType());
        BufferedImageOp invertOp = new LookupOp(new ShortLookupTable(0, invertTable), null);
        return invertOp.filter(image, tmp);
    }

    /**
     * Rotates an image.
     *
     * @param image the original image
     * @param angle the degree of rotation
     * @return a rotated image
     */
    public static Bitmap rotateImage(Bitmap image, double angle) {
        double theta = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(theta));
        double cos = Math.abs(Math.cos(theta));
        int w = image.getWidth();
        int h = image.getHeight();
        int newW = (int) Math.floor(w * cos + h * sin);
        int newH = (int) Math.floor(h * cos + w * sin);

        Bitmap tmp = new Bitmap(newW, newH, image.getType());
        Graphics2D g2d = tmp.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.translate((newW - w) / 2, (newH - h) / 2);
        g2d.rotate(theta, w / 2, h / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return tmp;
    }

    /**
     * Gets an image from Clipboard.
     *
     * @return image
     */
    public static Image getClipboardImage() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            return (Image) clipboard.getData(DataFlavor.imageFlavor);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Clones an image.
     * http://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
     *
     * @param bi
     * @return
     */
    public static Bitmap cloneImage(Bitmap bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new Bitmap(cm, raster, isAlphaPremultiplied, null);
    }
}
