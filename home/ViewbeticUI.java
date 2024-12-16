package home;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ViewbeticUI{

	private static Home home;
	private static ViewbeticUI ui = null;

	private static JFrame frame;
	private static JLabel firstNameLabel;
	private static JLabel lastNameLabel;
	//private static JLabel heightLabel;
	//private static JLabel weightLabel;
	private static JLabel pregnanciesLabel;
	private static JLabel glucoseLabel;
	private static JLabel bloodPressureLabel;
	private static JLabel skinThicknessLabel;
	private static JLabel insulinLabel;
	private static JLabel dpfLabel;
	private static JLabel ageLabel;
	private static JLabel bmiLabel;

	private static JTextField firstNameField;
	private static JTextField lastNameField;
	private static JTextField heightField;
	private static JTextField weightField;
	private static JTextField pregnanciesField;
	private static JTextField glucoseField;
	private static JTextField bloodPressureField;
	private static JTextField skinThicknessField;
	private static JTextField insulinField;
	private static JTextField dpfField;
	private static JTextField ageField;
	private static JTextField bmiField;

	private static JScrollPane scrollPane;

	private static JButton predictionButton;
	private static JButton feedbackButton;

	private static JTextArea outputArea;

	private static String firstNameText;
	private static String lastNameText;
	private static String heightText;
	private static String weightText;
	private static String pregnanciesText;
	private static String glucoseText;
	private static String bloodPressureText;
	private static String skinThicknessText;
	private static String insulinText;
	private static String dpfText;
	private static String ageText;
	private static String bmiText;
	
	private static Image image; 
	private static String pathToImageFile = "src/home/files/image.png";

	private ViewbeticUI(Home home) {
		ViewbeticUI.home = home;
	
		frame = new JFrame("Viewbetic System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(500, 500);

		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);

		firstNameLabel = new JLabel("First Name: ");
		firstNameField = new JTextField(25);

		lastNameLabel = new JLabel("Last Name: ");
		lastNameField = new JTextField(25);

		ageLabel = new JLabel("Age: ");
		ageField = new JTextField(5);

		pregnanciesLabel = new JLabel("Number of Pregnancies: ");
		pregnanciesField = new JTextField(5);

		glucoseLabel = new JLabel("Glucose (mg/dL): ");
		glucoseField = new JTextField(5);

		bloodPressureLabel = new JLabel("Blood Pressure (mmHg): ");
		bloodPressureField = new JTextField(5);

		skinThicknessLabel = new JLabel("Skin Thickness (mm): ");
		skinThicknessField = new JTextField(5);

		insulinLabel = new JLabel("Insulin (µU/mL): ");
		insulinField = new JTextField(5);

		bmiLabel = new JLabel("BMI (kg/m²): ");
		bmiField = new JTextField(5);

		dpfLabel = new JLabel("DPF: ");
		dpfField = new JTextField(5);

		
		//heightLabel = new JLabel("Height (cm): "); heightField = new JTextField(5);
		 
		//weightLabel = new JLabel("Weight (kg): "); weightField = new JTextField(5);
		

		predictionButton = new JButton("Prediction");
		feedbackButton = new JButton("Feedback");

		outputArea = new JTextArea(12, 20);
		outputArea.setEditable(false);
		scrollPane = new JScrollPane(outputArea);

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		frame.add(firstNameLabel, c);
		c.gridx = 1;
		frame.add(firstNameField, c);

		c.gridx = 0;
		c.gridy = 1;
		frame.add(lastNameLabel, c);
		c.gridx = 1;
		frame.add(lastNameField, c);

		c.gridx = 0;
		c.gridy = 2;
		frame.add(ageLabel, c);
		c.gridx = 1;
		frame.add(ageField, c);

		c.gridx = 0;
		c.gridy = 3;
		frame.add(pregnanciesLabel, c);
		c.gridx = 1;
		frame.add(pregnanciesField, c);

		c.gridx = 0;
		c.gridy = 4;
		frame.add(glucoseLabel, c);
		c.gridx = 1;
		frame.add(glucoseField, c);

		c.gridx = 0;
		c.gridy = 5;
		frame.add(bloodPressureLabel, c);
		c.gridx = 1;
		frame.add(bloodPressureField, c);

		c.gridx = 0;
		c.gridy = 6;
		frame.add(skinThicknessLabel, c);
		c.gridx = 1;
		frame.add(skinThicknessField, c);

		c.gridx = 0;
		c.gridy = 7;
		frame.add(insulinLabel, c);
		c.gridx = 1;
		frame.add(insulinField, c);

		c.gridx = 0;
		c.gridy = 8;
		frame.add(bmiLabel, c);
		c.gridx = 1;
		frame.add(bmiField, c);

		c.gridx = 0;
		c.gridy = 9;
		frame.add(dpfLabel, c);
		c.gridx = 1;
		frame.add(dpfField, c);

		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		frame.add(predictionButton, c);

		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		frame.add(feedbackButton, c);

		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		frame.getContentPane().add(scrollPane, c);
		
		try {
			image = ImageIO.read(new File(pathToImageFile));
		} catch (IOException e) {
			System.out.println("Cannot get image: "  + e.getMessage());
		}
		
		frame.setIconImage(image);
		
		frame.pack();
		frame.setVisible(true);
		
		predictionButton.addActionListener(new ActionListener() {
		  @Override 
		  public void actionPerformed(ActionEvent e) {
			  outputArea.append("Does the patient have diabetes?" + "\n");
			  home.createPredictionForPatient(getFirstNameText(), 
											  getLastNameText(),
											  getPregnanciesText(), 
											  getGlucoseText(), 
											  getBloodPressureText(),
											  getSkinThicknessText(), 
											  getInsulinText(), 
											  getBMIText(), 
											  getDPFText(),
											  getAgeText(), 
											  "0");
			  clearFields();
		  }
		});
		
		feedbackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewbeticUI.home.createFeedbackForPatient();
			}
		});			
	}

	public static ViewbeticUI createUI(Home home) {
		if (ui == null) {
			ui = new ViewbeticUI(home);
		}
		try {
			addImageIcon(pathToImageFile);
		} catch(Exception e) {
			System.out.println("Cannot add image: " + e);
		}
		
		return ui;
	}
	
	

	private static void addImageIcon(String pathToImageFile) throws IOException {
		
		image = ImageIO.read(new File(pathToImageFile));
	}

	public String getFirstNameText() {
		return firstNameField.getText();
	}

	public void setFirstNameText() {
		firstNameField.setText(firstNameText);
	}

	public String getLastNameText() {
		return lastNameField.getText();
	}

	public void setLastNameText() {
		lastNameField.setText(lastNameText);
	}

	public String getHeightText() {
		return heightField.getText();
	}

	public void setHeightText() {
		heightField.setText(heightText);
	}

	public String getWeightText() {
		return weightField.getText();
	}

	public void setWeightText() {
		weightField.setText(weightText);
	}

	public String getPregnanciesText() {
		return pregnanciesField.getText();
	}

	public void setPregnanciesText() {
		pregnanciesField.setText(pregnanciesText);
	}

	public String getGlucoseText() {
		return glucoseField.getText();
	}

	public void setGlucoseText() {
		glucoseField.setText(glucoseText);
	}

	public String getBloodPressureText() {
		return bloodPressureField.getText();
	}

	public void setBloodPressureText() {
		bloodPressureField.setText(bloodPressureText);
	}

	public String getSkinThicknessText() {
		return skinThicknessField.getText();
	}

	public void setSkinThicknessText() {
		skinThicknessField.setText(skinThicknessText);
	}

	public String getInsulinText() {
		return insulinField.getText();
	}

	public void setInsulinText() {
		insulinField.setText(insulinText);
	}

	public String getDPFText() {
		return dpfField.getText();
	}

	public void setDPFText() {
		dpfField.setText(dpfText);
	}

	public String getAgeText() {
		return ageField.getText();
	}

	public void setAgeText() {
		ageField.setText(ageText);
	}

	public String getBMIText() {
		return bmiField.getText();
	}

	public void setBMIText() {
		bmiField.setText(bmiText);
	}

	public void setMessage(String message) {
		outputArea.append(message + "\n");
	}
	
	public void setPredictionMessage(String message) {
		outputArea.append(message + "\n");
	}
	
	public void setFeedbackMessage(String message) {
		outputArea.append(message+ "\n");
	}
	
	public void clearFields() {
		firstNameField.setText("");
		lastNameField.setText("");
		//heightField.setText("");
		//weightField.setText("");
		pregnanciesField.setText("");
		glucoseField.setText("");
		bloodPressureField.setText("");
		skinThicknessField.setText("");
		insulinField.setText("");
		dpfField.setText("");
		ageField.setText("");
		bmiField.setText("");
	}
}