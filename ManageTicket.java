import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ManageTicket {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		List<FlightTicketInformation> list = new ArrayList<>();
		int choose;

		do {
			showMenu();

			System.out.println("Chọn thao tác thực thi:");
			choose = scan.nextInt();

			switch (choose) {
			case 1:
				input(list);
				break;
			case 2:
				save(list, "Dulieubay.dat");
				break;
			case 3:
				list = read("Dulieubay.dat");
				break;
			case 4:
				sort(list);
				break;
			case 5:
				saveByAirLine(list);
				break;
			case 6:
				System.out.println("Thoát thành công");
			default:
				System.out.println("Error");
				break;
			}

		} while (choose != 5);
	}

	static void input(List<FlightTicketInformation> list) {
		System.out.println("Nhập số vé máy bay");
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			FlightTicketInformation ticket = new FlightTicketInformation();
			ticket.inputInformation();
			list.add(ticket);
		}
	}

	static List<FlightTicketInformation> read(String filename) {
		List<FlightTicketInformation> list = new ArrayList<>();

		FileInputStream in = null;
		ObjectInputStream ois = null;
		try {
			in = new FileInputStream(filename);
			ois = new ObjectInputStream(in);

			list = (List<FlightTicketInformation>) ois.readObject();
		} catch (Exception e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.getStackTrace();
				}
			}

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}
		return list;
	}

	static void save(List<FlightTicketInformation> list, String filename) {
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		try {
			out = new FileOutputStream(filename);
			oos = new ObjectOutputStream(out);

			oos.writeObject(list);
		} catch (Exception e) {
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.getStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}

	}

	static void sort(List<FlightTicketInformation> list) {
		Collections.sort(list, (o1, o2) -> {
			// TODO Auto-generated method stub
			if (o1.getPrice() < o2.getPrice()) {
				return -1;
			}
			return 1; 
		});
		for (int i = 0; i < list.size(); i++) {
			list.get(i).outputInformation();
		}
	}

	static void saveByAirLine(List<FlightTicketInformation> list) {
		List<FlightTicketInformation> vietjetAirList = getAirline(list, "VJ");
		save(vietjetAirList, "VietjetAir.dat");

		List<FlightTicketInformation> vietnamAirLineList = getAirline(list, "VN");
		save(vietnamAirLineList, "vietnamAirLineList.dat");

		List<FlightTicketInformation> jetstarAirLineList = getAirline(list, "JET");
		save(jetstarAirLineList, "jetstarAirLineList.dat");
		
		System.out.println("Lưu thành công");

	}

	static List<FlightTicketInformation> getAirline(List<FlightTicketInformation> list, String prex) {
		List<FlightTicketInformation> airlineList = new ArrayList<>();

		for (FlightTicketInformation ticket : list) {
			if (ticket.getId().startsWith(prex)) {
				airlineList.add(ticket);
			}
		}
		return airlineList;
	}

	static void showMenu() {
		System.out.println("---BẢNG TÁC VỤ---");
		System.out.println("1. Nhập thông tin vé máy bay");
		System.out.println("2. Lưu thông tin");
		System.out.println("3. Đọc và hiển thị thông tin từ file");
		System.out.println("4. Sắp xếp theo giá vé theo chiều tăng dần");
		System.out.println("5. Lưu thông tin vé theo hãng hàng không");
		System.out.println("6. Thoát");
	}
}
