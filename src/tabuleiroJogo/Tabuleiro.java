package tabuleiroJogo;

public class Tabuleiro {

	private Integer linhas;
	private Integer colunas;
	private Peca[][] pecas;

	public Tabuleiro(Integer linhas, Integer colunas) {
		if (linhas <1 || colunas<1) {
			throw new ExcecaoTabuleiro("Erro ao criar Tabuleiro, necessario 1 linha e 1 coluna pelo menos");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public Integer getLinhas() {
		return linhas;
	}

	public Integer getColunas() {
		return colunas;
	}

	public Peca peca(int linha, int coluna) {
		if (!posicaoExistente (linha,coluna)) {
			throw new ExcecaoTabuleiro("Posição não existente no tabuleiro");
		}
		return pecas[linha][coluna];
	}

	public Peca peca(Posicao posicao) {
		if (!posicaoExistente (posicao)) {
			throw new ExcecaoTabuleiro("Posição não existente no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void lugarPeca(Peca peca, Posicao posicao) {
		if (temPeca(posicao)) {
			throw new ExcecaoTabuleiro("já existe uma peça em posição"+ posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean posicaoExistente(int linha, int coluna) {
	 return	linha >= 0 && linha < linhas && coluna >=0 && coluna < colunas;  
	}
	
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(),posicao.getColuna());
		
	}
	
	public boolean temPeca(Posicao posicao) {
		if (!posicaoExistente (posicao)) {
			throw new ExcecaoTabuleiro("Posição não existente no tabuleiro");
		}
		return peca(posicao) != null;
	}

}
