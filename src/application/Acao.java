package application;
//--------------------------------------------

/**
 * Classe representando uma A��o (Solicita��o ou Libera��o)
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
	 * Construtor da Controle (assinatura para Solicita��o)
	 * 
	 * @param tipo - "L" se for uma libera��o e "S" se for uma solicita��o. 
	 * @param tamanho - inteiro com tamanho necess�rio para aloca��o.
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
	 * Construtor da Controle (assinatura para Libera��o)
	 * 
	 * @param tipo - "L" se for uma libera��o e "S" se for uma solicita��o. 
	 * @param nome - string com o nome identificador do bloco.
	 */
	public Acao(String tipo, String nome) {
		this.tipo = tipo;
		this.nome = nome;

	}
	
	/**
	 * Retorna a string tipo
	 *  
	 * @return string tipo, com o tipo da a��o.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Retorna a string nome
	 *  
	 * @return string nome, com o nome da a��o.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna a int tamanho
	 *  
	 * @return int tamanho, com o tamanho necess�rio para aloca��o.
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * Retorna o valor inicial da aloca��o
	 *  
	 * @return int valorI, com o valor inicial da aloca��o.
	 */
	public int getValorI() {
		return valorI;
	}

	/**
	 * Retorna o valor final da aloca��o
	 *  
	 * @return int valorF, com o valor final da aloca��o.
	 */
	public int getValorF() {
		return valorF;
	}

	/**
	 * Substitui o elemento armazenado na vari�vel nome, pela string passada por par�metro.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Substitui o elemento armazenado na vari�vel tamanho, pelo int passada por par�metro.
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * Substitui o elemento armazenado na vari�vel valorI, pelo int passada por par�metro.
	 */
	public void setValorI(int valorI) {
		this.valorI = valorI;
	}

	/**
	 * Substitui o elemento armazenado na vari�vel valorF, pelo int passada por par�metro.
	 */
	public void setValorF(int valorF) {
		this.valorF = valorF;
	}

}
