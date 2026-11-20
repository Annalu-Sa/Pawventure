package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerP2 implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {}

    public boolean teleportPressed;
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();//retorna o int keyCode associado a tecla neste evento

        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        //botao de teletransporte do player2 pro player1
        if(code == KeyEvent.VK_L){
            teleportPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_L){
            teleportPressed = false;
        }
    }
}