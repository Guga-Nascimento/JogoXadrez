package pecasXadrez;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);		
	}
	
	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] possivelMovimento() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//Pra cima
		p.setValues(posicao.getLinha() - 1,posicao.getColuna());
		while (getTabuleiro().posicaoExistente(p)&& !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExistente(p)&& existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Pra Baixo
		p.setValues(posicao.getLinha() + 1,posicao.getColuna());
		while (getTabuleiro().posicaoExistente(p)&& !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExistente(p)&& existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValues(posicao.getLinha(),posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(p)&& !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(p)&& existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//direita
		p.setValues(posicao.getLinha(),posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(p)&& !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(p)&& existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		return mat;		
	}
	

}
