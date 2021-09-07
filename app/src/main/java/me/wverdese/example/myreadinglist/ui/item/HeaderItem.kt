package me.wverdese.example.myreadinglist.ui.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import me.wverdese.example.myreadinglist.R
import me.wverdese.example.myreadinglist.databinding.ItemBookBinding
import me.wverdese.example.myreadinglist.databinding.ItemHeaderBinding
import me.wverdese.example.myreadinglist.util.load


class HeaderItem(
    private val title: String
) : BindableItem<ItemHeaderBinding>() {

    override fun getLayout(): Int = R.layout.item_header

    override fun initializeViewBinding(view: View): ItemHeaderBinding = ItemHeaderBinding.bind(view)

    override fun bind(viewBinding: ItemHeaderBinding, position: Int) {
        viewBinding.titleTextView.text = title
    }
}
