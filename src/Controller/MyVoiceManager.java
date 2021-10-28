package Controller;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class MyVoiceManager {
    public static void speak(String voiceName, String text, int volume) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice(voiceName);
        if (voice != null) {
            voice.allocate();
            voice.setPitch(110f);
            voice.setRate(130f);
            voice.setVolume(volume);
            voice.speak(text);
            voice.deallocate();
        } else {
            System.out.println("cannot load voice");
        }
    }
}
