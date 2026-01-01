# StudentManager - Hệ Thống Quản Lý Sinh Viên

Ứng dụng quản lý sinh viên đơn giản sử dụng Java, JDBC và MySQL.

## 📋 Mô Tả

StudentManager là một ứng dụng console cho phép quản lý thông tin sinh viên với các chức năng:
- ➕ Thêm sinh viên mới
- 📋 Xem danh sách sinh viên
- ✏️ Cập nhật thông tin sinh viên
- 🗑️ Xóa sinh viên

## 🏗️ Kiến Trúc

Dự án áp dụng **Repository Pattern** với cấu trúc:

```
src/
├── Main.java                          # Entry point
├── TestConnection.java                # Test database connection
├── models/
│   └── Student.java                   # Student entity
├── repositories/
│   ├── IStudentRepository.java        # Repository interface
│   └── StudentRepositoryImpl.java     # Repository implementation
└── services/
    ├── MysqlConnection.java           # Database connection
    └── StudentManager.java            # Business logic
```

## 🔧 Công Nghệ Sử Dụng

- **Java**: 17
- **Build Tool**: Maven
- **Database**: MySQL 8.x
- **JDBC Driver**: MySQL Connector/J 8.3.0

## 📦 Yêu Cầu Hệ Thống

- JDK 17 hoặc cao hơn
- MySQL Server 8.x
- Maven 3.6+

## 🚀 Cài Đặt và Chạy

### 1. Cài đặt Database

Tạo database và table:

```sql
CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE IF NOT EXISTS students (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    mark DOUBLE NOT NULL CHECK (mark >= 0 AND mark <= 10)
);
```

### 2. Cấu hình Database

Mở file `src/services/MysqlConnection.java` và cập nhật thông tin kết nối:

```java
String url = "jdbc:mysql://localhost:3306/student_management";
String user = "root";
String password = "your_password";  // Thay đổi password của bạn
```

⚠️ **Lưu ý**: Không nên commit password vào Git. Xem phần [Bảo Mật](#-bảo-mật) bên dưới.

### 3. Build và Chạy

```bash
# Build project
mvn clean compile

# Chạy ứng dụng
mvn exec:java -Dexec.mainClass="Main"

# Hoặc compile và chạy trực tiếp
javac -cp ".:target/classes:~/.m2/repository/com/mysql/mysql-connector-j/8.3.0/mysql-connector-j-8.3.0.jar" src/**/*.java src/*.java
java -cp ".:target/classes:~/.m2/repository/com/mysql/mysql-connector-j/8.3.0/mysql-connector-j-8.3.0.jar" Main
```

### 4. Test Database Connection

```bash
mvn exec:java -Dexec.mainClass="TestConnection"
```

## 📖 Hướng Dẫn Sử Dụng

Khi chạy ứng dụng, bạn sẽ thấy menu:

```
===MENU QUẢN LÝ SINH VIÊN===
1. Thêm sinh viên mới
2. Xem danh sách sinh viên
3. Xóa sinh viên
4. Sửa thông tin sinh viên
0. Thoát
Lựa chọn của bạn:
```

### Thêm sinh viên
1. Chọn option 1
2. Nhập ID sinh viên (ví dụ: SV001)
3. Nhập tên sinh viên
4. Nhập điểm (0-10)

### Xem danh sách
- Chọn option 2 để xem tất cả sinh viên

### Cập nhật sinh viên
1. Chọn option 4
2. Nhập ID sinh viên cần cập nhật
3. Nhập tên mới (Enter để bỏ qua)
4. Nhập điểm mới (-1 để bỏ qua)

### Xóa sinh viên
1. Chọn option 3
2. Nhập ID sinh viên cần xóa

## 🔒 Bảo Mật

### Vấn đề hiện tại
⚠️ Database credentials đang được hardcode trong source code.

### Giải pháp đề xuất

Tạo file `config.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/student_management
db.username=root
db.password=your_password
```

Thêm vào `.gitignore`:
```
config.properties
*.properties
```

Cập nhật `MysqlConnection.java`:
```java
Properties props = new Properties();
try (InputStream input = MysqlConnection.class.getClassLoader()
        .getResourceAsStream("config.properties")) {
    props.load(input);
    String url = props.getProperty("db.url");
    String user = props.getProperty("db.username");
    String password = props.getProperty("db.password");
    return DriverManager.getConnection(url, user, password);
}
```

## 📝 Ví Dụ Sử Dụng

```
===MENU QUẢN LÝ SINH VIÊN===
1. Thêm sinh viên mới
2. Xem danh sách sinh viên
3. Xóa sinh viên
4. Sửa thông tin sinh viên
0. Thoát
Lựa chọn của bạn: 1

Nhập thông tin sinh viên
Nhập id sinh viên: SV001
Nhập tên sinh viên: Nguyễn Văn A
Nhập điểm sinh viên: 8.5
Thêm sinh viên thành công

===MENU QUẢN LÝ SINH VIÊN===
...
Lựa chọn của bạn: 2

Danh sách sinh viên:
Student{id='SV001', name='Nguyễn Văn A', mark=8.5}
```

## 🐛 Troubleshooting

### Lỗi kết nối database
```
Lỗi kết nối DATABASE: Access denied for user 'root'@'localhost'
```
**Giải pháp**: Kiểm tra lại username/password trong `MysqlConnection.java`

### Lỗi "Communications link failure"
```
com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure
```
**Giải pháp**: 
- Kiểm tra MySQL server đã chạy chưa
- Kiểm tra port 3306 có đúng không

### Lỗi "Table doesn't exist"
```
Table 'student_management.students' doesn't exist
```
**Giải pháp**: Chạy lại SQL script tạo table ở phần [Cài đặt Database](#1-cài-đặt-database)

## 📚 Tài Liệu Tham Khảo

- [Java JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [MySQL Connector/J Documentation](https://dev.mysql.com/doc/connector-j/8.0/en/)
- [Repository Pattern](https://martinfowler.com/eaaCatalog/repository.html)

## 🤝 Đóng Góp

Đây là một dự án học tập. Mọi góp ý và đề xuất đều được hoan nghênh!

## 📄 License

Dự án học tập - Tự do sử dụng

## ✨ Tác Giả

Dự án được phát triển như một bài tập học Java JDBC và Database.

---

**Lưu ý**: Đây là dự án cơ bản dùng để học. Trong production, nên sử dụng:
- Framework như Spring Boot
- ORM như Hibernate/JPA
- Connection Pool như HikariCP
- Security framework
- Logging framework (SLF4J, Log4j)
