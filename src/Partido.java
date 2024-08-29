import java.util.ArrayList;


public class Partido {

	private int numero;
	private String nome;
	private ArrayList<Candidato> candidato;

	public Partido(int numero, String nome, ArrayList<Candidato> candidato) {
		candidato = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public int getNumero() {
		return numero;
	}

	public void adicionaCandidato(Candidato c) {
		candidato.add(c);
	}

}
