package me.poker;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardRenderer extends MapRenderer {
    private BufferedImage image;
    private boolean done;
    public CardRenderer() {
        done = false;
    }
//    public CardRenderer(String url) {
//        load(url);
//        done = false;
//    }
    public boolean load(File file) {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            image = MapPalette.resizeImage(image);
        } catch (IOException e) {
            return false;
        }
        this.image = image;
        return true;
    }
    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        if (done) {
            return;
        }
        canvas.drawImage(0, 0, image);
        map.setTrackingPosition(false);
        done = true;
    }
}
