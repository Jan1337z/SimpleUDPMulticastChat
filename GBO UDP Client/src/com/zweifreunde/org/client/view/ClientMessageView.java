package com.zweifreunde.org.client.view;

import java.awt.Insets;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.zweifreunde.org.client.controller.INewMessagesListener;

public class ClientMessageView extends JScrollPane implements
        INewMessagesListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337L;
    private HTMLEditorKit kit;
	private HTMLDocument doc;

	public ClientMessageView() {
        JEditorPane textArea = new JEditorPane("text/html", null);
		this.kit = new HTMLEditorKit();
		this.doc = new HTMLDocument();
		textArea.setEditorKit(kit);
		textArea.setDocument(doc);
		textArea.setEditable(false);
		textArea.setMargin(new Insets(10, 10, 10, 10));

		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.setViewportView(textArea);
	}

	public void addMessage(String msg) {
		try {
			this.kit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void newMessage(String msg) {

		this.addMessage(String.format("%s %s", this.getHTMLTime(), msg));

	}

	public String getTime() {
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		return formatTime.format(now);
	}

	public String getHTMLTime() {
		return String.format("<a style='color: #999999'>&lt;%s&gt;</a>", this.getTime() );
	}
}
