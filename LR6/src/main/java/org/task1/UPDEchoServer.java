package org.task1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UPDEchoServer extends UPDServer {

    public final static int DEFAULT_PORT = 7;

    public UPDEchoServer() {
        super(DEFAULT_PORT);
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket request) {
        DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
        try {
            socket.send(reply);
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\n" + e);
        }
    }

    public static void main(String[] args) {
        UPDServer updServer = new UPDEchoServer();
        Thread thread = new Thread(updServer);
        thread.start();
        System.out.println("Start echo-server.");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage() + "\n" + e);
        }
        updServer.shutDown();
        System.out.println("Finish echo-server.");
    }
}
