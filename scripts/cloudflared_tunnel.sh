#!/data/data/com.termux/files/usr/bin/bash
set -e
command -v cloudflared >/dev/null || pkg install cloudflared -y
termux-wake-lock 2>/dev/null || true
cloudflared tunnel --url http://127.0.0.1:8080
