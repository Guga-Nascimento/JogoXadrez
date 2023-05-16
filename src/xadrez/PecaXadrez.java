package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;

public abstract class PecaXadrez extends Peca {
		private Cor cor;

		public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
			super(tabuleiro);
			this.cor = cor;
		}

		public Cor getCor() {
			return cor;
		}
		
		public PosicaoXadrez getPosicaoXadrez() {
			return PosicaoXadrez.daPosicao(posicao);//ok
		}
		
		
		
		protected boolean existePecaAdversaria(Posicao posicao) {
			PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
			return p != null && p.getCor() != cor;
			
		}
		
		
		
}
