import java.util.Scanner;

public class Temperature {

	public static int temp;

	public static int toFahrenheit (int c){
		temp = (c * 9/5) + 32;
		return temp;
	}
	
	public static int toCelsius (int f) {
		temp = (f - 32) * 5/9;
		return temp;
	
	}
	
	public static void setFahrenheit (int f) {
		temp = f;
		
	}

	public static void setCelsius (int c) {
		temp = c;
		
	}

	

	public static void main (String [] args){
	
	Scanner sc = new Scanner(System.in);
	int temperature = sc.nextInt();
	/** setFahrenheit(temperature);														Ex 07;
	System.out.println("Temperature in Celsius is : \n" + toCelsius(temperature)); **/
	setCelsius(temperature);
	System.out.println("Temprerature in Fahrenheit is : \n" + toFahrenheit(temperature));
	 // Ex 08;
	}

}
