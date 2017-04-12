package com.communicationapp.test.opusdecode;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FACHRUL_AUDIO_DECODE";

    //android helper class
    private MediaCodec mediaCodec;
    private MediaFormat mediaFormat;

    //main source
    private Resources audioResources;

    //decoding parameter
    //The speed at which these samples are taken
    private final int SAMPLE_RATE = 16000;
    private final int FRAME_SIZE = 960;
    private final int ENCODE_SIZE = 133;
    private final int FRAME_DURATION = 60;
    private final int NUMBER_OF_CHANNEL = 1;
    private final int BUFFER_SIZE_FACTOR = 2;
    private final String CHANNEL_CONFIG = "Mono";
    private final String AUDIO_FORMAT = "PCM";
    private final int AUDIO_FORMAT_BIT = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setUpMediaDecoder(){
        try {
            this.mediaFormat = new MediaFormat();
            this.mediaFormat.setString(MediaFormat.KEY_MIME, MediaFormat.MIMETYPE_AUDIO_AMR_NB);
            this.mediaFormat.setInteger(MediaFormat.KEY_SAMPLE_RATE, SAMPLE_RATE);
            this.mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, FRAME_SIZE);
            this.mediaFormat.setInteger(MediaFormat.KEY_DURATION, FRAME_DURATION);
            this.mediaFormat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, NUMBER_OF_CHANNEL);
            this.mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, AUDIO_FORMAT_BIT);

            String mime = MediaFormat.KEY_MIME;

            this.mediaCodec = MediaCodec.createDecoderByType(mime);

            this.mediaCodec.configure(mediaFormat, null, null, 0);
            this.mediaCodec.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decodeAudio(int sourceInput, int sourceMaster, float maxerror) throws IOException {

        //read master file into memory
        AssetFileDescriptor masterFD = audioResources.openRawResourceFd(sourceMaster);
        long masterLength = masterFD.getLength();
        short[] masterBuffer = new short[(int) (masterLength / 2)];
        InputStream inputStream = masterFD.createInputStream();
        BufferedInputStream buffInputStream = new BufferedInputStream(inputStream);




    }
}
