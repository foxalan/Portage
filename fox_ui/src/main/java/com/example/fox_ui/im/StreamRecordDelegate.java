package com.example.fox_ui.im;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_core.util.L;
import com.example.fox_ui.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alan on 2018/5/31.
 *
 * @author alan
 *         <p>
 *         字节流写入声音
 */

public class StreamRecordDelegate extends LatteDelegate {

    private static final String TAG = "stream record ";
    private Button mBtSteam;
    private boolean isRecording = false;
    private ExecutorService mExecutorService;

    private File mAutoFile;
    private FileOutputStream mFileOutputStream;
    private byte[] buffer;

    private static final int BUFFER_SIZE = 2 * 1024;

    private long startRecordTime;
    private long stopRecordTime;
    private AudioRecord mAudioRecord;

    @Override
    public Object getLayout() {
        return R.layout.delegate_im_recoder_stream;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        mBtSteam = rootView.findViewById(R.id.bt_stream);

        buffer = new byte[BUFFER_SIZE];
        mExecutorService = Executors.newSingleThreadExecutor();
        initEvent();
    }


    /**
     * 点击操作
     */
    private void initEvent() {
        mBtSteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRecording) {
                    //点击录音
                    //1.改变状态
                    isRecording = true;
                    //2.改变Button
                    mBtSteam.setText(String.valueOf("停止录音"));
                    mExecutorService.submit(new Runnable() {
                        @Override
                        public void run() {

                            if (!startRecord()) {
                                reportFail();
                            }
                        }
                    });

                } else {
                    //点击取消录音
                    //1.改变状态
                    isRecording = false;
                    //2.改变Button
                    mBtSteam.setText(String.valueOf("开始录音"));
                    //3.释放资源
                    releaseSource();
                    //4.得到时间
                    stopRecordTime = System.currentTimeMillis();

                    int time = (int) ((stopRecordTime - startRecordTime) / 1000);

                    L.d(TAG, "录音成功，时间为" + time + "s");
                }
            }
        });
    }

    private void releaseSource() {
        try {
            mAudioRecord.release();
            mFileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean startRecord() {

        //3.初始化录音文件
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/audio/" + System.currentTimeMillis() + ".pcm";
            mAutoFile = new File(path);
            mAutoFile.getParentFile().mkdirs();
            mAutoFile.createNewFile();
            //4.初始化FileOutPutStream
            mFileOutputStream = new FileOutputStream(mAutoFile);
            //5.初始化Record设置
            int audioSource = MediaRecorder.AudioSource.MIC;
            int sampleRate = 44100;
            int channelConfig = AudioFormat.CHANNEL_IN_MONO;
            int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
            int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);

            mAudioRecord = new AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, Math.max(minBufferSize, BUFFER_SIZE));
            //6.开始录音
            mAudioRecord.startRecording();
            //7.开始时间
            startRecordTime = System.currentTimeMillis();
            //8.开始写入到时文件
            while (isRecording) {

                int size = mAudioRecord.read(buffer, 0, BUFFER_SIZE);
                if (size > 0) {
                    mFileOutputStream.write(buffer, 0, size);
                } else {
                    return false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    /**
     * 报告错误，并重置
     */
    private void reportFail() {
        L.d(TAG, "record fail");
        isRecording = false;
        mBtSteam.setText(String.valueOf("开始录音"));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mExecutorService.shutdown();
        if (mAudioRecord != null) {
            mAudioRecord.release();
        }
    }
}
