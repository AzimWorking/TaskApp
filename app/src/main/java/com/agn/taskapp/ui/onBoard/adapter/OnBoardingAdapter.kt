package com.agn.taskapp.ui.onBoard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.agn.taskapp.databinding.ItemOnboardingBinding
import com.agn.taskapp.model.OnBoard
import com.agn.taskapp.utils.loadImage

// передача методов example onClick
// unit не возвращаемый метод
class OnBoardingAdapter(private val onClick: (OnBoard) -> Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

   private val data = arrayListOf(
        OnBoard(
            title = "Title 1",
            desc = "Desc 1",
            "https://w7.pngwing.com/pngs/185/850/png-transparent-task-computer-icons-tasks-s-angle-text-microsoft-office-thumbnail.png"
        ),
        OnBoard(
            title = "Title 2",
            desc = "Desc 2",
            "https://d57439wlqx3vo.cloudfront.net/iblock/03c/03c3f81942edbe28e073436c1e47b227/64c181f8554dc9a0e27e654be545e45d.png"
        ),
        OnBoard(
            title = "Title 3",
            desc = "Desc 3",
            "https://startinfinity.s3.us-east-2.amazonaws.com/production/blog/post/5/main/1SvzKctRCi8bwB0QPdOZkBP0pRhsOqZpl0wjs6y0.png"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

  inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.btnStart.setOnClickListener {
                onClick(onBoard)
            }
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.desc
            binding.ivBoard.loadImage(onBoard.imge)

        }

    }
}