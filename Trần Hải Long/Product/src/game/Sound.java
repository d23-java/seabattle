package game;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Sound {
    private static boolean isSoundEnabled = true;

    public static List<Clip> activeClips = new CopyOnWriteArrayList<>();

    public static void playSoundWithDuration(String soundFile, boolean replay, int durationMillis, float volume) {
        if (!isSoundEnabled) {
            //System.out.println("Âm thanh đang bị tắt. Không phát âm thanh.");
            return;
        }

        try {
            InputStream audioSrc = Sound.class.getResourceAsStream(soundFile);
            if (audioSrc == null) {
                System.out.println("Không tìm thấy tài nguyên âm thanh: " + soundFile);
                return;
            }
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            setVolume(clip, volume);
            clip.start();
            activeClips.add(clip);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    if(!replay) {
                        clip.close();
                        activeClips.remove(clip);
                        //System.out.println("Đã dừng và đóng clip: " + soundFile);
                    }
                    else{
                        clip.setFramePosition(0);
                        clip.start();
                    }
                }
            });

            Thread.sleep(durationMillis);

            if (clip.isRunning()) {
                clip.stop();
                //System.out.println("Đã dừng clip sau thời gian xác định: " + soundFile);
            }
            clip.close();
            activeClips.remove(clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Lỗi khi phát âm thanh: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Phát âm thanh bị gián đoạn.");
        }
    }

    public static void playSoundWithDurationAsync(String soundFile, boolean replay, int durationMillis, float volume) {
        new Thread(() -> playSoundWithDuration(soundFile, replay, durationMillis, volume)).start();
    }

    public static void toggleSound(){
        isSoundEnabled = !isSoundEnabled;
        if (isSoundEnabled) {
            System.out.println("Âm thanh đã được bật.");
        } else {
            System.out.println("Âm thanh đang được tắt.");
            stopAllClips();
        }
    }

    public static void stopAllClips() {
        for (Clip clip : activeClips) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
            activeClips.remove(clip);
            //System.out.println("Đã đóng clip: " + clip);
        }
    }

    private static void setVolume(Clip clip, float volume) {
        if (clip == null) return;

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        float dB = (float) (20.0 * Math.log10(volume));
        gainControl.setValue(dB);
    }
}