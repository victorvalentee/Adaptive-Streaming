package com.exotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import tv.icomp.vod.vodplayer.VodPlayer;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SimpleExoPlayerView player_view = (SimpleExoPlayerView) findViewById(R.id.player_view);
		VodPlayer vodPlayer = new VodPlayer(getApplicationContext(), player_view);

		//RandomEvaluator re = new RandomEvaluator();
		SimpleConservativeEvaluator simpleEvaluator = new SimpleConservativeEvaluator();

		vodPlayer.buildPlayer(null, C.TYPE_DASH, simpleEvaluator);
	}
}
