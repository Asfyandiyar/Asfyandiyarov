//класс "Является ли строка палиндромом"
public class Palindrome {
	//основной метод
	//в нем проверяется, является ли введённая строка палиндромом или нет, 
	//и выводится соответствующее сообщение на экран
	public static void main(String[] args) {
		String s="";
		for(int i = 0; i < args.length; i++) {
			s = s + args[i];
		}
		if(isPalindrome(s)) {
			System.out.println("This string is palindrome");
		} else {
			System.out.println("This string is not palindrome");
		}
	}

	//метод, формирующий обратную запись введенной строки
	public static String reverseString(String s) {
		String lS="";
		for(int i=s.length()-1; i>=0; i--) {
			lS+=s.charAt(i);
		}
		return lS;
	}
	
	//метод, который выявляет совпадение строк
	public static boolean isPalindrome(String s) {
		//boolean b=false;
		if(s.equals(reverseString(s))) {
			return true;
		} else {	
			return false;
		}
	}
}