package iuh.fit.son23641341.nhahanglau_phantan.control;

import iuh.fit.son23641341.nhahanglau_phantan.entity.Nhanvien;
import iuh.fit.son23641341.nhahanglau_phantan.entity.User; // Import lớp User

// NOTE: Controller now uses mock data; database logic removed.
public class thongTinNhanVien_ctrl {

    // 1. Tạo 2 biến để giữ thông tin
    private Nhanvien nhanVienHienTai;
    private User userHienTai;

    public thongTinNhanVien_ctrl() {

    }
// Tạo các hàm "getter" để lớp khác có thể lấy dữ liệu
    
    public Nhanvien getNhanVienHienTai() {
        return nhanVienHienTai;
    }

    public User getUserHienTai() {
        return userHienTai;
    }
    
    
}

