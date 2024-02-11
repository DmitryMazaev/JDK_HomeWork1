import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Client extends JFrame {

    private String loginClient;
    private String parolClient;
    private String messageClient;
    private int chartLength;

    public Client(String loginClient) {
        this.loginClient = loginClient;
    }

    public String getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(String loginClient) {
        this.loginClient = loginClient;
    }

    public String getParolClient() {
        return parolClient;
    }

    public void setParolClient(String parolClient) {
        this.parolClient = parolClient;
    }

    public String getMessageClient() {
        return messageClient;
    }

    public void setMessageClient(String messageClient) {
        this.messageClient = messageClient;
    }

    /**
     *
     * @param login - логин пользователя
     * @param message - сообщение
     *                Метод записи сообщений пользователей в файл
     */
    public void whriteInLogFile(String login, String message)
    {
        try {
            FileWriter writer = new FileWriter("log.txt", true);
            writer.write(loginClient + ": " + messageClient +"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    /**
     *
     * @return Возвращает весь текст чата
     * Метод чтения из файла, применяется для последующей записи чата в textArea
     */
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
