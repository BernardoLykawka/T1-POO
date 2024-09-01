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
		if(numero>=10&&numero<=99){
			return true;
		}
		return false;
	}

	public Candidato consultaCandidato(int numero) {

		for(Candidato c : candidato) {
			if(c.getNumero() == numero) {
				return c;
			}
		}
		return null;
	}


	public void toString(Candidato c){
		System.out.println("2: "+c.getNumero()+"- "+c.getNome()+"- "+c.getMunicipio());
	}

	public void toString(Candidato c, int votos){
		System.out.println("3: "+c.getNumero()+"- "+c.getMunicipio()+"- Votos: "+c.getVotos());
	}
}
