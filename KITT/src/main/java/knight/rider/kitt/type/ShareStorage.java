package knight.rider.kitt.type;

import android.os.Environment;

public enum ShareStorage {
    DIRECTORY_DOWNLOADS(Environment.DIRECTORY_DOWNLOADS),
    DIRECTORY_MOVIES(Environment.DIRECTORY_MOVIES),
    DIRECTORY_PICTURES(Environment.DIRECTORY_PICTURES),
    DIRECTORY_MUSIC(Environment.DIRECTORY_MUSIC);

    private final String dir;

    ShareStorage(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }
}
