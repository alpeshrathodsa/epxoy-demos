package com.sa.demo.exoxycarouseldemo.modal

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.sa.demo.exoxycarouseldemo.R

@EpoxyModelClass(layout = R.layout.item_loader)
abstract class ItemLoaderModel : EpoxyModelWithHolder<ItemLoaderModel.Holder>() {

    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
        }
    }
}
