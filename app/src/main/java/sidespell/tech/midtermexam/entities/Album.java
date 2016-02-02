package sidespell.tech.midtermexam.entities;

import android.graphics.Bitmap;

/**
 * Created by danica12 on 2/2/2016.
 */
public class Album {
    private Bitmap mAlbumIcon;
    private String mIconSrc;
    private String mAlbumName;
    private String mAlbumArtist;

    public Bitmap getmAlbumIcon() {
        return mAlbumIcon;
    }

    public void setmAlbumIcon(Bitmap mAlbumIcon) {
        this.mAlbumIcon = mAlbumIcon;
    }

    public String getmIconSrc() {
        return mIconSrc;
    }

    public void setmIconSrc(String mIconSrc) {
        this.mIconSrc = mIconSrc;
    }

    public String getmAlbumName() {
        return mAlbumName;
    }

    public void setmAlbumName(String mAlbumName) {
        this.mAlbumName = mAlbumName;
    }

    public String getmAlbumArtist() {
        return mAlbumArtist;
    }

    public void setmAlbumArtist(String mAlbumArtist) {
        this.mAlbumArtist = mAlbumArtist;
    }
}
