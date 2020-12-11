package questao3;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Semaphore sem = new Semaphore(1);
		Semaphore cheio = new Semaphore(0);
		Semaphore incompleto = new Semaphore(1);
		Mesa mesa = new Mesa(sem, cheio, incompleto);

		Aluno a1 = new Aluno(mesa);
		Aluno a2 = new Aluno(mesa);
		Aluno a3 = new Aluno(mesa);
		Aluno a4 = new Aluno(mesa);
		Aluno a5 = new Aluno(mesa);
		
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
		
	}

}
