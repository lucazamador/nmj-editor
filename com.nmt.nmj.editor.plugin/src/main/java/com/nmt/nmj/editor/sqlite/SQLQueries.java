package com.nmt.nmj.editor.sqlite;

public class SQLQueries {

    /**
     * Select the info needed to display the availables movies in the movies
     * table
     */
    public static final String MOVIES_QUERY = "SELECT VIDEO.VIDEO_ID, VIDEO.TITLE, VIDEO.RELEASE_DATE, VIDEO.RATING, VIDEO.PARENTAL_RATING, VIDEO.TTID, VIDEO.PATH, VIDEO.DETAIL_POSTER, SHOW.THUMBNAIL, VIDEO.SEARCH_TITLE, VIDEO.RUNTIME, VIDEO.SYSTEM, VIDEO.VIDEO_CODEC, VIDEO.RESOLUTION, VIDEO.FPS FROM VIDEO LEFT OUTER JOIN SHOW ON VIDEO.SHOW_ID = SHOW.SHOW_ID WHERE SHOW.SHOW_TYPE=3 ORDER BY VIDEO.TITLE";

    /**
     * Select keyword, casting, directors and extra information of a movie
     */
    public static final String MOVIES_INFORMATION_QUERY = "SELECT VIDEO_ATTR.TYPE, VIDEO_ATTR.VALUE FROM VIDEO_ATTR WHERE VIDEO_ATTR.VIDEO_ID=";

    /**
     * Movies synopsis query
     */
    public static final String MOVIES_SYNOPSIS_QUERY = "SELECT CONTENT FROM VIDEO_SYNOPSIS WHERE VIDEO_ID=";

}
