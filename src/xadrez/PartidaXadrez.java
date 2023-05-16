package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> capturedPieces = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		configuracaoInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possivelMovimento(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.posicionar();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possivelMovimento();
	}
	
	public PecaXadrez performaceMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoAlvo) {
		Posicao origem = posicaoOrigem.posicionar();
		Posicao alvo = posicaoAlvo.posicionar();
		validarPosicaoOrigem(origem);
		validarPosicaoAlvo(origem, alvo);
		Peca capturedPiece = fazerMover(origem,alvo);
		
		if (testCheck(jogadorAtual)) {
			anularMovimento(origem,alvo, capturedPiece);
			throw new ExcecaoXadrez("You can't put yourself in check");
		}
		
		check = (testCheck(oponente(jogadorAtual))) ? true : false;
		
		proximoTurno();
		return (PecaXadrez)capturedPiece;
	}
	
	private Peca fazerMover(Posicao origem, Posicao alvo) {
		Peca p = tabuleiro.removaPeca(origem);
		Peca capturedPiece = tabuleiro.removaPeca(alvo);
		tabuleiro.lugarPeca(p, alvo);
		
		if (capturedPiece != null) {
			pecasNoTabuleiro.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void anularMovimento(Posicao origem, Posicao alvo, Peca capturedPiece) {
		Peca p = tabuleiro.removaPeca(alvo);
		tabuleiro.lugarPeca(p, origem);
		
		if (capturedPiece != null) {
			tabuleiro.lugarPeca(capturedPiece, alvo);
			capturedPieces.remove(capturedPiece);
			pecasNoTabuleiro.add(capturedPiece);
		}
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temPeca(posicao)) {
			throw new ExcecaoXadrez("There is no piece on source position");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("The chosen piece is not yours");
		}
		if (!tabuleiro.peca(posicao).existeUmMovimentoPossivel()) {
			throw new ExcecaoXadrez("There is no possible moves for the chosen piece");
		}
	}
	
	private void validarPosicaoAlvo(Posicao origem, Posicao alvo) {
		if (!tabuleiro.peca(origem).possivelMovimento(alvo)) {
			throw new ExcecaoXadrez("The chosen piece can't move to target position");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("There is no " + cor + " king on the board");
	}
	
	private boolean testCheck(Cor cor) {
		Posicao reiPosicao = rei(cor).getPosicaoXadrez().posicionar();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasOponente) {
			boolean[][] mat = p.possivelMovimento();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna,linha).posicionar());
		pecasNoTabuleiro.add(peca);
	}
	private void configuracaoInicial() {
		novoLugarPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));

		novoLugarPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));
		novoLugarPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));

		novoLugarPeca('e', 8, new Rainha(tabuleiro, Cor.PRETO));
		novoLugarPeca('e', 1, new Rainha(tabuleiro, Cor.BRANCO));

		novoLugarPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		novoLugarPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		novoLugarPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		novoLugarPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));

		novoLugarPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		novoLugarPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		novoLugarPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novoLugarPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));

		// novoLugarPeca('h',7, new Peao(tabuleiro,Cor.PRETO));
		novoLugarPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
		novoLugarPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
		novoLugarPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
		novoLugarPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
		novoLugarPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
		novoLugarPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
		novoLugarPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));

		// novoLugarPeca('a',2, new Peao(tabuleiro,Cor.BRANCO));
		novoLugarPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		novoLugarPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		novoLugarPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		novoLugarPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		novoLugarPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		novoLugarPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		novoLugarPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));
	}

}
