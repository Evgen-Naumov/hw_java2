package Client;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws IOException{
        String str;
        Scanner s = new Scanner(System.in);

        try {
//            new ClientToWrite(socket).start();
            Socket socket = new Socket("localhost",8189);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //поток чтения
            new ClientToRead(socket).start();

            do {
                str = s.next();
                if(str.isEmpty()){
                    continue;
                }
                out.writeUTF(str);

            } while (!"//end".equals(str));

        }  catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Ошибка подключения");
        }


    }
}
