import java.util.ArrayList;

public class Candidatura {

	private ArrayList<Candidato> candidato;

	public Candidatura() {
		candidato = new ArrayList<>();
	}

	public boolean cadastraCandidato(Candidato c) {
		if (candidato.contains(c)) {
			return false;
		}
		else if (candidato.contains(c.getNumero())){
			if(candidato.contains(c.getMunicipio())){
				return false;
			}
			return candidato.add(c);
		}
		else return candidato.add(c);
	}

	public boolean isPrefeito(int numero){
		if(numero>10&&numero<99){
			return true;
		}
		return false;
	}

	public void toString(Candidato c){
		System.out.println("2: "+c.getNumero()+" "+c.getNome()+" "+c.getMunicipio());
	}
}
