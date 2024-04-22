# 스프링 시큐리티 아키텍쳐
스프링 시큐리티의 동작 과정의 흐름

## DelegatingFilterProxy
보안과 관련된 인증 처리는 스프링 컨테이너로 오기 전 서블릿 필터에서 이루어진다.
스프링부트를 사용하면 자동으로 스프링은 서블릿 컨테이너의 필터에 DelegatingFilterProxy를 등록해준다. 이름에서 알 수 있듯이 필터를 위임하는 프록시 객체이다. <br>


DelegatingFilterProxy는 FilterChainProxy를 통해 요청에 대한 인증을 처리한다.

## FilterChainProxy
FilterChainProxy는 DelegatingFilterProxy가 인증을 처리할 때 사용하는 객체로 스프링 빈으로 등록되어 있다. FilterChainProxy는 내부에 SecurityFilterChain을 가지고 있는데 이는 chain 형태로 인증을 처리하는 다수의 filter들로 구성되어 있다. FilterChainProxy는 인증을 처리할 때 SecurityFilterChain을 거치면서 인증을 처리한다.

## SecurityFilterChain
사용자의 요청에 대한 인증 및 인가를 처리하기 위한 필터들의 chain이다. SecurityFilterChain에 속해있는 filter들은 처리하는 요청들이 각각 다르고 인증 및 인가를 처리할 때 AuthenticationManager, AuthorizationManager를 사용하여 인증과 인가를 처리한다. 



