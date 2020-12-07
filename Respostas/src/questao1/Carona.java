package questao1;

public class Carona {
	
	private int capacidade;
	private int qtd_embarque;
	private boolean estado_carregar;
	private boolean estado_descarregar;
	private boolean estado_correr;
	
	
	public Carona(int capacidade) {
		this.capacidade = capacidade;
		this.qtd_embarque = 0;
		this.estado_carregar = false;
		this.estado_descarregar = false;
		this.estado_correr = false;
	}
	
	/**
	 * Embarque de passageiros
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized boolean embarque() throws InterruptedException {
		while(!this.estado_carregar || this.qtd_embarque >= this.capacidade) { 
			this.wait();
		}
		System.out.println("PASSAGEIRO EMBARCANDO");
		int value = this.qtd_embarque;
		this.qtd_embarque = value + 1;
		this.notifyAll();
		
		return true;
	}
	
	
	/**
	 * Desembarque de passageiros
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized boolean desembarque() throws InterruptedException {
		while(!this.estado_descarregar) {
			this.wait();
		}
		
		this.qtd_embarque--;
		System.out.println("PASSAGEIRO DESEMBARCANDO");
		this.notifyAll();
		
		return false;
	}
	
	/**
	 * Permite o inicio do carregamento de passageiro
	 */
	public synchronized void carregar(){
		this.estado_carregar = true;
		System.out.println("PERMITIR EMBARQUE");
		this.notifyAll();
	}
	
	/**
	 * O carro começa a corrida
	 * @throws InterruptedException
	 */
	public synchronized void correr() throws InterruptedException {
		while(this.qtd_embarque < this.capacidade) {
			this.wait();
		}
		this.estado_carregar = false;
		this.estado_correr = true;
		System.out.println("CORRENDO");
		this.notifyAll();
		
	}
	
	/**
	 * Permite o descarregamento de passageiros
	 */
	public synchronized void descarregar() {
		this.estado_correr = false;
		this.estado_descarregar = true;
		System.out.println("PERMITIR DESEMBARQUE");
		this.notifyAll();
	}
	
	//GETTERS
	
	public int getQtd_embarque() {
		return qtd_embarque;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public boolean isEstado_correr() {
		return estado_correr;
	}
}
