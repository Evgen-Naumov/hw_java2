package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        List<Socket> socketList = new ArrayList<>();
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)){
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            socketList.add(socket);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String str;
            Scanner s = new Scanner(System.in);
            //поток чтения
            new ServerToRead(socket).start();
            //
            do {
                str = s.next();
                if(str.isEmpty()){
                    continue;
                }
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(str);
            } while (!"//end".equals(str));

//               new ServerToWrite(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}