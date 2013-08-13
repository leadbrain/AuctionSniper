package com.leadbrain.ex;

import java.util.EventListener;

public interface AuctionEventListener extends EventListener {
	enum PriceSource {
		FromSniper, FromOtherBidder;
	};

	abstract void auctionClosed();

	abstract void currentPrice(int price, int increment, PriceSource priceSource);
}
