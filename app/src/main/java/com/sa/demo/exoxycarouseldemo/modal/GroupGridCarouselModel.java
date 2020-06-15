package com.sa.demo.exoxycarouseldemo.modal;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelGroup;
import com.sa.demo.exoxycarouseldemo.R;
import com.sa.demo.exoxycarouseldemo.data.Profile;
import com.sa.demo.exoxycarouseldemo.views.carousel.GridCarouselModel_;

import java.util.ArrayList;
import java.util.List;

public class GroupGridCarouselModel extends EpoxyModelGroup {
    public final Profile data;

    public GroupGridCarouselModel(Profile profile) {
        super(R.layout.item_group_carousel, buildModels(profile));
        this.data = profile;
        id(profile.getId());
    }

    private static EpoxyModel<?> buildModels(Profile proile) {
        List<String> urls = proile.getImage();


        List<ItemGridModel_> gridModals = new ArrayList<>();
        for (String url : urls) {
            gridModals.add(
                    new ItemGridModel_()
                            .id(url, proile.getId())
                            .imageUrl(url)
                            .preloading(true)
                            .onBind((model, view, position) -> {
                            })
            );
        }

        return new GridCarouselModel_()
                .numViewsToShowOnScreen(2.5F)
                .id(proile.getId())
                .models(gridModals)
                .onBind((model, view, position) -> {

                });
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
