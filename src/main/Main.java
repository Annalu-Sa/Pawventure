package main;

import javax.swing.JFrame;

public class Main {
    public static void main (String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//permite que a janela feche quando o usuario clica em "x"
        window.setResizable(false);//impede que a janela seja redimensionada
        window.setTitle("Pawventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();//faz com que a janela esteja do tamanho que caiba no definido = GamePanel

        window.setLocationRelativeTo(null);//janela é posta no centro da tela
        window.setVisible(true);//pra conseguirmos ver a janela

        gamePanel.startGameThread();
    }
}
