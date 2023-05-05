package Aplication;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while(true) {			
		UI.printTabuleiro(partidaXadrez.getPecas());
		System.err.println();
		System.out.println("Origem: ");
		PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
		
		System.out.println();
		System.out.println("Alvo: ");
		PosicaoXadrez alvo = UI.lerPosicaoXadrez(sc);
		
		PecaXadrez capturarPeca = partidaXadrez.performaceMovimentoXadrez(origem, alvo);
		}
		
	 

	}

}
