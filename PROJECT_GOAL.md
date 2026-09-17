# 目标
- 发送端：Android，root 可用但不强制
- 捕获：MediaProjection
- 裁剪：自由矩形区域（UI 拖框 -> OpenGL/MediaCodec crop）
- 传输：WebRTC 媒体 + WebSocket 信令；无公网时用 cloudflared quick tunnel 暴露信令/观看页（不创建 VPN 接口）
- 接收端：手机浏览器打开 https://xxx.trycloudflare.com 或信令地址观看
- 编译：GitHub Actions 出 debug APK
