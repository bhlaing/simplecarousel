package com.simple.carousel

import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView

class ImagePagerAdapter(private val context: Context) : RecyclerView.Adapter<ImagePagerAdapter.ItemViewHolder>() {
    lateinit var items: List<Int>
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(layoutInflater.inflate(R.layout.image_item_view, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position], context.getString(R.string.count_indicator_text, position + 1, items.size))

    override fun onViewAttachedToWindow(holder: ItemViewHolder) {
        holder.onViewAppear()

        super.onViewAttachedToWindow(holder)
    }

     class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private lateinit var indicatorTextView: TextView

        fun bind(@DrawableRes imageId: Int, countIndicatorText: String) {
            view.findViewById<ImageView>(R.id.carouselImageView).apply {
                setImageResource(imageId)
            }

            indicatorTextView = view.findViewById<TextView>(R.id.imagePositionIndicatorTextView).apply {
                text = countIndicatorText
            }
        }

        fun onViewAppear() {
            indicatorTextView.alpha = 1.0f

            fadeAwayIndicatorTextViewWithDelay(2000, 2000)
        }

        private fun fadeAwayIndicatorTextViewWithDelay(fadeDurationInMilliSeconds: Long, delayInMilliSeconds: Long) {
            ObjectAnimator.ofFloat(indicatorTextView, "alpha", 1f, 0f).apply {
                duration = fadeDurationInMilliSeconds
                startDelay = delayInMilliSeconds
            }.start()
        }
    }
}
