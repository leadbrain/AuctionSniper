package com.leadbrain.ex;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import com.leadbrain.ex.AuctionEventListener.PriceSource;

public class AuctionMessageTranslator implements MessageListener {
	private AuctionEventListener listener;
	private final String sniperId;

	public AuctionMessageTranslator(String sniperId,
			AuctionEventListener listener) {
		this.listener = listener;
		this.sniperId = sniperId;
	}

	public void processMessage(Chat chat, Message message) {
		AuctionEvent event = AuctionEvent.from(message.getBody());
		String eventType = event.type();
		if ("CLOSE".equals(eventType)) {
			listener.auctionClosed();
		} else if ("PRICE".equals(eventType)) {
			listener.currentPrice(event.currentPrice(), event.increment(),
					event.isFrom(sniperId));
		}
	}

	private HashMap<String, String> unpackEventFrom(Message message) {
		HashMap<String, String> event = new HashMap<String, String>();
		for (String element : message.getBody().split(";")) {
			String[] pair = element.split(":");
			event.put(pair[0].trim(), pair[1].trim());
		}
		return event;
	}

	private static class AuctionEvent {
		private final Map<String, String> fields = new HashMap<String, String>();

		public String type() {
			return get("Event");
		}

		public PriceSource isFrom(String sniperId) {
			return sniperId.equals(bidder()) ? PriceSource.FromSniper
					: PriceSource.FromOtherBidder;
		}

		private Object bidder() {
			return get("Bidder");
		}

		private String get(String fieldName) {
			return fields.get(fieldName);
		}

		public int currentPrice() {
			return getInt("CurrentPrice");
		}

		private int getInt(String fieldName) {
			return Integer.parseInt(get(fieldName));
		}

		public int increment() {
			return getInt("Increment");
		}

		public static AuctionEvent from(String messageBody) {
			AuctionEvent event = new AuctionEvent();
			for (String field : fieldsIn(messageBody)) {
				event.addField(field);
			}
			return event;
		}

		static String[] fieldsIn(String messageBody) {
			return messageBody.split(";");
		}

		private void addField(String field) {
			String[] pair = field.split(":");
			fields.put(pair[0].trim(), pair[1].trim());
		}
	}
}
