package P4;
import java.io.Serializable;
import java.util.Iterator;


public class AnimalList implements Iterable<Animal>, Serializable {

	private static int size;
	private AnimalNode head = null, tail = null;

	public AnimalList() {
	}

	int size() {
		if (head == null)
			return 0;
		else
			return size;
	}

	boolean isEmpty() {
		return (size == 0);
	}

	// Adding
	void addFirst(Animal animal) {
		AnimalNode newNode = new AnimalNode(animal);
		if (!isEmpty()) {
			newNode.next = head;
			head = newNode;
		} else
			head = tail = newNode;

		size++;
	}

	void addLast(Animal animal) {
		AnimalNode newNode = new AnimalNode(animal);
		if (isEmpty())
			head = tail = newNode;
		else {
			tail.next = newNode;
			tail = newNode;

			size++;
		}
	}

	void add(int index, Animal animal) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Invalid index value " + index + "Capacity: " + size);
		else if (index == size)
			addLast(animal);
		else if (index == 0)
			addFirst(animal);
		else {
			AnimalNode newNode = new AnimalNode(animal);
			AnimalNode pointer = head;
			for (int i = 0; i < index - 1; i++) // reference to node right before index
				pointer = pointer.next;

			newNode.next = pointer.next;
			pointer.next = newNode;

			size++;
		}

	}

	// Removing

	Animal removeFirst() {

		if (isEmpty())
			return null;

		else if (size == 1) {
			AnimalNode temp = head;
			head = tail = null;
			size--;
			return temp.element;
		} else {
			AnimalNode pointer = head;
			head = head.next;
			size--;
			return pointer.element;
		}
	}

	Animal removeLast() {

		if (isEmpty())
			return null;

		else if (size == 1)
			return removeFirst();

		else {
			AnimalNode pointer = tail;
			AnimalNode current = head;
			for (int i = 1; i < size - 1; i++)
				current = current.next;
			tail = current;
			tail.next = null;
			size--;
			return pointer.element;

		}

	}

	Animal remove(int index) {

		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index value " + index + "Capacity: " + size);
		} else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else {
			AnimalNode pointer = head;

			for (int i = 0; i < index - 1; i++)
				pointer = pointer.next; // pointer is index - 1

			AnimalNode temp = pointer.next; // store index value in temp
			pointer.next = temp.next; // make previous point to index value + 1;
			size--;

			return temp.element;
		}

	}

	// Getting
	Animal getFirst() {
		if (isEmpty())
			return null;
		else
			return head.element;
	}

	Animal getLast() {
		if (isEmpty())
			return null;
		else
			return tail.element;
	}

	Animal get(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Invalid index value " + index + "Capacity: " + size);

		else if (isEmpty())
			return null;

		else if (index == 0)
			return getFirst();

		else if (index == size - 1)
			return getLast();

		else {
			AnimalNode pointer = head;

			for (int i = 0; i < index; i++)
				pointer = pointer.next;

			return pointer.element;
		}

	}

	// Setting

	Animal set(int index, Animal animal) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Invalid index value " + index + "Capacity: " + size);

		else {
			AnimalNode pointer = head;
			Animal temp;
			for (int i = 0; i < index; i++)
				pointer = pointer.next;

			temp = pointer.element;
			pointer.element = animal;

			return temp;

		}

	}

	public String toString() { //
		String s = "";
		AnimalNode pointer = head;

		while (pointer != null) {
			s += pointer.element + "\n";
			pointer = pointer.next;
		}
		return s;
	}

	// custom methods

	AnimalList getHungryAnimals() {

		if (head == null & tail == null)
			return null;
		else {

			int idx = 0;

			AnimalList list = new AnimalList();
			AnimalNode pointer = head;

			while (pointer != null) {

				if (pointer.element.getEnergy() < 50)
					list.add(idx, pointer.element);

				pointer = pointer.next;

				idx++;

			}
			return list;

		}
	}

	AnimalList getStarvingAnimals() {

		if (head == null & tail == null)
			return null;

		else {

			int idx = 0;

			AnimalList list = new AnimalList();
			AnimalNode pointer = head;

			while (pointer != null) {

				if (pointer.element.getEnergy() < 17)
					list.add(idx, pointer.element);

				pointer = pointer.next;

				idx++;

			}
			return list;

		}
	}

	AnimalList getAnimalsInBarn() {

		AnimalList list = new AnimalList();

		int i = 0;
		AnimalNode pointer = head;

		while (pointer != null) {

			double y = pointer.element.getY();
			double x = pointer.element.getX();

			if (x >= 450 && x <= 550 && y <= 150 && y >= 50)
				list.add(i, pointer.element);

		}
		if (!list.isEmpty())
			return list;
		else
			return null;

	}

	double getRequiredFood() {

		double amount = 0;
		AnimalNode pointer = head;

		while (pointer != null) {
			amount += pointer.element.getMealAmount();
			pointer = pointer.next;
		}

		return amount;
	}

	// **********************************************BONUS*************************************
	AnimalList getByType(Class<Animal> class1) {
		AnimalNode n = head;
		Animal a = class1.cast(getClass());
		
		
		if (head == null)
			return null;
		else if (a instanceof Cow) {
			AnimalList cow = new AnimalList();
			while (n.element instanceof Cow) {
				cow.addFirst(n.element);
				n = n.next;
			}
			return cow;
		} else if (a instanceof Chicken) {
			AnimalList chick = new AnimalList();

			while (n.element instanceof Chicken) {
				chick.addFirst(n.element);
				n = n.next;
			}
			return chick;
		} else {
			AnimalList llama = new AnimalList();
			while (n.element instanceof Llama) {
				llama.addFirst(n.element);
				n = n.next;
			}
			return llama;
		}

	}

	class AnimalNode implements Serializable {
		Animal element;
		AnimalNode next;

		public AnimalNode(Animal a) {
			element = a;

		}
	}

	@Override
	public Iterator<Animal> iterator() {
		return new MyIterator();

	}

	class MyIterator implements Iterator<Animal> {
		public AnimalNode pointer = head;

		public boolean hasNext() {
			return (pointer != null);
		}

		public Animal next() {
			Animal temp = pointer.element;
			pointer = pointer.next;
			return temp;
		}

	}

}
