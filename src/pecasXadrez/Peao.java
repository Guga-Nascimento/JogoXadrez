package pecasXadrez;

import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {
	
	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "P";
	}
	@Override
	public boolean[][] possivelMovimento() {
		// TODO Auto-generated method stub
		return null;
	}

}