package com.example.trempel

import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
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

    private var binding: PdpFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PdpFragmentBinding.inflate(inflater, container, false)
        getProductInfo()
        return binding?.root
    }

    private fun getProductInfo() {
        ServiceProvider.getRetrofitService()?.getProduct(PRODUCT_ID)
            ?.enqueue(object : Callback<ProductModel> {
                override fun onResponse(
                    call: Call<ProductModel>,
                    response: Response<ProductModel>
                ) {
                    binding?.run {
                        tvProductTitle.text = response.body()?.title
                        Glide.with(ivProduct).load(response.body()?.image).into(ivProduct)
                        tvProductPrice.text =
                            getString(R.string.product_price_format, response.body()?.price)
                        tvProductDescription.text = response.body()?.description
                        rbProductRating.rating = response.body()?.rating?.rate?.toFloat() ?: 0f
                        tvProductScore.text = SpannableString(
                            getString(
                                R.string.product_score_format,
                                response.body()?.rating?.rate,
                                response.body()?.rating?.count
                            )
                        ).apply {
                            setSpan(
                                StyleSpan(BOLD),
                                0,
                                response.body()?.rating?.rate?.toString().orEmpty().length,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                    Log.e(this.javaClass.name, t.toString())
                    Toast.makeText(context, R.string.error_message_in_toast, Toast.LENGTH_LONG).show()
                }
            })
    }

    companion object {
        private const val PRODUCT_ID = 2
    }
}