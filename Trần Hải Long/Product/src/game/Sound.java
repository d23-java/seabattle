package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sound {
    private static boolean isSoundEnabled = true;
    public static List<Clip> activeClips = new ArrayList<>();

    public static void playSoundWithDuration(String soundFile, int durationMillis) {
        try {
            File soundPath = new File(soundFile);
            if(soundPath.exists() && isSoundEnabled) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                activeClips.add(clip);
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                        activeClips.remove(clip);
                    }
                });

                Thread.sleep(durationMillis);

                if (clip.isRunning()) {
                    clip.stop();
                }
                clip.close();
                activeClips.remove(clip);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Lỗi khi phát âm thanh: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Phát âm thanh bị gián đoạn.");
        }
    }

    public static void playSoundWithDurationAsync(String soundFile, int durationMillis) {
        new Thread(() -> playSoundWithDuration(soundFile, durationMillis)).start();
    }

    public static void toggleSound(){
        isSoundEnabled = !isSoundEnabled;
        if (isSoundEnabled) {
            System.out.println("Âm thanh đã được bật.");
        } else {
            System.out.println("Âm thanh đang được tắt.");
            for (Clip clip : new ArrayList<>(activeClips)) {
                if (clip.isRunning()) {
                    clip.stop();
                }
                clip.close();
                activeClips.remove(clip);
            }
        }
    }
}