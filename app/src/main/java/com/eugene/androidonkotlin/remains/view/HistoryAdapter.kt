package com.eugene.androidonkotlin.remains.view

//class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
//    private var data: List<Movie> = arrayListOf()
//
//    fun setData(data: List<Movie>) {
//        this.data = data
//        notifyDataSetChanged()
//    }

//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): HistoryAdapter.RecyclerItemViewHolder {
//        return RecyclerItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_data_base, parent, false) as View)
//    }

//    override fun onBindViewHolder(holder: HistoryAdapter.RecyclerItemViewHolder, position: Int) {
//       holder.bind(data[position])
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }


//    inner class RecyclerItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        fun bind(data: Movie) {
//            if (layoutPosition != RecyclerView.NO_POSITION) {
//                itemView.recycler_view_item.text = String.format("%s %s", data.title, data.rating)
//                itemView.setOnClickListener {
//                    Toast.makeText(itemView.context, "on_click: ${data.title}", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//}