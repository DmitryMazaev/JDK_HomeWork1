import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Server extends JFrame
{
    Client client;
    ArrayList<Client> clientList = new ArrayList<>();

    public void addClient(Client client)
    {
        clientList.add(client);
    }

    public void whriteInLogFile(String string)
    {

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            writer.write(string +"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public String readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("log.txt"));
        String line;
        String chart = "";
        while ((line = br.readLine()) != null) {
            //System.out.println(line);
            chart = chart + line + "\n";
            //return line;
        }
        return chart;
    }
}
