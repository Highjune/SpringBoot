https://youtu.be/9uKdes4tX7c / 스프링부트 프로젝트시작(세팅) (20200421)
https://youtu.be/C-06XevGbtQ / porn.xml에서 쉽게 dependency붙이기(lombok) devtool추가 (20200421)


application.properties - 유일한 환경설정파일
static 폴더 - 정적파일들(일반 html 등) 두는 곳
templates 폴더 - html을 templates에 두면 타임리프가 된다(일반 html을 th를 붙여서 타임리프로 바꿈)
cf) 
springboot는 jsp를 쓰지 않는다.
타임리프(th)가 jsp의 역할을 함



@RestController는 string을 리턴하기 때문에 view가 따로 필요없다. 단순 문자열은 아니고 json데이터
RestController는 Restful API를 통해서 json 데이터를 주고 받는다.(client<->server사이에)
Restful 방식으로 개발하고 싶으면 RestController, 일반Web application이면 Controller
cf) RestController는 http함수(post, get, put, delete) + view
RestController는 jason작업이 자동으로 일어나서 client단의 view나 react가 서버로부터 jason으로 받는다. 
그러면 client는 서버가 보내준 jason을 jquery의 ajax로 받으면 된다(타입은 jason, success할 때 function으로 데이터 받아서 jason의 parse로 객체를 만들어서 ,으로 보여줌)

@SpringBootApplication 를 컨트롤+클릭 해보면 여러가지 annotation이 합쳐져 있다는 것을 알 수 있다.

