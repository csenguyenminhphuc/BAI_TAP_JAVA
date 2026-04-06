-- =============================================
-- TẠO DATABASE VÀ BẢNG NHÂN VIÊN (Phòng tổ chức)
-- =============================================
-- Nguyễn Minh Phúc 

-- 1. Tạo Database
CREATE DATABASE PhongToChuc;
GO

USE PhongToChuc;
GO

-- 2. Tạo bảng NhanVien
CREATE TABLE NhanVien (
    MaSo        NVARCHAR(10)   PRIMARY KEY,           -- Mã số (NV111, ...)
    Ho          NVARCHAR(50)   NOT NULL,              -- Họ
    TenNhanVien NVARCHAR(50)   NOT NULL,              -- Tên nhân viên
    Phai        NVARCHAR(3)    NOT NULL 
                CHECK (Phai IN (N'Nam', N'Nữ')),     -- Phái
    NgaySinh    DATE           NOT NULL,              -- Ngày sinh
    TienLuong   DECIMAL(18, 2) NOT NULL              -- Tiền lương
);
GO

-- 3. Chèn 3 bản ghi mẫu từ ảnh
INSERT INTO NhanVien (MaSo, Ho, TenNhanVien, Phai, NgaySinh, TienLuong)
VALUES 
    (N'NV111', N'Nguyễn', N'An',     N'Nam', '1997-03-01', 3000.00),
    (N'NV222', N'Trần',   N'Bình',   N'Nam', '1994-03-21', 4000.00),
    (N'NV333', N'Hoàng',  N'Hoa',    N'Nam', '1995-01-21', 3500.00);
GO

-- 4. Xem kết quả (định dạng giống form)
SELECT 
    MaSo,
    Ho,
    TenNhanVien,
    Phai,
    FORMAT(NgaySinh, 'dd/MM/yyyy') AS NgaySinh,
    FORMAT(TienLuong, '#,##0') + ' $' AS TienLuong
FROM NhanVien;