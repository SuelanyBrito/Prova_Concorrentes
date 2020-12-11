package questao2;

import java.util.concurrent.Semaphore;

public class Barco {
	private static final int capacidade = 4;
	private int alunosUEPB;
	private int alunosUFCG;
	private Semaphore mutex;
	//private Semaphore tresAlunos;
	private Semaphore cheio;
	
	public Barco(Semaphore mutex) {
		this.alunosUEPB = 0;
		this.alunosUFCG = 0;
		this.mutex = mutex;
		//this.tresAlunos = new Semaphore(0);
		this.cheio = new Semaphore(0);
	}
	
	/**
	 * Metodo compartilhado entre as threads que tem por função adicionar
	 * alunos no barco
	 * @param tipo: tipo do aluno (UEPB ou UFCG)
	 * @throws InterruptedException
	 */
	public void embarcar(String tipo) throws InterruptedException {
		this.mutex.acquire();
		
		int value = alunosUEPB + alunosUFCG;
		
		if(value < this.capacidade) {
			if(tipo.equals("UEPB")) {
				this.addUEPB();
			}else if(tipo.equals("UFCG")) {
				this.addUFCG();
			}
			this.mutex.release();
		}
		else {
			this.cheio.acquire();
		}
		
		
		
	}
	
	/**
	 * Adicionar alunos da UEPB
	 * Se já houver mais de 3 alunos da ufcg esperar
	 * @throws InterruptedException
	 */
	public void addUEPB() throws InterruptedException {
		if(this.alunosUFCG < 3) {
			this.alunosUEPB++;
			System.out.println("ADD ALUNO UEPB");
		}
		
		
	}
	
	/**
	 * Adicionar alunos da ufcg
	 * Se os ja houver mais de 3 alunos da uepb esperar 
	 * @throws InterruptedException
	 */
	
	public void addUFCG() throws InterruptedException {
		
		if(this.alunosUEPB < 3) {
			this.alunosUFCG++;
			System.out.println("ADD ALUNO UFCG");
		}
		
	}
	
	
	/**
	 * Ao chamar esse métodos indica que o barco chegar ao destino e os alunos iram desembarcar
	 * @throws InterruptedException 
	 */
	public void remar() throws InterruptedException {
		
			this.cheio.acquire();
			System.out.println("REMANDO...");
		
			System.out.println("Chegou no destino!!");
		
			this.alunosUEPB = 0;
			this.alunosUFCG = 0;
		
			this.mutex.release();
			this.cheio.release();
		
		
	}
	
}
