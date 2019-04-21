package application;
//--------------------------------------------
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import application.IO;
//--------------------------------------------

/**
 * Classe de Controle
 * 
 * Autora: Mayara 
 * Data..: 12/11/2018
 */
public class Controle {
	private List<String[]> arquivo;
	private List<Acao> listAcoes;
	private Acao acao;
	private BlocoMemoria blocoMemoria;

	/**
	 * Construtor da Controle
	 */
	public Controle() {
		listAcoes = new LinkedList<>();
	}

	/**
	 * Chama o OI e para ler e retornar os dados do arquivo.
	 *
	 * @param nome - string com o caminho e nome do arquivo.
	 * @return true se o arquivo foi lido com sucesso.
	 */
	public boolean carregarArquivo(String nome) {

		try {
			arquivo = IO.carregarArquivo(nome);

		} catch (IOException e) {
			return false;
		}

		return true;
	}

	/**
	 * Cria o objeto para bloco de memória e ações vindas do arquivo.
	 */
	public void gerarObjetos() {

		try {
			String tipo = null, nome = null;
			int tamanho = 0, count = 1, aux = 0, mi = 0, mf = 0;

			for (String[] vet : arquivo) {

				if (aux != 0) {
					tipo = vet[0];

					if (tipo.equals("S")) {
						tamanho = Integer.parseInt(vet[1]);
						nome = "bloco " + count;

						acao = new Acao(tipo, tamanho, nome);

						count++;

					} else {
						nome = vet[1].replace(" ", "");
						acao = new Acao(tipo, nome);
					}

					listAcoes.add(acao);

				} else {
					mi = Integer.parseInt(vet[0]);
					mf = Integer.parseInt(vet[1]);

					blocoMemoria = new BlocoMemoria(mi, mf);

					aux++;
				}

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\nDocumento inválido!");

		} catch (NumberFormatException e) {
			System.out.println("\nDocumento inválido!");
		}

	}
	
	/**
	 * Gerencia solicitações e liberações dentro do bloco de memória.
	 */
	public void gerenciarMemoria() {
		Acao acao;

		for (int i = 0; i < listAcoes.size(); i++) {
			acao = listAcoes.get(i);

			if (acao.getTipo().equals("S")) {
				blocoMemoria.solicitacao(acao);

			} else {
				blocoMemoria.liberacao(acao);

			}

			imprimir();
			
		}
		
		List<Acao> memoria = blocoMemoria.getMemoria();
		imprimir2(memoria);
		
		System.out.println("\n\n------- ESTADO FINAL DO BLOCO DE MEMÓRIA");
		System.out.println("\n-------------------------------------------------------------------");
		
	}
	
	/**
	 * Imprime o estado atual do bloco de memória.
	 */
	public void imprimir() {
		List<Acao> memoria = blocoMemoria.getMemoria();
		Acao acao = null;
		int mf = blocoMemoria.getMF();
		int solicitado = blocoMemoria.getSolicitados(), livre = blocoMemoria.getLivre();
		
		if (solicitado <= livre && solicitado != 0) {
			imprimir2(memoria);
			System.out.print(", " + blocoMemoria.getSolicitados() + " solicitados - fragmentação externa.");
			blocoMemoria.setSolicitados(0);
			
		}
			
		if (solicitado > livre && solicitado != 0) {
			imprimir2(memoria);
			System.out.print(", " + blocoMemoria.getSolicitados() + " solicitados - memoria indisponível.");
			
		}
	}
	
	public void imprimir2(List<Acao> memoria) {
		
		System.out.println("\n\n-------------------------------------------------------------------\n");
		for (int i = 0; i < memoria.size(); i++) {
			acao = memoria.get(i);
			if (acao.getTipo().equals("S")) {
				System.out.println(acao.getValorI() + "-" + acao.getValorF() + " \t" + acao.getNome() + "  \t(tamanho "
						+ acao.getTamanho() + ")");
			} else {
				System.out.println(
						acao.getValorI() + "-" + acao.getValorF() + " \tlivre   \t(tamanho " + acao.getTamanho() + ")");
			}
		}
		System.out.print("\n" + blocoMemoria.getLivre() + " livres");
	}
}
