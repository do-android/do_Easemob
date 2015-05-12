/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package doext.easemob.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import core.helper.DoResourcesHelper;
import core.interfaces.DoIModuleTypeID;
import doext.app.do_HuanXinIM_App;

public class ExpressionAdapter extends ArrayAdapter<String> implements DoIModuleTypeID{

	public ExpressionAdapter(Context context, int textViewResourceId, List<String> objects) {
		super(context, textViewResourceId, objects);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = View.inflate(getContext(), DoResourcesHelper.getIdentifier("row_expression", "layout", this), null);
		}
		
		ImageView imageView = (ImageView) convertView.findViewById(DoResourcesHelper.getIdentifier("iv_expression", "id", this));
		
		String filename = getItem(position);
		//int resId = getContext().getResources().getIdentifier(filename, "drawable", getContext().getPackageName());
		int resId = DoResourcesHelper.getIdentifier(filename, "drawable", this);
		imageView.setImageResource(resId);
		
		return convertView;
	}


	@Override
	public String getTypeID() {
		return do_HuanXinIM_App.getInstance().getModuleTypeID();
	}
	
}
