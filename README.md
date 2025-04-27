# Coindesk Spring Boot Demo

## 專案概述
此專案使用 Java 17 與 Spring Boot 3，提供以下功能：
1. 呼叫 CoinDesk Bitcoin Price Index API，轉換並輸出更新時間及幣別匯率
2. 使用 H2 與 Spring Data JPA 建立幣別與中文名稱 CRUD API
3. 排程每小時同步匯率
4. AOP 日誌攔截 API 請求與回應
5. Swagger UI (OpenAPI) 文件
6. 多國語系 (英文/中文)
7. AES 加密/解密示範
8. Docker 容器化

## 本地執行
```bash
mvn clean package
java -jar target/coindesk-demo-0.0.1-SNAPSHOT.jar
```

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
