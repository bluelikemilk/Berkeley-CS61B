package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    public static void addHexagon(TETile[][] tiles, int i, int j, int s) {
        int width = 3 * s - 2;
        int height = 2 * s;
        // left bottom corner of the hex is (i,j)
        for (int x = i; x < i + width; x += 1) {
            for (int y = j; y < j + height; y += 1) {
                tiles[x][y] = Tileset.TREE;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexWorld = new TETile[WIDTH][HEIGHT];
        addHexagon(hexWorld, 2, 2, 3);

        ter.renderFrame(hexWorld);
    }
}
