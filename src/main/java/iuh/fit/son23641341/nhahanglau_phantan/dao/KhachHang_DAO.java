package iuh.fit.son23641341.nhahanglau_phantan.dao;

import iuh.fit.son23641341.nhahanglau_phantan.entity.KhachHang;
import iuh.fit.son23641341.nhahanglau_phantan.entity.PhieuDatBan;
import iuh.fit.son23641341.nhahanglau_phantan.mock.MockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

// NOTE: Database logic removed; DAO now uses in-memory mock data.
public class KhachHang_DAO {

    public String getMaKhachHangBySDT(String sdt) {
        return MockData.khachHangs().stream()
            .filter(item -> item.getSoDienThoai().equals(sdt))
            .map(KhachHang::getMaKhachHang)
            .findFirst()
            .orElse(null);
    }

    public ArrayList<KhachHang> getAllKhachHang() {
        return new ArrayList<>(MockData.khachHangs());
    }

    public String taoMaKhachHangMoi() {
        int max = MockData.khachHangs().stream()
            .map(KhachHang::getMaKhachHang)
            .filter(ma -> ma.startsWith("KH"))
            .map(ma -> ma.substring(2))
            .mapToInt(value -> {
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException ignored) {
                    return 0;
                }
            })
            .max()
            .orElse(0);
        return String.format("KH%03d", max + 1);
    }

    public ArrayList<KhachHang> timKhachHangTheoSDT(String sdt) {
        ArrayList<KhachHang> list = new ArrayList<>();
        for (KhachHang kh : MockData.khachHangs()) {
            if (kh.getSoDienThoai().contains(sdt)) {
                list.add(kh);
            }
        }
        return list;
    }

    public boolean existsBySoDienThoai(String sdt) {
        return MockData.khachHangs().stream()
            .anyMatch(item -> item.getSoDienThoai().equals(sdt));
    }

    public boolean existsBySoDienThoaiExcept(String sdt, String maKhachHang) {
        return MockData.khachHangs().stream()
            .anyMatch(item -> item.getSoDienThoai().equals(sdt) && !item.getMaKhachHang().equals(maKhachHang));
    }

    public boolean themKhachHang(KhachHang kh) {
        if (kh == null || existsBySoDienThoai(kh.getSoDienThoai())) {
            return false;
        }
        if (kh.getMaKhachHang() == null || kh.getMaKhachHang().isEmpty()) {
            try {
                kh.setMaKhachHang(taoMaKhachHangMoi());
                kh.setNgayDangKy(LocalDate.now());
            } catch (Exception ignored) {
            }
        }
        return MockData.khachHangs().add(kh);
    }

    public boolean capNhatKhachHang(KhachHang kh) {
        if (kh == null) {
            return false;
        }
        KhachHang existing = MockData.khachHangs().stream()
            .filter(item -> item.getMaKhachHang().equals(kh.getMaKhachHang()))
            .findFirst()
            .orElse(null);
        if (existing == null) {
            return false;
        }
        try {
            existing.setHoTen(kh.getHoTen());
            existing.setSoDienThoai(kh.getSoDienThoai());
            existing.setEmail(kh.getEmail());
            existing.setThanhVien(kh.getThanhVien());
            existing.setDiemTichLuy(kh.getDiemTichLuy());
            existing.setGioiTinh(kh.getGioiTinh());
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public boolean xoaKhachHang(String maKhachHang) {
        return MockData.khachHangs().removeIf(item -> item.getMaKhachHang().equals(maKhachHang));
    }

    public boolean congDiemTichLuy(String maKhachHang, int diemCong) {
        KhachHang existing = MockData.khachHangs().stream()
            .filter(item -> item.getMaKhachHang().equals(maKhachHang))
            .findFirst()
            .orElse(null);
        if (existing == null) {
            return false;
        }
        existing.setDiemTichLuy(existing.getDiemTichLuy() + diemCong);
        return true;
    }

    public String getHang(PhieuDatBan pdb) {
        if (pdb == null || pdb.getMaKhachHang() == null) {
            return null;
        }
        return MockData.khachHangs().stream()
            .filter(item -> item.getMaKhachHang().equals(pdb.getMaKhachHang()))
            .map(KhachHang::getThanhVien)
            .findFirst()
            .orElse(null);
    }

    public String getmaKhachHangbySDT(String sdt) {
        return getMaKhachHangBySDT(sdt);
    }
}

