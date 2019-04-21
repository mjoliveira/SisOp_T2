package application;
//--------------------------------------------

/**
 * Classe representando uma Ação (Solicitação ou Liberação)
 * 
 * Autora: Mayara 
 * Data..: 15/11/2018
 */
public class Acao {
	private String tipo;
	private String nome;
	private int tamanho;
	private int valorI, valorF;

	/**
	 * Construtor da Controle (assinatura para Solicitação)
	 * 
	 * @param tipo - "L" se for uma liberação e "S" se for uma solicitação. 
	 * @param tamanho - inteiro com tamanho necessário para alocação.
	 * @param nome - string com o nome identificador do bloco.
	 */
	public Acao(String tipo, int tamanho, String nome) {
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.nome = nome;
		valorI = 0;
		valorF = 0;

	}

	/**
	 * Construtor da Controle (assinatura para Liberação)
	 * 
	 * @param tipo - "L" se for uma liberação e "S" se for uma solicitação. 
	 * @param nome - string com o nome identificador do bloco.
	 */
	public Acao(String tipo, String nome) {
		this.tipo = tipo;
		this.nome = nome;

	}
	
	/**
	 * Retorna a string tipo
	 *  
	 * @return string tipo, com o tipo da ação.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Retorna a string nome
	 *  
	 * @return string nome, com o nome da ação.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna a int tamanho
	 *  
	 * @return int tamanho, com o tamanho necessário para alocação.
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * Retorna o valor inicial da alocação
	 *  
	 * @return int valorI, com o valor inicial da alocação.
	 */
	public int getValorI() {
		return valorI;
	}

	/**
	 * Retorna o valor final da alocação
	 *  
	 * @return int valorF, com o valor final da alocação.
	 */
	public int getValorF() {
		return valorF;
	}

	/**
	 * Substitui o elemento armazenado na variável nome, pela string passada por parâmetro.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Substitui o elemento armazenado na variável tamanho, pelo int passada por parâmetro.
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * Substitui o elemento armazenado na variável valorI, pelo int passada por parâmetro.
	 */
	public void setValorI(int valorI) {
		this.valorI = valorI;
	}

	/**
	 * Substitui o elemento armazenado na variável valorF, pelo int passada por parâmetro.
	 */
	public void setValorF(int valorF) {
		this.valorF = valorF;
	}

}
