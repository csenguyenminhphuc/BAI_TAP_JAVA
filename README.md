# Bài Tập Về Nhà - Ứng Dụng Java JDBC

Dự án Java với các ứng dụng GUI sử dụng JDBC để kết nối cơ sở dữ liệu.

## 📋 Nội dung Dự Án

Dự án này chứa hai ứng dụng chính:

### 1. **BaiTapJDatePicker**
- Ứng dụng sử dụng JDatePicker component
- Các class:
  - `ConnectDB.java`: Kết nối cơ sở dữ liệu
  - `JDatePickerEntity.java`: Lớp entity cho dữ liệu
  - `JDatePickerDao.java`: Data Access Object
  - `JDatePickerMethod.java`: Các phương thức xử lý
  - `JDatePickerGUI.java`: Giao diện người dùng
  - `MainGUI.java`: Lớp chọn ứng dụng

### 2. **BaiTapJTabbedPane**
- Ứng dụng sử dụng JTabbedPane (giao diện tab)
- Quản lý nhân viên với JDBC
- Các class:
  - `ConnectDB.java`: Kết nối cơ sở dữ liệu
  - `NhanVienEntity.java`: Lớp entity nhân viên
  - `NhanVienDao.java`: Data Access Object
  - `NhanVienMethod.java`: Các phương thức xử lý
  - `JTabbedPaneGUI.java`: Giao diện tab chính
  - `FileLoadAndSave.java`: Xử lý lưu/tải tập tin
  - `MainGUI.java`: Lớp chọn ứng dụng

## 🗄️ Cơ Sở Dữ Liệu

Dự án sử dụng các tập tin SQL để khởi tạo cơ sở dữ liệu:

- `QuanLyNhanVien.sql` - Schema quản lý nhân viên
- `QuanLyNhanVien JDBC.sql` - Schema JDBC
- `PhongToChuc.sql` - Schema phòng tổ chức

Vị trí: `/sql/`

## 📁 Cấu Trúc Thư Mục

```
.
├── README                     # Tài liệu hướng dẫn
├── bin/                       # Thư mục chứa các file class được biên dịch
│   ├── BaiTapJDatePicker/
│   └── BaiTapJTabbedPane/
├── src/                       # Mã nguồn Java
│   ├── BaiTapJDatePicker/
│   └── BaiTapJTabbedPane/
├── sql/                       # Tập tin SQL để tạo cơ sở dữ liệu
├── lib/                       # Thư viện bên ngoài
├── data/                      # Thư mục dữ liệu
└── .gitignore                 # Tệp Git ignore
```

## 🚀 Cách Sử Dụng

### 1. Chuẩn Bị Cơ Sở Dữ Liệu
- Chạy các tập tin SQL từ thư mục `/sql/` vào hệ quản trị cơ sở dữ liệu của bạn
- Cập nhật thông tin kết nối trong class `ConnectDB.java`

### 2. Biên Dịch
```bash
javac -d bin src/BaiTapJDatePicker/*.java
javac -d bin src/BaiTapJTabbedPane/*.java
```

### 3. Chạy Ứng Dụng
```bash
# Chạy MainGUI để chọn ứng dụng
java -cp bin BaiTapJDatePicker.MainGUI
java -cp bin BaiTapJTabbedPane.MainGUI
```

## 🔧 Yêu Cầu

- Java JDK 8 hoặc cao hơn
- Cơ sở dữ liệu (MySQL, SQL Server, Oracle, v.v.)
- JDBC Driver tương ứng (nằm trong `/lib/`)

## 📝 Ghi Chú

- Các kết nối cơ sở dữ liệu nên được cấu hình trong class `ConnectDB.java`
- Dự án sử dụng mô hình DAO (Data Access Object) để tách biệt logic xử lý dữ liệu
- Giao diện người dùng được xây dựng bằng Swing

## 📄 Tài Liệu Thêm

Xem tập tin `README` trong thư mục gốc để biết thêm chi tiết.

---

**Tác giả**: Nguyễn Minh Phúc - 22637001
