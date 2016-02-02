package sidespell.tech.midtermexam.fragments;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import sidespell.tech.midtermexam.BuildConfig;
import sidespell.tech.midtermexam.R;
import sidespell.tech.midtermexam.adapters.AlbumAdapter;
import sidespell.tech.midtermexam.apis.AlbumApi;
import sidespell.tech.midtermexam.entities.Album;

public class MainFragment extends Fragment {

    private EditText mEtAlbum;
    private RecyclerView rcv;
    private ProgressBar mProgressBar;
    private TextView mTvError;

    private String albumname;
    private int rcvID;

    private ImageView mImgIcon;
    private TextView mTvName;
    private TextView mTvArtist;

    private ArrayList<Album> albums;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Find all views
        //mImgIcon = (ImageView) view.findViewById(R.id.);
        mEtAlbum = (EditText) view.findViewById(R.id.etAlbum);
        //mTvName = (TextView) view.findViewById(R.id.albumName);
        //mTvArtist = (TextView) view.findViewById(R.id.albumArtist);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mTvError = (TextView) view.findViewById(R.id.emptyAlbum);
        albumname = mEtAlbum.getText().toString();

        rcv = (RecyclerView) view.findViewById(R.id.recyclerListView);
        rcvID = R.layout.recycler_list_item;
        rcv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(llm);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask();
        fetchWeatherTask.execute("Cebu");
    }

    /**
     * A background running task that retrieves weather data.
     */
    public class FetchWeatherTask extends AsyncTask<String, Void, ArrayList<Album>> {


        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mTvError.setVisibility(View.GONE);
            //mImgIcon.setVisibility(View.GONE);
            //mTvArtist.setVisibility(View.GONE);
            //mTvName.setVisibility(View.GONE);
        }

        @Override
        protected ArrayList<Album> doInBackground(String... params) {
            final int limit = 50;
            final int page = 2;
            if (params.length == 0) {
                return null;
            }



            Uri builtUri = Uri.parse(AlbumApi.BASE_URL).buildUpon()
                    .appendQueryParameter(AlbumApi.PARAM_METHOD, "album-search")
                    .appendQueryParameter(AlbumApi.PARAM_API_KEY, BuildConfig.MIDTERM_EXAM_API_KEY)
                    .appendQueryParameter(AlbumApi.PARAM_ALBUMNAME, albumname)
                    .appendQueryParameter(AlbumApi.PARAM_LIMIT, String.valueOf(limit))
                    .appendQueryParameter(AlbumApi.PARAM_PAGE, String.valueOf(page))
                    .build();

            albums = AlbumApi.getAlbum(builtUri, "GET");
            return albums;
        }

        @Override
        protected void onPostExecute(ArrayList<Album> w) {
            if (w == null) {
                // TODO: Set empty view
                mTvError.setVisibility(View.VISIBLE);
                rcv.setVisibility(View.GONE);
                return;
            }
            else {
                AlbumAdapter adapter = new AlbumAdapter(getContext(), rcvID, albums);
                rcv.setAdapter(adapter);
                if (adapter.getItemCount() == 0) {
                    mTvError.setVisibility(View.VISIBLE);
                } else {
                    mTvError.setVisibility(View.GONE);
                }
            }

            mProgressBar.setVisibility(View.GONE);
            mTvError.setVisibility(View.GONE);
            rcv.setVisibility(View.VISIBLE);

            /*mImgIcon.setImageBitmap(w.getmAlbumIcon());
            mTvArtist.setText(w.get);
            mTvName.setText(w.getmAlbumName());*/

        }
    }
}
