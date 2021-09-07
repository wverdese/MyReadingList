package me.wverdese.example.myreadinglist.ui.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import me.wverdese.example.myreadinglist.R
import me.wverdese.example.myreadinglist.databinding.ItemBookBinding
import me.wverdese.example.myreadinglist.util.load


class BookItem(
    private val state: BookItemState
) : BindableItem<ItemBookBinding>() {

    override fun getLayout(): Int = R.layout.item_book

    override fun initializeViewBinding(view: View): ItemBookBinding = ItemBookBinding.bind(view)

    override fun bind(viewBinding: ItemBookBinding, position: Int) {
        with(state) {
            viewBinding.apply {
                coverImageView.load(coverImageUrl)
                titleTextView.text = titleText
                subtitleTextView.text = subtitleText
                container.setOnClickListener { onViewTapped?.invoke() }
            }
        }
    }
}
