package com.zweifreunde.org.client.view;

import com.zweifreunde.org.client.localization.ILocalization;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ClientNameDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337L;;
	private final String question;
	private final String title;
	private ClientWindow window;

	public ClientNameDialog(ClientWindow window, ILocalization localization) {
		this.window = window;
        this.title = localization.getString("modal_login_dialog_title");
        this.question = localization.getString("modal_login_dialog_question");
	}

	public String askForName() {
		String name = null;
		while (name == null) {
			name = JOptionPane.showInputDialog(this.window, question, title,
					JOptionPane.PLAIN_MESSAGE);
		}
		return name;
	}
}
