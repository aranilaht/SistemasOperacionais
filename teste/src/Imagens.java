package src;

import javax.swing.*;
import java.awt.Image;
import java.util.Random;

public class Imagens
{
    private int iSprite;
    private int x=20;
    private Image imagem;
	
	String name = "";
	
	JFrame base;
	JLabel fundo;
    JLabel sprite;
	
	
	int flag = 1;
	

	ImageIcon sp1; //andando
	ImageIcon sp2;
	ImageIcon sp3;
	ImageIcon sp4;
	ImageIcon sp5;
	ImageIcon sp6;
	ImageIcon sp7;
	ImageIcon sp8;
	ImageIcon sp9;
	ImageIcon sp10;
	ImageIcon sp11;
	ImageIcon sp12;
	ImageIcon bb1; //brincando
	ImageIcon bb2;
	ImageIcon devolve;
	ImageIcon busca1;
	ImageIcon busca2;

    public Imagens(JFrame base){
		JPanel exibivel = new JPanel();
		this.base = base;
		int i = 0;
		buscaImg(0, i);
        sprite = new JLabel(busca1);
		sprite.setIcon(busca1);
		sprite.setBounds(530,500,100,100);
		sprite.setLocation(630, 1);
		base.add(sprite);
		exibivel.add(sprite);
    }

	public void buscaAnim(int iSprite){
		flag = 5;
		int y, i;
		i=0;
		x= sprite.getX();
		y= sprite.getY();
		if (y>240 && x>240){
			i=2;
			buscaImg(i, iSprite);
			while(x!=320){
				while(y != 240){
					sprite.setLocation(x, y);
					switch(flag){
						case 5:
							sprite.setIcon(busca1);
							flag++;
							break;
						case 6:
							sprite.setIcon(busca2);
							flag = 5;
							break;
					}
					y = y--;
				}
				x= x--;
			}


		}
		if (y<240 && x<240){
			while(x!=320){
				i=3;
				buscaImg(i , iSprite);
				while(y != 240){
					sprite.setLocation(x, y);
					switch(flag){
						case 5:
							sprite.setIcon(busca1);
							flag++;
							break;
						case 6:
							sprite.setIcon(busca2);
							flag = 5;
							break;
					}
					y = y++;
				}
				x= x++;
			}
	}
}

    public void brincaImg(int i){
		switch(i)
        		{
					
        			case 0:
		            	
		            	if(iSprite == 0) {
		            		bb1 = criarImageIcon("/src/imgs/brincando/green/green-frontBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/green/green-frontBall2.png","Sprite 4");}
		            	if(iSprite == 1) {	
		            		bb1 = criarImageIcon("/src/imgs/brincando/red/red-frontBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/red/red-frontBall2.png","Sprite 4");}
		            	if(iSprite == 2) {	
		            		bb1 = criarImageIcon("/src/imgs/brincando/yellow/yellow-frontBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/yellow/yellow-frontBall2.png","Sprite 4");}
        			
		            	i++;
		            	break;
					case 1:
		            	
		            	if(iSprite == 0) {
		            		bb1 = criarImageIcon("/src/imgs/brincando/green/green-leftBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/green/green-leftBall2.png","Sprite 4");}
		            	if(iSprite == 2) {	
		            		bb1 = criarImageIcon("/src/imgs/brincando/yellow/yellow-leftBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/yellow/yellow-leftBall2.png","Sprite 4");}
		            	if(iSprite == 1) {	
		            		bb1 = criarImageIcon("/src/imgs/brincando/red/red-leftBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/red/red-leftBall2.png","Sprite 4");}
        			
		            	i++;
		            	break;
					case 2:
		          
		            	if(iSprite == 0) {
		            		bb1 = criarImageIcon("/src/imgs/brincando/green/green-rightBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/green/green-rightBall2.png","Sprite 4");}
		            	if(iSprite == 1) {	
		            		bb1 = criarImageIcon("/src/imgs/brincando/red/red-rightBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/red/red-rightBall2.png","Sprite 4");}
		            	if(iSprite == 2) {	
		            		bb1 = criarImageIcon("/src/imgs/brincando/yellow/yellow-rightBall1.png","Sprite 4");
							bb2 = criarImageIcon("/src/imgs/brincando/yellow/yellow-rightBall2.png","Sprite 4");}
        			
		            	i=0;
		            	break;
					}
	}

	public void buscaImg(int i, int iSprite){
		switch(i)
        		{
        			case 0:
		            	if(iSprite == 0) {
		            		busca1 = criarImageIcon("/src/imgs/andando/red-right1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/red-right2.png","Sprite 5");}
		            	if(iSprite == 1) {	
		            		busca1 = criarImageIcon("/src/imgs/andando/green-right1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/green-right2.png","Sprite 5");}
		            	if(iSprite == 2) {	
		            		busca1 = criarImageIcon("/src/imgs/andando/yellow-right1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/yellow-right2.png","Sprite 5");}
        			
		            	i++;
		            	break;
					case 1:
		            	if(iSprite == 1) {
		            		busca1 = criarImageIcon("/src/imgs/andando/green-left1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/green-left2.png","Sprite 5");}
		            	if(iSprite == 0) {	
		            		busca1 = criarImageIcon("/src/imgs/andando/red-left1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/red-left2.png","Sprite 5");}
		            	if(iSprite == 2) {	
		            		busca1 = criarImageIcon("/src/imgs/andando/yellow-left1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/yellow-left2.png","Sprite 5");}
        			
		            	i++;
		            	break;
					case 2:
		            	if(iSprite == 1) {
		            		busca1 = criarImageIcon("/src/imgs/andando/green-back1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/green-back2.png","Sprite 5");}
		            	if(iSprite == 0) {	
		            		busca1 = criarImageIcon("/src/imgs/andando/red-back1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/red-back2.png","Sprite 5");}
		            	if(iSprite == 2) {	
		            		busca1 = criarImageIcon("/src/imgs/andando/yellow-back1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/yellow-back2.png","Sprite 5");}
		            	i++;
		            	break;
					case 3:
		            	if(iSprite == 0) {
		            		busca1 = criarImageIcon("/src/imgs/andando/red-front1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/red-front2.png","Sprite 5");}
		            	if(iSprite == 1) {	
		            		busca1 = criarImageIcon("/src/imgs/andando/green-front1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/green-front2.png","Sprite 5");}
		            	if(iSprite == 2) {	
							busca1 = criarImageIcon("/src/imgs/andando/yellow-front1.png","Sprite 4");
							busca2 = criarImageIcon("/src/imgs/andando/yellow-front2.png","Sprite 5");}
		            	i=0;
		            	break;}

	}

	public void devolveImg(int i){
		switch(i)
        		{
        			case 0:
		            	sprite.setLocation(65, 260);
		            	if(iSprite == 0) {
		            		devolve = criarImageIcon("/src/imgs/brincando/green/green-rightBall1.png","Sprite 4");}
		            	if(iSprite == 1) {	
		            		devolve = criarImageIcon("/src/imgs/brincando/red/red-righBall1.png","Sprite 4");}
		            	if(iSprite == 2) {	
		            		devolve = criarImageIcon("/src/imgs/brincando/yellow/yellow-rightBall1.png","Sprite 4");}
		            	break;
					case 1:
		            	sprite.setLocation(210, 260);
		            	if(iSprite == 0) {
		            		devolve = criarImageIcon("/src/imgs/brincando/green/green-leftBall1.png","Sprite 4");}
		            	if(iSprite == 1) {	
		            		devolve = criarImageIcon("/src/imgs/brincando/red/red-leftBall1.png","Sprite 4");}
		            	if(iSprite == 2) {	
		            		devolve = criarImageIcon("/src/imgs/brincando/yellow/yellow-leftBall1.png","Sprite 4");}
		            	break;
					case 2:
		            	sprite.setLocation(25, 80);
		            	if(iSprite == 0) {
		            		devolve = criarImageIcon("/src/imgs/andando/green-back1.png","Sprite 4");}
		            	if(iSprite == 1) {	
		            		devolve = criarImageIcon("/src/imgs/andando/red-back1.png","Sprite 4");}
		            	if(iSprite == 2) {	
		            		devolve = criarImageIcon("/src/imgs/andando/yellow/yellow-back1.png","Sprite 4");}
		            	break;
					case 3:
		            	sprite.setLocation(90, 80);
		            	if(iSprite == 0) {
		            		devolve = criarImageIcon("/src/imgs/brincando/green/green-frontBall1.png","Sprite 4");}
		            	if(iSprite == 1) {	
		            		devolve = criarImageIcon("/src/imgs/brincando/red/red-frontBall1.png","Sprite 4");}
		            	if(iSprite == 2) {	
		            		devolve = criarImageIcon("/src/imgs/brincando/yellow/yellow-frontBall1.png","Sprite 4");}
		            	break;}


	}

	public ImageIcon criarImageIcon(String caminho, String descricao)
	{
		java.net.URL imgURL = getClass().getResource(caminho);
		if (imgURL != null) {
			return new ImageIcon(imgURL, descricao);
		} else {
			System.err.println("Nao foi possivel carregar o arquivo de imagem: " + caminho);
			return null;
		}
	}
}