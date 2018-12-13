package jrtt.imust.com.jrtt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URI;
import java.net.URL;

/*
VideoView
    Video表示的视频,VideoView是一个包含播放视频功能的控制
    * 不需要我们编写视频播放代码
    * 以前
    SurfaceView:显示视频画面
    MediaPlayer:1，可以将视频音频里面的声音读出来
                2,如果是视频，还可以将画面读出来
                3，给SurfaceView显示
    * 掌握
        如果想让VideoView显示一个视频，必须告诉它视频的位置
            *网络：http://
            * 本地的视频
                    res
                    android.resource://包名/视频id
 */
public class SplahActivity extends AppCompatActivity {
    private boolean isClickClose = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);
        //1,复制一个视频到res/raw文件夹
        //2.视频的路径表示
        String mp4 = "android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri  uri = Uri.parse(mp4);
        //3.先在 layout布局文件中配置一个 VideoView,再找出来
        VideoView videoView = findViewById(R.id.video_view);
        setFullWidth(videoView);
        //6.如果视频播放结束，关闭这个页面进到主页
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isClickClose= true;//表示你是点击按钮关闭页面
                Intent intent = new Intent(SplahActivity.this,MainActivity.class);
                startActivity(intent);
                finish();;
            }
        });
        //4.将视频给 VideoView播放
        videoView.setVideoURI(uri);
        //5.把播放方法执行
        videoView.start();

        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClickClose){
                    //什么都不做
                }else{
                    Intent intent = new Intent(SplahActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    public void setFullWidth(VideoView videoView) {
        //如果视频画面没有沾满屏幕，只能重新计算一下屏幕的宽高
        //Metrics标尺
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int heigh = metrics.heightPixels;
        //创建LayoutParams:用代码来设置宽高的一个对象
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,heigh);
        //再设置给VideoView
        videoView.setLayoutParams(params);
    }
}
