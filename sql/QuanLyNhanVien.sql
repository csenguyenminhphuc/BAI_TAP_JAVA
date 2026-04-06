-- ==================== TẠO DATABASE ====================
-- Nguyễn Minh Phúc - 22637001
USE master;
GO

-- Buộc đóng tất cả kết nối đang dùng database
ALTER DATABASE QuanLyNhanVien 
SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO

-- Xóa database
DROP DATABASE QuanLyNhanVien;
GO

PRINT N'✅ Database QuanLyNhanVien đã bị xóa thành công!';

CREATE DATABASE QuanLyNhanVien;
GO

USE QuanLyNhanVien;
GO

-- ==================== BẢNG NHÂN VIÊN (CHỈ 1 BẢNG) ====================
CREATE TABLE NhanVien (
    MaNhanVien     NVARCHAR(20)  PRIMARY KEY,           -- Ví dụ: NV001, NV002...
    Ho             NVARCHAR(50)  NOT NULL,
    TenNhanVien    NVARCHAR(50)  NOT NULL,
    Tuoi           INT           NOT NULL CHECK (Tuoi BETWEEN 18 AND 70),
    Phai           NVARCHAR(5)   NOT NULL DEFAULT N'Nam' 
                   CHECK (Phai IN (N'Nam', N'Nữ')),
    
    PhongBan       NVARCHAR(100) NOT NULL 
                   CHECK (PhongBan IN (N'Phòng tổ chức', 
                                       N'Phòng kỹ thuật', 
                                       N'Phòng nhân sự', 
                                       N'Phòng tài vụ')),
    
    TienLuong      DECIMAL(18,2) NOT NULL CHECK (TienLuong >= 0)
);
GO

-- ==================== DỮ LIỆU MẪU ====================
INSERT INTO NhanVien (MaNhanVien, Ho, TenNhanVien, Tuoi, Phai, PhongBan, TienLuong)
VALUES 
    (N'NV001', N'Nguyễn',  N'Văn An',    28, N'Nam',  N'Phòng tổ chức',   15000000),
    (N'NV002', N'Trần',    N'Thị Bình',  25, N'Nữ',   N'Phòng kỹ thuật',  13500000),
    (N'NV003', N'Lê',      N'Minh Tuấn', 32, N'Nam',  N'Phòng tài vụ',    18000000);
GO

PRINT N'✅ ĐÃ TẠO THÀNH CÔNG! Chỉ có đúng 1 bảng NhanVien.';