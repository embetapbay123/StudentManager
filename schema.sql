-- ============================================
-- StudentManager Database Schema
-- ============================================
-- Mô tả: Script SQL để tạo database và table cho ứng dụng StudentManager
-- Phiên bản: 1.0
-- ============================================

-- Tạo database nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS student_management
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Sử dụng database
USE student_management;

-- Xóa table cũ nếu tồn tại (cẩn thận với lệnh này trong production!)
DROP TABLE IF EXISTS students;

-- Tạo table students
CREATE TABLE students (
    id VARCHAR(50) PRIMARY KEY COMMENT 'Mã sinh viên (unique)',
    name VARCHAR(255) NOT NULL COMMENT 'Họ và tên sinh viên',
    mark DOUBLE NOT NULL COMMENT 'Điểm số (0-10)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Thời gian tạo',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Thời gian cập nhật',
    
    -- Constraints
    CONSTRAINT chk_mark CHECK (mark >= 0 AND mark <= 10),
    
    -- Indexes
    INDEX idx_name (name),
    INDEX idx_mark (mark)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Bảng lưu trữ thông tin sinh viên';

-- ============================================
-- Sample Data (Optional - Dữ liệu mẫu)
-- ============================================
-- Uncomment các dòng dưới đây nếu muốn thêm dữ liệu mẫu

-- INSERT INTO students (id, name, mark) VALUES
-- ('SV001', 'Nguyễn Văn A', 8.5),
-- ('SV002', 'Trần Thị B', 7.2),
-- ('SV003', 'Lê Văn C', 9.0),
-- ('SV004', 'Phạm Thị D', 6.8),
-- ('SV005', 'Hoàng Văn E', 8.9);

-- ============================================
-- Verification Queries (Kiểm tra)
-- ============================================
-- Kiểm tra table đã được tạo
-- SHOW TABLES;

-- Xem cấu trúc table
-- DESCRIBE students;

-- Đếm số lượng sinh viên
-- SELECT COUNT(*) as total_students FROM students;

-- Xem tất cả sinh viên
-- SELECT * FROM students ORDER BY mark DESC;
