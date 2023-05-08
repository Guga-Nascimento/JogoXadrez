package Aplication;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();
	
		
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaXadrez,capturada);			
				System.out.println("Origem: ");
				
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
				
				boolean [][] movimentoPossivel = partidaXadrez.possivelMovimento(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPecas(), movimentoPossivel);
				
			
		
				System.out.println();
				System.out.println("Alvo: ");
			
				PosicaoXadrez alvo = UI.lerPosicaoXadrez(sc);
				
				PecaXadrez capturarPeca = partidaXadrez.performaceMovimentoXadrez(origem, alvo);
				
				if (capturarPeca != null) {
				  capturada.add(capturarPeca);				}
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
