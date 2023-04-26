package src;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.*;

public class Crianca extends Thread {

    private String nome;
    private boolean temBola;
    private int tempoBrincadeira, i=0;
    private int tempoEspera, tamCesto;
    private Cesto cesto;
	private Imagens imagens;
    public static Semaphore mutex;
    JFrame base;
	

    private JLabelWrapper JLabelWrapper;

    public Crianca(String nome, boolean temBola, int tempoBrincadeira, int tempoEspera, Cesto cesto, Semaphore mutex, JFrame base, JLabelWrapper JLabelWrapper) {


		this.nome = nome;
        this.temBola = temBola;
        this.tempoBrincadeira = tempoBrincadeira*1000;
        this.tempoEspera = tempoEspera*1000;
        this.tamCesto = cesto.getTamCesto();
		this.cesto = cesto;
		this.mutex = mutex;
        this.base = base;
        this.JLabelWrapper = JLabelWrapper;
        

    }

    public enum estadoCrianca{
        buscando,
        devolvendo,
        brincando1,
        brincando2,
        esperando1,
        esperando2,

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
        boolean flag = false;
        long time = System.currentTimeMillis();
        int contIteracoes = 0;
		while (System.currentTimeMillis() - time < tempoBrincadeira){
            if (flag){
                changeIcon(estadoCrianca.brincando1);
            } else{
                changeIcon(estadoCrianca.brincando2);
            }
            flag= !flag;
			contIteracoes++;}
        if (flag){
            changeIcon(estadoCrianca.brincando1);
        } 
		}
		
    
    public void quieto() {
        long time = System.currentTimeMillis();
		long contIteracoes = 0;
        boolean flag = false;
		while (System.currentTimeMillis() - time < tempoEspera){
            if(flag){
                changeIcon(estadoCrianca.esperando1);
            } else{
                changeIcon(estadoCrianca.esperando2);
            }
            flag = !flag;
			contIteracoes++;
		}
        if (flag){
          changeIcon(estadoCrianca.esperando1);
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                if (temBola == true) { // Se a criança tem bola, brinca com a bola
                    changeIcon(estadoCrianca.brincando1);
                    System.out.println(nome + " está brincando.");
                    brincar();
                } else { // Se a criança não tem bola, procura por uma no cesto
                    System.out.println(nome + " está procurando uma bola.");
                    changeIcon(estadoCrianca.buscando);
                    pegarBola();
                    System.out.println(nome + " está brincando.");
                    brincar();
                }
                changeIcon(estadoCrianca.devolvendo);
                System.out.println(nome + " devolveu a bola.");
                devolverBola();
                System.out.println(nome + " está esperando.");
                changeIcon(estadoCrianca.esperando1);
                quieto(); // Fica quieta pelo tempo determinado
            } catch (InterruptedException e) {
                System.out.println(nome + " foi interrompido.");
                return;
            }
        }

    }
    private void changeIcon(estadoCrianca estado) {
        ImageIcon childIcon = getChildIcon(estado);
        JLabelWrapper.lb_base2.setIcon(childIcon);
        JLabelWrapper.lb_base2.setText(getName());
       // JLabelWrapper.applicationPanel.repaint();
      }

    public static ImageIcon getChildIcon(estadoCrianca estado){
        URL imageChildURL = switch (estado){
            case devolvendo -> Main.class.getResource("/src/imgs/brincando/green/green-rightBall1.png");
            case buscando -> Main.class.getResource("/src/imgs/andando/green-right1.png");
            case brincando1 -> Main.class.getResource("/src/imgs/brincando/green/green-frontBall1.png");
            case brincando2 -> Main.class.getResource("/src/imgs/brincando/green/green-frontBall2.png");
            case esperando1 -> Main.class.getResource("/src/imgs/andando/green-right1.png");
            case esperando2 -> Main.class.getResource("/src/imgs/andando/green-right2.png");
        };
        assert imageChildURL != null;
        return new ImageIcon(
            imageChildURL
        );
    }

}
