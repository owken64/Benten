# Benten -弁天-
シンセサイザー
音源 + コントローラ
開発者向けソースコード

##ハードウェア要求
Raspberry Pi 3
Audio interface
USBケーブル
AC電源2本
Android端末 (コントローラ)

## 必要環境
Java JDK
sbt

## 説明
RaspberryPi と オーディオインターフェースをUSBケーブルで接続してください。
シンセサイザーの音源はRaspberry Piで動かしてください。
Raspberry PiのローカルIPv4アドレスへ通信します。
シンセサイザーの音源はScalaで動きます。
オーディオインターフェースのMidi Inからの信号で、オーディオインターフェースのLine Outへ出力信号が送られます。
