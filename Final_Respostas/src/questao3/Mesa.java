package questao3;

import java.util.concurrent.Semaphore;

public class Mesa {

	private final static int VAGAS = 5;
	private Semaphore sem, cheio, incompleto;
	private int pessoas;
	
	
	public Mesa(Semaphore sem, Semaphore cheio, Semaphore incompleto) {
		this.sem = sem;
		this.cheio = cheio;
		this.incompleto = incompleto;
		this.pessoas = 0;
	}
	
	
	public void acao() throws InterruptedException {
		this.incompleto.acquire();
		sem.acquire();
		
	    if (pessoas == VAGAS) {
	        this.cheio.release();
	    }else {
	    	pessoas++;
	    	this.incompleto.release();
		    sem.release();
	    }
	    
	    beber();

	    sair();	  
	    
	}
	
	public void beber() {
		System.out.println("Bebendo");
	}
	
	// Impedir que algum aluno fique sozinho na mesa
	public void sair() throws InterruptedException {
		sem.acquire(); 
	    pessoas--;
		System.out.println("Saindo");
		sem.release();
	}
	
	
}
