package com.sa.demo.exoxycarouseldemo.views.preload

import com.airbnb.epoxy.Typed2EpoxyController
import com.sa.demo.exoxycarouseldemo.data.Profile
import com.sa.demo.exoxycarouseldemo.modal.itemList

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 13/6/20
 */
class ImagePreloadController : Typed2EpoxyController<List<Profile>, Boolean>() {
    override fun buildModels(listProfile: List<Profile>?, data2: Boolean?) {
        listProfile?.forEachIndexed { index, profile ->
            itemList {
                id("${profile.image[0]} ${profile.id}")
                imageUrl(profile.image[0])
                preloading(true)
            }
        }
    }
}