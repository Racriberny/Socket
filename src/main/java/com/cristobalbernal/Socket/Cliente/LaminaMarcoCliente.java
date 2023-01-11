package com.cristobalbernal.Socket.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LaminaMarcoCliente extends JPanel {

    public LaminaMarcoCliente(){
        JLabel texto = new JLabel("CLIENTE");
        add(texto);
        campo1 = new JTextField(20);
        add(campo1);
        miBoton = new JButton("Enviar");
        EnviaTexto miEvento = new EnviaTexto();
        miBoton.addActionListener(miEvento);
        add(miBoton);
    }
    private JTextField campo1;
    private JButton miBoton;

    private class EnviaTexto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket miSockeet = new Socket("192.168.20.103",9999);
                DataOutputStream flujo_salida = new DataOutputStream(miSockeet.getOutputStream());
                flujo_salida.writeUTF(campo1.getText());
                flujo_salida.close();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}


