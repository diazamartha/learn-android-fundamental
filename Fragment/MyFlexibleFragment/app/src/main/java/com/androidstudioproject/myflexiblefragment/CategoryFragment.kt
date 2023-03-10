package com.androidstudioproject.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class CategoryFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDetailCategory: Button = view.findViewById(R.id.btn_detail_category)
        btnDetailCategory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_detail_category) {
            val mDetailCategoryFragment = DetailCategoryFragment()

            val mBundle = Bundle()
            mBundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle")
            val description = "Kategori ini akan berisi produk-produk lifestyle"
            // Pada kode di atas kita menggunakan obyek bundle untuk mengirimkan data antar fragment
            // Perhatikan cara yang digunakan sama dengan cara yang telah kita implementasikan sebelumnya di activity

            // Setelah dibuat obyeknya dan data yang mau dikirimkan apa, kita hanya perlu menambahkan sebaris kode berikut:
            mDetailCategoryFragment.arguments = mBundle
            mDetailCategoryFragment.description = description

            // Cara mengambil data yang dikirimkan melalui obyek bundle pada fragment tujuan pun, sangatlah mudah.
            // Cukup memanggil metode getArguments() di fragment DetailCategoryFragment (LIHAT DetailActegoryFragment)

            val mFragmentManager = parentFragmentManager
            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, mDetailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
                addToBackStack(null)
                commit()

            }
        }
    }


}