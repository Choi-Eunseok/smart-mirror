# 자작 스마트 미러
### 2018
### 작동 기기
### 자바, 라떼판다(초소형 컴퓨터)

학교에서 주제탐구 대회를 하는데 IOT에 관해서 탐구하고 특정한 물건을 만들어 보자고 했었다. 그때 팀원들의 다양한 아이디어가 나왔었는데, 그중에서 우리의 힘으로 만들 수 있는 것을 만들어 보자고 해서 스마트 미러를 만들게 되었다. 스마트 미러를 만들게 되었을 때 라즈베리파이에 기존에 인터넷에 있는 코드를 넣고, 인공지능 비서처럼 작동이 되게 만들 수 있었지만 직접 만들어 보는 것이 더 유익할 것 같아서 직접 만들게 되었다. <br/>
디스플레이에 디자인 된 창을 띄워야 하므로 가장 사용하기 쉬울 것 같은 자바를 사용하였다.
<br/>

전체적인 UI를 나타내는 Main.java
https://github.com/Choi-Eunseok/smart-mirror/blob/fef596aa2b1c574634531cefe02d9b38cded99db/SMART_MIRROR_1.8/src/Main.java#L25-L101
<div align="center"><img width="20%" alt="image" src="https://user-images.githubusercontent.com/61959836/211311542-4aa678fb-c243-4c68-85b9-611e666ef903.png"></div>
<br/>

스마트 미러에 날씨를 표시하기 위해서 기상청 날씨누리에서 제공해주는 RSS서비스를 활용하였다. RSS서비스에서 는 XML의 형식으로 10분마다 일기예보를 업데이트해서 제공을 해준다. 그래서 위의 코드에서 계속해서 XML을 받 으면서 그 중에서 필요한 부분인 일주일간의 날씨, 날짜, 요일을 잘라서 메인 프로그램에 계속적으로 전송을 해주는 스레드인 RSS_weather.java

▼현재의 날씨와 예정 날씨를 rss로 주는 사이트에서 그 값들만 가져오는 함수
https://github.com/Choi-Eunseok/smart-mirror/blob/fef596aa2b1c574634531cefe02d9b38cded99db/SMART_MIRROR_1.8/src/RSS_weather.java#L20-L68
