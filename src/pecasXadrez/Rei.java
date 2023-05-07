package pecasXadrez;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	@Override
	public boolean[][] possivelMovimento() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p =  new Posicao(0,0);
		
		//Pra cima 
		
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p)&& podeMover(p)) {
				mat[p.getLinha()][p.getColuna()]= true;
			}
		// Pra baixo
			p.setValues(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p)&& podeMover(p)) {
				mat[p.getLinha()][p.getColuna()]= true;
			}
		// esquerda
			p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p)&& podeMover(p)) {
				mat[p.getLinha()][p.getColuna()]= true;
			}
		//direita
			p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p)&& podeMover(p)) {
				mat[p.getLinha()][p.getColuna()]= true;
			}
				
		//diagonal-superior-esquerda
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p)&& podeMover(p)) {
				mat[p.getLinha()][p.getColuna()]= true;
			}
		//diagonal-superior-direita
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p)&& podeMover(p)) {
				mat[p.getLinha()][p.getColuna()]= true;
			}
		//diagonal-inferior-esquerda
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p)&& podeMover(p)) {
				mat[p.getLinha()][p.getColuna()]= true;
			}
		//diagonal-inferior-direita
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p)&& podeMover(p)) {
				mat[p.getLinha()][p.getColuna()]= true;
			}
				
				
				
				
				
			
		return mat;
		
	}
	
	

}
