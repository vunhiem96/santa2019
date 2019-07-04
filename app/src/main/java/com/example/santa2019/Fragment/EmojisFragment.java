package com.example.santa2019.Fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.santa2019.Adapter.ChismastEmojisAdapter;
import com.example.santa2019.Model.Image;
import com.example.santa2019.R;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmojisFragment extends Fragment {


    static final String name = "christmas_emoji_";
    private static final String ARG_POSITION = "position";

    String title;
    RecyclerView rvEmojis;
    ChismastEmojisAdapter adapter;
    ArrayList<Image> data = new ArrayList<>();
    private int position;

    public static EmojisFragment newInstance(int position) {
        EmojisFragment f = new EmojisFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.emojis_fragment, container, false);
        rvEmojis = rootView.findViewById(R.id.rv_emojis);
        rvEmojis.setHasFixedSize(true);
        checkCategory();
        return rootView;
    }

    private void checkCategory() {
        if (position == 0) {
            title = "all";
            getDataChristmasEmoji(title);

        } else if (position == 1) {
            title = "action";
            getDataChristmasHats(title);

        } else if (position == 2) {
            title = "best";
            getDataChrismastSanta(title);

        } else if (position == 3) {
            title = "outfit";
            getDataGifts(title);

        } else if (position == 4) {
            title = "outfit";
            getDataSanta(title);

        }else if (position == 5) {
                title = "outfit";
                getDataMerryChristmasSticker(title);
        }else if (position == 6) {
            title = "outfit";
            getDataChristmasTree(title);
        }else if (position == 7) {
            title = "outfit";
            getDataBells(title);
        } else {
            title = "tool";
            getDataBalls(title);
        }
    }

    private void getDataBalls(String title) {
        for (int i = 1; i <= 27; i++) {
            data.add(new Image(R.drawable.christmas_bells_ + i));
        }
        ConfigRV();
    }

    private void getDataBells(String title) {
        for (int i = 1; i <= 27; i++) {
            data.add(new Image(R.drawable.christmas_bells_ + i));
        }
        ConfigRV();
    }

    private void getDataChristmasTree(String title) {
        for (int i = 1; i <= 33; i++) {
            data.add(new Image(R.drawable.christmas_tree_ + i));
        }
        ConfigRV();
    }

    private void getDataMerryChristmasSticker(String title) {
        for (int i = 1; i <= 25; i++) {
            data.add(new Image(R.drawable.sticker_ + i));
        }
        ConfigRV();
    }

    private void getDataSanta(String title) {
        for (int i = 1; i <= 20; i++) {
            data.add(new Image(R.drawable.santa_ + i));
        }
        ConfigRV();
    }

    private void getDataGifts(String title) {
        for (int i = 1; i <= 62; i++) {
            data.add(new Image(R.drawable.christmas_gifts_ + i));
        }
        ConfigRV();
    }


    private void getDataChrismastSanta(String title) {
        for (int i = 1; i <= 39; i++) {
            data.add(new Image(R.drawable.christmas_santa_ + i));
        }
        ConfigRV();
    }


    private void getDataChristmasEmoji(String title) {
        for (int i = 1; i <= 49; i++) {
            data.add(new Image(R.drawable.christmas_emoji_ + i));
        }
        ConfigRV();
    }

    private void getDataChristmasHats(String title) {
        for (int i = 1; i <= 33; i++) {
            data.add(new Image(R.drawable.christmas_hats_ + i));
            ConfigRV();
        }

    }

    private void ConfigRV() {
        GridLayoutManager g = new GridLayoutManager(getActivity(), 4);
        rvEmojis.setLayoutManager(g);
        adapter = new ChismastEmojisAdapter(data, getActivity());
        rvEmojis.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        rvEmojis.setHasFixedSize(true);
        rvEmojis.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        rvEmojis.setItemViewCacheSize(20);
        rvEmojis.setDrawingCacheEnabled(true);



    }

    }






