package application;
//--------------------------------------------
import application.Controle;
//--------------------------------------------

/**
 * Classe Principal
 * - Solicita a leitura dos dados;
 * - Solicita a cria��o de objetos do tipo Acao e BlocoMemoria;
 * - Solicita a gerencia da mem�ria.
 * 
 * Autora: Mayara 
 * Data..: 12/11/2018
 */

public class App {
	public static void main(String[] args) {
		String nome = "teste01";
		String aux;
		
		Controle control = new Controle();
         
        aux = (control.carregarArquivo(nome))?"Conclu�do!" :"Falha no carregamento.";
		System.out.print("Carregamento do arquivo " + nome + "... " + aux);

		control.gerarObjetos();
		control.gerenciarMemoria();
	}

}
