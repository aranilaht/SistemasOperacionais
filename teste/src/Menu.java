package src;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu
{
	@SuppressWarnings("unused")
	private controleBrincadeira bola;
	private int tamCesto;
	private JFrame jf_menu;
	private JLabel lb_menu;
	private JButton bt_iniciar;
	private JButton bt_sair;
	private ImageIcon im_menu;
	private ImageIcon im_iniciar;
	private ImageIcon im_sair;
	private ImageIcon im_iniciar_entered;
	private ImageIcon im_sair_entered;



	private Timer timer;

	public Menu()
	{
		jf_menu = new JFrame("Brincadeira de Crianca");
		im_menu = criarImageIcon("../src/imgs/menu/menu.jpeg", "Menu");
		im_iniciar = criarImageIcon("../src/imgs/menu/bt_iniciar_default.png", "Iniciar");
		im_sair = criarImageIcon("../src/imgs/menu/bt_sair_default.png", "Sair");
		im_iniciar_entered = criarImageIcon("../src/imgs/menu/bt_iniciar_sob.png", "Iniciar Entered");
		im_sair_entered = criarImageIcon("../src/imgs/menu/bt_sair_sob.png", "Sair Entered");
		
		lb_menu = new JLabel(im_menu);
		bt_iniciar = new JButton();
		bt_sair = new JButton();
		bt_iniciar.setIcon(im_iniciar);
		bt_sair.setIcon(im_sair);

	
		bt_iniciar.addMouseListener(new MouseListener()
		{
            public void mouseClicked(MouseEvent e)
            {
            	
            	String qntBolas = (JOptionPane.showInputDialog(jf_menu, "Capacidade do cesto"));
            	
            	try {
	            	if(qntBolas == null || qntBolas == "") {
	            		JOptionPane.showMessageDialog(jf_menu, "Valor invalido");
	            		
	            	}else if(Integer.parseInt(qntBolas) < 1){
	            		JOptionPane.showMessageDialog(jf_menu, "Capacidade minima excedida(min:1)");
	            		
	            	} else {
                        tamCesto = Integer.parseInt(qntBolas);
						iniciar(tamCesto);
                        jf_menu.dispose();

                    }
            	}catch(Exception er) {
                    JOptionPane.showMessageDialog(jf_menu, "Valor Invalido");
            	}
				
				
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
                bt_iniciar.setIcon(im_iniciar_entered);
            }
            public void mouseExited(MouseEvent e) {
            	bt_iniciar.setIcon(im_iniciar);
            }
			
        });
		
		bt_sair.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            	System.exit(0);
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
                bt_sair.setIcon(im_sair_entered);
            }
            public void mouseExited(MouseEvent e) {
            	bt_sair.setIcon(im_sair);
            }
        });
	}
		
	public void apresenta()
	{
			lb_menu.setBounds(0 ,0 , 640, 480);
			bt_iniciar.setBounds(120, 360, 171, 55);
			bt_sair.setBounds(300, 360, 171, 55);
			bt_iniciar.setBackground(new Color(0, 0, 0, 0));
			bt_sair.setBackground(new Color(0, 0, 0, 0));
			bt_iniciar.setFocusable(false);
			bt_iniciar.setBorder(null);
			bt_sair.setFocusable(false);
			bt_iniciar.setContentAreaFilled(false);
			bt_iniciar.setBorderPainted(false);
			bt_sair.setBorder(null);
			jf_menu.add(bt_iniciar);
			jf_menu.add(bt_sair);
			jf_menu.add(lb_menu);
			jf_menu.setSize(640, 480);
			jf_menu.setLocationRelativeTo(null);
			jf_menu.setResizable(false);
			jf_menu.setVisible(true);
		}
	
	public void iniciar(int tamCesto) {
		bola = new controleBrincadeira(tamCesto);
	
	}

	public void main(String[] args) {
        // invokeLater() is used here to prevent our graphics processing from
        // blocking the GUI. https://stackoverflow.com/a/22534931/4655368
        // this is a lot of boilerplate code that you shouldn't be too concerned about.
        // just know that when main runs it will call initWindow() once.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                apresenta();
            }
        });
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
	
		//private void drawScore(Graphics g) {
			// set the text to be displayed
			//String text = "$" + player.getScore();
			// we need to cast the Graphics to Graphics2D to draw nicer text
		//	Graphics2D g2d = (Graphics2D) g;
			//g2d.setRenderingHint(
		//		RenderingHints.KEY_TEXT_ANTIALIASING,
		//		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//	g2d.setRenderingHint(
		//		RenderingHints.KEY_RENDERING,
		//		RenderingHints.VALUE_RENDER_QUALITY);
		//	g2d.setRenderingHint(
		//		RenderingHints.KEY_FRACTIONALMETRICS,
		//		RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		//	// set the text color and font
		//	g2d.setColor(new Color(30, 201, 139));
		//	g2d.setFont(new Font("Lato", Font.BOLD, 25));
		//	// draw the score in the bottom center of the screen
			// https://stackoverflow.com/a/27740330/4655368
		//	FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
			// the text will be contained within this rectangle.
			// here I've sized it to be the entire bottom row of board tiles
		//	Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
			// determine the x coordinate for the text
		//	int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
			// determine the y coordinate for the text
			// (note we add the ascent, as in java 2d 0 is top of the screen)
		//	int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
			// draw the string
		//	g2d.drawString(text, x, y);
	//	}
	
	}