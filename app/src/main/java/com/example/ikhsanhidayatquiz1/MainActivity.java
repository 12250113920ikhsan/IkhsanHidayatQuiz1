package com.example.ikhsanhidayatquiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNamaPelanggan, etJumlahBarang, etKodeBarang;
    private RadioGroup rgTipeMember;
    private Button btnProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaPelanggan = findViewById(R.id.et_nama_pelanggan);
        etJumlahBarang = findViewById(R.id.et_jumlah_barang);
        etKodeBarang = findViewById(R.id.et_kode_barang);
        rgTipeMember = findViewById(R.id.rg_tipe_member);
        btnProses = findViewById(R.id.btn_proses);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesDetailBelanja();
            }
        });
    }

    private void prosesDetailBelanja() {
        String namaPelanggan = etNamaPelanggan.getText().toString();
        int jumlahBarang = Integer.parseInt(etJumlahBarang.getText().toString());
        String kodeBarang = etKodeBarang.getText().toString();

        // Mendapatkan tipe member yang dipilih
        int selectedRadioButtonId = rgTipeMember.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
       final String tipeMember = selectedRadioButton.getText().toString();

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


        // Mengirim data ke aktivitas detail belanja
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("nama_pelanggan", namaPelanggan);
        intent.putExtra("jumlah_barang", jumlahBarang);
        intent.putExtra("kode_barang", kodeBarang);
        intent.putExtra("tipe_member", tipeMember);
        startActivity(intent);
    }
}