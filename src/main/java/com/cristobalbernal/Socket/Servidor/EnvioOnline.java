package com.cristobalbernal.Socket.Servidor;

import com.cristobalbernal.Socket.Cliente.PaqueteEnviado;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EnvioOnline extends WindowAdapter {
    public void windowsOpened(WindowEvent e){
        try{
            Socket miSocket = new Socket("192.168.20.103",9999);
            PaqueteEnviado datos = new PaqueteEnviado();
            datos.setMensaje("Online");
            ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
            paquete_datos.writeObject(datos);
            miSocket.close();
        }catch (Exception ex){

        }
    }
}
