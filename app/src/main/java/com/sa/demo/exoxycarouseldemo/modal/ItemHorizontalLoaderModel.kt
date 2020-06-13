package com.sa.demo.exoxycarouseldemo.modal

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.sa.demo.exoxycarouseldemo.R

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 10/6/20
 */

@EpoxyModelClass(layout = R.layout.item_loader_horizontal)
abstract class ItemHorizontalLoaderModel : EpoxyModelWithHolder<ItemHorizontalLoaderModel.Holder>() {

    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
        }
    }
}