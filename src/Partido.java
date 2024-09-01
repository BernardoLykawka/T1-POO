import java.util.ArrayList;

public class Partido {

	private int numero;
	private String nome;
	private ArrayList<Candidato> candidatos;

	public Partido(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
		this.candidatos = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public int getNumero() {
		return numero;
	}

	public void adicionaCandidato(Candidato c) {
		candidatos.add(c);
		c.setPartido(this);
	}
}
