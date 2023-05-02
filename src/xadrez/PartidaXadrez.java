package xadrez;

import entidades.Posicao;
import entidades.Tabuleiro;
import pecasXadrez.Bispo;
import pecasXadrez.Cavalo;
import pecasXadrez.Peao;
import pecasXadrez.Rainha;
import pecasXadrez.Rei;
import pecasXadrez.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configuracaoInicial();
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	private void configuracaoInicial() {
		tabuleiro.lugarPeca(new Torre(tabuleiro,Cor.BRANCO), new Posicao(7,0));
		tabuleiro.lugarPeca(new Torre(tabuleiro,Cor.BRANCO), new Posicao(7,7));
		tabuleiro.lugarPeca(new Torre(tabuleiro,Cor.PRETO), new Posicao(0,0));
		tabuleiro.lugarPeca(new Torre(tabuleiro,Cor.PRETO), new Posicao(0,7));
		
		tabuleiro.lugarPeca(new Rei(tabuleiro,Cor.BRANCO), new Posicao(7,3));
		tabuleiro.lugarPeca(new Rei(tabuleiro,Cor.PRETO), new Posicao(0,3));
		
		tabuleiro.lugarPeca(new Rainha(tabuleiro,Cor.PRETO), new Posicao(0,4));
		tabuleiro.lugarPeca(new Rainha(tabuleiro,Cor.BRANCO), new Posicao(7,4));
		
		tabuleiro.lugarPeca(new Bispo(tabuleiro,Cor.BRANCO), new Posicao(7,2));
		tabuleiro.lugarPeca(new Bispo(tabuleiro,Cor.BRANCO), new Posicao(7,5));
		tabuleiro.lugarPeca(new Bispo(tabuleiro,Cor.PRETO), new Posicao(0,2));
		tabuleiro.lugarPeca(new Bispo(tabuleiro,Cor.PRETO), new Posicao(0,5));
		
		tabuleiro.lugarPeca(new Cavalo(tabuleiro,Cor.BRANCO), new Posicao(7,1));
		tabuleiro.lugarPeca(new Cavalo(tabuleiro,Cor.BRANCO), new Posicao(7,6));
		tabuleiro.lugarPeca(new Cavalo(tabuleiro,Cor.PRETO), new Posicao(0,1));
		tabuleiro.lugarPeca(new Cavalo(tabuleiro,Cor.PRETO), new Posicao(0,6));
		
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.PRETO), new Posicao(1,0));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.PRETO), new Posicao(1,1));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.PRETO), new Posicao(1,2));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.PRETO), new Posicao(1,3));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.PRETO), new Posicao(1,4));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.PRETO), new Posicao(1,5));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.PRETO), new Posicao(1,6));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.PRETO), new Posicao(1,7));
		
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.BRANCO), new Posicao(6,0));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.BRANCO), new Posicao(6,1));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.BRANCO), new Posicao(6,2));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.BRANCO), new Posicao(6,3));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.BRANCO), new Posicao(6,4));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.BRANCO), new Posicao(6,5));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.BRANCO), new Posicao(6,6));
		tabuleiro.lugarPeca(new Peao(tabuleiro,Cor.BRANCO), new Posicao(6,7));
	}
}
