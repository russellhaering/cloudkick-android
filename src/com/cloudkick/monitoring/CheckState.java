/*
 * Licensed to Cloudkick, Inc ('Cloudkick') under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * Cloudkick licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloudkick.monitoring;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CheckState implements Serializable {
	private static final long serialVersionUID = -2297216122693619863L;
	public final String status;
	public final String whence;
	public final String serviceState;
	public final Integer stateColor;

	public CheckState(JSONObject state) throws JSONException {
		// Grab the "whence" if available
		if (state.has("whence")) {
			whence = state.getString("whence");
		}
		else {
			whence = null;
		}

		// Grab the "service_state" if available
		if (state.has("service_state")) {
			serviceState = state.getString("service_state");
		}
		else {
			serviceState = "UNKNOWN";
		}

		// Depending on the serviceState set the status and color
		if (serviceState.equals("OK")) {
			status = state.getString("status");
			stateColor = 0xFFA9F5A9;
		}
		else if (serviceState.equals("WARNING")) {
			status = state.getString("status");
			stateColor = 0xFFFAAC58;
		}
		else if (serviceState.equals("ERROR")) {
			status = state.getString("status");
			stateColor = 0xFFE34648;
		}
		else if (serviceState.equals("NO-AGENT")) {
			status = "Agent Not Connected";
			stateColor = 0xFFBDBDBD;
		}
		else if (serviceState.equals("UNKNOWN")) {
			status = "No Data Available";
			stateColor = 0xFFBDBDBD;
		}
		else {
			Log.e("Check", "Unknown Service State: " + serviceState);
			status = state.getString("status");
			stateColor =  0xFFBDBDBD;
		}
	}
}