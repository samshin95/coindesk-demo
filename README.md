# Coindesk Spring Boot Demo

## 🔍 專案概述

- **語言/框架**：Java 17、Spring Boot 3.
- **主要功能清單**：
  1. **CoinDesk 匯率查詢**
     - 位置：`CoindeskService#getCurrentPrice()` → `CoindeskController#getTransformedBpi()`
     - 說明：調用外部 API，解析 JSON (`time.updatedISO` + `bpi`)、轉時區並結合本地幣別中文名稱後回傳。
  2. **幣別 CRUD（H2 + Spring Data JPA）**
     - 位置：`Currency.java`、`CurrencyRepository.java`、`CurrencyController.java`
     - 說明：`Entity` 映射、`JpaRepository` 自動實現 CRUD，提供 RESTful 接口。
  3. **排程每小時同步**
     - 位置：`Application.java` (`@EnableScheduling`)、`RateSyncService#syncBpiRates()` (`@Scheduled(cron="0 0 * * * *")`)
     - 說明：定時觸發匯率同步，更新系統快取/資料庫。
  4. **AOP 日誌攔截**
     - 位置：`LoggingAspect.java` (`@Aspect` + `@Around("execution(* com.example..controller..*(..))")`)
     - 說明：環繞通知自動記錄 API 請求參數、響應結果與耗時，實現關注點分離。
  5. **自動化測試**
     - 位置：`src/test/java/...`
     - 說明：
       - **單元測試**：JUnit 5 + Mockito 模擬外部依賴，測工具／服務邏輯。
       - **整合測試**：`@SpringBootTest` + MockMvc / TestRestTemplate 驗證 REST API。
-
- **試做功能清單**：  
  6. **Swagger UI (OpenAPI)**
  - 位置：引入 `springdoc-openapi-ui`，(選填) `OpenApiConfig.java`
  - 說明：自動生成 `/v3/api-docs` JSON 和 `/swagger-ui.html` 互動文件，支援 `@Operation`、`@Schema` 增補。
  7. **多國語系 (中/英)**
     - 位置：`src/main/resources/messages.properties`、`messages_zh.properties`
     - 說明：Spring Boot `MessageSource` + `AcceptHeaderLocaleResolver`，根據 `Accept-Language` 載入對應語系訊息。
  8. **AES 加解密示範**
     - 位置：`AesEncryptionUtil.java`（`encrypt()`, `decrypt()`）、`/api/encrypt`, `/api/decrypt`
     - 說明：`Cipher.getInstance("AES/CBC/PKCS5Padding")` + 隨機 IV + Base64，展示對稱加密流程。
  9. **Docker 容器化**
     - 位置：根目錄 `Dockerfile`
     - 說明：`FROM openjdk:17-jdk-slim` → `COPY target/*.jar` → `ENTRYPOINT ["java","-jar","..."]` → `EXPOSE 8080`。

---

## 🚀 本地執行

````bash
# 1. 建置專案
mvn clean package

# 2. 啟動應用
java -jar target/coindesk-demo-0.0.1-SNAPSHOT.jar


## 本地執行
```bash
mvn clean package
java -jar target/coindesk-demo-0.0.1-SNAPSHOT.jar
````

開啟 H2 Console: http://localhost:8080/h2-console  
JDBC URL: `jdbc:h2:mem:coinsdb`

Swagger UI: http://localhost:8080/swagger-ui/index.html

## Docker 執行

```bash
docker build -t coindesk-demo .
docker run -p 8080:8080 coindesk-demo
```

## API 列表

- **GET** `/currencies`
- **GET** `/currencies/{code}`
- **POST** `/currencies`
- **PUT** `/currencies/{code}`
- **DELETE** `/currencies/{code}`
- **GET** `/coindesk/transformed`

## 測試

```bash
mvn test
```
