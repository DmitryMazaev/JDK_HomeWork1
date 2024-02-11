import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int WINDOW_POSX = 200;
    private static final int WINDOW_POSY = 200;

    public boolean isChartStart() {
        return isChartStart;
    }

    public boolean isChartStop() {
        return isChartStop;
    }

    /**
     * Переменная, возвращающая true, если чат запущен
     */
    private boolean isChartStart;
    /**
     * Переменная, возвращающая false, если чат остановлен
     */
    private boolean isChartStop;

    JButton startChart;
    JButton stopChart;

    Server server;


    public JTextArea textAreaServer = new JTextArea();

    public ServerWindow(Server server) throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WIDTH, HEIGHT);
        setTitle("Server");
        JPanel panelServer = new JPanel(new GridLayout(1, 2, 10, 10));
        startChart = new JButton("Запустить");
        stopChart = new JButton("Остановить");
        startChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isChartStart = true;
                server.whriteInLogFile("Чат открыт");
                textAreaServer.setText("");
                try {
                    textAreaServer.append(server.readFile());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        stopChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isChartStart = false;
                server.whriteInLogFile("Чат закрыт");
                textAreaServer.setText("");
                try {
                    textAreaServer.append(server.readFile());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        textAreaServer.append(server.readFile());
        add(textAreaServer, BorderLayout.NORTH);
        panelServer.add(startChart);
        panelServer.add(stopChart);
        panelServer.revalidate();
        add(panelServer, BorderLayout.SOUTH);
        setVisible(true);
    }
}
