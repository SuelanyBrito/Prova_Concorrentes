package questao3;

public class Aluno extends Thread {
	
	public Mesa m;
	
	public Aluno(Mesa m) {
		this.m = m;
	}
	
	@Override
	public void run() {
		
		try {
			m.acao();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
