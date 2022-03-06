# DesignPattern_Study_Bridge

# Notion Link
https://five-cosmos-fb9.notion.site/Bridge-a4bac96c3e88452c804e9952a618741b


# 가교 (Bridge)

### 의도

구현에서 추상을 분리하여

이들이 독립적으로 다양성을 가질 수 있도록 한다.

<aside>
🎈 다른 이름 : 핸들/구현부 (Handle/Body)

</aside>

![image](https://user-images.githubusercontent.com/18654358/156903996-e9c40591-58b3-4bc8-a78d-f98c18e84ff9.png)



**우리는 유연하게 !**

강아지를 자동차에도 태우고, 보트에도 태우고

고양이를 비행기에 태우고, 자동차에도 태우고 싶어..

클래스 구조를 고려하지 않고 바로 코딩한다면 동물이 2종류, 탈것이 3종류 이기 때문에 

총 여섯가지 클래스를 따로 만들어야 할 것이다.

- CatAirplane, CatCar, CatBoat
- DogAirplane, DogCar, DogBoat

<aside>
🎈 현재는 동물이 2종류이고 탈것이 3종류라 총 6개의 클래스가 만들어지는 것이고
탈것이 추가되거나 동물이 추가되면..?!

→ 이럴 때 Bridge 패턴을 사용할 수 있다.

</aside>

**탈 것 인터페이스를 만들고 Boat, Car, Airplane 탈 것 인터페이스에서 상속시킬 수 있다.**

- 그리고 탈 것 인터페이스 안에 추상화된 동물 클래스를 가지도록 한다면
- 우리가 원하는 자유로운 구조를 만들 수 있다.
- 이 후 새로운 동물이 추가되더라도 탈것과는 무관하게 확장할 수 있는 것이다.
    - 또한 새로운 탈것이 생기더라도?! 가능하다. 🙂

![image](https://user-images.githubusercontent.com/18654358/156904008-7b1e6705-4157-4eec-865f-720c2d543d46.png)

위 구조를 다시보면 Bridge 패턴에서 말하는 Abstraction과 Implementation 개념에서

무엇이 Abstraction이고 무엇이 Implementation인지 알 수 없다.

- Abstraction과 Implementation이라는 키워드는 GoF 디자인패턴을 작성한 저자들이 정의한 내용이다.

**Bridge 패턴에 대해 다시 한번 확인하보자.**

- **Abstraction**이 존재하고 그 안에 **Implementor**가 존재한다.
- 여기서 **Implementor** 또한 **Abstract한 Layer** 이다.
    - *둘 다 Abstract 한 Layer 이지만 Abstraction이 Implementor 보다 고수준의 개념이다!*
- **Client는 더 추상적인 개념인 Abstraction을 이용해 접근**하고
    - 그 내부 수행은 Implementor가 수행될 수 있는 것이다.



![image](https://user-images.githubusercontent.com/18654358/156904019-cee6c909-914d-4fd0-a4f2-74073f4ccb53.png)



[Power.java](http://Power.java) (interface)

```java
public interface Power {
    void powerUp();
    void powerDown();
}
```

[Diesel.java](http://Diesel.java), [Motor.java](http://Motor.java) (implements Power)

```java
public class Diesel implements Power{

    @Override
    public void powerUp() {
        System.out.println("engine power up");
    }

    @Override
    public void powerDown() {
        System.out.println("engine power down");
    }
}

/////////////////////////////////////////////
public class Motor implements Power{
    @Override
    public void powerUp() {
        System.out.println("motor power up");
    }

    @Override
    public void powerDown() {
        System.out.println("motor power down");
    }
}
```

[Car.java](http://Car.java) (abstract Class)

```java
public abstract class Car {
    private Power power;

    public Car(Power power) {
        this.power = power;
    }

    public void drive(){
        power.powerUp();
    }
    public void stop(){
        power.powerDown();
    }
}
```

[SUV.java](http://SUV.java) (extends Car)

```java
public class SUV extends Car{
    public SUV(Motor motor) {
			super(motor);
		}
}
```

Moter 엔진을 가진 SUV를 만들어서 Drive 시켜보자!

```java
public class Main {

    public static void main(String[] args) {
        SUV suv = new SUV(new Motor());
        suv.drive();
        suv.stop();
    }
}
```

```java
C:\Users\ssh1224\.jdks\azul-15.0.5\bin\java.exe -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:49875,suspend=y,server=n -javaagent:C:\Users\ssh1224\AppData\Local\JetBrains\IntelliJIdea2021.3\captureAgent\debugger-agent.jar -Dfile.encoding=UTF-8 -classpath "D:\dev\study\DS_Bridge\out\production\DS_Bridge;C:\Program Files\JetBrains\IntelliJ IDEA 2021.3\lib\idea_rt.jar" Main
Connected to the target VM, address: '127.0.0.1:49875', transport: 'socket'
motor power up
motor power down
Disconnected from the target VM, address: '127.0.0.1:49875', transport: 'socket'

Process finished with exit code 0
```

### 우리 제품에 적용시킬 수 있는 케이스는 무엇이 있을까?

- 인사평가에서 **평가(Evaluation)**은 **성과평가**(PerformanceEvaluation), **역량평가**(CapabilityEvaluation) 으로 정의될 수 있다.
- 각 평가의 방식은 **원점수**(Rawscore), **등급**(Grade)의 형태로 평가를 진행할 수 있다.
- 우리(Client)는 평가결과를 알기 위해 성과평가나 역량평가를 생성하며
- 생성 시 어떤 평가방식으로 평가를 할 것인지 주입하여 사용할 수 있다.

![image](https://user-images.githubusercontent.com/18654358/156904025-cf021456-dabf-482f-9a49-ce48f327e2ac.png)

[Evaluator.java](http://Evaluator.java) (interface)

```java
package hrpex;

public interface Evaluator {
    void sum();
    void percentage();
}
```

RawscoreEvaluator.java, [GradeEvaluator.java](http://GradeEvaluator.java) (implements Evaluator)

```java
package hrpex;

public class RawscoreEvaluator implements Evaluator {
    @Override
    public void sum() {
        System.out.println("rawscore sum!!!");
    }

    @Override
    public void percentage() {
        System.out.println("rawscore percentage!!!");
    }
}

/////////////////////////////////////////////////////////////////
package hrpex;

public class CapabilityEvaluation extends Evaluation{
    public CapabilityEvaluation(Evaluator evaluator) {
        super(evaluator);
    }
}
```

[Evaluation.java](http://Evaluation.java) (abstract class)

```java
package hrpex;

public abstract class Evaluation {
    private Evaluator evaluator;

    public Evaluation(Evaluator evaluator){
        this.evaluator = evaluator;
    }

    public void sumEvaluation(){
        System.out.println("[Evaluation] Call sumEvaluation");
        this.evaluator.sum();
    }

    public void percentageEvaluation(){
        System.out.println("[Evaluation] Call percentageEvaluation");
        this.evaluator.percentage();
    }
}
```

PerformanceEvaluation.java, [CapabilityEvaluation.java](http://CapabilityEvaluation.java) (extends Evaluation)

```java
package hrpex;

public class PerformanceEvaluation extends Evaluation{
    public PerformanceEvaluation(Evaluator evaluator) {
        super(evaluator);
    }
}

/////////////////////////////////////////////////////////
package hrpex;

public class CapabilityEvaluation extends Evaluation{
    public CapabilityEvaluation(Evaluator evaluator) {
        super(evaluator);
    }
}
```

**사용코드**

- 원점수 방식의 성과평가를 생성하고
- 성과평가의 합산방식의 결과를 조회
- 성과평가의 백분율방식의 결과를 조회

```java
package hrpex;

public class HrpExMain {
    public static void main(String[] args) {
        System.out.println("hrp ex main");

        PerformanceEvaluation performanceEvaluation = new PerformanceEvaluation(new RawscoreEvaluator());
        performanceEvaluation.sumEvaluation();
        performanceEvaluation.percentageEvaluation();
    }
}
```

### 활용성

- 추상적 개념과 이에 대한 구현 사이의 지속적인 종속 관계를 피하고 싶을 때
    - 런타임에 구현 방법을 선택하거나 구현 내용을 변경하고 싶을 때!
- 추상적 개념과 구현 모두가 독립적으로 서브클래싱을 통해 확장되어야 할 때
    - 이 때, 가교 패턴은 개발자가 구현을 또 다른 추상적 개념과 연결할 수 있게 할 뿐 아니라, 각각을 독립적으로 확장 가능하게 한다.
- 추상적 개념에 대한 구현 내용을 변경하는 것이 다른 관련 프로그램에 아무런 영향을 주지 않아야 할 때
    - 추상적 개념에 해당하는 클래스를 사용하는 코드들은 구현 클래스가 변경되었다고 해서 다시 컴파일 되지 않아야 한다.
    

### 구조

![image](https://user-images.githubusercontent.com/18654358/156904031-e021468c-c2c4-40c7-9cfd-acf0eaf4b532.png)

### 참여자

**Abstraction**

- 추상적 개념에 대한 인터페이스를 제공하고 객체 구현자에 대한 참조자를 관리한다.

**RefinedAbstraction**

- 추상적 개념에 정의된 인터페이스를 확장한다.

**Implementor**

- 구현 클래스에 대한 인터페이스를 제공한다.
- 실질적인 구현을 제공한 서브클래스들에 공통적인 연산의 시그니처만 정의한다.
- 이 인터페이스는 Abstraction 클래스에 정의된 인터페이스에 정확하게 대응할 필요가 없다.
- 즉, 두 인터페이스는 서로 다른 형태일 수 있다.
- 일반적으로 Implemntor 인터페이스는 기본적인 구현 연산을 수행하고
- Abstraction은 더 추상화된 서비스 관점의 인터페이스를 제공한다.

**ConcreateImplementor**

- Implementor 인터페이스를 구현하는 것으로 실제적인 구현 내용을 담았다.

### 협력방법

- Abstraction  클래스가 사용자 요청을 Implementor 객체에 전달한다.

### 결과

1. 인터페이스와 구현 분리
    1. 구현이 인터페이스에 얽매이지 않게 된다.
    2. 추상적 개념에 대한 어떤 방식의 구현을 택할지가 런타임에 결정될 수 있다.
    3. 이는 런타임에 어떤 객체가 자신의 구현을 수시로 변경할 수 있음을 의미한다.
2. 확장성 제고
    1. Abstraction과 Implementor를 독립적으로 확장할 수 있다.
3. 구현 세부 사항을 사용자에게서 숨기기
    1. 상세한 구현 내용을 사용자에게서 은닉할 수 있다.
