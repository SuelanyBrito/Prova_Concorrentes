package questao2;

public class AlunoUEPB implements Runnable {
	
	private Barco barco;
	
	public AlunoUEPB(Barco barco) {
		this.barco = barco;
	}

	@Override
	public void run() {
		try {
			this.barco.embarcar("UEPB");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
