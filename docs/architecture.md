# 架构
发送端: MediaProjection -> 裁剪(待做) -> HW Enc -> WebRTC
信令: WebSocket(待做)
无公网: Termux cloudflared quick tunnel 暴露本地 HTTP/WS/viewer
接收端: 浏览器 viewer.html + WebRTC
