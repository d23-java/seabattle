package system;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Audio {
    public static void playSound(String filePath) {
        try {
            // Lấy tài nguyên âm thanh từ thư mục resources
            URL soundURL = Audio.class.getResource("/" + filePath);  // Đảm bảo có dấu "/" trước tên file
            if (soundURL != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } else {
                System.out.println("Không tìm thấy file âm thanh.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

