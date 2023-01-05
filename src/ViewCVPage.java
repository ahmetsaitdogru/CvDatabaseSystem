import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ViewCVPage extends JFrame implements ActionListener {

    JLabel nameLabel;
    JLabel skillLabel;
    JLabel experienceLabel;
    JLabel educationLabel;
    JLabel addressLabel;
    JButton backButton;
    JButton saveButton;
    CV cv;

    public ViewCVPage(CV cv) {
        super("CV Database - View CV");

        this.cv = cv;

        setLayout(new FlowLayout());

        nameLabel = new JLabel("Name: " + cv.getName());
        skillLabel = new JLabel("Skill: " + cv.getSkill());
        experienceLabel = new JLabel("Experience: " + cv.getExperience());
        educationLabel = new JLabel("Education: " + cv.getEducation());
        addressLabel = new JLabel("Address: " + cv.getAddress());
        backButton = new JButton("Back");
        saveButton = new JButton("Save As");

        backButton.addActionListener(this);
        saveButton.addActionListener(this);

        add(nameLabel);
        add(skillLabel);
        add(experienceLabel);
        add(educationLabel);
        add(addressLabel);
        add(backButton);
        add(saveButton);

        setSize(300,250);
                setVisible(true);
    }  public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            SearchPage search = new SearchPage();
            search.setVisible(true);
            setVisible(false);
        } else if (e.getSource() == saveButton) {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write("Name: " + cv.getName() + "\n");
                    writer.write("Skill: " + cv.getSkill() + "\n");
                    writer.write("Experience: " + cv.getExperience() + "\n");
                    writer.write("Education: " + cv.getEducation() + "\n");
                    writer.write("Address: " + cv.getAddress() + "\n");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

