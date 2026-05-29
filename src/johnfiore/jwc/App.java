package johnfiore.jwc;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class App extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTextField countMinInp;
	private static JTextField countMaxInp;
	private static int countMax;
	private static int countMin;
	
	private static JTextArea textArea;
	private static JCheckBox academicToggle;
	private static boolean academicMode = false;
	private static JPanel academicSettings;
	private static JLabel wordCountTxt;
	private static JLabel charCountTxt;
	private static JLabel sentCountTxt;

	/**
	 * Create the panel.
	 */
	public App()
	{
		setPreferredSize(new Dimension(510, 320));
		setLayout(null);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Calibri", Font.BOLD, 13));
		textArea.setLineWrap(true);
		textArea.setBounds(10, 15, 295, 273);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(textArea);
		
		JLabel creatorTxt = new JLabel("Created by John Fiore");
		creatorTxt.setHorizontalAlignment(SwingConstants.RIGHT);
		creatorTxt.setBounds(311, 297, 189, 14);
		add(creatorTxt);
		
		academicToggle = new JCheckBox("Academic Mode");
		academicToggle.setBounds(311, 15, 189, 23);
		academicToggle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt)
			{
				if (evt.getStateChange() == ItemEvent.SELECTED)
				{
					academicMode = true;
				}
				else
				{
					academicMode = false;
				}
				checkAcademicMode();
			}
		});
		add(academicToggle);
		
		academicSettings = new JPanel();
		academicSettings.setBorder(new TitledBorder(null, "Academic Mode Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		academicSettings.setBounds(314, 42, 186, 135);
		add(academicSettings);
		academicSettings.setLayout(null);
		
		countMaxInp = new JTextField();
		countMaxInp.setText("100");
		countMaxInp.setHorizontalAlignment(SwingConstants.CENTER);
		countMaxInp.setBounds(10, 77, 166, 20);
		academicSettings.add(countMaxInp);
		countMaxInp.setColumns(10);
		
		JLabel countMaxTxt = new JLabel("Word Count Maximum");
		countMaxTxt.setHorizontalAlignment(SwingConstants.CENTER);
		countMaxTxt.setBounds(10, 61, 166, 14);
		academicSettings.add(countMaxTxt);
		
		countMinInp = new JTextField();
		countMinInp.setHorizontalAlignment(SwingConstants.CENTER);
		countMinInp.setText("50");
		countMinInp.setBounds(10, 35, 166, 20);
		academicSettings.add(countMinInp);
		countMinInp.setColumns(10);
		
		JLabel countMinTxt = new JLabel("Word Count Minimum");
		countMinTxt.setHorizontalAlignment(SwingConstants.CENTER);
		countMinTxt.setBounds(10, 19, 166, 14);
		academicSettings.add(countMinTxt);
		
		JButton applyBtn = new JButton("Apply Changes");
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				countMin = Integer.parseInt(countMinInp.getText());
				countMax = Integer.parseInt(countMaxInp.getText());
				System.out.println(String.format("VALUES CHANGED SUCCESSFULLY.\nMin. Count: %d\nMax. Count: %d", countMin, countMax));
			}
		});
		applyBtn.setBounds(10, 101, 166, 23);
		academicSettings.add(applyBtn);
		
		JPanel resultsSection = new JPanel();
		resultsSection.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		resultsSection.setBounds(315, 177, 185, 85);
		add(resultsSection);
		resultsSection.setLayout(null);
		
		wordCountTxt = new JLabel("Word Count: 0");
		wordCountTxt.setBounds(10, 22, 165, 14);
		resultsSection.add(wordCountTxt);
		
		charCountTxt = new JLabel("Character Count: 0");
		charCountTxt.setBounds(10, 40, 165, 14);
		resultsSection.add(charCountTxt);
		
		sentCountTxt = new JLabel("Sentence Count: 0");
		sentCountTxt.setBounds(10, 59, 165, 14);
		resultsSection.add(sentCountTxt);
		
		JLabel versionTxt = new JLabel("v1.0 - Last Updated: 05/29/2026");
		versionTxt.setBounds(10, 297, 189, 14);
		add(versionTxt);
		
		JButton countBtn = new JButton("Count Words");
		countBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String paragraph = textArea.getText();
				int wordCount = paragraph.split("\\s").length;
				int charCount = paragraph.length();
				int sentCount = paragraph.split("[.!?]\\s*").length;
				
				if (paragraph.length() < 1)
				{
					JOptionPane.showMessageDialog(null, "You must have at least 1 character.");
				}
				
				if (academicMode)
				{
					if (wordCount < countMin)
					{
						JOptionPane.showMessageDialog(null, "Your paragraph is below the minimum requirement.");
					}
					else if (wordCount > countMax)
					{
						JOptionPane.showMessageDialog(null, "Your paragraph is above the maximum requirement.");
					}
				}
				
				wordCountTxt.setText("Word Count: " + wordCount);
				charCountTxt.setText("Character Count: " + charCount);
				sentCountTxt.setText("Sentence Count: " + sentCount);
			}
		});
		countBtn.setBounds(315, 262, 185, 23);
		add(countBtn);
		
		checkAcademicMode();
	}
	
	static void checkAcademicMode()
	{
		for (Component comp : academicSettings.getComponents())
		{
			comp.setEnabled(academicMode);
		}
	}
}
