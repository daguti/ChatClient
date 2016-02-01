/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.threads.audio.utils;

import chatclient.StaticData;
import java.util.Vector;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;

/**
 *
 * @author ESa10969
 */
public class AudioUtils {
    public static Vector<AudioFormat> getSupportedFormats(Class<?> dataLineClass) {
    /*
     * These define our criteria when searching for formats supported
     * by Mixers on the system.
     */
    float sampleRates[] = { (float) 8000.0, (float) 16000.0, (float) 44100.0 };
    int channels[] = { 1, 2 };
    int bytesPerSample[] = { 1, 2, 4 };

    AudioFormat format;
    DataLine.Info lineInfo;

    Vector<AudioFormat> formats = new Vector<>();

    for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
        for (int a = 0; a < sampleRates.length; a++) {
            for (int b = 0; b < channels.length; b++) {
                for (int c = 0; c < bytesPerSample.length; c++) {
                    format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                            sampleRates[a], 8 * bytesPerSample[c], channels[b], bytesPerSample[c],
                            sampleRates[a], false);
                    lineInfo = new DataLine.Info(dataLineClass, format);
                    if (AudioSystem.isLineSupported(lineInfo)) {
                        /*
                         * TODO: To perform an exhaustive search on supported lines, we should open
                         * TODO: each Mixer and get the supported lines. Do this if this approach
                         * TODO: doesn't give decent results. For the moment, we just work with whatever
                         * TODO: the unopened mixers tell us.
                         */
                        if (AudioSystem.getMixer(mixerInfo).isLineSupported(lineInfo)) {
                            StaticData.audioFormat = format;
                            formats.add(format);
                        }
                    }
                }
            }
        }
    }
    return formats;
}
}
