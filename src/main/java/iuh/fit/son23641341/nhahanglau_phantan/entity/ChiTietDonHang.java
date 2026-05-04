package iuh.fit.son23641341.nhahanglau_phantan.entity;

public class ChiTietDonHang {
    private MonAn monAn;
    private int soLuong;

    public ChiTietDonHang(MonAn monAn, int soLuong) {
        this.monAn = monAn;
        this.soLuong = soLuong;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // Phương thức tiện ích
    public void increment() {
        this.soLuong++;
    }

    public void decrement() {
        this.soLuong--;
    }
}

