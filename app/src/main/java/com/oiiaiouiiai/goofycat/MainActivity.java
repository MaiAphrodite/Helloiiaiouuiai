package com.oiiaiouiiai.goofycat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isImageShowing = true;
    private ImageView imageView;
    private VideoView videoView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        videoView = findViewById(R.id.videoView);

        // Set a listener to handle the tap on the screen
        View rootView = findViewById(android.R.id.content);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageShowing) {
                    // Hide image and show video
                    imageView.setVisibility(View.GONE);
                    videoView.setVisibility(View.VISIBLE);

                    // Play the video and the music
                    // Get URI for the video file in res/raw
                    Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.catwebm);
                    videoView.setVideoURI(videoUri);
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });
                    videoView.start();

                    // Play the sound in a loop
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.catsound);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                } else {
                    // Show image and stop video and audio
                    imageView.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.GONE);

                    if (videoView.isPlaying()) {
                        videoView.stopPlayback();
                    }

                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                }

                // Toggle between image and video
                isImageShowing = !isImageShowing;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isImageShowing) {
            videoView.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release resources when the app is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}