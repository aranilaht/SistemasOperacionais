package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import src.Imagens;

public class Container extends JPanel implements ActionListener {
    private Image fundo;
    private Timer timer;
    public static boolean Sinal = false;
    public int i = 0, j = 0, flag = 4, qtd = 3;
    public int menosflag = 0 - flag;
    private int distancia = 220 / flag;
    private int length;

    public Container() {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
