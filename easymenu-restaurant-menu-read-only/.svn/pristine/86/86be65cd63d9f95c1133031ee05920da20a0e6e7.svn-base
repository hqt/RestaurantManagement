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
package biz.easymenu.easymenung.dbtables;
import java.io.File;
import java.io.FileFilter;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.util.Log;
import biz.easymenu.easymenung.DBContentProvider;
import biz.easymenu.easymenung.EasymenuNGActivity;
import biz.easymenu.easymenung.HTTPHelper;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

public class DBSyncManager {
	
	private static final String rpcUri = "emng/syncmanager.rpc.php";
	
	private Context context=null;
	DBContentProvider dbc = null;
	
	public DBSyncManager(Context c){
		context = c;
		dbc = new DBContentProvider(c);
	}
	
	public void close(){
		dbc.close();
	}
	
	public void syncMenus(){
		
		String method = "getMenus";
		String id = "syncmenus-1";
		JSONRPC2Request reqOut = new JSONRPC2Request(method, id);
		String jsonString = reqOut.toString();
		Log.i(EasymenuNGActivity.TAG,jsonString);
		HTTPHelper httph = new HTTPHelper(rpcUri,context);
		httph.setBody(jsonString);
		
		JSONRPC2Response respIn = null;

		try {
			respIn = JSONRPC2Response.parse(httph.executePost());
		} catch (JSONRPC2ParseException e) {
			Log.e(EasymenuNGActivity.TAG,"Error parsing JSONRPC2 response string: "+e.getMessage());
		}

		// Check for success or error
		if (respIn.indicatesSuccess()) {
			
			try {
				dbc.syncMenus(new JSONArray((String)respIn.getResult()));
			} catch (JSONException e) {
				Log.e(EasymenuNGActivity.TAG,"JSON exception: "+e.getMessage());
			}
		}
		else {

			printError(respIn.getError());
			
		}
	}
	
	public void syncItems(){
		
		
		String method = "getItems";
		String id = "syncitems-1";
		JSONRPC2Request reqOut = new JSONRPC2Request(method, id);
		String jsonString = reqOut.toString();
		Log.i(EasymenuNGActivity.TAG,jsonString);
		HTTPHelper httph = new HTTPHelper(rpcUri,context);
		httph.setBody(jsonString);
		
		JSONRPC2Response respIn = null;
		
		try {
			respIn = JSONRPC2Response.parse(httph.executePost());
		} catch (JSONRPC2ParseException e) {
			Log.e(EasymenuNGActivity.TAG,"Error parsing JSONRPC2 response string: "+e.getMessage());
		}

		// Check for success or error
		if (respIn.indicatesSuccess()) {
			
			try {
				dbc.syncItems(new JSONArray((String)respIn.getResult()));
			} catch (JSONException e) {
				Log.e(EasymenuNGActivity.TAG,"JSON exception: "+e.getMessage());
			}
		}
		else {

			printError(respIn.getError());
			
		}
		
	} 
	
	public void syncMenulists(){
		
		String method = "getMenulists";
		String id = "syncmenulists-1";
		JSONRPC2Request reqOut = new JSONRPC2Request(method, id);
		String jsonString = reqOut.toString();
		Log.i(EasymenuNGActivity.TAG,jsonString);
		HTTPHelper httph = new HTTPHelper(rpcUri,context);
		httph.setBody(jsonString);
		
		JSONRPC2Response respIn = null;
		
		try {
			respIn = JSONRPC2Response.parse(httph.executePost());
		} catch (JSONRPC2ParseException e) {
			Log.e(EasymenuNGActivity.TAG,"Error parsing JSONRPC2 response string: "+e.getMessage());
		}

		// Check for success or error
		if (respIn.indicatesSuccess()) {
			
			try {
				dbc.syncMenulists(new JSONArray((String)respIn.getResult()));
			} catch (JSONException e) {
				Log.e(EasymenuNGActivity.TAG,"JSON exception: "+e.getMessage());
			}
		}
		else {

			printError(respIn.getError());
			
		}
	} 
	
	public void syncImages(){
		
		String method = "getImages";
		String id = "syncimages-1";
		JSONRPC2Request reqOut = new JSONRPC2Request(method, id);
		String jsonString = reqOut.toString();
		Log.i(EasymenuNGActivity.TAG,jsonString);
		HTTPHelper httph = new HTTPHelper(rpcUri,context);
		httph.setBody(jsonString);
		
		JSONRPC2Response respIn = null;
		
		
		try {
			respIn = JSONRPC2Response.parse(httph.executePost());
		} catch (JSONRPC2ParseException e) {
			Log.e(EasymenuNGActivity.TAG,"Error parsing JSONRPC2 response string: "+e.getMessage());
		}

		// Check for success or error
		if (respIn.indicatesSuccess()) {
			
			try {
				//Prepare fo image sync
				deleteRecursive(context.getFilesDir());
				dbc.syncImagesTable(new JSONArray((String)respIn.getResult()));
				
			} catch (JSONException e) {
				Log.e(EasymenuNGActivity.TAG,"JSON exception: "+e.getMessage());
			}
		}
		else {

			printError(respIn.getError());
			
		}
		
		
	} 
	
	public void syncCategories(){
		
		String method = "getCategories";
		String id = "synccategories-1";
		JSONRPC2Request reqOut = new JSONRPC2Request(method, id);
		String jsonString = reqOut.toString();
		Log.i(EasymenuNGActivity.TAG,jsonString);
		HTTPHelper httph = new HTTPHelper(rpcUri,context);
		httph.setBody(jsonString);
		
		JSONRPC2Response respIn = null;
		
		try {
			respIn = JSONRPC2Response.parse(httph.executePost());
		} catch (JSONRPC2ParseException e) {
			Log.e(EasymenuNGActivity.TAG,"Error parsing JSONRPC2 response string: "+e.getMessage());
		}

		// Check for success or error
		if (respIn.indicatesSuccess()) {
			
			try {
				dbc.syncCategories(new JSONArray((String)respIn.getResult()));
			} catch (JSONException e) {
				Log.e(EasymenuNGActivity.TAG,"JSON exception: "+e.getMessage());
			}
		}
		else {

			printError(respIn.getError());
			
		}
		
	} 
	
	public void syncConfig(){
		
		String method = "getConfig";
		String id = "syncconfig-1";
		JSONRPC2Request reqOut = new JSONRPC2Request(method, id);
		String jsonString = reqOut.toString();
		Log.i(EasymenuNGActivity.TAG,jsonString);
		HTTPHelper httph = new HTTPHelper(rpcUri,context);
		httph.setBody(jsonString);
		
		JSONRPC2Response respIn = null;
		
		try {
			respIn = JSONRPC2Response.parse(httph.executePost());
		} catch (JSONRPC2ParseException e) {
			Log.e(EasymenuNGActivity.TAG,"Error parsing JSONRPC2 response string: "+e.getMessage());
		}

		// Check for success or error
		if (respIn.indicatesSuccess()) {
			
			try {
				dbc.syncConfig(new JSONArray((String)respIn.getResult()));
			} catch (JSONException e) {
				Log.e(EasymenuNGActivity.TAG,"JSON exception: "+e.getMessage());
			}
		}
		else {

			printError(respIn.getError());
			
		}
		
	} 
	
	public void syncFullMenu(){
		syncMenus();
		syncItems();
		syncCategories();
		syncMenulists();
		syncConfig();
		syncImages();
	}
	
	public void syncData(){
		syncMenus();
		syncItems();
		syncCategories();
		syncMenulists();
	}
	
	private void printError(JSONRPC2Error err){
		Log.e(EasymenuNGActivity.TAG,"The request failed :");
		Log.e(EasymenuNGActivity.TAG,"\terror.code    : " + err.getCode());
		Log.e(EasymenuNGActivity.TAG,"\terror.message : " + err.getMessage());
		Log.e(EasymenuNGActivity.TAG,"\terror.data    : " + err.getData());
	}

	private void deleteRecursive(File fileOrDirectory) {
	    for (File child : fileOrDirectory.listFiles(new MyFileFilter()))
	    	child.delete();
	}
	
	private class MyFileFilter implements FileFilter{

		@Override
		public boolean accept(File pathname) {
			return pathname.getName().endsWith(".img");
		}
		
	}
}
