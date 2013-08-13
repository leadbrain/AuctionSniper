package com.leadbrain.ex;

public class AuctionSniper implements AuctionEventListener {
	private SniperListener sniperListener;
	private Auction auction;

	public AuctionSniper(Auction auction, SniperListener listener) {
		this.sniperListener = listener;
		this.auction = auction;
	}

	public void auctionClosed() {
		sniperListener.sniperLost();
	}

	@Override
	public void currentPrice(int price, int increment, PriceSource priceSource) {
		switch (priceSource) {
		case FromSniper:
			sniperListener.sniperWinning();
			break;
		case FromOtherBidder:
			auction.bid(price + increment);
			sniperListener.sniperBidding();
			break;
		}

	}
}
