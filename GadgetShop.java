import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//main application window
public class GadgetShop extends JFrame implements ActionListener {

//aray list
private ArrayList<Gadget> gadgets = new ArrayList<>();
//text input
private JTextField modelField, priceField, weightField, sizeField, creditField, memoryField, phoneNumberField,

durationField, downloadSizeField, displayNumberField;

private JTextArea displayArea;

//contructor for gadget shop
public GadgetShop() {

setTitle("Gadget Shop");

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

setLayout(new BorderLayout(10, 10));

JPanel inputPanel = new JPanel(new GridLayout(3, 5, 2, 4));

inputPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

inputPanel.add(new JLabel("Model:"));

modelField = new JTextField();

inputPanel.add(modelField);

inputPanel.add(new JLabel("Price (£):"));

priceField = new JTextField();

inputPanel.add(priceField);

inputPanel.add(new JLabel("Weight (g):"));

weightField = new JTextField();

inputPanel.add(weightField);

inputPanel.add(new JLabel("Size:"));

sizeField = new JTextField();

inputPanel.add(sizeField);

inputPanel.add(new JLabel("Initial Credit (Mobile):"));

creditField = new JTextField();

inputPanel.add(creditField);

inputPanel.add(new JLabel("Initial Memory (MP3):"));

memoryField = new JTextField();

inputPanel.add(memoryField);

inputPanel.add(new JLabel("Phone Number:"));

phoneNumberField = new JTextField();

inputPanel.add(phoneNumberField);

inputPanel.add(new JLabel("Duration (minutes):"));

durationField = new JTextField();

inputPanel.add(durationField);

inputPanel.add(new JLabel("Download Size (MB):"));

downloadSizeField = new JTextField();

inputPanel.add(downloadSizeField);

inputPanel.add(new JLabel("Display Number:"));

displayNumberField = new JTextField();

inputPanel.add(displayNumberField);

JPanel buttonPanel = new JPanel(new GridLayout(5, 6, 8, 15));

buttonPanel.setBorder(BorderFactory.createEmptyBorder(8, 4, 3, 2));

JButton addMobileButton = new JButton("Add Mobile");

addMobileButton.addActionListener(this);

buttonPanel.add(addMobileButton);

JButton addMP3Button = new JButton("Add MP3");

addMP3Button.addActionListener(this);

buttonPanel.add(addMP3Button);

JButton makeCallButton = new JButton("Make A Call");

makeCallButton.addActionListener(this);

buttonPanel.add(makeCallButton);

JButton downloadMusicButton = new JButton("Download Music");

downloadMusicButton.addActionListener(this);

buttonPanel.add(downloadMusicButton);

JButton displayAllButton = new JButton("Display All");

displayAllButton.addActionListener(this);

buttonPanel.add(displayAllButton);

JButton clearButton = new JButton("Clear");

clearButton.addActionListener(this);

buttonPanel.add(clearButton);
//scroll pane for display
JScrollPane scrollPane = new JScrollPane();

displayArea = new JTextArea(10, 30);

scrollPane.setViewportView(displayArea);

add(inputPanel, BorderLayout.WEST);

add(buttonPanel, BorderLayout.CENTER);

add(scrollPane, BorderLayout.EAST);

pack();

setLocationRelativeTo(null); // Center the window

setVisible(true);

}

@Override

public void actionPerformed(ActionEvent e) {

switch (e.getActionCommand()) {

case "Add Mobile":

addMobile();

break;

case "Add MP3":

addMP3();

break;

case "Make A Call":

makeCall();

break;

case "Download Music":

downloadMusic();

break;

case "Display All":

displayAll();

break;

case "Clear":

clearFields();

break;

}

}
//add mobile method
private void addMobile() {

try {

String model = modelField.getText();

double price = Double.parseDouble(priceField.getText());

int weight = Integer.parseInt(weightField.getText());

String size = sizeField.getText();

int credit = Integer.parseInt(creditField.getText());

gadgets.add(new Mobile(model, price, weight, size, credit));

displayArea.append("Mobile added: " + model + "\n");

} catch (NumberFormatException ex) {

JOptionPane.showMessageDialog(this, "Wrong input entered.");

}

}
// add mp3 gadget
private void addMP3() {

try {

String model = modelField.getText();

double price = Double.parseDouble(priceField.getText());

int weight = Integer.parseInt(weightField.getText());

String size = sizeField.getText();

int memory = Integer.parseInt(memoryField.getText());

gadgets.add(new MP3(model, price, weight, size, memory));

displayArea.append("MP3 added: " + model + "\n");

} catch (NumberFormatException ex) {

JOptionPane.showMessageDialog(this, "Wrong input entered.");

}

}
//make a call
private void makeCall() {

int displayNumber = getDisplayNumber();

if (displayNumber != -1) {

String phoneNumber = phoneNumberField.getText();

String durationStr = durationField.getText();

if (!phoneNumber.isEmpty() && !durationStr.isEmpty()) {

try {

int duration = Integer.parseInt(durationStr);

Gadget selectedGadget = gadgets.get(displayNumber);

if (selectedGadget instanceof Mobile) {

Mobile mobile = (Mobile) selectedGadget;

if (mobile.getCallingCredit() >= duration) {

mobile.makeCall(phoneNumber, duration);

displayArea.append("Call made.\n");

} else {

JOptionPane.showMessageDialog(this, "Not enough credit.");

}

} else {

JOptionPane.showMessageDialog(this, "This is not a mobile phone gadget.");

}

} catch (NumberFormatException ex) {

JOptionPane.showMessageDialog(this, "Must be entered into a integar.");

}

} else {

JOptionPane.showMessageDialog(this, "Enter the phone number and duration");

}

}

}
//download music method
private void downloadMusic() {

int displayNumber = getDisplayNumber();

if (displayNumber != -1) {

String downloadSizeStr = downloadSizeField.getText();

if (!downloadSizeStr.isEmpty()) {

try {

int downloadSize = Integer.parseInt(downloadSizeStr);

Gadget selectedGadget = gadgets.get(displayNumber);

if (selectedGadget instanceof MP3) {

((MP3) selectedGadget).downloadMusic(downloadSize);

displayArea.append("Music downloaded.\n");

} else {

JOptionPane.showMessageDialog(this, "This gadget is not a mp3 player");

}

} catch (NumberFormatException ex) {

JOptionPane.showMessageDialog(this, "Must be entered in integar");

}

} else {

JOptionPane.showMessageDialog(this, "Enter a download size.");

}

}

}
//display gadgets
private void displayAll() {

displayArea.setText("");

for (Gadget gadget : gadgets) {

displayArea.append(gadget.toString() + "\n");

}

}
// clear all input fields
private void clearFields() {

modelField.setText("");

priceField.setText("");

weightField.setText("");

sizeField.setText("");

creditField.setText("");

memoryField.setText("");

phoneNumberField.setText("");

durationField.setText("");

downloadSizeField.setText("");

displayNumberField.setText("");

displayArea.setText("");

}
// display number
private int getDisplayNumber() {

int displayNumber;

try {

displayNumber = Integer.parseInt(displayNumberField.getText());

if (displayNumber < 0 || displayNumber >= gadgets.size()) {

JOptionPane.showMessageDialog(this, "Invalid display number. Please enter a valid input.");

return -1;

}

} catch (NumberFormatException ex) {

JOptionPane.showMessageDialog(this, "Display number must be a non negative input.");

return -1;

}

return displayNumber;

}
// main method which starts the application
public static void main(String[] args) {

SwingUtilities.invokeLater(() -> new GadgetShop());

}

}