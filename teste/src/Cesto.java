package src;

import java.util.concurrent.Semaphore;

public class Cesto {

    private int tamCesto;
    public static Semaphore mutex;

    private int numeroBolas;
    int tamFila =4;

    private Semaphore cesto_vazio, cesto_cheio;


    public Cesto(int tamCesto){

        this.tamCesto = tamCesto;
        this.cesto_vazio = new Semaphore(tamCesto, true);
        this.cesto_cheio = new Semaphore(tamCesto, true);
        cesto_cheio.tryAcquire(tamCesto);
        cesto_vazio.release(tamCesto);
        this.mutex = new Semaphore(1, true);
        this.numeroBolas = 0;

        }

    public void setFila(int i){
        this.tamFila = i;
     }
     public int getFila() {
        return this.tamFila;
    }

    public int getTamCesto(){
        return tamCesto;
    }

    public boolean adicionarBola() throws InterruptedException {
        cesto_vazio.acquire();
        mutex.acquire();
        numeroBolas++;
        mutex.release();
        cesto_cheio.release();
        return true;
    }

    public boolean removerBola() throws InterruptedException {
        cesto_cheio.acquire();
        mutex.acquire();
        numeroBolas--;
        mutex.release();
        cesto_vazio.release();
        return true;
    }
    
    public boolean haBolasDisponiveis() throws InterruptedException {
        mutex.acquire();
        boolean resultado = (numeroBolas > 0);
        mutex.release();
        return resultado;
    }


}
