package questao2;

public class Barco {
	private static final int capacidade = 4;
	private int alunosUEPB;
	private int alunosUFCG;
	
	public Barco() {
		this.alunosUEPB = 0;
		this.alunosUFCG = 0;
	}
	
	/**
	 * Metodo compartilhado entre as threads que tem por função adicionar
	 * alunos no barco
	 * @param tipo: tipo do aluno (UEPB ou UFCG)
	 * @throws InterruptedException
	 */
	public synchronized void embarcar(String tipo) throws InterruptedException {
		int value = alunosUEPB + alunosUFCG;
		while(value <= this.capacidade) {
			this.wait();
		}
		if(tipo.equals("UEPB")) {
			this.addUEPB();
		}else if(tipo.equals("UFCG")) {
			this.addUFCG();
		}
		
		this.notifyAll();
	}
	
	/**
	 * Adicionar alunos da UEPB
	 * Se já houver mais de 3 alunos da ufcg esperar
	 * @throws InterruptedException
	 */
	public synchronized void addUEPB() throws InterruptedException {
		while(this.alunosUFCG >= 3) {
			this.wait();
		}
		this.alunosUEPB++;
		this.notifyAll();
	}
	
	/**
	 * Adicionar alunos da ufcg
	 * Se os ja houver mais de 3 alunos da uepb esperar 
	 * @throws InterruptedException
	 */
	
	public synchronized void addUFCG() throws InterruptedException {
		while(this.alunosUEPB >= 3) {
			this.wait();
		}
		this.alunosUFCG++;
		this.notifyAll();
	}
	
	
	/**
	 * Ao chamar esse métodos indica que o barco chegar ao destino e os alunos iram desembarcar
	 */
	public synchronized void remar() {
		this.alunosUEPB = 0;
		this.alunosUFCG = 0;
		this.notifyAll();
	}
	
}
