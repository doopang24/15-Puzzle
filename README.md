# 15-Puzzle

15 퍼즐 구현하기

## 프로그램 구성

### 퍼즐 생성
- 2차원 퍼즐 생성 및 초기화
  - 0부터 15까지의 숫자를 순서대로 타일에 넣음
  - 출력할 때 0은 빈칸으로 출력
- 퍼즐 무작위로 섞기

### 퍼즐과 turn을 관리
- 퍼즐이 완성되었는지 확인
- 완성되었으면 축하 메시지와 몇 턴만에 성공했는지 출력하고 프로그램 종료
- 완성되지 않았으면 0과 위치를 바꿀 숫자 입력받음

### 입력 받기
- 움직일 퍼즐을 입력받은
- 올바른 입력인지 확인
  - 1 ~ 15 사이의 수인지 확인
  - 한글이 아닌 숫자로 입력했는지 확인
  - 공백으로 시작하지 않는지 확인
  - 0과 바꿀 수 있는 칸의 수인지 확인
- 올바른 입력이 아니면 다시 입력받음

### 퍼즐 자리 바꾸기
- 입력받은 숫자와 0의 자리 바꾸기


## 코드 설명

### main()
- 모든 코드의 시작
- 진입점과 프로그램의 흐름을 관리

### int[][] start()
- 시작 멘트 출력
- 2차원 퍼즐 생성해서 반환

### int[][] shuffle(int[][] numbers)
- 입력받은 2차원 퍼즐을 무작위로 섞음
- ThreadLocalRandom 활용

### void printStatus(int[][] puzzle, int turn)
- 현재 턴과 퍼즐을 출력

### boolean isOrdered(int[][] puzzle)
- 퍼즐이 완성되었는지 확인

### int getNumberToMove(int[][] puzzle)
- 움직일 숫자를 입력받음
- 입력받은 숫자가 올바른지 검증한 뒤 반환

### int checkRange(int inputNumber) throws IllegalArgumentException
- 입력받은 숫자가 1 ~ 15 범위 안에 있는지 확인

### int validatePosition(int inputNumber, int[][] puzzle) throws IllegalArgumentException
- 입력받은 숫자가 빈 칸과 교환 가능한지 확인

### int[][] moveTarget(int[][] puzzle, int targetNumber)
- 입력받은 숫자와 빈 칸의 자리를 바꾸는 메서드

### void printClosingMessage(int turn)
- 축하메시지와 턴을 표시하는 메서드


## Random 대신 ThreadLocalRandom
### Random이 멀티 쓰레드에서 위험한 이유
- java.util.Random은 멀티 쓰레드에서 하나의 Random 인스턴스를 공유하며 전역적으로 동작한다.
- 이때 같은 nanoTime에 수많은 Random 수많은 요청이 들어오게 되면,
    - Random의 난수 생성은 선형 합동 생성기(Linear Congruential Generator)알고리즘으로 동작한다.
    - 하나의 쓰레드가 동시 경합에서 이기면 다른 쓰레드는 자신이 이길 때까지 같은 동작을 반복한다.
    - 즉, 쓰레드가 난수를 생성할 때까지 Random을 호출한다.

### ThreadLocalRandom
- 동시성 문제를 해결하기 위해 각 쓰레드마다 생성된 인스턴스에서 각각 난수를 반환한다.
- 쓰레드들이 경합할 필요가 없다.

