package com.cristobalbernal.Socket.Cliente;

import com.cristobalbernal.Socket.Cliente.LaminaMarcoCliente;

import javax.swing.*;

public class MarcoCliente extends JFrame {
    public MarcoCliente(){
        setBounds(600,300,280,350);
        LaminaMarcoCliente milamina = new LaminaMarcoCliente();
        add(milamina);
        setVisible(true);
    }
}
