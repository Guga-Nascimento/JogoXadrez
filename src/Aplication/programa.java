package Aplication;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		//comentario atualixado
		
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaXadrez);
				System.err.println();
				System.out.println();
				System.out.println("Origem: ");
				System.out.println();
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
				
				boolean [][] movimentoPossivel = partidaXadrez.possivelMovimento(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPecas(), movimentoPossivel);
				
			
		
				System.out.println();
				System.out.println("Alvo: ");
				System.out.println();
				PosicaoXadrez alvo = UI.lerPosicaoXadrez(sc);
				
				PecaXadrez capturarPeca = partidaXadrez.performaceMovimentoXadrez(origem, alvo);
				}
			catch(ExcecaoXadrez e) {
		 System.out.println(e.getLocalizedMessage());
		 sc.nextLine();
		   }
			catch(InputMismatchException e) {
		 System.out.println(e.getLocalizedMessage());
		 sc.nextLine();
		   }
		}
	}	
}
