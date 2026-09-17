# CropCastNoVPN

安卓区域投屏方案：发送端自由选定区域，接收端用浏览器观看。
无公网 IP，不弹系统 VPN（用 cloudflared 应用层反向隧道 / WebRTC 信令中转）。
有 root 可选辅助，但核心不依赖 VPN 接口。

当前：工程骨架 + GitHub Actions 编译 debug APK。
后续填充：MediaProjection、MediaCodec/OpenGL 裁剪、WebRTC、信令/WebSocket、观看页。
