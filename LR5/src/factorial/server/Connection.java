package factorial.server;


import factorial.interfaces.Executable;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import static factorial.server.ServerFrame.Text;

public class Connection extends Thread {
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Socket socket;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public void run() {
        while (true) {
            try {
                try {

                    String classFile = (String) ois.readObject();
                    classFile.replaceFirst("client", "server");
                    byte[] b = (byte[]) ois.readObject();
                    FileOutputStream fos = new FileOutputStream(classFile);
                    fos.write(b);
                    Executable executable = (Executable) ois.readObject();

                    Text.append("Start counting" + "\n");
                    long startTime = System.nanoTime();
                    Object output = executable.execute();
                    long endTime = System.nanoTime();
                    long competitionTime = endTime - startTime;
                    ResultImpl result = new ResultImpl(output, competitionTime);

                    classFile.replaceFirst("server", "client");
                    oos.writeObject(classFile);
                    FileInputStream fis = new FileInputStream(classFile);
                    byte[] bo = new byte[fis.available()];
                    fis.read(bo);
                    oos.writeObject(bo);
                    oos.writeObject(result);
                    Text.append("Sending result..." + "\n");


                } catch (SocketException e) {
                    break;
                }

            }catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
            }
        }

    }


}
