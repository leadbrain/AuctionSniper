package auctionsniper.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import com.leadbrain.ex.Main;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 2965004132202061780L;
	public static final String STATUS_LOST = "Lost";
	public static final String STATUS_JOINING = "Joining";
	public static final String STATUS_BIDDING = "Bidding";
	public static final String STATUS_WINNING = "Winning";
	public static final String STATUS_WON = "Win";
	private static final String SNIPERS_TABLE_NAME = "table";
	private final JLabel sniperStatus = createLabel(STATUS_JOINING);
	private final SnipersTableModel snipers = new SnipersTableModel();

	public MainWindow() {
		super("Auction Sniper");
		setName(Main.MAIN_WINDOW_NAME);
		fillContentPane(makeSnipersTable());
		add(sniperStatus);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void fillContentPane(JTable snipersTable) {
		final Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(new JScrollPane(snipersTable), BorderLayout.CENTER);
	}

	private JTable makeSnipersTable() {
		final JTable snipersTable = new JTable(snipers);
		snipersTable.setName(SNIPERS_TABLE_NAME);
		return snipersTable;
	}

	public void showStatus(String status) {
		snipers.setStatusText(status);
	}

	private static JLabel createLabel(String initialText) {
		JLabel result = new JLabel(initialText);
		result.setName(Main.SNIPER_STATUS_NAME);
		result.setBorder(new LineBorder(Color.BLACK));
		return result;
	}

	public class SnipersTableModel extends AbstractTableModel {
		private String statusText = STATUS_JOINING;

		@Override
		public int getColumnCount() {
			return 1;
		}

		public void setStatusText(String status) {
			statusText = status;
			fireTableRowsUpdated(0, 0);
		}

		@Override
		public int getRowCount() {
			return 1;
		}

		@Override
		public Object getValueAt(int rawIndex, int columnIndex) {
			return statusText;
		}

	}

}
