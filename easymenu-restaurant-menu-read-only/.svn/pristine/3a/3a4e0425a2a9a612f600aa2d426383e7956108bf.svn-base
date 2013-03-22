/*******************************************************************************
 * Copyright 2012 Gianrico D'Angelis  -- gianrico.dangelis@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package biz.easymenu.easymenung;

import android.content.Context;
import android.content.SharedPreferences;

public class EmPrefs {

	private static final String SHARED_PREFS_NAME = "settings";
	
	private String sid = null;
	SharedPreferences settings = null;
	SharedPreferences.Editor editor = null;
	Context context = null;
	
	EmPrefs(Context c){
		
		context = c;
		settings = c.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
		editor = settings.edit();
	}
	
	public void commit(){
		editor.commit();
	}

	public String getSid() {
		return settings.getString("sid", "");
	}

	public void setSid(String sid) {
		editor.putString("sid", sid);
	}
	
	public String getValue(String key){
		return settings.getString(key, "");
	}
	
	public void setKeyValue(String key, String value){
		editor.putString(key, value);
	}
}
