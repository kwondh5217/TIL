# 스프링 시큐리티 아키텍쳐
스프링 시큐리티의 동작 과정의 흐름

## DelegatingFilterProxy
보안과 관련된 인증 처리는 스프링 컨테이너로 오기 전 서블릿 필터에서 이루어진다.
스프링부트를 사용하면 자동으로 스프링은 서블릿 컨테이너의 필터에 DelegatingFilterProxy를 등록해준다. 이름에서 알 수 있듯이 인증처리를 위임하는 역할을 한다. <br>


DelegatingFilterProxy는 ApplicationContext에서 FilterChainProxy를 찾아서 요청에 대한 인증을 처리한다.

## FilterChainProxy
FilterChainProxy는 DelegatingFilterProxy가 인증을 처리할 때 사용하는 객체로 스프링 빈으로 등록되어 있다. FilterChainProxy는 내부에 스프링 빈으로 등록된 SecurityFilterChain을 가지고 있는데 이는 chain 형태로 인증을 처리하는 다수의 filter들로 구성되어 있다. FilterChainProxy는 인증을 처리할 때 SecurityFilterChain을 거치면서 인증을 처리한다.

## SecurityFilterChain
사용자의 요청에 대한 인증 및 인가를 처리하기 위한 필터들의 chain이다. SecurityFilterChain에 속해있는 filter들은 처리하는 요청들이 각각 다르고 인증 및 인가를 처리할 때 AuthenticationManager, AuthorizationManager를 사용하여 인증과 인가를 처리한다. 다음은 요청을 처리하는 filter들의 순서이다.

### 1. WebAsyncManagerIntegrationFilter

Security Context 는 Local Thread를 사용하여 스레드마다 고유한 저장공간에 Authentication(인증정보)를 저장하여 요청을 처리하게 된다. 스프링 프레임워크는 스레드 풀을 사용하여 다수의 스레드들이 요청을 처리하게 되는데, 각각의 스레드는 자신의 Local Thread를 사용하기 때문에 비동기적인 요청에 대한 처리는 할 수 없다. 하지만 WebAsyncManagerIntegrationFilter가 SecurityContextHolder의 전략에 맞게 SecurityContext를 통합해주는 역할을 한다. SecurityContextHolder란 SecurityContext를 가지고 있는 객체이다. 

요약하면 비동기처리를 할 수 있도록 도와주는 filter이다.

### 2. SecurityContextPersistenceFilter (세션을 사용할 경우)

스프링 시큐리티에서 요청을 처리할 때 Authentication을 사용하여 처리하는데, 만약 인증된 사용자에 대한 정보가 세션에 있다면 해당 요청은 다시 인증과정을 거칠 필요가 없다. 요청이 해당 필터에 들어오게 되면 SecurityContextPersistenceFilter는 내부에 있는 SecurityContextRepository를 조회하여 세션ID와 일치하는 인증정보가 있는지 확인한다.
SecurityContextRepository는 인터페이스로 여러가지의 구현체가 있는데 그 중에서 세션을 사용할 경우에는 HttpSessionSecurityContextRepository를 사용하게 된다. HttpSessionSecurityContextRepository는 세션에 있는 인증정보를 가져와 요청을 수행할 스레드의 Security Context에 저장한다. 해당 스레드는 요청을 처리하는 과정에서 다른 스레드와 해당 정보를 공유하지 않는다. 요청을 모두 처리하게 되면 Local Thread에 있는 인증정보를 세션에 갱신하고 스레드의 Local Thread를 비운다.

이 필터의 우선순위가 높은 이유는 만약 들어온 요청이 인증이 필요한 요청이라면 남은 필터들을 거치면서 인증처리를 해야하지만 이미 인증이 된 요청이라면 남아있는 필터들을 거칠 필요가 없기 때문에 우선순위가 높은 것이다.
JWT는 상태를 서버측에서 저장할 필요가 없는 stateless 인증이기 때문에 SecurityFilterChain에 sessionManagement 설정을 통하여 해당 필터를 비활성화 시켜줄 수 있다.



