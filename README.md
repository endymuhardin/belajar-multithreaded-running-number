# Contoh Implementasi Running Number #

Running number biasa digunakan untuk menghasilkan nomer yang urut untuk berbagai keperluan, diantaranya:

* nomer surat
* nomer bukti transaksi
* message id (STAN dalam ISO-8583)

Untuk bisa menghasilkan nomer yang urut dengan benar, kita perlu menggunakan isolation level `serializable` dalam mengakses database, sehingga tidak ada user yang mengakses tabel secara bersamaan.


