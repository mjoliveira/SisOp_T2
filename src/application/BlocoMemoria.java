package application;
//--------------------------------------------
import java.util.LinkedList;
import java.util.List;
//--------------------------------------------

/**
 * Classe representando Bloco de Mem�ria
 * 
 * Autora: Mayara 
 * Data..: 15/11/2018
 */

public class BlocoMemoria {
	private int mi, mf;
	private int livre, solicitados;

	private List<Acao> memoria;
	private List<Acao> espera;

	private Acao blocoInicial;
	
	boolean aux;
	
	/**
	 * Construtor da Controle (assinatura para Solicita��o)
	 * 
	 * @param mi - inteiro com o valor inicial do bloco de mem�ria. 
	 * @param mi - inteiro com o valor final do bloco de mem�ria. 
	 */
	public BlocoMemoria(int mi, int mf) {
		this.mi = mi;
		this.mf = mf;

		livre = mf - mi;

		memoria = new LinkedList<>();
		espera  = new LinkedList<>();

		blocoInicial = new Acao("L", livre, "Bloco 0");
		blocoInicial.setValorI(mi);
		blocoInicial.setValorF(mf);

		aux = false;
		
		memoria.add(blocoInicial);
	}

	/**
	 * Adiciona uma a��o no bloco de mem�ria, gerando uma solicita��o
	 * 
	 * @param solicitacao - objeto do tipo Acao que representa uma solicita��o de aloca��o de mem�ria. 
	 */
	public void solicitacao(Acao solicitacao) {
		int tamanho = solicitacao.getTamanho();

		if (livre >= tamanho) {
			Acao acao;

			int index = bestFit(solicitacao);

			if (index != -1) {
				acao = memoria.get(index);

				solicitacao.setValorI(acao.getValorI());
				solicitacao.setValorF(solicitacao.getValorI() + solicitacao.getTamanho());

				if (acao.getTamanho() != tamanho) {
					acao.setValorI(solicitacao.getValorF());
					acao.setTamanho(acao.getValorF() - acao.getValorI());
					memoria.set(index, acao);
					memoria.add(index, solicitacao);

				} else {
					memoria.set(index, solicitacao);

				}

				livre = livre - tamanho;

			} else {
				solicitados = solicitacao.getTamanho();
				espera.add(solicitacao);
			}

		} else {
			solicitados = solicitacao.getTamanho();
			espera.add(solicitacao);

		}

	}

	/**
	 * Verifica toda lista de mem�ria, procurando o segmento que tiver espa�o mais pr�ximo ou igual ao tamanho da solicitacao.
	 * 
	 * @param solicitacao - objeto do tipo Acao que representa uma solicita��o de aloca��o de mem�ria. 
	 * @return index - inteiro que representa uma posi��o livre, muito pr�xima ou igual ao tamanho desejado.
	 */
	public int bestFit(Acao solicitacao) {
		Acao acao;
		int tamanho = solicitacao.getTamanho();
		int tamanhoLivre = 0, tamanhoAtual = 0, index = -1;

		for (int i = memoria.size() - 1; i >= 0; i--) {
			acao = memoria.get(i);
			tamanhoLivre = acao.getTamanho();

			if (acao.getTipo().equals("L") && tamanhoLivre >= tamanho) {

				if (tamanhoAtual == 0 || tamanhoLivre <= tamanhoAtual) {
					tamanhoAtual = tamanhoLivre;
					index = i;

				}
			}
		}

		return index;
	}

	/**
	 * Substitui a a��o igual ao nome da liberacao pela pr�pria liberacao, tornando o espa�o livre.
	 * 		- Verifica a cada liberacao se uma nova solicitacao em espera pode ser atendida.
	 * 
	 * @param liberacao - objeto do tipo Acao que representa uma libera��o de aloca��o de mem�ria. 
	 */
	public void liberacao(Acao liberacao) {
		String bloco = "bloco " + liberacao.getNome();
		Acao solicitacao;
		int tamanho;

		for (int i = 0; i < memoria.size(); i++) {
			solicitacao = memoria.get(i);
			tamanho = solicitacao.getTamanho();

			if (solicitacao.getNome().equals(bloco)) {
				liberacao.setValorI(solicitacao.getValorI());
				liberacao.setValorF(solicitacao.getValorF());
				liberacao.setTamanho(tamanho);

				memoria.set(i, liberacao);
				livre = livre + tamanho;

				buscaLivre(i);

				int qtEspera = espera.size();

				if (qtEspera != 0) {

					for (int j = 0; j < qtEspera; j++) {
						solicitacao(espera.get(j));
						espera.remove(j);

						j--;
						qtEspera--;
						
						if(qtEspera == espera.size()-1) aux = true;
						

					}

				}
				break;

			}

		}

	}
	
	
	/**
	 * Busca espa�os livres antes e depois da index passada pro par�metro, 
	 * para unir todos os espa�os livres.
	 * 
	 * @param int index, inteiro que representa uma posi��o livre.
	 */
	public void buscaLivre(int index) {
		if (index < 0 || index >= memoria.size())
			return;

		Acao atual = memoria.get(index);

		if (index + 1 < memoria.size()) {
			Acao posterior = memoria.get(index + 1);

			if (posterior.getTipo().equals("L")) {
				atual.setValorF(posterior.getValorF());
				atual.setTamanho(atual.getValorF() - atual.getValorI());

				memoria.remove(index + 1);
			}
		}

		if (index - 1 >= 0) {

			Acao anterior = memoria.get(index - 1);

			if (anterior.getTipo().equals("L")) {
				atual.setValorI(anterior.getValorI());
				atual.setTamanho(atual.getValorF() - atual.getValorI());

				memoria.remove(index - 1);
			}

		}

		return;
	}

	/**
	 * Retorna o valor inicial.
	 * 
	 * @return int mi, valor inicial do bloco de mem�ria.
	 */
	public int getMI() {
		return mi;
	}

	/**
	 * Retorna o valor final.
	 * 
	 * @return int mf, valor final do bloco de mem�ria.
	 */
	public int getMF() {
		return mf;
	}

	/**
	 * Retorna o valor do espa�o livre em mem�ria.
	 * 
	 * @return int livre, valor livre em mem�ria.
	 */
	public int getLivre() {
		return livre;
	}

	/**
	 * Retorna valor solicitado para aloca��o.
	 * 
	 * @return int solicitados, valor solicitado para aloca��o no momento atual.
	 */
	public int getSolicitados() {
		return solicitados;
	}

	/**
	 * Retorna a lista com todos os objetos alocados.
	 * 
	 * @return List<Acao> memoria, LinkedList com todos os objetos alocados.
	 */
	public List<Acao> getMemoria() {
		return memoria;
	}

	/**
	 * Substitui o elemento armazenado na vari�vel solicitados, pelo int passada por
	 * par�metro.
	 */
	public void setSolicitados(int solicitados) {
		this.solicitados = solicitados;
	}

}
