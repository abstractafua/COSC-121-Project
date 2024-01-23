package P4;
//this class is the same as P3
@SuppressWarnings("serial")
public class Cow extends Animal{
	private static int id;
	public Cow() {
		setName("Cow" + ++id);
		setMealAmount(20);
	}
	public void sound(){
		if(isAlive()) System.out.println("Moo!");
	}
	public void milk(){
		if(isAlive()) System.out.println("We can milk cows as following:...!");
	}	
}
