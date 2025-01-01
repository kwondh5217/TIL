#### 람다의 특징
##### 익명
- 메서드는 특정 클래스, 인터페이스에 속해 이름이 있지만, 람다는 이름이 없는 익명이다.
##### 함수
- 람다는 메서드처럼 특정 클래스에 종속되지 않으므로 함수라고 부른다.
##### 전달
- 람다는 메서드 인수로 전달하거나 변수로 저장할 수 있다.
```
list.sort((o1, o2 -> o1 - o2));
```
##### 간결성
- 익명 클래스와 비교하여 코드의 양이 줄어들고 간결하다.

#### 함수형 인터페이스
- 람다 표현식을 인수로 전달하기 위해서 인자값으로는 함수형 인터페이스가 선언되어야 한다.
- 함수형 인터페이스는 추상 메서드가 하나만 선언된 인터페이스를 뜻한다.
- @FunctionalInterface 어노테이션을 붙여 함수형 인터페이스를 강제할 수 있다.
- JVM은 컴파일 시점에 함수형 디스크립터와 람다 표현식이 일치하는 지 검사
- JVM은 컴파일 시 람다 표현식을 기반으로 익명 클래스를 생성하는 것이 아니라, JVM 명령어인 invokedynamic 명령어를 생성하여 런타임에 처리하도록 한다.
- 함수형 인터페이스에 추상 메서드가 여러개 있다면 JVM은 람다 표현식이 어떤 메서드를 구현하는 것인지 알 수 없다. 따라서 추상 메서드는 하나만 존재해야하며, 이를 함수형 인터페이스라고 한다.

#### 특별한 void 호환 규칙
람다의 바디에 일반 표현식이 있으면 void를 반환하는 함수 디스크립터와 호환된다.
```
Predicate<String> p = s -> list.add(s); // add 의 반환 값은 boolean 이지만 OK
```

#### 지역 변수 사용
- 람다 표현식 외부에 있는 변수를 참조하여 사용할 수 있다.
- 하지만 final 로 선언되거나, final 처럼 취급되어야 한다. 만약 값을 변경한다면 컴파일 에러를 발생시킨다.
```
int portNumber = 8080;
Runnable r = (() -> System.out.println(portNumber));
portNumber = 80801;


java: local variables referenced from a lambda expression must be final or effectively final
```
- Java는 Call By Value 로 메서드에 전달되는 인자는 값을 복사해서 전달한다. 즉 람다 표현식에 전달되는 portNumber 는 전달하는 시점에서 복사되어 전달되게 된다.
- 바로 아랫 라인에서는 portNumber를 변경하고 있다. 이렇게 되면 람다 표현식 내부에 전달된 값과 지역 변수 간의 불일치가 발생한다.
- 멀티 스레딩 환경에서 안전하지 못하기에 지역 변수는 불변으로 사용하도록 되어있다.

#### 메서드 참조
- 람다 표현식으로 메서드 참조를 전달할 수 있다.
```
Function<Person, Integer> lambda = (Person p) -> p.getAge(); // 람다
Function<Person, Integer> method = Person::getAge; // 메서드 참조
```
- 추상 메서드 시그니처의 형태에 맞게 적절한 함수형 인터페이스로 형변환이 가능하다.
```
people.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())); // 람다
people.sort(comparing(Person::getAge)); // 메서드 참조
```
- 위 두 코드는 동일한 기능을 수행한다.
- comparing 은 Comparator 객체를 반환하는 static 메서드로 파라미터로 Function 함수형 인터페이스를 받는다.
- 따라서 Person 을 입력받고 Integer 값인 age를 반환하는 메서드를 참조하여 람다표현식으로 사용할 수 있는 것이다.
```
public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
        Function<? super T, ? extends U> keyExtractor)
{
    Objects.requireNonNull(keyExtractor);
    return (Comparator<T> & Serializable)
        (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
}
```
- Comparator 의 comparing 도 내부에서는 Function 함수형 인터페이스의 추상 메서드인 apply 를 사용하여 Comparator 함수형 인터페이스를 반환하고 있다. 이때 사용하는 Function 함수형 인터페이스가 메서드 참조로 넘겨주는 Person::getAge 이다.
- 컴파일러는 주어진 메서드 참조가 추상 메서드 시그니처와 호환되는지 확인한다.

#### 생성자 참조
- 메서드 참조처럼 new 키워드를 사용하여 생성자를 참조할 수 있다.
```
Suplier<Apple> s = Apple::new;
Apple a1 = s.get();
Function<Integer, Apple> f = Apple::new;
Apple a2 = f.apply(1);
BiFunction<Integer, String, Apple> bf = Apple::new;
Apple a3 = bf.apply(1, "GREEN");
```
- 이때도 핵심은 추상 메서드 시그니처와 매핑되는 생성자가 있어야 한다.
- 예를 들어 생성자가 없다거나, 함수형 인터페이스와 호환되는 생성자가 없다면 컴파일 에러가 발생한다.

