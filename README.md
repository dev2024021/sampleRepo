# 원문보기 Spring Boot 샘플

Java 컨트롤러와 정적 HTML/JavaScript로 구성한 특허 원문보기 예제입니다.

## 실행

JDK 17 이상이 필요합니다.

```powershell
.\gradlew.bat bootRun
```

실행 후 <http://localhost:8080>에서 확인할 수 있습니다.

## API

`GET /api/patents/original?patentId=TEST-001`은 검증된 특허 번호에 대응하는
동일 출처 샘플 원문 URL을 JSON으로 반환합니다.
