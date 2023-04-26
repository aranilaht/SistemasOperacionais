package src;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.Semaphore;

public class controleBrincadeira {
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

	public controleBrincadeira(int tamCesto) {
		this.cesto = new Cesto(tamCesto);
		this.mutex = new Semaphore(1);

		im_base = construir();
		im_add = criarImageIcon("/src/imgs/locais/add_default.png", "Adicionar Crianca");
		lb_base = new JLabel(im_base);
		bt_add = new JButton(im_add);
		lb_base.setBounds(0, 0, 640, 480);
		bt_add.setBounds(30, 20, 60, 60);
		bt_add.setBackground(new Color(0, 0, 0, 0));
		bt_add.setBorder(null);
		bt_add.setFocusable(false);
		bt_add.setContentAreaFilled(false);
		bt_add.setBorderPainted(false);
		bt_add.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

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
				if (result == JOptionPane.OK_OPTION) {
					tempo_brincando = barField.getText();
					tempo_quieta = homeField.getText();
					if (temBola1.isSelected()) {
						temBola = true;
					} else {
						temBola = false;
					}

				}

				if (tempo_brincando == null || tempo_brincando == "" || tempo_quieta == null || tempo_quieta == "") {
				}

				else {
					try {

						Random gerador = new Random();
						int x = gerador.nextInt(640);
						int y = gerador.nextInt(480);
						int tb, tq;

						tb = Integer.parseInt(tempo_brincando);
						tq = Integer.parseInt(tempo_quieta);

						addChildToFrame(nome, temBola, tb, tq, cesto, mutex, base, myPanel);
						
					}

					catch (Exception er) {
						JOptionPane.showMessageDialog(base, "Valor invalido");
					}
				}
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				bt_add.setIcon(criarImageIcon("/src/imgs/locais/add_entered.png", "Adicionar Cliente"));
			}

			public void mouseExited(MouseEvent e) {
				bt_add.setIcon(criarImageIcon("/src/imgs/locais/add_default.png", "Adicionar Cliente"));
			}
		});

		base = new JFrame("Brincadeira de Crianca");
		base.setLayout(null);

		JLabel addLabel = new JLabel("Adicionar criança (thread)");
		addLabel.setBounds(100, 20, 400, 60);
		addLabel.setForeground(Color.BLACK);

		int fontSize = 24;
		Font currentFont = addLabel.getFont();
		Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), fontSize);
		addLabel.setFont(newFont);

		base.add(addLabel);

		base.setSize(450, 150);
		base.setLocationRelativeTo(null);
		base.getContentPane().add(bt_add);
		base.getContentPane().add(lb_base);
		base.setResizable(false);
		base.setVisible(true);
	}

	public void addChildToFrame(String nome, Boolean temBola, int tb, int tq, Cesto cesto, Semaphore mutex, JFrame base,
			JPanel myPanel) {

		JLabel childLabel = new JLabel();
		JFrame mainFrame = new JFrame(nome);
		JPanel panel = new JPanel();
		JLabelWrapper JLabelWrapper = new JLabelWrapper(childLabel, panel);
		Crianca crianca = new Crianca(nome, temBola, tb, tq, cesto, mutex, base, JLabelWrapper);

		System.out.println( "Nome: " + crianca.getNome());
        System.out.println("Tempo Brincando: " + tb + " || Tempo Quieta: " + tq);

		JPanel backgroundPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage backgroundImage = null;
				try {
					backgroundImage = ImageIO.read(new File("src/imgs/locais/mesa_type_1.jpg"));
					g.drawImage(backgroundImage, 0, 0, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		mainFrame.setContentPane(backgroundPanel);

		mainFrame.setSize(300, 145);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setOpaque(false);

		childLabel.setIcon(getChildIcon());
		childLabel.setForeground(getRandomColor());
		childLabel.setOpaque(false);

		panel.add(childLabel);
		crianca.start();
		mainFrame.add(panel);
		mainFrame.setVisible(true);
	}

	public ImageIcon construir() {
		return criarImageIcon("/src/imgs/locais/mesa_type_1.jpg", "Fundo Padrao");

	}

	public Color getRandomColor() {
		Random random = new Random();
		Color[] colorsArray = new Color[10];
		colorsArray[0] = Color.BLACK;
		colorsArray[1] = Color.BLUE;
		colorsArray[2] = Color.DARK_GRAY;
		colorsArray[3] = Color.GREEN;
		colorsArray[4] = Color.MAGENTA;
		colorsArray[5] = Color.PINK;
		colorsArray[6] = Color.ORANGE;
		colorsArray[7] = Color.RED;
		colorsArray[8] = Color.LIGHT_GRAY;
		colorsArray[9] = Color.CYAN;
		int randomIndex = random.nextInt(10);
		Color randomElement = colorsArray[randomIndex];

		return randomElement;
	}

	public ImageIcon criarImageIcon(String caminho, String descricao) {
		java.net.URL imgURL = getClass().getResource(caminho);
		if (imgURL != null) {
			return new ImageIcon(imgURL, descricao);
		} else {
			System.err.println("Nao foi possivel carregar o arquivo de imagem: " + caminho);
			return null;
		}
	}

	private static ImageIcon getChildIcon() {
		URL imageChildURL = Main.class.getResource("/src/imgs/andando/green-front1.png");
		assert imageChildURL != null;

		return new ImageIcon(
				imageChildURL);
	}
}
