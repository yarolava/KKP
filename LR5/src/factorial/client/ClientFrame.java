package factorial.client;

import factorial.interfaces.Result;

import java.awt.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.*;




public class ClientFrame extends JFrame {

    public static int PORT = 27015;

	private static final long serialVersionUID = 1L;
	
	private JButton EnterButton;
	private JButton ConnectButton;
    private JLabel Label;
    private JTextArea Text;
    private JTextField TextNumber;
    private JScrollPane jScrollPane1;
    
    private Socket client;
    private ObjectOutputStream ous;
    private ObjectInputStream ois;

    
    public ClientFrame() {
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        Text = new JTextArea();
        Label = new JLabel();
        TextNumber = new JTextField();
        EnterButton = new JButton();
        ConnectButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Text.setColumns(20);
        Text.setRows(5);
        jScrollPane1.setViewportView(Text);
        Text.setEditable(false);
        EnterButton.setEnabled(false);

        Label.setText("Enter number: ");

        EnterButton.setText("Enter");
        EnterButton.addActionListener(evt -> {
            try {
                countResult();
            }catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });
        

        ConnectButton.setText("Connect");
        ConnectButton.addActionListener(evt -> {
            try {
                connect();
            } catch (ConnectException e) {
                JOptionPane.showMessageDialog(ClientFrame.this, "Server is not available", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TextNumber, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EnterButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConnectButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(Label)
                    .addComponent(TextNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnterButton)
                    .addComponent(ConnectButton))
                .addContainerGap())
        );
        pack();
        this.setResizable(true);
    }                       

    private void countResult() throws IOException, ClassNotFoundException{
    	try{
            String classFile = "out/production/LR9/second/userInterface/client/Factorial.class";
            ous.writeObject(classFile);
            FileInputStream fis = new FileInputStream(classFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            ous.writeObject(b);
            int number = Integer.parseInt(TextNumber.getText());
            Factorial factorial = new Factorial(number);
            ous.writeObject(factorial);

            classFile = (String) ois.readObject();
            b = (byte[]) ois.readObject();
            FileOutputStream fos = new FileOutputStream(classFile);
            fos.write(b);

            Result result = (Result) ois.readObject();
            String res = "Result = " + result.getMeasure() + ", time taken = " + result.getTime() + " ns";


            Text.setText(res);


		}catch(SocketException e) {
    	    e.printStackTrace();
    	}
    }                     
    
    private void connect() throws ConnectException {
    	try {
			client = new Socket("localHost",PORT);
			ous = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			EnterButton.setEnabled(true);
			ConnectButton.setEnabled(false);
			JOptionPane.showMessageDialog(ClientFrame.this, "Connection established", "Yes", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
    	    JOptionPane.showMessageDialog(ClientFrame.this, "Server is not available", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }

   
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ClientFrame().setVisible(true));
    }

}
