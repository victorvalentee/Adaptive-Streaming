package com.exotest;

import android.util.Log;

import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.upstream.BandwidthMeter;

import java.util.Random;

import tv.icomp.vod.vodplayer.trackselector.evaluator.source.Evaluator;

/**
 * Created by victor.valente on 12/01/17.
 */
public class RandomEvaluator implements Evaluator {
	@Override
	public int getSelectedTrack(long bufferTime, MediaChunk lastChunk, TrackGroup trackGroup,
	                            int indexLastFormat, BandwidthMeter bandwidthMeter) {

		int ideal = new Random().nextInt(trackGroup.length);

		Log.d("QUALIDADE", "Formato: " + ideal);

		return ideal;
	}
}
