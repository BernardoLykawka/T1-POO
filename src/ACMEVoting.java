import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEVoting {

	private Candidatura candidatura;
	private CadastroPartido cadastroPartido;
	private Scanner input = new Scanner(System.in);
	private PrintStream saidaPadrao = System.out;
	private final String nomeArquivoEntrada = "entrada.txt";
	private final String nomeArquivoSaida = "saida.txt";

	public ACMEVoting() {
		candidatura = new Candidatura();
		cadastroPartido = new CadastroPartido();
	}

	public void executar() {
		redirecionaEntrada();
		redirecionaSaida();

		String nomeP= input.nextLine();
		int numP = input.nextInt();
 		//arraylist candidatos
		Partido p1 = new Partido(numP, nomeP, );
		cadastroPartido.cadastraPartido(p1);




	}
	private void redirecionaEntrada() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
			input = new Scanner(streamEntrada);   // Usa como entrada um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
		input.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
	}

	// Redireciona Saida de dados para arquivos em vez da tela (terminal)
	// Chame este metodo para redirecionar a escrita de dados para arquivos
	private void redirecionaSaida() {
		try {
			PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
			System.setOut(streamSaida);             // Usa como saida um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
	}

	// Restaura Entrada padrao para o teclado
	// Chame este metodo para retornar a leitura de dados para o teclado
	private void restauraEntrada() {
		input = new Scanner(System.in);
	}

	// Restaura Saida padrao para a tela (terminal)
	// Chame este metodo para retornar a escrita de dados para a tela
	private void restauraSaida() {
		System.setOut(saidaPadrao);
	}
}
