package com.sa.demo.exoxycarouseldemo.utils;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 * <p>
 * Created on 10/6/20
 */
public class CyclicAdapter extends RecyclerView.Adapter {


    private final RecyclerView.Adapter adapter;

    /**
     * Pass in an adapter to wrap. This nested adapter should contain whatever you want to show
     */
    public CyclicAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;

        super.setHasStableIds(adapter.hasStableIds());
        super.registerAdapterDataObserver(observerDelegate);
    }

    public int getActualItemCount() {
        return adapter.getItemCount();
    }

    /**
     * Do Not Use!!!
     * <p>
     * This returns {@link Integer#MAX_VALUE} in order to create an infinite looping adapter.
     * <p>
     * Use {@link #getActualItemCount()} instead.
     */
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    private int adjustedPosition(int position) {
        return position % getActualItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapter.onBindViewHolder(holder, adjustedPosition(position));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        adapter.onBindViewHolder(holder, adjustedPosition(position), payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return adapter.getItemViewType(adjustedPosition(position));
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
        adapter.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        return adapter.getItemId(adjustedPosition(position));
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        adapter.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return adapter.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        adapter.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        adapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        adapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        adapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        adapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        adapter.unregisterAdapterDataObserver(observer);
    }

    private final RecyclerView.AdapterDataObserver observerDelegate = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            adapter.notifyItemRangeChanged(adjustedPosition(positionStart), itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            adapter.notifyItemRangeChanged(adjustedPosition(positionStart), itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            adapter.notifyItemRangeInserted(adjustedPosition(positionStart), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            adapter.notifyItemRangeRemoved(adjustedPosition(positionStart), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            // Unsupported
        }
    };
}
