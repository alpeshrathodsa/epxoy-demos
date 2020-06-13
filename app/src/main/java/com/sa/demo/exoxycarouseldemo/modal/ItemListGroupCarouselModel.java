package com.sa.demo.exoxycarouseldemo.modal;

import com.airbnb.epoxy.Carousel;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelGroup;
import com.sa.demo.exoxycarouseldemo.R;
import com.sa.demo.exoxycarouseldemo.data.Profile;
import com.sa.demo.exoxycarouseldemo.views.carousel.CarouselController;
import com.sa.demo.exoxycarouseldemo.views.carousel.ListCarouselModel_;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 * <p>
 * Created on 10/6/20
 */
public class ItemListGroupCarouselModel extends EpoxyModelGroup {
    public final Profile data;

    public ItemListGroupCarouselModel(Profile profile, Boolean loadMore, CarouselController.AdapterCallbacks callbacks) {
        super(R.layout.item_group_carousel, buildModels(profile, loadMore, callbacks));
        this.data = profile;
        id(profile.getId());
    }

    private static List<EpoxyModel<?>> buildModels(Profile profile, Boolean loadMore, CarouselController.AdapterCallbacks callbacks) {
        ArrayList<EpoxyModel<?>> models = new ArrayList<>();

        List<String> urls = profile.getImage();
        List<ItemListModel_> gridModals = new ArrayList<>();
        for (String url : urls) {
            gridModals.add(
                    new ItemListModel_()
                            .id(url, profile.getId())
                            .imageUrl(url)
                            .preloading(true)
                            .clickListener((model, parentView, clickedView, position) -> {
                                callbacks.onDeleteClicked(profile, position);
                            })
                            .onBind((model, view, position) -> {
                            })
            );
        }

        models.add(new ListCarouselModel_()
                .numViewsToShowOnScreen(1.2F)
                .id(profile.getId())
                .padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
                .models(gridModals)
                .profile(profile)
                .adapterCallback(callbacks)
                .onBind((model, view, position) -> {
                }).onUnbind((model, view) -> {
                })
        );

        //if want to add loader please uncomment this part
        /*models.add(new ItemHorizontalLoaderModel_()
                .id("horizontalLoading")
                .show(loadMore)
        );*/
        return models;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }


}
