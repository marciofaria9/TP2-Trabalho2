package br.edu.univas.main;
import java.util.Scanner;
import java.util.Arrays;

import br.edu.univas.vo.Partida;
import br.edu.univas.vo.Time;

public class StartApp {
	public static Scanner leitura = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Time[] times = new Time[50];
		Partida[] partidas = new Partida[50];
		
		int contador = 0;
		int escolha1 = 0;
		int escolha2 = 0;						
		int quantidade = 0;
	  
	
		do {	
		imprimeMenu();
		
		escolha1 = leitura.nextInt();
		leitura.nextLine();
			
		if (escolha1 == 1 ) {
			System.out.println("Quantos times deseja cadastrar?");	
			quantidade = leitura.nextInt();
			leitura.nextLine();
			
		cadastrarTimes(times,quantidade);
			 
		}
	
		else if (escolha1 == 2) {
			
			do {
				
			editarTimes(times,quantidade);
		 
			System.out.println("Deseja efetuar nova edição?"); 
			System.out.println("1 - Sim                  2 - Não"); 
			escolha2 = leitura.nextInt();
			leitura.nextLine();				
				} while(escolha2 == 1);
			}
		
		else if (escolha1 == 3) {
						
			excluirTime(times);	
		
		}
		else if (escolha1 == 4) {
			
			cadastrarPartidas(times, partidas, contador);
			contador++;	
						
		}
		
		else if (escolha1 == 5) {
			editarJogos(times, partidas);	
			
		}
		
		else if (escolha1 == 6) {
			excluirPartidas(times, partidas);
					
		
		}
		
		else if (escolha1 == 7){
			
			tabela(times);
										
			
		}
			
	} 
	while (escolha1 != 9);
	
			
		
	leitura.close();	
	}
	public static void imprimeMenu() {
		
		System.out.println("Campeonato de Futebol");
		System.out.println();
		System.out.println("Digite o número correspondente a opção desejada:");
		System.out.println("1 - Cadastrar time");
		System.out.println("2 - Editar time");
		System.out.println("3 - Excluir time");
		System.out.println("4 - Cadastrar jogo");
		System.out.println("5 - Editar jogo");
		System.out.println("6 - Excluir jogo");
		System.out.println("7 - Mostrar Classificação do Campeonato");
		System.out.println("9 - Sair");
		System.out.println("\n");
	}
	
	public static void mostraEquipes(Time[] times) {
		
		System.out.println("\nTimes cadastrados:"); 
		for (int i = 0; i < 50; i++) {
			if (times[i]!= null) {
			System.out.println("Time " + (i+1) + ": " + times[i].nome);						
			}
		}
		
	}
	
	public static void cadastrarTimes(Time[] times, int escolha) {
		  for (int i = 0; i < escolha; i++) {
		    	
	    	    Time time1 = new Time();
	    	    System.out.println("Digite o nome do time:"); 
				time1.nome = leitura.nextLine();
				System.out.println("Digite o estado do time:"); 
				time1.estado = leitura.nextLine();
				times[i] = time1;
		
	       			}
	       		System.out.println("Cadastro concluído.\n"); 	
		
	}
	
	public static void editarTimes(Time[] times, int escolha) {
		
		System.out.println("Editar times"); 
		
		mostraEquipes(times);
		
		System.out.println("Digite o número do time que deseja editar");
		int numero = leitura.nextInt();
		leitura.nextLine();
		System.out.println("Digite o novo nome: "); 
		times[numero - 1].nome = leitura.nextLine();
		System.out.println("Digite o novo estado: "); 
		times[numero - 1].estado = leitura.nextLine();		
		System.out.println("Edição concluída.");
			
	}
	
	public static void excluirTime(Time[] times ) {
		
		System.out.println("Excluir times");  
		
		mostraEquipes(times);
		
		System.out.println("Qual time deseja excluir?");
		int escolha = leitura.nextInt();
		leitura.nextLine();
		
		times[escolha - 1]= null;
		
		System.out.println("Time excluído");
			
	}
	public static void cadastrarPartidas(Time[] times, Partida[] partidas, int indice) {
		Partida partida = new Partida();
		
		
		System.out.println("Cadastrar partidas");
		mostraEquipes(times);
		System.out.println("Escolha o time mandante");
		int escolhaM = leitura.nextInt();
		leitura.nextLine();
		partida.timeMandante.nome = times[escolhaM - 1].nome;
		System.out.println("Digite o placar do time mandante:");
		int placarM = leitura.nextInt();
		leitura.nextLine();
		partida.placarMandante = placarM;
		
		System.out.println("\nEscolha o time visitante");
		int escolhaV = leitura.nextInt();
		leitura.nextLine();
		partida.timeVisitante.nome = times[escolhaV - 1].nome;
		System.out.println("Digite o placar do time visitante:");
		int placarV = leitura.nextInt();
		leitura.nextLine();
		partida.placarVisitante = placarV;		
		partidas[indice] = partida;
		
		System.out.println("Jogo cadastrado: " + partidas[indice].timeMandante.nome + " " + partidas[indice].placarMandante + " x " + 
		partidas[indice].placarVisitante + " " + partidas[indice].timeVisitante.nome );
		
		if (placarM  >   placarV  ) { 
			times[escolhaM - 1].pontos = times[escolhaM - 1].pontos + 3;
			times[escolhaM - 1].saldo = times[escolhaM - 1].saldo + (placarM - placarV);
			times[escolhaV - 1].saldo = times[escolhaV - 1].saldo -  ( placarM - placarV) ;			
		}
		
		else if (placarV > placarM) {
			times[escolhaV - 1].pontos = times[escolhaV - 1].pontos + 3;
			times[escolhaV - 1].saldo = times[escolhaV -1].saldo + (placarV - placarM);
			times[escolhaM - 1].saldo = times[escolhaM - 1].saldo - (placarV - placarM);
			
		}
		else {
			times[escolhaM - 1].pontos = times[escolhaM - 1].pontos + 1;
			times[escolhaV - 1].pontos = times[escolhaV - 1].pontos + 1;
				
		}


	}

	public static void editarJogos(Time[] times, Partida[] partidas) {
		
		Partida partida = new Partida();
		
		System.out.println("Editar partidas");
		mostraPartidasCadastradas(times, partidas);
		System.out.println("Digite o número da partida que dejesa editar");
		int escolhaPartida= leitura.nextInt();
		leitura.nextLine();
		mostraEquipes(times);
		
		System.out.println("Digite o número do novo time mandante");
		int escolhaTimeM = leitura.nextInt();
		leitura.nextLine();	
		partida.timeMandante.nome = times[escolhaTimeM - 1].nome;
		System.out.println("Digite o novo placar do mandante");
		int placarM = leitura.nextInt();
		leitura.nextLine();
		partida.placarMandante = placarM;
		
		
		System.out.println("Digite o número do novo time visitante");
		int escolhaTimeV = leitura.nextInt();
		leitura.nextLine();	
		partida.timeVisitante.nome = times[escolhaTimeV - 1].nome;
		System.out.println("Digite o novo placar do visitante");
		int placarV = leitura.nextInt();
		leitura.nextLine();	
		partida.placarVisitante = placarV;
		
		partidas[escolhaPartida - 1] = partida;
			
		
		
	}
	public static void mostraPartidasCadastradas (Time[] times, Partida[] partidas) {
		
		System.out.println("Partidas Cadastradas\n");
		for (int i = 0 ; i < 50 ; i++) {
			if (partidas[i] != null) {
				System.out.println((i + 1) + ": " + partidas[i].timeMandante.nome + " " + partidas[i].placarMandante + " x " + 
						partidas[i].placarVisitante + " " + partidas[i].timeVisitante.nome );					
			}			
		}			
	}

	public static void excluirPartidas(Time[] times, Partida[] partidas) {
		
		System.out.println("Excluir partidas");
		mostraPartidasCadastradas(times, partidas);
		System.out.println("Digite o número da partida que deseja excluir");
		int escolha = leitura.nextInt();
		
		partidas[escolha - 1] = null;
		
		System.out.println("Partida excluida!");
			
	}
	
	public static void tabela(Time[] times) {
	/* Tentativa sem sucesso
	  
	  Time timeAuxiliar = new Time();
		
		boolean swap = true;
	
	do {
		for (int i = 0; i < 50; i ++) {
			if (times[i] != null) {
			if (times[i].pontos > times[i + 1].pontos) {
				timeAuxiliar = times[i];
				times[i] = times[i +1];
				times[i+1]= timeAuxiliar;
				swap = true;	
				
				}	
			}
			
		}
	
		
		swap = true;
	}while (swap);
		
	*/
		
		for (int i = 0; i < 50; i++) {
			
			if(times[i] != null) {
				System.out.println((i + 1) + ": "+ times[i].nome + " | " + times[i].pontos + " pts" + " | " + times[i].saldo + " saldo");
						}	
			
		}
		
	}
	
}
