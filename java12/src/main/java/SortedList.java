import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

public class SortedList {

	static List<Person> sortedList = new ArrayList<>();

	public static void main(String[] args) {
		var personList = Arrays.asList(
				new Person("John", "Doe"),
				new Person("Bryan", "Sully"),
				new Person("Harry", "Ache"),
				new Person("Ali", "Doe"),
				new Person("Ali", "oe"),
				new Person("Ali", "oe"),
				new Person("Bry", "Dyre"));
		personList.stream().forEach(SortedList::addPerson);
		System.out.println(sortedList);
	}

	static void addPerson(Person person) {
		for (int i = 0; i < sortedList.size(); i++) {
			var existingPerson = sortedList.get(i);
			var compareResult = compare(existingPerson, person);
			if (compareResult > 0) {
				sortedList.add(i, person);
				return;
			}
		}
		sortedList.add(person);
	}

	static int compare(Person p1, Person p2) {
		return (p1.lastName + p1.firstName).compareTo(p2.lastName + p2.firstName);
	}

	static class Person {
		final String firstName;
		final String lastName;

		Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override
		public String toString() {
			return "Person{" +
					"firstName='" + firstName + '\'' +
					", lastName='" + lastName + '\'' +
					'}';
		}
	}
}


