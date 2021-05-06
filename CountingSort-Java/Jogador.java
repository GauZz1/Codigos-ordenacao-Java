import java.io.FileNotFoundException;
import java.io.*;
import java.util.Formatter;
import java.nio.charset.*;
import java.io.File;
import java.util.Scanner;
import java.util.Date;

class Jogador{
	private static String nomeArquivo = "countingsort.txt";
	private static String charsetArquivo = "ISO-8859-1";
	private static boolean write = false, read = false;
	private static Formatter saida = null;
	private static Scanner entrada = null;
	private static int c = 0;
	private static int m = 0;
	private int id, altura, peso, anoNascimento;
	private String nome, universidade, cidadeNascimento, estadoNascimento;

	public static int quantidade = 0;
// --------- Construtores -----------
	public Jogador(){
		this.id = this.altura = this.anoNascimento = this.peso = 0;
		this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "";
		quantidade++;
	}

	public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento){
		this.id = id;
		this.altura = altura;
		this.peso = peso;
		this.nome = nome;
		this.universidade = universidade;
		this.anoNascimento = anoNascimento;
		this.cidadeNascimento = cidadeNascimento;
		this.estadoNascimento = estadoNascimento;
		quantidade++;
	}
// --------- Fim Construtores --------

// --------- Gets / Sets ---------
	public static int getQuantidade(){
		return quantidade;
	}

	public int getID(){
		return this.id;
	}

	public void setID(int id){
		this.id = id;
	}

	public int getAltura(){
		return this.altura;
	}

	public void setAltura(int altura){
		this.altura = altura;
	}

	public int getPeso(){
		return this.peso;
	}

	public void setPeso(int peso){
		this.peso = peso;
	}

	public String getNome(){
		return this.nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getUniversidade(){
		return this.universidade;
	}

	public void setUniversidade(String universidade){
		this.universidade = universidade;
	}

	public int getAnoNascimento(){
		return this.anoNascimento;
	}

	public void setAnoNascimento(int anoNascimento){
		this.anoNascimento = anoNascimento;
	}

	public String getCidadeNascimento(){
		return this.cidadeNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento){
		this.cidadeNascimento = cidadeNascimento;
	}

	public String getEstadoNascimento(){
		return this.estadoNascimento;
	}

	public void setEstadoNascimento(String estadoNascimento){
		this.estadoNascimento = estadoNascimento;
	}
// --------- FIM Gets / Sets ---------

// --------- Funções ----------------
	public Jogador(Jogador clone){
		this.id = clone.id;
		this.altura = clone.altura;
		this.peso = clone.peso;
		this.nome = clone.nome;
		this.universidade = clone.universidade;
		this.anoNascimento = clone.anoNascimento;
		this.cidadeNascimento = clone.cidadeNascimento;
		this.estadoNascimento = clone.estadoNascimento;
	}
// --------- Recebe uma linha lida do arquivo e pega até a virgula o que está escrito -----
	public String[] lerJogador(String[] linhaJogador, Jogador j1){
					for(int i=0; i<8; i++){
// ---------- Se tiver vazio("") ou \n o local recebe a string "nao informado" ----------
						if(linhaJogador[i].equals("") || linhaJogador[i].equals("\n")) linhaJogador[i] = "nao informado";
					}
					j1.setID(Integer.parseInt(linhaJogador[0]));
					j1.setNome(linhaJogador[1]);
					j1.setAltura(Integer.parseInt(linhaJogador[2]));
					j1.setPeso(Integer.parseInt(linhaJogador[3]));
					j1.setUniversidade(linhaJogador[4]);
					j1.setAnoNascimento(Integer.parseInt(linhaJogador[5]));
					j1.setCidadeNascimento(linhaJogador[6]);
					j1.setEstadoNascimento(linhaJogador[7]);
					return linhaJogador;
				}
	public void escreveJogador(){
			System.out.println("[" + getID() + " ## " + getNome() + " ## " + getAltura() + " ## " + getPeso() + " ## " + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]");
	}
    
    public static Jogador[] CountingSort(Jogador[] jogadores) { 
        
         
            int n = getQuantidade(); 
      
            Jogador[] novo_jogador = new Jogador[n]; 

            int count[] = new int[256]; 
            for (int i = 0; i < 256; ++i) 
                count[i] = 0; 

            for (int i = 0; i < n; ++i) {
                ++count[jogadores[i].getAltura()]; 
            }

            for (int i = 1; i <= 255; ++i) {
                count[i] += count[i - 1]; 
            }

            for (int i = n - 1; i >= 0; i--) { 
                novo_jogador[count[jogadores[i].getAltura()] - 1] = jogadores[i];
                m++;
                --count[jogadores[i].getAltura()]; 
            } 
            for (int i = 0; i < n; ++i) {
                jogadores[i] = novo_jogador[i];
                m++;
            }
            return jogadores;
    } 
	
	public static Jogador[] comparaNome(Jogador[] jogadores){
		CountingSort(jogadores);
		for (int i = 1; i < getQuantidade(); i++) {
			Jogador tmp = jogadores[i];
	        int j = i - 1;      // ---- Ano de nascimento igual entre os dois comparados ---- ----- Compara o nome do jogador que está na tmp com todos os j ------
	        c = c + (j+1);
	        while ((j >= 0) && (jogadores[j].getAltura() == tmp.getAltura()) && (jogadores[j].getNome().compareTo(tmp.getNome())>0)) {
		            jogadores[j+1] = jogadores[j];
		            m++;
		            j--;
	        }
	        jogadores[j + 1] = tmp;
	        m++;
    	}
		return jogadores;
	}

	public static boolean openWrite(String nomeArq, String charset) {
      boolean resp = false;
      close();
		try{
		   saida = new Formatter(nomeArq, charset);
         nomeArquivo = nomeArq;
         resp = write = true;
		}  catch (Exception e) {}
      return resp;
   	}

	public static void openWriteClose(String nomeArq, String charset, String conteudo) {
      boolean resp = openWrite(nomeArq, charset);
      if(resp == true){
      	println(conteudo);
        close();
      }
   	}

	public static void println(String x){
	    if(write == true){
		   	saida.format( "%s\t", x);
	    }
	}

   	public static void close() {
      if(write == true){
         saida.close();
      }
      if(read == true){
         entrada.close();
      }
      write = read = false;
      nomeArquivo = "";
      charsetArquivo = "ISO-8859-1";
	}

// ---------- FIM Funções ---------------

	public static void main(String[] args){
			File arq = new File("/tmp/players.csv");
			int j=0;
// -------- Declara vetor de Jogador ------------
			Jogador[] jogadores = new Jogador[4000];
			String[] leitura = new String[8];
			try{
				String numDigitado = MyIO.readString();
				int numero;
// ----------- while para ler linhas do arquivo até aparece FIM --------
				while(!numDigitado.equals("FIM")){
					numero = Integer.parseInt(numDigitado);
// ----------- Inicia o Scanner com delimiter "," ou "/n" 
//			   para saber que este é o fim da informação ou da linha --------------
					Scanner leArquivo = new Scanner (arq).useDelimiter(",|\n");
					for(int i=0;i<=numero;i++){
						leArquivo.nextLine();
					}
// seleciona o próximo item de jogador  Ex: ID, nome, altura...
					for(int i=0; i<8; i++){
						leitura[i] = leArquivo.next();
					}
					Jogador j1 = new Jogador();
					j1.lerJogador(leitura, j1);
					//j1.escreveJogador();
					jogadores[j] = j1;
					j++;
					numDigitado = MyIO.readString();
				}

			}catch(FileNotFoundException e){}
			long tInicio = new Date().getTime();
			jogadores = comparaNome(jogadores);
			long tFim = new Date().getTime();
			long tTotal = (tFim-tInicio);
			String escrever = "\tComparações: "+ c + "\t Movimentações: " + m + "\tTempo de execução: " + tTotal + "ms";
			openWriteClose(nomeArquivo, charsetArquivo, escrever);
			for(int i=0; i<getQuantidade(); i++){
				jogadores[i].escreveJogador();
			}
		}
}