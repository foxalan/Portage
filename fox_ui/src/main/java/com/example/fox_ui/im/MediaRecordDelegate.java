package com.example.fox_ui.im;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_core.util.L;
import com.example.fox_ui.R;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author alan
 */

public class MediaRecordDelegate extends LatteDelegate {

    private static final String TAG = "Record";
    private TextView mTvRecord;
    private Button mBtnListen;
    private TextView mTvResult;

    private int state = 0;

    private ExecutorService mExecutorService;

    private MediaRecorder mMediaRecorder;
    private File mAudioFile;
    private String mFilePath;

    private long startRecordTime;
    private long stopRecordTime;

    private boolean isPlaying;
    private MediaPlayer mMediaPlayer;

    @Override
    public Object getLayout() {
        return R.layout.delegate_im_recoder_file;
    }

    @Override
    public void onBindView(View rootView) throws Exception {

        mTvRecord = rootView.findViewById(R.id.tv_recoder_file);
        mBtnListen = rootView.findViewById(R.id.bt_listen);
        mTvResult = rootView.findViewById(R.id.tv_result);

        mExecutorService = Executors.newSingleThreadExecutor();

        init();
    }

    private void init() {
        mTvRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startRecord();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        stopRecord();
                        break;
                    default:
                        break;
                }
                //这里一定是True
                return true;
            }
        });

        mBtnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //检查是否正在播放
                if (!isPlaying){
                    //检查文件是否存在
                    if (mAudioFile!=null){
                        //初始化MediaPlayer
                        mExecutorService.submit(new Runnable() {
                            @Override
                            public void run() {
                                playRecord(mAudioFile);
                            }
                        });

                    }
                }
            }
        });


    }

    /**
     * 播放失败
     */
    private void playFail() {
       L.e(TAG,"播放失败");
       isPlaying = false;
       releaseMediaPlayer();

    }


    private void playRecord(File mAudioFile) {

        try {
            //初始化MediaPlayer
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(mAudioFile.getAbsolutePath());

            //设置回调
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    L.e(TAG,"play completion");
                    isPlaying = false;
                }
            });

            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    playFail();
                    return false;
                }
            });

            //设置声道，循环
            mMediaPlayer.setLooping(false);
            mMediaPlayer.setVolume(1,1);

            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 停止录音
     */
    private void stopRecord() {
        L.e("关闭录音");
        state = 0;
        changeView();

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {

                if (!doStop()){

                }
                releaseRecord();
            }
        });
    }

    /**
     * 开始录音
     */
    private void startRecord() {
        L.e("开始录音");
        state = 1;
        changeView();

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {

                releaseRecord();
                if (!doStart()) {
                    L.e(TAG,"record io ex");
                }


            }
        });


    }

    /**
     * 停止录音
     *
     * @return
     */
    private boolean doStop() {
        L.e(TAG,"stop");
        //1.停止录音
        mMediaRecorder.stop();
        //2.记录时间
        stopRecordTime = System.currentTimeMillis();
        int time = (int) ((stopRecordTime-startRecordTime)/1000);
        mTvResult.setText(String.valueOf("录制成功"+time+"s"));
        return false;
    }

    /**
     * 开始录音
     *
     * @return
     */
    private boolean doStart() {


        try {

            //1.实例化MediaRecord
            mMediaRecorder = new MediaRecorder();
            //2.创建文件
            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/alanDemo/"+System.currentTimeMillis()+
                    "record.m4a";
            mAudioFile = new File(mFilePath);
            mAudioFile.getParentFile().mkdirs();

            mAudioFile.createNewFile();

            //3.设置MediaRecord

            //设置麦克疯采集
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //保存文件为MP4
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //设置采样
            mMediaRecorder.setAudioSamplingRate(44100);
            //通用的AAC编码格式
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            //音质比较好频率
            mMediaRecorder.setAudioEncodingBitRate(96000);
            mMediaRecorder.setOutputFile(mAudioFile.getAbsolutePath());
            L.e(TAG,mAudioFile.getAbsolutePath());
            //4.开始录音
            mMediaRecorder.prepare();
            mMediaRecorder.start();

            //5.设置录音时间
            startRecordTime = System.currentTimeMillis();


        } catch (IOException e) {

            e.printStackTrace();
            L.e(e.toString());

            return false;
        }

        return true;
    }


    /**
     * 释放资源
     */
    private void releaseRecord() {
        if (mMediaRecorder!=null){
            mMediaRecorder.release();
        }
    }

    /**
     * 改变文字
     */
    private void changeView() {

        switch (state) {
            case 0:
                mTvRecord.setText(String.valueOf("开始录音"));
                mTvRecord.setBackgroundResource(R.drawable.bg_im_recoder_file_tv_normal);
                break;
            case 1:
                mTvRecord.setText(String.valueOf("停止录音"));
                mTvRecord.setBackgroundResource(R.drawable.bg_im_recoder_file_tv_pressed);
                break;
            default:
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mExecutorService.shutdown();
        releaseRecord();

        releaseMediaPlayer();


    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer!=null){
            mMediaPlayer.setOnErrorListener(null);
            mMediaPlayer.setOnCompletionListener(null);

            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
        }
    }
}
