-- -----------------------------------------------------
-- MẢNG STEAM
-- -----------------------------------------------------
-- PHẦN 6: GAME STEAM (8 GAME)
-- -----------------------------------------------------

--  Game 1
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Cyberpunk 2077',
  'Trải nghiệm thế giới tương lai đầy công nghệ, nơi con người hòa quyện với máy móc trong thành phố Night City.',
  1200000, 790000,
  320,
  '8X2kIfS6fb8',
  'uploads/steam_cyberpunk_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (1, 'Cyberpunk 2077 – Thành phố công nghệ và tội phạm',
	'<p><strong style="color:#FF4500;">Cyberpunk 2077</strong> đưa bạn đến Night City – thành phố tương lai nơi công nghệ và con người hòa quyện.</p>
	<p><span style="color:#1E90FF;">Khám phá</span> từng con phố neon rực rỡ, thực hiện nhiệm vụ mạo hiểm và tương tác với hàng loạt nhân vật đầy màu sắc.</p>
	<p><span style="color:#32CD32;">Điểm nổi bật của Cyberpunk 2077:</span></p>
	<ul>
	<li><span style="color:#FFD700;">Thế giới mở chi tiết:</span> Khám phá khu phố, tòa nhà cao tầng và các khu ổ chuột đầy rẫy cạm bẫy.</li>
	<li><span style="color:#FF6347;">Nhiệm vụ đa dạng:</span> Hoàn thành nhiệm vụ chính, phụ hoặc tự do khám phá mà không bị gò bó.</li>
	<li><span style="color:#1E90FF;">Nhân vật đa chiều:</span> Tạo hình và kỹ năng nhân vật tùy chỉnh theo lối chơi của bạn.</li>
	<li><span style="color:#32CD32;">Đồ họa và âm thanh:</span> Trải nghiệm thế giới sống động với hiệu ứng ánh sáng và âm nhạc hiện đại.</li>
	<li><span style="color:#FF69B4;">Chế độ tương tác:</span> Quan hệ với NPC, đối thoại, hoặc hợp tác với các băng nhóm trong thành phố.</li>
	</ul>
	<p><em style="color:#00CED1;">Cyberpunk 2077</em> không chỉ là một trò chơi, mà còn là một trải nghiệm văn hóa, khám phá và phiêu lưu vô cùng sống động.</p>');

INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (1, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 10 64-bit</li>
            <li>CPU: Intel Core i5-3570K / AMD FX-8310</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 970 / AMD RX 470</li>
            <li>HDD: 70 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10/11 64-bit</li>
            <li>CPU: Intel Core i7-4790 / AMD Ryzen 3 3200G</li>
            <li>RAM: 12 GB</li>
            <li>GPU: NVIDIA GTX 1060 / AMD RX 590</li>
            <li>SSD: 70 GB</li>
        </ul>
    </div>
</div>');

-- Game 2
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Elden Ring',
  'Một thế giới mở huyền bí, nơi người chơi du hành qua The Lands Between để khôi phục chiếc nhẫn Elden huyền thoại.',
  1200000, 890000,
  500,
  'E3Huy2cdih0',
  'uploads/steam_elden_ring_detail.jpg');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (2, 'Elden Ring – Kiệt tác thế giới mở huyền bí',
'<p><strong style="color:#32CD32;">Elden Ring</strong> mở ra vùng đất The Lands Between với bầu không khí huyền bí và những sinh vật khổng lồ.</p>
<p><span style="color:#FFD700;">Khám phá</span> lâu đài, rừng rậm, hang động và những bí mật cổ xưa được FromSoftware xây dựng công phu.</p>
<p><span style="color:#FF4500;">Điểm nổi bật của Elden Ring:</span></p>
<ul>
<li><span style="color:#1E90FF;">Thế giới mở rộng lớn:</span> Không giới hạn khám phá với nhiều khu vực độc đáo.</li>
<li><span style="color:#32CD32;">Chiến đấu kịch tính:</span> Hệ thống combat sâu sắc với boss hùng mạnh.</li>
<li><span style="color:#FFD700;">Hệ thống tùy chỉnh nhân vật:</span> Vũ khí, phép thuật, và kỹ năng đa dạng.</li>
<li><span style="color:#FF69B4;">Cốt truyện đa lớp:</span> Kết hợp nhiều nhân vật và tuyến nhiệm vụ phong phú.</li>
</ul>
<p><em style="color:#00CED1;">Elden Ring</em> mang đến trải nghiệm phiêu lưu, thử thách trí tuệ và kỹ năng chiến đấu vượt trội.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (2, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i5-8400 / AMD Ryzen 3 3300X</li>
            <li>RAM: 12 GB</li>
            <li>GPU: NVIDIA GTX 1060 3GB / AMD RX 580 4GB</li>
            <li>HDD: 60 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10/11</li>
            <li>CPU: Intel Core i7-8700K / AMD Ryzen 5 3600</li>
            <li>RAM: 16 GB</li>
            <li>GPU: NVIDIA RTX 2070 / AMD RX 6700 XT</li>
            <li>SSD: 60 GB</li>
        </ul>
    </div>
</div>');

-- Game 3
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Red Dead Redemption 2',
  'Theo chân Arthur Morgan trong hành trình sinh tồn giữa miền Tây hoang dã của nước Mỹ.',
  1100000, 750000,
  700,
  'eaW0tYpxyp0',
  'uploads/steam_red_dead_redemption_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (3, 'Red Dead Redemption 2 – Huyền thoại miền Tây hoang dã',
'<p><strong style="color:#8A2BE2;">Red Dead Redemption 2</strong> kể câu chuyện về Arthur Morgan và băng đảng Van der Linde, sinh tồn trong miền Tây hoang dã.</p>
<p><span style="color:#FF6347;">Trải nghiệm</span> môi trường rộng lớn, từ những thị trấn nhộn nhịp đến thảo nguyên và núi cao hiểm trở.</p>
<p><span style="color:#32CD32;">Điểm nổi bật của RDR2:</span></p>
<ul>
<li><span style="color:#FFD700;">Cốt truyện sâu sắc:</span> Danh dự, tội lỗi, và sự chuộc lỗi của các nhân vật.</li>
<li><span style="color:#FF4500;">Thế giới sống động:</span> Hệ thống NPC phản ứng theo hành vi của bạn.</li>
<li><span style="color:#1E90FF;">Hoạt động đa dạng:</span> Săn bắn, cướp ngân hàng, câu cá, hoặc phiêu lưu tự do.</li>
<li><span style="color:#32CD32;">Đồ họa tuyệt đẹp:</span> Môi trường, ánh sáng, và thời tiết thay đổi theo thời gian.</li>
</ul>
<p><em style="color:#00CED1;">RDR2</em> là kiệt tác đưa người chơi trở về miền Tây nước Mỹ chân thực và sống động.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (3, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 7 SP1</li>
            <li>CPU: Intel Core i5-2500K / AMD FX-6300</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 770 2GB / AMD R9 280 3GB</li>
            <li>HDD: 150 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i7-4770K / AMD Ryzen 5 1500X</li>
            <li>RAM: 12 GB</li>
            <li>GPU: NVIDIA GTX 1060 6GB / AMD RX 480 4GB</li>
            <li>SSD: 150 GB</li>
        </ul>
    </div>
</div>');      

-- Game 4
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Resident Evil 4 Remake',
  'Phiên bản làm lại của tựa game sinh tồn kinh dị huyền thoại với đồ họa và lối chơi được nâng cấp toàn diện.',
  900000, 690000,
  450,
  'Id2EaldBaWw',
  'uploads/steam_resident_evil_4_remake_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (4, 'Resident Evil 4 Remake – Sống sót trong kinh dị',
'<p><strong style="color:#FF1493;">Resident Evil 4 Remake</strong> đưa Leon Kennedy trở lại chống lại dịch bệnh ký sinh Las Plagas.</p>
<p><span style="color:#00CED1;">Khám phá</span> làng, lâu đài và các khu vực đáng sợ với đồ họa và âm thanh sống động.</p>
<p><span style="color:#FFD700;">Điểm nổi bật:</span></p>
<ul>
<li><span style="color:#FF6347;">Kinh dị sống động:</span> Bầu không khí căng thẳng với quái vật và bẫy.</li>
<li><span style="color:#32CD32;">Hành động phiêu lưu:</span> Chiến đấu, giải đố, và khám phá môi trường.</li>
<li><span style="color:#1E90FF;">Cốt truyện hấp dẫn:</span> Leon chống lại âm mưu sinh học đen tối.</li>
<li><span style="color:#FFA500;">Tái tạo đồ họa:</span> Phiên bản remake nâng cấp từ bản gốc với chi tiết chân thực.</li>
</ul>
<p><em style="color:#FF4500;">Resident Evil 4 Remake</em> là trải nghiệm kinh dị đỉnh cao cho mọi fan survival horror.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (4, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i5-4460 / AMD Ryzen 3 1200</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 760 / AMD R9 280X</li>
            <li>HDD: 25 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i7-6700 / AMD Ryzen 5 1600</li>
            <li>RAM: 16 GB</li>
            <li>GPU: NVIDIA GTX 1070 / AMD RX 590</li>
            <li>SSD: 25 GB</li>
        </ul>
    </div>
</div>');

-- Game 5
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Hollow Knight',
  'Phiêu lưu trong vương quốc sâu thẳm Hallownest đầy sinh vật bí ẩn và thử thách.',
  300000, 190000,
  250,
  'UAO2urG23S4',
  'uploads/steam_hollow-knight-detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (5, 'Hollow Knight – Thế giới sâu thẳm của côn trùng',
'<p><strong style="color:#8B4513;">Hollow Knight</strong> đưa người chơi vào vương quốc Hallownest đầy bí ẩn và sinh vật kỳ dị.</p>
<p><span style="color:#1E90FF;">Khám phá</span> hang động, thành phố bị bỏ hoang, và chiến đấu với kẻ thù mạnh mẽ.</p>
<p><span style="color:#FFD700;">Điểm nổi bật:</span></p>
<ul>
<li><span style="color:#FF6347;">Gameplay tinh tế:</span> Khám phá, nhảy, chiến đấu và giải đố.</li>
<li><span style="color:#32CD32;">Đồ họa 2D đẹp mắt:</span> Phong cách gothic độc đáo.</li>
<li><span style="color:#1E90FF;">Thế giới mở kết nối:</span> Mỗi khu vực đều liên kết và mở ra bí mật.</li>
<li><span style="color:#FFA500;">Âm nhạc tuyệt vời:</span> Nhạc nền tạo cảm giác phiêu lưu và căng thẳng.</li>
</ul>
<p><em style="color:#FF4500;">Hollow Knight</em> là một kiệt tác indie khó quên, vừa thử thách vừa tuyệt đẹp.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (5, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 7</li>
            <li>CPU: Intel Core 2 Duo E5200</li>
            <li>RAM: 4 GB</li>
            <li>GPU: NVIDIA GeForce 9600 GT / AMD HD 5670</li>
            <li>HDD: 9 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i5-4690</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 660 / AMD HD 7870</li>
            <li>HDD: 9 GB</li>
        </ul>
    </div>
</div>');

-- Game 6
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Baldur’s Gate 3',
  'Trải nghiệm RPG theo phong cách Dungeons & Dragons với hàng trăm lựa chọn ảnh hưởng đến cốt truyện.',
  1300000, 950000,
  640,
  '1T22wNvoNiU',
  'uploads/steam_baldur_gate_3_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (6, 'Baldur’s Gate 3 – Tự do tuyệt đối trong thế giới D&D',
'<p><strong style="color:#FF6347;">Baldur’s Gate 3</strong> đưa bạn vào thế giới Dungeons & Dragons, nơi mọi quyết định ảnh hưởng đến cốt truyện.</p>
<p><span style="color:#32CD32;">Tạo nhân vật</span>, phiêu lưu cùng nhóm, và khám phá hàng trăm nhiệm vụ đa dạng.</p>
<p><span style="color:#FFD700;">Điểm nổi bật:</span></p>
<ul>
<li><span style="color:#1E90FF;">Hệ thống chiến đấu turn-based:</span> Chiến lược, phép thuật và vũ khí đa dạng.</li>
<li><span style="color:#FF4500;">Tùy chỉnh nhân vật:</span> Chọn chủng tộc, lớp, và kỹ năng theo ý muốn.</li>
<li><span style="color:#32CD32;">Thế giới mở rộng lớn:</span> Kết nối nhiều khu vực với nhiệm vụ phụ phong phú.</li>
<li><span style="color:#FFA500;">Cốt truyện phong phú:</span> Lựa chọn và hậu quả tạo nên trải nghiệm RPG độc nhất.</li>
</ul>
<p><em style="color:#00CED1;">Baldur’s Gate 3</em> là RPG sâu sắc và tự do, thích hợp cho fan D&D.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (6, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel i5-4690 / AMD FX 4350</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 780 / AMD R9 290</li>
            <li>HDD: 70 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel i7-7700 / AMD Ryzen 5 3600</li>
            <li>RAM: 16 GB</li>
            <li>GPU: NVIDIA GTX 1070 / AMD RX 580</li>
            <li>SSD: 70 GB</li>
        </ul>
    </div>
</div>');

-- Game 7
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Stardew Valley',
  'Bắt đầu cuộc sống mới tại nông trại nhỏ, trồng trọt, chăn nuôi và xây dựng mối quan hệ trong thị trấn.',
  250000, 150000,
  900,
  'ot7uXNQskhs',
  'uploads/steam_stardew_valley_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (7, 'Stardew Valley – Cuộc sống mộng mơ nơi nông trại',
'<p><strong style="color:#32CD32;">Stardew Valley</strong> đưa bạn đến Pelican Town, nơi cuộc sống yên bình và nông trại đầy màu sắc.</p>
<p><span style="color:#FFD700;">Trồng trọt, chăn nuôi</span>, tham gia lễ hội, kết bạn với NPC và xây dựng mối quan hệ.</p>
<p><span style="color:#FF4500;">Điểm nổi bật:</span></p>
<ul>
<li><span style="color:#1E90FF;">Thế giới sinh động:</span> Bốn mùa, các sự kiện và thị trấn nhộn nhịp.</li>
<li><span style="color:#32CD32;">Hoạt động đa dạng:</span> Câu cá, khai thác khoáng sản, nấu ăn và chế tạo.</li>
<li><span style="color:#FFA500;">Nhân vật đáng nhớ:</span> Mỗi NPC đều có câu chuyện và tính cách riêng.</li>
<li><span style="color:#FF6347;">Thời gian trôi chậm:</span> Cho phép bạn tận hưởng nhịp sống nông trại thoải mái.</li>
</ul>
<p><em style="color:#00CED1;">Stardew Valley</em> là game nông trại dễ thương, thư giãn và cuốn hút mọi lứa tuổi.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (7, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 7</li>
            <li>CPU: 2 GHz</li>
            <li>RAM: 2 GB</li>
            <li>GPU: 256 MB VRAM</li>
            <li>HDD: 500 MB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: 2 GHz</li>
            <li>RAM: 4 GB</li>
            <li>GPU: 512 MB VRAM</li>
            <li>HDD: 500 MB</li>
        </ul>
    </div>
</div>');


-- Game 8
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Grand Theft Auto V',
  'Thế giới mở tội phạm khổng lồ với ba nhân vật chính và cốt truyện kịch tính.',
  800000, 520000,
  1500,
  'QkkoHAzjnUs',
  'uploads/steam_gta_5_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (8, 'Grand Theft Auto V – thế giới tội phạm không giới hạn',
        '<p><strong style="color:#FF4500;">Grand Theft Auto V (GTA V)</strong> là một trong những trò chơi hành động thế giới mở nổi tiếng nhất của Rockstar Games. Trò chơi mang đến một trải nghiệm sống động tại thành phố <em style="color:#1E90FF;">Los Santos</em>, nơi người chơi có thể khám phá, thực hiện nhiệm vụ, hoặc tự do gây rối theo cách riêng của mình.</p>

<p><span style="color:#32CD32;">Cốt truyện</span> xoay quanh ba nhân vật chính: <strong>Michael De Santa</strong>, <strong>Franklin Clinton</strong> và <strong>Trevor Philips</strong>. Mỗi nhân vật có câu chuyện, mục tiêu và kỹ năng riêng, tạo nên trải nghiệm đa chiều và liên kết giữa các nhiệm vụ.</p>

<p><span style="color:#FFD700;">Điểm nổi bật của GTA V:</span></p>
<ul>
    <li><span style="color:#FF6347;">Thế giới mở rộng lớn:</span> Los Santos và vùng Blaine County với chi tiết sống động và môi trường đa dạng.</li>
    <li><span style="color:#1E90FF;">Nhiệm vụ phong phú:</span> Hàng loạt nhiệm vụ chính, nhiệm vụ phụ, phi vụ cướp ngân hàng đầy kịch tính.</li>
    <li><span style="color:#32CD32;">Điều khiển linh hoạt:</span> Chuyển đổi giữa ba nhân vật bất kỳ, sử dụng kỹ năng đặc trưng của từng người.</li>
    <li><span style="color:#FF69B4;">Đồ họa chân thực:</span> Chi tiết môi trường, ánh sáng, bóng đổ sống động, đặc biệt trên PC và console thế hệ mới.</li>
    <li><span style="color:#FFA500;">Chế độ trực tuyến:</span> GTA Online cho phép người chơi hợp tác hoặc cạnh tranh với bạn bè và cộng đồng toàn cầu.</li>
</ul>

<p><strong style="color:#00CED1;">GTA V</strong> không chỉ là một trò chơi, mà còn là một trải nghiệm văn hóa, khám phá, và hành động cực kỳ hấp dẫn. Bạn có thể lái xe, bay máy bay, chơi golf, tham gia đua xe, hoặc chỉ đơn giản là lang thang khám phá thành phố rộng lớn.</p>

<p><em style="color:#FF8C00;">Nếu bạn yêu thích thể loại hành động, phiêu lưu, và tự do khám phá, GTA V chắc chắn sẽ mang đến cho bạn hàng giờ trải nghiệm thú vị và đầy kịch tính.</em></p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (8, 'Cấu hình game: ',
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>Hệ điều hành: Windows 10 64-bit</li>
            <li>Bộ xử lý: Intel Core 2 Quad CPU Q6600 / AMD Phenom 9850 Quad-Core</li>
            <li>RAM: 4 GB</li>
            <li>Card đồ họa: NVIDIA 9800 GT 1GB / AMD HD 4870 1GB (DX 10, 10.1, 11)</li>
            <li>Dung lượng trống: 90 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>Hệ điều hành: Windows 10/11 64-bit</li>
            <li>Bộ xử lý: Intel Core i5 3470 / AMD X8 FX-8350</li>
            <li>RAM: 8 GB</li>
            <li>Card đồ họa: NVIDIA GTX 660 2GB / AMD HD 7870 2GB</li>
            <li>Dung lượng trống: 90 GB</li>
        </ul>
    </div>
</div>
<p>🎮 Ghi chú: Nên cài game trên SSD để giảm thời gian load và tăng trải nghiệm mượt mà.</p>');