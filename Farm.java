package P4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class Farm {
	private double availableFood;
	private AnimalList animals = new AnimalList();

	public Farm(String filename) { // change from P3 starter: has an argument, uses the load method
		load(filename);
	}

	public void exit(String filename) { // change from P3 starter: new method
		// save data to filename before leaving
		try (ObjectOutput out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
			// out.writeInt(animals.size()); // not needed if student doesn't use
			// animalsCount
			out.writeDouble(availableFood);
			out.writeObject(animals);
			System.out.println("Data saved successfully to " + filename + ".");
		} catch (FileNotFoundException e) {
			System.err.println("Cannot save your status!" + e.getMessage());
		} catch (IOException e) {
			System.err.println("I/O Error" + e.getMessage());
		}
	}

	public void load(String filename) { // change from P3 starter: new method
		// load current player status from filename
		File file = new File(filename);
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			// animalsCount = in.readInt(); // not needed if student doesn't use
			// animalsCount
			availableFood = in.readDouble();
			animals = (AnimalList) in.readObject();
			System.out.println("Data loaded from " + filename + ".");
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open file. Using default values!");
			setAvailableFood(1000);
			animals = new AnimalList();
			add(new Chicken());
			add(new Cow());
			add(new Llama());
			add(new Llama());
		} catch (IOException e) {
			System.err.println("I/O Error" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Internal Error!" + e.getMessage());
		}
	}

	public void makeNoise() {
		for (Animal animal : getAnimals())
			animal.sound();
	}

	public void feedAnimals() {
		for (Animal animal : getAnimals())
			if (availableFood >= Math.min(animal.getMealAmount(), (100 - animal.getEnergy())))
				availableFood -= animal.eat();
			else
				System.out.println("Not enough food for your animals! You need to collect more food items.");
	}

	public void animSort() {
		System.out.println("System not supported yet");
	}

	public void addClone(Animal anim) throws CloneNotSupportedException {
		// this method creates a clone of an animal and adds it to the list of animals
		// in the farm
		add((Animal) anim.clone());
	}

	public void add(Animal anim) { // add an animal object to animals, return true if added successfully and false
									// otherwise

		animals.addLast(anim);

	}

	public void printAnimals() {
		System.out.println(animals.toString());
	}

	public int getNumChicken() {
		int num = 0;
		for (Animal a : getAnimals())
			if (a instanceof Chicken)
				num++;
		return num;
	}

	public int getNumCows() {
		int num = 0;
		for (Animal a : getAnimals())
			if (a instanceof Cow)
				num++;
		return num;
	}

	public int getNumLlamas() {
		int num = 0;
		for (Animal a : getAnimals())
			if (a instanceof Llama)
				num++;
		return num;
	}

	public void printSummary() {

		System.out.println("The farm has:");
		System.out.printf("- %d animals (%d Chicken, %d Cows, and %d Llamas)\n", animals.size(), getNumChicken(),
				getNumCows(), getNumLlamas());
		System.out.printf("- %.2f units of available food\n", availableFood);
	}

	public double getAvailableFood() {
		return availableFood;
	}

	public void setAvailableFood(double availableFood) {
		if (availableFood >= 0 && availableFood <= 1000)
			this.availableFood = availableFood;
	}

	public AnimalList getAnimals() {
		return animals;
	}
}
