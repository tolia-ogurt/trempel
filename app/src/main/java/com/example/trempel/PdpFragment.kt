package com.example.trempel

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.trempel.databinding.PdpFragmentBinding
import com.example.trempel.network.ServiceProvider
import com.example.trempel.network.model.ProductModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class PdpFragment : Fragment() {

    private var _binding: PdpFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PdpFragmentBinding.inflate(inflater, container, false)
        getInfoProduct()
        return binding.root
    }

    private fun getInfoProduct() {
        ServiceProvider.getRetrofitService()?.getProduct()
            ?.enqueue(object : Callback<ProductModel> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<ProductModel>,
                    response: Response<ProductModel>
                ) {
                    binding.tvProductTitle.text = response.body()?.title
                    Glide.with(requireContext()).load(response.body()?.image)
                        .into(binding.ivProduct)
                    binding.tvProductPrice.text = "$${response.body()?.price.toString()}"
                    binding.tvProductDescription.text = response.body()?.description
                    binding.rbProductRating.rating = response.body()?.rating?.rate?.toFloat() ?: 0f
                    binding.tvProductScore.text = response.body()?.rating?.rate.toString()
                    binding.tvReviews.text = "(${response.body()?.rating?.count.toString()})"
                }

                override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                    Log.e(this.javaClass.name, t.toString())
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong, try later.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}