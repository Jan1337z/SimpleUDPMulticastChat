package com.zweifreunde.org.client.model;

import java.util.ArrayList;

import com.zweifreunde.org.client.controller.INewMessagesListener;
import com.zweifreunde.org.client.controller.ISendMessageListener;

public class ClientModel {
	private final ArrayList<INewMessagesListener> newMsgListeners;
	private final ArrayList<ISendMessageListener> sendMsgListener;

	private String ID;

	public ClientModel() {
		this.newMsgListeners = new ArrayList<INewMessagesListener>();
		this.sendMsgListener = new ArrayList<ISendMessageListener>();

		this.ID = this.toString().substring(this.toString().indexOf("@") + 1);

	}

	public void addNewMessageListener(INewMessagesListener listener) {
		synchronized (this.newMsgListeners) {
			this.newMsgListeners.add(listener);
		}
	}

	private void notifyListeners(String msg) {
		synchronized (this.newMsgListeners) {
			for (INewMessagesListener l : this.newMsgListeners) {
				l.newMessage(msg);
			}
		}
	}

	public void sendMessage(String newMsg) {
		if (!"".equals(newMsg)) {
			synchronized (this.sendMsgListener) {
				for (ISendMessageListener l : this.sendMsgListener) {
					l.sendMessage(this.getName() + ": " + newMsg);
				}
			}
		}
	}

	public void newMessage(String msg) {
		notifyListeners(msg);
	}

	public void addSendMessageListener(ISendMessageListener listener) {
		synchronized (this.sendMsgListener) {
			this.sendMsgListener.add(listener);
		}
	}

	public void removeSendMessageListener(ISendMessageListener listener) {
		synchronized (this.sendMsgListener) {
			this.sendMsgListener.remove(listener);
		}
	}

	public void setName(String name) {
		this.ID = name;
	}

	public String getName() {
		return this.ID;
	}
}
