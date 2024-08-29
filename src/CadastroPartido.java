import java.util.ArrayList;

public class CadastroPartido {

	private ArrayList<Partido> partido;

	public CadastroPartido() {
		partido = new ArrayList<>();
	}

	public boolean cadastraPartido(Partido p) {
		if(partido.contains(p)) {
			return false;
		}
		else partido.add(p);
		return true;

	}

	public Partido consultaPartido(String nome) {
		if(partido.contains(nome)) {
			for(Partido p : partido) {
				if(p.getNome().equals(nome)) {
					return p;
				}
			}
		}
		return null;
	}

	public Partido consultaPartido(int numero) {

		if(partido.contains(numero)) {
			for(Partido p : partido) {
				if(p.getNumero() == numero) {
					return p;
				}
			}
		}
		return null;
	}

}
