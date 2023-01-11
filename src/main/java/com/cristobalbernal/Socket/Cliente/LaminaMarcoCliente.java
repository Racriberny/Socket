package com.cristobalbernal.Socket.Cliente;

import javax.swing.*;

public class LaminaMarcoCliente extends JPanel {
    private JTextField campo1;
    private JButton miBoton;
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
}
