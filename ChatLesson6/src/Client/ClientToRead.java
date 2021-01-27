package Client;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClientToRead extends Thread{
    private Socket socket;

    public ClientToRead(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String str;
            boolean toRead= true;
            do {
                try {
                    str = in.readUTF();

                    if (str.equals("//end")) {
                        toRead = false;
                        System.out.println("Соединение завершено");
                        in.close();
                        socket.close();
                    }else {
                        System.out.println("Сервер/ " + str.toString());
                    }
                } catch (EOFException e){
                    //e.printStackTrace();
                    toRead = false;
                    System.out.println("Соединение завершено");
                    in.close();
                    socket.close();
                }


            } while (toRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

