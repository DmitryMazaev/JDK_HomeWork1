import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 500;

    JButton btnSendMessage;
    JTextField fieldMessage;

    private JTextArea textArea = new JTextArea();

    Server server;
    ServerWindow serverWindow;


    public ClientWindow(Client client, ServerWindow serverWindow, Server server) throws IOException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WIDTH, HEIGHT);
        setTitle(client.getLoginClient());
        JPanel panelMessage = new JPanel(new GridLayout(1, 2, 10, 10));
        fieldMessage = new JTextField();
        btnSendMessage = new JButton("Отправить");

        /**
         * Действие при вводе текста
         */
        fieldMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isChartStart())
                {
                    client.setMessageClient(fieldMessage.getText());
                    if (fieldMessage.getText().length() > 0) {
                        serverWindow.textAreaServer.setText("");
                        server.whriteInLogFile(client.getLoginClient() + ": " +client.getMessageClient());
                        try {
                            serverWindow.textAreaServer.append(server.readFile());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        fieldMessage.setText("");
                        textArea.setText("");
                        try {
                            textArea.append(server.readFile());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        repaint();
                    }

                }
            }
        });

        /**
         * Действие при нажатии кнопки "Отправить"
         */
        btnSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isChartStart())
                {
                    client.setMessageClient(fieldMessage.getText());
                    if (fieldMessage.getText().length() > 0) {
                        serverWindow.textAreaServer.setText("");
                        server.whriteInLogFile(client.getLoginClient() + ": " +client.getMessageClient());
                        try {
                            serverWindow.textAreaServer.append(server.readFile());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        fieldMessage.setText("");
                        textArea.setText("");
                        try {
                            textArea.append(server.readFile());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        repaint();
                    }

                }
            }
        });

        textArea.append(client.readFile());
        panelMessage.add(fieldMessage);
        panelMessage.add(btnSendMessage);
        panelMessage.revalidate();
        add(textArea, BorderLayout.NORTH);
        add(panelMessage, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     *
     * @param client
     * Метод вывода текста чата в textArea.
     */
    public void printChart(Client client) throws IOException {

        textArea.append(client.readFile());

        serverWindow.textAreaServer.append(server.readFile());
    }

}
