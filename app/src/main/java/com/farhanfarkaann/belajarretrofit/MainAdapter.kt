package com.farhanfarkaann.belajarretrofit

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanfarkaann.belajarretrofit.databinding.RecyLayoutBinding
import com.farhanfarkaann.belajarretrofit.model.GetAllCarResponseItem

class MainAdapter(private val onItemCLick: OnClickListener)  : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    private val diffCallback  = object : DiffUtil.ItemCallback<GetAllCarResponseItem>(){
        override fun areItemsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean = oldItem.hashCode() == newItem.hashCode()

    }
    private val differ = AsyncListDiffer(this,diffCallback)
    fun submitData (value : List<GetAllCarResponseItem>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(RecyLayoutBinding.inflate(inflater, parent,false)) }


    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        Log.d("MainAdapter","Image : ${data.image}")

        data.let { holder.bind(data) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder( val binding: RecyLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetAllCarResponseItem) {
            binding.apply {
                tvJudul.text = data.name
                tvHarga.text = "RP" + data.price.toString()
//                Glide.with().load(data.image).into(tvJudul)
                root.setOnClickListener{
                    onItemCLick.onClickItem(data)
                }
            }
            Glide.with(binding.tvImage).load(data.image).into(binding.tvImage)
        }
    }
    interface OnClickListener {
        fun onClickItem(data : GetAllCarResponseItem)
    }
}




