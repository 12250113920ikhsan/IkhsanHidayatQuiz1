package com.example.ikhsanhidayatquiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNamaPelanggan, tvJumlahBarang, tvKodeBarang, tvNamaBarang, tvHargaBarang,
            tvTipeMember, tvHargaTotal, tvDiskonMember, tvHargaDiskon, tvJumlahBayar;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNamaPelanggan = findViewById(R.id.tv_nama_pelanggan);
        tvJumlahBarang = findViewById(R.id.tv_jumlah_barang);
        tvKodeBarang = findViewById(R.id.tv_kode_barang);
        tvNamaBarang = findViewById(R.id.tv_nama_barang);
        tvHargaBarang = findViewById(R.id.tv_harga_barang);
        tvTipeMember = findViewById(R.id.tv_tipe_member);
        tvHargaTotal = findViewById(R.id.tv_harga_total);
        tvDiskonMember = findViewById(R.id.tv_diskon_member);
        tvHargaDiskon = findViewById(R.id.tv_harga_diskon);
        tvJumlahBayar = findViewById(R.id.tv_jumlah_bayar);
        btnShare = findViewById(R.id.btn_share);

        // Mengambil data yang dikirim dari MainActivity
        Intent intent = getIntent();
        String namaPelanggan = intent.getStringExtra("nama_pelanggan");
        int jumlahBarang = intent.getIntExtra("jumlah_barang", 0);
        String kodeBarang = intent.getStringExtra("kode_barang");
        String tipeMember = intent.getStringExtra("tipe_member");

        // Mendapatkan harga barang berdasarkan kode barang
        String namaBarang;
        long hargaBarang;
        switch (kodeBarang) {
            case "LV3":
                namaBarang = "Lenovo V14 Gen 3";
                hargaBarang = 6666666;
                break;
            case "AA5":
                namaBarang = "Acer Aspire 5";
                hargaBarang = 9999999;
                break;
            case "MP3":
                namaBarang = "Macbook Pro M3";
                hargaBarang = 28999999;
                break;
            default:
                namaBarang = "Unknown";
                hargaBarang = 0;
        }

        final double diskon;
        switch (tipeMember) {
            case "Gold":
                diskon = 0.1;
                break;
            case "Silver":
                diskon = 0.05;
                break;
            case "Biasa":
                diskon = 0.02;
                break;
            default:
                diskon = 0;
        }

        // Menghitung total harga
        long totalHarga = hargaBarang * jumlahBarang;

        // Menghitung harga diskon
        long hargaDiskon = (long) (totalHarga * diskon);

        // Menghitung jumlah bayar
        long jumlahBayar = totalHarga - hargaDiskon;

        // Menampilkan data ke TextView
        tvNamaPelanggan.setText("Nama Pelanggan: " + namaPelanggan);
        tvJumlahBarang.setText("Jumlah Barang: " + jumlahBarang);
        tvKodeBarang.setText("Kode Barang: " + kodeBarang);
        tvNamaBarang.setText("Nama Barang: " + namaBarang);
        tvHargaBarang.setText("Harga Barang: " + hargaBarang);
        tvTipeMember.setText("Tipe Member: " + tipeMember);
        tvHargaTotal.setText("Total Harga: " + totalHarga);
        tvDiskonMember.setText("Diskon Member: " + (diskon * 100) + "%");
        tvHargaDiskon.setText("Harga Diskon: " + hargaDiskon);
        tvJumlahBayar.setText("Jumlah Bayar: " + jumlahBayar);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDetailBelanja(namaPelanggan, jumlahBarang, kodeBarang, namaBarang, hargaBarang, tipeMember, totalHarga, diskon, hargaDiskon, jumlahBayar);
            }
        });
    }

    private void shareDetailBelanja(String namaPelanggan, int jumlahBarang, String kodeBarang, String namaBarang,
                                    long hargaBarang, String tipeMember, long totalHarga, double diskon,
                                    long hargaDiskon, long jumlahBayar) {
        String detailBelanja = "Nama Pelanggan: " + namaPelanggan + "\n" +
                "Jumlah Barang: " + jumlahBarang + "\n" +
                "Kode Barang: " + kodeBarang + "\n" +
                "Nama Barang: " + namaBarang + "\n" +
                "Harga Barang: " + hargaBarang + "\n" +
                "Tipe Member: " + tipeMember + "\n" +
                "Total Harga: " + totalHarga + "\n" +
                "Diskon Member: " + (diskon * 100) + "%" + "\n" +
                "Harga Diskon: " + hargaDiskon + "\n" +
                "Jumlah Bayar: " + jumlahBayar;

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, detailBelanja);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}