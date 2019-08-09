package org.kitfox.cracking;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Jean-Francois Larouche (resolutech) on 2019-08-06
 */
@Slf4j
public class Question5_8 {

    @Test
    public void testDrawLine() {
        byte[] screen = new byte[4 * 32];
        drawLine(screen, 32, 0, 31, 31);

        drawScreen(screen, 32);
    }

    private void drawLine(byte[] screen, int width, int x1, int x2, int y) {

        if (x1 >= x2 || x1 >= width) {
            return;
        }
        if (x2 >= width) {
            x2 = width - 1;
        }

        int byteHeight = screen.length / width;

        int offset = y * byteHeight;

        int indexX1 = x1 / 8 + offset;
        int indexX2 = x2 / 8 + offset;

        int switchLeft = x1 % 8;
        // 7 because of off by one.
        int switchRight = 7 - x2 % 8;

        int all1 = ~0;

        if (indexX1 == indexX2) {
            //Same byte
            int line = all1 >>> (switchLeft + switchRight + 24);
            line <<= switchRight;
            screen[indexX1] = (byte) line;
            return;
        }

        int startLine = all1 >>> (switchLeft + 24);
        int endLine = all1 << switchRight;

        //Dont erase already drawn line
        screen[indexX1] |= (byte) startLine;
        screen[indexX2] |= (byte) endLine;

        for (int i = indexX1 + 1; i < indexX2; ++i) {
            screen[i] = (byte) all1;
        }
    }

    private void drawScreen(byte[] screen, int width) {

        StringBuilder line = new StringBuilder(width);
        int byteWidth = width / 8;
        int height = screen.length / byteWidth;
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < byteWidth; ++x) {
                int mask = 0x00000080;
                for (int b = 0; b < 8; ++b) {
                    byte pixel = (byte) (screen[x + y * byteWidth] & (byte) mask);
                    line.append(pixel != 0 ? 1 : 0);
                    mask >>>= 1;
                }
            }
            log.info(line.toString());
            line = new StringBuilder(width);
        }

    }

}
