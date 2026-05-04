package iuh.fit.son23641341.nhahanglau_phantan.entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class KhuyenMai {
    private String maKhuyenMai;
    private String tenKhuyenMai;
    private double phanTramGiam;
    private String ngayBatDau; 
    private String ngayKetThuc;
    private String moTa;

        public KhuyenMai(KhuyenMai km) {
        this(km.maKhuyenMai, km.tenKhuyenMai, km.phanTramGiam, km.ngayBatDau, km.ngayKetThuc, km.moTa);
    }


    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, double phanTramGiam,
                     String ngayBatDau, String ngayKetThuc, String moTa) {
        setMaKhuyenMai(maKhuyenMai);
        setTenKhuyenMai(tenKhuyenMai);
        setPhanTramGiam(phanTramGiam);
        setNgayBatDau(ngayBatDau);
        setNgayKetThuc(ngayKetThuc);
        setMoTa(moTa);
    }


    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        if (maKhuyenMai == null || maKhuyenMai.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã khuyến mãi không được rỗng.");
        }
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        if (tenKhuyenMai == null || tenKhuyenMai.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên khuyến mãi không được rỗng.");
        }
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(double phanTramGiam) {
        if (phanTramGiam < 0 || phanTramGiam > 100) {
            throw new IllegalArgumentException("Phần trăm giảm phải nằm trong khoảng 0 đến 100.");
        }
        this.phanTramGiam = phanTramGiam;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        if (ngayBatDau == null || !isValidDateFormat(ngayBatDau)) {
            throw new IllegalArgumentException("Ngày bắt đầu không hợp lệ. Định dạng đúng là dd/MM/yyyy (ví dụ: 15/10/2025).");
        }
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        if (ngayKetThuc == null || !isValidDateFormat(ngayKetThuc)) {
            throw new IllegalArgumentException("Ngày kết thúc không hợp lệ. Định dạng đúng là dd/MM/yyyy (ví dụ: 20/10/2025).");
        }

        // Kiểm tra ngày kết thúc >= ngày bắt đầu
        if (this.ngayBatDau != null && isValidDateFormat(this.ngayBatDau)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate start = LocalDate.parse(this.ngayBatDau, formatter);
                LocalDate end = LocalDate.parse(ngayKetThuc, formatter);
                if (end.isBefore(start)) {
                    throw new IllegalArgumentException("Ngày kết thúc phải sau hoặc bằng ngày bắt đầu.");
                }
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Lỗi khi xử lý định dạng ngày.");
            }
        }

        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // ------------------- Hàm hỗ trợ kiểm tra định dạng -------------------
    private static boolean isValidDateFormat(String dateStr) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}$";
        return Pattern.matches(regex, dateStr);
    }

    // ------------------- Phương thức nghiệp vụ -------------------
    @Override
    public String toString() {
        return String.format("KhuyenMai[ma=%s, ten=%s, giam=%.1f%%, batDau=%s, ketThuc=%s, moTa=%s]",
                maKhuyenMai, tenKhuyenMai, phanTramGiam, ngayBatDau, ngayKetThuc, moTa);
    }
}
