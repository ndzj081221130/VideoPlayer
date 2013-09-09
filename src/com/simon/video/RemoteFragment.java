package com.simon.video;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RemoteFragment extends Fragment {
	private List<String> myMusicList = new ArrayList<String>();
	private AutoCompleteTextView autoTv;
	private Button search;
	private ListView listview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflateAndSetupView(inflater, container, savedInstanceState,
				R.layout.remote);
	}

	private View inflateAndSetupView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState, int layoutResourceId) {
		View layout = inflater.inflate(layoutResourceId, container, false);
		autoTv = (AutoCompleteTextView) layout
				.findViewById(R.id.autoCompleteTextView);
		search = (Button) layout.findViewById(R.id.button1);
		listview = (ListView) layout.findViewById(R.id.local_list);
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				playMusic();
			}
		});

		initAutoComplete("history", autoTv);
		getMusicList();
		return layout;
	}

	void getMusicList() {
		ArrayAdapter<String> musicList = new ArrayAdapter<String>(
				getActivity(), R.layout.music_item, myMusicList);
		listview.setAdapter(musicList);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				playMusic();
			}
		});
	}

	/**
	 * 初始化AutoCompleteTextView，最多显示5项提示，使 AutoCompleteTextView在一开始获得焦点时自动提示
	 * 
	 * @param field
	 *            保存在sharedPreference中的字段名
	 * @param auto
	 *            要操作的AutoCompleteTextView
	 */
	private void initAutoComplete(String field, AutoCompleteTextView auto) {
		SharedPreferences sp = getActivity().getSharedPreferences("video_url",
				0);
		String longhistory = sp.getString("history",
				getString(R.string.no_records));
		String[] hisArrays = longhistory.split(",");
//		hisArrays.
		for (String str : hisArrays) {
			if (str.equals("nothing") == false)
				myMusicList.add(str);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, myMusicList);
		// 只保留最近的50条的记录
		if (hisArrays.length > 50) {
			String[] newArrays = new String[50];
			System.arraycopy(hisArrays, 0, newArrays, 0, 50);
			adapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_dropdown_item_1line, newArrays);
		}
		auto.setAdapter(adapter);
		auto.setDropDownHeight(350);
		auto.setThreshold(1);
		auto.setCompletionHint(getString(R.string.recent_records));
		auto.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AutoCompleteTextView view = (AutoCompleteTextView) v;
				if (hasFocus) {
					view.showDropDown();
				}
			}
		});

	}

	void playMusic() {
		Intent intent = new Intent(getActivity(), VideoActivity.class);
		intent.putExtra("path", autoTv.getText().toString().trim());
		intent.putExtra("type", "remote");
		startActivity(intent);
	}
}
