### Collectors
- 최종 연산을 수행하는 팩토리 메서드를 제공하는 클래스
- 중간 연산의 결과를 컬렉션, 매핑, 연산을 통해 최종 결과를 반환하는 기능을 제공

#### 요약 연산
- Dish 는 칼로리를 필드 값으로 가지고 있다.
- 주어진 Dish 컬렉션에서 칼로리의 합계를 구해야하는 연산은 다음과 같은 코드로 옮길 수 있다.
```
Integer reduce = menu.stream()
    .map(Dish::getCalories)
    .reduce(0, Integer::sum);
```
- 위 코드는 Dish 컬렉션에서 스트림을 생성, Dish 를 칼로리로 매핑하여 `Stream<Integer>` 를 반환
- 반환된 `Stream<Integer>` 를 `reduce` 연산을 통해 초기값 0에 연산을 누적하는 코드이다.

위의 코드는 아래와 같이 Collectors 의 정적 메서드를 사용하면 더 간결해진다.
```
Integer collect = menu.stream()
    .collect(Collectors.summingInt(Dish::getCalories));
```
- `summingInt` 는 `int` 형 데이터의 누적 값을 계산하는 메서드이다.
- 파라미터로는 `<T>` 타입을 받고 `int` 를 반환하는 `applyAsInt` 추상 메서드를 가지는 `ToIntFunction` 함수형 인터페이스를 받는다.

**`summingInt`, `summingDouble`, `summingLong` 도 사용법은 동일하다.**

#### 문자열 연결
- Dish 컬렉션의 이름을 하나의 문자열로 만드는 코드를 자바 8 이전의 코드로 작성하면 다음과 같은 형태가 된다.
```
StringBuilder sb = new StringBuilder();
for(Dish dish : menu) {
	sb.append(dish.getName());
}
sb.toString();
```
- 위 코드를 자바 8 문법으로 바꾸면 아래와 같이 간결해진다.
```
menu.stream().map(Dish::getName).collect(Collectors.joining());
```
- `joining` 은 내부적으로 `StringBuilder` 를 사용해서 문자열 연산을 한다.
- 구분자가 필요하면 인자값으로 넘겨주면 됨

#### 다양한 표현방법
- 스트림 API가 지원하는 인터페이스, 메서드는 동일한 연산을 다양한 방법으로 구현할 수 있다.
- 가독성, 성능 등 어떤 것에 중점을 두어 구현할 지를 적절히 선택해야한다.
```
int totalCalories = menu.stream()
	.mapToInt(Dish::getCalories)
	.sum();

Integer reduce = menu.stream()
    .map(Dish::getCalories)
    .reduce(0, Integer::sum);
```
- 칼로리의 총합을 계산하는 두 코드의 결과 값은 동일하게 나올 것이다.
- 하지만 map 을 사용하는 두번째 코드는 박싱, 언박싱 비용이 발생하고, 첫번째 코드는 `mapToInt` 에서 `IntStream` 을 반환하기에 박싱, 언박싱 비용이 발생하지 않는다.

#### 그룹화
- 메뉴가 담긴 컬렉션에서 카테고리끼리 그룹화를 해야하는 경우
  - 예를 들어 고기, 생선, 야채 종류로 그룹화를 해야한다면
- `Collectors.grouping(Function<T, K> function)`메서드는 map으로 그룹화를 한다.
```
Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
	.collect(Collectors.grouping(Dish::getType));
```
- 만약 500 칼로리가 넘는 메뉴만 필터링해서 그룹화를 해야한다면 다음처럼 작성할 수 있다.
```
Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
	.collect(Collectors.grouping(Dish::getType, Collectors.filtering(dish -> dish.getCalories() > 500, Collector.toList())));
```
- 그룹화 이전에 filter 메서드를 사용하지 않은 이유는 filter 메서드에서 500이 넘지 않는 조건을 걸어버리면 키 자체가 사라질 수 있기 때문이다.
