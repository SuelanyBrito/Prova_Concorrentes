package questao1;

public class Passageiro implements Runnable{
	
	private Carona carona;
	
	public Passageiro(Carona carona){
		this.carona = carona;
	}

	/**
	 * Neste método o passageiro pode embarcar ou desembarcar
	 */
	@Override
	public void run() {
		try {
			for(int i = 0; i < 5; i++) {
				carona.embarque();
				carona.desembarque();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
