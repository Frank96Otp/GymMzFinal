package com.example.proyectofinalgymmz.onboarding


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalgymmz.databinding.BoardItemBinding

class ViewPagerAdapter(
    private val boardList: List<Board>
    ) : RecyclerView.Adapter<ViewPagerAdapter.BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val binding = BoardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return boardList.size
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.bind(boardList[position])
    }

    inner class BoardViewHolder(private val binding: BoardItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(board: Board) {
            binding.container.setBackgroundColor(ContextCompat.getColor( binding.container.context,board.fondo))
            binding.tvTituloBoard.text = board.titulo
            binding.tvBodyBoard.text = board.descripcion
            binding.ivImgBoard.setImageResource(board.img)

            if(adapterPosition.equals(boardList.size - 1)){
                binding.btnNextBoard.text = "Finalizar"
            }

        }
    }
}