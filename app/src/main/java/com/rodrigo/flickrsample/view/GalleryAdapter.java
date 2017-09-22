package com.rodrigo.flickrsample.view;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodrigo.flickrsample.data.ImageItem;
import com.rodrigo.flickrsample.databinding.ImageItemBinding;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ObservableList<ImageItem> items;
    private OnImageClickedListener onImageClickedListener;

    public GalleryAdapter(@NonNull ObservableList<ImageItem> items) {
        this.items = items;
        this.items.addOnListChangedCallback(onListChangedCallback);
    }

    public void setOnImageClickedListener(OnImageClickedListener onImageClickedListener) {
        this.onImageClickedListener = onImageClickedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ImageItemBinding binding =
                ImageItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ImageItem item = items.get(position);
        if (item.getMedia() != null) {
            String url = item.getMedia().getMedium();
            holder.binding.setUrl(url);
        }
        holder.binding.setLink(item.getLink());

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick(holder.binding.image, item);
            }
        });
    }

    private void onImageClick(View view, ImageItem item) {
        if (onImageClickedListener != null) {
            onImageClickedListener.onImageClicked(view, item);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private ObservableList.OnListChangedCallback<ObservableList<ImageItem>> onListChangedCallback
            = new ObservableList.OnListChangedCallback<ObservableList<ImageItem>>() {
        @Override
        public void onChanged(ObservableList<ImageItem> imageItems) {

        }

        @Override
        public void onItemRangeChanged(ObservableList<ImageItem> imageItems, int i, int i1) {

        }

        @Override
        public void onItemRangeInserted(ObservableList<ImageItem> imageItems, int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(ObservableList<ImageItem> imageItems, int i, int i1, int i2) {

        }

        @Override
        public void onItemRangeRemoved(ObservableList<ImageItem> imageItems, int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart, itemCount);
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageItemBinding binding;

        ViewHolder(ImageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    interface OnImageClickedListener {
        void onImageClicked(View view, ImageItem item);
    }
}
