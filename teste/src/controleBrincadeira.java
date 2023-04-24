package src;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.swing.*;

public class controleBrincadeira
{
	private JFrame base;
	private JCheckBox temBola1;
	private Cesto cesto;
	private boolean temBola;
	private JLabel lb_base;
	private ImageIcon im_base;
	private JButton bt_add;
	private ImageIcon im_add;
	public static Semaphore mutex;
	List<Crianca> criancas = new ArrayList<>();

	Crianca newCrianca;

	public controleBrincadeira(int tamCesto)
	{
		this.cesto = new Cesto(tamCesto);
		this.mutex = new Semaphore(1);

		im_base = construir();
		im_add = criarImageIcon("../src/imgs/locais/add_default.png", "Adicionar Crianca");
		lb_base = new JLabel(im_base);
		bt_add = new JButton(im_add);
		lb_base.setBounds(0, 0, 640, 480);
		bt_add.setBounds(50, 10, 60, 60);
		bt_add.setBackground(new Color(0, 0, 0, 0));
		bt_add.setBorder(null);
		bt_add.setFocusable(false);
    	bt_add.setContentAreaFilled(false);
    	bt_add.setBorderPainted(false);
		bt_add.addMouseListener(new MouseListener()
		{
            public void mouseClicked(MouseEvent e)
            {
				
            	String tempo_brincando = "", tempo_quieta = "", nome = "Sem nome";
            	JTextField barField = new JTextField(5);
                JTextField homeField = new JTextField(5);
                JPanel myPanel = new JPanel();
				
                
                nome = JOptionPane.showInputDialog(base, "Nome da Crianca");
                
                myPanel.add(new JLabel("Tempo Brincando:"));
                myPanel.add(barField);

                myPanel.add(Box.createHorizontalStrut(15)); 
                myPanel.add(new JLabel("Tempo Quieta:"));
                myPanel.add(homeField);

				temBola1 = new JCheckBox("Tem Bola?", true);
				myPanel.add(temBola1);

				

                int result = JOptionPane.showConfirmDialog(null, myPanel, 
                         "Insira os Tempos", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION)
                {
                   tempo_brincando = barField.getText();
				   tempo_quieta = homeField.getText();
				   if (temBola1.isSelected()){
					temBola = true;
				}
				else{temBola=false;}

                }
				
                if(tempo_brincando == null || tempo_brincando == "" || tempo_quieta == null || tempo_quieta == "") {} 
			
                else
                {
            		try
            		{
            			int tb, tq;
            			
            			tb = Integer.parseInt(tempo_brincando);
            			tq = Integer.parseInt(tempo_quieta);

						Crianca crianca = new Crianca(nome, temBola, tb, tq, cesto, mutex, base);
						System.out.println( "Nome: " + crianca.getNome());
            			System.out.println("Tempo Brincando: " + tb + " || Tempo Quieta: " + tq);
						
				
						criancas.add(crianca);
						crianca.start();

                    }
            		
                    catch(Exception er) {
                        JOptionPane.showMessageDialog(base, "Valor invalido");
                    }
            	}
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
                bt_add.setIcon(criarImageIcon("../src/imgs/locais/add_entered.png", "Adicionar Cliente"));
            }
            public void mouseExited(MouseEvent e) {
            	bt_add.setIcon(criarImageIcon("../src/imgs/locais/add_default.png", "Adicionar Cliente"));
            }
        });
		
		base = new JFrame("Brincadeira de Crianca");
		base.setLayout(null);
		base.setSize(640, 480);
		base.setLocationRelativeTo(null);
		base.add(bt_add);
		base.add(lb_base);
		base.setResizable(false);
		base.setVisible(true);
	} 



	public ImageIcon construir()
	{
			return criarImageIcon("../src/imgs/locais/mesa_type_1.jpg","Fundo Padrao");

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
