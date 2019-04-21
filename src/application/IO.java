package application;
//--------------------------------------------
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.plaf.FileChooserUI;
//--------------------------------------------

/**
 * Classe usada para leitura de arquivo
 * 
 * Autora: Mayara 
 * Data..: 12/11/2018
 */
class IO {

	/**
     * Metodo responsavel por:
     * - Ler o arquivo;
     * - Converter as informações em dados.
     *
     * @param arquivo - string com o caminho e nome do arquivo.
     * @return uma lista com os dados recolhidos do arquivo.
     * @throws IOException erro na leitura do arquivo.
     */
	public static List<String[]> carregarArquivo(String arquivo) throws IOException{
		
		List<String[]> lista = new LinkedList<String[]>();
		String [] tamanho = new String[2];
		
		FileReader arq = new FileReader(arquivo);
		BufferedReader lerArq = new BufferedReader(arq);
		
	    // Le a primeira linha.
	    int modo = Integer.parseInt(lerArq.readLine()); 
	    
	    // Le a segunda linha.
	    String mi = lerArq.readLine(); 
	    
	    // Le a terceira linha.
	    String mf = lerArq.readLine(); 
	    
	    tamanho[0] = mi;
	    tamanho[1] = mf;
	    
	    lista.add(tamanho);
	    
		String linha = null;
		linha = lerArq.readLine();
		
		while (linha != null) {   
			
			lista.add(linha.split(" ", 2));

			linha = lerArq.readLine();
		}
		
		lerArq.close();
		
		return lista;
		
	}

}
