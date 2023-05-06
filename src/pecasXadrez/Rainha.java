package pecasXadrez;

import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {
	
	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "D";
	}
	@Override
	public boolean[][] possivelMovimento() {
		// TODO Auto-generated method stub
		return null;
	}

}
