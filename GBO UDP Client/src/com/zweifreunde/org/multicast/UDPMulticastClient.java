package com.zweifreunde.org.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.SwingUtilities;

import com.zweifreunde.org.client.controller.ISendMessageListener;
import com.zweifreunde.org.client.debug.IDebug;
import com.zweifreunde.org.client.localization.ILocalization;
import com.zweifreunde.org.client.model.ClientModel;

public class UDPMulticastClient implements ISendMessageListener {

    final private MulticastSocket socket;
	final private InetAddress addr;
    final private int port;
    final private ClientModel model;
    private final IDebug debugger;
    private final ILocalization localization;

    public UDPMulticastClient(ClientModel model, int port, ILocalization localization, IDebug debugger) throws IOException {
		this.socket = new MulticastSocket(port);
		this.addr = InetAddress.getByName("238.254.254.254");
		this.port = port;
		this.socket.joinGroup(this.addr);
		this.model = model;
		this.model.addSendMessageListener(this);
        this.debugger = debugger;
        this.localization = localization;
	}



	@Override
	public void sendMessage(String msg) {
		DatagramPacket packet = new DatagramPacket(msg.getBytes(),
				msg.getBytes().length, this.addr, this.port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startListening() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					byte[] b = new byte[2048];
					DatagramPacket read = new DatagramPacket(b, b.length);
					try {
						socket.receive(read);
					} catch (IOException e) {
						UDPMulticastClient.this.debugger.error(UDPMulticastClient.this.localization.getString("multicast_client_receive_fail"));
					}
					String msg = new String(read.getData());
					// model.newMessage(msg);
					SwingUtilities.invokeLater(new MsgRunnable(model, msg));
				}

			}
		});
		thread.start();

	}

	class MsgRunnable implements Runnable {
		private String msg;
		private ClientModel model;

		public MsgRunnable(ClientModel model, String msg) {
			this.model = model;
			this.msg = msg;
		}

		@Override
		public void run() {
			this.model.newMessage(this.msg.trim());

		}

	}
}
