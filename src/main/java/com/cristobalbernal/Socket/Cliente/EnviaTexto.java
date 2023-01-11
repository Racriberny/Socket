package com.cristobalbernal.Socket.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;


public class EnviaTexto implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Socket miSockeet = new Socket("192.168.20.103",9999);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
