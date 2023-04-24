package src;

import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;

public class Crianca extends Thread {
    private String nome;
    private boolean temBola;
    private int tempoBrincadeira, i=0;
    private int tempoEspera, tamCesto;
    private Cesto cesto;
	private Imagens imagens;
    public static Semaphore mutex;
    JFrame base;
	
    public Crianca(String nome, boolean temBola, int tempoBrincadeira, int tempoEspera, Cesto cesto, Semaphore mutex, JFrame base) {


		this.nome = nome;
        this.temBola = temBola;
        this.tempoBrincadeira = tempoBrincadeira*1000;
        this.tempoEspera = tempoEspera*1000;
        this.tamCesto = cesto.getTamCesto();
		this.cesto = cesto;
		this.mutex = mutex;
        this.base = base;
        

    }

    public String getNome() {
        return this.nome;
    }

    public boolean temBola() {
        return this.temBola;
    }

    public void pegarBola() throws InterruptedException {
        this.imagens = new Imagens(base);
		Random gerador = new Random();
		int iSprite = gerador.nextInt(3);
        imagens.buscaAnim(iSprite);
		cesto.removerBola();
		mutex.acquire();
		temBola=true;
        mutex.release();
    }

    public void devolverBola() throws InterruptedException {
		if (cesto.adicionarBola()){
			mutex.acquire();
			temBola = false;
		} 
		mutex.release();
    }

    public void brincar() {
        long time = System.currentTimeMillis();
        int contIteracoes = 0;
		while (System.currentTimeMillis() - time < tempoBrincadeira){
			contIteracoes++;
		}
		
    }
    
    public void quieto() {
        long time = System.currentTimeMillis();
		long contIteracoes = 0;
		while (System.currentTimeMillis() - time < tempoEspera){
			contIteracoes++;
		}
}

    public void run() {
        while (true) {
            try {
                if (temBola == true) { // Se a criança tem bola, brinca com a bola
                    System.out.println(nome + " está brincando.");
                    brincar();
                } else { // Se a criança não tem bola, procura por uma no cesto
                    System.out.println(nome + " está procurando uma bola.");
                    pegarBola();
                    System.out.println(nome + " está brincando.");
                    brincar();
                }
                System.out.println(nome + " devolveu a bola.");
                devolverBola();
                System.out.println(nome + " está esperando.");
                quieto(); // Fica quieta pelo tempo determinado
            } catch (InterruptedException e) {
                System.out.println(nome + " foi interrompido.");
                return;
            }
        }

    }

}