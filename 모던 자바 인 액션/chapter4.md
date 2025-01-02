##### 스트림이란 ?
책에서 설명하는 스트림의 정의는 다음과 같다.
- 스트림이란 데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소로 정의할 수 있다.
  
잘 와닿지 않는다.

관심사로 컬렉션과 스트림을 구분지어 보자.

컬렉션 인터페이스의 주요 메서드는 다음과 같다.
- int size();
- boolean isEmpty();
- boolean contains(Object o);
- boolean add(E e);
- boolean remove(Object o);

컬렉션은 데이터를 저장하고, 추가하거나 삭제하며, 관리하는 것을 목적으로 한다.

다음으로 스트림 인터페이스의 주요 메서드이다.
- Stream<T> filter(Predicate<? super T> predicate);
- <R> Stream<R> map(Function<? super T, ? extends R> mapper);
- Stream<T> sorted(Comparator<? super T> comparator);

스트림은 데이터를 필터링, 매핑, 정렬 등의 방식으로 연산하는 데 초점이 맞춰져 있다.

##### 스트림의 특징
1. 일회성
- 스트림은 데이터의 관리를 지원하지 않으므로 재사용할 수 없다. 새로운 연산을 위해서는 새로운 스트림을 생성해야 한다.
2. 다양한 데이터 소스
- 스트림은 컬렉션뿐만 아니라 배열, I/O 채널, 파일 등 다양한 데이터 소스에서 생성될 수 있다.
3. 중간 연산과 최종 연산
- 중간 연산: filter, map, sorted와 같이 데이터를 변환하거나 필터링. 이들은 lazy(지연 평가)되며, 최종 연산이 호출되기 전까지 실행되지 않는다.
- 최종 연산: collect, forEach, reduce와 같이 스트림을 닫고 결과를 반환.


##### 스트림 API 장점
- 선언형 코드 스타일로 간결성, 가독성이 좋아진다.
- filter, sorted, map 등 다양한 연산을 파이프라인으로 연결할 수 있어 유연성이 좋아진다.
- parallelStream() 으로 손쉽게 병렬처리가 가능하다.

##### 스트림과 컬렉션의 차이
컬렉션은 모든 데이터를 메모리에 로드 후 사용하는 방식이다.
이런 코드를 작성하진 않겠지만 예시로 다음과 같은 코드가 있다고 가정하자.
```
List<Member> findAll();

public List<Member> findAllByArea() {
	List<Member> list = repository.findAll();
	List<Member> result = new ArrayList<>();
	for(Member member : list) {
		if(member.area.equals("AREA")){
			result.add(member);
		}
	}
	return result;
}
```
- DB의 Member 테이블의 모든 로우를 조회하여 메모리에 로드하게 된다.
- 만약 데이터가 메모리의 크기를 초과한다면 OutOfMemory 가 발생할 수도 있다.

스트림은 필요한 데이터만 소비하는 방식이라 모든 데이터를 메모리에 로드하지 않는다.
MySQL의 커서를 사용하면 다음처럼 코드를 작성할 수 있다.
```
Stream<Member> findAll();

public List<Member> findAllByArea() {
	return repository.findAll()
			.filter((m) -> m.area.equals("AREA"))
			.collect(toList());
}
```

위 코드는 데이터베이스의 모든 데이터가 메모리에 준비되고 실행되는 것이 아니라, 한건씩 소비하는 방식으로 메모리 성능상 이점을 누릴 수 있다.
하지만 스트림은 재사용이 불가능하므로 collect 를 호출한 시점에서 재사용이 불가능하게 된다.

##### 내부 반복의 장점
최적화된 데이터 처리
* 자동 최적화 :  내부 반복을 사용하면 데이터 표현과 하드웨어를 활용한 병렬성을 JVM이 자동으로 최적화 함. 개발자는 이를 명시적으로 처리할 필요가 없음.
* 루프 퓨전 (Loop Fusion) : 여러 중간 연산(filter, map 등)이 병합 가능한 연산이라면, 컬렉션을 두 번 이상 순회하지 않고, 하나의 루프로 합쳐서 처리 예: filter와 map을 함께 사용할 경우, 내부적으로 두 연산을 병합하여 단일 반복으로 처리
* 쇼트 서킷 (Short-Circuit) 조건이 만족될 경우 모든 데이터를 다 소비하지 않더라도 스트림 처리를 중단하고 결과를 반환 예: findFirst, anyMatch 등의 연산은 조건이 충족되면 스트림을 더 이상 순회하지 않고 즉시 결과를 반환


