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
		mostrarDadosPrefeitos(); //6
		mostrarPartidoMaisCandidatos(); //7
		mostrarMaisVotados(); //8
		maisVotosVereadores(); //9
		municipioMaisVotos(); //10
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
					System.out.println("1: " + p1.getNumero() + "- " + p1.getNome());
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
				System.out.println("2: " + c1.getNumero() + "- " + c1.getNome() + "- " + c1.getMunicipio());
			}
		}
	}

	public void cadastrarVotos(){
		while (true) {
			int numero = input.nextInt();
			if (numero == -1) {
				break;
			}
			input.nextLine();
			String municipio = input.nextLine();
			int votos = input.nextInt();

			if(candidatura.consultaCandidato(numero, municipio)!=null){
				Candidato c = candidatura.consultaCandidato(numero,municipio);
				c.setVotos(votos);
				System.out.println("3: " + c.getNumero() + "- "+c.getMunicipio() + "- Votos: " + c.getVotos());
			}
			else System.out.println("3: ERRO: O numero desse candidato nao foi encontrado!");
		}
	}

	public void mostrarDadosPartido(){
		int numero = input.nextInt();

		if(cadastroPartido.consultaPartido(numero) != null){
			Partido p = cadastroPartido.consultaPartido(numero);
			System.out.println("4: " + p.getNumero() + "- " + p.getNome());
		}
		else System.out.println("4: ERRO: Nenhum partido encontrado!");
	}

	public void mostrarDadosCandidatos(){
		int numero = input.nextInt();
		input.nextLine();
		String municipio = input.nextLine();

		if(candidatura.consultaCandidato(numero,municipio)!=null) {
			Candidato c = candidatura.consultaCandidato(numero,municipio);
			System.out.println("5: "+c.getNumero() + "- " + c.getNome() + "- " + c.getMunicipio() + " Votos: " + c.getVotos());
		}
		else System.out.println("5: ERRO: Nenhum candidato encontrado!");
	}

	public void mostrarDadosPrefeitos(){
		String nome = input.nextLine();
		Partido p = cadastroPartido.consultaPartido(nome);
		if(p == null) {
			System.out.println("6: Erro: Nenhum partido encontrado!");
			return;
		}
		int numero = p.getNumero();
		boolean prefeitoEncontrado = false;

		Candidato c = candidatura.consultaCandidato(numero);
		for (Candidato cand : candidatura.getCandidato()) {
			if (cand.getNumero() == p.getNumero() || (cand.getNumero() / 1000) == p.getNumero()) {
				if (candidatura.isPrefeito(cand.getNumero())) {
					System.out.println("6: " + p.getNome() + " " + cand.getNumero() + "- " + cand.getNome() + " " + cand.getMunicipio() + " Votos: " + cand.getVotos());
					prefeitoEncontrado = true;
				}
			}
		}
		if (!prefeitoEncontrado) {
			System.out.println("6: Nenhum prefeito encontrado.");
		}

	}

	public void mostrarPartidoMaisCandidatos() {
		Partido partidoComMaisCandidatos = null;
		int maisCandidatos = 0;

		for (Partido partido : cadastroPartido.getPartido()) {
			int contadorCandidatos = 0;

			for (Candidato candidato : candidatura.getCandidato()) {
				if (candidato.getNumero() == partido.getNumero() ||
						(candidato.getNumero() / 1000) == partido.getNumero()) {
					contadorCandidatos++;
				}
			}

			if (contadorCandidatos > maisCandidatos) {
				maisCandidatos = contadorCandidatos;
				partidoComMaisCandidatos = partido;
			}
		}

		if (partidoComMaisCandidatos == null || maisCandidatos == 0) {
			System.out.println("7: Nenhum partido com candidatos.");
		} else {
			System.out.println("7: " + partidoComMaisCandidatos.getNumero() + " " + partidoComMaisCandidatos.getNome() + " Candidatos: " + maisCandidatos);
		}
	}

	public void mostrarMaisVotados(){
		Candidato vMaisVotado = null;
		Candidato pMaisVotado = null;

		for (Candidato c : candidatura.getCandidato()) {
			if (candidatura.isPrefeito(c.getNumero())) {
				if (pMaisVotado == null || c.getVotos() > pMaisVotado.getVotos()) {
					pMaisVotado = c;
				}
			} else {
				if (vMaisVotado == null || c.getVotos() > vMaisVotado.getVotos()) {
					vMaisVotado = c;
				}
			}
		}
		if (pMaisVotado == null && vMaisVotado == null) {
			System.out.println("8:Nenhum candidato encontrado.");
		} else {
			if (pMaisVotado != null) {
				System.out.println("8: " + pMaisVotado.getNumero() + " " + pMaisVotado.getNome() + " " + pMaisVotado.getMunicipio() + " " + pMaisVotado.getVotos());
			}
			if (vMaisVotado != null) {
				System.out.println("8: " + vMaisVotado.getNumero() + " " + vMaisVotado.getNome() + " " + vMaisVotado.getMunicipio() + " " + vMaisVotado.getVotos());
			}
		}
	}
	public void maisVotosVereadores(){
		int maisVotos = 0;
		Partido partidoMaisVotado = null;

		for(Partido p:cadastroPartido.getPartido()) {
			int totalVotos = 0;

			for (Candidato c : candidatura.getCandidato()) {
				if (!candidatura.isPrefeito(c.getNumero())) {
					totalVotos+=c.getVotos();
				}
			}
			if(totalVotos > maisVotos) {
				maisVotos = totalVotos;
				partidoMaisVotado = p;
			}
		}
		if(partidoMaisVotado == null) {
			System.out.println("9: Nenhum partido com vereadores votados.");
		}
		else System.out.println("9:" + partidoMaisVotado.getNumero() + " " + partidoMaisVotado.getNome() + " " + maisVotos);
	}

	public void municipioMaisVotos() {
		String municipioComMaisVotos = null;
		int maisVotos = 0;

		for (Candidato c : candidatura.getCandidato()) {
			String municipio = c.getMunicipio();
			int votosTotal = 0;

			for (Candidato c2 : candidatura.getCandidato()) {
				if (c2.getMunicipio().equals(municipio)) {
					votosTotal += c2.getVotos();
				}
			}

			if (votosTotal > maisVotos) {
				maisVotos = votosTotal;
				municipioComMaisVotos = municipio;
			}
		}

		if (municipioComMaisVotos == null) {
			System.out.println("10: Nenhum município com votos.");
		} else {
			System.out.println("10:" + municipioComMaisVotos + " " + maisVotos);
		}
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



