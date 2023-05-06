package pecasXadrez;

import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {
	
	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "C";
	}
	@Override
	public boolean[][] possivelMovimento() {
		// TODO Auto-generated method stub
		return null;
	}

}