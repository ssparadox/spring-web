* Local URL
	* http://localhost:8888/console
	* http://localhost:8888/swagger-ui.html
* 완료조건
	* 마감은 11/26(일) 24:00 까지로 정한다.
	* 상세 기능 대한 분배는 조별로 위임한다.
	* Priority 가 높은 기능을 우선적으로 구현한다.
	* Priority 가 낮은 기능은 코드에 주석으로 TODO로 마킹할 경우, 감점 없이 다음 주차 숙제에 포함시킨다.
	* 작업이 완료되면 직접 merge 한다. 컴파일 오류가 나타나건, Exception 이 발생하지 않는 상황이라면 merge 의 횟수에 대한 제약은 없다.
	* 잘 동작하던 코드에 오류가 발생하고 이를 강사가 알게 될 경우, 오류코드를 작성한 사람에게 email 및 trello 로 Noti 합니다.
	* 만약 Noti 후 1시간 이내에 응답이 없을 경우 감점 처리 합니다. (따라서 새벽에 merge 하는 것은 자제 바랍니다.)
	* 잘 모르는 내용은 google 및 강사에게 문의해 주세요. 최대한 친절히 답해드립니다.
* Git 사용방법!!
	* /Robinkim0182/knu-was 를 fork 따서 자신의 repository 로 가져온다.
	* 자신의 repository 에 있는 프로젝트를 clone 하여 local PC에 저장한다.
	* 자신의 PC에서 git remote add 명령으로 원본 프로젝트를 추가한다.
		```
		# upstream 이라는 이름으로 새로운 remote 저장소를 연결한다.
		git remote add upstream https://github.com/Robinkim0182/knu-was.git
		# remote 저장소를 조회한다.
		git remote -v
		```
	* 주기적으로 git pull upstream master 명령을 사용해서, upstream 과 싱크를 맞춰준다.
	* conflict 가 발생하는 경우, 직접 해결하도록 한다.
	* upstream 으로 PR은 누구나 날릴 수 있다. 각 조 조장에게 merge 권한을 부여하였으니, 조장에게 직접 merge 를 부탁한다.
	* <중요> PR을 날릴땐 항상 prefix 로 [X조] 를 붙여준다.
