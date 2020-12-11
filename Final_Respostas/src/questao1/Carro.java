package questao1;

public class Carro implements Runnable {
	
	private Carona carona;
	
	public Carro(Carona carona) {
		this.carona = carona;
	}

	
	@Override
	public void run() {
		try {
			for(int i = 0; i < 5; i++) {
				this.carona.carregar();
				this.carona.correr();
				this.carona.descarregar();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
