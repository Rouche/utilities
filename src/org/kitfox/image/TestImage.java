package org.kitfox.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.imgscalr.Scalr;

public class TestImage {

    public static void main(String[] args) {
        File outputfile = new File("c:\\shared\\saved.jpg");
        try {
            ImageIO.write(TestImage.base64ToBufferedImage(), "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static InputStream base64ToInputStream(String fichierBase64) throws IOException {
        return new ByteArrayInputStream(Base64.getMimeDecoder().decode(fichierBase64));
    }

    protected static BufferedImage base64ToBufferedImage() throws IOException {
        String fichierBase64 = "";
        return ImageIO.read(base64ToInputStream(fichierBase64));
    }

    public BufferedImage redimensionnerPhoto(BufferedImage image) {
        return Scalr.resize(
                image,
                Scalr.Method.QUALITY,
                Scalr.Mode.FIT_EXACT,
                600,
                800,
                Scalr.OP_ANTIALIAS);
    }
}
