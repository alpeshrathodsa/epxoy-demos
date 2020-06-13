package com.sa.demo.exoxycarouseldemo.modal

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.sa.demo.exoxycarouseldemo.R

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 29/5/20
 */
@EpoxyModelClass(layout = R.layout.item_header)
abstract class ItemHeaderModel : EpoxyModelWithHolder<ItemHeaderModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.text = title
    }


    class Holder : EpoxyHolder() {

        lateinit var title: AppCompatTextView

        override fun bindView(itemView: View) {
            title = itemView.findViewById(R.id.title_text)
        }
    }
}