package P4;
//this class is the same as P3
@SuppressWarnings("serial")
public class Llama extends Animal{
	private static int id;
	public Llama() {
		setName("Llama" + ++id);
		setMealAmount(9);
	}
	public void sound(){
		if(isAlive()) System.out.println("Hmmm!");
	}
	public void jump(){
		if(isAlive()) System.out.println("Llamas can jump as following:...!");
	}
}
