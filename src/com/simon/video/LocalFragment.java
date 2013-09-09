package com.simon.video;

import java.util.ArrayList;
import java.util.List;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LocalFragment extends Fragment {
	private List<String> _myMusicList ;
	private List<MusicData> _musDatas ;
	private ListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflateAndSetupView(inflater, container, savedInstanceState,
				R.layout.local);
	}

//	private 
	@Override  
    public void onResume() {  
        super.onResume();  
        musicList();
//        Log.d("Fragment 1", "onResume");  
    }  
	
	private View inflateAndSetupView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState, int layoutResourceId) {
		View layout = inflater.inflate(layoutResourceId, container, false);
		listView = (ListView) layout.findViewById(R.id.local_list);
		musicList();
		return layout;
	}
	
	void musicList() { 
		_myMusicList = new ArrayList<String>();
		_musDatas = getMusicFileList();
		ArrayAdapter<String> musicList = new ArrayAdapter<String>(
				getActivity(), R.layout.music_item, _myMusicList);
		
		listView.setAdapter(musicList);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(),VideoActivity.class);
				intent.putExtra("path", _musDatas.get(arg2).getmMusicPath());
				intent.putExtra("name", _musDatas.get(arg2).getmMusicName());
				intent.putExtra("type", "local");
	 			startActivity(intent);
			}
		});
	}
	
	
	private List<MusicData> getMusicFileList() {
		List<MusicData> list = new ArrayList<MusicData>();

		String[] projection = new String[] { MediaStore.Audio.Media._ID,
				MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DURATION,
				MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ARTIST };

 		Cursor cursor = getActivity().getContentResolver().query(
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null,
				null, null);
		if (cursor != null) {
			cursor.moveToFirst();

			int colNameIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.TITLE);
			int colTimeIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.DURATION);
			int colPathIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.DATA);
			int colArtistIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.ARTIST);

			int fileNum = cursor.getCount();
			for (int counter = 0; counter < fileNum; counter++) {

				MusicData data = new MusicData();
				data.mMusicName = cursor.getString(colNameIndex);
				data.mMusicTime = cursor.getInt(colTimeIndex);
				data.mMusicPath = cursor.getString(colPathIndex);
				data.mMusicAritst = cursor.getString(colArtistIndex);

				list.add(data);
				_myMusicList.add(data.mMusicName);
				cursor.moveToNext();
			}

			cursor.close();
		}
 
 		return list;
	}
}
