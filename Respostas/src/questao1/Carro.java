package questao1;

public class Carro implements Runnable {
	
	private Carona carona;
	
	public Carro(Carona carona) {
		this.carona = carona;
	}

	public void carregar() {
		this.carona.carregar();
				
	}
	
	public void descarregar() {
		this.carona.descarregar();
	}
	
	public void correr() {
		try {
			this.carona.correr();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.carregar();
		this.correr();
		this.descarregar();
	}

}
