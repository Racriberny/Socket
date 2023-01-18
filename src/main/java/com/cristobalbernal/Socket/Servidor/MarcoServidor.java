package com.cristobalbernal.Socket.Servidor;

import com.cristobalbernal.Socket.Cliente.PaqueteEnviado;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            String nick,ip,mensaje;
            PaqueteEnviado paqueteRecibidos;
            while (true){

                Socket miSocket = servidor.accept();

                ObjectInputStream paquete_Datos = new ObjectInputStream(miSocket.getInputStream());
                paqueteRecibidos = (PaqueteEnviado) paquete_Datos.readObject();
                nick = paqueteRecibidos.getNick();
                ip = paqueteRecibidos.getIp();
                mensaje = paqueteRecibidos.getMensaje();
                /*
                DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());
                String mensaje_texto = flujo_entrada.readUTF();
                areaTexto.append("\n" + mensaje_texto);

                 */
                areaTexto.append("\n" + nick + ": " + mensaje + " para " + ip);
                Socket enviarDestinatario = new Socket(ip,9090);
                ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviarDestinatario.getOutputStream());
                paqueteReenvio.writeObject(paqueteRecibidos);
                paqueteReenvio.close();
                enviarDestinatario.close();
                miSocket.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
