### 자바8 이전의 한계
#### 요구사항
- 사과 리스트 중에서 녹색 사과를 필터링 해야함

**코드로 옮기면 다음과 같이 표현할 수 있음**
```
public static List<Apple> filterAppleByColor(List<Apple> apples) {
	List<Apple> filtered = new ArrayList<>();
	for(Apple apple : apples) {
		if("GREEN".equals(apple.getColor()){
			filtered.add(apple);
		}
	}
	return filtered;
}
```

- 요구사항을 충족하였지만 필터링 해야하는 색깔이 변경되는 경우에 코드 내부를 수정해야 하기 때문에 유연한 구조가 아님.
- 해결 방법으로는 파라미터로 필터링할 색깔을 입력 받아서 처리하는 방법이 있음.
- 하지만 추가적으로 다른 기준이 적용되어야 한다면(예 : 무게) 중복된 코드, 혹은 유지보수에 좋지 못한 구조가 됨.

이러한 문제점을 동작 파라미터화, 람다 표현식을 통해 해결

#### 동작 파라미터화
- 메소드가 실행해야할 코드가 파라미터로 전달 되는 것
#### 목적
- 유연한 변경에 대응하기 위함
- 코드 내부의 관심사 분리를 위함이기도 함
- 자주 변경되는 요구 사항마다 메소드를 정의하는 것 보다 실행해야할 동작을 파라미터로 넘기는 것이 유연함

```
List<Apple> filteredApples = filterApples(apples, new ApplePredicate() {
    @Override
    public boolean test(Apple apple) {
        return "GREEN".equals(apple.getColor());
    }
});

public static List<Apple> filterApples(List<Apple> apples, ApplePredicate predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : apples) {
        if (predicate.test(apple)) {
            result.add(apple);
        }
    }
    return result;
}
```

- 위와 같은 익명 클래스, 혹은 인터페이스를 구현한 구현체를 파라미터로 넘겨주는 방식, 람다 표현식이 동작 파라미터화를 구현하는 방법
- 이 방법은 디자인 패턴에서 전략 패턴으로 불리우며, 위 코드에서 ApplePredicate 가 알고리즘 패밀리이고, 동적으로 넘어오는 파라미터를 전략이라고 한다.


#### 익명 클래스, 구현체를 사용하는 동작 파라미터화의 문제점
- 구현체를 사용하는 경우 관리해야할 클래스 파일이 많아진다.
- 익명 클래스를 사용하는 경우 코드의 가독성이 떨어진다.

**이러한 문제점을 해결하기 위해 람다 표현식이 등장하였다.**

```
List<Apple> filteredApples = filterApples(apples, (apple -> "GREEN".equals(apple.getColor());

public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : apples) {
        if (predicate.test(apple)) {
            result.add(apple);
        }
    }
    return result;
}
```
- 익명 클래스와 비교하여 코드의 양이 줄어들어 코드의 가독성이 향상
- 구현체 없기에 관리해야할 클래스 파일이 줄어듬