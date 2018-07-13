package org.jmeshtru.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;

public class FirstExample {

	static void sayHello(String... names) {
		Observable.from(names).subscribe(s -> System.out.println("Hello " + s));
	}

	public static void main(String[] args) {
		sayHello("Rama", "Krishna", "Raghava Simha");

		PublishSubject<Integer> subject = PublishSubject.create();
		subject.onNext(1);
		subject.subscribe(System.out::println);
		subject.onNext(2);
		subject.onNext(3);
		subject.onNext(4);
	}
}
