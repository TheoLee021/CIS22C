
public class LabPrinter {
	protected String secret;
	
	public LabPrinter(String secretStringValue) {
		secret = secretStringValue;
	}
	
	void print2Plus2() {
		System.out.println("2 + 2 = 4");
	}
	
	void printSecret() {
		System.out.println("Secret string: \"" + secret + "\"");
	}
}
