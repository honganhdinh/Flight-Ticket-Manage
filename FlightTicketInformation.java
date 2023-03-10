import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightTicketInformation implements Serializable {
	Scanner input = new Scanner(System.in);

	public FlightTicketInformation() {
		// TODO Auto-generated constructor stub
	}

	String id, name, bag, date;
	int price;

	public FlightTicketInformation(String id, String name, String bag, String date, int price) {
		super();
		this.id = id;
		this.name = name;
		this.bag = bag;
		this.date = date;
		this.price = price;
	}

	public void inputInformation() {
		System.out.println("Nhập mã vé: ");
		while (true) {
			id = input.nextLine();
			String pattermStr = "";
			if (id.startsWith("VJ")) {
				pattermStr = "VJ[1-9]{1}[0-9]{2}";
			} else if (id.startsWith("VN")) {
				pattermStr = "VN[1-9]{1}[0-9]{2}[0-9]?";
			} else if (id.startsWith("JET")) {
				pattermStr = "JET[1-9]{1}[0-9]{2}";
			} else {
				System.err.println("Nhập lại");
				continue;
			}
			Pattern pattern = Pattern.compile(pattermStr);
			Matcher matcher = pattern.matcher(id);

			if (matcher.find()) {
				break;
			} else {
				System.err.println("Nhập lại");
			}

		}

		System.out.println("Nhập tên chuyến bay: ");
		name = input.nextLine();

		System.out.println("Nhập ngày bay: ");
		date = input.nextLine();

		System.out.println("Nhập hành lí kí gửi: ");
		bag = input.nextLine();

		System.out.println("Nhập giá vé: ");
		price = input.nextInt();

	}

	@Override
	public String toString() {
		return "FlightTicketInformation [id=" + id + ", name=" + name + ", bag=" + bag + ", date=" + date + ", price="
				+ price + "]";
	}

	public void outputInformation() {
		System.out.println(toString());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBag() {
		return bag;
	}

	public void setBag(String bag) {
		this.bag = bag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
