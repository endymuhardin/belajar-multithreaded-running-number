# Contoh Implementasi Running Number #

Running number biasa digunakan untuk menghasilkan nomer yang urut untuk berbagai keperluan, diantaranya:

* nomer surat
* nomer bukti transaksi
* message id (STAN dalam ISO-8583)

Untuk bisa menghasilkan nomer yang urut dengan benar, kita perlu menggunakan isolation level `serializable` dalam mengakses database, sehingga tidak ada user yang mengakses tabel secara bersamaan.


## Menjalankan Database dengan Docker ##

```
docker run --name belajar-locking \
           -e MYSQL_RANDOM_ROOT_PASSWORD=true \
           -e MYSQL_USER=belajar \
           -e MYSQL_PASSWORD=java \
           -e MYSQL_DATABASE=belajarlocking \
           -v "$PWD/mariadb-data:/var/lib/mysql" \
           -p 3306:3306 --rm mariadb:10
```