import java.util.ArrayList;

public class Candidatura {

	private ArrayList<Candidato> candidato;

	public Candidatura() {
		candidato = new ArrayList<>();
	}

	public boolean cadastraCandidato(Candidato c) {
		for (Candidato cand : candidato) {
			if (cand.getNumero() == c.getNumero() && cand.getMunicipio().equals(c.getMunicipio())) {
				return false;
			}
		}
		return candidato.add(c);
	}

	public boolean isPrefeito(int numero){
		if(numero >= 10 && numero <= 99){
			return true;
		}
		return false;
	}

	public Candidato consultaCandidato(int numero) {

		for(Candidato c : candidato) {

			if(c.getNumero() == numero) {
				if (c.getVotos() > 0) {
					continue;
				}
				return c;
			}
		}
		return null;
	}

	public Candidato consultaCandidato(int numero, String municipio){
		for(Candidato c : candidato) {
			if(c.getNumero() == numero && c.getMunicipio().equals(municipio)) {
				return c;
			}
		}
		return null;
	}

	public ArrayList<Candidato> getCandidato() {
		return candidato;
	}
}
