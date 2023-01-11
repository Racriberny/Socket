package com.cristobalbernal.Socket.Servidor;

import javax.swing.*;
import java.awt.*;

public class MarcoServidor extends JFrame {
    private JTextArea areaTexto;
    public MarcoServidor(){
        setBounds(1200,300,280,350);
        JPanel milamina = new JPanel();
        milamina.setLayout(new BorderLayout());
        areaTexto = new JTextArea();
        milamina.add(areaTexto,BorderLayout.CENTER);
        add(milamina);
        setVisible(true);
    }
}
