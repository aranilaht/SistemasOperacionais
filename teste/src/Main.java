package src;

import java.util.concurrent.Semaphore;

public class Main
{	
	public static void main(String[] args)
	{
		Semaphore mutex = new Semaphore(1);
		Menu menu = new Menu();
		menu.apresenta();
	}
}