import java.util.ArrayList;

public class Candidato {

	private int numero;
	private String nome;
	private String municipio;
	private int votos;
	private Partido partido;

	public Candidato(int numero, String nome, String municipio) {
		this.numero = numero;
		this.nome = nome;
		this.municipio = municipio;
	}


	public int getNumero() {
		return numero;
	}

	public String getMunicipio(){
		return municipio;
	}

	public String getNome() {
		return nome;
	}

	public int getVotos() {
		return votos;
	}

	Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido){
		this.partido=partido;
	}
}
