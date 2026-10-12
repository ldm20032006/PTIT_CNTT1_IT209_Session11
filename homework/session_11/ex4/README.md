# Bài 4: Khai báo GitHub Secrets và cấu hình Đăng nhập Container Registry

## 1. Mục tiêu bài thực hành
- Tạo Personal Access Token (PAT) an toàn trên GitHub có thẩm quyền thao tác với Packages (`write:packages`).
- Khai báo Repository Secret `REGISTRY_TOKEN` trên GitHub để bảo mật thông tin xác thực.
- Cấu hình workflow GitHub Actions tự động đăng nhập vào **GitHub Container Registry (ghcr.io)** bằng action `docker/login-action@v3`.
- Bàn giao sản phẩm minh chứng gồm kịch bản `ci.yml`, ảnh chụp Repository Secret và log xác nhận **`Login Succeeded`**.

---

## 2. Sản phẩm bàn giao (Deliverables)

Theo yêu cầu đề bài, sản phẩm nộp bài gồm:

| STT | Thành phần bàn giao | Vị trí / Tên tệp | Trạng thái |
|---|---|---|---|
| 1 | Kịch bản CI Workflow | `.github/workflows/ci.yml` | Đã hoàn thành |
| 2 | Ảnh cấu hình Secret `REGISTRY_TOKEN` | `screenshots/repository_secrets.png` | Cần chụp từ GitHub Web |
| 3 | Ảnh log chi tiết `Login Succeeded` | `screenshots/login_succeeded.png` | Cần chụp từ Tab Actions |

---

## 3. Nội dung kịch bản CI (`.github/workflows/ci.yml`)

```yaml
name: CI - Login GitHub Container Registry

on:
  push:
    branches:
      - main
  workflow_dispatch:

jobs:
  login-ghcr:
    name: Authenticate GHCR
    runs-on: ubuntu-latest

    steps:
      - name: Checkout source code
        uses: actions/checkout@v4

      - name: Đăng nhập vào GitHub Container Registry
        uses: docker/login-action@v3
        with:
          registry: ghcr.io
          username: ${{ github.actor }}
          password: ${{ secrets.REGISTRY_TOKEN }}

      - name: Verify login
        run: echo "GHCR authentication step completed successfully with Login Succeeded!"
```

---

## 4. Các bước thực hiện chi tiết

### Bước 1: Tạo Personal Access Token (classic)
1. Truy cập `https://github.com/settings/tokens`.
2. Chọn **Generate new token (classic)**.
3. Đặt Note: `REGISTRY_TOKEN`.
4. Tích chọn scope: **`write:packages`** (kèm `read:packages`).
5. Nhấn **Generate token** và sao chép token `ghp_...`.

### Bước 2: Thêm Secret vào Repository
1. Vào repository: `Settings` -> `Secrets and variables` -> `Actions`.
2. Nhấn **New repository secret**.
3. Điền Name: `REGISTRY_TOKEN`, Value: mã token vừa tạo.
4. Chụp ảnh màn hình mục Repository Secrets để làm minh chứng 1.

### Bước 3: Đẩy code và chạy GitHub Actions
1. Đảm bảo file `ci.yml` nằm tại thư mục gốc `.github/workflows/ci.yml` để GitHub nhận diện workflow.
2. Commit và push lên branch `main`.

### Bước 4: Kiểm tra log xác nhận `Login Succeeded`
1. Vào tab **Actions** trên GitHub.
2. Mở run workflow gần nhất, chọn step **Đăng nhập vào GitHub Container Registry**.
3. Kiểm tra log hiển thị dòng:
   ```text
   Logging into ghcr.io...
   Login Succeeded
   ```
4. Chụp ảnh màn hình bước này để làm minh chứng 2.

---

## 5. Ảnh chụp màn hình minh chứng bàn giao

### Minh chứng 1: Khởi tạo Repository Secret `REGISTRY_TOKEN`
![Repository Secrets](screenshots/repository_secrets.png)

### Minh chứng 2: Log chi tiết hiển thị `Login Succeeded`
![Login Succeeded](screenshots/login_succeeded.png)
