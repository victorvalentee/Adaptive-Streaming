package com.exotest;

import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.upstream.BandwidthMeter;

import tv.icomp.vod.vodplayer.trackselector.evaluator.source.Evaluator;

/**
 * Created by victor.valente on 19/01/17.
 */
public class SimpleConservativeEvaluator implements Evaluator {

	@Override
	public int getSelectedTrack(long bufferTime, MediaChunk lastChunk, TrackGroup trackGroup,
	                            int indexLastFormat, BandwidthMeter bandwidthMeter) {

		int qualidadeRepresentacao = indexLastFormat;

		// Usa 80% da largura de banda.
		double bandaDisponível = 0.8 * bandwidthMeter.getBitrateEstimate();

		// Se estiver abaixo do limiar mínimo, usa a representação de menor qualidade.
		if (bufferTime <= 10000) {
			return 0;
		}

		// Se estiver entre o mínimo e o médio:
		// mantém, aumenta ou diminui a qualidade da próxima representação
		// de acordo com a banda disponível.
		else if (bufferTime <= 20000) {
			if (bandaDisponível < lastChunk.trackFormat.bitrate) {
				return --qualidadeRepresentacao;
			}

			else if (bandaDisponível == lastChunk.trackFormat.bitrate) {
				return qualidadeRepresentacao;
			}

			else {
				// Procurar no mapeamento de formato <-> bitrate
				// para saber se posso aumentar a qualidade.

				// Verifica se posso aumentar a qualidade.
				if (bandaDisponível > lastChunk.trackFormat.bitrate) {
					return ++qualidadeRepresentacao;
				}

				return qualidadeRepresentacao;
			}
		}

		// Se estiver acima do limiar médio.
		else {
			// Verifica se posso aumentar a qualidade.
			if (bandaDisponível > lastChunk.trackFormat.bitrate) {
				return ++qualidadeRepresentacao;
			}
			return qualidadeRepresentacao;
		}
	}
}
