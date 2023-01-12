package com.cristobalbernal.Socket.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LaminaMarcoCliente extends JPanel {

    public LaminaMarcoCliente(){
        nick = new JTextField(5);
        add(nick);
        JLabel texto = new JLabel("-CHAT-");
        add(texto);
        ip = new JTextField(8);
        add(ip);
        campoChat = new JTextArea(12,20);
        add(campoChat);
        campo1 = new JTextField(20);
        add(campo1);
        miBoton = new JButton("Enviar");
        EnviaTexto miEvento = new EnviaTexto();
        miBoton.addActionListener(miEvento);
        add(miBoton);
    }
    private JTextField campo1,nick,ip;
    private JButton miBoton;
    private  JTextArea campoChat;

    private class EnviaTexto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket miSockeet = new Socket("192.168.20.103",9999);
                PaqueteEnviado datos = new PaqueteEnviado();
                datos.setNick(nick.getText());
                datos.setIp(ip.getText());
                datos.setMensaje(campo1.getText());
                ObjectOutputStream paquete_dato = new ObjectOutputStream(miSockeet.getOutputStream());
                paquete_dato.writeObject(datos);
                miSockeet.close();

                /*
                DataOutputStream flujo_salida = new DataOutputStream(miSockeet.getOutputStream());
                flujo_salida.writeUTF(campo1.getText());
                flujo_salida.close();*/

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}


