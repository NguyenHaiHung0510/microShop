<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="${empty taiKhoan.maTaiKhoan ? 'Thêm Tài Khoản Game' : 'Sửa Tài Khoản Game'}"/>
</jsp:include>

<style>
    .specific-fields {
        display: none;
        border: 1px dashed #ccc;
        padding: 15px;
        margin-top: 15px;
        border-radius: 8px;
    }
</style>

<div class="admin-container">
    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard">Bảng Điều Khiển</a>
        <a href="${pageContext.request.contextPath}/admin/products/game" class="active">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products/steam">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders">Quản lý Đơn Hàng</a>
    </nav>
</div>

<div class="admin-form-container">
    <form action="${pageContext.request.contextPath}/admin/account/save" method="POST" enctype="multipart/form-data">
        <h2>
            <c:if test="${empty taiKhoan.maTaiKhoan}">Thêm Tài Khoản Game Mới</c:if>
            <c:if test="${not empty taiKhoan.maTaiKhoan}">Cập nhật Tài Khoản #${taiKhoan.maTaiKhoan}</c:if>
            </h2>

        <c:if test="${not empty taiKhoan.maTaiKhoan}">
            <input type="hidden" name="maTaiKhoan" value="${taiKhoan.maTaiKhoan}" />
        </c:if>

        <c:if test="${not empty errorMessage}">
            <div class="alert-danger" style="margin-bottom: 20px;">${errorMessage}</div>
        </c:if>

        <fieldset class="form-group full-width" style="border: 1px solid #ddd; padding: 15px; border-radius: 8px;">
            <legend style="font-weight: 500;">Thông Tin Chung</legend>
            <div class="form-grid">

                <div class="form-group">
                    <label for="maDanhMuc">Danh Mục (*)</label>
                    <select id="maDanhMuc" name="maDanhMuc" class="form-control" required>
                        <option value="">-- Chọn Danh Mục --</option>
                        <c:forEach items="${danhMucMap}" var="entry">
                            <option value="${entry.key}" ${taiKhoan.maDanhMuc == entry.key ? 'selected' : ''}>
                                ${entry.value}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="trangThai">Trạng Thái (*)</label>
                    <select id="trangThai" name="trangThai" class="form-control" required>
                        <option value="DANG_BAN" ${taiKhoan.trangThai == 'DANG_BAN' ? 'selected' : ''}>Đang Bán</option>
                        <option value="DA_BAN" ${taiKhoan.trangThai == 'DA_BAN' ? 'selected' : ''}>Đã Bán</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="giaGoc">Giá Gốc (VNĐ)</label>
                    <input type="number" id="giaGoc" name="giaGoc" value="${taiKhoan.giaGoc}">
                </div>

                <div class="form-group">
                    <label for="giaBan">Giá Bán (VNĐ) (*)</label>
                    <input type="number" id="giaBan" name="giaBan" value="${taiKhoan.giaBan}" required>
                </div>

                <div class="form-group full-width">
                    <label for="diemNoiBat">Điểm Nổi Bật (Mô tả ngắn)</label>
                    <textarea id="diemNoiBat" name="diemNoiBat">${taiKhoan.diemNoiBat}</textarea>
                </div>

                <div class="form-group">
                    <label for="fileAnhChinh">Ảnh Chính (Hiển thị ở trang chủ)</label>
                    <input type="file" id="fileAnhChinh" name="fileAnhChinh" accept="image/png, image/jpeg">
                    <input type="hidden" name="anhHienTai" value="${taiKhoan.duongDanAnh}">
                    <c:if test="${not empty taiKhoan.duongDanAnh}">
                        <span class="note" style="margin-top: 5px;">
                            Ảnh hiện tại: 
                            <img src="${pageContext.request.contextPath}/${taiKhoan.duongDanAnh}" width="100" style="display: block; margin-top: 5px; border-radius: 4px;">
                        </span>
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="fileAnhPhu">Ảnh Chi Tiết (Upload nhiều ảnh)</label>
                    <input type="file" id="fileAnhPhu" name="fileAnhPhu" accept="image/png, image/jpeg" multiple>
                </div>

            </div>
        </fieldset>

        <%-- LIÊN QUÂN (MaDanhMuc = 2) --%>
        <fieldset id="lienquan-fields" class="specific-fields form-group full-width">
            <legend style="font-weight: 500;">Thông Tin Liên Quân</legend>
            <div class="form-grid">
                <div class="form-group">
                    <label for="lq_tenDangNhap">Tên Đăng Nhập (*)</label>
                    <input type="text" id="lq_tenDangNhap" name="lq_tenDangNhap" value="${taiKhoan.maDanhMuc == 2 ? taiKhoan.tenDangNhap : ''}">
                </div>
                <div class="form-group">
                    <label for="lq_matKhau">Mật Khẩu (*)</label>
                    <input type="text" id="lq_matKhau" name="lq_matKhau" value="${taiKhoan.maDanhMuc == 2 ? taiKhoan.matKhau : ''}">
                </div>
                <div class="form-group">
                    <label for="lq_hangRank">Rank</label>
                    <input type="text" id="lq_hangRank" name="lq_hangRank" value="${taiKhoan.maDanhMuc == 2 ? taiKhoan.hangRank : ''}">
                </div>
                <div class="form-group">
                    <label for="lq_soTuong">Số Tướng</label>
                    <input type="number" id="lq_soTuong" name="lq_soTuong" value="${taiKhoan.maDanhMuc == 2 ? taiKhoan.soTuong : ''}">
                </div>
                <div class="form-group">
                    <label for="lq_soTrangPhuc">Số Trang Phục</label>
                    <input type="number" id="lq_soTrangPhuc" name="lq_soTrangPhuc" value="${taiKhoan.maDanhMuc == 2 ? taiKhoan.soTrangPhuc : ''}">
                </div>
                <div class="form-group">
                    <label for="lq_loaiDangKy">Loại Đăng Ký (Garena, FB...)</label>
                    <input type="text" id="lq_loaiDangKy" name="lq_loaiDangKy" value="${taiKhoan.maDanhMuc == 2 ? taiKhoan.loaiDangKy : ''}">
                </div>
            </div>
        </fieldset>

        <%-- FREE FIRE (MaDanhMuc = 1) --%>
        <fieldset id="freefire-fields" class="specific-fields form-group full-width">
            <legend style="font-weight: 500;">Thông Tin Free Fire</legend>
            <div class="form-grid">
                <div class="form-group">
                    <label for="ff_tenDangNhap">Tên Đăng Nhập (*)</label>
                    <input type="text" id="ff_tenDangNhap" name="ff_tenDangNhap" value="${taiKhoan.maDanhMuc == 1 ? taiKhoan.tenDangNhap : ''}">
                </div>
                <div class="form-group">
                    <label for="ff_matKhau">Mật Khẩu (*)</label>
                    <input type="text" id="ff_matKhau" name="ff_matKhau" value="${taiKhoan.maDanhMuc == 1 ? taiKhoan.matKhau : ''}">
                </div>
                <div class="form-group">
                    <label for="ff_hangRank">Rank</label>
                    <input type="text" id="ff_hangRank" name="ff_hangRank" value="${taiKhoan.maDanhMuc == 1 ? taiKhoan.hangRank : ''}">
                </div>
                <div class="form-group">
                    <label for="ff_soSkinSung">Số Skin Súng</label>
                    <input type="number" id="ff_soSkinSung" name="ff_soSkinSung" value="${taiKhoan.maDanhMuc == 1 ? taiKhoan.soSkinSung : ''}">
                </div>
                <div class="form-group">
                    <label for="ff_loaiDangKy">Loại Đăng Ký (VK, FB...)</label>
                    <input type="text" id="ff_loaiDangKy" name="ff_loaiDangKy" value="${taiKhoan.maDanhMuc == 1 ? taiKhoan.loaiDangKy : ''}">
                </div>
                <div class="form-group">
                    <label for="ff_coTheVoCuc">Có Thẻ Vô Cực?</label>
                    <select id="ff_coTheVoCuc" name="ff_coTheVoCuc" class="form-control">
                        <option value="false" ${taiKhoan.maDanhMuc == 1 && taiKhoan.coTheVoCuc == false ? 'selected' : ''}>Không</option>
                        <option value="true" ${taiKhoan.maDanhMuc == 1 && taiKhoan.coTheVoCuc == true ? 'selected' : ''}>Có</option>
                    </select>
                </div>
            </div>
        </fieldset>

        <%-- RIOT (MaDanhMuc = 3) --%>
        <fieldset id="riot-fields" class="specific-fields form-group full-width">
            <legend style="font-weight: 500;">Thông Tin Riot (LMHT & TFT)</legend>
            <div class="form-grid">
                <div class="form-group">
                    <label for="riot_tenDangNhap">Tên Đăng Nhập (*)</label>
                    <input type="text" id="riot_tenDangNhap" name="riot_tenDangNhap" value="${taiKhoan.maDanhMuc == 3 ? taiKhoan.tenDangNhap : ''}">
                </div>
                <div class="form-group">
                    <label for="riot_matKhau">Mật Khẩu (*)</label>
                    <input type="text" id="riot_matKhau" name="riot_matKhau" value="${taiKhoan.maDanhMuc == 3 ? taiKhoan.matKhau : ''}">
                </div>
                <div class="form-group">
                    <label for="riot_capDoRiot">Cấp Độ Riot</label>
                    <input type="number" id="riot_capDoRiot" name="riot_capDoRiot" value="${taiKhoan.maDanhMuc == 3 ? taiKhoan.capDoRiot : ''}">
                </div>
                <div class="form-group">
                    <label for="riot_soTuongLMHT">Số Tướng LMHT</label>
                    <input type="number" id="riot_soTuongLMHT" name="riot_soTuongLMHT" value="${taiKhoan.maDanhMuc == 3 ? taiKhoan.soTuongLMHT : ''}">
                </div>
                <div class="form-group">
                    <label for="riot_soTrangPhucLMHT">Số Trang Phục LMHT</label>
                    <input type="number" id="riot_soTrangPhucLMHT" name="riot_soTrangPhucLMHT" value="${taiKhoan.maDanhMuc == 3 ? taiKhoan.soTrangPhucLMHT : ''}">
                </div>
                <div class="form-group">
                    <label for="riot_hangRankLMHT">Rank LMHT</label>
                    <input type="text" id="riot_hangRankLMHT" name="riot_hangRankLMHT" value="${taiKhoan.maDanhMuc == 3 ? taiKhoan.hangRankLMHT : ''}">
                </div>
                <div class="form-group">
                    <label for="riot_soThuCungTFT">Số Thú Cưng TFT</label>
                    <input type="number" id="riot_soThuCungTFT" name="riot_soThuCungTFT" value="${taiKhoan.maDanhMuc == 3 ? taiKhoan.soThuCungTFT : ''}">
                </div>
                <div class="form-group">
                    <label for="riot_soSanDauTFT">Số Sàn Đấu TFT</label>
                    <input type="number" id="riot_soSanDauTFT" name="riot_soSanDauTFT" value="${taiKhoan.maDanhMuc == 3 ? taiKhoan.soSanDauTFT : ''}">
                </div>
            </div>
        </fieldset>

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/admin/products/game" class="nav-button">Hủy</a>
            <button type="submit" class="nav-button accent" style="color: white; border: none; cursor: pointer;">Lưu Tài Khoản</button>
        </div>
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const danhMucSelect = document.getElementById('maDanhMuc');
        const lienQuanFields = document.getElementById('lienquan-fields');
        const freeFireFields = document.getElementById('freefire-fields');
        const riotFields = document.getElementById('riot-fields');

        function toggleFields() {
            const selectedValue = danhMucSelect.value;

            lienQuanFields.style.display = 'none';
            freeFireFields.style.display = 'none';
            riotFields.style.display = 'none';

            setRequired(lienQuanFields, false);
            setRequired(freeFireFields, false);
            setRequired(riotFields, false);

            if (selectedValue === '1') { // Free Fire
                freeFireFields.style.display = 'block';
                setRequired(freeFireFields, true);
            } else if (selectedValue === '2') { // Liên Quân
                lienQuanFields.style.display = 'block';
                setRequired(lienQuanFields, true);
            } else if (selectedValue === '3') { // Riot
                riotFields.style.display = 'block';
                setRequired(riotFields, true);
            }
        }

        function setRequired(fieldset, isRequired) {
            const inputs = fieldset.querySelectorAll('input[name$="_tenDangNhap"], input[name$="_matKhau"]');
            inputs.forEach(input => {
                input.required = isRequired;
            });
        }

        toggleFields();
        danhMucSelect.addEventListener('change', toggleFields);
    });
</script>

<jsp:include page="/common/footer.jsp" />