package com.leadbrain.ex;

public interface AuctionEventListener {
	abstract void auctionClosed();

	abstract void currentPrice(int price, int increment);
}
