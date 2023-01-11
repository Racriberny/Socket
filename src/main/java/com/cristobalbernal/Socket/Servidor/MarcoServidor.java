package com.cristobalbernal.Socket.Servidor;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MarcoServidor extends JFrame implements Runnable{
    private JTextArea areaTexto;
    public MarcoServidor(){
        setBounds(1200,300,280,350);
        JPanel milamina = new JPanel();
        milamina.setLayout(new BorderLayout());
        areaTexto = new JTextArea();
        milamina.add(areaTexto,BorderLayout.CENTER);
        add(milamina);
        setVisible(true);
        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    @Override
    public void run() {
        //System.out.println("Estoy a la escucha");
        try {
            ServerSocket servidor = new ServerSocket(9999);

            while (true){

                Socket miSocket = servidor.accept();
                DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());
                String mensaje_texto = flujo_entrada.readUTF();
                areaTexto.append("\n" + mensaje_texto);
                miSocket.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
