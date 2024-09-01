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
	private final String nomeArquivoEntrada = "input.txt";
	private final String nomeArquivoSaida = "output.txt";

	public ACMEVoting() {
		candidatura = new Candidatura();
		cadastroPartido = new CadastroPartido();
	}

	public void executar() {
		redirecionaEntrada();
		redirecionaSaida();

		cadastrarPartido(); //1
		cadastrarCandidatos(); //2
		cadastrarVotos();  //3
		mostrarDadosPartido();  //4
		mostrarDadosCandidatos(); //5
	}

	public void cadastrarPartido() {

		while(true) {
			int numero = input.nextInt();
			if(numero == -1){
				break;
			}
			input.nextLine();
			String nome = input.nextLine();
			if (numero > 99 || numero < 10) {
				System.out.println("1: Erro: Um partido deve ter 2 numeros!");
			} else {
				Partido p1 = new Partido(numero, nome);
				if (!cadastroPartido.cadastraPartido(p1)) {
					System.out.println("1: Erro: Já existe um partido com este número.");
				}
				else {
					System.out.println("1: "+p1.getNumero()+"- "+p1.getNome());
				}
			}
		}
	}

	public void cadastrarCandidatos() {
		while (true) {
			int numero;
            numero = input.nextInt();
            if (numero == -1) {
				break;
			}
			input.nextLine();
			String nome = input.nextLine();
			String municipio = input.nextLine();

			Candidato c1 = new Candidato(numero, nome, municipio);
			Partido partido;

			if (candidatura.isPrefeito(numero)) {
				partido = cadastroPartido.consultaPartido(numero);
			} else if (numero > 10000 && numero < 99999) {
				int numeroPartido = numero / 1000;
				partido = cadastroPartido.consultaPartido(numeroPartido);

			} else {
				System.out.println("2: ERRO: Número de candidato fora dos limites");
				continue;
			}

			if (partido == null) {
				System.out.println("2: Erro: Partido não encontrado.");
				continue;

			}

			partido.adicionaCandidato(c1);
			if (!candidatura.cadastraCandidato(c1)) {
				System.out.println("2: ERRO: Já existe uma candidatura com esse número nesse município!");
			}
			else{
				System.out.println("2: "+c1.getNumero()+"- "+c1.getNome()+"- "+c1.getMunicipio());
			}
		}
	}

	public void cadastrarVotos(){
		while (true) {
			int numero = input.nextInt();
			if (numero == -1) {
				break;
			}
			int votos = input.nextInt();

			if(candidatura.consultaCandidato(numero)!=null){
				Candidato c= candidatura.consultaCandidato(numero);
				c.setVotos(votos);
				System.out.println("3: "+c.getNumero()+"- "+c.getMunicipio()+"- Votos: "+c.getVotos());
			}
			else System.out.println("3: ERRO: O numero desse candidato nao foi encontrado!");
		}
	}

	public void mostrarDadosPartido(){
		int numero = input.nextInt();

		if(cadastroPartido.consultaPartido(numero)!=null){
			Partido p = cadastroPartido.consultaPartido(numero);
			System.out.println("4: "+p.getNumero()+"- "+p.getNome());
		}
		else System.out.println("4: ERRO: Nenhum partido encontrado!");
	}

	public void mostrarDadosCandidatos(){
		int numero = input.nextInt();
		input.nextLine();
		String municipio = input.nextLine();

		if(candidatura.consultaCandidato(numero,municipio)!=null) {
			Candidato c = candidatura.consultaCandidato(numero,municipio);
			System.out.println("5: "+c.getNumero()+"- "+c.getNome()+"- "+c.getMunicipio()+" Votos: "+c.getVotos());
		}
		else System.out.println("5: ERRO: Nenhum candidato encontrado!");
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
