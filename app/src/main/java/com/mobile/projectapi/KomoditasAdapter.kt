package com.mobile.projectapi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mobile.projectapi.database.ProjectApiEntity
import com.mobile.projectapi.databinding.AdapterKomoditasBinding

class KomoditasAdapter(
    private val listKomoditas: ArrayList<ProjectApiEntity>
): RecyclerView.Adapter<KomoditasAdapter.ViewHolder>(), Filterable {

    private var komoditasFilter = ArrayList<ProjectApiEntity>()

    init {
        komoditasFilter = listKomoditas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        AdapterKomoditasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = komoditasFilter[position]
        holder.binding.komoditas.text = data.name?: "-"
    }

    override fun getItemCount() = komoditasFilter.size

    class ViewHolder(val binding: AdapterKomoditasBinding): RecyclerView.ViewHolder(binding.root)

    fun setData(data: List<ProjectApiEntity>) {
        listKomoditas.clear()
        listKomoditas.addAll(data)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    komoditasFilter = listKomoditas
                } else {
                    val komoditasFiltered = ArrayList<ProjectApiEntity>()
                    for (data in listKomoditas) {
                        if (data.name!!.toLowerCase().contains(charSearch.toLowerCase())) {
                            komoditasFiltered.add(data)
                        }
                    }
                    komoditasFilter = komoditasFiltered
                }
                val komoditasFilteredResult = FilterResults()
                komoditasFilteredResult.values = komoditasFilter
                return komoditasFilteredResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                komoditasFilter = p1?.values as ArrayList<ProjectApiEntity>
                notifyDataSetChanged()
            }
        }
    }
}