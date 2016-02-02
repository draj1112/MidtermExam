package sidespell.tech.midtermexam.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sidespell.tech.midtermexam.R;
import sidespell.tech.midtermexam.entities.Album;

/**
 * Created by danica12 on 2/2/2016.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{

    private Context mContext;
    private int         mLayoutId;
    private List<Album> mAlbums;

    public AlbumAdapter(Context context, int layoutId, List<Album> albums) {
        mContext = context;
        mLayoutId = layoutId;
        mAlbums = albums;
    }

    @Override
    public AlbumAdapter.AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new AlbumAdapter.AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumAdapter.AlbumViewHolder holder, int position) {
        Album album = mAlbums.get(position);

        if (album != null) {
            /*if (holder.imgMovie != null) {
                 holder.imgMovie.setImageResource(album.getImageId());
            }*/
            if (holder.tvName != null) {
                holder.tvName.setText(album.getmAlbumName());
            }
            if (holder.tvArtist != null) {
                holder.tvArtist.setText(album.getmAlbumArtist());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView imgMovie;
        public TextView tvName;
        public TextView  tvArtist;

        public AlbumViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            imgMovie = (ImageView) view.findViewById(R.id.imgAlbum);
            tvName = (TextView) view.findViewById(R.id.albumName);
            tvArtist = (TextView) view.findViewById(R.id.albumArtist);
        }
    }
}
