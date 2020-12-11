package questao1;

import java.util.concurrent.Semaphore;

public class Carona {
	
	private int capacidade;
	private int qtd_embarque;
	private Estado estado;
	private Semaphore mutex;
	private Semaphore filaCheia;
	private Semaphore filaIncompleta;
	
	
	public Carona(int capacidade, Semaphore mutex) {
		this.capacidade = capacidade;
		this.qtd_embarque = 0;
		this.estado = Estado.INICIAL;
		this.mutex = mutex;
		
		this.filaCheia = new Semaphore(0);
		this.filaIncompleta = new Semaphore(1);
	}
	
	/**
	 * Embarque de passageiros
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized void embarque() throws InterruptedException{
		
		this.mutex.acquire();
		this.filaIncompleta.acquire();
		
		if(this.estado == Estado.CARREGANDO) {
			System.out.println("PASSAGEIRO EMBARCANDO");
			this.qtd_embarque++;
			
			if(this.qtd_embarque >= this.capacidade) {
				this.estado = Estado.CHEIO;
				this.filaCheia.release();
			}else {
				this.filaIncompleta.release();
			}
			
			this.mutex.release();
		}
	}
	
	
	/**
	 * Desembarque de passageiros
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized boolean desembarque() throws InterruptedException {
		this.mutex.acquire();
		this.filaCheia.acquire();
		
		if(this.estado == Estado.DESCARREGANDO) {			
			this.qtd_embarque--;
			System.out.println("PASSAGEIRO DESEMBARCANDO");
				
			if(this.qtd_embarque == 0) {
				this.estado = Estado.INICIAL;
			}
			
			this.mutex.release();
		}
		
		return false;
	}
	
	/**
	 * Permite o inicio do carregamento de passageiro
	 * @throws InterruptedException 
	 */
	public synchronized void carregar() throws InterruptedException{

		this.mutex.acquire();
		if(this.estado == Estado.INICIAL) {
			this.estado = Estado.CARREGANDO;
			
			System.out.println("PERMITIR EMBARQUE");
			
			this.mutex.release();
		}	
	}
	
	/**
	 * O carro começa a corrida
	 * @throws InterruptedException
	 */
	public synchronized void correr() throws InterruptedException {
		this.mutex.acquire();
		if(this.estado.equals(Estado.CHEIO)) {
			
			System.out.println("CORRENDO");
			
			this.mutex.release();
		}
		
	}
	
	/**
	 * Permite o descarregamento de passageiros
	 * @throws InterruptedException 
	 */
	public synchronized void descarregar() throws InterruptedException {
		
		System.out.println("PERMITIR DESEMBARQUE");
		this.estado = Estado.DESCARREGANDO;
		this.filaCheia.release();
		this.mutex.release();
	}
	
	//GETTERS
	
	public int getQtd_embarque() {
		return qtd_embarque;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public Estado getEstado() {
		return estado;
	}
}
