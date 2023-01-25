package com.cristobalbernal.Socket.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LaminaMarcoCliente extends JPanel implements Runnable {

    public LaminaMarcoCliente(){
        String nick_usuario = JOptionPane.showInputDialog("Nick: ");
        JLabel n_nick = new JLabel("Nick: ");
        add(n_nick);
        nick = new JLabel();
        nick.setText(nick_usuario);
        add(nick);
        JLabel texto = new JLabel("Online: ");
        add(texto);
        ip = new JComboBox();
        ip.addItem("Usuario 1");
        ip.addItem("Usuario 2");
        ip.addItem("Usuario 3");
        add(ip);
        campoChat = new JTextArea(12,20);
        add(campoChat);
        campo1 = new JTextField(20);
        add(campo1);
        miBoton = new JButton("Enviar");
        EnviaTexto miEvento = new EnviaTexto();
        miBoton.addActionListener(miEvento);
        add(miBoton);
        Thread miHilo = new Thread(this);
        miHilo.start();
    }
    private JTextField campo1;
    private JComboBox ip;
    private JLabel nick;
    private JButton miBoton;
    private  JTextArea campoChat;

    @Override
    public void run() {
        try {
            ServerSocket servidor_cliente = new ServerSocket(9090);
            Socket cliente;
            PaqueteEnviado paqueteRecibido;
            while (true){
                cliente = servidor_cliente.accept();
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido = (PaqueteEnviado) flujoEntrada.readObject();
                campoChat.append("\n" + paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private class EnviaTexto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            campoChat.append("\n" + campo1.getText());


            try {
                Socket miSockeet = new Socket("192.168.20.103",9999);
                PaqueteEnviado datos = new PaqueteEnviado();
                datos.setNick(nick.getText());
                datos.setIp(ip.getSelectedItem().toString());
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


