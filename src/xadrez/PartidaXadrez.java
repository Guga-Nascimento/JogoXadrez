package xadrez;



import java.util.ArrayList;
import java.util.List;

import pecasXadrez.Bispo;
import pecasXadrez.Cavalo;
import pecasXadrez.Peao;
import pecasXadrez.Rainha;
import pecasXadrez.Rei;
import pecasXadrez.Torre;
import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;

public class PartidaXadrez {
	
	private int Turno;
	private Cor JogadorAtual;
	private Tabuleiro tabuleiro;
	
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	
	
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		Turno = 1;
		JogadorAtual = Cor.BRANCO;
		configuracaoInicial();
	}
	
	public int getTurno() {
		return Turno;
	}

	public Cor getJogadorAtual() {
		return JogadorAtual;
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
	
	public boolean[][] possivelMovimento(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.posicionar();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possivelMovimento();
	}
	
	public PecaXadrez performaceMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoAlvo) {
		
		Posicao origem = posicaoOrigem.posicionar();
		Posicao alvo = posicaoAlvo.posicionar();
		
		validarPosicaoOrigem(origem);
		validarPosicaoAlvo(origem,alvo);
		Peca capturarPeca = fazerMover(origem,alvo);
		proximoTurno();
		return (PecaXadrez)capturarPeca;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça nessa posição");
		}
		if (JogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("Não é sua vez:");		}
		if (!tabuleiro.peca(posicao).existeUmMovimentoPossivel()) {
			throw new ExcecaoXadrez("Não existe movimeneto para essa peça");
		}
	}
	
	private void validarPosicaoAlvo(Posicao origem, Posicao alvo) {
		if (!tabuleiro.peca(origem).possivelMovimento(alvo)) {
			throw new ExcecaoXadrez("Essa peça não pode se mover para esse lugar");
		}
	}
	
	private Peca fazerMover(Posicao origem,Posicao alvo) {
		Peca p = tabuleiro.removaPeca(origem);
		Peca capturarPeca = tabuleiro.removaPeca(alvo);
		tabuleiro.lugarPeca(p, alvo);
		
		
		if (pecasCapturadas != null) {
			pecasNoTabuleiro.remove(capturarPeca);
			pecasCapturadas.add(capturarPeca);
		}
		return capturarPeca;
		}
	
	private void proximoTurno() {
		Turno++;
		JogadorAtual = (JogadorAtual == Cor.BRANCO)? Cor.PRETO:Cor.BRANCO;
	}
	
	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca ) {
		tabuleiro.lugarPeca(peca,new PosicaoXadrez(coluna,linha).posicionar());
		pecasNoTabuleiro.add(peca);
	}
	
	private void configuracaoInicial() {
		novoLugarPeca('a',1, new Torre(tabuleiro,Cor.BRANCO));
		novoLugarPeca('h',1, new Torre(tabuleiro,Cor.BRANCO));
		novoLugarPeca('a',8, new Torre(tabuleiro,Cor.PRETO));
		novoLugarPeca('h',8, new Torre(tabuleiro,Cor.PRETO));
		
		novoLugarPeca('d',1, new Rei(tabuleiro,Cor.BRANCO));
		novoLugarPeca('d',8, new Rei(tabuleiro,Cor.PRETO));
		
		novoLugarPeca('e',8, new Rainha(tabuleiro,Cor.PRETO));
		novoLugarPeca('e',1, new Rainha(tabuleiro,Cor.BRANCO));
		
		novoLugarPeca('f',1, new Bispo(tabuleiro,Cor.BRANCO));
		novoLugarPeca('c',1, new Bispo(tabuleiro,Cor.BRANCO));
		novoLugarPeca('f',8, new Bispo(tabuleiro,Cor.PRETO));
		novoLugarPeca('c',8, new Bispo(tabuleiro,Cor.PRETO));
		
		novoLugarPeca('b',1, new Cavalo(tabuleiro,Cor.BRANCO));
		novoLugarPeca('g',1, new Cavalo(tabuleiro,Cor.BRANCO));
		novoLugarPeca('g',8, new Cavalo(tabuleiro,Cor.PRETO));
		novoLugarPeca('b',8, new Cavalo(tabuleiro,Cor.PRETO));
		
		novoLugarPeca('h',7, new Peao(tabuleiro,Cor.PRETO));
		novoLugarPeca('g',7, new Peao(tabuleiro,Cor.PRETO));
		novoLugarPeca('f',7, new Peao(tabuleiro,Cor.PRETO));
		novoLugarPeca('e',7, new Peao(tabuleiro,Cor.PRETO));
		novoLugarPeca('d',7, new Peao(tabuleiro,Cor.PRETO));
		novoLugarPeca('c',7, new Peao(tabuleiro,Cor.PRETO));
		novoLugarPeca('b',7, new Peao(tabuleiro,Cor.PRETO));
		novoLugarPeca('a',7, new Peao(tabuleiro,Cor.PRETO));
		
		novoLugarPeca('a',2, new Peao(tabuleiro,Cor.BRANCO));
		novoLugarPeca('b',2, new Peao(tabuleiro,Cor.BRANCO));
		novoLugarPeca('c',2, new Peao(tabuleiro,Cor.BRANCO));
		novoLugarPeca('d',2, new Peao(tabuleiro,Cor.BRANCO));
		novoLugarPeca('e',2, new Peao(tabuleiro,Cor.BRANCO));
		novoLugarPeca('f',2, new Peao(tabuleiro,Cor.BRANCO));
		novoLugarPeca('g',2, new Peao(tabuleiro,Cor.BRANCO));
		novoLugarPeca('h',2, new Peao(tabuleiro,Cor.BRANCO));
	}
	
}
