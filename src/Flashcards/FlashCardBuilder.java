import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FlashCardBuilder {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<FlashCard> cardList;
    private JFrame frame;

    public static void main(String[] args) {
        FlashCardBuilder builder = new FlashCardBuilder();
        builder.buildGui();
    }

    public void buildGui(){
        frame = new JFrame("Flashcards");
        JPanel panel = new JPanel();
        question = new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);

        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);

        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("Next Card");

        cardList = new ArrayList<FlashCard>();
        JLabel qLabel = new JLabel("Question:");
        JLabel aLabel = new JLabel("Answer:");

        panel.add(qLabel);
        panel.add(qScroller);
        panel.add(aLabel);
        panel.add(aScroller);
        panel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());

        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500,600);
        frame.setVisible(true);
    }

    public class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {

            FlashCard card = new FlashCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }

    public class SaveMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            FlashCard card = new FlashCard(question.getText(), answer.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser();                fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }
    public class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            cardList.clear();
            clearCard();
        }
    }
        private void clearCard() {
            question.setText("");
            answer.setText("");
            question.requestFocus();
        }

    private void saveFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(FlashCard card:cardList) {
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        } catch(IOException ex) {
            System.out.println("couldn’t write the cardList out");
            ex.printStackTrace();
        }
    }
}
