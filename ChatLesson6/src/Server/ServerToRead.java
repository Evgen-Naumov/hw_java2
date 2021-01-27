package Server;

import java.io.*;
import java.net.Socket;

public class ServerToRead extends Thread {
    private Socket socket;

    public ServerToRead(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            StringBuilder stringBuilder;
            String str;
            boolean toRead = true;
            do {
                try {
                    str = in.readUTF();

                    if (str.equals("//end")) {
                        System.out.println("Клиент отключился");
                        in.close();
                        socket.close();
                        toRead = false;
                    } else {
                        stringBuilder  = new StringBuilder(str);
                        System.out.println("Клиент/ " + stringBuilder.toString());
                    }

                } catch(EOFException e){
                    System.out.println("Клиент отключился");
                    socket.close();
                    toRead = false;

                }

            } while (toRead);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

