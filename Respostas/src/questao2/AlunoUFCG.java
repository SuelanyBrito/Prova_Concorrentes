package questao2;

public class AlunoUFCG implements Runnable{
	
	private Barco barco;
	
	public AlunoUFCG(Barco barco) {
		this.barco = barco;
	}
	
	public void remar() {
		this.barco.remar();
	}

	@Override
	public void run() {
		try {
			this.barco.embarcar("UFCG");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
