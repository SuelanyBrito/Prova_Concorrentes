package questao1;

public class Passageiro implements Runnable{
	
	private Carona carona;
	private boolean estado;
	
	public Passageiro(Carona carona){
		this.carona = carona;
		this.estado = false;
	}

	/**
	 * Neste método o passageiro pode embarcar ou desembarcar
	 */
	@Override
	public void run() {
		try {
			if(!estado) {
				this.estado = carona.embarque();
			}else {
				this.estado = carona.desembarque();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
