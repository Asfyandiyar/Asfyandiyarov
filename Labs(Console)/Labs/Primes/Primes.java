//класс "Простые числа меньше 100"
public class Primes {
	//основной метод 
	//в нем перебираются все числа от 2 до 100(включительно)
	//с проверкой на то, является число простым или нет 
	public static void main(String[] args) {
		for(int i=2; i<=100; i++) {
			if(isPrime(i)) {
				System.out.println(i);
			}
		}
	}
	
	//метод, который сообщает, является аргумент простым числом или нет
	public static boolean isPrime(int n) {
		boolean ft=true;
		for(int i=2; i<n; i++) {
			if(n%i==0) { ft=false;}
		}
		return ft;
	}
}