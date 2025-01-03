### 스트림 API가 지원하는 연산 기능

#### 필터링
주어진 스트림에서 조건에 맞는 요소를 필터링

##### Predicate 로 필터링
- Predicate 는 제네릭 타입을 받고 boolean을 반환하는 추상메서드 test를 가지고 있는 함수형 인터페이스이다.
```
List<Dish> dishes = menu.stream()
    .filter(Dish::isVegetarian)
    .collect(Collectors.toList());
```
- 위의 코드는 filter 에 메서드 참조로 boolean 을 반환하는 Dish 의 isVegetarian 을 넘겨주었다.
- 이는 함수형 인터페이스 Predicate<Dish> 로서 실행된다.

##### 고유 요소 필터링
- distinct() 는 중복된 요소를 필터링한다.
```
List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
numbers.stream()
	.filter(i -> i % 2 == 0) // Predicate<Integer> 로 짝수 값 필터링
	.distinct()
	.forEach(System.out::println);
```

##### TAKEWHILE
- 만약 칼로리 순으로 정렬되어 있는 리스트에서 300 칼로리 이하까지 필터링 해야하는 조건이 있다고 가정할 때 filter 를 사용하면 다음과 같은 코드를 작성할 수 있다.
```
List<Dish> dishes = menu.stream()
    .filter(d -> d.getCalories() <= 300)
    .collect(Collectors.toList());
```
- 위의 코드는 정렬되어 있으므로 300 칼로리를 초과하는 요소를 만나는 순간 탐색을 이어가는 의미가 없다.(이후의 요소들은 모두 300 칼로리를 초과하여 filter 조건에 부합하지 않기 때문)
- 만약 리스트의 크기가 엄청 크거나, 무한대라면 이는 성능 이슈로 이어질 수 있다.

이때 사용할 수 있는 것이 TAKEWHILE 이다.
```
List<Dish> dishes = menu.stream()
    .takeWhile(d -> d.getCalories() <= 300) // 300 칼로리 이하일 때 까지
    .collect(Collectors.toList());
```
- takeWhile 은 Stream 인터페이스의 default 메서드로 파라미터는 Predicate를 받는다.

##### DROPWHILE
- 주어진 조건이 참인 동안 스트림 요소를 건너뛰고, 조건이 거짓이 된 이후부터 모든 요소를 반환
```
List<Dish> dishes = menu.stream()
    .dropWhile(d -> d.getCalories() <= 300) // 300 칼로리 이하는 다 버림. 초과하는 것들만 반환
    .collect(Collectors.toList());
```
- TAKEWHILE 은 모든 리스트를 순회하지 않을 수 있지만, DROPWHILE은 항상 모든 리스트를 순회

##### limit
- 주어진 리스트에서 필요한 요소의 갯수가 정해져있을 때, limit 제한을 두어 필요한 수만큼 추출할 수 있다.
```
List<Dish> dishes = menu.stream()
    .filter(d -> d.getCalories() <= 300)
	.limit(3) // 3개 제한
    .collect(Collectors.toList());
```
- 3개를 선택한다면 즉시 반환

##### skip
- 주어진 리스트에서 일정 갯수만큼 건너뛰는 것이 가능하다.
```
List<Dish> dishes = menu.stream()
    .filter(d -> d.getCalories() <= 300)
	.skip(3) // 3개 건너뛰고 탐색
    .collect(Collectors.toList());
```

##### map
- 스트림에서 Function 함수형 인터페이스를 파라미터로 받는 메서드이다.
- Function 함수형 인터페이스는 제네릭 <T> 타입을 입력받아 <R> 타입을 반환하는 apply 추상 메서드를 가지고 있다.
```
List<Integer> dishes = menu.stream()
    .map(Dish::getCalories) // Integer 를 반환하는 메서드 참조. Stream<Integer>
    .collect(Collectors.toList()); // Stream<Integer> 를 toList()
```
- 위와 같이 Dish 에 getter 가 구현되어 있다는 가정하에 map의 아규먼트로 넘겨주면 getter 가 Function 함수형 인터페이스로 인식한다.

##### flatMap
- [] array 를 입력 받아 스트림으로 반환해주는 스트림 평면화를 할 때 쓰는 메서드
```
List<String> list = Arrays.asList("Hello", "World");
List<String> collect = list.stream()
    .map(s -> s.split("")) // Stream<String[]> 를 반환
    .flatMap(Arrays::stream) // String[] 을 풀어 String 으로 반환
    .distinct() // 중복 제거
    .collect(Collectors.toList());
```
- flatMap은 파라미터로 Function 함수형 인터페이스를 받는다. 따라서 위의 코드에서 Function 인터페이스의 시그니처는 Function<String[], Stream<String>> 와 같은 형태가 된다.

#### 검색과 매칭

##### anyMatch
- 조건이 하나라도 맞다면 true 반환
```
if(menu.stream().anyMatch(Dish::isVegetarian)) {
	return true;
}
```
- anyMatch 는 Predicate 가 최소 하나라도 맞다면 true 를 반환하는 최종 연산이다.

##### allMatch
- 조건이 모두 맞다면 true 반환
```
if(menu.stream().allMatch(Dish::isVegetarian)) {
	return true;
}
```
- allMatch 는 스트림의 모든 요소가 Predicate 조건과 일치한다면 true을 반환하는 최종연산이다.

##### noneMatch
- allMatch와 반대 연산을 수행
```
if(menu.stream().noneMatch(Dish::isVegetarian)) {
	return true;
}
```
- noneMatch 는 스트림의 모든 요소가 Predicate 조건과 맞지 않다면 true을 반환하는 최종연산이다.

##### 검색
- 주어진 스트림에서 요소를 반환
```
Optional<Dish> dish = 
	menu.stream()
		.filter(Dish::isVegetarian)
		.findAny(); // findAny. 임의의 요소를 반환

Optional<Dish> dish = 
	menu.stream()
		.filter(Dish::isVegetarian)
		.findFirst(); // 가장 먼저 찾는 요소를 반환
```

#### 리듀싱
- 스트림으로 연산을 진행한 뒤 컬렉션을 반환하는 것이 아니라, 특정한 값을 반환하는 최종 연산
```
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6, 7, 7, 8);
Stream<Integer> stream = numbers.stream();
Integer reduce = stream.reduce(0, (a, b) -> a + b);
```
- 위 코드는 numbers 의 값을 모두 더하는 코드이다.
- reduce 는 BinaryOperator 함수형 인터페이스를 파라미터로 받는 메서드이다.
- 위 코드에서는 reduce 에 명시적으로 초기값 0을 주고, 초기값에 연산을 누적하여 입력 값으로 사용하는 것이다.
- 만약 초기값이 없다면 결과 값 자체가 null이 되어버릴 수 있으니 반환 값은 Optional 이 된다.
```
Optional<Integer> optional = numbers.stream()
    .reduce(Integer::max);
```