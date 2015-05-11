package projectPlanner.view.personalInfo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import projectPlanner.view.utilities.NameObject;

@SuppressWarnings("serial")
public class PersonalInfoPane extends JPanel {
	
	private NameObject namePane;
	private JTextArea aboutTxtArea;
	
	
	public PersonalInfoPane(String name) {
		this.namePane = new NameObject("Om " + name.split("\\s+")[0]);
		this.aboutTxtArea = new JTextArea(setAbout(), 10, 10);
		aboutTxtArea.setLineWrap(true);
		aboutTxtArea.setWrapStyleWord(true);
		aboutTxtArea.setEditable(false);
		aboutTxtArea.setBackground(new Color(238,238,238));
		JScrollPane aboutTxtScrollPane = new JScrollPane(aboutTxtArea);
		aboutTxtScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		aboutTxtScrollPane.setPreferredSize(new Dimension(600, 300));
		

		this.aboutTxtArea.setFont(new Font("Arial Black", Font.BOLD, 12));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(namePane);
		this.add(aboutTxtScrollPane);		
	}

	private String setAbout() {
		return "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia,"
				+ "there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, "
				+ " a large language ocean. A small river named Duden flows by their place and supplies it with "
				+ "the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences "
				+ "fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an "
				+ "almost unorthographic life One day however a small line of blind text by the name of "
				+ "Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, "
				+ "because there were thousands of bad Commas, wild Question Marks and devious Semikoli, "
				+ "but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the "
				+ "belt and made herself on the way. When she reached the first hills of the Italic Mountains, "
				+ "she had a last view back on the skyline of her hometown Bookmarksgrove, "
				+ "the headline of Alphabet Village and the subline of her own road, the Line Lane. "
				+ "Pityful a rethoric question ran over her cheek, then ";
	}
}
